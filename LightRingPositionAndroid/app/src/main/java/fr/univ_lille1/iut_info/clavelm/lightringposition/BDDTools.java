package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public  class BDDTools {

    public static String identification(String url, String login, String password) throws ClientProtocolException, IOException {
        String reponse = null;

        HttpGet requete = new HttpGet(url);
        requete.setHeader("Authorization", "Basic " +
                Base64.encodeToString((login + ":" + password).getBytes(), Base64.DEFAULT));
        AndroidHttpClient client = AndroidHttpClient.newInstance(Build.MODEL);

        try {
            reponse = client.execute(requete, new BasicResponseHandler());
        } finally {
            if (client != null) client.close();
        }
        return reponse;
    }

    public static void entry(String url, String name, String email, String pseudo, String passwd) throws ClientProtocolException, IOException {
        String reponse ;
        HttpPost requete;
        AndroidHttpClient client;
        reponse = null;


        requete = new HttpPost(url);
        requete.setHeader("Authorization", "Basic " +
                Base64.encodeToString((name + ":" + passwd).getBytes(), Base64.DEFAULT));
        client = AndroidHttpClient.newInstance(Build.MODEL);

        try {
            reponse = client.execute(requete, new BasicResponseHandler());
        } finally {
            if (client != null) client.close();
        }

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("name",name));
            nameValuePairs.add(new BasicNameValuePair("email",email));
            nameValuePairs.add(new BasicNameValuePair("pseudo",pseudo));
            nameValuePairs.add(new BasicNameValuePair("password",passwd));


            requete.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response;
            response = client.execute(requete);


        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }
   public static void insertFriend(String url,String friendLogin) throws ClientProtocolException, IOException{
       String reponse ;
       HttpPost requete;
       AndroidHttpClient client;
       reponse = null;


       requete = new HttpPost(url);
       client = AndroidHttpClient.newInstance(Build.MODEL);

       try {
           reponse = client.execute(requete, new BasicResponseHandler());
       } finally {
           if (client != null) client.close();
       }

       try {
           // Add your data
           List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
           nameValuePairs.add(new BasicNameValuePair("newFriend",friendLogin));
           requete.setEntity(new UrlEncodedFormEntity(nameValuePairs));

           // Execute HTTP Post Request
           HttpResponse response;
           response = client.execute(requete);


       } catch (ClientProtocolException e) {
           // TODO Auto-generated catch block
       } catch (IOException e) {
           // TODO Auto-generated catch block
       }

   }
public static void deleteFriend(String url, String friendLogin)throws ClientProtocolException, IOException{
    String reponse ;
    HttpPost requete;
    AndroidHttpClient client;
    reponse = null;


    requete = new HttpPost(url);
    client = AndroidHttpClient.newInstance(Build.MODEL);

    try {
        reponse = client.execute(requete, new BasicResponseHandler());
    } finally {
        if (client != null) client.close();
    }

    try {
        // Add your data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("newFriend",friendLogin));
        requete.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        // Execute HTTP Post Request
        HttpResponse response;
        response = client.execute(requete);


    } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
    } catch (IOException e) {
        // TODO Auto-generated catch block
    }


}
    public static ArrayList<User>listFriend(String theUrl,String login)throws ClientProtocolException, IOException{

        ArrayList<User> friends = new ArrayList<User>();

        try {
            URL url= new URL(theUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
        String result = InputStreamOperations.InputStreamToString(inputStream);

        // On récupère le JSON complet
        JSONObject jsonObject = new JSONObject(result);
        // On récupère le tableau d'objets qui nous concernent
        JSONArray array = new JSONArray(jsonObject.getString("User"));
        // Pour tous les objets on récupère les infos
        for (int i = 0; i < array.length(); i++) {
            // On récupère un objet JSON du tableau
            JSONObject obj = new JSONObject(array.getString(i));
            // On fait le lien Personne - Objet JSON
            User friend = new User();
            friend.setNickname(obj.getString("Nickname"));
            // On ajoute la personne à la liste
            friends.add(friend);

        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    // On retourne la liste des personnes
    return friends;



}





}
