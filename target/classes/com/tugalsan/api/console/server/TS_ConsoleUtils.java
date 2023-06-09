package com.tugalsan.api.console.server;

import com.tugalsan.api.console.client.TGS_ConsoleUtils;
import com.tugalsan.api.input.server.TS_InputKeyboardUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.runnable.client.TGS_RunnableType1;
import com.tugalsan.api.stream.client.TGS_StreamUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TS_ConsoleUtils {

    private final static TS_Log d = TS_Log.of(TS_ConsoleUtils.class);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mainLoop(List<String> quitCommands, List<String> clearScreen, TGS_RunnableType1<List<String>> tokens, String... args) {
        TS_ConsoleUtils.clearScreen();
        while (true) {
            if (args == null) {
                d.cr("main", "newCommand:");
                var line = TS_InputKeyboardUtils.readLineFromConsole().trim();
                if (quitCommands.stream().filter(cmd -> Objects.equals(cmd, line)).findAny().isPresent()) {
                    return;
                }
                TS_ConsoleUtils.clearScreen();
                if (clearScreen.stream().filter(cmd -> Objects.equals(cmd, line)).findAny().isPresent()) {
                    continue;
                }
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
