package TwoDTree.treeElements;

import processing.core.PApplet;

/**
 * @author Daniel Gaitan
 * @version 1.1
 * @since 1.0
 * <p>
 * KD Tree class, it holds the root of the tree and has the methods for inserting nodes, drawing the tree and
 * experimental drawing algorithms.
 */
public class TwoDTree extends PApplet {

    /**
     * Root node of the tree.
     */
    public TwoDNode root;

    /**
     * Public insert function for the KD Tree, it generates the nodes for the tree using a Point instance and the
     * current canvas space for the subdivision.
     *
     * @param coordinates Point object with the coordinates of the node.
     * @param canvas      Int array holding the points for the corners of the node's canvas.
     */
    public void insert(Point coordinates, int[] canvas) {
        root = addRecursive(root, coordinates, 0, canvas);
    }

    /**
     * Recursive function, it goes down the KD tree and checks for empty children nodes to create a new node in that
     * spot.
     * <p>
     * Depending on the current level of the tree the node is, it will decide when to compare the x or y value of
     * the node with the parent.
     *
     * @param current current node inside the traversal of the tree.
     * @param value   Point coordinates of the new node.
     * @param level   current level of the node.
     * @param canvas  canvas coordinates for the new node.
     * @return returns the current node.
     */
    private TwoDNode addRecursive(TwoDNode current, Point value, int level, int[] canvas) {
        //nothing set
        if (current == null) {
            current = new TwoDNode(value, level, canvas);
            return current;
        }
        if (level % 2 == 0) {
            //1 check value compared to value of current node value
            if (value.getX() < current.getX()) {
                //it goes to the left node
                current.leftChild = addRecursive(current.leftChild, value, level + 1, current.childCanvasLeft);
            } else {
                //it goes to the right node
                current.rightChild = addRecursive(current.rightChild, value, level + 1, current.childCanvasRight);
            }
        } else {
            if (value.getY() > current.getY()) {
                //it goes to the left node
                current.leftChild = addRecursive(current.leftChild, value, level + 1, current.childCanvasLeft);
            } else {
                //it goes to the right node
                current.rightChild = addRecursive(current.rightChild, value, level + 1, current.childCanvasRight);
            }
        }
        return current;
    }

}