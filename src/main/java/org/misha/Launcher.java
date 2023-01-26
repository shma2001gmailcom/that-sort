package org.misha;

import org.misha.beanutils.BeanManipulator;
import org.misha.interview.CounterLauncher;
import org.misha.merge.Merge;
import org.misha.sort.that.Main;
import org.misha.state.PlayerLauncher;
import org.misha.threads.latch.Csv;

/**
 * author: misha
 * date: 2/27/12 8:54 PM.
 */
public class Launcher {
    public static void main(String... args) throws Throwable {
        String[] newStringNull = new String[]{null};
        org.misha.threads.executors.Launcher.main(newStringNull);
        Csv.main(newStringNull);
        Merge.main(newStringNull);
        org.misha.proxy.proxyjdk.Main.main(newStringNull);
        org.misha.runtime.annotation.Main.main(newStringNull);
        org.misha.threads.semaphore.Main.main(newStringNull);
        org.misha.services.Launcher.main(newStringNull);
        Main.main(newStringNull);
        CounterLauncher.main(newStringNull);
        BeanManipulator.main(newStringNull);
        org.misha.threads.singletons.Launcher.main(newStringNull);
        PlayerLauncher.main(newStringNull);
        org.misha.heterogenius.Main.main(newStringNull);
    }
}
