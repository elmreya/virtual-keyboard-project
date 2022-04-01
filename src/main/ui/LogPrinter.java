package ui;

import model.EventLog;
import model.Exception.LogException;

public interface LogPrinter {

    void printLog(EventLog el) throws LogException;

}
