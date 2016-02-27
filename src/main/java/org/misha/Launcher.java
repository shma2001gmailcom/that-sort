package org.misha;

import org.misha.executors.TaskExecutorExample;
import org.misha.interview.Interview;
import org.misha.latch.rewrite.Csv;
import org.misha.merge.Merge;

import java.lang.reflect.InvocationTargetException;

/**
 * author: misha
 * date: 2/27/16 8:54 PM.
 */
public class Launcher {
    public static void main(String... args) throws InvocationTargetException, IllegalAccessException,
                                                   InterruptedException {
        String[] newStringNull = new String[]{null};
        TaskExecutorExample.main(newStringNull);
        Interview.main(newStringNull);
        Csv.main(newStringNull);
        Merge.main(newStringNull);
        org.misha.proxy.Main.main(newStringNull);
        org.misha.runtime.annotation.Main.main(newStringNull);
        org.misha.semaphore.Main.main(newStringNull);
        org.misha.services.Launcher.main(newStringNull);
        org.misha.sort.App.main(newStringNull);
    }
}
