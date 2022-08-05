package maze;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * @author Nikiforos Xylogiannopoulos
 */
class MazePositionTest {

    @DisplayName("test maze position creation")
    @ParameterizedTest
    @MethodSource(value = "provideMazePositionAttributes")
    void mazePositionCreationTest(final char character, final String mazeLocation, final boolean isStart,
                                  final boolean isBlock, final boolean isGoal) {
        MazePosition mazePosition = new MazePosition(character, mazeLocation);
        Assertions.assertEquals(mazeLocation, mazePosition.getMazeLocation());
        Assertions.assertEquals(isStart, mazePosition.isStart());
        Assertions.assertEquals(isBlock, mazePosition.isBlock());
        Assertions.assertEquals(isGoal, mazePosition.isGoal());
    }

    private static Stream<Arguments> provideMazePositionAttributes() {
        return Stream.of(Arguments.of(MazeCharsEnum.UNDERSCORE.getValue(), "12", false, false, false),
                         Arguments.of(MazeCharsEnum.BLOCK.getValue(), "12", false, true, false),
                         Arguments.of(MazeCharsEnum.GOAL.getValue(), "12", false, false, true),
                         Arguments.of(MazeCharsEnum.START.getValue(), "12", true, false, false));
    }
}
