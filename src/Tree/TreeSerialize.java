package Tree;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeSerialize {
    public static void main(String[] args) {
        TreeSerialize serialize = new TreeSerialize();
        TreeNode root = serialize.deSeralize("123#@2#3#4#@5#6#@@7#8#@");
        serialize.levelOrderPrint(root);
    }

    public void levelOrderPrint(TreeNode root){
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode curr = queue.poll();
                System.out.print(curr.val + " ");
                List<TreeNode> children = curr.getChildren();
                for(TreeNode child : children){
                    queue.add(child);
                }
            }
            System.out.println();
        }
    }

    public String serialize(TreeNode root){
        //breath first - level order traversal of the tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder builder = new StringBuilder();
        while(!queue.isEmpty()){            
            int queue_size = queue.size();
            for(int i = 1; i <= queue_size; i++){
                //process the nodes from the queue
                TreeNode curr = queue.remove();
                builder.append(curr.val);
                builder.append("#");//#: end of the node
                List<TreeNode> children = curr.getChildren();
                for(TreeNode child : children) queue.add(child);
            }
            //root has been removed
            //all chidlren in the queue
            //you need to add a @ symbol to represent no more children
            builder.append("@");//@: end of the children
        }
        return builder.toString();

    }

    public TreeNode deSeralize(String str){
        TreeNode dummy_root = new TreeNode(0);
        int val = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(dummy_root);

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c >= '0' && c <= '9'){
                //part of the node val
                val = val * 10 + (c - '0');
            } else if(c == '#'){
                //end of the node
                TreeNode node = new TreeNode(val);
                //reset val for next node
                val = 0;
                
                TreeNode parent = queue.peek();
                parent.addChild(node);
                
                queue.add(node);
            } else if(c == '@'){
                //end of the children
                //poll the parent from the queue
                queue.poll();
            }
        }
        
        return dummy_root.getChildren().get(0);
    }
}
