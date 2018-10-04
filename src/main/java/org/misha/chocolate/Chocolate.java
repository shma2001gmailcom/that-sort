package org.misha.chocolate;

class Chocolate {

    private Chocolate() {}

    static int equalize(int[] arr) {
        int n = arr.length;
        double[] a = new double[n];
        for (int i = 0; i < n; ++i) {
            a[i] = 0d + arr[i];
        }
        final double[] negatedPreImage = Trans.negate(new Trans(n).timesToReversed(a));
        int opCount = 0;
        for (final double d : Trans.plus(constMin(arr.length, -min(negatedPreImage)), negatedPreImage)) {
            opCount += opCount(d);
        }
        return opCount;
    }

    public static class Trans {
        private final int dim;
        private final int[][] adj;

        private Trans(final int dim) {
            this.dim = dim;
            adj = get(dim);
        }

        private static int[][] get(int dim) {
            int[][] w = new int[dim][dim];
            for (int row = 0; row < dim; ++row) {
                for (int col = 0; col < dim; ++col) {
                    if (col != row) {
                        w[row][col] = 1;
                    } else {
                        w[row][col] = 2 - dim;
                    }
                }
            }
            return w;
        }

        double[] timesToReversed(double[] arr) {
            double[] result = new double[dim];
            for (int i = 0; i < dim; ++i) {
                double resI = 0;
                for (int j = 0; j < dim; ++j) {
                    resI += (arr[j] * (double) adj[i][j]);
                }
                result[i] = resI / (double) (dim - 1);
            }
            return result;
        }

        static double[] plus(double[] left, double[] right) {
            final int length = left.length;
            double[] arr = new double[length];
            for (int i = 0; i < length; i++) {
                arr[i] = left[i] + right[i];
            }
            return arr;
        }

        static double[] negate(double[] data) {
            double[] arr = new double[data.length];
            for (int i = 0; i < data.length; ++i) {
                arr[i] = -data[i];
            }
            return arr;
        }
    }

    private static int opCount(double a) {
        int i = (int) Math.round(a);
        return i / 5 + (i % 5) / 2 + (i % 5) % 2;
    }

    private static double[] constMin(int length, double min) {
        double[] result = new double[length];
        for (int i = 0; i < length; ++i) {
            result[i] = min;
        }
        return result;
    }

    private static double min(double[] x) {
        double result = Double.MIN_VALUE;
        for (final double d : x) {
            if (d < result) {
                result = d;
            }
        }
        return result;
    }
}
