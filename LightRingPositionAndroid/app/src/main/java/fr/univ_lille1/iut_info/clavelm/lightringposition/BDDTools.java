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
import java.util.ArrayList;
import java.util.List;

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




}
