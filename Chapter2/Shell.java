package Chapter2;

import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/8.
 */
public class Shell extends SortExample {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1;
        while (h >=1)
        {
            for (int i = h; i < N; i++) {
                for (int j = i; j > h && less(a[j], a[j-h]) ; j -= h) {
                    exch(a, j, j-h);
                }
            }
            h = h / 3;
        }
    }
    public static void main(String[] args) {
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/words3.txt");
        In in = new In(file);
        String[] a = in.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
