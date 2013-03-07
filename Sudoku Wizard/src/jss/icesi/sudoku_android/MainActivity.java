package jss.icesi.sudoku_android;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public Button boton_resolver;
	public Button boton_comprobar;
	public Button boton_desafio;
	public Button configuracion;
	public ImageView acerca;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		boton_resolver = (Button) findViewById(R.id.boton_resolver);
		boton_comprobar = (Button) findViewById(R.id.boton_comprobar);
		boton_desafio = (Button) findViewById(R.id.boton_desafio);
		configuracion = (Button) findViewById(R.id.boton_configuracion);
		acerca = (ImageView) findViewById(R.id.acerca_de);
;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onResume() 
	{
		super.onResume();

		boton_resolver.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				llamadoAResolver();
			}
		});

		boton_comprobar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				llamadoAComprobar();
			}
		});

		boton_desafio.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				llamadoADesafio();
			}
		});

		configuracion.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				llamadoAConfiguracion();
			}
		});
		
		acerca.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				llamadoAcerca();
			}
		});
	}

	public void onStop() {
		super.onStop();
	}
	
	public void llamadoAResolver()
	{
		Intent intent = new Intent(this, ActividadResolver.class);
		startActivity(intent);
	}
	
	public void llamadoAComprobar()
	{
		Intent intent = new Intent(this, ActividadComprobar.class);
		startActivity(intent);
	}
	
	public void llamadoADesafio()
	{
		Intent intent = new Intent(this, ActividadDesafio.class);
		startActivity(intent);
	}
	
	public void llamadoAConfiguracion()
	{
		Intent intent = new Intent(this, ActividadConfiguracion.class);
		startActivity(intent);
	}
	
	public void llamadoAcerca()
	{
		Intent intent = new Intent(this, ActividadAcerca.class);
		startActivity(intent);
	}
	
}