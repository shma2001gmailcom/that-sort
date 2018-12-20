package org.misha.interview.glue;

import java.util.ArrayList;
import java.util.List;

/**
 * glues the whole item out of a head, a body and a tail which have common id.
 */
class Fitter {
    private List<Item> fit(List<Head> heads, List<Tail> tails, List<Body> bodies) {
        List<Item> result = new ArrayList<>();
        for (Head head : heads) {
            for (Tail tail : tails) {
                for (Body body : bodies) {
                    final long id = head.getId();
                    if (id == tail.getId() && id == body.getId()) {
                        result.add(new Item(id, head, tail, body));
                    }
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        List<Head> heads = new ArrayList<>();
        List<Body> bodies = new ArrayList<>();
        List<Tail> tails = new ArrayList<>();
        for (int i = 0; i < 1000; ++i) {
            heads.add(new Head(i% 17));
            bodies.add(new Body(i % 13));
            tails.add(new Tail(i % 37));
        }
        System.out.println(new Fitter().fit(heads, tails, bodies));
    }
}

class Body {
    private final long id;

    Body(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

class Head {
    private final long id;

    Head(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

class Tail {
    private final long id;

    Tail(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

class Item {
    private final long id;
    private final Head head;
    private final Tail tail;
    private final Body body;

    Item(final long id, final Head head, final Tail tail, final Body body) {
        this.id = id;
        this.head = head;
        this.tail = tail;
        this.body = body;
    }
    //getters
}


