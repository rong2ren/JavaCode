package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;


public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = tree.buildTree(nums);

        //List<Integer> list = tree.postorderTraversal(root);
        //System.out.println(Arrays.toString(list.toArray()));

        //TreeNode LCA = tree.lowestCommonAncestor(root, 9, 7);
        //System.out.println("lowestCommonAncestor:" + LCA.val);

        Vector<Integer> Euler = new Vector<Integer>();
 
        Euler = tree.eulerWalk(root, Euler);
 
        for (int i = 0; i < Euler.size(); i++)
            System.out.print(Euler.get(i) + " ");
        
    }

    //level order way
    public TreeNode buildTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < nums.length) {
            TreeNode curr = q.remove();
            if (i < nums.length) {
                curr.left = new TreeNode(nums[i++]);
                q.add(curr.left);
            }
            if (i < nums.length) {
                curr.right = new TreeNode(nums[i++]);
                q.add(curr.right);
            }
        }
        return root;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new LinkedList<>();
        if(root == null) return inorder;

        //inorder: left, parent, right
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;//curr is the tree node to process next
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
		            //keep push the left child to the stack to visit later
                stack.push(curr);
                curr = curr.left;
            }
            //backtrack → when we cannot go further (when no left child is present).
            curr = stack.pop();
            inorder.add(curr.val);
            curr = curr.right;//add the right child
        }

        return inorder;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new LinkedList<>();
        
        //preorder: root, left, right
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        printStack(stack);
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            printStack(stack);
            if(curr != null){
		            //process the parent node
                preorder.add(curr.val);
                //add left and right children
                //add left child last so it can be process first
                if(curr.right != null) stack.push(curr.right);
                if(curr.left != null) stack.push(curr.left);
                printStack(stack);
            }
        }

        return preorder;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                stack.add(cur);
                cur = cur.left;
                printStack(stack);
            }
            cur = stack.pop();
            printStack(stack);
            if (!stack.isEmpty() && cur == stack.peek()) {
                cur = cur.right;
            } else {
                result.add(cur.val);
                cur = null;
            }
        }
        return result;
    }


    public List<Integer> preorderTraversal2(TreeNode root) {//root,left,right
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                //push to the stack
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.pop();    // 重新取出栈中元素
                result.add(node.val); // 加入到结果集
            }
            printStack(st);
        }
        return result;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {//left, root, right
        List<Integer> result = new LinkedList<>();
		    Stack<TreeNode> st = new Stack<>();
		    if (root != null) st.push(root);
		    while (!st.empty()) {
		        TreeNode node = st.peek();
		        if (node != null) {
		            st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
		            if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
		            st.push(node);                          // 添加中节点
		            st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
		
		            if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
		        } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
		            st.pop();           // 将空节点弹出
		            node = st.pop();    // 重新取出栈中元素
		            result.add(node.val); // 加入到结果集
		        }
                printStack(st);
		    }
    return result;
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


    private static int BOTH_PENDING = 0;
    private static int LEFT_DONE = 1;
    private static int BOTH_DONE = 2;
    
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if(root == null) return null;
        
        // This flag is set when either one of p or q is found.
        boolean one_node_found = false;

        // This is used to keep track of the LCA.
        TreeNode LCA = null;
        TreeNode currentNode = null;
        
        Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();
        //initialize the stack with the root node
        stack.push(new Pair<TreeNode, Integer>(root, BinaryTree.BOTH_PENDING));
        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> stackTop = stack.pop();
            currentNode = stackTop.getKey();
            int currentState = stackTop.getValue();
            System.out.println("current visiting " + currentNode.val + " and its status=" + currentState);
            TreeNode childNode;
            
            if(currentState == BinaryTree.BOTH_DONE){
                //only when its state is both done, pop out of the stack
                //update the LCA node to be the next top node.
                if(LCA == currentNode && one_node_found){
                    LCA = stack.peek().getKey();
                    System.out.println(currentNode.val + " is done visiting, update LCA to " + LCA.val);
                }
            } else {//BOTH_PENDING or LEFT_DONE
                if(currentState == BinaryTree.BOTH_PENDING){
                    if(currentNode.val == p || currentNode.val == q){
                        if(one_node_found) {
                            System.out.println("find 2nd node. return LCA=" + LCA.val);
                            return LCA;
                        }
                        else {
                            one_node_found = true;
                            LCA = currentNode;
                            System.out.println("find 1st node: LCA=" + LCA.val);
                        }
                    }
                    //BOTH_PENDING -> push left child to the stack
                    childNode = currentNode.left;
                } else {
                    //LEFT_DONE -> push right child to the stack
                    childNode = currentNode.right;
                }
                 //update the currentNode state
                stack.push(new Pair<TreeNode, Integer>(currentNode, currentState + 1));
                
                if(childNode != null){
                    stack.push(new Pair<TreeNode, Integer>(childNode, BinaryTree.BOTH_PENDING));
                }
            
            }
        }
        
        return LCA;
        
    }

    // Find Euler Tour
    public Vector<Integer> eulerWalk(TreeNode root, Vector<Integer> Euler)
    {
        // store current node's data
        Euler.add(root.val);
        System.out.println(root.val);
 
        // If left node exists
        if (root.left != null)
        {
            // traverse left subtree
            Euler = eulerWalk(root.left, Euler);
            // store parent node's data
            Euler.add(root.val);
            System.out.println(root.val);
        }
 
        // If right node exists
        if (root.right != null)
        {
            // traverse right subtree
            Euler = eulerWalk(root.right, Euler);
            // store parent node's data
            Euler.add(root.val);
            System.out.println(root.val);
        }
        return Euler;
    }



    public void printEulerWalk(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(node == null){
                    node = queue.poll();
                    System.out.println(node.val + ",");
                } else {
                    System.out.println(node.val + ",");
                    if(node.left != null){
                        queue.add(node.left);
                        queue.add(null);
                        queue.add(node);
                    }
                    if(node.right != null){
                        queue.add(node.right);
                        queue.add(null);
                        queue.add(node);
                    }
                }
                
            }
        }
    }
}
