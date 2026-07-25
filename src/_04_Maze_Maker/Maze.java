package _04_Maze_Maker;

import java.awt.Graphics;

public class Maze {

    // 1. Create a 2D array of cells.
    private Cell[][] cells;

    private int rows;
    private int cols;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        // 2. Initialize the cells array
        cells = new Cell[rows][cols];

        // 3. Initialize each cell
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    // 4. Draw every cell in the maze
    public void draw(Graphics g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col].draw(g);
            }
        }
    }

    // 5. Return the selected cell
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
