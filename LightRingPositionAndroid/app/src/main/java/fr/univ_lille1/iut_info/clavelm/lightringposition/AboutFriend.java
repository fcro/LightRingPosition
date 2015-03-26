package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by migank on 25/03/15.
 */
public class AboutFriend extends Activity {

    //suppression et ajout
    String aSupprimer="";
    String aAjouter="";

//pour recuperer la liste d'amis
    public class AsyncGet extends AsyncTask<String, Long, String> {
        public void onPreExecute() {
            super.onPreExecute();
        }

        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(String... url) {
            String nickNames=new String();

            try {
                for(int i=0;i<BDDTools.listFriend(url[0]).size();++i)
                  nickNames+=(BDDTools.listFriend(url[0]).get(i).getNickname())+"+";

            } catch (Exception e) {
                return null;
            }
            return nickNames.toString();

        }
    }

    //pour supprimer des amis
    public class AsyncSupprimer extends AsyncTask<String, Long, String> {
        public void onPreExecute() {
            super.onPreExecute();
        }

        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(String... url) {
            try {
                BDDTools.deleteFriend(url[0],aSupprimer);
                return "OK";
            } catch (IOException e) {
                return null;
            }
        }
    }

//Pour ajouter des amis
    public class AsyncAjout extends AsyncTask<String, Long, String> {
        public void onPreExecute() {
            super.onPreExecute();
        }

        public void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected String doInBackground(String... url) {
            try {
                BDDTools.insertFriend(url[0],aAjouter);
                return "OK";
            } catch (IOException e) {
                return null;
            }
        }
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutfriends);
    }
    //clic inviter amis par message
    public void inviter(View view){
        Intent intent = new Intent(AboutFriend.this, Invitation.class);
        startActivity(intent);

    }
    //pour obtenir la liste d'amis
    public void listeAmis(View view){

        TextView liste=(TextView)findViewById(R.id.champListe);

            String response;
            final String url = getString(R.string.url) + "/webapi/friends/";
            AsyncGet ag = new AsyncGet();
            ag.execute(url);
            try {
                response = ag.get();
                Toast.makeText(this, "Réponse de " + url + " : " + response,
                        Toast.LENGTH_LONG).show();
                //ask
                int debut=0;
                for(int i=0;i<response.length();++i){
                    if(response.charAt(i)=='+') {
                        liste.append(response.substring(debut, i) + "\n");
                        debut = i + 1;
                    }

                }


            } catch (Exception e) {
                Toast.makeText(this, "Aucune réponse reçue de " + url,
                        Toast.LENGTH_LONG).show();
            }

        }

//clic suppression d'amis
   public void suppr(View view){
       EditText sup=(EditText) findViewById(R.id.champSup);

       if(sup.getText().toString().isEmpty()) {
           sup.setText("veuillez entrer un login");
       }else {
           aSupprimer += sup.getText().toString();
           final String url = getString(R.string.url) + "/webapi/friends/" + sup.getText();
            AsyncSupprimer ag = new AsyncSupprimer();
           ag.execute(url);
           try {
               //faire le post
               Toast.makeText(this, "Envoyé à" + url,
                       Toast.LENGTH_LONG).show();

           } catch (Exception e) {
               sup.setText("Echec de la recherchez d'amis");
               Toast.makeText(this, "Echec de l'envoi à" + url,
                       Toast.LENGTH_LONG).show();
           }
       }

   }
    //clic ajout d'amis
    public void ajouter(View view) {
        EditText ret = (EditText) findViewById(R.id.champRet);
        if (ret.getText().toString().isEmpty()) {
            ret.setText("veuillez entrer un login");

        } else {
            aAjouter += ret.getText().toString();
            final String url = getString(R.string.url) + "/webapi/friends";
            AsyncAjout ag = new AsyncAjout();
            ag.execute(url);
            try {
                //faire le post
                Toast.makeText(this, "Envoyé à" + url,
                        Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                ret.setText("Echec de la recherchez d'amis");
                Toast.makeText(this, "Echec de l'envoi à" + url,
                        Toast.LENGTH_LONG).show();
            }

        }
    }
}
