package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree2 {
    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,1,2,20,15,7};
        int[] inorder = new int[]{1,9,2,3,15,20,7};

        BinaryTree2 tree = new BinaryTree2();
        TreeNode root = tree.buildTree3(preorder, inorder);
    }

    private int preorder_index;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorder_index = 0;//next item to process in preorder array
        
        Map<Integer, Integer> inorder_indexmap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            inorder_indexmap.put(inorder[i], i);
        }
        
        return build_binary_tree(preorder, inorder_indexmap, 0, inorder.length);
    }
    
    private TreeNode build_binary_tree(int[] preorder, Map<Integer, Integer> inorder_indexmap, int inorder_range_left, int inorder_range_right){
        System.out.println("preorder_index=" + preorder_index + " inorder range:" + inorder_range_left + ":" + inorder_range_right);
        if(preorder_index == preorder.length || inorder_range_left > inorder_range_right) return null;

        //preorder - root, left ,right
        //Always use the next element in preorder to initialize a root.
        int root_val = preorder[preorder_index++];
        TreeNode root = new TreeNode(root_val);
        System.out.println("processing building node:" + root_val);
        
        //inorder - left, root, right
        //we can split the entire inorder range into two subtrees.
//this two subsets basically tells us when to stop putting subarray as a left subtree and continue as right subtree
        //for example: [3, 9 ,1 ,2, 20, 15, 7] ->3 is the root, 9, 1, 2 is the left subtree and 20,15,7 is the right subtree
        int root_index_inorder = inorder_indexmap.get(root_val);
        System.out.println("building" + root_val + "'s left child");
        root.left = build_binary_tree(preorder, inorder_indexmap, inorder_range_left, root_index_inorder - 1);
        System.out.println("building" + root_val + "'s right child");
        root.right = build_binary_tree(preorder, inorder_indexmap, root_index_inorder + 1, inorder_range_right);
        System.out.println("building" + root_val + " - done");
        return root;
    }


    private int i = 0;
    private int p = 0;

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (p >= preorder.length) {
            return null;
        }
        if (inorder[i] == stop) {
            //when you reach  the leave node
            ++i;
            System.out.println("node at inorder index = stop, increase inorder index to " + i);
            return null;
        }
        System.out.println("stop = " + stop + " inorder index = " + i + " preorder index = " + p);

        TreeNode node = new TreeNode(preorder[p++]);
        System.out.println("building node " + node.val);
        System.out.println("building node " + node.val + "'s left child with stop=" + node.val);
        node.left = build(preorder, inorder, node.val);
        System.out.println("building node " + node.val + "'s right child with stop=" + stop);
        node.right = build(preorder, inorder, stop);
        System.out.println("building node " + node.val + " Done!");
        return node;
    }

    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        Stack<TreeNode> preorder_stack = new Stack<>();
        int inorder_index = 0;
        TreeNode pre_node = null;
        boolean right_child = false;
        TreeNode root = null;
        
        for(int i = 0; i < preorder.length; i++){
            //construct a treenode from preorder array at current index i
            TreeNode curr_node = new TreeNode(preorder[i]);
            System.out.println("creating node:" + curr_node.val);
            //push the currNode into the stack
            preorder_stack.push(curr_node);
            printStack(preorder_stack);
            //determine whether the currNode is prevNode's left or right child.
            if(pre_node != null){
                if(!right_child){
                    System.out.println("it is:" + pre_node.val + "'s left child");
                    pre_node.left = curr_node;
                } else {
                    System.out.println("it is:" + pre_node.val + "'s right child");
                    pre_node.right = curr_node;
                    right_child = false; 
                }
            } else { root = curr_node; }
            pre_node = curr_node;
            
            //if the top of the stack matches the inorder(left, root, right)
            //it means the left children are done, following items in the preorder array are right children
            while(!preorder_stack.isEmpty() && preorder_stack.peek().val == inorder[inorder_index]){
                pre_node = preorder_stack.pop();
                System.out.println("inorder_index increase");
                printStack(preorder_stack);
                inorder_index++;
                right_child = true;
            }
            
        }
        return root;
    }



    public void printStack(Stack<TreeNode> s) {
        System.out.print("\n------------\n");
        printStackRecursion(s);
        System.out.print("\n------------\n");
    }
    
    private void printStackRecursion(Stack<TreeNode> s) {
        if (s.empty()) return;
        TreeNode x = s.peek();
        s.pop();
        if(x != null)
            System.out.print(x.val + ", ");
        else
            System.out.print("null, ");
        printStackRecursion(s);
        s.push(x);
    }

    

}
