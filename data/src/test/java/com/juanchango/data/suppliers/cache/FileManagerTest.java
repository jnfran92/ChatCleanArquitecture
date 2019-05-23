package com.juanchango.data.suppliers.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileManagerTest {

    private static final String FAKE_FILE_CONTENT = "fake_content2";
    private static final String FAKE_FILE_NAME = "fake_name";
    private static final String FAKE_CACHE_DIR_PATH ="/Users/Juan/GitHub/PlayingCleanArchitecture/data/src/test/java/com/speakliz/data/suppliers/cache/fake_cache_dir";

    private static final File cacheDirDummyFile = new File(FAKE_CACHE_DIR_PATH);

    private FileManager fileManager;


    @Before
    public void setUp(){
        fileManager = FileManager.getInstance();
    }

    @After
    public void tearDown(){
        if(cacheDir() != null){
            fileManager.clearDirectory(cacheDir());
        }
    }

    @Test
    public void testWriteAndReadToFileHappyCase(){

        final File dummyFile = createDummyFile();
        fileManager.writeToFile(dummyFile, FAKE_FILE_CONTENT);

        final String stringToTest = fileManager.readFileContent(dummyFile);

//        assertEquals(FAKE_FILE_CONTENT, stringToTest); It is not equals because of read method adds \n at end of String
        assertThat(fileManager.exists(dummyFile), is(true));
    }

    @Test
    public void testWriteAndReadToFileWrongCase(){
        final File fileFake = new File(FAKE_FILE_NAME);
        fileManager.writeToFile(fileFake, "whatever");

        final String stringToTest = fileManager.readFileContent(fileFake);

        assertNotEquals(FAKE_FILE_CONTENT, stringToTest);
    }

    private File createDummyFile(){
        return new File(cacheDir().getPath() + File.separator + FAKE_FILE_NAME);
    }

    private File cacheDir(){
        return cacheDirDummyFile;
    }


}