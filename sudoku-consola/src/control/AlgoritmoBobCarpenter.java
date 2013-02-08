package control;

import java.util.ArrayList;

import modelo.Celda;

/**
 * Algoritmo de Bob Carpenter Natural Language Scientist & Software Architect
 * Alias I, Inc.
 */
public class AlgoritmoBobCarpenter {
	public static int[][] matriz;

	public static void main(String[] args) {

		matriz = new int[9][9];
		//Se ingresan los numero del sudoku por resolver
		matriz [0][0]=1;
		
		writeMatrix(matriz);
		if (solve(0, 0, matriz)) // solves in place
			writeMatrix(matriz);
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
		// System.out.println("Fila : " + i + "----Columna" + j);
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
		// System.out.println("Valor por agregar " + val);
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