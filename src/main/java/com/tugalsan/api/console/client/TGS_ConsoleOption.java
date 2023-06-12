package com.tugalsan.api.console.client;

import com.tugalsan.api.list.client.TGS_ListUtils;
import java.util.List;

public class TGS_ConsoleOption {

    private TGS_ConsoleOption(List<CharSequence> alias) {
        this.alias = alias;
    }
    final public List<CharSequence> alias;
    public CharSequence value = null;

    public static TGS_ConsoleOption of(List<CharSequence> alias) {
        return new TGS_ConsoleOption(alias);
    }

    public static TGS_ConsoleOption of(CharSequence... alias) {
        return new TGS_ConsoleOption(TGS_ListUtils.of(alias));
    }
}
