package org.misha.interview.luxoft;

import java.util.ArrayList;
import java.util.List;

public class MatrixRotation {
    public static final List<List<Integer>> MATRIX = new ArrayList<List<Integer>>() {{
        add(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }});
        add(new ArrayList<Integer>() {{
            add(5);
            add(6);
            add(7);
            add(8);
        }});
        add(new ArrayList<Integer>() {{
            add(9);
            add(10);
            add(11);
            add(12);
        }});
        add(new ArrayList<Integer>() {{
            add(13);
            add(14);
            add(15);
            add(16);
        }});
        add(new ArrayList<Integer>() {{
            add(17);
            add(18);
            add(19);
            add(20);
        }});
    }};
    public static final List<List<Integer>> MATRIX0 = new ArrayList<List<Integer>>() {{
        add(new ArrayList<Integer>() {{
            add(11);
            add(12);
            //            add(13);
            //            add(14);
        }});
        add(new ArrayList<Integer>() {{
            add(21);
            add(22);
            //            add(23);
            //            add(24);
        }});
        //        add(new ArrayList<Integer>() {{
        //            add(31);
        //            add(32);
        //            add(33);
        //            add(34);
        //
        //        }});
        //        add(new ArrayList<Integer>() {{
        //            add(41);
        //            add(42);
        //            add(43);
        //            add(44);
        //
        //        }});
        //        add(new ArrayList<Integer>() {{
        //            add(51);
        //            add(52);
        //            add(53);
        //            add(54);
        //
        //        }});
    }};

    private static List<List<Integer>> rotateOnce(List<List<Integer>> matrix, int start) {
        int temp = matrix.get(start).get(start);
        int tempNext = temp;
        int i = start;
        int rowNum = matrix.size() - start;
        while (i < rowNum) {
            ++i;
            if (i < rowNum) {
                tempNext = matrix.get(i).get(start);
                matrix.get(i).set(start, temp);
            }
            temp = tempNext;
        }
        int j = start;
        int colNum = matrix.get(start).size() - start;
        while (j < colNum) {
            ++j;
            if (j < colNum) {
                tempNext = matrix.get(i - 1).get(j);
                matrix.get(i - 1).set(j, temp);
            }
            temp = tempNext;
        }
        while (i > start) {
            --i;
            if (i > start) {
                tempNext = matrix.get(i - 1).get(j - 1);
                matrix.get(i - 1).set(j - 1, temp);
            }
            temp = tempNext;
        }
        while (j > start) {
            --j;
            if (j > start) {
                tempNext = matrix.get(i).get(j - 1);
                matrix.get(i).set(j - 1, temp);
            }
            temp = tempNext;
        }
        return matrix;
    }

    private static List<List<Integer>> rotate(List<List<Integer>> matrix) {
        int rowNum = matrix.size();
        int colNum = matrix.get(0).size();
        int min = Math.min(rowNum, colNum);
        for (int times = 0; times < min / 2; rotateOnce(matrix, times++)) {
            ;
        }
        return matrix;
    }

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
        for (int i = 0; i < r; rotate(matrix), ++i) {
            ;
        }
    }

    public static String print(List<List<Integer>> matrix) {
        StringBuilder sb = new StringBuilder("-----------------------\n");
        matrix.forEach(row -> {
            row.forEach(i -> sb.append(" ").append(i));
            sb.append('\n');
        });
        return sb.toString();
    }

    public static void main(String[] args) {
        //        2 3 4 8
        //        1 7 11 12
        //        5 6 10 16
        //        9 13 14 15
        System.err.println(print(MATRIX));
        System.err.println(print(rotate(MATRIX)));
    }
}

