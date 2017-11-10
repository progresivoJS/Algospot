import java.util.*;
import java.io.*;

/**
 * TICTACTOE
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    private static int[][] board;
    private static int[] cache;
    /**
     * board[i][j]
     * '.' = 0
     * 'x' = 1
     * 'o' = 2
     */
    public static void solve(int[][] board)
    {
        Main.board = board;
        
        cache = new int[27 * 27 * 27 + 1];
        Arrays.fill(cache, -2);
        
        int result = canWin(board, 1);
        System.out.println(result);
    }
    
    /**
     * board상태일 때,
     * turn 이 질수밖에 없으면 -1, 비길수 있으면 0, 이길수 있으면 1.
     */
    private static int canWin(int[][] board, int turn)
    {
        // 이전 사람이 이겨버렸는가 ?
        if (isFinished(board, turn == 1 ? 2 : 1))
            return -1;
        
        int cacheIndex = bijection(board);
        if (cache[cacheIndex] != -2)
            return cache[cacheIndex];
            
        int result = 2;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] == 0)
                {
                    board[i][j] = turn;
                    result = Math.min(result, canWin(board, turn == 1 ? 2 : 1));
                    board[i][j] = 0;
                }
            }
        
        if (result == 2 || result == 0)
            return cache[cacheIndex] = 0;
        
        return cache[cacheIndex] = -result;
    }
    
    /**
     * board일 때, turn이 이겼습니까?
     */
    private static boolean isFinished(int[][] board, int turn)
    {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == turn && board[i][1] == turn && board[i][2] == turn)
                return true;
        
        for (int i = 0; i < 3; i++)
            if (board[0][i] == turn && board[1][i] == turn && board[2][i] == turn)
                return true;
        
        if (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn)
            return true;
        if (board[2][0] == turn && board[1][1] == turn && board[0][2] == turn)
            return true;
        
        return false;
    }
    
    private static int bijection(int[][] board)
    {
        int index = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
            {
                index *= 3;
                index += board[i][j];
            }
        return index;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int[][] board = new int[3][3];
            for (int j = 0; j < 3; j++)
            {
                String line = In.next();
                for (int k = 0; k < 3; k++)
                {
                    if (line.charAt(k) == '.')
                        board[j][k] = 0;
                    else if (line.charAt(k) == 'x')
                        board[j][k] = 1;
                    else
                        board[j][k] = 2;
                }
            }
            solve(board);
        }
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    
        public static String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}