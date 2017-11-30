import java.util.*;
import java.io.*;

/**
 * written by progresivoJS
 */
public class Main
{
    private static int n, lo, hi;

    private static int[] factors;
    private static int TM = 1000 * 1000 * 10;

    public static int solve(int n, int lo, int hi)
    {
        Main.n = n;
        Main.lo = lo;
        Main.hi = hi;

        int count = 0;
        for (int i = lo; i <= hi; i++)
            if (factors[i] == n)
                count++;

        return count;
    }

    private static void getFactorsBrute()
    {
        factors = new int[TM + 1];
        for (int i = 1; i <= TM; i++)
            for (int j = i; j <= TM; j+= i)
                factors[j] ++;
    }

    public static void main(String[] args)
    {
        In.init();

        int test = In.nextInt();

        getFactorsBrute();

        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            int lo = In.nextInt();
            int hi = In.nextInt();

            System.out.println(solve(n, lo, hi));
        }
    }

    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;

        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
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
