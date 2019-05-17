package com.speakliz.data.suppliers.cache;

/**
 * Help class to do operations on regular file directories
 *
 * It is Singleton
 */
public class FileManager {

    FileManager instance;

    private FileManager() {
    }

    public FileManager getInstance(){

        if(instance == null){
            instance = new FileManager();
        }

        return instance;
    }




}
