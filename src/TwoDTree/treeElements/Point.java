package TwoDTree.treeElements;
    /**
     * @author Daniel Gaitan
     * @version 1.0
     * @since 1.0
     * <p>
     * Point class, it defines the x and y coordinates of a node within the tree.
     */
    public class Point {

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
