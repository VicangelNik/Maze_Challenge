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
    private final MazeMatrix mazeMatrix;
    private final Random random = new Random();
    @Getter
    private MazePosition currentPosition;
    private List<MazePosition> passedPositionsList = new ArrayList<>();
    @Getter
    private boolean isGoalFound = false;

    public Actor(MazeMatrix mazeMatrix) {
        this.mazeMatrix = mazeMatrix;
        currentPosition = mazeMatrix.getStartingPosition();
    }

    public void move() {
        List<MazePosition> possibleMoveList = inspectPossibleMoves();
        currentPosition = moveLogic(possibleMoveList);
        currentPosition.setAccessed(true);
        passedPositionsList.add(currentPosition);
        currentPosition.increaseNumberOfPasses();
        if (currentPosition.isGoal())
        {
            isGoalFound = true;
        }
    }

    private MazePosition moveLogic(List<MazePosition> possibleMoveList) {
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

    private List<MazePosition> inspectPossibleMoves() {
        List<MazePosition> possibleMoveList = new ArrayList<>();
        int positionNumber = Integer.parseInt(currentPosition.getPosition());
        Optional<MazePosition> eastPosition = mazeMatrix.getMazePosition(String.valueOf(positionNumber + 1));
        Optional<MazePosition> westPosition = mazeMatrix.getMazePosition(String.valueOf(positionNumber - 1));
        Optional<MazePosition> southPosition = mazeMatrix.getMazePosition(String.valueOf(positionNumber + 10));
        Optional<MazePosition> northPosition = mazeMatrix.getMazePosition(String.valueOf(positionNumber - 10));
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
