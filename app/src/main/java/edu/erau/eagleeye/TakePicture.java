package edu.erau.eagleeye;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TakePicture extends AppCompatActivity {

    private final static int PICTURE_TAKEN = 1;
    public Uri photoUri;
    public Bitmap photoBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
    }

    // App Content

    public void onTakePicturePressed(View v) {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PICTURE_TAKEN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICTURE_TAKEN) {

            photoUri = data.getData();
            photoBitmap = (Bitmap)data.getExtras().get("data");

            if (BlackBox.isMatch(photoUri, photoUri)) {
                Intent changeToFound = new Intent(this, FoundBuilding.class);
                startActivity(changeToFound);
            } else {
                Intent changeToNotFound = new Intent(TakePicture.this, NoMatch.class);
                TakePicture.this.startActivity(changeToNotFound);
            }
        }

    }

}
