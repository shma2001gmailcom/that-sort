package org.misha.runtime.annotation.objects;

import org.apache.log4j.Logger;
import org.misha.runtime.annotation.MyAble;
import org.misha.runtime.annotation.proxy.UseProxy;

/**
 * author: misha
 * date: 2/21/16 8:18 PM.
 */
public class My implements MyAble {
    private static final Logger log = Logger.getLogger(My.class);

    @UseProxy
    public void print() {
        log.info("my");
    }
}
