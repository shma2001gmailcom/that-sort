package org.misha.beanutils.xml;

import org.apache.log4j.Logger;
import org.misha.beanutils.Data;
import org.misha.beanutils.tree.Node;
import org.misha.beanutils.tree.Tree;
import org.misha.beanutils.tree.impl.NodeImpl;
import org.misha.beanutils.tree.impl.TreeImpl;
import org.misha.segments.Segment;
import org.misha.segments.Segments;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.substring;

/**
 * author: misha
 * date: 4/30/16
 * time: 12:04 PM
 */
public class Xml {
    private static final Logger log = Logger.getLogger(Xml.class);
    private static final String CLOSE = "</(%s)>";
    private static final Pattern OPEN_PATTERN = compile("<([a-zA-Z0-9]+)>");
    private final Map<Segment, Node<Data>> nodeBySegment = new HashMap<Segment, Node<Data>>();
    private final Segments segments = new Segments();

    /**
     * No recursion
     *
     * @param xml text to parse
     * @return tree containing nodes
     */
    public Tree<Data> parse(final String xml) {
        final Matcher openMatcher = OPEN_PATTERN.matcher(xml);
        while (openMatcher.find()) {
            final String type = readType(openMatcher);
            final Matcher closeMatcher = compile(format(CLOSE, type)).matcher(xml);
            if (closeMatcher.find(openMatcher.end())) {
                final Segment segment = new Segment(openMatcher.end(), closeMatcher.start());
                final Node<Data> node = makeNode(type, substring(xml, segment.left(), segment.right()));//todo ms: problem with enum
                nodeBySegment.put(segment, node);
                segments.add(segment);
            }
        }
        return new TreeImpl<Data>(makeNodes());
    }

    protected String readType(Matcher openMatcher) {
        return openMatcher.group(1);
    }

    private NodeImpl<Data> makeNode(final String type, final String mince) {
        final Data data = new Data();
        data.setType(type);
        if (!OPEN_PATTERN.matcher(mince).find()) {
            data.setValue(mince);
            log.debug(type + "=" + mince);
        } else {
            log.debug(type);
        }
        return new NodeImpl<Data>(data);
    }

    private Node<Data> makeNodes() {
        Node<Data> node = null;
        for (final Map.Entry<Integer, Segment> entry : segments) {
            final Segment segment = entry.getValue();
            final Segment parent = segment.parent(segments);
            if (parent != null) {
                node = nodeBySegment.get(segment);
                nodeBySegment.get(parent).addChild(node);
            }
        }
        if (node != null) {
            while (!node.isRoot()) {
                node = node.getParent();
            }
        }
        return node;
    }
}
