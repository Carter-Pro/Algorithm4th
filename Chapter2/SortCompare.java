package Chapter2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.Stopwatch;

/**
 * Created by carter on 16/3/8.
 */
public class SortCompare {
    public static double time(String alg, Double[] a)
    {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg, int N, int T)
    {
        //use algorithm alg to sort T arrays which length is N.
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++)
        {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform() * 100;
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Merge";
        String alg2 = "Insertion";
        int N = 1000;
        int T = 100;
        double t1 = timeRandomInput(alg1, N, T);
        System.out.println(t1);
        double t2 = timeRandomInput(alg2, N, T);
        System.out.println(t2);
        System.out.printf("%s %.1f times faster than %s\n", alg1, t2 / t1, alg2);
    }
}
