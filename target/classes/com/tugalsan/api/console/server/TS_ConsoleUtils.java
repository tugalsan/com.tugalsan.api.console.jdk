package com.tugalsan.api.console.server;

import com.tugalsan.api.console.client.TGS_ConsoleUtils;
import com.tugalsan.api.input.server.TS_InputKeyboardUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.runnable.client.TGS_RunnableType2;
import com.tugalsan.api.stream.client.TGS_StreamUtils;
import java.util.List;
import java.util.Objects;

public class TS_ConsoleUtils {

    private final static TS_Log d = TS_Log.of(TS_ConsoleUtils.class);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mainLoop(List<String> quitCommands, List<String> clearScreen, TGS_RunnableType2<CharSequence, List<CharSequence>> cmd_restArguments, final CharSequence... initCmdAndArguments) {
        TS_ConsoleUtils.clearScreen();
        if (initCmdAndArguments != null && initCmdAndArguments.length > 0) {
            var fullInitCmd = String.join(" ", initCmdAndArguments);
            var fullInitCmd_ParsedLine = TGS_ConsoleUtils.parseLine(fullInitCmd);
            if (!fullInitCmd_ParsedLine.isEmpty()) {
                var fullInitCmd_ParsedList = TGS_ConsoleUtils.parseList(TGS_StreamUtils.toLst(fullInitCmd_ParsedLine.stream().map(s -> (CharSequence) s)));
                cmd_restArguments.run(fullInitCmd_ParsedList.value0, fullInitCmd_ParsedList.value1);
            }
        }
        while (true) {
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
            var parsedList = TGS_ConsoleUtils.parseList(TGS_StreamUtils.toLst(parsedLine.stream().map(s -> (CharSequence) s)));
            cmd_restArguments.run(parsedList.value0, parsedList.value1);
        }
    }
}
