package Test;

import java.util.ArrayList;

/**
 * Created by carter on 16/3/22.
 */
public class Collection {
    class Set {
        int low;
        int high;

        public Set() {
        }

        public Set(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public ArrayList<Set> interSection(ArrayList<Set> A, ArrayList<Set> B) {
        ArrayList<Set> C = new ArrayList<Set>();
        for (Set a : A) {
            for (Set b : B) {
                // 将A中每个元素与B中每个元素求交集，如果所得的集合上界大于下界，则添加到输出集合中
                Set c = new Set();
                c.low = (a.low > b.low) ? a.low : b.low;
                c.high = (a.high < b.high) ? a.high : b.high;
                if (c.low < c.high) {
                    C.add(c);
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        Collection collection = new Collection();
        Collection.Set t;
        ArrayList<Set> A = new ArrayList<Set>(); // 初始化输入数组A
        t = collection.new Set(4, 8);
        A.add(t);
        t = collection.new Set(9, 13);
        A.add(t);
        ArrayList<Set> B = new ArrayList<Set>(); // 初始化输入数组B
        t = collection.new Set(6, 12);
        B.add(t);
        ArrayList<Set> C = collection.interSection(A, B); // 数组求交
        for (Set s : C) {
            System.out.println("<" + s.low + "," + s.high + ">");
        }
    }
}
