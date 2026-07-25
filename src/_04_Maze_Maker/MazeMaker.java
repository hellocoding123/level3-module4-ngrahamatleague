package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

    private static int rows;
    private static int cols;

    private static Maze maze;

    private static Random randGen = new Random();
    private static Stack<Cell> uncheckedCells = new Stack<Cell>();

    public static Maze generateMaze(int r, int c) {
        rows = r;
        cols = c;
        maze = new Maze(rows, cols);

        // 1. Pick random start and finish cells on opposite borders
        int startRow = randGen.nextInt(rows);
        Cell start = maze.getCell(startRow, 0);
        start.setWestWall(false);

        int finishRow = randGen.nextInt(rows);
        Cell finish = maze.getCell(finishRow, cols - 1);
        finish.setEastWall(false);

        // 2. Select a random cell in the maze
        int randomRow = randGen.nextInt(rows);
        int randomCol = randGen.nextInt(cols);
        Cell currentCell = maze.getCell(randomRow, randomCol);

        // 3. Start generating the maze
        selectNextPath(currentCell);

        return maze;
    }

    // 4. Recursive backtracking algorithm
    private static void selectNextPath(Cell currentCell) {

        // A. Mark current cell as visited
        currentCell.setBeenVisited(true);

        // B. Get all unvisited neighbors
        ArrayList<Cell> neighbors = getUnvisitedNeighbors(currentCell);

        // C. If there are unvisited neighbors
        if (!neighbors.isEmpty()) {

            // C1. Pick one at random
            Cell nextCell = neighbors.get(randGen.nextInt(neighbors.size()));

            // C2. Push current cell onto stack
            uncheckedCells.push(currentCell);

            // C3. Remove the wall between them
            removeWalls(currentCell, nextCell);

            // C4. Mark new cell visited
            nextCell.setBeenVisited(true);

            // C5. Continue recursively
            selectNextPath(nextCell);

        } else {

            // D. All neighbors visited
            if (!uncheckedCells.isEmpty()) {

                // D1a. Pop previous cell
                Cell previousCell = uncheckedCells.pop();

                // D1b & D1c. Continue from it
                selectNextPath(previousCell);
            }
        }
    }

    // This method will check if c1 and c2 are adjacent.
    // If they are, the walls between them are removed.
    private static void removeWalls(Cell c1, Cell c2) {
        if (c1.getRow() == c2.getRow()) {
            if (c1.getCol() > c2.getCol()) {
                c1.setWestWall(false);
                c2.setEastWall(false);
            } else {
                c1.setEastWall(false);
                c2.setWestWall(false);
            }
        } else {
            if (c1.getRow() > c2.getRow()) {
                c1.setNorthWall(false);
                c2.setSouthWall(false);
            } else {
                c1.setSouthWall(false);
                c2.setNorthWall(false);
            }
        }
    }

    // Returns all unvisited neighboring cells
    private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        int row = c.getRow();
        int col = c.getCol();

        ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

        if (row > 0 && !maze.getCell(row - 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row - 1, col));
        }

        if (col > 0 && !maze.getCell(row, col - 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col - 1));
        }

        if (row < rows - 1 && !maze.getCell(row + 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row + 1, col));
        }

        if (col < cols - 1 && !maze.getCell(row, col + 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col + 1));
        }

        return unvisitedNeighbors;
    }
}