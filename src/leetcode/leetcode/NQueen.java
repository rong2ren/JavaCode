package leetcode;

public class NQueen {
    private int[][] current_board;
    private static int EMPTY = 0;
    private static int QUEUE_PLACED = -1;
    private static int BEING_ATTACKED = 1;

    public static void main(String[] args) {
        int n = 4;
        NQueen nq = new NQueen();
        int count = nq.totalNQueens(n);
        System.out.println("Done. Total: " + count);
    }

    private void printBoard(){
        for(int[] row : current_board){
            for(int i = 0; i < row.length; i++){
                System.out.print(row[i] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }
    
    public int totalNQueens(int n) {
        current_board = new int[n][n];
        return backtrack_nqueen(n, 0, 0);
    }
    
    private int backtrack_nqueen(int n, int row, int count){
        //for current row, iterate through all columns
        for(int col = 0; col < n; col++){
            if(!is_under_attack(row, col)){
                place_queen(row, col, n);
                if(row + 1 == n){
                    //we reach the bottom, i.e. we find a solution
                    count += 1;
                    System.out.println("Found one solution: ");
                    return count;
                } else {
                    //continue go to the next row until find a solution
                    count = backtrack_nqueen(n, row + 1, count);
                }
                remove_queue(row, col, n);
            }
        }
        
        return count;
    }
    
    private boolean is_under_attack(int row, int col){
        System.out.println("trying on row " + row + " col " + col + ": " + current_board[row][col]);
        if(current_board[row][col] != NQueen.EMPTY) return true;//mean it is either Queen or Being attacked
        else return false;
    }
    
    private void place_queen(int row, int col, int n){
        current_board[row][col] = NQueen.QUEUE_PLACED;
        //mark all places on board that are being attacked
        int i, j;
        /* update same row and same column */
        for(i = 0; i < n; i++){
            if(current_board[i][col] != NQueen.QUEUE_PLACED) current_board[i][col]++;
            if(current_board[row][i] != NQueen.QUEUE_PLACED) current_board[row][i]++;
        }
        /*  upper diagonal on left side */
        for (i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]++;
            
        /*  lower diagonal on left side */
        for (i = row + 1, j = col - 1; j >= 0 && i < n; i++, j--)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]++;
        
        /*  upper diagonal on right side */
        for (i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]++;
        
        /*  lower diagonal on right side */
        for (i = row + 1 , j = col + 1; i < n && j < n; i++, j++)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]++;
            
        System.out.println("place queue on row " + row + " col " + col);
        printBoard();
    }
    
    private void remove_queue(int row, int col, int n){
        System.out.println("remove queue on row " + row + " col " + col);
        
        //remove all the attach places
        /*  upper diagonal on left side */
        int i, j;
        /* update same row and same column */
        for(i = 0; i < n; i++){
            if(current_board[i][col] != NQueen.QUEUE_PLACED) current_board[i][col]--;
            if(current_board[row][i] != NQueen.QUEUE_PLACED) current_board[row][i]--;
        }
        /*  upper diagonal on left side */
        for (i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]--;
            
        /*  lower diagonal on left side */
        for (i = row + 1, j = col - 1; j >= 0 && i < n; i++, j--)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]--;
        
        /*  upper diagonal on right side */
        for (i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]--;
        
        /*  lower diagonal on right side */
        for (i = row + 1 , j = col + 1; i < n && j < n; i++, j++)
            if(current_board[i][j] != NQueen.QUEUE_PLACED) current_board[i][j]--;
        
        current_board[row][col] = NQueen.EMPTY;
        printBoard();
    }
}
