package Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by carter on 16/3/22.
 */
public class Example
{
    public static boolean find(String input, String[] dic) {
        int n = input.length();
        int count = 0;
        int index = 0;
        for (String s : dic) {
            for (int i = 0; i < n; i++) {
                if (input.charAt(i) != s.charAt(i)) {
                    index = i;
                    count++;
                    break;
                }
            }
            if (count == 0) return false;
            else if (count == 1){
                if (delete(input, s, index))    return true;
                if (add(input, s, index))   return true;
                if (change(input, s, index))    return true;
            }
        }
        return false;
    }
    public static boolean delete(String input, String s, int index) {
        String tmp = input.substring(0, index) + input.substring(index+1);
        if (tmp.equals(s))
            return true;
        else
            return false;
    }

    public static boolean add(String input, String s, int index) {
        for (int a = 'a'; a <= 'z'; a++) {
            String tmp = input.substring(0, index + 1) + Integer.toString(a) + input.substring(index+1);
            if (tmp.equals(s))
                return true;
        }
        return false;
    }

    public static boolean change(String input, String s, int index) {
        String tmp = input.substring(0, index + 1) + s.substring(index+1, index+2) + input.substring(index+1);
        if (tmp.equals(s))
            return true;
        else
            return false;

    }

    public static void main(String[] args) {
        String A;
        String B;
        Scanner scanner = new Scanner(System.in);
        A = scanner.nextLine();
        B = scanner.nextLine();
        String[] dic = A.split(" ");
        String[] input = B.split(" ");
        Arrays.sort(dic);
        Arrays.sort(input);
        int index = 0;
        for (String s : input) {
            if (find(s, dic))
                System.out.println(s + " " + dic[index]);
            index++;
        }
    }
}


