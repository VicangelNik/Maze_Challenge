package maze;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/**
 * @author Nikiforos Xylogiannopoulos
 */
class MazeInputReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"src\\test\\resources\\mazeInput1.txt", "src\\test\\resources\\mazeInput2.txt"})
    void testGetMazeCase(final String filePath) {
        val mazeInputReader = new MazeInputReader(filePath);
        final List<MazePosition> mazePositionList = mazeInputReader.getMaze();
        val maze = new Maze(mazePositionList);
        Assertions.assertFalse(mazePositionList.isEmpty());
        Assertions.assertNotNull(maze.getStartingPosition());
        Assertions.assertNotNull(maze.getGoalPosition());
    }

    @Test
    void testMazeNoGoalExceptionCase() {
        val mazeInputReader = new MazeInputReader("src\\test\\resources\\mazeInput3NoGoalPosition.txt");
        final List<MazePosition> mazePositionList = mazeInputReader.getMaze();

        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new Maze(mazePositionList),
                                       "Goal position does not exist");
    }
}
