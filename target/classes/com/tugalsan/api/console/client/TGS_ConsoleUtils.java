package com.tugalsan.api.console.client;

import com.tugalsan.api.stream.client.TGS_StreamUtils;
import com.tugalsan.api.tuple.client.TGS_Tuple2;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TGS_ConsoleUtils {

    public static TGS_Tuple2<String, List<CharSequence>> parseList(List<String> parsedLine) {
        return TGS_Tuple2.of(
                parsedLine.isEmpty() ? null : parsedLine.get(0),
                TGS_StreamUtils.toLst(
                        IntStream.range(0, parsedLine.size())
                                .filter(i -> i != 0)
                                .mapToObj(i -> parsedLine.get(i))
                )
        );
    }

    public static List<String> parseLine(String line) {
        List<String> tokens = new ArrayList();
        var cpSpace = " ".codePointAt(0);
        var cpDoubleQuote = "\"".codePointAt(0);
        var sb = new StringBuilder();
        for (var i = 0; i < line.length(); i++) {
            var cpCur = line.codePointAt(i);
            var cpCurIsSpace = cpCur == cpSpace;
            var newTokenStartsWithDoubleQuote = !sb.isEmpty() && sb.codePointAt(0) == cpDoubleQuote;
            var newTokenEndsWithDoubleQuote = sb.length() > 1 && sb.codePointAt(sb.length() - 1) == cpDoubleQuote;
            var newTokenMessageComplete = newTokenStartsWithDoubleQuote && newTokenEndsWithDoubleQuote;
            if (cpCurIsSpace && newTokenMessageComplete) {
                if (sb.length() == 2) {
                    tokens.add("");
                    continue;
                }
                var newToken = sb.substring(1, sb.length() - 1);
                tokens.add(newToken);
                sb.setLength(0);
                continue;
            }
            if (cpCurIsSpace && newTokenStartsWithDoubleQuote) {
                sb.appendCodePoint(cpCur);
                continue;
            }
            if (cpCurIsSpace) {
                var newToken = sb.toString();
                tokens.add(newToken);
                sb.setLength(0);
                continue;
            }
            sb.appendCodePoint(cpCur);
        }
        if (!sb.isEmpty()) {
            var newTokenStartsWithDoubleQuote = !sb.isEmpty() && sb.codePointAt(0) == cpDoubleQuote;
            var newTokenEndsWithDoubleQuote = sb.length() > 1 && sb.codePointAt(sb.length() - 1) == cpDoubleQuote;
            var newTokenMessageComplete = newTokenStartsWithDoubleQuote && newTokenEndsWithDoubleQuote;
            if (newTokenMessageComplete) {
                var newToken = sb.substring(1, sb.length() - 1);
                tokens.add(newToken);
            } else {
                tokens.add(sb.toString());
            }
        }
        return tokens;
    }
}
