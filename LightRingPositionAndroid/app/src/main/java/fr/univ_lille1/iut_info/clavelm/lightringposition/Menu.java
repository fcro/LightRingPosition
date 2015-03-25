package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by migank on 23/03/15.
 */
public class Menu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
    public void affichageScores(View view){
        Intent intent = new Intent(Menu.this, Scores.class);
        startActivity(intent);
    }
    public void amis(View view){
        Intent intent = new Intent(Menu.this, AboutFriend.class);
        startActivity(intent);
    }


}

