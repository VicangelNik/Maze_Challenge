package maze;

import lombok.*;

/**
 * This class indicates a position in the maze with its properties.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MazePosition {

    private boolean isBlock = false;
    private boolean isGoal = false;
    private boolean isStart = false;
    @Setter
    private boolean isAccessed = false;
    private int numberOfPasses = 0;
    private String position;

    public MazePosition(final char character, final String position) {
        this.position = position;
        String value = String.valueOf(character);
        if (MazeCharsEnum.UNDERSCORE.getValue().equalsIgnoreCase(value)) {
            // nothing to do
        }
        else if (MazeCharsEnum.BLOCK.getValue().equalsIgnoreCase(value)) {
            this.isBlock = true;
        }
        else if (MazeCharsEnum.START.getValue().equalsIgnoreCase(value)) {
            this.isStart = true;
        }
        else if (MazeCharsEnum.GOAL.getValue().equalsIgnoreCase(value)) {
            this.isGoal = true;
        }
        else {
            throw new IllegalArgumentException("Invalid character detected.");
        }
    }

    public void increaseNumberOfPasses() {
        numberOfPasses++;
    }


}
