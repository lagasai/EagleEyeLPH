package edu.erau.eagleeye;

import android.net.Uri;

/**
 * ReferenceImageDatabase is the database that organizes the reference images used for comparison.
 * @author James Combs
 * @version 1.0
 */
public class ReferenceImageDatabase {

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
}
