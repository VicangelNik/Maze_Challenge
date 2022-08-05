package maze;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * @author Nikiforos Xylogiannopoulos
 */
@RequiredArgsConstructor
public class Maze {

    private final List<MazePosition> mazePositionList;

    public Optional<MazePosition> getMazePosition(final String matrixLocation) {
        return mazePositionList.stream()
                               .filter(mazePosition -> mazePosition.getMazeLocation().equals(matrixLocation))
                               .findFirst();
    }

    public MazePosition getStartingPosition() {
        return mazePositionList.stream()
                               .filter(MazePosition::isStart)
                               .findFirst()
                               .orElseThrow(() -> new IllegalArgumentException(" Starting position does not exist"));
    }

    public MazePosition getGoalPosition() {
        return mazePositionList.stream()
                               .filter(MazePosition::isGoal)
                               .findFirst()
                               .orElseThrow(() -> new IllegalArgumentException(" Goal position does not exist"));
    }

}
