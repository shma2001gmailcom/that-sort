package org.misha.interview.luxoft;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.min;
import static org.misha.interview.luxoft.MatrixRotation.MATRIX;
import static org.misha.interview.luxoft.MatrixRotation.print;

public class MatrixRotor {
    private static Pos imageOf(int rBound, int cBound, Pos arg, int step) {
        int spent = 0;
        Pos upLeft = arg.upLeft();
        Pos upRight = arg.upRight();
        Pos dnRight = arg.dnRight();
        Pos dnLeft = arg.dnLeft();
        int times = step % (2 * (upRight.col - upLeft.col + 1 + dnRight.row - upRight.row + 1) - 4);
        if (arg.isRight()) {
            if (arg.row - times >= upRight.row) {
                return new Pos(arg.row - times, upRight.col, rBound, cBound);
            }
            spent += upRight.row - arg.row;
            if (upRight.col - (times - spent) >= upLeft.col) {
                return new Pos(upRight.row, upRight.col - (times - spent), rBound, cBound);
            }
            spent += upRight.col - upLeft.col;
            if (upLeft.row + (times - spent) <= dnLeft.row) {
                return new Pos(upLeft.row + (times - spent), upLeft.col, rBound, cBound);
            }
            spent += dnLeft.row - upLeft.row;
            if (dnLeft.col + (times - spent) <= dnRight.col) {
                return new Pos(dnLeft.row, dnLeft.col + (times - spent), rBound, cBound);
            }
            spent += dnRight.col - dnLeft.col;
            return new Pos(dnRight.row - (times - spent), dnRight.col, rBound, cBound);
        }
        if (arg.isLeft()) {
            if (arg.row + times <= dnLeft.row) {
                return new Pos(arg.row + times, arg.col, rBound, cBound);
            }
            spent += arg.row - upLeft.row;
            if (upLeft.col + (times - spent) <= upRight.col) {
                return new Pos(upLeft.row, upLeft.col + (times - spent), rBound, cBound);
            }
            spent += upRight.col - upLeft.col;
            if (upRight.row + (times - spent) <= dnRight.row) {
                return new Pos(upRight.row + (times - spent), upRight.col, rBound, cBound);
            }
            spent += dnRight.row - upRight.row;
            if (dnRight.col + (times - spent) <= dnLeft.col) {
                return new Pos(dnRight.row, dnRight.col + (times - spent), rBound, cBound);
            }
            spent += dnRight.col - dnLeft.col;
            return new Pos(dnLeft.row - (times - spent), dnLeft.col, rBound, cBound);
        }
        if (arg.isDn()) {
            if (arg.col + times <= dnRight.col) {
                return new Pos(dnLeft.row, arg.col + times, rBound, cBound);
            }
            spent += dnRight.col - dnLeft.col;
            if (dnRight.row - (times - spent) >= upRight.row) {
                return new Pos(dnRight.row - (times - spent), dnRight.col, rBound, cBound);
            }
            spent += dnRight.row - upRight.row;
            if (upRight.col - (times - spent) >= upLeft.col) {
                return new Pos(upRight.row, upRight.col - (times - spent), rBound, cBound);
            }
            spent += upRight.col - upLeft.col;
            if (upLeft.row + (times - spent) <= dnLeft.row) {
                return new Pos(upLeft.row + (times - spent), upLeft.col, rBound, cBound);
            }
            spent += dnLeft.row - upLeft.row;
            return new Pos(dnLeft.row, dnLeft.col + (times - spent), rBound, cBound);
        }
        if (arg.col - times >= upLeft.col) {
            return new Pos(arg.row, arg.col - times, rBound, cBound);
        }
        spent += arg.col - upLeft.col;
        if (upLeft.row + (times - spent) <= dnLeft.row) {
            return new Pos(upLeft.row + (times - spent), upLeft.col, rBound, cBound);
        }
        spent += dnLeft.row - upLeft.row;
        if (dnLeft.col + (times - spent) <= dnRight.col) {
            return new Pos(dnLeft.row, dnLeft.col + (times - spent), rBound, cBound);
        }
        spent += dnRight.col - dnLeft.col;
        if (dnRight.row - (times - spent) >= upRight.row) {
            return new Pos(dnRight.row - (times - spent), dnRight.col, rBound, cBound);
        }
        spent += dnRight.row - upRight.row;
        return new Pos(upRight.row, upRight.col - (times - spent), rBound, cBound);
    }

    private static List<List<Integer>> rotate(List<List<Integer>> matrix, int step) {
        int rBound = matrix.size();
        int cBound = matrix.get(0).size();
        List<List<Integer>> rotated = new ArrayList<>();
        for (int i = 0; i < rBound; ++i) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrix.get(0).size(); ++j) {
                row.add(-1);
            }
            rotated.add(row);
        }
        for (int i = 0; i < rBound; ++i) {
            for (int j = 0; j < cBound; ++j) {
                Pos pos = new Pos(i, j, rBound, cBound);
                Pos upRight = pos.upRight();
                Pos dnRight = pos.dnRight();
                Pos upLeft = pos.upLeft();
                int times = step % (2 * (upRight.col - upLeft.col + 1 + dnRight.row - upRight.row + 1) - 4);
                for (int k = 0; k < times; ++k) {
                    pos = imageOf(rBound, cBound, pos, 1);
                }
                rotated.get(pos.row).set(pos.col, matrix.get(i).get(j));
            }
        }
        return rotated;
    }

    public static void main(String[] args) {
        System.err.println(print(rotate(MATRIX, 2)));
        System.err.println(print(rotate(MATRIX, 16)));
    }

    private static class Pos {
        private final int row;
        private final int col;
        private final int rBound;
        private final int cBound;
        int mi;

        private Pos(int row, int col, int rBound, int cBound) {
            this.row = row;
            this.col = col;
            this.rBound = rBound;
            this.cBound = cBound;
            mi = mi(row, col, rBound - row - 1, cBound - col - 1);
        }

        private static int mi(int a, int b, int c, int d) {
            return min(a, min(b, min(c, d)));
        }

        private Pos upLeft() {
            return new Pos(mi, mi, rBound, cBound);
        }

        private Pos upRight() {
            return new Pos(mi, cBound - mi - 1, rBound, cBound);
        }

        private Pos dnRight() {
            return new Pos(rBound - mi - 1, cBound - mi - 1, rBound, cBound);
        }

        private Pos dnLeft() {
            return new Pos(rBound - mi - 1, mi, rBound, cBound);
        }

        private boolean isLeft() {
            return col == upLeft().col && row >= upLeft().row && row < dnLeft().row;
        }

        private boolean isUp() {
            return row == upRight().row && col <= upRight().col && col > upLeft().col;
        }

        private boolean isRight() {
            return col == dnRight().col && row <= dnRight().row && row > upRight().row;
        }

        private boolean isDn() {
            return !isUp() && !isRight() && !isLeft();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pos pos = (Pos) o;
            return row == pos.row && col == pos.col && rBound == pos.rBound && cBound == pos.cBound && mi == pos.mi;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            result = 31 * result + rBound;
            result = 31 * result + cBound;
            result = 31 * result + mi;
            return result;
        }
    }
}

