package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by migank on 20/03/15.
 */
public class compte extends Activity {

    String EXTRA_NAME = "user_name";
    String EXTRA_PASSWORD = "user_password";
    String EXTRA_PSEUDO = "user_pseudo";
    String EXTRA_EMAIL= "user_email";
    public class AsyncGet extends AsyncTask<String, Long, String> {

        public void onPreExecute() {
            super.onPreExecute();
        }

        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(String... url) {
            try {
                BDDTools.entry(url[0], EXTRA_NAME, EXTRA_EMAIL, EXTRA_PSEUDO, EXTRA_PASSWORD);
                return "OK";
            } catch (IOException e) {
                return null;
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compte);

        Intent intent = getIntent();
        TextView nameDisplay = (TextView) findViewById(R.id.name_display);
        TextView passwordDisplay = (TextView) findViewById(R.id.password_display);
        TextView emailDisplay = (TextView) findViewById(R.id.email_display);
        TextView nicknameDisplay = (TextView) findViewById(R.id.nickname_display);
        if (intent != null) {
            nameDisplay.setText(intent.getStringExtra(EXTRA_NAME));
            passwordDisplay.setText(intent.getStringExtra(EXTRA_PASSWORD));
            emailDisplay.setText(intent.getStringExtra(EXTRA_EMAIL));
            nicknameDisplay.setText(intent.getStringExtra(EXTRA_PSEUDO));
        }
    }
    public void creationCompte(View view){

        TextView nameDisplay = (TextView) findViewById(R.id.name_display);
        TextView passwordDisplay = (TextView) findViewById(R.id.password_display);
        TextView emailDisplay = (TextView) findViewById(R.id.email_display);
        TextView nicknameDisplay = (TextView) findViewById(R.id.nickname_display);
        EXTRA_NAME =nameDisplay.getText().toString();
        EXTRA_EMAIL =  emailDisplay.getText().toString();
        EXTRA_PSEUDO = nicknameDisplay.getText().toString();
        EXTRA_PASSWORD = passwordDisplay.getText().toString();

        if(EXTRA_PASSWORD.isEmpty()||EXTRA_PSEUDO.isEmpty()||EXTRA_EMAIL.isEmpty()||EXTRA_NAME.isEmpty()) {
            Toast.makeText(this, "Veuiller Remplir tous les Champs",
                    Toast.LENGTH_LONG).show();
        }else {

            final String url = "http://www.perdu.com";
            AsyncGet ag = new AsyncGet();
            ag.execute(url);
            try {
                //faire le post
                Toast.makeText(this, "Envoyé à" + url,
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(compte.this, Menu.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Echec de l'envoi à" + url,
                        Toast.LENGTH_LONG).show();
            }

        }

    }

    }

