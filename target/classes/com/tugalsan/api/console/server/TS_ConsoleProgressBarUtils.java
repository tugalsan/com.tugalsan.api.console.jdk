package com.tugalsan.api.console.server;

import com.tugalsan.api.charset.client.TGS_CharSetUTF8;
import java.util.stream.Stream;

public class TS_ConsoleProgressBarUtils {

    public static void printMsgWithProgressBar(int charSize) {
        var progressLine = new StringBuilder();
        Stream.generate(() -> TGS_CharSetUTF8.UTF8_INCOMPLETE()).limit(charSize).forEach(progressLine::append);
        for (var i = 0; i < charSize; i++) {
            progressLine.replace(i, i + 1, TGS_CharSetUTF8.UTF8_COMPLETE());
            var progressBar = "\r" + progressLine;
            System.out.print(progressBar);
        }
    }
}
