package model;

import model.Exception.LogException;
import ui.LogPrinter;


public class FilePrinter implements LogPrinter {
    private static final int LOG_INIT = 1;
    private static int log_num = LOG_INIT;

    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
            System.out.println("\n");
        }
    }
}
