package edu.erau.eagleeye;

import android.app.Activity;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike R on 11/16/2015.
 */

public class TestingCV{


    private static String[] referenceImages = new String[8];

    private static void populateReferenceImagesArray(){
        referenceImages[0] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageOne.jpg";
        referenceImages[1] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageTwo.jpg";
        referenceImages[2] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageThree.jpg";
        referenceImages[3] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageOne.jpg";
        referenceImages[4] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageFive.jpg";
        referenceImages[5] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageSix.jpg";
        referenceImages[6] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageSeven.jpg";
        referenceImages[7] = "C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageEight.jpg";
    }

    public static void main(String[] args){

        double matchPercent=40;
        int a=0;

        //populate reference image array
        populateReferenceImagesArray();

        MatOfKeyPoint k1 = new MatOfKeyPoint();
        Mat d1 = new Mat();
        FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        MatOfDMatch matches = new MatOfDMatch();

        //import image
        Mat img1= Imgcodecs.imread("C:/Users/waelchlr/workspace/sandbox/src/TestPictures/TestImageFour.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        //Detect the key features of the image
        detector.detect(img1, k1);

        //Extract descriptors from the image
        extractor.compute(img1, k1, d1);

        //Begin loop for matching
        while (matchPercent<=100){

            //initialize counter
            int b=0;

            //load reference image
            Mat img2=Imgcodecs.imread(referenceImages[a], Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

            //Initialize key features matrix
            MatOfKeyPoint k2 = new MatOfKeyPoint();

            //Initialize descriptor matrix
            Mat d2 = new Mat();

            //Detect the key features of the reference images
            detector.detect(img2, k2);

            //Extract the descriptors from the image
            extractor.compute(img2, k2, d2);

            //Match points of two images
            matcher.match(d1,d2,matches);

            List<DMatch> matchesDMatch = matches.toList();
            ArrayList<Float> goodMatches = new ArrayList<>();

            while (b<matches.height()){

                int c=Math.round(matchesDMatch.get(b).distance);
                if (c<55){
                    goodMatches.add(matchesDMatch.get(b).distance);
                }

                b=b+1;
            }

            System.out.println(goodMatches.size());

            //Advance through the string array
            a=a+1;
            matchPercent=goodMatches.size();
        }

        System.out.println("\nBest Match is:  "+referenceImages[a-1]);
    }

}
