package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.util.Base64;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;

public class QueryHandler {
    public String reponse ;

    public QueryHandler(String url, String login, String password) throws ClientProtocolException, IOException {
        reponse = null;

        HttpGet requete = new HttpGet(url);
        requete.setHeader("Authorization", "Basic " +
                Base64.encodeToString((login + ":" + password).getBytes(), Base64.DEFAULT));
        AndroidHttpClient client = AndroidHttpClient.newInstance(Build.MODEL);

        try {
            reponse = client.execute(requete, new BasicResponseHandler());
        } finally {
            if (client != null) client.close();
        }
    }

    public String get() {
        return reponse ;
    }
}
