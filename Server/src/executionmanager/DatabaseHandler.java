package executionmanager;

import data.StudyGroup;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class DatabaseHandler {
    private static final Logger log = Logger.getLogger(DatabaseHandler.class);
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;
    private static final String ADD_USER_REQUEST = "INSERT INTO USERS (name, password) VALUES (?, ?)";
    private static final String CHECK_USERNAME = "SELECT COUNT(*) FROM USERS WHERE name = ?";
    private static final String CHECK_LOGIN = "SELECT COUNT(*) FROM USERS WHERE name = ? AND password = ?";

    private static final String SAVE_USER ="INSERT INTO study_groups (id, name, coord_x, coord_y, creation_date, students_count, form_of_education, semester, admin_name, weight, colour, loc_x, loc_y, loc_z, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name = VALUES(name), coord_x = VALUES(coord_x), coord_y = VALUES(coord_y), creation_date = VALUES(creation_date), students_count = VALUES(students_count), form_of_education = VALUES(form_of_education), semester = VALUES(semester), admin_name = VALUES(admin_name), weight = VALUES(weight), colour = VALUES(colour), loc_x = VALUES(loc_x), loc_y = VALUES(loc_y), loc_z = VALUES(loc_z), username = VALUES(username)";
    public void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            log.debug("Connection to: " + url + " successful");
        } catch (SQLException e) {

            log.error("Connection to: " + url + "failed: " + e.getMessage());
            System.exit(1);
        }
    }

    public boolean registerUser(String username, String password) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_USER_REQUEST)) {
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("User registered successfully: " + username);
            } else {
                log.error("Failed to register user: " + username);
                return false;
            }
        } catch (SQLException e) {
            log.error("Error occurred while registering user: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkUsername(String name) {
        try (PreparedStatement statement = connection.prepareStatement(CHECK_USERNAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            log.error("Error occurred while checking username: " + e.getMessage());
        }
        return false;
    }

    public boolean loginUser(String name, String password) {
        try (PreparedStatement statement = connection.prepareStatement(CHECK_LOGIN)) {
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            log.error("Error occurred while checking login: " + e.getMessage());
        }
        return false;
    }

    public List<StudyGroup> getAllStudyGroups() {
        List<StudyGroup> studyGroups = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM study_groups")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int coordX = resultSet.getInt("coord_x");
                int coordY = resultSet.getInt("coord_y");
                String creationDate = resultSet.getString("creation_date");
                int studentsCount = resultSet.getInt("students_count");
                String formOfEducation = resultSet.getString("form_of_education");
                String semester = resultSet.getString("semester");
                String adminName = resultSet.getString("admin_name");
                int weight = resultSet.getInt("weight");
                String colour = resultSet.getString("colour");
                int locX = resultSet.getInt("loc_x");
                int locY = resultSet.getInt("loc_y");
                int locZ = resultSet.getInt("loc_z");
                String username = resultSet.getString("username");

                StudyGroup studyGroup = new StudyGroup(id, name, coordX, coordY, creationDate, studentsCount, formOfEducation, semester, adminName, weight, colour, locX, locY, locZ, username);
                studyGroups.add(studyGroup);
            }
        } catch (SQLException e) {
            log.error("Error occurred while getting all study groups: " + e.getMessage());
        }
        return studyGroups;
    }



    private void saveStudyGroup(StudyGroup studyGroup) throws SQLException {
        try (PreparedStatement checkStatement = connection.prepareStatement("SELECT id FROM study_groups WHERE id = ?")) {
            checkStatement.setInt(1, (int) studyGroup.getId());
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // Запись с таким id уже существует, выполняем операцию UPDATE
                try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE study_groups SET name = ?, coord_x = ?, coord_y = ?, creation_date = ?, students_count = ?, form_of_education = ?, semester = ?, admin_name = ?, weight = ?, colour = ?, loc_x = ?, loc_y = ?, loc_z = ?, username = ? WHERE id = ?")) {
                    updateStatement.setString(1, studyGroup.getName());
                    updateStatement.setDouble(2, studyGroup.getCoordinates().getX());
                    updateStatement.setInt(3, Math.toIntExact(studyGroup.getCoordinates().getY()));
                    updateStatement.setString(4, studyGroup.getCreationDate());
                    updateStatement.setInt(5, studyGroup.getStudentsCount());
                    updateStatement.setString(6, studyGroup.getFormOfEducation().toString());
                    updateStatement.setString(7, studyGroup.getSemester().toString());
                    updateStatement.setString(8, studyGroup.getGroupAdmin().getName());
                    updateStatement.setInt(9, Math.toIntExact(studyGroup.getGroupAdmin().getWeight()));
                    updateStatement.setString(10, studyGroup.getGroupAdmin().getEyeColor().toString());
                    updateStatement.setObject(11, studyGroup.getGroupAdmin().getLocation().getX());
                    updateStatement.setInt(12, studyGroup.getGroupAdmin().getLocation().getY());
                    updateStatement.setObject(13, studyGroup.getGroupAdmin().getLocation().getZ());
                    updateStatement.setObject(14, studyGroup.getUsername());
                    updateStatement.setInt(15, (int) studyGroup.getId());

                    updateStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO study_groups (id, name, coord_x, coord_y, creation_date, students_count, form_of_education, semester, admin_name, weight, colour, loc_x, loc_y, loc_z, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    insertStatement.setInt(1, (int) studyGroup.getId());
                    insertStatement.setString(2, studyGroup.getName());
                    insertStatement.setDouble(3, studyGroup.getCoordinates().getX());
                    insertStatement.setInt(4, Math.toIntExact(studyGroup.getCoordinates().getY()));
                    insertStatement.setString(5, studyGroup.getCreationDate());
                    insertStatement.setInt(6, studyGroup.getStudentsCount());
                    insertStatement.setString(7, studyGroup.getFormOfEducation().toString());
                    insertStatement.setString(8, studyGroup.getSemester().toString());
                    insertStatement.setString(9, studyGroup.getGroupAdmin().getName());
                    insertStatement.setInt(10, Math.toIntExact(studyGroup.getGroupAdmin().getWeight()));
                    insertStatement.setString(11, studyGroup.getGroupAdmin().getEyeColor().toString());
                    insertStatement.setObject(12, studyGroup.getGroupAdmin().getLocation().getX());
                    insertStatement.setInt(13, studyGroup.getGroupAdmin().getLocation().getY());
                    insertStatement.setObject(14, studyGroup.getGroupAdmin().getLocation().getZ());
                    insertStatement.setObject(15, studyGroup.getUsername());

                    insertStatement.executeUpdate();
                }
            }
        }
    }


    public void save(List<StudyGroup> groups) throws SQLException {
        for(var it : groups){
            saveStudyGroup(it);
        }
    }
}
