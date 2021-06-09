package message;

/**
 * Класс для вывода информации в командную строку
 */
public class Messages {
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Выводит на экран сообщение, которое говорит о каких-то действиях программы
     *
     * @param message сообщение которые выведется на экран
     */
    public static void normalMessageOutput(String message, MessageColor messageColor) {
        System.out.println(messageColor.getColorType() + message + ANSI_RESET);
    }
    public static String unnormalMessageOutput(String message, MessageColor messageColor) {
       return (messageColor.getColorType() + message + ANSI_RESET);
    }
}