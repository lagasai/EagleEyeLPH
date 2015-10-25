package edu.erau.eagleeye;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class TakePicture extends AppCompatActivity {

    public Uri photoUri;
    private File photoFileDir = new File("/sdcard/eagle_eye");
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        if(!photoFileDir.mkdirs()) {
            Log.d("TakePicture", "Did not make.");
        }
    }

    // App Content

    public void onTakePicturePressed(View v) {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = new File(photoFileDir, "eagle_eye_photo.jpg");
        photoUri = Uri.fromFile(photoFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(cameraIntent, 1);

    }

}
