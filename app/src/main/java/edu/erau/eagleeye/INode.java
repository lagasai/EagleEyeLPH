package edu.erau.eagleeye;

import android.net.Uri;

/**
 * Created by Mike R on 10/6/2015.
 * Edited by James Combs on 10/17/2015.
 */
public class INode {

    //Class attributes
    //Image image: Image held in node
    private Uri image;
    //String name: Name of building associated with image
    private String name;
    //INode next: Next INode in the linked list
    private INode next;

    /**
     * Basic Constructor
     * @param n Name of the building associated with image
     * @param i Image to be held in INode
     */
    public void INode(String n, Uri i){
        name = n;
        image = i;
    }

    /**
     * Provides a link to next INode in linked list
     * @param a INode to be linked to
     */
    public void setNext(INode a){
        next = a;
    }

    /**
     * Returns the image stored in INode
     * @return Returns the image
     */
    public Uri getImageUri(){
        return this.image;
    }

    /**
     * Returns the building name stored in INode
     * @return Returns the building name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the next INode
     * @return Returns the next INode
     */
    public INode getNext(){
        return this.next;
    }
}