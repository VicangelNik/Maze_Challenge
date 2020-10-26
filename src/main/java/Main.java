import maze.Maze;
import maze.MazePosition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            String algorithm = "";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("write your preferable algorithm between: ");
                System.out.println("1) Random Search");
                // System.out.println("2) Random Probability Search");
                // Reading data using readLine
                algorithm = reader.readLine();
            }

            Maze maze = new Maze();
            Actor actor = new Actor(maze.getStartingPosition());
            while (actor.inspect(maze) != 1) {
                MazePosition nextPosition = actor.choose(algorithm);
                actor.move(nextPosition);
                actor.printPath();
            }
            actor.printPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
