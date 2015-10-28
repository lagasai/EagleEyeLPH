package edu.erau.eagleeye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Process;

/**
 * This class, NoMatch, is an activity that displays when no match has been found for the user
 * image.  It displays a short message along with the Eagle Eye logo.  In following versions, the
 * logo will be replaced with the user image.
 * @author Rebecca Ivans
 * @version 1.0
 */
public class NoMatch extends AppCompatActivity {

    /**
     * This method overrides the onCreate method from AppCompatActivity.  It sets the content view
     * to the correct layout, activity_no_match.xml, and provides listeners for the "Take Another
     * Picture" and "Quit" buttons in that layout
     *
     * @param savedInstanceState A Bundle containing the activity's previously frozen state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set GUI to correct xml file
        setContentView(R.layout.activity_no_match);

        /*
        The Button newPicButton is instantiated and identified as the newPicButton in the layout,
         and a listener is set to capture and handle "click" events for this button by opening the
         "TakePicture" activity
         */
        Button newPicButton = (Button) findViewById(R.id.newPicButton);
        newPicButton.setOnClickListener(
                //Create object of OnClickListener and assign to the Button
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //This is where I reopen the TakePicture by clicking the button
                        Intent takePicIntent = new Intent(NoMatch.this, TakePicture.class);
                        startActivity(takePicIntent);
                    }
                }
        );

        /*
        The Button quitButton is instantiated and identified as the quitButton in the layout,
         and a listener is set to capture and handle "click" events for this button by opening the
         Home Screen of the device and killing the Eagle Eye app
         */
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(
                new Button.OnClickListener() {
                    //Create object of OnClickListener and assign to the Button
                    public void onClick(View v) {
                        //This is where I "close the app" by returning to the home screen
                        Intent quitterIntent = new Intent(Intent.ACTION_MAIN);
                        quitterIntent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(quitterIntent);
                        //This line actually kills the app
                        Process.killProcess(Process.myPid());
                    }
                }
        );
    }
}

