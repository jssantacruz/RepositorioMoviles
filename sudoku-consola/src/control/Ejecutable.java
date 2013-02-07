package control;

import java.awt.peer.ContainerPeer;
import java.util.ArrayList;

import modelo.Celda;

public class Ejecutable {

	public static Object[][] sudoku;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Se crea y se llena el sudoku ejemplo:
		// unSudokuTerminado();

		sudoku = new Object[9][9];

		sudoku[0][1] = (Object) new Celda(7, 0, 1, 1);
		sudoku[0][5] = (Object) new Celda(8, 0, 5, 2);
		sudoku[0][7] = (Object) new Celda(9, 0, 7, 3);

		sudoku[1][1] = (Object) new Celda(8, 1, 1, 1);
		sudoku[1][3] = (Object) new Celda(9, 1, 3, 2);

		sudoku[2][5] = (Object) new Celda(1, 2, 5, 2);
		sudoku[2][6] = (Object) new Celda(2, 2, 6, 3);
		sudoku[2][8] = (Object) new Celda(6, 2, 8, 3);

		sudoku[3][2] = (Object) new Celda(6, 3, 2, 4);
		sudoku[3][8] = (Object) new Celda(7, 3, 8, 6);

		sudoku[4][3] = (Object) new Celda(3, 4, 3, 5);
		sudoku[4][5] = (Object) new Celda(9, 4, 5, 5);
		sudoku[4][6] = (Object) new Celda(6, 4, 6, 6);

		sudoku[5][2] = (Object) new Celda(4, 5, 2, 4);
		sudoku[5][4] = (Object) new Celda(6, 5, 4, 5);
		sudoku[5][6] = (Object) new Celda(3, 5, 6, 6);

		sudoku[6][1] = (Object) new Celda(2, 6, 1, 7);
		sudoku[6][2] = (Object) new Celda(1, 6, 2, 7);
		sudoku[6][7] = (Object) new Celda(5, 6, 7, 9);
		sudoku[6][8] = (Object) new Celda(8, 6, 8, 9);

		sudoku[7][1] = (Object) new Celda(3, 7, 1, 7);
		sudoku[7][2] = (Object) new Celda(5, 7, 2, 7);
		sudoku[7][3] = (Object) new Celda(2, 7, 3, 8);
		sudoku[7][4] = (Object) new Celda(7, 7, 4, 8);

		// AGREGUE PARA VER QUE TAL
		// sudoku[2][7] = (Object) new Celda(8, 2, 7, 3);
		// sudoku[3][6] = (Object) new Celda(8, 3, 6, 6);
		//
		// sudoku[3][1] = (Object) new Celda(9, 3, 1, 4);
		// sudoku[3][0] = (Object) new Celda(3, 3, 0, 4);
		//
		// sudoku[4][8] = (Object) new Celda(5, 4, 8, 6);
		// sudoku[5][8] = (Object) new Celda(9, 5, 8, 6);
		//
		// sudoku[8][3] = (Object) new Celda(1, 8, 3, 8);
		// sudoku[8][4] = (Object) new Celda(8, 8, 4, 8);

		// Se crean los objetos Celda restantes en la matriz
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				if (((Object) (sudoku[fila][columna])) == null) {
					Celda unaCelda = new Celda(-1, fila, columna);
					unaCelda.getPosibles().add(0, 1);
					unaCelda.getPosibles().add(1, 2);
					unaCelda.getPosibles().add(2, 3);
					unaCelda.getPosibles().add(3, 4);
					unaCelda.getPosibles().add(4, 5);
					unaCelda.getPosibles().add(5, 6);
					unaCelda.getPosibles().add(6, 7);
					unaCelda.getPosibles().add(7, 8);
					unaCelda.getPosibles().add(8, 9);

					if (fila < 3 && columna < 3) {
						unaCelda.setCuadrante(1);
					}
					if (fila < 3 && (columna >= 3 && columna <= 5)) {
						unaCelda.setCuadrante(2);
					}
					if (fila < 3 && columna >= 6) {
						unaCelda.setCuadrante(3);
					}
					if ((fila <= 5 && fila >= 3) && columna < 3) {
						unaCelda.setCuadrante(4);
					}
					if ((fila <= 5 && fila >= 3)
							&& (columna < 6 && columna >= 3)) {
						unaCelda.setCuadrante(5);
					}
					if ((fila <= 5 && fila >= 3) && columna >= 6) {
						unaCelda.setCuadrante(6);
					}
					if (fila >= 6 && columna < 3) {
						unaCelda.setCuadrante(7);
					}
					if ((fila >= 6) && (columna < 6 && columna >= 3)) {
						unaCelda.setCuadrante(8);
					}
					if ((fila >= 6) && columna >= 6) {
						unaCelda.setCuadrante(9);
					}
					sudoku[fila][columna] = unaCelda;
				}
			}
		}
		System.out.println("Imprime la matriz");
		imprimirMatriz();

		int contador = 0;
		boolean ver = true;
		do {
			for (int i = 1; i < 10; i++) {

				buscarUnico(i);
				eliminarImposibles();
				buscarUnico(i);
			}
			contador++;
			System.out.println("Sale una Matrix " + contador);
			imprimirMatriz();
			System.out.println(contador);
			ver = verificarSudoku();
			System.out.println(ver);
		} while (contador < 8);

		// imprimirPosiblesUnicos();
	}

	public static void imprimirMatriz() {
		// Se imprime el mapa
		String fila = "";

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (((Celda) (sudoku[i][j])).getDefinitivo() != -1) {
					fila += ((Celda) (sudoku[i][j])).getDefinitivo() + " ";
				} else {
					if (((Celda) (sudoku[i][j])).getDefinitivo() == -1) {
						fila += "0 ";
					}
				}
			}
			System.out.println(fila);
			fila = "";
		}
	}

	public static void imprimirPosibles() {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				Celda x = ((Celda) (sudoku[i][j]));
				System.out.println("Posibles actuales de " + x.posicion());
				x.imprimirPosibles();
				// System.out.println("Que numero es ***** " +
				// x.getDefinitivo());

			}

		}
	}

	public static void eliminarImposibles() {
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				// System.out.println("Cambio celdas");
				Celda unaCelda = (Celda) (sudoku[fila][columna]);
				if (unaCelda.getDefinitivo() == -1) {
					// System.out.println("Analizar " + unaCelda.posicion());
					// Analizar fila
					analizadorVector(unaCelda, true);
					// Analizar columna
					analizadorVector(unaCelda, false);
					// Analizar cuadrante
					analizarCuadrante(unaCelda);

				}else{
					
						 borrarPosibilidades(unaCelda);
						 analizarCuadrante(unaCelda);
						 }
						 
				
				 
			}
		}
	}

	public static void borrarPosibilidades(Celda laCelda) {
		int fila = laCelda.getFila();
		int columna = laCelda.getColunma();
		Integer numero = laCelda.getDefinitivo();
		// lista test para contener la lista de posibles de una Celda con -1
		ArrayList<Integer> depurafc = new ArrayList<Integer>();

		for (int i = 0; i < 9; i++) {
			// fcCelda es una Celda del sudoku que se esta recorriendo
			Celda fcCelda = (Celda) (sudoku[fila][i]);
			if (fcCelda.getDefinitivo() == -1) {
				depurafc = fcCelda.getPosibles();
				for (int c = 0; c < depurafc.size(); c++) {
					if (depurafc.get(c) == numero) {
						depurafc.remove(c);
						fcCelda.setPosibles(depurafc);
						sudoku[fila][i] = fcCelda;
						System.out
								.println("***********************************************************FILA");

					}
				}

				// fcCelda.imprimirPosibles();

			}
		}

		for (int i = 0; i < 9; i++) {
			Celda fcCelda = (Celda) (sudoku[i][columna]);
			if (fcCelda.getDefinitivo() == -1) {
				depurafc = fcCelda.getPosibles();
				for (int c = 0; c < depurafc.size(); c++) {
					if (depurafc.get(c) == numero) {
						depurafc.remove(c);
						fcCelda.setPosibles(depurafc);
						sudoku[i][columna] = fcCelda;
						System.out
								.println("***********************************************************Columna");
					}
				}

				// fcCelda.imprimirPosibles();
			}
		}
		//

	}

	public static ArrayList<Celda> determinarCuadrante(Celda unaCelda) {
		ArrayList<Celda> cuadro33 = new ArrayList<Celda>();
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				Celda celdaSudoku = (Celda) sudoku[a][b];
				if (celdaSudoku.getCuadrante() == unaCelda.getCuadrante()) {
					cuadro33.add(celdaSudoku);

				}
			}
		}
		return cuadro33;
	}

	public static void analizarCuadrante(Celda unaCelda) {
		ArrayList<Celda> cuadrante33 = determinarCuadrante(unaCelda);
		ArrayList<Integer> posiblesUnaCelda = unaCelda.getPosibles();

		for (int d = 0; d < 9; d++) {
			Celda celdaCuadrante = cuadrante33.get(d);
			if (celdaCuadrante.getDefinitivo() != -1) {
				int numero = celdaCuadrante.getDefinitivo();
				for (int e = 0; e < posiblesUnaCelda.size(); e++) {

					if (posiblesUnaCelda.get(e) == numero) {
						posiblesUnaCelda.remove(e);
						unaCelda.setPosibles(posiblesUnaCelda);
						sudoku[unaCelda.getFila()][unaCelda.getColunma()] = unaCelda;

						if (posiblesUnaCelda.size() == 1) {
							System.out.println("Definitivo "
									+ unaCelda.getPosibles().get(0));
							unaCelda.setDefinitivo(unaCelda.getPosibles()
									.get(0));
							ArrayList<Integer> vacia = unaCelda.getPosibles();
							vacia.clear();
							unaCelda.setPosibles(vacia);
						} else {
							unaCelda.setPosibles(posiblesUnaCelda);
							sudoku[unaCelda.getFila()][unaCelda.getColunma()] = unaCelda;
						}

					}
				}
			}
			//

		}

	}

	public static void buscarUnico(int numeroCuadrante) {
		ArrayList<ArrayList<Celda>> contenedorOpciones = new ArrayList<ArrayList<Celda>>();
		contenedorOpciones.add(0, null);
		contenedorOpciones.add(1, null);
		contenedorOpciones.add(2, null);
		contenedorOpciones.add(3, null);
		contenedorOpciones.add(4, null);
		contenedorOpciones.add(5, null);
		contenedorOpciones.add(6, null);
		contenedorOpciones.add(7, null);
		contenedorOpciones.add(8, null);
		contenedorOpciones.add(9, null);

		boolean imprimir = false;

		if (numeroCuadrante == 6) {
			imprimirPosibles();
			imprimir = true;
		}

		Celda unacelda = new Celda();
		unacelda.setCuadrante(numeroCuadrante);
		ArrayList<Celda> cuadrante33 = determinarCuadrante(unacelda);

		// System.out.println("******Cuadrante: " +numeroCuadrante +", datos: "+
		// cuadrante33.size() +"*****");

		for (int i = 0; i < cuadrante33.size(); i++) {
			Celda x = cuadrante33.get(i);

			if (x.getPosibles().size() > 0) {

				ArrayList<Integer> opcionesX = x.getPosibles();

				if (opcionesX.size() == 1) {
					x.setDefinitivo(opcionesX.get(0));
					x.setPosibles(new ArrayList<Integer>());
					sudoku[x.getFila()][x.getColunma()] = x;
					System.out
							.println("***Ingresará unico definido: "
									+ opcionesX.get(0) + " en la celda "
									+ x.posicion());

					// elimino el numero agregado de la fila, columna y el
					// cuadrante dados.
					borrarPosibilidades(x);
					// Reasigno cuadrante33
					// cuadrante33 = determinarCuadrante(x);
				} else {
					for (int j = 0; j < opcionesX.size(); j++) {
						int numeroOpcion = opcionesX.get(j);
						/*
						 * if(numeroCuadrante == 4) { imprimir = true; }
						 */

						if (contenedorOpciones.get(numeroOpcion) == null) {
							ArrayList<Celda> listado = new ArrayList<Celda>();
							listado.add(x);
							contenedorOpciones.set(numeroOpcion, listado);
						} else {
							ArrayList<Celda> listado = contenedorOpciones
									.get(numeroOpcion);
							listado.add(x);

							contenedorOpciones.set(numeroOpcion, listado);
						}
					}
				}
			}
		}

		if (imprimir) {
			for (int i = 0; i < contenedorOpciones.size(); i++) {
				ArrayList<Celda> lista = contenedorOpciones.get(i);
				if (lista != null) {
					System.out.println("Opciones en la lista " + i);
					for (int j = 0; j < lista.size(); j++) {
						System.out.println(lista.get(j).posicion());
					}
				}
			}
		}

		for (int a = 1; a < contenedorOpciones.size(); a++) {

			if (contenedorOpciones.get(a) != null) {
				ArrayList<Celda> listaCeldas = contenedorOpciones.get(a);

				if (listaCeldas.get(0) != null && listaCeldas.size() == 1) {

					Celda definitiva = listaCeldas.get(0);
					/*
					 * definitiva.setDefinitivo(a); ArrayList<Integer> vacia =
					 * definitiva.getPosibles(); vacia.clear();
					 * definitiva.setPosibles(vacia);
					 * System.out.println("***Ingresará unico calculado: " + a +
					 * " en la celda " + definitiva.posicion());
					 * sudoku[definitiva.getFila()][definitiva.getColunma()] =
					 * definitiva;
					 */

					Celda pifia = new Celda();
					pifia.setFila(definitiva.getFila());
					pifia.setColunma(definitiva.getColunma());
					pifia.setDefinitivo(a);
					pifia.setCuadrante(definitiva.getCuadrante());

//					boolean t = verificarFila(pifia);
//
//					boolean y = verificarColumna(pifia);
					//boolean z=validarCuadrante(pifia);
					boolean y=true;
					boolean t=true;

					if (y && t ) {
						definitiva.setDefinitivo(a);
						ArrayList<Integer> vacia = definitiva.getPosibles();
						vacia.clear();
						definitiva.setPosibles(vacia);
						System.out.println("***Ingresará unico calculado: " + a
								+ " en la celda " + definitiva.posicion());
						sudoku[definitiva.getFila()][definitiva.getColunma()] = definitiva;
					} else {
						definitiva.setDefinitivo(-1);
						System.out.println("No lo puso ---- "
								+ pifia.posicion());
					}

				}
			}
		}

	}

	public static boolean verificarSudoku() {
		int buenos[] = new int[9];
		buenos[0] = 1;
		buenos[1] = 2;
		buenos[2] = 3;
		buenos[3] = 4;
		buenos[4] = 5;
		buenos[5] = 6;
		buenos[6] = 7;
		buenos[7] = 8;
		buenos[8] = 9;

		boolean estan[] = new boolean[9];
		estan[0] = false;
		estan[1] = false;
		estan[2] = false;
		estan[3] = false;
		estan[4] = false;
		estan[5] = false;
		estan[6] = false;
		estan[7] = false;
		estan[8] = false;

		int fila = 0;
		int columna = 0;
		int contador = 0;

		for (fila = 0; fila < 9; fila++) {
			for (columna = 0; columna < 9; columna++) {
				Celda unaCelda = (Celda) (sudoku[fila][columna]);
				int numeroDefinitivo = unaCelda.getDefinitivo();
				// for para ver si el numero esta o no?
				for (int bn = 0; bn < buenos.length; bn++) {
					if (buenos[bn] == numeroDefinitivo) {
						estan[bn] = true;
					}
				}
			}

			if (columna == 9) {
				for (int bn = 0; bn < buenos.length; bn++) {
					if (estan[bn] == true) {
						contador++;

					} else {
						System.out.println("esta malo");
						columna = 10;
						fila = 10;
						return false;
					}

				}

			}
			// reiniciar los valores de verdad del arreglo
			for (int bn = 0; bn < buenos.length; bn++) {
				estan[bn] = false;
			}

		}

		if (contador == 81) {
			System.out.println("SAQUELA ");
			return true;
		} else {
			return false;
		}

	}

	private static void unSudokuTerminado() {
		sudoku = new Object[9][9];

		sudoku[0][0] = (Object) new Celda(7, 0, 0, 1);
		sudoku[0][1] = (Object) new Celda(6, 0, 1, 1);
		sudoku[0][2] = (Object) new Celda(1, 0, 2, 1);
		sudoku[0][3] = (Object) new Celda(5, 0, 3, 2);
		sudoku[0][4] = (Object) new Celda(2, 0, 4, 2);
		sudoku[0][5] = (Object) new Celda(8, 0, 5, 2);
		sudoku[0][6] = (Object) new Celda(3, 0, 6, 3);
		sudoku[0][7] = (Object) new Celda(9, 0, 7, 3);
		sudoku[0][8] = (Object) new Celda(4, 0, 8, 3);

		sudoku[1][0] = (Object) new Celda(3, 1, 0, 1);
		sudoku[1][1] = (Object) new Celda(4, 1, 1, 1);
		sudoku[1][2] = (Object) new Celda(5, 1, 2, 1);
		sudoku[1][3] = (Object) new Celda(1, 1, 3, 2);
		sudoku[1][4] = (Object) new Celda(7, 1, 4, 2);
		sudoku[1][5] = (Object) new Celda(9, 1, 5, 2);
		sudoku[1][6] = (Object) new Celda(6, 1, 6, 3);
		sudoku[1][7] = (Object) new Celda(2, 1, 7, 3);
		sudoku[1][8] = (Object) new Celda(8, 1, 8, 3);

		sudoku[2][0] = (Object) new Celda(9, 2, 0, 1);
		sudoku[2][1] = (Object) new Celda(2, 2, 1, 1);
		sudoku[2][2] = (Object) new Celda(8, 2, 2, 1);
		sudoku[2][3] = (Object) new Celda(3, 2, 3, 2);
		sudoku[2][4] = (Object) new Celda(6, 2, 4, 2);
		sudoku[2][5] = (Object) new Celda(4, 2, 5, 2);
		sudoku[2][6] = (Object) new Celda(5, 2, 6, 3);
		sudoku[2][7] = (Object) new Celda(7, 2, 7, 3);
		sudoku[2][8] = (Object) new Celda(1, 2, 8, 3);

		sudoku[3][0] = (Object) new Celda(1, 3, 0, 4);
		sudoku[3][1] = (Object) new Celda(8, 3, 1, 4);
		sudoku[3][2] = (Object) new Celda(7, 3, 2, 4);
		sudoku[3][3] = (Object) new Celda(6, 3, 3, 5);
		sudoku[3][4] = (Object) new Celda(9, 3, 4, 5);
		sudoku[3][5] = (Object) new Celda(5, 3, 5, 5);
		sudoku[3][6] = (Object) new Celda(2, 3, 6, 6);
		sudoku[3][7] = (Object) new Celda(4, 3, 7, 6);
		sudoku[3][8] = (Object) new Celda(3, 3, 8, 6);

		sudoku[4][0] = (Object) new Celda(6, 4, 0, 4);
		sudoku[4][1] = (Object) new Celda(3, 4, 1, 4);
		sudoku[4][2] = (Object) new Celda(2, 4, 2, 4);
		sudoku[4][3] = (Object) new Celda(8, 4, 3, 5);
		sudoku[4][4] = (Object) new Celda(4, 4, 4, 5);
		sudoku[4][5] = (Object) new Celda(1, 4, 5, 5);
		sudoku[4][6] = (Object) new Celda(9, 4, 6, 6);
		sudoku[4][7] = (Object) new Celda(5, 4, 7, 6);
		sudoku[4][8] = (Object) new Celda(7, 4, 8, 6);

		sudoku[5][0] = (Object) new Celda(4, 5, 0, 4);
		sudoku[5][1] = (Object) new Celda(5, 5, 1, 4);
		sudoku[5][2] = (Object) new Celda(9, 5, 2, 4);
		sudoku[5][3] = (Object) new Celda(7, 5, 3, 5);
		sudoku[5][4] = (Object) new Celda(3, 5, 4, 5);
		sudoku[5][5] = (Object) new Celda(2, 5, 5, 5);
		sudoku[5][6] = (Object) new Celda(8, 5, 6, 6);
		sudoku[5][7] = (Object) new Celda(1, 5, 7, 6);
		sudoku[5][8] = (Object) new Celda(6, 5, 8, 6);

		sudoku[6][0] = (Object) new Celda(8, 6, 0, 7);
		sudoku[6][1] = (Object) new Celda(9, 6, 1, 7);
		sudoku[6][2] = (Object) new Celda(6, 6, 2, 7);
		sudoku[6][3] = (Object) new Celda(2, 6, 3, 8);
		sudoku[6][4] = (Object) new Celda(1, 6, 4, 8);
		sudoku[6][5] = (Object) new Celda(7, 6, 5, 8);
		sudoku[6][6] = (Object) new Celda(4, 6, 6, 9);
		sudoku[6][7] = (Object) new Celda(3, 6, 7, 9);
		sudoku[6][8] = (Object) new Celda(5, 6, 8, 9);

		sudoku[7][0] = (Object) new Celda(2, 7, 0, 7);
		sudoku[7][1] = (Object) new Celda(1, 7, 1, 7);
		sudoku[7][2] = (Object) new Celda(3, 7, 2, 7);
		sudoku[7][3] = (Object) new Celda(4, 7, 3, 8);
		sudoku[7][4] = (Object) new Celda(5, 7, 4, 8);
		sudoku[7][5] = (Object) new Celda(6, 7, 5, 8);
		sudoku[7][6] = (Object) new Celda(7, 7, 6, 9);
		sudoku[7][7] = (Object) new Celda(8, 7, 7, 9);
		sudoku[7][8] = (Object) new Celda(9, 7, 8, 9);

		sudoku[8][0] = (Object) new Celda(5, 8, 0, 7);
		sudoku[8][1] = (Object) new Celda(7, 8, 1, 7);
		sudoku[8][2] = (Object) new Celda(4, 8, 2, 7);
		sudoku[8][3] = (Object) new Celda(9, 8, 3, 8);
		sudoku[8][4] = (Object) new Celda(8, 8, 4, 8);
		sudoku[8][5] = (Object) new Celda(3, 8, 5, 8);
		sudoku[8][6] = (Object) new Celda(1, 8, 6, 9);
		sudoku[8][7] = (Object) new Celda(6, 8, 7, 9);
		sudoku[8][8] = (Object) new Celda(2, 8, 8, 9);
		imprimirMatriz();
		verificarSudoku();

	}

	public static void analizadorVector(Celda unaCelda, boolean factor) {
		// Si factor es true, se analiza filas, si es false, se analiza columnas
		if (factor) {
			// Analiza fila
			// System.out.println("Eliminar fila");
			int fila = unaCelda.getFila();
			ArrayList<Integer> posiblesActuales = unaCelda.getPosibles();
			// System.out.println("Total actuales: " + posiblesActuales.size());
			for (int i = 0; i < 9; i++) {
				Celda revisada = (Celda) (sudoku[fila][i]);
				if ((revisada.getDefinitivo() != -1)) {

					int eliminar = revisada.getDefinitivo();

					for (int j = 0; j < posiblesActuales.size(); j++) {
						if (posiblesActuales.get(j) == eliminar) {

							posiblesActuales.remove(j);
							unaCelda.setPosibles(posiblesActuales);

							/*
							 * if(posiblesActuales.size()==1) { //
							 * System.out.println
							 * ("Definitivo "+unaCelda.getPosibles().get(0)+
							 * "- "+unaCelda.posicion());
							 * unaCelda.setDefinitivo(
							 * unaCelda.getPosibles().get(0));
							 * unaCelda.setPosibles(new ArrayList<Integer>()); }
							 * else { unaCelda.setPosibles(posiblesActuales); }
							 */

						}
					}
				}
			}

		} else {
			// Analiza columna
			int columna = unaCelda.getColunma();
			// System.out.println("Eliminar columna");
			ArrayList<Integer> posiblesActuales = unaCelda.getPosibles();
			// System.out.println("Total actuales: " + posiblesActuales.size());
			for (int i = 0; i < 9; i++) {
				Celda revisada = (Celda) (sudoku[i][columna]);
				// && (revisada.getFila() != unaCelda.getFila() &&
				// revisada.getColunma() != unaCelda.getColunma())
				if ((revisada.getDefinitivo() != -1)) {
					int eliminar = revisada.getDefinitivo();
					// Eliminar de posibles de unaCelda el valor de eliminar.
					for (int j = 0; j < posiblesActuales.size(); j++) {
						if (posiblesActuales.get(j) == eliminar) {

							posiblesActuales.remove(j);
							unaCelda.setPosibles(posiblesActuales);
							/*
							 * if(posiblesActuales.size()==1) { //
							 * System.out.println
							 * ("Definitivo "+unaCelda.getPosibles().get(0)+
							 * "- "+unaCelda.posicion());
							 * unaCelda.setDefinitivo(
							 * unaCelda.getPosibles().get(0));
							 * unaCelda.setPosibles(new ArrayList<Integer>()); }
							 * else { unaCelda.setPosibles(posiblesActuales); }
							 */

						}
					}
				}
			}
		}
	}

	public static void imprimirPosiblesUnicos() {
		int numero = 0;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (((Celda) (sudoku[i][j])).getDefinitivo() == -1) {
					Celda x = (Celda) (sudoku[i][j]);
					if ((x.getPosibles().size()) == 1) {
						System.out.println(x.posicion());
						numero++;
					}
				}
			}
		}

		System.out.println("Numero de celdas con una unica opcion : " + numero);
	}

	// *************************************************************************************************************
	// Para validar filas y columnas
	public static ArrayList<Celda> obtenerFila(int fila) {
		ArrayList<Celda> filasCeldas = new ArrayList<Celda>();
		for (int c = 0; c < 9; c++) {
			Celda celdaPorFila = (Celda) sudoku[fila][c];
			filasCeldas.add(celdaPorFila);

		}
		return filasCeldas;

	}

	public static boolean verificarFila(Celda pronosticada) {
		ArrayList<Celda> filaDeCeldapronosticada = obtenerFila(pronosticada
				.getFila());
		int numeroPronosticado = pronosticada.getDefinitivo();
		boolean valido = false;

		for (int columna = 0; columna < filaDeCeldapronosticada.size(); columna++) {
			Celda celdaJuez = filaDeCeldapronosticada.get(columna);

			if (celdaJuez.getColunma() != pronosticada.getColunma()) {
				if ((celdaJuez.getDefinitivo() == numeroPronosticado)) {
					System.out.println("no puede ir!! ---"
							+ celdaJuez.getDefinitivo() + "POR PONER"
							+ pronosticada.getDefinitivo());
					return valido;
				}

			}

		}
		valido = true;
		return valido;

	}

	public static ArrayList<Celda> obtenerColumna(int columna) {
		ArrayList<Celda> columnasCeldas = new ArrayList<Celda>();
		for (int f = 0; f < 9; f++) {
			Celda celdaPorFila = (Celda) sudoku[f][columna];
			columnasCeldas.add(celdaPorFila);

		}
		return columnasCeldas;
	}

	public static boolean verificarColumna(Celda pronosticada) {
		ArrayList<Celda> columnaDeCeldapronosticada = obtenerColumna(pronosticada
				.getFila());
		int numeroPronosticado = pronosticada.getDefinitivo();
		boolean valido = false;

		for (int columna = 0; columna < columnaDeCeldapronosticada.size(); columna++) {
			Celda celdaJuez = columnaDeCeldapronosticada.get(columna);

			if ((celdaJuez.getFila() != pronosticada.getFila())) {
				if ((celdaJuez.getDefinitivo() == numeroPronosticado)) {
					return valido;

				}
				System.out.println("FUERA DEL IF!! ---"
						+ celdaJuez.getDefinitivo() + "POR PONER"
						+ pronosticada.getDefinitivo());
			}
		}
		valido = true;
		return valido;

	}

	// Para validar filas y columnas
	// *************************************************************************************************************

	public static boolean validarCuadrante(Celda pronosticada) {
		boolean valido = false;
		ArrayList<Celda> cuadrante33 = determinarCuadrante(pronosticada);
		int numeroPronosticado = pronosticada.getDefinitivo();
		for (int i = 0; i < 9; i++) {
			Celda cuadranteCelda = cuadrante33.get(i);
			if ((cuadranteCelda.getFila() != pronosticada.getFila())
					&& (cuadranteCelda.getColunma() != pronosticada
							.getColunma())) {
				if ((cuadranteCelda.getDefinitivo() == numeroPronosticado)) {
					valido = true;
					return valido;
				}
			}

		}
		return valido;

	}
}
