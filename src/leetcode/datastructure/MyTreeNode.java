package datastructure;

public class MyTreeNode {
    int val;
    MyTreeNode left;
    MyTreeNode right;

    public MyTreeNode(){}

    public MyTreeNode(int val){this.val = val;}

    public MyTreeNode(int val, MyTreeNode left, MyTreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
