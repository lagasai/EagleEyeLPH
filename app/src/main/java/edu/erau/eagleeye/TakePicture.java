package edu.erau.eagleeye;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class TakePicture extends AppCompatActivity {

    private final static int PICTURE_TAKEN = 1;
    public Uri photoUri;
    private File photoFileDir;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        photoFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "eagleEye");
        photoFileDir.mkdirs();
    }

    // App Content

    public void onTakePicturePressed(View v) {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = new File(photoFileDir, "eagleEyePhoto.jpg");
        photoUri = Uri.fromFile(photoFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(cameraIntent, PICTURE_TAKEN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICTURE_TAKEN && photoFile.exists()) {

        }

    }

}
