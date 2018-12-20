package org.misha.interview;

public class CommonPrefix {
    public static String commonPrefix(String left, String right) {
        if (left == null || right == null) {
            return "";
        }
        int leftLength = left.length();
        int rightLength = right.length();
        int upperBound = leftLength < rightLength ? leftLength : rightLength;
        int pos = 0;
        char[] cLeft = left.toCharArray(), cRight = right.toCharArray();
        while (pos < upperBound && cLeft[pos] == cRight[pos]) {
            ++pos;
        }
        return left.substring(0, pos);
    }

    public static void main(String... args) {
        System.out.println(commonPrefix("abc", "abcyyy"));
    }
}
