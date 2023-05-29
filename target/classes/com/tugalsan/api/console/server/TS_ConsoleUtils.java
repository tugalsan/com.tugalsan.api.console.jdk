package com.tugalsan.api.console.server;

import com.tugalsan.api.charset.client.TGS_CharSetCast;
import com.tugalsan.api.console.client.TGS_ConsoleUtils;
import com.tugalsan.api.input.server.TS_InputKeyboardUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.runnable.client.TGS_RunnableType1;
import com.tugalsan.api.stream.client.TGS_StreamUtils;
import java.util.Arrays;
import java.util.List;

public class TS_ConsoleUtils {

    private final static TS_Log d = TS_Log.of(TS_ConsoleUtils.class);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mainLoop(List<String> quitCommands, TGS_RunnableType1<List<String>> tokens, String... args) {
        TS_ConsoleUtils.clearScreen();
        var line = "";
        while (!TGS_CharSetCast.equalsLocaleIgnoreCase(line, "q") || !TGS_CharSetCast.equalsLocaleIgnoreCase(line, "quit")) {
            if (args == null) {
                d.cr("main", "newCommand:");
                line = TS_InputKeyboardUtils.readLineFromConsole();
                TS_ConsoleUtils.clearScreen();
                d.cr("main", "givenCommand", line);
                var parsedLine = TGS_ConsoleUtils.parseLine(line);
                tokens.run(parsedLine);
            } else {
                var parsedLine = TGS_StreamUtils.toLst(Arrays.stream(args));
                d.cr("main", "mainCommad:", parsedLine);
                tokens.run(parsedLine);
                args = null;
            }
        }
    }
}
