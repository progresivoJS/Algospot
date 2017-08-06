import java.util.Scanner;
public class Main
{
  private static int count;
  
  public static String reverseQuad(String s)
  {
	count = 0;
	return reverse(s);
  }
  
  private static String reverse(String s)
  {
	char target = s.charAt(count);
	count ++;
	if (target == 'w' || target == 'b')
	  return Character.toString(target);
	String upperLeft = reverse(s);
	String upperRight = reverse(s);
	String lowerLeft = reverse(s);
	String lowerRight = reverse(s);
	
	return 'x' + lowerLeft + lowerRight + upperLeft + upperRight;
  }
  
  public static void main(String[] args)
  {
	Scanner sc = new Scanner(System.in);
	int cases = sc.nextInt();
	while (cases-- > 0)
	{
	  String quadTree = sc.next();
	  System.out.println(reverseQuad(quadTree));
	}
  }
}
