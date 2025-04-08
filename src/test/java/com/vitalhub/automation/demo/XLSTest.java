package com.vitalhub.automation.demo;

import com.codeborne.xlstest.XLS;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.xlstest.XLS.containsRow;
import static com.codeborne.xlstest.XLS.containsText;
import static org.hamcrest.MatcherAssert.assertThat;

public class XLSTest {

    private static final String XLS_FILE_PATH = System.getProperty("user.dir") + "/data-files/";

    @Test
    public void canAssertThatXlsContainsText() throws IOException {
        File xlsFile = new File(XLS_FILE_PATH + "/my-friends.xls");
        XLS spreadsheet = new XLS(xlsFile);
        assertThat(spreadsheet, containsText("Sri Lanka"));
    }


    @Test
    public void canAssertThatXlsContainsRow() {

        File xlsFile = new File(XLS_FILE_PATH + "/hicom-export.xlsx");
        XLS spreadsheet = new XLS(xlsFile);
        //assertThat(spreadsheet, containsRow("HOSP916840", "541 716 1822", "Mr", "Ahlberg", "Blane", "1974-07-18 00:00:00", "50"));
        assertThat(spreadsheet, containsRow("White", "Davi", "2/8/1995  12:00:00 AM", "30"));
    }
}
