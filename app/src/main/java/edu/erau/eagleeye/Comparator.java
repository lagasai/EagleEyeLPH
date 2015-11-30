package edu.erau.eagleeye;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import org.opencv.android.Utils;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for catching a reference image from the TakePicture activity, then comparing that image
 * to various images saved in the ReferenceImageDatabase array of reference images. It utilizes the
 * OpenCV open-source library to perform the comparison between the taken images and the reference images.
 *
 * @author Los Pollos Hermanos
 * @version S2
 */

public class Comparator {

    /**
     * This method is used when the TakePicture method is used to send a preloaded reference image using
     * its testing function. This method runs through the database of images to find matches. It then
     * sets global variables to reflect the correct match or lack thereof for use by the FoundBuilding
     * or NoMatch activity.  It will then call the appropriate activity (either FoundBuilding or NoMatch).
     *
     * @param newContext Context of calling activity
     * @param rfileHandle The R.java file handle for a drawable resource
     */
    public void attemptMatch(Context newContext, Integer rfileHandle) {

        //declare match percent, counter, buildingName string;
        double matchPercent = 10;
        int a = 0;
        int stateID = 0;
        String buildingName;

        //a new instance of the reference database
        ReferenceImageDatabase nRFD = new ReferenceImageDatabase();
        nRFD.populate();

        //create the matrices required for the first query image and the comparison
        MatOfKeyPoint k1 = new MatOfKeyPoint();
        Mat d1 = new Mat();
        FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        MatOfDMatch matches = new MatOfDMatch();

        try {

            //load the sample image from the drawable directory
            Mat img1 = Utils.loadResource(newContext, rfileHandle, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

            //Detect the key features of the image
            detector.detect(img1, k1);

            //Extract descriptors from the image
            extractor.compute(img1, k1, d1);

            while (matchPercent < 100 && stateID!=100 && stateID!=50) {

                    //initialize counter
                    int b = 0;

                    //load reference image
                    Mat img2 = Utils.loadResource(newContext, nRFD.referenceImages[a][0], Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

                    //Initialize key features matrix
                    MatOfKeyPoint k2 = new MatOfKeyPoint();

                    //Initialize descriptor matrix
                    Mat d2 = new Mat();

                    //Detect the key features of the reference images
                    detector.detect(img2, k2);

                    //Extract the descriptors from the image
                    extractor.compute(img2, k2, d2);

                    //Match points of two images
                    matcher.match(d1, d2, matches);

                    List<DMatch> matchesDMatch = matches.toList();
                    ArrayList<Float> goodMatches = new ArrayList<>();

                    while (b < matches.height()) {

                        int c = Math.round(matchesDMatch.get(b).distance);
                        if (c < 55) {
                            goodMatches.add(matchesDMatch.get(b).distance);
                        }

                        b = b + 1;
                    }

                    //Advance through the reference photo array
                    a = a + 1;
                    matchPercent = goodMatches.size();

                    //break the while loop if a match is found or at the end of the reference array
                    if (a==30&&goodMatches.size()<=100){
                        stateID=100;
                    }
                    if (goodMatches.size()>100){
                        stateID=50;
                    }
            }

            if (stateID==100){
                NoMatch.testImage=true;
                NoMatch.queryImageSmall=null;
                NoMatch.testQueryImage=rfileHandle;
                Intent noMatchIntent = new Intent(newContext, NoMatch.class);
                newContext.startActivity(noMatchIntent);
            }else {
                buildingName = nRFD.askMeANumberAndIllGiveYouAString(nRFD.referenceImages[a][1]);
                FoundBuilding.BuildingName=(buildingName);
                FoundBuilding.BuildingRemark=("Home to Aerospace Engineering, Engineering Technology, Computing and Mathematics, Physical Sciences, Human Factors and the Center for Aviation and Aerospace Research");
                FoundBuilding.referencePic=R.drawable.lb;
                Intent matchIntent = new Intent(newContext, FoundBuilding.class);
                newContext.startActivity(matchIntent);
            }

        } catch (IOException e) {
            System.out.println("IOException Thrown and Caught:  " + e);
        }

    }

    /**
     * This method is used when the TakePicture method is used to send a user captured image using the
     * devices camera intent. This method runs through the database of images to find matches. It then
     * sets global variables to reflect the correct match or lack thereof for use by the FoundBuilding
     * or NoMatch activity.  It will then call the appropriate activity (either FoundBuilding or NoMatch).
     *
     * @param newContext Context of calling activity
     * @param queryPicture URI of captured photo file
     * @param queryImageSmall Bitmap sample of captured photo
     */
    public void attemptMatch(Context newContext, Uri queryPicture, Bitmap queryImageSmall) {

        //declare match percent, counter, buildingName string;
        double matchPercent = 10;
        int a = 0;
        int stateID = 0;
        String buildingName;

        //a new instance of the reference database
        ReferenceImageDatabase nRFD = new ReferenceImageDatabase();
        nRFD.populate();

        //create the matrices required for the first query image and the comparison
        MatOfKeyPoint k1 = new MatOfKeyPoint();
        Mat d1 = new Mat();
        FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);
        MatOfDMatch matches = new MatOfDMatch();

        try {

            //load the sample image from the drawable directory
            Mat img1 = new Mat();
            Utils.bitmapToMat(queryImageSmall, img1);

            //Detect the key features of the image
            detector.detect(img1, k1);

            //Extract descriptors from the image
            extractor.compute(img1, k1, d1);

            while (matchPercent < 100 && stateID!=100 && stateID!=50) {

                //initialize counter
                int b = 0;

                //load reference image
                Mat img2 = Utils.loadResource(newContext, nRFD.referenceImages[a][0], Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

                //Initialize key features matrix
                MatOfKeyPoint k2 = new MatOfKeyPoint();

                //Initialize descriptor matrix
                Mat d2 = new Mat();

                //Detect the key features of the reference images
                detector.detect(img2, k2);

                //Extract the descriptors from the image
                extractor.compute(img2, k2, d2);

                //Match points of two images
                matcher.match(d1, d2, matches);

                List<DMatch> matchesDMatch = matches.toList();
                ArrayList<Float> goodMatches = new ArrayList<>();

                while (b < matches.height()) {

                    int c = Math.round(matchesDMatch.get(b).distance);
                    if (c < 55) {
                        goodMatches.add(matchesDMatch.get(b).distance);
                    }

                    b = b + 1;
                }

                //Advance through the reference photo array
                a = a + 1;
                matchPercent = goodMatches.size();

                System.out.println(matchPercent);

                //break the while loop if a match is found or at the end of the reference array
                if (a==30&&goodMatches.size()<=100){
                    stateID=100;
                }
                if (goodMatches.size()>100){
                    stateID=50;
                }
            }

            if (stateID==100){
                Intent noMatchIntent = new Intent(newContext, NoMatch.class);
                NoMatch.testImage=false;
                NoMatch.queryImageSmall=queryImageSmall;
                NoMatch.testQueryImage=null;
                newContext.startActivity(noMatchIntent);
            }else {
                buildingName = nRFD.askMeANumberAndIllGiveYouAString(nRFD.referenceImages[a][1]);
                FoundBuilding.BuildingName=(buildingName);
                FoundBuilding.BuildingRemark=("Home to Aerospace Engineering, Engineering Technology, Computing and Mathematics, Physical Sciences, Human Factors and the Center for Aviation and Aerospace Research");
                FoundBuilding.referencePic=R.drawable.lb;
                Intent matchIntent = new Intent(newContext, FoundBuilding.class);
                newContext.startActivity(matchIntent);
            }

        } catch (IOException e) {
            System.out.println("IOException Thrown and Caught:  " + e);
        }

    }
}