package org.misha.fibo;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.misha.fibo.Fibo.bigInt;

/**
 * author: misha
 * date: 10/27/16
 * time: 9:05 PM
 */
public class FiboTest {
    private static final Logger log = Logger.getLogger(FiboTest.class);
    private final int[] fibos = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};

    @Test
    public void calc() throws Exception {
        for (int i = 1; i < 13; ++i) {
            assertTrue(new Fibo().calc(i) == fibos[i - 1]);
        }
    }

    @Test
    public void fibo() throws Exception {
        for (int i = 1; i < 13; ++i) {
            assertTrue(new Fibo().fibo(i, 1, 1) == fibos[i - 1]);
        }
    }

    @Test
    public void testCalcTime() throws Exception {
        System.out.println(new BigInteger(String.valueOf(new Fibo().calc(46))));
    }

    @Test
    public void testFiboTime() throws Exception {
        int number = 3320;
        BigInteger actual = new BigInteger("3086601960942735306757989666935105993741772865807255718956417907168559351112162859744072545569944776396395826673996069267325475247032124543417988535544412308457485762863826503096865073698814376278426948453060901117734956594798032564800075554643639720227428152536765166489517069714919037068338647220238490376554713697177849431915066975690383803728128284332646908617345096999227818167857581710354920915390232620420375960999226552470551641939702245831315064764129538464865689303453011949710974347181249561397639486843356579248498555923016597462265621552944609469915875137412854218557525343017754922208853722151128739487456037764573120921474138119332042386582026380120696832674285886784220672757365");
        long now = new Date().getTime();
        assertEquals(new Fibo().fibonacci(3320, bigInt(1), bigInt(1)), actual);
        log.info(MessageFormat.format("\n\nfibo({0}) has been calculated at {1}ms.\n\n", number, new Date().getTime() - now));
    }
}