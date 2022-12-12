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
 * <p>
 * This sketch implements the Nearest Point Search algorithm that the KD Tree wikipedia page describes.
 */
public class NearestPointSearch extends PApplet {
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
    int drawingBoundOffset = 10;

    /**
     * This global variable will point to what the closest point is to the mouse.
     */
    TwoDNode closest = null;

    /**
     * This global variable will describe the radius of the circle that will be drawn around the closest point to the
     * mouse. It is arbitrarily large to ensure that the circle will always be drawn.
     */
    int smallestRadius = 1000000;

    /**
     * This global variable contains how many times the NPS algorithm has been called.
     */
    int indexation = 0;

    /**
     * This algorithm generates a point with the mouse cordinates and then searches for the closest point to it.
     * <p>
     * The process it follows is similar to the insertion algorithm, but once it reaches a leaf node it checks its distance
     * and makes it the closest yet before going back up the tree. It then checks if the other side of the tree is closer
     * than the current closest point and if it is, it goes down that side of the tree. This process is repeated until the
     * algorithm reaches the root node.
     *
     * @param node
     * @return The closest node to the mouse.
     */
    public TwoDNode NPS(TwoDNode node) {
        if (node == null) {
            return null;
        }

        indexation++;

        Point target = new Point(mouseX, mouseY);
        Point current = node.coordinates;

        TwoDNode next, other;
        int unidimensionalDistance;

        if (node.level % 2 == 0) {
            if (target.getX() < current.getX()) {
                next = node.leftChild;
                other = node.rightChild;
            } else {
                next = node.rightChild;
                other = node.leftChild;
            }
            unidimensionalDistance = target.getX() - current.getX();
        } else {
            if (target.getY() < current.getY()) {
                next = node.leftChild;
                other = node.rightChild;
            } else {
                next = node.rightChild;
                other = node.leftChild;
            }
            unidimensionalDistance = target.getY() - current.getY();
        }

        TwoDNode temp = NPS(next);
        TwoDNode best = closest(target, temp, node);

        int radiusSquared = distanceSquared(target, best.coordinates);
        if (radiusSquared >= unidimensionalDistance * unidimensionalDistance) {
            temp = NPS(other);
            best = closest(target, temp, best);
        }
        smallestRadius = radiusSquared;
        return best;
    }


    /**
     * This method checks which of the two points is closer to the target.
     *
     * @param target
     * @param n1
     * @param n2
     * @return The closest node to the target.
     */
    public TwoDNode closest(Point target, TwoDNode n1, TwoDNode n2) {
        if (n1 == null) return n2;
        if (n2 == null) return n1;

        if (distanceSquared(target, n1.coordinates) < distanceSquared(target, n2.coordinates)) return n1;
        else return n2;
    }

    /**
     * This method calculates the distance between two points.
     *
     * @param p1
     * @param p2
     * @return The distance between the two points.
     */
    public int distanceSquared(Point p1, Point p2) {
        int distance = (int) (Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
        return distance;
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

    public void drawMousePoint() {
        circle(mouseX, mouseY, 5);
    }

    public void settings() {
        fullScreen(P2D, SPAN);
    }

    public void setup() {
        frameRate(frameRate);

        for (int i = 0; i < numberOfPoints; i++) {
            treePoints.add(new Point((int) random(drawingBoundOffset, width - drawingBoundOffset),
                    (int) random(drawingBoundOffset, height - drawingBoundOffset)));
        }
    }


    /**
     * For every frame draw() will recreate the tree so it as the user moves the root point, the rest of the nodes will be
     * redrawn accordingly.
     */
    public void draw() {
        closest = null;
        smallestRadius = 1000000;
        indexation = 0;

        int[] canvas = {0, 0, width, height};
        strokeWeight(2);
        background(255);

        TwoDTree test = new TwoDTree();
        for (Point tp : treePoints) {
            test.insert(tp, canvas);
        }
        stroke(0);
        fill(0);
        closest = NPS(test.root);
        drawTree(test.root);
        noFill();

        circle(mouseX, mouseY, (int) Math.sqrt(smallestRadius) * 2);
        stroke(255, 0, 0);
        line(mouseX, mouseY, closest.getX(), closest.getY());
        drawMousePoint();
        textSize(40);
        text("Indexation: " + indexation, 100, 300);
        indexation = 0;
    }

    public static void main(String... args) {
        PApplet.main("TwoDTree.NearestPointSearch");
    }
}
