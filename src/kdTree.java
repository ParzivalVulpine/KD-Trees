import processing.core.PApplet;

public class kdTree extends PApplet{
    class Point {
        public int coordinateX;
        public int coordinateY;

        public int getX() {
            return coordinateX;
        }

        public void setX(int coordinateX) {
            this.coordinateX = coordinateX;
        }

        public int getY() {
            return coordinateY;
        }

        public void setY(int coordinateY) {
            this.coordinateY = coordinateY;
        }

        public Point(int x, int y){
            coordinateX = x;
            coordinateY = y;
        }
    }
    class KDTreeNode {
        public int level;
        public Point coordinates;
        public KDTreeNode parentKDTreeNode;
        public KDTreeNode leftChild;
        public KDTreeNode rightChild;

        //constructor to create a new node
        public KDTreeNode(Point value, int lvl) {
            this.coordinates = value;
            parentKDTreeNode = null;
            leftChild = null;
            rightChild = null;
            level = lvl;
        }

        public int[] getCoordinates() {
            return new int[]{coordinates.coordinateX, coordinates.coordinateY};
        }
        public int getX(){
            return getCoordinates()[0];
        }
        public int getY(){
            return getCoordinates()[1];
        }
    }
    class KDTree {

        KDTreeNode root;

        //insert a node into the proper position based on it's value
        public void insert(Point coordinates){
            //recursively add from root
            root = addRecursive(root, coordinates, 0);
        }

        //recursively add Nodes to binary tree in proper position
        private KDTreeNode addRecursive(KDTreeNode current, Point value, int level){
            //nothing set
            if (current == null){
                current = new KDTreeNode(value, level);
                return current;
            }
            if (level % 2 == 0){
                //1 check value compared to value of current node value
                if (value.getX() < current.getCoordinates()[0]){
                    //it goes to the left node
                    current.leftChild = addRecursive(current.leftChild, value, level+1);
                } else {
                    //it goes to the right node
                    current.rightChild = addRecursive(current.rightChild, value, level+1);
                }
            }
            else{
                if (value.getY() < current.getCoordinates()[1]){
                    //it goes to the left node
                    current.leftChild = addRecursive(current.leftChild, value, level+1);
                } else {
                    //it goes to the right node
                    current.rightChild = addRecursive(current.rightChild, value, level+1);
                }
            }



            return current;
        }

        public void traverseInOrder(KDTreeNode node){
            if (node != null){
                traverseInOrder(node.leftChild);
                if(node.parentKDTreeNode == null){
                    line(node.getX(), 500, node.getX(),0);
                }
                else {
                    if(node.level % 2 == 0){
                        line(node.getX(), node.parentKDTreeNode.getY(), node.getX(), 0);
                    }
                    else {
                        line(node.parentKDTreeNode.getX(), node.getY(), node.parentKDTreeNode.getX(), 0);
                    }
                }
                traverseInOrder(node.rightChild);
            }
        }

        public KDTreeNode getRoot(){
            return root;
        }

    }

    public void settings(){
        size(500, 500);
    }

    public void setup() {
    }

    public void draw() {
        strokeWeight(5);
        Point[] treePoints = {new Point(250, 250),
                new Point(150, 150),
                new Point(200, 100),
                new Point(140, 130),
                new Point(120, 100)};
        KDTree test = new KDTree();
        for (Point tp : treePoints) {
            test.insert(tp);
        }
        test.traverseInOrder(test.root);
    }


    public static void main(String... args){
        PApplet.main("kdTree");
    }
}