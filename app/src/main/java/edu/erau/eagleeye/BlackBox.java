package edu.erau.eagleeye;

import android.net.Uri;
import java.util.Random;

/**
 * Date: Oct 27-2015
 * BlackBox class is a simulation of the 3rd party, pattern matching software that we hope to
 * purchase or find for later integration.  This is meant to be only a temporary component of
 * the EagleEye program.
 * @author Robert Waelchli
 * @version 1.0
 * @see BlackBox
 */
public class BlackBox {

    /**
     * this global variable enables isMatch to return matches only every second time its called
     */
    private static int methodCounter=1;

    /**
     * this is the public constructor for BlackBox class
     */
    public BlackBox(){
    }

     /**
     * this is the method designed to take in a Uri object and spit out a boolean match or miss
     * @param queryImage This Uri represents the just taken picture
     * @param referenceImage This Uri represents the reference image used for comparison
     * @return returns the boolean value indicating whether a simulated match was achieved
     */
    public static boolean isMatch(Uri queryImage, Uri referenceImage){

        //declare boolean variable
        boolean matchTrueFalse;

        //instantiate new Random object
        //Random decider = new Random();

        //assign boolean variable true or false randomly
        //matchTrueFalse=decider.nextBoolean();

        //assign boolean variable true or false depending if methodCounter variable is odd or even
        if((methodCounter%2)==0){
            matchTrueFalse=true;  //return match on even attempts
        }
            else{
            matchTrueFalse=false; //return false on odd attempts
        }

        //update counter
        methodCounter=methodCounter+1;

        //return true or false value
        return matchTrueFalse;
    }

}