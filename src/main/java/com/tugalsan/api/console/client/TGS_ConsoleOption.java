package com.tugalsan.api.console.client;

import com.tugalsan.api.list.client.TGS_ListUtils;
import com.tugalsan.api.runnable.client.TGS_RunnableType1;
import java.util.List;

public class TGS_ConsoleOption {

    private TGS_ConsoleOption(TGS_RunnableType1<CharSequence> run, List<CharSequence> alias) {
        this.run = run;
        this.alias = alias;
    }
    final TGS_RunnableType1<CharSequence> run;
    final public List<CharSequence> alias;

    public static TGS_ConsoleOption of(TGS_RunnableType1<CharSequence> run, List<CharSequence> alias) {
        return new TGS_ConsoleOption(run, alias);
    }

    public static TGS_ConsoleOption of(TGS_RunnableType1<CharSequence> run, CharSequence... alias) {
        return of(run, TGS_ListUtils.of(alias));
    }
}
