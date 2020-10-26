package maze;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * This class indicates our maze. The map in which our traveller is moving.
 * The maze is considered 2-dimensional and continuous. Also is assumed that is not modified while game has started.
 */
public class Maze {

    private final TreeMap<MazePosition, ArrayList<MazePosition>> mazeMap;
    private static final String defaultMazePath = "resources/mazeexcersise.txt";

    public Maze() throws IOException {
        this.mazeMap = loadFromFile(defaultMazePath);
    }

    private TreeMap loadFromFile(String pathToMaze) throws IOException {
        TreeMap<MazePosition, ArrayList<MazePosition>> mazeMapL = new TreeMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(System.getProperty("user.dir") + File.separator + pathToMaze))) {
            AtomicInteger lineCount = new AtomicInteger(1);
            stream.forEach(line -> {
                if (!line.matches(MazeCharsEnum.getRegex())) {
                    char[] lineCharacters = line.toCharArray();
                    AtomicInteger columnCount = new AtomicInteger(1);
                    for (char lineChar : lineCharacters) {
                        Point point = new Point(lineCount.get(), columnCount.get());
                        MazePosition position = new MazePosition(point, lineChar);
                        mazeMapL.put(position, new ArrayList<>());
                        columnCount.getAndIncrement();
                    }
                    lineCount.getAndIncrement();
                } else {
                    throw new IllegalArgumentException(String.format("Line %s contains not valid character. Valid characters are only" + " the following: %s", line, MazeCharsEnum.toCustomString()));
                }
            });

        }
        return mazeMapL;
    }

    public static ArrayList<MazePosition> findNeighborPositions(MazePosition position, TreeMap<MazePosition, ArrayList<MazePosition>> map) {
        ArrayList<MazePosition> neighbors = new ArrayList<>();
        map.forEach((pos, list) -> {
            if (neighbors.size() < 4 && !position.equals(pos) && (pos.getPoint().x == position.getPoint().x && (Math.abs(pos.getPoint().y - position.getPoint().y) == 1)) || (pos.getPoint().y == position.getPoint().y && (Math.abs(pos.getPoint().x - position.getPoint().x) == 1))) {
                neighbors.add(pos);
            } else if (neighbors.size() == 4) {
                return;
            }
        });
        return neighbors;
    }

    public TreeMap<MazePosition, ArrayList<MazePosition>> getMazeMap() {
        return mazeMap;
    }

    public MazePosition getStartingPosition() {
        MazePosition startingPosition = this.mazeMap.keySet().stream().filter(MazePosition::isStart).findFirst().get();
        startingPosition.setAccessed(true);
        return startingPosition;
    }
}
