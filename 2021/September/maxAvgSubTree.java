import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public double maximumAverageSubtree(TreeNode root) {

        List<TreeNode> visitiedNodeList = new ArrayList<>();
        List<Double> avgList = new ArrayList<>();

        dfs(visitiedNodeList, avgList, root);

        return Collections.max(avgList);

    }

    private List<Double> dfs(List<TreeNode> visitiedNodeList, List<Double> avgList, TreeNode root) {

        double sum = 0;
        double counts = 0;

        List<Double> sumCountsInfoList = new ArrayList<>(); // [sum, counts]

        // 1. Mark root node as visited node
        visitiedNodeList.add(root);
        sum += root.val;
        counts += 1;

        if(root.left == null && root.right == null){
            avgList.add(sum/counts);
            sumCountsInfoList.add(sum);
            sumCountsInfoList.add(counts);
            return sumCountsInfoList;
        }

        // 2. visit children
        List<Double> childSumCountsInfoList;
        if(root.left != null && !visitiedNodeList.contains(root.left)){
            childSumCountsInfoList = dfs(visitiedNodeList, avgList, root.left);
            sum += childSumCountsInfoList.get(0);
            counts += childSumCountsInfoList.get(1);
        }

        if(root.right != null && !visitiedNodeList.contains(root.right)){
            childSumCountsInfoList = dfs(visitiedNodeList, avgList, root.right);
            sum += childSumCountsInfoList.get(0);
            counts += childSumCountsInfoList.get(1);
        }

        avgList.add(sum/counts);

        sumCountsInfoList.add(sum);
        sumCountsInfoList.add(counts);
        return sumCountsInfoList;

    }
}
