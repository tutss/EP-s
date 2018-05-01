/* Made by: tuts at 26/04/18
 *  Project: ep4work
 *  Dependencies:
 */

import edu.princeton.cs.algs4.Point2D;

public class KdTreeST{

    private Node root;
    private int n;

    private static class Node {

        private Point2D p;
        private RectHV  rect;
        private Node    lf;
        private Node    rt;

        public Node(Point2D p, RectHV rect) {
            RectHV r = rect;
            if (r == null)
                r = new RectHV(0, 0, 1, 1);
            this.rect   = r;
            this.p      = p;
        }
    }

    // Public methods
    public KdTreeST(){
        n = 0;
        root = null;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Point2D p) {
        return contains(root, p, true);
    }

    // Private methods
    private boolean contains(Node node, Point2D p, boolean b) {
        if (node == null) return false;
        if (node.p.equals(p)) return true;

        int compare;
        if (b) {
            compare = Point2D.X_ORDER.compare(node.p,p);
        }
        else {
            compare = Point2D.Y_ORDER.compare(node.p,p);
        }

        if (compare < 0) {
            return contains(node.rt, p, !b);
        }
        else {
            return contains(node.lf, p, !b);
        }
    }

    // Unit testing
    public static void main(String[]args){

    }
}