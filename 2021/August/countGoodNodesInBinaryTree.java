import java.util.*;

class Solution {

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int goodNodes(TreeNode root) {

        Set<TreeNode> visitedNodes = new HashSet<>();
        List<TreeNode> currentPathNodes = new ArrayList<>();
        List<TreeNode> goodNodeList = new ArrayList<>();

        DFS(visitedNodes, currentPathNodes, root, goodNodeList);

        return goodNodeList.size();

    }

    private void DFS(Set<TreeNode> visitedNodes, List<TreeNode> currentPathNodes, TreeNode currentNode, List<TreeNode> goodNodeList){

        if(currentPathNodes.size() == 0 || (currentPathNodes.size() > 0 && returnCurrentMaxValue(currentPathNodes) <= currentNode.val)){
            goodNodeList.add(currentNode);
        }
        visitedNodes.add(currentNode);
        currentPathNodes.add(currentNode);

        if(currentNode.left != null){
            if(!visitedNodes.contains(currentNode.left)){
                DFS(visitedNodes, currentPathNodes, currentNode.left, goodNodeList);
                currentPathNodes.remove(currentNode.left);
            }
        }

        if(currentNode.right != null){
            if(!visitedNodes.contains(currentNode.right)){
                DFS(visitedNodes, currentPathNodes, currentNode.right, goodNodeList);
                currentPathNodes.remove(currentNode.right);
            }
        }

    }

    private Integer returnCurrentMaxValue(List<TreeNode> currentPathNodes){

        if(currentPathNodes.size() == 0){
            return 0;
        }

        Integer maxValue = currentPathNodes.get(0).val;

        for(int i = 1; i < currentPathNodes.size(); i++){
            if(currentPathNodes.get(i).val > maxValue){
                maxValue = currentPathNodes.get(i).val;
            }
        }

        return maxValue;

    }

}
