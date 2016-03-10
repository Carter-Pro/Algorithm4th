package Chapter2;


import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/8.
 */
public class MergeBU extends SortExample{
    private static Comparable[] aux;
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz)
            for (int i = 0; i < N - sz; i += sz + sz)
                merge(a, i, i + sz - 1, Math.min(i + 2 * sz -1, N - 1));
    }
    private static void merge(Comparable[] a, int lo, int mid, int hi)
    {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi ; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi ; k++) {
            if (i > mid)                    a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
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
