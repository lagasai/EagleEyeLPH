package edu.erau.eagleeye;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * The TakePicture activity is the activity that is displayed when the app launches. It allows the user
 * to capture a picture using the device's default camera app.
 *
 * @author Ian LaGasa
 */
public class TakePicture extends AppCompatActivity {

    private final static int PICTURE_TAKEN = 1;
    private Button takePictureButton;
    public Uri photoUri;
    public Bitmap photoBitmap;

    /**
     * Initializes the activity.
     * @param savedInstanceState: Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        // Adds listener to Take Picture button.
        takePictureButton = (Button)findViewById(R.id.takePicture);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTakePicturePressed();
            }
        });

        // Adds camera bypass
        ImageView clickPic = (ImageView)findViewById(R.id.eagleEyeLogo);
        clickPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraBypass();
            }
        });
    }

    // App Content

    /**
     * This method initiates the camera intent.
     */
    public void onTakePicturePressed() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PICTURE_TAKEN);

    }

    /**
     * This will handle the result of the camera intent. It saves the URI of the image and a bitmap
     * thumbnail to this class' attributes, and it tells the BlackBox to compare the images.
     * @param requestCode: int
     * @param resultCode: int
     * @param data: Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // Checks that the activity result is the camera intent
        if (requestCode == PICTURE_TAKEN && resultCode != RESULT_CANCELED) {

            photoUri = data.getData();
            photoBitmap = (Bitmap)data.getExtras().get("data");
            android.widget.Toast.makeText(this, photoUri.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private void cameraBypass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select photo:")
                .setItems(new CharSequence[]{"Lehman Building", "Student Center"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.widget.Toast.makeText(TakePicture.this, Integer.toString(which), Toast.LENGTH_LONG).show();
                    }
                });
    }

}
