package datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyLinkedList {
        
    private ListNode head;

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {head = null;}
        ListNode(int val) { this.val = val; next = null;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void append(int data){
        if(head == null) head = new ListNode(data);

        ListNode current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = new ListNode(data);
    }

    public void prepend(int data){
        ListNode newHead = new ListNode(data);
        newHead.next = head;
        head = newHead;
    }

    public void delete(int data){
        if(head == null) return;
        if(head.val == data) {
            head = head.next;
            return;
        }
        ListNode current = head;
        while(current.next != null) {
            if(current.next.val == data){
                current.next = current.next.next;
            }
        }
    }

    /**
     * Iterative Method: previous, current, next
     * Time Complexity: O(n) 
     * Space Complexity: O(1)
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        //initialize the currentNode to head, and previousNode & nextNode to null
        ListNode currentNode = head;
        ListNode previousNode = null;
        ListNode nextNode = null;

        while(currentNode != null){
            //store the nextNode
            nextNode = currentNode.next;
            //reverse the node
            currentNode.next = previousNode;
            //move previousNode, and currentNode one step forward
            previousNode = currentNode;
            currentNode = nextNode;
            
        }
        //update the real head
        head = previousNode;
        return head;

    }

    /**
     * 1) Divide the list in two parts - first node and rest of the linked list.
     * 2) Call reverse for the rest of the linked list.
     * 3) Link rest to first.
     * 4) Fix head pointer
     * Time Complexity: O(n) 
     * Space Complexity: O(1)
     */
    public ListNode reverseListRecursively(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode currentNode = head;
        /* reverse the rest list and put 
        the first element at the end */
        ListNode restLinkedListHead = reverseListRecursively(currentNode.next);

        currentNode.next.next = currentNode;
        currentNode.next = null;
        
        return restLinkedListHead;

    }

    /**
     * A simple and tail recursive function to reverse a linked list.  previousNode  is passed as NULL initially.
     */
    public ListNode reverseListRecursively(ListNode currentNode, ListNode previousNode) {
        if(currentNode == null ) return currentNode;

        /* If last node mark it head*/
        if(currentNode.next == null){
            //update next to previousNode
            currentNode.next = previousNode;
            return currentNode;
        } else {
            ListNode nextNode = currentNode.next;
            //update next to previousNode
            currentNode.next = previousNode;
            return reverseListRecursively(nextNode, currentNode);
        }
    }


    /**
     * Given the head of a singly linked list and two integers left and right where left <= right, 
     * reverse the nodes of the list from position left to position right, 
     * and return the reversed list.
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left == right) return head;

        ListNode currentNode = head;
        ListNode previousNode = null;
        ListNode nextNode = null;

        ListNode beforeFirstReverseNode = null;
        ListNode afterLastReverseNode = null;

        for(int index = 1; index <= right; index++){
            nextNode = currentNode.next;

            if(index == left - 1) beforeFirstReverseNode = currentNode;          
            else if(index >= left){
                if(index == right){
                    afterLastReverseNode = currentNode.next;
                    currentNode.next = previousNode;
                    if(left == 1){
                        //when left == 1, which means the beforeFirstReverseNode will be null
                        beforeFirstReverseNode = head;
                        head = currentNode;//need to update head
                        beforeFirstReverseNode.next = afterLastReverseNode;
                    } else {                   
                        beforeFirstReverseNode.next.next = afterLastReverseNode;
                        beforeFirstReverseNode.next = currentNode;
                    } 
                } else currentNode.next = previousNode;
            }

            previousNode = currentNode;
            currentNode = nextNode;
        }

        return head;
    }



    /**
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by 
     * continuously following the next pointer. 
     * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. 
     * Note that pos is not passed as a parameter.
     * 
     * Return true if there is a cycle in the linked list. Otherwise, return false.

     * 2 pointers, one slow, one fast
     * only when the fast reach the end, then no cycle
     * otherwise when slow and fast reach to the same node, means cycle detected
     */
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        
        ListNode slowNode = head;
        ListNode fastNode = head.next;
        
        while(slowNode != fastNode){
            if(fastNode == null || fastNode.next == null){
                //reach the end, no cycle
                return false;
            }
            
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        
        return true;
    }


    /**
     * You are given the heads of two sorted linked lists list1 and list2.
     * Merge the two lists in a one sorted list. 
     * The list should be made by splicing together the nodes of the first two lists.
     * Return the head of the merged linked list.  
     * 
     * set the next node to the smaller of the two nodes
     * m = list1 length;
     * n = list2 length;
     * time: O(m+n)
     * space: O(1)
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        //create a dummy head node
        ListNode newHead = new ListNode(0);
        
        ListNode currentNode = newHead;

        //comparison, the smallest node will be currentNode.next;
        while(list1 != null && list2 != null){
            //at first, the currentNode is point to the dummy newHead;
            //once you do the comparison, currentNode.next will update to one of the linkedlist
            //for example, list1.val is 1 and list2.val is 2
            //so currentNode.next = list2, then advance the list2 pointer
            if(list2.val < list1.val){
                currentNode.next = list2;
                list2 = list2.next;
            } else {
                currentNode.next = list1;
                list1 = list1.next;
            }
            currentNode = currentNode.next;//currentNode will always be the tail of the output sorted list
        }
        if(list1 != null) currentNode.next = list1;//append the remainder of list1
        else if(list2 != null) currentNode.next = list2;//append the remainder of list1

        return newHead.next;
    }


    /**
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     * Merge all the linked-lists into one sorted linked-list and return it.
     * Input: lists = [[1,4,5],[1,3,4],[2,6]]
     * Output: [1,1,2,3,4,4,5,6]
     * Explanation: The linked-lists are:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * merging them into one sorted list:
     * 1->1->2->3->4->4->5->6
     */

    /**
     * brute force way:
     * Traverse all the linked lists and collect the values of the nodes into an array.
     * Sort and iterate over this array to get the proper value of nodes.
     * Create a new sorted linked list and extend it with the new nodes.
     * Time complexity : O(NlogN) where N is the total number of nodes.
        - Collecting all the values costs O(N) time.
        - A stable sorting algorithm costs O(NlogN) time.
        - Iterating for creating the linked list costs O(N) time.
     * Space complexity : O(N).
        - Sorting cost O(N) space (depends on the algorithm you choose).
        - Creating a new linked list costs O(N) space. 
     */
    public ListNode mergeKListsBruteForce(ListNode[] lists) {
        if(lists.length == 0) return null;
        
        List<Integer> tempArrayList = new ArrayList<Integer>();

        for(ListNode theNode : lists){
            while(theNode != null){
                tempArrayList.add(theNode.val);
                theNode = theNode.next;
            }
        }

        Collections.sort(tempArrayList);

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for(int val : tempArrayList){
            ListNode theNode = new ListNode(val);
            current.next = theNode;
            current = current.next;
        }

        return dummyHead.next;
    }


     /**
      * 我们可以一列一列的比较，将最小的一个存到链表里。
      * Compare one by one method
      * Compare every k nodes (head of every linked list) and get the node with the smallest value.
      * Extend the final sorted linked list with the selected nodes.
      * Time complexity : O(kN) where k is the number of linked lists.
      * space complexity: O(1)
      */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;

        ListNode dummyHead = new ListNode(0);
        ListNode currentNode = dummyHead;

        int exahustedList = 0;
        while(exahustedList <= lists.length){
            int minValue = Integer.MAX_VALUE;
            int minValueIndex = -1;
            exahustedList = 0;

            for(int i = 0; i < lists.length; i++){
                if (lists[i] == null){
                    //means the list[i] linkedlist has been exahusted
                    exahustedList++;
                    continue;
                } 

                if(lists[i].val <= minValue){
                    minValue = lists[i].val;
                    minValueIndex = i;
                }
            }

            if(minValueIndex >= 0){
                //find the minValueIndex
                currentNode.next = lists[minValueIndex];
                lists[minValueIndex] = lists[minValueIndex].next;

                if(exahustedList >= lists.length - 1){
                    break;
                }
                currentNode = currentNode.next;
            } else break;

        }

        return dummyHead.next;
    }
    
}
