package helpers;

import commands.*;
import general.*;
import message.MessageColor;
import message.Messages;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс Парсер(Parser). Делает парсинг команд
 */
public class CommandReader {
    private static final ArrayList<String> listOfPaths = new ArrayList<>();
    private static boolean isRec = false;
    private static boolean isExecuteScript = false;
    private static final ArrayList<String> listOfCommands = new ArrayList<>();
    private static String argument = "";
    private final ArrayList<String> scriptPaths = new ArrayList<>();
    private final String[] fields = new String[12];


    /**
     * Метод для парсинга и запуска команды
     *
     * @param input аргументы команды
     * @param login
     */
    public void parseCommand(String[] input, DatagramSocket socket, SocketAddress address, Scanner scanner,String login) {
        String commandKey = input[0];
        String[] ar = Arrays.copyOfRange(input, 1, input.length);
        switch (commandKey) {
            case "help":
                send(new HelpCommand(), socket, address);
                receive(socket);
                break;
            case "info":
                send(new InfoCommand(), socket, address);
                receive(socket);
                break;
            case "clear":
                send(new ClearCommand(login), socket, address);
                receive(socket);
                break;
            case "print_unique_semester":
                send(new PrintSemesterCommand(), socket, address);
                receive(socket);
                break;
            case "update" :
                try {
                    //Проверяем на execute_script
                    try {
                        //Проверяем на execute_script
                        if (isExecuteScript) send(new UpdateCommand(Long.parseLong(argument), addGroup(login),login), socket, address);
                        else send(new UpdateCommand(Long.parseLong(ar[0]), add(ar,login),login), socket, address);
                        receive(socket);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Недостаточно аргументов");
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный формат ключа");
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Недостаточно аргументов");
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат ключа");
                }
                break;
            case "show":
                send(new ShowCommand(), socket, address);
                receive(socket);
                break;
            case "add":
                if (isExecuteScript) {send(new AddCommand(addGroup(login),login), socket, address);}
                else{
                    send(new AddCommand(add(ar, login),login), socket, address);}
                receive(socket);
                break;
            case "addmin":
                send(new AddMinCommand(add(ar,login)), socket, address);
                receive(socket);
            case "min_by_id":
                send(new MinByIdCommand(), socket, address);
                receive(socket);
                break;
            case "execute_script":
                isExecuteScript = true;
                ArrayList<String> script = new ArrayList<>();
                //script.clear();
                try{
                    script=readScript(ar[0]);

                scriptPaths.add(ar[0]);
                if (checkRecurssionInScript(script, scriptPaths)) {
                    System.out.println("Рекурсия");
                }
                else {
                    try {
                        readWholeScript(script); //Читаем полный скрипт и записываем в listOfCommands
                        for (int i = 0; i < listOfCommands.size(); i++) {
                            String[] in = listOfCommands.get(i).trim().split(" ");
                            if (in[0].equals("add") || in[0].equals("update")) {
//                            if (!in[0].equals("remove_lower")) argument = in[1];
                                if (listOfCommands.size() - i <= 12) {
                                    System.out.println("Недостаточно полей");
                                    break;
                                } else {
                                    fields[0] = listOfCommands.get(i + 1);
                                    fields[1] = listOfCommands.get(i + 2);
                                    fields[2] = listOfCommands.get(i + 3);
                                    fields[3] = listOfCommands.get(i + 4);
                                    fields[4] = listOfCommands.get(i + 5);
                                    fields[5] = listOfCommands.get(i + 6);
                                    fields[6] = listOfCommands.get(i + 7);
                                    fields[7] = listOfCommands.get(i + 8);
                                    fields[8] = listOfCommands.get(i + 9);
                                    fields[9] = listOfCommands.get(i + 10);
                                    fields[10] = listOfCommands.get(i + 11);
                                    fields[11] = listOfCommands.get(i + 12);
                                    i += 12;
                                }
                            }
                            parseCommand(in, socket, address, scanner, login); //Отправляем команду
                        }
                        listOfCommands.clear();
                        isExecuteScript=false;
                        Messages.normalMessageOutput("Выполнение скрипта закончено", MessageColor.ANSI_YELLOW);
                    } catch (FileNotFoundException e) {
                        Messages.normalMessageOutput("Файл не найден", MessageColor.ANSI_RED);
                    }
                }}
                catch (ArrayIndexOutOfBoundsException e){
                    Messages.normalMessageOutput("Введите название файла скрипта", MessageColor.ANSI_RED);
                }
                script.clear();
                  break;
            case "exit":
                System.out.println("Завершение работы клиентского приложения");
                System.exit(0);
                break;
            case "head":
                send(new HeadCommand(), socket, address);
                receive(socket);
                break;
            case "filter_admin":
                send(new FilterByGroupAdminCommand(ar[0]), socket, address);
                receive(socket);
                break;
            case "remove_greatest":
                send(new RemoveGreatestCommand(add(ar,login),login), socket, address);
                receive(socket);
                break;
            case "remove_id" :
                try {
                    send(new RemoveIdCommand(Long.parseLong(ar[0]),login), socket, address);
                    receive(socket);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Недостаточно аргументов");
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат ключа");
                }
                break;
            default:
                if (!commandKey.equals("")) System.out.println("Неверная команда");
    }

}

    /**
     * Метод для отправки пакета с командой Серверу
     * @param command- команда
     * @param socket - сокет
     * @param address - адрес для отправки
     */
    public void send(Command command, DatagramSocket socket, SocketAddress address) {
        byte[] sending;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(command);
            out.flush();
            sending = bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sending, sending.length, address);
            socket.send(packet);
        } catch (PortUnreachableException e) {
            System.out.println("Порт недоступен");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public StudyGroup addmin(String ar[]){
        StudyGroup group;
        Scanner scanner = new Scanner(System.in);
            group = new StudyGroupMaker().makeGroup(scanner);
            return group;
    }
    public StudyGroup add(String ar[],String  login){
        try{Scanner scanner = new Scanner(System.in);
        StudyGroup group = new StudyGroupMaker().makeGroup(scanner);
        group.setCreator(login);
        return  group;}
        catch (NullPointerException e){
            return null;
        }
    }
    public void checkFeedback(DatagramSocket socket){byte[] message = new byte[16384]; //Массив байт, который мы получаем
        try {
            DatagramPacket packet = new DatagramPacket(message, message.length);
            socket.setSoTimeout(3000); //Задержка для возможности обработки запроса клиента
            socket.receive(packet);
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInput in = new ObjectInputStream(bis);
            String received_message = (String) in.readObject();
            System.out.println(received_message);
        } catch (SocketTimeoutException e) {
            Messages.normalMessageOutput("Проверьте ваш сервер на готовность к передаче информации",MessageColor.ANSI_RED);
            System.exit(-1);
            // Messages.normalMessageOutput("Cервер отключен",MessageColor.ANSI_RED);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод для получения пакета от Сервера
     * @param socket - сокет
     */
    public void receive(DatagramSocket socket) {
        byte[] message = new byte[16384]; //Массив байт, который мы получаем
        try {
            DatagramPacket packet = new DatagramPacket(message, message.length);
            socket.setSoTimeout(10000); //Задержка для возможности обработки запроса клиента
            socket.receive(packet);
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInput in = new ObjectInputStream(bis);
            String received_message = (String) in.readObject();
            System.out.println(received_message);
        } catch (SocketTimeoutException e) {
            Messages.normalMessageOutput("Время ожидания превышено",MessageColor.ANSI_RED);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (ClassCastException e ){
        }

    }
    //TODO Проверить корректность работы
    public boolean checkServerUser(ServerUser serverUser, DatagramSocket socket, SocketAddress address){
        byte[] sending;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(serverUser);
            out.flush();
            sending = bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sending, sending.length, address);
            socket.send(packet);
        } catch (PortUnreachableException e) {
            System.out.println("Порт недоступен");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Получили реакцию сервера
        byte[] message = new byte[16384]; //Массив байт, который мы получаем
        try {
            DatagramPacket packet = new DatagramPacket(message, message.length);
            socket.setSoTimeout(10000); //Задержка для возможности обработки запроса клиента
            socket.receive(packet);
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInput in = new ObjectInputStream(bis);
            return (Boolean) in.readObject();
        }
        catch (ClassCastException e ){
        }
        catch (SocketTimeoutException e) {
            System.out.println("Время ожидания превышено");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * Метод, который проверяет рекурсию в файлах execute_script
     * @param script - изначальный список команд
     * @param listOfPaths - изначальный список путей у всех execute_script
     * @return есть ли рекурсия или нет
     */
    private boolean checkRecurssionInScript(ArrayList<String> script, ArrayList<String> listOfPaths) {
        for (int i = 0; i < script.size(); i++) {
            String[] input = script.get(i).trim().split(" ");
            if (input[0].equals("execute_script"))
                if (listOfPaths.contains(input[1])) {
                    isRec = true;
                }
                else {
                    listOfPaths.add(input[1]);
                    checkRecurssionInScript(readScript(input[1]), listOfPaths);
                }
        }
        return isRec;
    }
    public void registerServerUser(ServerUser serverUser, DatagramSocket socket, SocketAddress address){
        byte[] sending;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(serverUser);
            out.flush();
            sending = bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sending, sending.length, address);
            socket.send(packet);
        } catch (PortUnreachableException e) {
            System.out.println("Порт недоступен");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод, читающий команды из скрипт-файла
     * @param path путь к файлу
     * @return Изначальный список команд
     */
    private ArrayList<String> readScript(String path) {
        path= "M:\\лабы прога\\Lab7\\Client\\script";
        File file = new File(path);
        ArrayList<String> listOfCommands = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                listOfCommands.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
        return listOfCommands;
    }
    /**
     * Метод для получения экземпляра класса StudyGroup
     * @return Group
     */
    public StudyGroup addGroup(String login) {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        StudyGroup group = new StudyGroup();
        group.setCreator(login);
        //Ввод product если execute_script
        if (isExecuteScript) {
            group.setName(fields[0]);
            group.setStudentsCount(Long.parseLong(fields[1]));
            group.setExpelledStudents(Long.parseLong(fields[2]));
            Person admin = new Person();
            admin.setName(fields[3]);
            admin.setPassportID(fields[4]);
            switch (fields[5]) {
                case ("BLACK"):
                    admin.setEyeColor(Color.BLACK);
                    break;
                case ("BLUE"):
                    admin.setEyeColor(Color.BLUE);
                    break;
                case ("BROWN"):
                    admin.setEyeColor(Color.BROWN);
                    break;
                case ("GREEN"):
                    admin.setEyeColor(Color.GREEN);
                    break;
                case ("ORANGE"):
                    admin.setEyeColor(Color.ORANGE);
                    break;
                case ("WHITE"):
                    admin.setEyeColor(Color.WHITE);
                    break;
            }
            switch (fields[6]) {
                case ("BLACK"):
                    admin.setHairColor(Color.BLACK);
                    break;
                case ("BLUE"):
                    admin.setHairColor(Color.BLUE);
                    break;
                case ("BROWN"):
                    admin.setHairColor(Color.BROWN);
                    break;
                case ("GREEN"):
                    admin.setHairColor(Color.GREEN);
                    break;
                case ("ORANGE"):
                    admin.setHairColor(Color.ORANGE);
                    break;
                case ("WHITE"):
                    admin.setHairColor(Color.WHITE);
                    break;
            }
            switch (fields[7]) {
                case ("USA"):
                    admin.setNationality(Country.USA);
                    break;
                case ("JAPAN"):
                    admin.setNationality(Country.JAPAN);
                    break;
                case ("SPAIN"):
                    admin.setNationality(Country.SPAIN);
                    break;
                case ("VATICAN"):
                    admin.setNationality(Country.VATICAN);
                    break;

            }
            group.setGroupAdmin(admin);
            Coordinates coordinates = new Coordinates();
            coordinates.setX(Long.parseLong(fields[8]));
            coordinates.setY(Float.parseFloat(fields[9]));
            group.setCoordinates(coordinates);
            switch (fields[10]) {
                case "DISTANCE_EDUCATION":
                    group.setFormOfEducation(FormOfEducation.DISTANCE_EDUCATION);
                    break;
                case "FULL_TIME_EDUCATION":
                    group.setFormOfEducation(FormOfEducation.FULL_TIME_EDUCATION);
                    break;
                case "EVENING_CLASSES":
                    group.setFormOfEducation(FormOfEducation.EVENING_CLASSES);
                    break;
            }
            switch (fields[11]){
                case("FIRST"):
                    group.setSemesterEnum(Semester.FIRST);
                    break;
                case("SECOND"):
                    group.setSemesterEnum(Semester.SECOND);
                    break;
                case("THIRD"):
                    group.setSemesterEnum(Semester.THIRD);
                    break;
                case("EIGHT"):
                    group.setSemesterEnum(Semester.EIGHTH);
                    break;
            }
            group.setGroupAdmin(admin);
            group.setCoordinates(coordinates);
            group.setCreationDate(LocalDateTime.now());
        }
           return group;
    }
    /**
     * Метод, который возвращает полный список команд для испонения, даже если есть вложенные execute_script
     * @param list изначальный список команд
     * @return Полный список команд
     * @throws FileNotFoundException
     */
    private ArrayList<String> readWholeScript(ArrayList<String> list) throws FileNotFoundException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).trim().split(" ")[0].equals("execute_script")) {
                readWholeScript(readScript(list.get(i).trim().split(" ")[1]));
            }
            else listOfCommands.add(list.get(i));
        }
        return listOfCommands;
       // listOfCommands.clear();
    }
}
