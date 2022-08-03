package maze;

import lombok.RequiredArgsConstructor;
import maze.MazePosition;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nikiforos Xylogiannopoulos
 */
@RequiredArgsConstructor
public class MazeInputReader {

    private final String FILE_PATH;
    private int lineNumber = 0;

    public List<MazePosition> getMaze() {
        List<MazePosition> matrix = new LinkedList<>();
        try (InputStream inputStream = new FileInputStream(FILE_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                for (int i = 0; i < line.length(); i++) {
                    String position = String.valueOf(lineNumber) + i;
                    matrix.add(new MazePosition(line.charAt(i), position));
                }
            }
            checkMatrixSize(matrix);
            return matrix;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkMatrixSize(List<MazePosition> matrix) {
        if (matrix.size() < 2) {
            throw new IllegalArgumentException("Invalid matrix size: " + matrix.size());
        }
    }
}
