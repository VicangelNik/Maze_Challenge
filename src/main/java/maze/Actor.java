package maze;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class indicates the actor whose goal is to find the end of our maze.
 * The actor is assumed that has not any prior knowledge of the map but is possible to remember.
 */
public class Actor {
    private final Maze maze;
    private final Random random = new Random();
    @Getter
    private MazePosition currentPosition;
    private List<MazePosition> passedPositionsList = new ArrayList<>();
    @Getter
    private boolean isGoalFound = false;

    public Actor(final Maze maze) {
        this.maze = maze;
        currentPosition = maze.getStartingPosition();
    }

    public void move() {
        List<MazePosition> possibleMoveList = inspectPossibleMoves();
        currentPosition = moveLogic(possibleMoveList);
        currentPosition.setAccessed(true);
        passedPositionsList.add(currentPosition);
        currentPosition.increaseNumberOfPasses();
        if (currentPosition.isGoal()) {
            isGoalFound = true;
        }
    }

    /**
     * This method contains actor's move logic. The logic is that 1) actor can not move in a blocking position.
     * 2) actor marks the positions that passes as accessed, thus actor remembers. At first searches and moves to a position that did not pass before.
     * However, if he has passed by all the available positions. He selects randomly his next move.
     *
     * @param possibleMoveList
     * @return mazeposition decision
     */
    private MazePosition moveLogic(final List<MazePosition> possibleMoveList) {
        List<MazePosition> possibleNonBlockPositionList = possibleMoveList.stream()
                                                                          .filter(pos -> !pos.isBlock())
                                                                          .collect(Collectors.toList());
        if (possibleNonBlockPositionList.isEmpty()) {
            throw new IllegalArgumentException("Actor can not move");
        }
        Optional<MazePosition> notAccessedPosition = possibleNonBlockPositionList.stream()
                                                                                 .filter(pos -> !pos.isAccessed())
                                                                                 .findFirst();
        return notAccessedPosition.orElseGet(
                () -> possibleNonBlockPositionList.get(random.nextInt(possibleNonBlockPositionList.size())));
    }

    /**
     * Method inspects all possible moves from actor's current position and returns the maze positions (east, west, north south).
     * There is a chance that a possible move is out of matrix boundaries. Of-course it is not considered a possible move.
     *
     * @return List<MazePosition>
     */
    private List<MazePosition> inspectPossibleMoves() {
        List<MazePosition> possibleMoveList = new ArrayList<>();
        int positionNumber = Integer.parseInt(currentPosition.getMazeLocation());
        Optional<MazePosition> eastPosition = maze.getMazePosition(String.valueOf(positionNumber + 1));
        Optional<MazePosition> westPosition = maze.getMazePosition(String.valueOf(positionNumber - 1));
        Optional<MazePosition> southPosition = maze.getMazePosition(String.valueOf(positionNumber + 10));
        Optional<MazePosition> northPosition = maze.getMazePosition(String.valueOf(positionNumber - 10));
        eastPosition.ifPresent(possibleMoveList::add);
        westPosition.ifPresent(possibleMoveList::add);
        southPosition.ifPresent(possibleMoveList::add);
        northPosition.ifPresent(possibleMoveList::add);
        return possibleMoveList;
    }

    public void printRoute() {
        passedPositionsList.forEach(mazePosition -> System.out.println(mazePosition.toString()));
    }
}
