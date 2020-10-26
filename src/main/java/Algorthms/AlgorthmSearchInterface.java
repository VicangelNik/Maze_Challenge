package Algorthms;

import maze.MazePosition;

import java.util.ArrayList;

public interface AlgorthmSearchInterface {

    public MazePosition choosePosition(ArrayList<MazePosition> possibleNextPositions);
}
