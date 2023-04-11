package com.tugalsan.api.console.server;

import com.tugalsan.api.charset.client.TGS_CharSetUTF8;
import com.tugalsan.api.executable.client.TGS_ExecutableType1;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TS_ConsoleProgressBar {

    private TS_ConsoleProgressBar(int size) {
        this.current = 0;
        this.size = size;
        this.progressLine = new StringBuilder();
        Stream.generate(() -> TGS_CharSetUTF8.UTF8_INCOMPLETE()).limit(size).forEach(progressLine::append);
    }
    final private int size;
    private int current;
    final private StringBuilder progressLine;

    public int size() {
        return size;
    }

    public static TS_ConsoleProgressBar of(int size) {
        return new TS_ConsoleProgressBar(size);
    }

    public int getCurrent() {
        return current;
    }

    public TS_ConsoleProgressBar setCurrent(int newCurret) {
        current = newCurret;
        progressLine.replace(current, current + 1, TGS_CharSetUTF8.UTF8_COMPLETE());
        return this;
    }

    public TS_ConsoleProgressBar showCurrent() {
        var progressBar = "\r" + progressLine.toString();
        System.out.print(progressBar);
        return this;
    }

    public void forEach(TGS_ExecutableType1<TS_ConsoleProgressBar> progress) {
        IntStream.range(0, size).forEach(i -> {
            progress.execute(setCurrent(i));
        });
    }
}
