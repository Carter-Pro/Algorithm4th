package Chapter3;

import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/10.
 */
public class FrequencyCounter {
    public static void main(String[] args) {
        int minlen = 1;
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tinyTale.txt");
        In in = new In(file);

        while (!in.isEmpty())
        {
            String word = in.readString();
            if (word.length() < minlen) continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }

        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        System.out.println(max + " " + st.get(max));
    }
}
