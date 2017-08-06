import java.util.Scanner;
public class Main
{
	private static boolean[][] isBlack;
	private static int row, col;
	private static final int[][][] coverType = {
		{{0,0},{0,1},{1,1}},
		{{0,0},{1,0},{1,1}},
		{{0,0},{1,0},{0,1}},
		{{0,0},{1,0},{1,-1}}
	};
	public static int countCover(boolean[][] board)
	{
		isBlack = board;
		row = board.length;
		col = board[0].length;
		return countBoardCover(board);
	}
	private static int countBoardCover(boolean[][] isBlack)
	{
		// find free x, y
		int smallestX = -1;
		int smallestY = -1;
		for (int i = 0; i < isBlack.length; i++)
		{
			for (int j = 0; j < isBlack[i].length; j++)
				if (!isBlack[i][j])
				{
					smallestX = i;
					smallestY = j;
					break;
				}
			
			if (smallestX != -1) break;
		}
		
		// base case
		if (smallestX == -1 && smallestY == -1)
			return 1;
		
		int result = 0;
		for (int type = 0; type < 4; type++)
		{
			int[] newX = new int[3];
			int[] newY = new int[3];
			for (int i = 0; i < 3; i++)
			{
				newX[i] = smallestX + coverType[type][i][0];
				newY[i] = smallestY + coverType[type][i][1];
			}
			
			if (isValid(newX, newY) && isWhites(newX, newY))
			{
				for (int i = 0; i < 3; i++)
					isBlack[newX[i]][newY[i]] = true;
				result += countBoardCover(isBlack);
				for (int i = 0; i < 3; i++)
					isBlack[newX[i]][newY[i]] = false;
			}
		}
		
		return result;
	}
	
	private static boolean isValid(int[] newX,int[] newY)
	{
		boolean isValid = true;
		for (int i = 0; i < newX.length; i++)
		{
			if (newX[i] >= row || newX[i] < 0)
				isValid = false;
			
			if (newY[i] >= col || newY[i] < 0)
				isValid = false;
		}
		
		return isValid;
	}
	
	private static boolean isWhites(int[] newX, int[] newY)
	{
		boolean isWhites = true;
		for (int i = 0; i < newX.length; i++)
		{
			if (isBlack[newX[i]][newY[i]])
				isWhites = false;
		}
		return isWhites;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		
		
		for (int i = 0; i < test; i++)
		{
			int h = sc.nextInt();
			int w = sc.nextInt();
			boolean[][] isBlack = new boolean[h][w];
			sc.nextLine();
			for (int j = 0; j < h; j++)
			{
				String str = sc.nextLine();
				for (int k = 0; k < str.length(); k++)
				{
					if (str.charAt(k) == '#')
						isBlack[j][k] = true;
					else if (str.charAt(k) == '.')
						isBlack[j][k] = false;
				}
			}
			
			System.out.println(countCover(isBlack));
		}
	}
}