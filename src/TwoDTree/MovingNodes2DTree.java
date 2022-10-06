package TwoDTree;

import TwoDTree.treeElements.Point;
import TwoDTree.treeElements.TwoDNode;
import TwoDTree.treeElements.TwoDTree;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Gaitan
 * @version 1.1
 * @since 1.0
 * <p>
 * Second sketch, it visualizes a two-dimensional tree in fullscreen. The all the nodes will move around with the use
 * of a 'vector'.
 */

public class MovingNodes2DTree extends PApplet {

    /**
     * List holding the points to be used for the 2D tree.
     */
    List<Point> treePoints = new ArrayList<>();

    /**
     * List holding the movement vectors for each point.
     */
    List<int[]> vectors = new ArrayList<>();

    //Setup variables
    /**
     * Frame rate parameter.
     */
    int frameRate = 30;
    /**
     * Number of points to be generated for visualizing.
     */
    int numberOfPoints = 10;
    /**
     * Limit of where the points can position from the bounds of the screen.
     */
    int drawingBoundOffset = 100;
    /**
     * Defines how big can a movement vector be, the higher the number the faster some points can move.
     */
    int vectorRandomFactor = 5;

    /**
     * Draws the KD tree by doing an inorder traversal of the tree and using the subdivided canvas of each node to
     * draw a line splitting a section of the plane in half. For the best results always pass the root of the tree
     * as a parameter.
     *
     * @param node tree node to use as the starting point for the traversal and drawing of the tree.
     */
    public void drawTree(TwoDNode node) {
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
     * Settings holds how the program will display.
     */
    public void settings() {
        fullScreen(P2D, SPAN);
    }

    /**
     * Defines the frame rate and generate the points in random locations inside the screen and the movement vector for
     * each point.
     */
    public void setup() {
        frameRate(frameRate);

        for (int i = 0; i < numberOfPoints; i++) {
            treePoints.add(new Point((int) random(drawingBoundOffset, width - drawingBoundOffset),
                    (int) random(drawingBoundOffset, height - drawingBoundOffset)));

            int[] v = {(int) random(-vectorRandomFactor, vectorRandomFactor), (int) random(-vectorRandomFactor, vectorRandomFactor)};
            vectors.add(v);
        }
    }

    /**
     * For every frame draw() will change the coordinates of each point in the list based on its respective movement
     * vector and will regenerate the tree to draw it.
     */
    public void draw() {
        int[] canvas = {0, 0, width, height};
        strokeWeight(2);
        background(255);

        TwoDTree test = new TwoDTree();
        int counter = 0;
        for (Point tp : treePoints) {
            int newX = tp.getX() + vectors.get(counter)[0];
            int newY = tp.getY() + vectors.get(counter)[1];
            tp.setX(newX);
            tp.setY(newY);
            test.insert(tp, canvas);
            counter += 1;
        }
        drawTree(test.root);
    }

    public static void main(String... args) {
        PApplet.main("TwoDTree.MovingNodes2DTree");
    }
}