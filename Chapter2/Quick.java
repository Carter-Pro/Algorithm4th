package Chapter2;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdRandom;

import java.io.File;

/**
 * Created by carter on 16/3/9.
 */
public class Quick extends SortExample{
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo)   return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi;
        Comparable v = a[lo];
        while (true)
        {
            while (less(a[++i], v))
                if (i == hi)
                    break;
            while (less(v, a[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    public static void main(String[] args) {
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/words3.txt");
        In in = new In(file);
        String[] a = in.readAllStrings();
        sort(a);
        if (!isSorted(a)) {
            System.out.println("not sorted");
            return;
        }
        show(a);
    }
}
