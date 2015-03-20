package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    String strPseudo;
    String strMDP;

    public class AsyncGet extends AsyncTask<String, Long, String> {
        protected QueryHandler qh;

        public void onPreExecute() {
            super.onPreExecute();
        }

        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(String... url) {
            try {
                qh = new QueryHandler(url[0], strPseudo, strMDP);
                return qh.get();
            } catch (IOException e) {
                return null;
            }
        }
    }
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

    public void connect(View v) {
        TextView pseudo = (TextView) findViewById(R.id.pseudo);
        TextView pwd = (TextView) findViewById(R.id.pwd);
        strPseudo = pseudo.getText().toString();
        strMDP = pwd.getText().toString();

        if (strPseudo.equals("toto") && strMDP.equals("toto")) {
            Intent i = new Intent(this, MenuLightRingActivity.class);
            startActivity(i);

        } else if (strPseudo.equals("admin") && strMDP.equals("admin")) {
            String response;
            final String url = "http://www.perdu.com";
            AsyncGet ag = new AsyncGet();
            ag.execute(url);
            try {
                response = ag.get();
                Toast.makeText(this, "Réponse de " + url + " : " + response,
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Aucune réponse reçue de " + url,
                        Toast.LENGTH_LONG).show();
            }

        } else {
            TextView error = (TextView) findViewById(R.id.ErrorMessage);
            error.setText("Le pseudo ou le mot de passe est incorrect");
        }
    }
}