package TwoDTree;

import TwoDTree.treeElements.Point;
import TwoDTree.treeElements.TwoDNode;
import TwoDTree.treeElements.TwoDTree;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class MovingColored2DTree extends PApplet {
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
    int numberOfPoints = 1000;
    /**
     * Limit of where the points can position from the bounds of the screen.
     */
    int drawingBoundOffset = 100;
    /**
     * Defines how big can a movement vector be, the higher the number the faster some points can move.
     */
    int vectorRandomFactor = 5;

    //Coloring selection

    int[] colorPalette_1 = {0xffca054d,0xff3b1c32,0xffa4d4b4,0xffffcf9c,
                            0xffb96d40, 0xFFF9564F, 0xffF9564F, 0xffFF6B6B};

    int[] colorPalette_2 = {0xFF009FB7, 0xFF53FF45, 0xFFFFD766, 0xFFFF6201, 0xFFFE5E41, 0xFFFF6685, 0xFFF61067,
                            0xFF071E22, 0xFF0D1B1E, 0xFF0C1618};

    int[] colorPalette_3 = {0xFFF72585, 0xFF7209B7, 0xFF3A0CA3, 0xFF4361EE, 0xFF4CC9F0};

    int[] colorPalette_4 = {0xFFDF2935, 0xFF86BA90, 0xFFF5F3BB, 0xFFDFA06E, 0xFF412722};

    /**
     * Current color palette selected for the animated sketch.
     */
    int[] colorSelection = colorPalette_1;

    /**
     * Draws the KD tree by doing an inorder traversal of the tree and using the subdivided canvas of each node to
     * draw a line splitting a section of the plane in half. For the best results always pass the root of the tree
     * as a parameter.
     *
     * @param node tree node to use as the starting point for the traversal and drawing of the tree.
     */
    public void drawTree(TwoDNode node) {
        fill(255);
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
     * Function picks a color from the color selection for fill and draws a rectangle with the coordinates of the child
     * canvases of the current node. It uses preorder traversal to make sure, the lower nodes are drawn last and not
     * covered by bigger rectangles from the parent nodes.
     * @param node tree node to use as the starting point for the traversal and drawing of the canvases.
     */
    public void drawTreeShapes(TwoDNode node) {
        if (node != null) {
            int colorLevel = node.level % colorSelection.length;
            fill(colorSelection[colorLevel], 255);
            rect(node.childCanvasLeft[0],node.childCanvasLeft[1],node.childCanvasLeft[2],node.childCanvasLeft[3]);
            rect(node.childCanvasRight[0],node.childCanvasRight[1],node.childCanvasRight[2],node.childCanvasRight[3]);
            drawTreeShapes(node.leftChild);
            drawTreeShapes(node.rightChild);
        }
    }

    /**
     * Settings holds how the program will display.
     */
    public void settings(){fullScreen(P2D, SPAN);}

    /**
     * Defines the frame rate and generate the points in random locations inside the screen and the movement vector for
     * each point.
     */
    public void setup(){
        rectMode(CORNERS);
        frameRate(frameRate);
        for (int i = 0; i < numberOfPoints + 1; i++) {
            if (i < numberOfPoints){
                treePoints.add(new Point((int) random(drawingBoundOffset, width - drawingBoundOffset),
                        (int) random(drawingBoundOffset, height - drawingBoundOffset)));

                int[] v = {(int) random(-vectorRandomFactor, vectorRandomFactor), (int) random(-vectorRandomFactor, vectorRandomFactor)};
                vectors.add(v);
            }
        }
    }

    /**
     * For every frame draw() will change the coordinates of each point in the list based on its respective movement
     * vector and will regenerate the tree to draw it.
     */
    public void draw(){
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
        drawTreeShapes(test.root);
        drawTree(test.root);
        /*if(frameCount < 1000){
            saveFrame("moving-#####.png");
        }
        if(frameCount > 1000){
            exit();
        }*/
    }


    public static void main(String... args) {
        PApplet.main("TwoDTree.MovingColored2DTree");
    }


}
