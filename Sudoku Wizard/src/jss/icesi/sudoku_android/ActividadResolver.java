package jss.icesi.sudoku_android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActividadResolver extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_resolver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_resolver, menu);
        return true;
    }
}
