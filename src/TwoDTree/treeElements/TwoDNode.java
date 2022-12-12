package TwoDTree.treeElements;

/**
 * @author Daniel Gaitan
 * @version 1.0
 * @since 1.0
 * <p>
 * KD Tree Node class, serves as a container for the node location and holds pointers for the parent and children
 * nodes.
 */

public class TwoDNode {

    /**
     * The current level of the node within the tree.
     */
    public int level;

    /**
     * Point object holding the coordinates of the node.
     */
    public Point coordinates;
    /**
     * Parent of the current node.
     */
    public TwoDNode parentKDTreeNode;

    /**
     * Left child of the current node. Whenever the current level is odd, the subdivision resulting from the left
     * node will be going down in the plane.
     */
    public TwoDNode leftChild;

    /**
     * Right child of the current node. Whenever the current level is odd, the subdivision resulting from the right
     * node will be going up in the plane.
     */
    public TwoDNode rightChild;

    /**
     * Int array holding the coordinates of the corners of the left child sub-canvas.
     */
    public int[] childCanvasLeft = new int[4];

    /**
     * Int array holding the coordinates of the corners of the right child sub-canvas.
     */
    public int[] childCanvasRight = new int[4];

    /**
     * Int array holding the coordinates of the corners for the canvas of the current node.
     */
    public int[] Canvas;

    /**
     * Initializer for the Node class
     *
     * @param value  point object with the coordinates of the node.
     * @param lvl    level of the node within the tree.
     * @param canvas corners of the canvas for the current node.
     */
    public TwoDNode(Point value, int lvl, int[] canvas) {
        this.coordinates = value;
        parentKDTreeNode = null;
        leftChild = null;
        rightChild = null;
        level = lvl;
        Canvas = canvas;
        generateCanvases();
    }

    /**
     * Helper function, uses the coordinates of the node and the canvas to subdivide itself for the children nodes.
     * If the level of the node is even, the child nodes will be subdivided based on the x coordinate of the current
     * node. Otherwise, the subdivision will be based on the y coordinate of the current node.
     */
    public void generateCanvases() {
        if (level % 2 == 0) {
            childCanvasLeft[0] = Canvas[0];
            childCanvasLeft[1] = Canvas[1];
            childCanvasLeft[2] = getX();
            childCanvasLeft[3] = Canvas[3];

            childCanvasRight[0] = getX();
            childCanvasRight[1] = Canvas[1];
            childCanvasRight[2] = Canvas[2];
            childCanvasRight[3] = Canvas[3];
        } else {
            childCanvasLeft[0] = Canvas[0];
            childCanvasLeft[1] = getY();
            childCanvasLeft[2] = Canvas[2];
            childCanvasLeft[3] = Canvas[3];

            childCanvasRight[0] = Canvas[0];
            childCanvasRight[1] = Canvas[1];
            childCanvasRight[2] = Canvas[2];
            childCanvasRight[3] = getY();
        }
    }

    /**
     * Getter for the x coordinate of the node.
     *
     * @return x coordinate of the current node.
     */
    public int getX() {
        return coordinates.getX();
    }

    /**
     * Getter for the y coordinate of the node.
     *
     * @return y coordinate of the current node.
     */
    public int getY() {
        return coordinates.getY();
    }

    public int getLevel() {
        return level;
    }
}