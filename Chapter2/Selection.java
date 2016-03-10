package Chapter2;

import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/8.
 */
public class Selection extends SortExample {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
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
