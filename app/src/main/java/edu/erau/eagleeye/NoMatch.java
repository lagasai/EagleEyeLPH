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
        setContentView(R.layout.activity_no_match);

        Button newPicButton = (Button) findViewById(R.id.newPicButton);
        newPicButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //This is where I open the OtherActivity by clicking the button
                        Intent myIntent = new Intent(NoMatch.this, TakePicture.class);
                        NoMatch.this.startActivity(myIntent);
                    }
                }
        );
    }
}

