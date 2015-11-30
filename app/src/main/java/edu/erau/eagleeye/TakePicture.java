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

/**
 * This activity is responsible for calling the camera intent in the Android operating system. It allows
 * the user to capture an image, and then calls upon the Comparator class object for analysis. It also
 * for testing by passing a pre-loaded image to the Comparator class.
 *
 * @author Los Pollos Hermanos
 * @version S2
 */
public class TakePicture extends AppCompatActivity {

    // Required to use openCV Library.
    static {System.loadLibrary("opencv_java3");}

    private final static int PICTURE_TAKEN = 100;
    private Uri photoUri;
    private int drawableID;
    private Comparator c=new Comparator();

    /**
     * This class is responsible for saving the previous activities state and loading the TakePicture
     * activity for display to the user. It sets the GUI to the correct XML file, and adds button
     * listeners for the takePictureButton, as well as a button listener for the application logo
     * (this listener is used for testing purposes, as it will send a stock image to the comparator
     * object instead of using the system camera for image capture), which prompts the user with a
     * drop down menu for a reference image to be selected for comparison to the reference image database.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        // Adds listener to Take Picture button.
        Button takePictureButton = (Button)findViewById(R.id.takePicture);
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
     * This method handles the result of the android operating system camera intent. It saves the Uri
     * of the image that was taken, along with a thumbnail version of the image in bitmap format. It
     * then will call the comparator object for image comparison with the reference database.
     * @param requestCode Request code of calling intent
     * @param resultCode Result code of calling intent
     * @param data The Intent object containing all returned data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // Checks that the activity result is the camera intent
        if (requestCode == PICTURE_TAKEN && resultCode != RESULT_CANCELED) {

            photoUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                c.attemptMatch(this, photoUri, bitmap);
            } catch (Exception e) {

            }
        }

    }

    /**
     * This method defines the content and functionality of the drop down menu that is displayed as a
     * result on activating the app logo. It provides options for selecting stock images of various
     * buildings to be sent to the bypassMethod.
     */
    private void cameraBypass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select photo:")
                .setItems(new CharSequence[]{"Lehman Building", "Student Center"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TakePicture.this.bypassMethod(which);
                    }
                });
        builder.create().show();
    }

    /**
     * This method handles sending the stock reference image to the comparator object for comparison
     * to the reference database.
     * @param i Index of selected building
     */
    private void bypassMethod(int i) {
        switch(i) {
            case 0: drawableID = R.drawable.bypass_lb; break;
            case 1: drawableID = R.drawable.bypass_sc; break;
        }

        c.attemptMatch(this, drawableID);

    }

}