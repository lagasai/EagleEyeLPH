package edu.erau.eagleeye;

// Rebecca Ivans

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class NoMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set GUI to correct xml file
        setContentView(R.layout.activity_no_match);

        //Take picture again button listener
        Button newPicButton = (Button) findViewById(R.id.newPicButton);
        newPicButton.setOnClickListener(
                //Create object of OnClickListener and assign to the Button
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //This is where I reopen the TakePicture by clicking the button
                        Intent myIntent = new Intent(NoMatch.this, TakePicture.class);
                        startActivity(myIntent);
                    }
                }
        );

        //Quit button listener
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(
                new Button.OnClickListener() {
                    //Create object of OnClickListener and assign to the Button
                    public void onClick(View v) {
                        //This is where I "close the app" by returning to the home screen
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                }
        );
    }
}

