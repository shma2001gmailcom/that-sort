package org.misha.xor;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * author: misha
 * date: 3/13/18
 */
public class Xor {
    private static final Logger log = Logger.getLogger(Xor.class);

    public static void main(String... args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(50);
        int result = 0;
        List<Integer> singles = new ArrayList<>();
        for (int i : integers) {
            if (!singles.contains(i)) singles.add(i);
            result ^= i;
        }
        log.debug(result);
        log.debug(Objects.toString(singles));
    }
}
