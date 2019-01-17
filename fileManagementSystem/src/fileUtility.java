package filemanagementsystem;

import java.io.*;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author aakashkanvinde
 */

public class fileUtility {
    //constant variable declaration. put your own paths here
    private static final String zipFolder = "/Users/aakashkanvinde/NetBeansProjects/File_management_system/zipFolder/";

    private static final String extractFolder = "/Users/aakashkanvinde/NetBeansProjects/File_management_system/extractedFiles/";

    private static final String encryptedFileLocation = "/Users/aakashkanvinde/NetBeansProjects/File_management_system/encryptedFiles/";

    private static final String decryptedFileLocation = "/Users/aakashkanvinde/NetBeansProjects/File_management_system/decryptedFiles/";

    //file operations

    public static void createFile(String inputFile, String content) throws IOException {
        try{
            File file = new File(inputFile);
            // if file does not exists, create a new file
            if(!file.exists()){
                file.createNewFile();
            }
            
            //get the file in File Writer
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            
            //initialize the buffer writer with the file
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            
            //write content in the file
            bufferWriter.write(content);
            
            //flush and close the buffer
            bufferWriter.flush();
            bufferWriter.close();
            System.out.println("File "+ inputFile + " has been successfully created!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void renameFile(String inputFile, String newFileName){
        //get the path in the File object
        File file = new File(inputFile);
        
        //validate the file
        if(file.isFile()){
            String fileDirectory = file.getParent();
            File newName = new File(fileDirectory + "/" + newFileName);
            
            //renaming the file and validate it
            if(file.renameTo(newName))
                System.out.println("File has been renamed to "+ newFileName);
            else
                System.out.println("Error in renaming the File.");
        }else {
            System.out.println("Invalid File Path or file of that name does not exists");
        }
    }
    
    public static void deleteFile(String inputFile){
        //get the file path in the file object
        File file = new File(inputFile);
        //validate the file
        if(file.isFile()){
            try{
                if(file.delete())
                    System.out.println("File "+inputFile+" has been deleted.");
                else
                    System.out.println("Error in deleting the file.");
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Invalid file path.");
        }
    }
}
