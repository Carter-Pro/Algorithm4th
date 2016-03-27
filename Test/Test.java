package Test;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by carter on 16/3/22.
 */
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        ArrayList<String> s = lcst(a, b);
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
    public static int[][] getdp(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(0))
                dp[i][0] = 1;
        }

        for (int j = 1; j < s2.length(); j++) {
            if (s1.charAt(0) == s2.charAt(j))
                dp[0][j] = 1;
        }
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = dp[i-1][j-1] + 1;
            }
        }
        return dp;
    }

    public static ArrayList<String> lcst(String s1, String s2)
    {
        ArrayList<String> s = new ArrayList<>();
        if (s1 == null || s2 == null || s1.equals("") || s2.equals(""))
            return s;
        int[][] dp = getdp(s1, s2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (dp[i][j] > 0) {
                    end = i;
                    max = dp[i][j];
                    s.add(s1.substring(end - max + 1, end + 1));
                }
            }
        }
        return s;
    }
}
