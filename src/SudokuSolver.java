public class SudokuSolver {
    public static final int GRID_SIZE = 9;
    public static void main(String[] args){
        SudokuSolver controller = new SudokuSolver();
        int[][] board = {
                {7,0,2,0,5,0,6,0,0},
                {0,0,0,0,0,3,0,0,0},
                {1,0,0,0,0,9,5,0,0},
                {8,0,0,0,0,0,0,9,0},
                {0,4,3,0,0,0,7,5,0},
                {0,9,0,0,0,0,0,0,8},
                {0,0,9,7,0,0,0,0,5},
                {0,0,0,2,0,0,0,0,0},
                {0,0,7,0,4,0,2,0,3}
        };
        System.out.println("Board before: ");
        controller.displayBoard(board);
        if(controller.solveBoard(board)){
            System.out.println("\nBoard After:");
            controller.displayBoard(board);
            System.out.println("\nSolved Successfully!");
        }else{
            System.out.println("Unsolvable board!");
        }

    }

    private boolean isNumberInRow(int[][] board, int number, int row){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInCol(int[][] board, int number, int col){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[i][col] == number){
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(int[][] board, int number, int row, int col){
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;
        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for(int j = localBoxCol; j < localBoxCol + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPlacement(int[][] board, int number, int row, int col){
        return !isNumberInRow(board, number, row) && !isNumberInCol(board, number, col) && !isNumberInBox(board, number, row, col);
    }

    private boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 0; col < GRID_SIZE; col++){
                if(board[row][col] == 0){
                    for(int numberToAttempt = 1; numberToAttempt <= GRID_SIZE; numberToAttempt++){
                        if(isValidPlacement(board, numberToAttempt, row, col)){
                            board[row][col] = numberToAttempt;
                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void displayBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for(int col = 0; col < GRID_SIZE; col++){
                if(col % 3 == 0 && col != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

}
