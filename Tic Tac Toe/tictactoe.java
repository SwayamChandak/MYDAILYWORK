	import java.util.*;
public class tictactoe {
    public static int[] board={1,2,3,4,5,6,7,8,9};
    static int turn=0;
    public static void main(String[] args)
    {
        printBoard(board);
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            int result=checkWinner(board);
            if(result!=0)
            {
            	if(result==100)
            	{
            		System.out.println("It's a draw!!!");
            		break;
            	}
            	else if(result==20)
            	{
	                System.out.println("Computer wins the game");
	                break;
            	}
            }
            if(turn==0)
            {
                System.out.println("User select a number to place your move");
                int user=sc.nextInt();
                turn=1;
                board[user-1]=10;
                printBoard(board);
                continue;
            }
            if(turn==1)
            {
                System.out.println("Computer select a number to place its move");
                findBestMove(board);
                turn=0;
                printBoard(board);
                continue;
            }
        }
        sc.close();
    }
    static int checkWinner(int[] board)
    {
        for(int i=0; i<9; i++)
        {
            if(i%3==0 && board[i]==board[i+1] && board[i+1]==board[i+2])
            {
                // System.out.println(board[i]+" wins the game");
                return board[i];
            }
            else if(i<=2 && board[i]==board[i+3] && board[i+3]==board[i+6])
            {
                // System.out.println(board[i]+"wins the game");
                return board[i];
            }
        }
        // Check diagonals
        if (board[0] == board[4] && board[4] == board[8]) {
            return board[0];
        }
        if (board[2] == board[4] && board[4] == board[6]) {
            return board[2];
        }
        if(!isFull(board))
            return 0;
        return 100;
    }
    static boolean isFull(int[] board)
    {
        for(int i=0; i<9; i++)
        {
            if(board[i]<10)
                return false;
        }
        return true;
    }
    static void findBestMove(int[] board) {
        int bestScore=-100;
        int move=0;
        for(int i=0; i<9; i++)
        {
            if(board[i]<10)
            {
            int ai=20;
            board[i]=ai;
            int score=minimax(board, 0, false);
            board[i]=i+1;
            if(score>bestScore)
            {
                bestScore=score;
                move=i;
            }
        }
    }
        board[move]=20;
    }
    public static int minimax(int[] board, int depth, boolean isAITurn)
    {
        int result=checkWinner(board);
        if(result==10)
            return -10;
        else if(result==20)
            return 10;
        else if(result==100)
            return 0;
        
        if(isAITurn)
        {
            int bestScore=Integer.MIN_VALUE;
            for(int i=0; i<9; i++)
            {
                if(board[i]<10)
                {
                    board[i]=20;
                    int score=minimax(board, depth+1, false);
                    board[i]=i+1;
                    if(score>bestScore)
                    {
                        bestScore=score;
                    }
                }   
            }
            
            return bestScore;
        }
        else
        {
            int bestScore=Integer.MAX_VALUE;
            
            for(int i=0; i<9; i++)
            {
                if(board[i]<10)
                {
                    board[i]=10;
                    int score=minimax(board, depth+1, true);
                    board[i]=i+1;
                    if(score<bestScore)
                    {
                        bestScore=score;
                        
                    }
                }
            }   
            
            return bestScore;            
        }
    }

    static void printBoard(int[] board)
    {
//        for(int value: board)
//        {
//            System.out.print(value+" ");
//        }
        for(int i=0; i<board.length; i++)
        {

            if(i%3==0)
                System.out.println();
            if(board[i]==10)
            {
                System.out.print("X"+" ");
            }
            else if(board[i]==20)
            {
                System.out.print("O"+" ");
            }
            else
            {
                System.out.print(board[i]+" ");
            }
            
        }
        System.out.println();
    }
}