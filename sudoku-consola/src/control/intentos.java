package control;

import java.util.ArrayList;

import modelo.Celda;

public class intentos {
	public static int[][] matrix;

	public static void main(String[] args) {

		matrix = new int[9][9];

		matrix[0][1] = 7;
		matrix[0][5] = 8;
		matrix[0][7] = 9;

		matrix[1][1] = 8;
		matrix[1][3] = 9;

		matrix[2][5] = 1;
		matrix[2][6] = 2;
		matrix[2][8] = 6;

		matrix[3][2] = 6;
		matrix[3][8] = 7;

		matrix[4][3] = 3;
		matrix[4][5] = 9;
		matrix[4][6] = 6;

		matrix[5][2] = 4;
		matrix[5][4] = 6;
		matrix[5][6] = 3;

		matrix[6][1] = 2;
		matrix[6][2] = 1;
		matrix[6][7] = 5;
		matrix[6][8] = 8;

		matrix[7][1] = 3;
		matrix[7][2] = 5;
		matrix[7][3] = 2;
		matrix[7][4] = 7;

		writeMatrix(matrix);
		if (solve(0, 0, matrix)) // solves in place
			writeMatrix(matrix);
		else
			System.out.println("NONE");
	}

	// Metodo para dibujar la matrix
	// "tanto los números iniciales como el esquema de cuadratura"
	public static void writeMatrix(int[][] solution) {
		for (int i = 0; i < 9; ++i) {
			if (i % 3 == 0) {
				System.out.println(" -----------------------");
			}
			for (int j = 0; j < 9; ++j) {
				if (j % 3 == 0)
					System.out.print("| ");
				System.out.print(solution[i][j] == 0 ? " " : Integer
						.toString(solution[i][j]));

				System.out.print(' ');
			}
			System.out.println("|");
		}
		System.out.println(" -----------------------");
	}

	public static boolean solve(int i, int j, int[][] cells) {
		if (i == 9) {
			i = 0;
			if (j++ == 8) {
				return true;
			}
		}
		//System.out.println("Fila : " + i + "----Columna" + j);
		if (cells[i][j] != 0) { // skip filled cells
			return solve(i + 1, j, cells);
		}
		for (int val = 1; val <= 9; ++val) {
			if (legal(i, j, val, cells)) {
				cells[i][j] = val;
				if (solve(i + 1, j, cells)) {
					return true;
				}
			}
		}
		cells[i][j] = 0; // reset on backtrack
		return false;
	}

	public static boolean legal(int i, int j, int val, int[][] cells) {
		//System.out.println("Valor por agregar " + val);
		for (int k = 0; k < 9; ++k)
			// row
			if (val == cells[k][j])
				return false;

		for (int k = 0; k < 9; ++k)
			// col
			if (val == cells[i][k])
				return false;

		int boxRowOffset = (i / 3) * 3;
		int boxColOffset = (j / 3) * 3;
		for (int k = 0; k < 3; ++k)
			// box
			for (int m = 0; m < 3; ++m)
				if (val == cells[boxRowOffset + k][boxColOffset + m])
					return false;

		return true; // no violations, so it's legal
	}

}