package maze;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum MazeCharsEnum {
    UNDERSCORE("_"), BLOCK("X"), START("S"), GOAL("G");

    private String mazeChar;

    MazeCharsEnum(String mazeChar) {
        this.mazeChar = mazeChar;
    }

    public static String getRegex() {
        StringBuilder sb = new StringBuilder();
        MazeCharsEnum[] values = MazeCharsEnum.values();
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
        }
        return sb.toString();
    }

    public static String toCustomString() {
        return Arrays.asList(getRegex().toCharArray()).stream().map(s -> "\'" + s + "\'").collect(Collectors.joining(", "));
    }

    public String getMazeChar() {
        return this.mazeChar;
    }
}
