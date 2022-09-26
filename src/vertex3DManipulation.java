/*
* TODO: Review what java library does not let IntelliJ run 3D.
* */

import processing.core.PApplet;

public class vertex3DManipulation extends PApplet {
    class CustomBoxx{
        int size;
        int[][] vectors = new int[8][];
        int[][] saved = new int[8][];
        float[][] move = new float[8][];
        double sizeLimitOutliar;
        int randomFactor;
        CustomBoxx(int x){
            size = x;
            sizeLimitOutliar = x*0.15;
            randomFactor = 3;
            vectors[0] = new int[] {-size/2, -size/2, -size/2};
            vectors[1] = new int[] {size/2, -size/2, -size/2};
            vectors[2] = new int[] {size/2, -size/2, size/2};
            vectors[3] = new int[] {-size/2, -size/2, size/2};
            vectors[4] = new int[] {-size/2, size/2, -size/2};
            vectors[5] = new int[] {size/2, size/2, -size/2};
            vectors[6] = new int[] {size/2, size/2, size/2};
            vectors[7] = new int[] {-size/2, size/2, size/2};

            saved[0] = new int[] {-size/2, -size/2, -size/2};
            saved[1] = new int[] {size/2, -size/2, -size/2};
            saved[2] = new int[] {size/2, -size/2, size/2};
            saved[3] = new int[] {-size/2, -size/2, size/2};
            saved[4] = new int[] {-size/2, size/2, -size/2};
            saved[5] = new int[] {size/2, size/2, -size/2};
            saved[6] = new int[] {size/2, size/2, size/2};
            saved[7] = new int[] {-size/2, size/2, size/2};

            move[0] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};
            move[1] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};
            move[2] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};
            move[3] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};
            move[4] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};
            move[5] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};
            move[6] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};
            move[7] = new float[] {random(-randomFactor, randomFactor), random(-randomFactor, randomFactor), random(-randomFactor, randomFactor)};

        }

        void drawCustomBox(){
            beginShape();
            vertex(vectors[0][0], vectors[0][1], vectors[0][2]);
            vertex(vectors[1][0], vectors[1][1], vectors[1][2]);
            vertex(vectors[2][0], vectors[2][1], vectors[2][2]);
            vertex(vectors[3][0], vectors[3][1], vectors[3][2]);
            vertex(vectors[0][0], vectors[0][1], vectors[0][2]);
            vertex(vectors[4][0], vectors[4][1], vectors[4][2]);
            vertex(vectors[5][0], vectors[5][1], vectors[5][2]);
            vertex(vectors[6][0], vectors[6][1], vectors[6][2]);
            vertex(vectors[7][0], vectors[7][1], vectors[7][2]);
            vertex(vectors[4][0], vectors[4][1], vectors[4][2]);
            vertex(vectors[5][0], vectors[5][1], vectors[5][2]);
            vertex(vectors[1][0], vectors[1][1], vectors[1][2]);
            vertex(vectors[2][0], vectors[2][1], vectors[2][2]);
            vertex(vectors[6][0], vectors[6][1], vectors[6][2]);
            vertex(vectors[7][0], vectors[7][1], vectors[7][2]);
            vertex(vectors[3][0], vectors[3][1], vectors[3][2]);
            endShape();
        }

        void elevatedBox(){

            for (int currentVertex = 0; currentVertex < 8; ++currentVertex) {
                if (vectors[currentVertex][0] + move[currentVertex][0] < saved[currentVertex][0] + sizeLimitOutliar &&
                        vectors[currentVertex][0] + move[currentVertex][0] > saved[currentVertex][0] - sizeLimitOutliar) {
                    vectors[currentVertex][0] += move[currentVertex][0];
                }
                if(vectors[currentVertex][0] + move[currentVertex][0] + 1 >= saved[currentVertex][0] + sizeLimitOutliar ||
                        vectors[currentVertex][0] + move[currentVertex][0] - 1 <= saved[currentVertex][0] - sizeLimitOutliar) {
                    move[currentVertex][0] *= random((float) -1.2, (float) -0.8);
                }
                if (vectors[currentVertex][1] + move[currentVertex][1] < saved[currentVertex][1] + sizeLimitOutliar &&
                        vectors[currentVertex][1] + move[currentVertex][1] > saved[currentVertex][1] - sizeLimitOutliar) {
                    vectors[currentVertex][1] += move[currentVertex][1];
                }
                if(vectors[currentVertex][1] + move[currentVertex][1] + 1 >= saved[currentVertex][1] + sizeLimitOutliar ||
                        vectors[currentVertex][1] + move[currentVertex][1] - 1 <= saved[currentVertex][1] - sizeLimitOutliar){
                    move[currentVertex][1] *= random((float) -1.2, (float) -0.8);
                }
                if (vectors[currentVertex][2] + move[currentVertex][2] < saved[currentVertex][2] + sizeLimitOutliar &&
                        vectors[currentVertex][2] + move[currentVertex][2] > saved[currentVertex][2] - sizeLimitOutliar) {
                    vectors[currentVertex][2] += move[currentVertex][2];
                }
                if(vectors[currentVertex][2] + move[currentVertex][2] + 1 >= saved[currentVertex][2] + sizeLimitOutliar ||
                        vectors[currentVertex][2] + move[currentVertex][2] - 1 <= saved[currentVertex][2] - sizeLimitOutliar){
                    move[currentVertex][2] *= random((float) -1.2, (float) -0.8);
                }
            }


            beginShape(); //<>//
            vertex(vectors[0][0], vectors[0][1], vectors[0][2]);
            vertex(vectors[1][0], vectors[1][1], vectors[1][2]);
            vertex(vectors[2][0], vectors[2][1], vectors[2][2]);
            vertex(vectors[3][0], vectors[3][1], vectors[3][2]);
            vertex(vectors[0][0], vectors[0][1], vectors[0][2]);
            vertex(vectors[4][0], vectors[4][1], vectors[4][2]);
            vertex(vectors[5][0], vectors[5][1], vectors[5][2]);
            vertex(vectors[6][0], vectors[6][1], vectors[6][2]);
            vertex(vectors[7][0], vectors[7][1], vectors[7][2]);
            vertex(vectors[4][0], vectors[4][1], vectors[4][2]);
            vertex(vectors[5][0], vectors[5][1], vectors[5][2]);
            vertex(vectors[1][0], vectors[1][1], vectors[1][2]);
            vertex(vectors[2][0], vectors[2][1], vectors[2][2]);
            vertex(vectors[6][0], vectors[6][1], vectors[6][2]);
            vertex(vectors[7][0], vectors[7][1], vectors[7][2]);
            vertex(vectors[3][0], vectors[3][1], vectors[3][2]);
            endShape();
        }
    }
    CustomBoxx custom = new CustomBoxx(500);
    int rotation = 0;
    public void settings(){
        size(1000, 1000, P3D);
    }

    @Override
    public void draw() {
        strokeWeight(5);
        background(255);
        noFill();
        pushMatrix();
        translate(500, 500, 0);
        rotateY(radians(rotation));
        custom.elevatedBox();
        popMatrix();

        rotation += 1;
    }
    public static void main(String... args){
        PApplet.main("vertex3DManipulation");
    }
}
