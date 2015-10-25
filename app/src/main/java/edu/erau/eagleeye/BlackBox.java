package edu.erau.eagleeye;

import android.net.Uri;
import java.util.Random;

/**
 * BlackBox class is a simulation of the 3rd party, pattern matching software
 * this is designed to only be temporary until we can find/license 3rd party software
 *
 * Created by Ian on 10/21/2015
 * Modified by Robert Waelchli on multiple dates
 *
 * @author Robert Waelchli
 * @see BlackBox
 */
public class BlackBox {

    //this is the public constructor for BlackBox class
    public BlackBox(){
    }

    //this is the method designed to take in a Uri object and spit out a boolean match or miss
    public static boolean isMatch(Uri queryImage, Uri referenceImage){

        //declare boolean variable
        boolean matchTrueFalse;

        //instantiate new Random object
        Random decider = new Random();

        //assign boolean variable true or false randomly
        matchTrueFalse=decider.nextBoolean();

        //return true or false value
        return matchTrueFalse;
    }

}
