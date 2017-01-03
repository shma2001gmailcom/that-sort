package org.misha.segments;

import java.text.MessageFormat;

/**
 * author: misha
 * date: 5/1/16
 * time: 7:14 PM
 * <p>
 * represents a segment of integers
 * among some amount of another such segments.
 * the condition is: there aren't two different
 * segments having common left or right bound. {@link org.misha.segments.Segments}
 */
public final class Segment {
    private final int startIndex;
    private final int endIndex;

    public Segment(final int start, final int end) {
        startIndex = start;
        endIndex = end;
    }

    private boolean sitsIn(final Segment segment) {
        return segment != null && (startIndex > segment.startIndex && endIndex < segment.endIndex);
    }

    public Segment parent(final Segments segments) {
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
        return MessageFormat.format("[{0}, {1}]", startIndex, endIndex);
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
        return startIndex * 31 + endIndex;
    }
}
