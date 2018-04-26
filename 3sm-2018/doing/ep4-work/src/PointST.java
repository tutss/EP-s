/* Made by: tuts at 26/04/18
 *  Project: ep4work
 *  Dependencies: Point2D.java
 */


import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Point2D;

public class PointST<Value>{
    // Instance variables
    private RedBlackBST<Point2D, Value> tree;
    // Public methods
    public PointST(){
        tree = new RedBlackBST<Point2D, Value>();

    }

    public boolean isEmpty() {

    }

    public int size() {

    }
    public void put(Point2D p, Value val) {
        if (p == null || val == null) throw new IllegalArgumentException();

    }
    public Value get(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

    }
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

    }
    public Iterable<Point2D> points() {

    }
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new IllegalArgumentException();

    }
    public Iterable<Point2D> nearest(Point2D p, int k){
        if (p == null) throw new IllegalArgumentException();

    }
    // Private methods


    // Unit testing
    public static void main(String[]args){

    }
}