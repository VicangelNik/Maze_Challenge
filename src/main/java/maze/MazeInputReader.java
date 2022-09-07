package maze;

import lombok.RequiredArgsConstructor;

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
        List<MazePosition> maze = new LinkedList<>();
        try (InputStream inputStream = new FileInputStream(FILE_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                for (int i = 0; i < line.length(); i++) {
                    final int position = lineNumber * 10 + i;
                    maze.add(new MazePosition(line.charAt(i), position));
                }
            }
            checkMazeSize(maze);
            return maze;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkMazeSize(final List<MazePosition> maze) {
        if (maze.size() < 2) {
            throw new IllegalArgumentException("Invalid matrix size: " + maze.size());
        }
    }
}
