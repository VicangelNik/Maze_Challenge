package maze;

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
    void getMaze(String filePath) {
        MazeInputReader mazeInputReader = new MazeInputReader(filePath);
        List<MazePosition> mazePositionList = mazeInputReader.getMaze();
        Assertions.assertFalse(mazePositionList.isEmpty());
    }
}
