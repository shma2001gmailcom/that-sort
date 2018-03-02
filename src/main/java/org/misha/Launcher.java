package org.misha;

import org.misha.beanutils.BeanManipulator;
import org.misha.interview.CounterLauncher;
import org.misha.interview.Interview;
import org.misha.merge.Merge;
import org.misha.state.PlayerLauncher;
import org.misha.threads.latch.Csv;

/**
 * author: misha
 * date: 2/27/16 8:54 PM.
 */
public class Launcher {

    public static void main(String... args) throws Exception {
        String[] newStringNull = new String[]{null};
        org.misha.threads.executors.Launcher.main(newStringNull);
        Interview.main(newStringNull);
        Csv.main(newStringNull);
        Merge.main(newStringNull);
        org.misha.proxy.proxyjdk.Main.main(newStringNull);
        org.misha.runtime.annotation.Main.main(newStringNull);
        org.misha.threads.semaphore.Main.main(newStringNull);
        org.misha.services.Launcher.main(newStringNull);
        org.misha.sort.Main.main(newStringNull);
        CounterLauncher.main(newStringNull);
        BeanManipulator.main(newStringNull);
        org.misha.threads.singletons.Launcher.main(newStringNull);
        PlayerLauncher.main(newStringNull);
    }
}
