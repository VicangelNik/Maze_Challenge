package Algorthms;

import maze.MazePosition;

import java.util.ArrayList;
import java.util.Random;

public class RandomSearch extends Algorithm{

    @Override
    public MazePosition choosePosition(ArrayList<MazePosition> possibleNextPositions) {
        for (MazePosition nextPosition : possibleNextPositions) {
            if (nextPosition.isGoal()) {
                return nextPosition;
            } else if (!nextPosition.isAccessed()) {
                return nextPosition;
            }
        }

        Random random = new Random();
        int randomSelection =  random.ints(0, possibleNextPositions.size()).findFirst().getAsInt();
        return possibleNextPositions.get(randomSelection);
    }
}
