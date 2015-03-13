package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void connect(View v){
        TextView pseudo = (TextView)findViewById(R.id.pseudo);
        TextView pwd = (TextView)findViewById(R.id.pwd);
        String strPseudo = pseudo.getText().toString();
        String strMDP =pwd.getText().toString();
        if(strPseudo.equals("toto") && strMDP.equals("toto")){
            System.out.println("On est entré ici");
            Intent i = new Intent(this,MenuLightRingActivity.class);
            startActivity(i);
            }else{
            TextView error = (TextView) findViewById(R.id.ErrorMessage);
            error.setText("Le pseudo ou le mot de passe est incorrect");
        }


    }
}
