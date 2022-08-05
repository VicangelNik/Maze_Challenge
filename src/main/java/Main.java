import maze.MazeInputReader;
import maze.Actor;
import maze.Maze;
import maze.MazePosition;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MazeInputReader mazeInputReader = new MazeInputReader("src\\test\\resources\\mazeInput2.txt");
        List<MazePosition> mazePositionList = mazeInputReader.getMaze();
        Maze maze = new Maze(mazePositionList);
        Actor actor = new Actor(maze);
        while (!actor.isGoalFound()) {
            actor.move();
        }
        actor.printRoute();
    }
}
