package edu.erau.eagleeye;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Process;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This activity is responsible for the GUI after no matches have been found from the TakePicture activity.
 * It will display that no match has been found using the image taken by the user, and prompts the user
 * with the option to take another picture to compare with the reference image database.
 *
 * @author Los Pollos Hermanos
 * @version S2
 */
public class NoMatch extends AppCompatActivity {

    public static Integer testQueryImage;
    public static boolean testImage;
    public static Bitmap queryImageSmall;

    /**
     * This class is responsible for saving the previous activities state and loading the NoMatch activity
     * for display to the user. It sets the GUI to the correct XML file, and the ImageView to display
     * the query image Uri. This method detects whether TakePicture is functioning via testing mode, or
     * not, and displays the reference picture that was not matched to the database back to the user with
     * the option of capturing a new image via the TakePicture activity. This method also provides button
     * listeners for the “Take Another Picture” button.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set GUI to correct xml file
        setContentView(R.layout.activity_no_match);

        //Set ImageView to display the queryImage Uri
        ImageView noMatchPic = (ImageView)findViewById(R.id.noMatchPic);

        if (testImage){
            noMatchPic.setImageResource(testQueryImage);
        }else{
            noMatchPic.setImageBitmap(queryImageSmall);
        }

        // Adds listener to Take Another Picture button.
        Button goBackButton = (Button)findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

