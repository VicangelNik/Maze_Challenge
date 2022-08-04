package maze;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/**
 * @author Nikiforos Xylogiannopoulos
 */
class MazeInputReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"src\\test\\resources\\mazeInput1.txt", "src\\test\\resources\\mazeInput2.txt"})
    void getMaze(final String filePath) {
        val mazeInputReader = new MazeInputReader(filePath);
        List<MazePosition> mazePositionList = mazeInputReader.getMaze();
        val mazeMatrix = new MazeMatrix(mazePositionList);
        Assertions.assertFalse(mazePositionList.isEmpty());
        Assertions.assertNotNull(mazeMatrix.getStartingPosition());
        Assertions.assertNotNull(mazeMatrix.getGoalPosition());
    }
}
