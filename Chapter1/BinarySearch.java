package Chapter1;
import java.util.Arrays;
/**
 * Created by carter on 16/3/10.
 */
public class BinarySearch {
    public static int indexOf ( int[] a, int key){
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }


    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 6, 8, 10};
        System.out.println(rank(11, a));
    }
}
