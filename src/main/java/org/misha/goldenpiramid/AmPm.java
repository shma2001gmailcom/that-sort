package org.misha.goldenpiramid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmPm {
    public static void main(String... arg) {
        Pattern pattern = Pattern.compile("([0-9]{2}):([0-9]{2}):([0-9]{2})(AM|PM)");
        Matcher matcher = pattern.matcher("07:05:45PM");
        if (matcher.matches()) {
            String hours = matcher.group(1);
            String minutes = matcher.group(2);
            String secomds = matcher.group(3);
            String amPm = matcher.group(4);
            System.out.println(remZero(hours) + " " + minutes + " " + secomds + " " + amPm);
        }
    }

    public static String remZero(String s) {
        return s.replaceFirst("0", "");
    }
}
