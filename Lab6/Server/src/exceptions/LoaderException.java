package exceptions;



public class LoaderException extends RuntimeException {
    /**
     * Конструктор, создающий исключение с указанным сообщением.
     * @param message сообщение ошибки.
     */
    public LoaderException(String message) {
        super(message);
    }
}
