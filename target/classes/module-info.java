module com.tugalsan.api.console {
    //requires commons.cli;
    requires com.tugalsan.api.charset;
    requires com.tugalsan.api.runnable;
    requires com.tugalsan.api.input;
    requires com.tugalsan.api.validator;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.coronator;
    requires com.tugalsan.api.tuple;
    requires com.tugalsan.api.callable;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.math;
    requires com.tugalsan.api.stream;
    exports com.tugalsan.api.console.client;
    exports com.tugalsan.api.console.server;
}