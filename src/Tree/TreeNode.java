package Tree;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    int val = 0;
    List<TreeNode> children = null;

    public TreeNode(int val){
        this.val = val;
        this.children = new LinkedList<>();
    }

    public TreeNode(int val, List<TreeNode> children){
        this.val = val;
        this.children = children;
    }

    public List<TreeNode> getChildren(){
        return this.children;
    }

    public void addChild(TreeNode child){
        this.children.add(child);
    }
}
