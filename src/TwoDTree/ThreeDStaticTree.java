package TwoDTree;

import processing.core.PApplet;

public class ThreeDStaticTree extends PApplet {

    float rotation = 0;

    public void settings() {
        size(1920, 1080, P3D);
    }

    public void draw() {
        background(255);

        noFill();

        float orbitRadius = 1000;
        float ypos = mouseY / 2;
        float xpos = cos(radians(rotation)) * orbitRadius;
        float zpos = sin(radians(rotation)) * orbitRadius;

        camera(xpos, ypos, zpos, 0, 0, 0, 0, -1, 0);

        box(500);

        rotation++;
    }

    public static void main(String... args) {
        PApplet.main("TwoDTree.ThreeDStaticTree");
    }
}
