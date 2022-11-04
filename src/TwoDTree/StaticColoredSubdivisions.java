package TwoDTree;

import TwoDTree.treeElements.Point;
import TwoDTree.treeElements.TwoDNode;
import TwoDTree.treeElements.TwoDTree;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class StaticColoredSubdivisions extends PApplet {


    /**
     * List holding the points to be used for the 2D tree.
     */
    List<Point> treePoints = new ArrayList<>();

    //Setup variables

    /**
     * Frame rate parameter.
     */
    int frameRate = 1;
    /**
     * Number of points to be generated for visualizing.
     */
    int numberOfPoints = 1000;

    /**
     * Limit of where the points can position from the bounds of the screen.
     */
    int drawingBoundOffset = 100;

    /**
     * Function picks a random color for fill and draws a rectangle with the coordinates of the child canvases of the
     * current node. It uses preorder traversal to make sure, the lower nodes are drawn last and not covered by bigger
     * rectangles from the parent nodes.
      * @param node tree node to use as the starting point for the traversal and drawing of the canvases.
     */
    public void drawTreeShapes(TwoDNode node) {
        if (node != null) {
            fill(random(255), random(255), random(255));
            rect(node.childCanvasLeft[0],node.childCanvasLeft[1],node.childCanvasLeft[2],node.childCanvasLeft[3]);
            fill(random(255), random(255), random(255));
            rect(node.childCanvasRight[0],node.childCanvasRight[1],node.childCanvasRight[2],node.childCanvasRight[3]);
            drawTreeShapes(node.leftChild);
            drawTreeShapes(node.rightChild);
        }
    }

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
        fullScreen(P2D,SPAN);

    }

    /**
     * Defines the frame rate, the rectangle mode for drawing and generate the points in random locations inside the
     * screen.
     */
    public void setup() {
        frameRate(frameRate);
        rectMode(CORNERS);

        for (int i = 0; i < numberOfPoints; i++) {
            treePoints.add(new Point((int) random(drawingBoundOffset, width-drawingBoundOffset),
                    (int) random(drawingBoundOffset, height-drawingBoundOffset)));
        }


    }

    /**
     * For every frame draw() will recreate the tree so it as the user moves the root point, the rest of the nodes will
     * be redrawn accordingly.
     */
    public void draw() {
        int[] canvas = {0, 0, width, height};
        strokeWeight(2);
        background(255);

        TwoDTree test = new TwoDTree();
        for (Point tp : treePoints) {
            test.insert(tp, canvas);
        }
        drawTreeShapes(test.root);
        drawTree(test.root);
    }

    public static void main(String[] args) {
        PApplet.main("TwoDTree.StaticColoredSubdivisions");
    }
}
