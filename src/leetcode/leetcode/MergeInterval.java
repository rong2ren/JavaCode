package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


public class MergeInterval {
    public static void main(String[] args) {
        int[][] input = new int[][]{new int[]{1,3},new int[]{2,6},new int[]{8,10},new int[]{15,18}};
        int[][] output = merge2(input);
        for(int[] inteval: output){
            System.out.println(inteval[0] + ", " + inteval[1]);
        }
    }

    /**
     * brute force way
     */
    private Set<int[]> visited;
    private Map<Integer, List<int[]>> nodesInComp;
    private Map<int[], LinkedList<int[]>> graphy;

    public int[][] merge1(int[][] intervals) {
        List<int[]> mergedList = new LinkedList<int[]>();

        //build graphy where an undirected ege between intervals u and v exits
        graphy = new HashMap<int[], LinkedList<int[]>>();
        for(int[] interval : intervals){
            graphy.put(interval, new LinkedList<int[]>());
        }
        
        for(int[] node1: intervals){
            for(int[] node2: intervals){
                if(overlap(node1, node2)){
                    graphy.get(node1).add(node2);
                    graphy.get(node2).add(node1);
                }
            }
        }

        //gets the connected componenets of the interval overlap grpahy.
        visited = new HashSet<int[]>();
        int compNumber = 0;
        for(int[] interval : intervals){
            if(visited.contains(interval)){
                markComponentDFS(interval, compNumber);
                compNumber++;
            }
        }


        //for each component, merge all intervals into one interval
        for(int comp = 0; comp < nodesInComp.size(); comp++){
            mergedList.add(mergeNodes(nodesInComp.get(comp)));
        }

        return mergedList.toArray(new int[mergedList.size()][]);
    }

    //check whether two intervals overlap
    private boolean overlap(int[] a, int[] b){
        if(a[0] <= b[1] && b[0] <= a[1]) return true;
        else return false;
    }

    // use depth-first search to mark all nodes in the same connected component
    // with the same integer.
    private void markComponentDFS(int[] start, int compNumber){
        Stack<int[]> theStack = new Stack<>();
        theStack.add(start);
        while(!theStack.isEmpty()){
            int[] node = theStack.pop();
            if(!visited.contains(node)){
                visited.add(node);

                if(nodesInComp.get(compNumber) == null){
                    nodesInComp.put(compNumber, new LinkedList<int[]>());
                }
                nodesInComp.get(compNumber).add(node);

                for(int[] child : graphy.get(node)){
                    theStack.add(child);
                }
            }
        }
    }
    
    // merges all of the nodes in this connected component into one interval.
    private int[] mergeNodes(List<int[]> nodes) {
        int minStart = nodes.get(0)[0];
        for (int[] node : nodes) {
            minStart = Math.min(minStart, node[0]);
        }

        int maxEnd = nodes.get(0)[1];
        for (int[] node : nodes) {
            maxEnd = Math.max(maxEnd, node[1]);
        }

        return new int[] {minStart, maxEnd};
    }

    /**
     * Given an array of intervals where intervals[i] = [starti, endi], 
     * merge all overlapping intervals, 
     * and return an array of the non-overlapping intervals that cover all the intervals in the input.
     * 
     * sort the input: we can use TreeMap or ArrayList
     */
    public static int[][] merge2(int[][] intervals) {
        //sort the intervals array
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> mergedList = new LinkedList<int[]>();

        for(int[] interval : intervals){
            if(mergedList.isEmpty() || interval[0] > mergedList.getLast()[1]){
                //if current starting point is bigger than last end point: no overlap, simple add to the linkedlist
                mergedList.add(interval);
            } else {
                mergedList.getLast()[1] = Math.max(mergedList.getLast()[1], interval[1]);
            }
        }

        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
