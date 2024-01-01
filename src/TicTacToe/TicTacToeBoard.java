package TicTacToe;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard {
	protected TicTacToeCell cells[][];
	protected TicTacToeCell cellsToCheckForWin[][];

	public TicTacToeBoard() {
		cells = new TicTacToeCell[][] {
			{ new TicTacToeCell(100, 100, 50, 50), new TicTacToeCell(150, 100, 50, 50),
				new TicTacToeCell(200, 100, 50, 50) },
		{ new TicTacToeCell(100, 150, 50, 50), new TicTacToeCell(150, 150, 50, 50),
				new TicTacToeCell(200, 150, 50, 50) },
		{ new TicTacToeCell(100, 200, 50, 50), new TicTacToeCell(150, 200, 50, 50),
				new TicTacToeCell(200, 200, 50, 50) } };
		this.cellsToCheckForWin = new TicTacToeCell[][] { { cells[0][0], cells[0][1], cells[0][2] },
				{ cells[1][0], cells[1][1], cells[1][2] }, { cells[2][0], cells[2][1], cells[2][2] },
				{ cells[0][0], cells[1][0], cells[2][0] }, { cells[0][1], cells[1][1], cells[2][1] },
				{ cells[0][2], cells[1][2], cells[2][2] }, { cells[0][0], cells[1][1], cells[2][0] },
				{ cells[0][2], cells[1][1], cells[2][0] }, };
	}

	public void setContentToX(int indexRow, int indexColumn) {
		TicTacToeCell cells = this.cells[indexRow][indexColumn];
		cells.setContentToX();
	}

	public void setContentToO(int indexRow, int indexColumn) {
		TicTacToeCell cells = this.cells[indexRow][indexColumn];
		cells.setContentToO();
	}

	public void setContentToEmpty(int indexRow, int indexColumn) {
		TicTacToeCell cells = this.cells[indexRow][indexColumn];
		cells.setContentToEmpty();
	}

	public boolean isContentX(int indexRow, int indexColumn) {
		TicTacToeCell cell = this.cells[indexRow][indexColumn];
		return cell.isContentX();
	}

	public boolean isContentO(int indexRow, int indexColumn) {
		TicTacToeCell cell = this.cells[indexRow][indexColumn];
		return cell.isContentO();
	}

	public boolean isContentEmpty(int indexRow, int indexColumn) {
		TicTacToeCell cell = this.cells[indexRow][indexColumn];
		return cell.isContentEmpty();
	}

	public void setEveryCellToEmpty() {
		for (int indexRow = 0; indexRow < 3; indexRow++) {
			for (int indexColumn = 0; indexColumn < 3; indexColumn++) {
				this.cells[indexRow][indexColumn].setContentToEmpty();
			}
		}
	}

	public void printContentToConsole() {
		for (int indexRow = 0; indexRow < 3; indexRow++) {
			for (int indexColumn = 0; indexColumn < 3; indexColumn++) {
				this.cells[indexRow][indexColumn].printContentToConsole();
			}
		}
	}

	public boolean didXWin() {
		for (int indexRow = 0; indexRow < cellsToCheckForWin.length; indexRow++) {
			if ((cellsToCheckForWin[indexRow][0].isContentX()) && (cellsToCheckForWin[indexRow][1].isContentX())
					&& (cellsToCheckForWin[indexRow][2].isContentX())) {
				return (true);
			}
		}
		return (false);
	}

	public boolean didOWin() {
		for (int indexRow = 0; indexRow < cellsToCheckForWin.length; indexRow++) {
			if ((cellsToCheckForWin[indexRow][0].isContentO()) && (cellsToCheckForWin[indexRow][1].isContentO())
					&& (cellsToCheckForWin[indexRow][2].isContentO())) {
				return (true);
			}
		}
		return (false);
	}

	public int numberOfCellsThatContainX() {
		int counter = 0;
		for (int indexRow = 0; indexRow < 3; indexRow++) {
			for (int indexColumn = 0; indexColumn < 3; indexColumn++) {
				if (isContentX(indexRow, indexColumn) == true) {
					counter = counter + 1;
				}
			}
		}
		return (counter);
	}

	public int numberOfCellsThatContainO() {
		int counter1 = 0;
		for (int indexRow = 0; indexRow < 3; indexRow++) {
			for (int indexColumn = 0; indexColumn < 3; indexColumn++) {
				if (isContentO(indexRow, indexColumn) == true) {
					counter1 = counter1 + 1;
				}
			}
		}
		return (counter1);
	}

	public int numberOfCellsThatContainEmpty() {
		int counter2 = 0;
		for (int indexRow = 0; indexRow < 3; indexRow++) {
			for (int indexColumn = 0; indexColumn < 3; indexColumn++) {
				if (isContentEmpty(indexRow, indexColumn) == true) {
					counter2 = counter2 + 1;
				}
			}
		}
		return (counter2);
	}

	public void changeFirstEmptyToX() {
		for (int indexRow = 0; indexRow < 3; indexRow++) {
			for (int indexColumn = 0; indexColumn < 3; indexColumn++) {
				if (isContentEmpty(indexRow, indexColumn)) {
					cells[indexRow][indexColumn].setContentToX();
					return;
				}
			}
		}
	}
	

	public boolean isGameOver() {
		if (this.didXWin()) {
			System.out.println("Game over");
		} else if (this.didOWin()) {
			System.out.println("Game over");
		} else {
			System.out.println("Game is not over");
		}
		return (isGameOver());
	}

	public void paintBoardToConsole(Graphics g) {
		for (int indexRow = 0; indexRow < 3; indexRow++) {
			for (int indexColumn = 0; indexColumn < 3; indexColumn++) {
				this.cells[indexRow][indexColumn].paintComponent(g);
			}
		}
	}
	

	
	public void ifPointInCellAndIfEmptyThenSetContentsTo0(int x, int y){
		for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            TicTacToeCell cell = cells[i][j];
	            if (cell.isInsideCell(x, y)) {
	                // Check if the cell is empty
	                if (cell.isContentEmpty()) {
	                    // Set the cell's content to O
	                    cell.setContentToO();
	                }
	                return;
	            }
	        }
	    }	
}


}

