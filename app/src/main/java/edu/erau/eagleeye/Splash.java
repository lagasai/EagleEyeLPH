package edu.erau.eagleeye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * This activity is designed to display the Eagle Eye app logo for a short period of time before the app
 * becomes usable.
 *
 * @author Los Pollos Hermanos
 * @version S2
 */
public class Splash extends AppCompatActivity {

    /**
     * This class is responsible for saving the previous activities state and loading the Splash activity
     * for display to the user. It sets the GUI to the correct XML file, and the ImageView to display
     * the application logo. It also sets the correct XML file for its “fade out” function. This activity
     * is displayed briefly before loading the TakePicture activity.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Create objects for image and animation
        final ImageView splashImage = (ImageView) findViewById(R.id.splashImage);
        final Animation fadeOutAnim = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);

        //Start animation for the image
        splashImage.startAnimation(fadeOutAnim);

        //Listener for animation that starts the Take Picture activity when animation complete
        fadeOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //Not used
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Start Take Picture activity
                Intent startApp = new Intent(Splash.this, TakePicture.class);
                startActivity(startApp);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //Not used
            }
        });
    }
}
