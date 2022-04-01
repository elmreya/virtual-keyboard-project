package model;

import model.Exception.LogException;
import ui.LogPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements LogPrinter {
    private static final int LOG_INIT = 1;
    private static int log_num = LOG_INIT;


    /**
     * Constructor creates file.  Each log file has a sequential log number
     * starting at LOG_INIT for each run of the application.
     *
     * @throws LogException when any kind of input/output error occurs
     */

    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
            System.out.println("\n");
        }
    }
}
