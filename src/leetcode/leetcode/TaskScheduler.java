package leetcode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();
        char[] input = {'A','A', 'A', 'B', 'B', 'B'};
        System.out.println(ts.leastInterval2(input, 2));
        
    }
    public int leastInterval2(char[] tasks, int n) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for(char c: tasks){
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        maxHeap.addAll(frequencyMap.values());
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        int unitTime = 0;
        
        while(!maxHeap.isEmpty() || !queue.isEmpty()){
            unitTime++; //process the task or idle
            if(!maxHeap.isEmpty()){
                int currentTaskFreq = maxHeap.poll();
                if(currentTaskFreq > 1)
                    queue.add(new int[]{currentTaskFreq - 1, unitTime + n});//task freq, next available time
            }
            
            if(!queue.isEmpty()){
                int[] topTask = queue.peek();
                if(topTask[1] <= unitTime){
                    queue.poll();
                    maxHeap.add(topTask[0]);
                }
            }
            
        }
        
        return unitTime;
        
    }

    public int leastInterval(char[] tasks, int n) {
        //Map<Character, Integer> tasksMap = new HashMap<>();
        Queue<Map.Entry<Character, Integer>> taskQueue = new PriorityQueue<>((a,b) -> (a.getValue() - b.getValue()));
        int unitTime = 0;
        
        for(char task : tasks){
            if(taskQueue.isEmpty()){
                unitTime++;
                taskQueue.offer(new AbstractMap.SimpleEntry<>(task, unitTime + n + 1));//task, next unit time
                System.out.print(task + ' ');
            }else{
                Map.Entry<Character, Integer> map = taskQueue.peek();
                if(map.getKey() == task){
                    if(map.getValue() <= unitTime + 1){
                        //you can perform the task
                        taskQueue.poll();
                        unitTime++;
                        taskQueue.offer(new AbstractMap.SimpleEntry<>(task, unitTime + n + 1));
                        System.out.print(task + ' ');
                    } else {
                        //not available, you cannot perform the task now.
                        taskQueue.offer(new AbstractMap.SimpleEntry<>(task, map.getValue() + n + 1));//task, the earliest available time
                    }
                } else {
                    unitTime++;
                    taskQueue.offer(new AbstractMap.SimpleEntry<>(task, unitTime + n + 1));
                    System.out.print(task + ' ');
                }
            }
            
        }
        
        return unitTime;
        
    }
}
