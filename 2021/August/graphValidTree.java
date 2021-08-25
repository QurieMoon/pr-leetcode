import java.util.*;

/*
 - reference code: https://www.techiedelight.com/check-undirected-graph-contains-cycle-not/
 */
class Solution {

    public boolean validTree(int n, int[][] edges) {

        Graph graph = new Graph(n, edges);

        // key variable for dfs
        List<Integer> visitiedNodeList = new ArrayList<>();

        boolean result = dfs(graph, 0, visitiedNodeList, -1);

        if(visitiedNodeList.size() != n){
            result = false;
        }

        return result;

    }

    private boolean dfs(Graph graph, int currentNode, List<Integer> visitiedNodeList, int parentNode) {

        visitiedNodeList.add(currentNode);

        List<Integer> neighborNodeList = graph.getAdjNodeList().get(currentNode);

        for(Integer neighborNode : neighborNodeList){

            if(!visitiedNodeList.contains(neighborNode)){

                dfs(graph, neighborNode, visitiedNodeList, currentNode);

            } else{

                if(neighborNode != parentNode){
                    return false;
                }

            }

        }

        return true;

    }

    class Graph{

        List<List<Integer>> adjNodeList = new ArrayList<>();

        public Graph(int n, int[][] edges){

            for (int i = 0; i < n; i++){
                this.adjNodeList.add(new ArrayList<>());
            }

            // convert undirected graph into directed graphs
            for(int[] edge : edges){
                int point1 = edge[0];
                int point2 = edge[1];

                this.adjNodeList.get(point1).add(point2);
                this.adjNodeList.get(point2).add(point1);

            }

        }

        public List<List<Integer>> getAdjNodeList() {
            return this.adjNodeList;
        }
    }

}
