module com.tugalsan.api.console {
    //requires commons.cli;
    requires com.tugalsan.api.charset;
    requires com.tugalsan.api.input;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.tuple;
    requires com.tugalsan.api.function;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.math;
    requires com.tugalsan.api.stream;
    exports com.tugalsan.api.console.jdk.client;
}