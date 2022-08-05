package maze;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Nikiforos Xylogiannopoulos
 */
class ActorTest {

    @DisplayName("test actor's inspect possible moves with default current position the starting position")
    @ParameterizedTest
    @MethodSource(value = "provideStringsForIsBlank")
    void inspectPossibleMovesTest(final String filePath, int expectedValue) throws NoSuchMethodException {
        val mazeInputReader = new MazeInputReader(filePath);
        List<MazePosition> mazePositionList = mazeInputReader.getMaze();
        val mazeMatrix = new Maze(mazePositionList);
        val actor = new Actor(mazeMatrix);
        Method method = actor.getClass().getDeclaredMethod("inspectPossibleMoves");
        List<MazePosition> possibleMoveList = (List<MazePosition>) ReflectionUtils.invokeMethod(method, actor, null);
        Assertions.assertFalse(possibleMoveList.isEmpty());
        Assertions.assertEquals(expectedValue, possibleMoveList.size());
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(Arguments.of("src\\test\\resources\\mazeInput1.txt", 2),
                         Arguments.of("src\\test\\resources\\mazeInput2.txt", 3));
    }
}
