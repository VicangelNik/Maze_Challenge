import Algorthms.Algorithm;
import Algorthms.RandomSearch;
import Algorthms.RandomSearchProbabilities;
import maze.Maze;
import maze.MazePosition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class indicates the actor whose goal is to find the end of our maze.
 * The actor is assumed that has not any prior knowledge of the map but is possible to remember.
 */
public class Actor {

    MazePosition currentPosition;
    // linked list is faster when inserting or deleting but it may be a bit heavier in memory than arraylist
    ArrayList<MazePosition> possibleNextPositions = new ArrayList<>();
    LinkedList<MazePosition> allPassedPositions = new LinkedList<>();

    public Actor(MazePosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int inspect(Maze maze) {
        if (currentPosition.isGoal()) {
            return 1;
        }

        ArrayList<MazePosition> availablePositions = maze.getMazeMap().get(currentPosition);
        if (availablePositions.isEmpty()) {
            maze.getMazeMap().put(currentPosition, Maze.findNeighborPositions(currentPosition, maze.getMazeMap()));
            availablePositions = maze.getMazeMap().get(currentPosition);
        }
        possibleNextPositions = (ArrayList<MazePosition>) availablePositions.stream().filter(MazePosition::isAccessible).collect(Collectors.toList());
        return 0;
    }

    // The algorithm
    public MazePosition choose(String algorithm) {
        if ("Random Search".equalsIgnoreCase(algorithm) || "1".equals(algorithm)) {
            Algorithm randomSearch = new RandomSearch();
            return randomSearch.choosePosition(possibleNextPositions);
        }
//        } else if ("Random Probability Search".equalsIgnoreCase(algorithm) || "2".equals(algorithm)) {
//            Algorithm randomSearch = new RandomSearchProbabilities();
//            return randomSearch.choosePosition(possibleNextPositions);
//        }
        throw new IllegalArgumentException("No valid algorithm was chosen");
    }

    public void move(MazePosition position) {
        currentPosition = position;
        currentPosition.setAccessed(true);
        allPassedPositions.add(position);
        currentPosition.increaseNumberOfPasses();
    }

    public void printPath() {
        allPassedPositions.forEach(pos -> System.out.println(pos.toString()));
    }

}
