package com.tugalsan.api.console.client;

import java.util.ArrayList;
import java.util.List;

public class TGS_ConsoleUtils {

    public static List<String> parseLine(String line) {
        List<String> tokens = new ArrayList();
        var cpSpace = " ".codePointAt(0);
        var cpDoubleQuote = "\"".codePointAt(0);
//        d.ci("parseLine", "cpSpace", cpSpace, "cpDoubleQuote", cpDoubleQuote);
        var sb = new StringBuilder();
        for (var i = 0; i < line.length(); i++) {
            var cpCur = line.codePointAt(i);
            var cpCurIsSpace = cpCur == cpSpace;
            var newTokenStartsWithDoubleQuote = !sb.isEmpty() && sb.codePointAt(0) == cpDoubleQuote;
            var newTokenEndsWithDoubleQuote = sb.length() > 1 && sb.codePointAt(sb.length() - 1) == cpDoubleQuote;
            var newTokenMessageComplete = newTokenStartsWithDoubleQuote && newTokenEndsWithDoubleQuote;
            if (cpCurIsSpace && newTokenMessageComplete) {
                var newToken = sb.toString();
//                d.ci("parseLine", cpCur, "cpCurIsSpace && newTokenMessageComplete", "newToken", newToken);
                tokens.add(newToken);
                sb.setLength(0);
                continue;
            }
            if (cpCurIsSpace && newTokenStartsWithDoubleQuote) {
//                d.ci("parseLine", cpCur, "ccpCurIsSpace && newTokenStartsWithDoubleQuote", "appendCodePoint");
                sb.appendCodePoint(cpCur);
                continue;
            }
            if (cpCurIsSpace) {
                var newToken = sb.toString();
//                d.ci("parseLine", cpCur, "cpCurIsSpace", "newToken", newToken);
                tokens.add(newToken);
                sb.setLength(0);
                continue;
            }
//            d.ci("parseLine", cpCur, "appendCodePoint");
            sb.appendCodePoint(cpCur);
        }
        if (!sb.isEmpty()) {
//            d.ci("parseLine", "adding last token");
            tokens.add(sb.toString());
        }
        return tokens;
    }
}
