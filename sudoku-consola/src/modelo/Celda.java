package modelo;

import java.util.ArrayList;


public class Celda {

		public int definitivo = -1;
		public ArrayList<Integer> posibles = new ArrayList<Integer>();
		public int fila;
		public int colunma;
		public int cuadrante;
		
		public Celda(int definitivo, int fila, int colunma, int cuadrante) {
			super();
			this.definitivo = definitivo;
			this.fila = fila;
			this.colunma = colunma;
			this.cuadrante = cuadrante;
		}
		
		public Celda(int definitivo, int fila, int colunma) {
			super();
			this.definitivo = definitivo;
			this.fila = fila;
			this.colunma = colunma;
			this.cuadrante = 0;
		}
		
		public Celda(){
			super();
		}

		public int getDefinitivo() {
			return definitivo;
		}

		public void setDefinitivo(int definitivo) {
			this.definitivo = definitivo;
		}

		public ArrayList<Integer> getPosibles() {
			return posibles;
		}

		public void setPosibles(ArrayList<Integer> posibles) {
			this.posibles = posibles;
		}

		public int getFila() {
			return fila;
		}

		public void setFila(int fila) {
			this.fila = fila;
		}

		public int getColunma() {
			return colunma;
		}

		public void setColunma(int colunma) {
			this.colunma = colunma;
		}
		
		public int getCuadrante() {
			return cuadrante;
		}

		public void setCuadrante(int cuadrante) {
			this.cuadrante = cuadrante;
		}

		public void imprimirPosibles()
		{
			String cadena = "";
			for(int i = 0; i <posibles.size();i++)
			{
				cadena += posibles.get(i)+", ";
			}
			System.out.println(cadena);
		}
		
		public String posicion()
		{
			return "("+fila + "," +colunma+") C: " +cuadrante;
		}
		
		
}





