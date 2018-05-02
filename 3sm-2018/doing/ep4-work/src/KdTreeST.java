/* Made by: tuts at 26/04/18
 *  Project: ep4work
 *  Dependencies:
 */

import edu.princeton.cs.algs4.*;

public class KdTreeST{

    private Node root;
    private int n;

    private static class Node {

        private Point2D p;
        private RectHV  rect;
        private Value val;
        private Node    lf;
        private Node    rt;
        private int level;

        public Node(Point2D p, RectHV rect) {
            RectHV r = rect;
            if (r == null)
                r = new RectHV(0, 0, 1, 1);
            this.rect   = r;
            this.p      = p;
            this.level = 0;
        }

        public int compare(Point2D point) {
            if (level % 2 == 0) {
                if (point.x() < p.x())
                    return 1;
                else if (point.x() > p.x())
                    return -1;
                else
                    return 0;
            } else {
                if (point.y() < p.y())
                    return 1;
                else if (point.y() > p.y())
                    return -1;
                else
                    return 0;
            }
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
        return get(p) != null;
    }

    public void put(Point2D p, Value val) {
        put(p, val, root);
    }
    private void put(Point2D p, Value val, Node node) {
        if (node == null) ret
    }
    public Value get(Point2D p) {
        return get(p, root, 0);
    }

    private Value get(Point2D p, Node node, int height) {
        if (node == null) return null;
        int comp = node.compare(p);
        if (comp < 0) return get(p, node.lf, node.level + 1);
        else if (comp > 0) return get(p, node.rt, node.level + 1);
        else return node.val;
    }
    public Iterable<Point2D> points() {
        return tree.keys();
    }
    public Iterable<Point2D> range(RectHV rect){

    }
    public Iterable<Point2D> nearest(Point2D p, int k){ }

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