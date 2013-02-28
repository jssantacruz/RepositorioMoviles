package jss.icesi.sudoku_android;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class ActividadDesafio extends Activity {

	public TextView textoTiempo;
	public Handler handler = new Handler(); // controlador de pila de hilos
	public Timer timer;
	public boolean estado = true;
	public int minuto = 0;
	public int segundo = 0;
	public SharedPreferences myPrefs;
	public String reloj;
	public String sudoku_desafio;

	public ArrayList<TextView> listaCeldas;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad_desafio);
		listaCeldas = new ArrayList<TextView>();
		textoTiempo = (TextView) findViewById(R.id.tiempo);
		myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
		reloj = myPrefs.getString("Reloj", "-");

		if (!reloj.equals("-")) {
			String[] split = reloj.split(";");
			minuto = Integer.parseInt(split[0]);
			segundo = Integer.parseInt(split[1]);
		}
		obtenerIdCeldas();
		myPrefs.edit().clear();

		sudoku_desafio = myPrefs.getString("Desafio", "-");
		
		if (!sudoku_desafio.equals("-"))
		{
			//Log.e("Reestablecer Sudoku", "Entro!" );
			
			String[] celdas = sudoku_desafio.split("#");
			for (int i = 0; i < celdas.length; i++) {
				String parte[] = celdas[i].split(",");
				// 0 = defi, 1 = posib, 2 = fila, 3 = col, 4 = cuadr.

				String tag = "f" + parte[2] + "c" + parte[3] + "c" + parte[4];
				//Log.e("Tag buscado:", tag );
				//Log.e("Longitud: ", ""+listaCeldas.size() );
				//Log.e("Insertar: ", ""+parte[0] );
				
				for (int j = 0; j < listaCeldas.size()-1; j++) 
				{
					TextView componente = listaCeldas.get(j);
					//Log.e("Componente: ", ""+componente.getTag() );
					if (componente.getTag().equals(tag))
					{
						if (!parte[0].equals("-1")) {
							//Log.e("? ", "Saquelo!" );
							componente.setText(parte[0]);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_actividad_desafio, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onRestart() {
		super.onRestart(); // Always call the superclass method first
		estado = true;

		reloj = myPrefs.getString("Reloj", "-");
		if (!reloj.equals("-")) {
			String[] split = reloj.split(";");
			minuto = Integer.parseInt(split[0]);
			segundo = Integer.parseInt(split[1]);
		}

		timer = new Timer();
		metodoTemporizador();
		Log.e("cicloVida", "onRestart");

	}

	@Override
	public void onPause() {
		super.onPause();
		
		
		estado = false;
		// timer.cancel();
		myPrefs.edit().putString("Reloj",new String("" + minuto + ";" + "" + segundo)).commit();
		
		String sudokuActual = guardarSudoku();
		listaCeldas.clear();
		Log.e("Que se guardo??", sudokuActual );
		myPrefs.edit().putString("Desafio", sudokuActual).commit();
		Log.e("cicloVida", "onPause");

	}

	public String guardarSudoku() {
		String total = "";
		
		//Log.e("Tamano de la Lista",""+listaCeldas.size());
		for (int j = 0; j <81; j++) {
			TextView componente = listaCeldas.get(j);
			String definitivo= ""+componente.getText();
			if (definitivo.length()==0){
				definitivo=-1+"";
			}	
			//Llamar al metodo del sudoku que retorna los posibles
			String tag=(String) componente.getTag();
			char fila=tag.charAt(1);
			//Log.e("FILA",""+fila);
			char columna=tag.charAt(3);
			//Log.e("Columna",""+columna);
			char cuadrante=tag.charAt(5);
			//Log.e("Cuadrante",""+cuadrante);
						
			total=total+definitivo+", posibles"+","+fila+","+columna+","+cuadrante+"#";

		}

		return total;

	}

	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first
		estado = true;
		timer = new Timer();
		metodoTemporizador();
		Log.e("cicloVida", "onResumen");

	}

	@Override
	public void onStop() {
		super.onStop(); // Always call the superclass method first
		estado = false;
		timer.cancel();
		Log.e("cicloVida", "onStop");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("cicloVida", "onDestroy");

	}

	public void metodoTemporizador() {
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				// le entrego el hilo al handler
				handler.post(new Runnable() {

					public void run() {
						caminaReloj();

					}
				});

			}

		};
		timer.schedule(timerTask, 0, 1000);

	}

	public void caminaReloj() {
		if (estado == true) {
			segundo = segundo + 1;
			if (segundo > 59) {
				minuto = minuto + 1;
				segundo = 0;
			}
			// mostrando el tiempo de la forma 00:00
			if (minuto < 10 && segundo < 10) {
				textoTiempo.setText("0" + minuto + ":0" + segundo + "");
			} else {
				if (minuto < 10 && segundo >= 10) {
					textoTiempo.setText("0" + minuto + ":" + segundo + "");
				} else {
					textoTiempo.setText("" + minuto + ":" + segundo + "");
				}
			}

		}

	}

	public void obtenerIdCeldas() {
		for (int i = 2131230725; i < 2131230815; i++) {
			TextView idC;
			try {
				idC = (TextView) findViewById(i);
				listaCeldas.add(idC);
				Log.e("TAG", "" + idC.getTag());

			} catch (Exception e) {
				//Log.e("ID de la FILA del layaut", "" + i);
			}

		}
	}

}
