package org.misha.segments;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.misha.beanutils.xml.Reader.read;

/**
 * author: misha
 * date: 4/10/16
 * time: 8:50 PM
 */
public class SegmentTest {
    private static final int ROOT = 0;
    private static final int NODE0 = 1;
    private static final int NODE00 = 2;
    private static final int INTEGER_AT_NODE00 = 3;
    private static final int NODE01 = 4;
    private static final int STRING_AT_NODE01 = 5;
    private static final int NODE02 = 6;
    private static final int NODE1 = 7;
    private static final int NODE10 = 8;
    private static final int LIST_AT_NODE10 = 9;
    private static final int FIRST_NODE100_AT_LIST_AT_NODE10 = 10;
    private static final int STRING_S0 = 11;
    private static final int SECOND_NODE100_AT_LIST_AT_NODE10 = 12;
    private static final int STRING_S1 = 13;
    private static final int LIST_AT_NODE1 = 14;
    private static final int FIRST_NODE11_AT_LIST_AT_NODE1 = 15;
    private static final int STRING_AT_FIRST_NODE11_AT_LIST_AT_NODE1 = 16;
    private static final int SECOND_NODE11_AT_LIST_AT_NODE1 = 17;
    private static final int STRING_AT_SECOND_NODE11_AT_LIST_AT_NODE1 = 18;
    private static final int DATE = 19;
    private static final String SEGMENTS = "src/test/resources/segments";
    private static final String SEGMENT = "(\\[[0-9]+, [0-9]+\\])";
    private Segments segments = new Segments();
    private List<Segment> segmentList = new ArrayList<Segment>();

    @Before
    public void setUp() throws IOException {
        Matcher matcher = compile(SEGMENT).matcher(read(SEGMENTS));
        while(matcher.find()) {
            final String data = matcher.group();
            final String[] parts = split(data, ", ");
            final int leftBound = Integer.parseInt(removeStart(parts[0], "["));
            final int rightBound = Integer.parseInt(removeEnd(parts[1], "]"));
            final Segment segment = new Segment(leftBound, rightBound);
            segmentList.add(segment);
            segments.add(segment);
        }
    }



    @Test
    public void testSitsIn() throws Exception {
        assertParent(NODE0, ROOT);
        assertParent(NODE1, ROOT);
        assertParent(DATE, ROOT);
        assertParent(NODE00, NODE0);
        assertParent(NODE01, NODE0);
        assertParent(NODE02, NODE0);
        assertParent(NODE10, NODE1);
        assertParent(FIRST_NODE11_AT_LIST_AT_NODE1, LIST_AT_NODE1);
        assertParent(SECOND_NODE11_AT_LIST_AT_NODE1, LIST_AT_NODE1);
        assertParent(FIRST_NODE100_AT_LIST_AT_NODE10, LIST_AT_NODE10);
        assertParent(SECOND_NODE100_AT_LIST_AT_NODE10, LIST_AT_NODE10);
        assertParent(INTEGER_AT_NODE00, NODE00);
        assertParent(STRING_AT_NODE01, NODE01);
        assertParent(NODE10, NODE1);
        assertParent(LIST_AT_NODE1, NODE1);
        assertParent(STRING_S0, FIRST_NODE100_AT_LIST_AT_NODE10);
        assertParent(STRING_S1, SECOND_NODE100_AT_LIST_AT_NODE10);
        assertParent(STRING_AT_FIRST_NODE11_AT_LIST_AT_NODE1, FIRST_NODE11_AT_LIST_AT_NODE1);
        assertParent(STRING_AT_SECOND_NODE11_AT_LIST_AT_NODE1, SECOND_NODE11_AT_LIST_AT_NODE1);
        assertNull(segmentList.get(0).parent(segments));
    }

    private void assertParent(int child, int parent) {
        assertTrue(segmentList.get(child).parent(segments).equals(segmentList.get(parent)));
    }
}