package maze;

import java.awt.*;
import java.util.Objects;

/**
 * This class indicates a position in the maze with its properties.
 */
public class MazePosition implements Comparable<MazePosition> {

    boolean isAccessible = false;
    boolean isGoal = false;
    boolean isStart = false;
    boolean isAccessed = false;
    private final Point point;
    private int numberOfPasses = 0;
    private int currentProbability = 0;

    public MazePosition(Point point, boolean isAccessible, boolean isGoal, boolean isStart) {
        this.point = point;
        this.isAccessible = isAccessible;
        this.isGoal = isGoal;
        this.isStart = isStart;
    }

    public MazePosition(Point point, char lineChar) {
        this.point = point;
        assignPositionProperties(lineChar);
    }

    private void assignPositionProperties(char character) {
        String value = String.valueOf(character);
        if (MazeCharsEnum.UNDERSCORE.getMazeChar().equals(value)) {
            this.isAccessible = true;
        } else if (MazeCharsEnum.BLOCK.getMazeChar().equals(value)) {
            // nothing to do
        } else if (MazeCharsEnum.START.getMazeChar().equals(value)) {
            this.isAccessible = true;
            this.isStart = true;
        } else if (MazeCharsEnum.GOAL.getMazeChar().equals(value)) {
            this.isAccessible = true;
            this.isGoal = true;
        } else {
            throw new IllegalArgumentException("Invalid character detected.");
        }
    }

    public boolean isAccessible() {
        return this.isAccessible;
    }

    public boolean isGoal() {
        return this.isGoal;
    }

    public boolean isStart() {
        return this.isStart;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isAccessed() {
        return isAccessed;
    }

    public void setAccessed(boolean accessed) {
        isAccessed = accessed;
    }

    public int getNumberOfPasses() {
        return numberOfPasses;
    }

    public void increaseNumberOfPasses() {
        this.numberOfPasses++;
    }

    public int getCurrentProbability() {
        return currentProbability;
    }

    public void setCurrentProbability(int currentProbability) {
        this.currentProbability = currentProbability;
    }

    @Override
    public String toString() {
        String goal = "";
        String start = "";
        if (this.isGoal) {
            goal = "(G)";
        }
        if (this.isStart) {
            start = "(S)";
        }
        return "(" + this.point.getX() + ":" + this.point.getY() + start + goal + ")";
    }

    // tree map is a sorted data structure and here we define in what manner it will be sorted.
    @Override
    public int compareTo(MazePosition o) {
        if (this.point.x < o.point.x) {
            return -1;
        } else if (this.point.x > o.point.x) {
            return 1;
        } else {
            if (this.point.y < o.point.y) {
                return -1;
            } else if (this.point.y > o.point.y) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MazePosition that = (MazePosition) o;
        return isAccessible == that.isAccessible && isGoal == that.isGoal && isStart == that.isStart && isAccessed == that.isAccessed && Objects.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAccessible, isGoal, isStart, isAccessed, point);
    }
}
