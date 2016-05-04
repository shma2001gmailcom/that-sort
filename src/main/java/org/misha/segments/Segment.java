package org.misha.segments;

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
        return segment != null && (startIndex > segment.startIndex && endIndex < segment.endIndex);
    }

    public Segment parent(Segments segments) {
        Segment segment = this;
        while (!this.sitsIn(segment)) {
            segment = segments.nexLeft(segment);
            if (segment == null) {
                break;
            }
        }
        return segment;
    }

    public int left() {
        return startIndex;
    }

    public int right() {
        return endIndex;
    }

    @Override
    public String toString() {
        return "[" + startIndex + ", " + endIndex + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Segment segment = (Segment) o;
        return endIndex == segment.endIndex && startIndex == segment.startIndex;
    }

    @Override
    public int hashCode() {
        int result = startIndex;
        result = 31 * result + endIndex;
        return result;
    }
}
