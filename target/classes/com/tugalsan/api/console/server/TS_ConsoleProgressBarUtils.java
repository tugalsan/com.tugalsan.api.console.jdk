package com.tugalsan.api.console.server;

import java.util.Collection;
import me.tongfei.progressbar.ProgressBar;

public class TS_ConsoleProgressBarUtils {

    public static void test1(Collection collection) {
        // Looping over a collection:
        for (var x : ProgressBar.wrap(collection, "TaskName")) {
            // Progress will be automatically monitored by a progress bar
        }
    }

    public static void test2() {
        var max1 = 100;
        var max2 = 200;
        try (ProgressBar pb1 = new ProgressBar("Job1", max1); ProgressBar pb2 = new ProgressBar("Job2", max2)) {

        }
    }

    public static void test3() {
        // try-with-resource block
        try (ProgressBar pb = new ProgressBar("Test", 100)) { // name, initial max
            // Use ProgressBar("Test", 100, ProgressBarStyle.ASCII) if you want ASCII output style
            for (var n = 1; n < 10; n++) {
                pb.step(); // step by 1
                pb.stepBy(n); // step by n

                pb.stepTo(n); // step directly to n

                pb.maxHint(n);
                // reset the max of this progress bar as n. This may be useful when the program
                // gets new information about the current progress.
                // Can set n to be less than zero: this means that this progress bar would become
                // indefinite: the max would be unknown.
                pb.setExtraMessage("Reading..."); // Set extra message to display at the end of the bar
            }
        } // progress bar stops automatically after completion of try-with-resource block
    }

}
