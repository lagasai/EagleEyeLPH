package edu.erau.eagleeye;

import android.net.Uri;

/**
 * ReferenceImageDatabase is the database that organizes the reference images used for comparison.
 * @author James Combs
 * @version 1.0
 */
public class ReferenceImageDatabase {

    //declare the reference image array
    public Integer[][] referenceImages=new Integer[30][2];

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
        this.referenceImages[0][0] = R.raw.lb01;
        this.referenceImages[0][1] = 300;
        this.referenceImages[1][0] = R.raw.lb02;
        this.referenceImages[1][1] = 300;
        this.referenceImages[2][0] = R.raw.lb03;
        this.referenceImages[2][1] = 300;
        this.referenceImages[3][0] = R.raw.lb04;
        this.referenceImages[3][1] = 300;
        this.referenceImages[4][0] = R.raw.lb05;
        this.referenceImages[4][1] = 300;
        this.referenceImages[5][0] = R.raw.lb06;
        this.referenceImages[5][1] = 300;
        this.referenceImages[6][0] = R.raw.lb07;
        this.referenceImages[6][1] = 300;
        this.referenceImages[7][0] = R.raw.lb08;
        this.referenceImages[7][1] = 300;
        this.referenceImages[8][0] = R.raw.lb09;
        this.referenceImages[8][1] = 300;
        this.referenceImages[9][0] = R.raw.lb10;
        this.referenceImages[9][1] = 300;
        this.referenceImages[10][0] = R.raw.lb11;
        this.referenceImages[10][1] = 300;
        this.referenceImages[11][0] = R.raw.lb12;
        this.referenceImages[11][1] = 300;
        this.referenceImages[12][0] = R.raw.lb13;
        this.referenceImages[12][1] = 300;
        this.referenceImages[13][0] = R.raw.lb14;
        this.referenceImages[13][1] = 300;
        this.referenceImages[14][0] = R.raw.lb15;
        this.referenceImages[14][1] = 300;
        this.referenceImages[15][0] = R.raw.lb16;
        this.referenceImages[15][1] = 300;
        this.referenceImages[16][0] = R.raw.lb17;
        this.referenceImages[16][1] = 300;
        this.referenceImages[17][0] = R.raw.lb18;
        this.referenceImages[17][1] = 300;
        this.referenceImages[18][0] = R.raw.lb19;
        this.referenceImages[18][1] = 300;
        this.referenceImages[19][0] = R.raw.lb20;
        this.referenceImages[19][1] = 300;
        this.referenceImages[20][0] = R.raw.lb21;
        this.referenceImages[20][1] = 300;
        this.referenceImages[21][0] = R.raw.lb22;
        this.referenceImages[21][1] = 300;
        this.referenceImages[22][0] = R.raw.lb23;
        this.referenceImages[22][1] = 300;
        this.referenceImages[23][0] = R.raw.lb24;
        this.referenceImages[23][1] = 300;
        this.referenceImages[24][0] = R.raw.lb25;
        this.referenceImages[24][1] = 300;
        this.referenceImages[25][0] = R.raw.lb26;
        this.referenceImages[25][1] = 300;
        this.referenceImages[26][0] = R.raw.lb27;
        this.referenceImages[26][1] = 300;
        this.referenceImages[27][0] = R.raw.lb28;
        this.referenceImages[27][1] = 300;
        this.referenceImages[28][0] = R.raw.lb29;
        this.referenceImages[28][1] = 300;
        this.referenceImages[29][0] = R.raw.lb30;
        this.referenceImages[29][1] = 300;
    }

    public String askMeANumberAndIllGiveYouAString(Integer i){
        String buildingName;

        switch (i){

            case 100: buildingName="Student Center";
                break;
            case 200: buildingName="I.C. Center";
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
