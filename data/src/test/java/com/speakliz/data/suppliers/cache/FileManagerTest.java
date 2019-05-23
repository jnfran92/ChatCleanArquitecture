package com.speakliz.data.suppliers.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileManagerTest {

    private static final String FAKE_FILE_CONTENT = "fake_content";
    private static final String FAKE_FILE_PATH = "fake_path";

    private FileManager fileManager;

    @Before
    public void setUp(){
        fileManager = FileManager.getInstance();
    }

    @Test
    public void testWriteAndReadToFileHappyCase(){
        final File fileFake = new File(FAKE_FILE_PATH);

        fileManager.writeToFile(fileFake, FAKE_FILE_CONTENT);

        final String stringToTest = fileManager.readFileContent(fileFake);

        assertEquals(FAKE_FILE_CONTENT, stringToTest);
    }

    @Test
    public void testWriteAndReadToFileWrongCase(){
        final File fileFake = new File(FAKE_FILE_PATH);
        fileManager.writeToFile(fileFake, "whatever");

        final String stringToTest = fileManager.readFileContent(fileFake);

        assertNotEquals(FAKE_FILE_CONTENT, stringToTest);
    }


}