module com.tugalsan.api.console {
    requires com.tugalsan.api.charset;
    requires com.tugalsan.api.runnable;
    requires com.tugalsan.api.validator;
    requires com.tugalsan.api.coronator;
    requires com.tugalsan.api.callable;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.math;
    exports com.tugalsan.api.console.client;
    exports com.tugalsan.api.console.server;
}