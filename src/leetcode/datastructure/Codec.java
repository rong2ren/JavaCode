package datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class Codec {

    public static void main(String[] args) {
        String data = "4,-7,-3,#,#,-9,-3,9,-7,-4,#,6,#,-6,-6,#,#,0,6,5,#,9,#,#,-1,-4,#,#,#,-2,#,#,#,#,#,#,#";
        Codec c = new Codec();
        c.deserialize(data);
    }

    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;

        String[] treeArray = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        
        TreeNode root = createTreeNode(treeArray[0]);
        queue.add(root);
        for(int i = 0; i < treeArray.length; i++){
            TreeNode parent = queue.poll();
            
            TreeNode left = createTreeNode(treeArray[i+1]);
            parent.left = left;
            if(left != null) queue.add(left);
            
            TreeNode right = createTreeNode(treeArray[i+2]);
            parent.right = right;
            if(right != null) queue.add(right);
        }
        return root;
    }
    
    private TreeNode createTreeNode(String str){
        if(str.equals("#")) return null;
        return new TreeNode(Integer.parseInt(str));
    }
}
