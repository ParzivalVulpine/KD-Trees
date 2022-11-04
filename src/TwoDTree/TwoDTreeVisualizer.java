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
 *
 * First sketch, it visualizes a two-dimensional tree in fullscreen. The root node of the tree will follow the mouse and
 * the sketch subdivisions will react accordingly.
 */

public class TwoDTreeVisualizer extends PApplet {

    /**
     * List holding the points to be used for the 2D tree.
     */
    List<Point> treePoints = new ArrayList<>();

    //Setup variables

    /**
     * Frame rate parameter.
     */
    int frameRate = 30;
    /**
     * Number of points to be generated for visualizing.
     */
    int numberOfPoints = 100;

    /**
     * Limit of where the points can position from the bounds of the screen.
     */
    int drawingBoundOffset = 100;

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
     * Moves the root of the KD Tree to the mouse position.
     */
    public void moveNodeToMouse(TwoDNode node) {
        node.coordinates.setX(mouseX);
        node.coordinates.setY(mouseY);
    }

    /**
     * Settings holds how the program will display.
     */
    public void settings() {
        fullScreen(P2D,SPAN);
    }

    /**
     * Defines the frame rate and generate the points in random locations inside the screen.
     */
    public void setup() {
        frameRate(frameRate);

        for (int i = 0; i < numberOfPoints; i++) {
            treePoints.add(new Point((int) random(drawingBoundOffset, width-drawingBoundOffset),
                                     (int) random(drawingBoundOffset, height-drawingBoundOffset)));
        }


    }

    /**
     * For every frame draw() will recreate the tree so it as the user moves the root point, the rest of the nodes will be
     * redrawn accordingly.
     */
    public void draw() {
        int[] canvas = {0, 0, width, height};
        strokeWeight(2);
        background(255);

        TwoDTree test = new TwoDTree();
        for (Point tp : treePoints) {
            test.insert(tp, canvas);
        }

        moveNodeToMouse(test.root);
        drawTree(test.root);
    }

    public static void main(String... args) {
        PApplet.main("TwoDTree.TwoDTreeVisualizer");
    }
}