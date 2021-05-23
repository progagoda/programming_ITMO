package message;

import java.io.Serializable;

public enum MessageColor implements Serializable {
    ANSI_BLACK("\u001B[30m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_WHITE("\u001B[37m");

    private static final long serialVersionUID = 1223;
    private final String colorType;

    MessageColor(String colorType){
        this.colorType = colorType;
    }

    public String getColorType() {
        return colorType;
    }
}


