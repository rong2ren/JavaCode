package leetcode;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[3][3];
        matrix[0][0] = 1;
        matrix[0][1] = 5;
        matrix[0][2] = 9;
        matrix[1][0] = 10;
        matrix[1][1] = 11;
        matrix[1][2] = 13;
        matrix[2][0] = 12;
        matrix[2][1] = 13;
        matrix[2][2] = 15;

        s.kthSmallest(matrix, 8);
    }
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int[] cursor = new int[n];
        int count = 0;
        int smallest = Integer.MAX_VALUE;
        while(count <= k){
            int smallest_row = -1;
            smallest = Integer.MAX_VALUE;
            //find the smallest number from each row
            for(int i = 0; i < n; i++){
                if(cursor[i] == n) continue;
                System.out.println("i=" + i + " its cursor=" + cursor[i] + "-" + matrix[i][cursor[i]]);
                if(matrix[i][cursor[i]] < smallest){
                    smallest = matrix[i][cursor[i]];
                    smallest_row = i;
                }
            }
            
            if(smallest_row > -1){
                cursor[smallest_row]++;
                count++;
                System.out.println("smallest_row="+smallest_row + " count=" + count + " cursor[smallest_row]=" + cursor[smallest_row]);
            } else {
                System.out.println("something wrong: " + smallest_row);
            }
        }
        return smallest;
    }
}