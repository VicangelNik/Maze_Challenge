package Algorthms;

import maze.MazePosition;

import java.util.*;
import java.util.function.Function;

public class RandomSearchProbabilities extends Algorithm {

    @Override
    public MazePosition choosePosition(ArrayList<MazePosition> possibleNextPositions) {
        for (MazePosition nextPosition : possibleNextPositions) {
            if (nextPosition.isGoal()) {
                return nextPosition;
            } else if (!nextPosition.isAccessed()) {
                return nextPosition;
            }
        }

        if (possibleNextPositions.size() > 1) {
            Random random = new Random();
            int total100Unit = 100 / possibleNextPositions.size();
            int total = 0;
            for (MazePosition nextPosition : possibleNextPositions) {
                int totalUnit = -2 * nextPosition.getNumberOfPasses();
                total += total100Unit + totalUnit;
                nextPosition.setCurrentProbability(total100Unit + totalUnit);
            }
            int randomNumber = random.ints(0, total + 1).findFirst().getAsInt();
            for (MazePosition nextPosition : possibleNextPositions) {
                if (randomNumber <= total && randomNumber > nextPosition.getCurrentProbability()) {
                    return nextPosition;
                } else {
                    total -= nextPosition.getCurrentProbability();
                }
            }
        } else {
            return possibleNextPositions.get(0);
        }
        return null;
    }
}
