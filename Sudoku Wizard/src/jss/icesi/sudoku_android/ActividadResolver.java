
package jss.icesi.sudoku_android;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ActividadResolver extends Activity {

	public SharedPreferences myPrefs;
	public String sudoku_resolver;
	
	public ArrayList<TextView> listaCeldas;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad_resolver);
		
		listaCeldas = new ArrayList<TextView>();
		obtenerIdCeldas();
		
		myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
		myPrefs.edit().clear();
		
		sudoku_resolver = myPrefs.getString("Resolver", "-");
		
		if (!sudoku_resolver.equals("-"))
		{
			Log.e("Reestablecer Sudoku", "Entro!" );
			
			String[] celdas = sudoku_resolver.split("#");
			for (int i = 0; i < celdas.length; i++) {
				String parte[] = celdas[i].split(",");
				// 0 = defi, 1 = fila, 2 = col, 3 = cuadr.

				String tag = "f" + parte[1] + "c" + parte[2] + "c" + parte[3];
				Log.e("Tag buscado:", tag );
				Log.e("Longitud: ", ""+listaCeldas.size() );
				Log.e("Insertar: ", ""+parte[0] );
				
				for (int j = 0; j < listaCeldas.size()-1; j++) 
				{
					TextView componente = listaCeldas.get(j);
					Log.e("Componente: ", ""+componente.getTag() );
					if (componente.getTag().equals(tag))
					{
						if (!parte[0].equals("-1")) {
							Log.e("? ", "Saquelo!" );
							componente.setText(parte[0]);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_actividad_resolver, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart(); // Always call the superclass method first
		Log.e("cicloVida", "onStart");
	}

	@Override
	public void onRestart() {
		super.onRestart(); // Always call the superclass method first
		Log.e("cicloVida", "onRestart");
	}

	@Override
	public void onPause() {
		super.onPause();
		String sudokuActual = guardarSudoku();
		listaCeldas.clear();
		myPrefs.edit().putString("Resolver", sudokuActual).commit();
		
		Log.e("cicloVida", "onPause");
	}

	public String guardarSudoku() 
	{
		String total = "";
		
		Log.e("Tamano de la Lista",""+listaCeldas.size());
		for (int j = 0; j <81; j++) {
			TextView componente = listaCeldas.get(j);
			String definitivo= ""+componente.getText();
			if (definitivo.length()==0){
				definitivo=-1+"";
			}	
			//Llamar al metodo del sudoku que retorna los posibles
			String tag=(String) componente.getTag();
			char fila=tag.charAt(1);
			Log.e("FILA",""+fila);
			char columna=tag.charAt(3);
			Log.e("Columna",""+columna);
			char cuadrante=tag.charAt(5);
			Log.e("Cuadrante",""+cuadrante);
						
			total=total+definitivo+","+fila+","+columna+","+cuadrante+"#";

		}
		return total;
	}
	
	public void obtenerIdCeldas() {
		for (int i = 2131230725; i < 2131230815; i++) {
			//No encuentra el hp!
			TextView idC;
			try {
				idC = (TextView) findViewById(i);
				
				if(idC == null)
				{
					listaCeldas.add((TextView) findViewById(2131230806));
					Log.e("Madre!", "" + (TextView) findViewById(2131230806).getTag());
				}
				else
				{
					listaCeldas.add(idC);
				}
				
				listaCeldas.add(idC);
				
				Log.e("TAG", "" + idC.getTag());

			} catch (Exception e) {
				//Log.e("ID de la FILA del layaut", "" + i);
			}
		}
	}
	
	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first
		Log.e("cicloVida", "onResumen");
	}

	@Override
	public void onStop() {
		super.onStop(); // Always call the superclass method first
		Log.e("cicloVida", "onStop");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("cicloVida", "onDestroy");
	}

}
