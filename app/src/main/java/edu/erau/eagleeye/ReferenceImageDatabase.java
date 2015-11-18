package edu.erau.eagleeye;

import android.net.Uri;

/**
 * ReferenceImageDatabase is the database that organizes the reference images used for comparison.
 * @author James Combs
 * @version 1.0
 */
public class ReferenceImageDatabase {

    //declare the reference image array
    public Integer[][] referenceImages=new Integer[8][2];

    //Class attributes
    //INode root: First INode in the queue
    public INode root;
    //INode last: Last INode in the queue
    public INode last;

    //Simple constructor for the Linked List
    //Creates a Linked List with a single INode
    //@@Arg INode r: First INode of the Linked List
    public void RefrenceImageDatabase(INode r){
        //Sets root to r
        root = r;
        //Sets last to r
        //r is currently only node in Linked List
        last = r;
    }

    //Adds an INode to the end of the Linked List
    //@@Arg INode n: INode to be added
    public void addINode(INode n){
        //Links the current end of the list to n
        last.setNext(n);
        //Sets the end of the list to n
        last = n;
    }

    public Uri passImage(INode p){
        return p.getImageUri();
    }

    public void passDatabase(INode r, Uri[] table){
        //NEED TO COMPLETE passImage(INode p) TO
        //COMPLETE METHOD
        if(r.getNext() != null){
            passDatabase(r.getNext(), table);
        }
        passImage(r);
    }
    public void removeNextImage(){

    }

    public void populate(){

        //populate the reference image array
        this.referenceImages[0][0] = R.raw.testimageone;
        this.referenceImages[0][1] = 100;
        this.referenceImages[1][0] = R.raw.testimagetwo;
        this.referenceImages[1][1] = 200;
        this.referenceImages[2][0] = R.raw.testimagethree;
        this.referenceImages[2][1] = 300;
        this.referenceImages[3][0] = R.raw.testimageone;
        this.referenceImages[3][1] = 300;
        this.referenceImages[4][0] = R.raw.testimagefive;
        this.referenceImages[4][1] = 300;
        this.referenceImages[5][0] = R.raw.testimagesix;
        this.referenceImages[5][1] = 300;
        this.referenceImages[6][0] = R.raw.testimageseven;
        this.referenceImages[6][1] = 400;
        this.referenceImages[7][0] = R.raw.testimageeight;
        this.referenceImages[7][1] = 500;
    }

    public String askMeANumberAndIllGiveYouAString(Integer i){
        String buildingName;

        switch (i){

            case 100: buildingName="Chrysler Building";
                break;
            case 200: buildingName="Sears Tower";
                break;
            case 300: buildingName="Lehman Building";
                break;
            case 400: buildingName="Welcome Center";
                break;
            case 500: buildingName="College of Arts and Sciences";
                break;
            default: buildingName="Building Not Found";
                break;
        }

        return buildingName;
    }
}
