package org.misha.beanutils.xml;

import org.misha.beanutils.tree.Node;
import org.misha.beanutils.tree.Tree;
import org.misha.beanutils.tree.impl.NodeImpl;
import org.misha.beanutils.tree.impl.TreeImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: misha
 * date: 4/30/16
 * time: 12:04 PM
 */
public class Xml {
    private static final String data =
            "<Root><Node0><Node00><Integer>0</Integer></Node00><Node01><String>string01</String></Node01><Node02>null</Node02></Node0><Node1><Node10><list><Node100><String>s0</String></Node100><Node100><String>s1</String></Node100></list></Node10><list><Node11><String>null</String></Node11><Node11><String>null</String></Node11></list></Node1><Date>Sat Apr 30 10:10:41 OMST 2016</Date></Root>";
    private Pattern startTag = Pattern.compile("<([a-zA-Z0-9]+)>");
    private Map<Node<String>, Segment> nodeSegmentMap = new HashMap<Node<String>, Segment>();
    private Segments segments = new Segments();

    public static void main(String... args) {
        Xml xml = new Xml();
        xml.parse(data);

        //xml.print(xml.parent);
    }

    private void parse(String xml) {
        Matcher startTagMatcher = startTag.matcher(xml);
        int closeStart;
        int openEnd;
        while (startTagMatcher.find()) {
            open(startTagMatcher);
            openEnd = startTagMatcher.end();
            Pattern endTag = Pattern.compile("</(" + startTagMatcher.group(1) + ")>");
            Matcher matcher = endTag.matcher(xml);
            while (matcher.find()) {
                if (matcher.start() > openEnd) {
                    close(matcher);
                    closeStart = matcher.start();
                    Segment segment = new Segment(openEnd, closeStart);
                    Node<String> node = new NodeImpl<String>(startTagMatcher.group(1));
                    nodeSegmentMap.put(node, segment);
                    segments.add(segment);
                    break;
                }
            }
        }
        //for(Segment segment)
    }

    private void close(Matcher matcher) {
        System.out.println(matcher.start()+"]\n");
        //System.out.println(matcher.group(1));
    }

    private void open(Matcher startTagMatcher) {
        System.out.print("[" + startTagMatcher.end() +", " );
        //System.out.println(startTagMatcher.group(1));
    }

    public void print(Node<String> node) {
        while (node.getParent() != null) {
            node = node.getParent();
        }
        Tree<String> tree = new TreeImpl<String>(node);
        System.out.println("888888888888888888888888888\n\n" + tree + "\n\n888888888888888888888888888888");
    }

    private boolean isChildOf(Segment a, Segment b) {
        return false;
    }
}
