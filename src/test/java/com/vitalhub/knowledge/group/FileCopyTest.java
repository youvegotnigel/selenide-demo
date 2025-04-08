package com.vitalhub.knowledge.group;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FileCopyTest {



    @Test
    public void fileTest(){

        File sourceFolder = new File("C:/dev/tunnel-azure-self-hosted/_work/5/s/target/cucumber-reports/1743160979979");
        File destinationFolder = new File("C:/dev/tunnel-azure-self-hosted/_work/5/s/target/cucumber-reports/latest/");

        try {

            // Step 1: Clean destination folder if it exists
            if (destinationFolder.exists()) {
                FileUtils.deleteDirectory(destinationFolder);
                System.out.println("Destination folder cleaned.");
            }


            FileUtils.copyDirectory(sourceFolder, destinationFolder);
            System.out.println("Folder copied successfully!");
        } catch (IOException e) {
            System.err.println("Error copying folder: " + e.getMessage());
        }


    }
}
