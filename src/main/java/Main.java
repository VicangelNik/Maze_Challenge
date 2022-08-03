import maze.MazeInputReader;
import maze.Actor;
import maze.MazeMatrix;
import maze.MazePosition;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MazeInputReader mazeInputReader = new MazeInputReader("src\\test\\resources\\mazeInput2.txt");
        List<MazePosition> mazePositionList = mazeInputReader.getMaze();
        MazeMatrix mazeMatrix = new MazeMatrix(mazePositionList);
        Actor actor = new Actor(mazeMatrix);
        while (!actor.isGoalFound()) {
            actor.move();
        }
        actor.printRoute();
    }
}
