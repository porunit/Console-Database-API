package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;

public class InsertAtCommand extends Command {
    /**
     * Action for <b>insert_at</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        var argument = commandData.arg();
        try {
            int index = Integer.parseInt(argument);
            if (index >= 0 && CollectionManager.isStackEmpty() ||
                    index > CollectionManager.getAmountElements() + 1 && !CollectionManager.isStackEmpty())
                System.out.println("index bigger than must be");
            else {
                CollectionManager.insertAt(index, commandData.group());
                commandData.outputHandler().print("Element added at index " + index);
            }
        } catch (NumberFormatException | NullPointerException e) {
            commandData.outputHandler().print("Wrong index format");
        }
    }

    @Override
    public String getDescription() {
        return "insert_at index {element} : добавить новый элемент в заданную позицию";
    }
}
