import java.util.*;
import java.io.*;

/**
 * LAN
 * algospot
 * written by progresivoJS on 2017.11.10
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        StringBuilder str = new StringBuilder();
        
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            int[] x = new int[n];
            int[] y = new int[n];
            Point[] points = new Point[n];
            UF uf = new UF(n);
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            
            for (int i = 0; i < n; i++)
                x[i] = In.nextInt();
            for (int i = 0; i < n; i++)
                y[i] = In.nextInt();
            for (int i = 0; i < n; i++)
                points[i] = new Point(x[i], y[i]);
            
            int edgeCount = 0;
            while (m-- > 0)
            {
                int v = In.nextInt();
                int w = In.nextInt();
                if (!uf.connected(v, w))
                {
                    edgeCount++;
                    uf.union(v, w);
                }
            }
            
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    pq.add(new Edge(i, j, points[i].distTo(points[j])));
            
            double weight = 0;
            while (!pq.isEmpty())
            {
                Edge e = pq.poll();
                if (!uf.connected(e.v, e.w))
                {
                    uf.union(e.v, e.w);
                    weight += e.weight;
                    if (++edgeCount == n - 1) break;
                }
            }
            
            str.append(weight).append('\n');
        }
        
        System.out.println(str);
    }
    
    private static class Point
    {
        int x, y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        
        public double distTo(Point other)
        {
            int dx = this.x - other.x;
            int dy = this.y - other.y;
            
            return Math.sqrt(dx * dx + dy * dy);
        }
    }
    
    private static class Edge implements Comparable<Edge>
    {
        int v, w;
        double weight;
        public Edge(int v, int w, double weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        
        public int compareTo(Edge other)
        {
            double diff = this.weight - other.weight;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else
                return 0;
        }
    }
    
    private static class UF
    {
        int[] parent, size;
        public UF(int n)
        {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++)
            {
                size[i] = 1;
                parent[i] = i;
            }
        }
        
        public boolean connected(int p, int q)
        {
            return find(p) == find(q);
        }
        
        public int find(int p)
        {
            if (parent[p] == p)
                return p;
            return parent[p] = find(parent[p]);
        }
        
        public void union(int p, int q)
        {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            
            if (size[rootP] > size[rootQ])
            {
                size[rootP] += size[rootQ];
                parent[rootQ] = rootP;
            }
            else
            {
                size[rootQ] += size[rootP];
                parent[rootP] = rootQ;
            }
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