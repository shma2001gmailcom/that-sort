package org.misha.runtime.annotation.objects;

import org.misha.runtime.annotation.MyAble;
import org.misha.runtime.annotation.proxy.UseProxy;

/**
 * author: misha
 * date: 2/21/16 8:18 PM.
 */
public class My implements MyAble {

    @UseProxy
    public void print() {
        System.out.println("my");
    }
}
