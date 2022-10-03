import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Gaitan
 * @version 1.0
 * @since 1.0
 * <p>
 * Main class for the KD Tree, it implements a 2-Dimensional Tree using 3 classes. Points, KDTreeNodes and a KD Tree.
 * Using the Processing library it then visualizes it as a subdividing tree.
 */

public class kdTree extends PApplet {

    /**
     * treePoints is a list containing the Points for the 2D tree to store, it is stored as a global variable.
     */
    List<Point> treePoints = new ArrayList<>();


    /**
     * @author Daniel Gaitan
     * @version 1.0
     * @since 1.0
     * <p>
     * Point class, it defines the x and y coordinates of a node within the tree.
     */
    static class Point {

        /**
         * Horizontal coordinate of a Point
         */
        public int coordinateX;

        /**
         * Vertical coordinate of a Point
         */
        public int coordinateY;

        /**
         * Getter for x coordinate
         *
         * @return The x value of the Point
         */
        public int getX() {
            return coordinateX;
        }

        /**
         * Setter for X coordinate
         *
         * @param coordinateX new integer for the x coordinate
         */
        public void setX(int coordinateX) {
            this.coordinateX = coordinateX;
        }

        /**
         * Getter for y coordinate
         *
         * @return The y value of the Point
         */
        public int getY() {
            return coordinateY;
        }

        /**
         * Setter for X coordinate
         *
         * @param coordinateY new integer for the y coordinate
         */
        public void setY(int coordinateY) {
            this.coordinateY = coordinateY;
        }

        /**
         * Initializer for the Point class
         *
         * @param x horizontal position of the Point
         * @param y vertical position of the Point
         */
        public Point(int x, int y) {
            coordinateX = x;
            coordinateY = y;
        }
    }

    /**
     * @author Daniel Gaitan
     * @version 1.0
     * @since 1.0
     * <p>
     * KD Tree Node class, serves as a container for the node location and holds pointers for the parent and children
     * nodes.
     */
    static class KDTreeNode {

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
        public KDTreeNode parentKDTreeNode;

        /**
         * Left child of the current node. Whenever the current level is odd, the subdivision resulting from the left
         * node will be going down in the plane.
         */
        public KDTreeNode leftChild;

        /**
         * Right child of the current node. Whenever the current level is odd, the subdivision resulting from the right
         * node will be going up in the plane.
         */
        public KDTreeNode rightChild;

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
        public KDTreeNode(Point value, int lvl, int[] canvas) {
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
    }

    /**
     * @author Daniel Gaitan
     * @version 1.0
     * @since 1.0
     * <p>
     * KD Tree class, it holds the root of the tree and has the methods for inserting nodes, drawing the tree and
     * experimental drawing algorithms.
     */
    class KDTree {

        /**
         * Root node of the tree.
         */
        public KDTreeNode root;

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
        private KDTreeNode addRecursive(KDTreeNode current, Point value, int level, int[] canvas) {
            //nothing set
            if (current == null) {
                current = new KDTreeNode(value, level, canvas);
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

        /**
         * Draws the KD tree by doing an inorder traversal of the tree and using the subdivided canvas of each node to
         * draw a line splitting a section of the plane in half. For the best results always pass the root of the tree
         * as a parameter.
         *
         * @param node tree node to use as the starting point for the traversal and drawing of the tree.
         */
        public void drawTree(KDTreeNode node) {
            if (node != null) {
                drawTree(node.leftChild);
                if (node.level % 2 == 0) {
                    line(node.getX(), node.Canvas[1], node.getX(), node.Canvas[3]);
                    circle(node.getX(), node.getY(), 5);
                } else {
                    line(node.Canvas[0], node.getY(), node.Canvas[2], node.getY());
                    circle(node.getX(), node.getY(), 5);
                }
                drawTree(node.rightChild);
            }
        }

        /**
         * Moves the root of the KD Tree to the mouse position.
         */
        public void moveRoot() {
            root.coordinates.setX(mouseX);
            root.coordinates.setY(mouseY);
        }

    }

    public void settings() {
        size(1024, 1024);
        smooth(5);
    }

    public void setup() {
        frameRate(30);
        for (int i = 0; i < 50; i++) {
            treePoints.add(new Point((int) random(100, 924), (int) random(100, 924)));
        }

    }

    public void draw() {
        int[] canvas = {0, 0, width, height};

        strokeWeight(5);
        background(255);

        KDTree test = new KDTree();
        for (Point tp : treePoints) {
            test.insert(tp, canvas);
        }
        test.drawTree(test.root);
        test.moveRoot();
    }


    public static void main(String... args) {
        PApplet.main("kdTree");
    }
}