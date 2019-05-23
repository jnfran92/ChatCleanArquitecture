package com.speakliz.data.suppliers.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Help class to do operations on regular file directories
 * It is a Singleton object
 *
 */
public class FileManager {

    private static FileManager instance;

    private FileManager() {
    }

    /**
     * Singleton init
     *
     * @return instance
     */
    public static FileManager getInstance(){
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }

    /**
     * Write a file to Disk
     * Note: This is a I/O operation, so execute this in a non UI-Thread
     *
     * @param file  file to write to disk
     * @param fileContent   string file content
     */
    void writeToFile(File file, String fileContent){

        if(!file.exists()){
            try {
                final FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read the content from a file.
     * Note: This is a I/O operation, so execute this in a non UI-Thread
     *
     * @param file The File to read from.
     * @return  A string with the content of the file.
     */
    String readFileContent(File file){
        final StringBuilder fileContentStringBuilder = new StringBuilder();

        if(file.exists()){
            String stringLine;

            try {
                final FileReader fileReader = new FileReader(file);
                final BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((stringLine = bufferedReader.readLine()) != null){
                    fileContentStringBuilder.append(stringLine).append("\n");
                }

                bufferedReader.close();
                fileReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContentStringBuilder.toString();
    }

    /**
     * Check if the file exists.
     *
     * @param file The file to check to.
     * @return  true if it exits, false otherwise.
     */
    boolean exists(File file){return file.exists();}

    /**
     * Warning: Deletes the content of the directory
     * Note: This is a I/O operation, so execute this in a non UI-Thread
     *
     * @param directory The directory wich its content will be deleted.
     * @return true if the directory was deleted, otherwise false. Could be also returned false if
     * the directory doesn't exist or it doesn't have files into.
     */
    boolean clearDirectory(File directory){
        boolean result = false;
        if(directory.exists()){
            for (File file:directory.listFiles()){
                result = file.delete();
            }
        }
        return result;
    }

    /**
     * Write a long value into SharedPreferences file.
     *
     * @param context Android Application context
     * @param preferencesFileName The name of the SharedPreferences file
     * @param key A string representing the name that will be used to reference the long value
     * @param value A long value stored.
     */
    void writeToSharedPreferences(Context context, String preferencesFileName, String key, long value){

        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFileName,
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * Read a long value from Shared Preferences.
     *
     * @param context Android Application Context.
     * @param preferencesFileName A file name representing where data will be get from.
     * @param key A key value that will be used to retrieve the value from the shared preferences.
     * @return A long representing the value retrieved from the shared preferences file.
     * Default value is zero.
     */
    long getFromSharedPreferences(Context context, String preferencesFileName, String key){
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferencesFileName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }



}
