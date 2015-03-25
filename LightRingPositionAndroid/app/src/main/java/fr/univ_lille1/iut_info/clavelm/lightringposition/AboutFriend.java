package fr.univ_lille1.iut_info.clavelm.lightringposition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by migank on 25/03/15.
 */
public class AboutFriend extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutfriends);
    }
    public void inviter(View view){
        Intent intent = new Intent(AboutFriend.this, Invitation.class);
        startActivity(intent);

    }
    public void listeAmis(View view){

    }
}
