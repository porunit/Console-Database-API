<div class="relative flex w-[calc(100%-50px)] flex-col gap-1 md:gap-3 lg:w-[calc(100%-115px)]">
    <div class="flex flex-grow flex-col gap-3">
        <div class="min-h-[20px] flex flex-col items-start gap-3 overflow-x-auto whitespace-pre-wrap break-words">
            <div class="markdown prose w-full break-words dark:prose-invert light">
                <h1>Study-Group-Manager</h1>
                <p>Study-Group-Manager - это консольное приложение,
                    разработанное на чистом Java, предназначенное для управления
                    базой данных учебных групп. Приложение построено на
                    клиент-серверной архитектуре с использованием UDP и
                    обеспечивает многопоточную обработку запросов. В качестве
                    базы данных используется PostgreSQL.</p>
                <h2>Особенности</h2>
                <ul>
                    <li><p><strong>Клиент-серверная архитектура</strong>:
                        Использует протокол UDP для обмена данными между
                        клиентом и сервером.</p></li>
                    <li><p><strong>Многопоточность</strong>: Обеспечивает
                        параллельную обработку данных и запросов.</p></li>
                    <li><p><strong>База данных</strong>: PostgreSQL для
                        надежного и быстрого хранения данных групп учащихся.</p>
                    </li>
                    <li><p><strong>Авторизация</strong>: Встроенная система
                        авторизации для обеспечения безопасности данных.</p>
                    </li>
                </ul>
                <h2>Установка</h2>
                <p>Для запуска приложения вам потребуется Java Runtime
                    Environment (JRE). Если у вас его нет, установите его
                    первым.</p>
                <ol>
                    <li>Скачайте JAR-файлы из репозитория.</li>
                    <li>Откройте консоль или терминал в директории, содержащей
                        JAR-файл.
                    </li>
                    <li>Запустите приложение с помощью команды:</li>
                </ol>
                <pre><div class="bg-black rounded-md mb-4"><div
                        class="p-4 overflow-y-auto"><code
                        class="!whitespace-pre hljs language-bash">java -jar server.jar
</code></div><code
                        class="!whitespace-pre hljs language-bash">java -jar client.jar
</code></div></div></pre>
                <h2>Использование</h2>
                <p>После запуска приложения следуйте инструкциям на экране для
                    выполнения различных операций, таких как добавление,
                    удаление или изменение информации о группах учащихся.</p>
            </div>
        </div>
    </div>
