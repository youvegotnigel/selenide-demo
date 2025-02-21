package com.vitalhub.automation.demo;

import com.codeborne.xlstest.XLS;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

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
}
