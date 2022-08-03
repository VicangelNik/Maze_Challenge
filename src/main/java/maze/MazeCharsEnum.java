package maze;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MazeCharsEnum {
    UNDERSCORE("_"), BLOCK("X"), START("S"), GOAL("G");

    private final String value;
}
