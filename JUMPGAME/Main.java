import java.util.Scanner;
public class Main
{
  private static int[][] board;
  private static int dimension;
  private static int[][] cache;
  public static void main(String[] args)
  {
	Scanner sc = new Scanner(System.in);
	int test = sc.nextInt();
	for (int i = 0; i < test; i++)
	{
	  int n = sc.nextInt();
	  int[][] board = new int[n][n];
	  for (int row = 0; row < n; row++)
		for (int col = 0; col < n; col++)
		  board[row][col] = sc.nextInt();
	  if (jump(board, n))
		System.out.println("YES");
	  else
		System.out.println("NO");
	}
  }
  
  public static boolean jump(int[][] jumpBoard, int n)
  {
	board = jumpBoard;
	dimension = n;
	cache = new int[n+1][n+1];
	for (int i = 0; i < cache.length; i++)
		for (int j = 0; j < cache[i].length; j++)
			cache[i][j] = -1;	
	return jump(0,0) == 1? true : false;
  }
  
  private static int jump(int row, int col)
  {
	if (row == dimension-1 && col == dimension-1)
		return 1;
	if (row >= dimension || col >= dimension)
		return 0;
	if (cache[row][col] != -1) return cache[row][col];
	
	cache[row][col] = (jump(row, col + board[row][col]) == 1 || jump(row + board[row][col], col) == 1) ? 1 : 0;
	return cache[row][col];
  }
}