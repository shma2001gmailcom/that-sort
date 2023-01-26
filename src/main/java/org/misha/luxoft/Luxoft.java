package org.misha.luxoft;

import java.util.regex.Pattern;

public class Luxoft {
    public static void main(String... a) {
        Pattern pattern = Pattern.compile("[^\\p{L}\\p{Nd}]+");
        String x =
                pattern.matcher("lkjAWce7q3y0rq93ur    ] q-u098@!##$%^&*;," + ";mnbvnxzm,c/?><MNBVCXRTYUKL<ML}|}+_*RE")
                        .replaceAll(" ");
        System.err.println(x);
    }
}
