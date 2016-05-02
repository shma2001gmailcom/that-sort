package org.misha.beanutils.xml;

/**
 * author: misha
 * date: 5/1/16
 * time: 7:14 PM
 */
public class Segment {
    private final int startIndex;
    private final int endIndex;

    public Segment(final int start, final int end) {
        startIndex = start;
        endIndex = end;
    }

    public boolean sitsIn(final Segment segment) {
        return startIndex > segment.startIndex && endIndex < segment.endIndex;
    }

    public Segment findParentAmong(Segments segments) {
        Segment segment = nextSegment(segments, this, 0);
        int counter = 0;
        while (!this.sitsIn(segment)) {
            segment = nextSegment(segments, segment, ++counter);
        }
        return segment;
    }

    private Segment nextSegment(Segments segments, Segment segment, int counter) {
        return counter % 2 == 0 ? segments.nexLeft(segment) : segments.nexRight(segment);
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
