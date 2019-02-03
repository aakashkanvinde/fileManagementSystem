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

    public static void createDirectory(String inputDirectory) {
        //get the filepath in File object
        File file = new File(inputDirectory);
        if (file.exists()) {
            System.out.println("The directory is already present");
        } else {
            //use mkdir() and check its return value
            file.mkdir();
            System.out.println("The directory " + inputDirectory + " has been created.");
        }
    }

    public static void renameDirectory(String inputFile, String newDirName) {
        //get filepath in the File Object
        File file = new File(inputFile);
        if (file.isDirectory()) {
            File newName = new File(file.getParent() + "/" + newDirName);
            //use renameTo() and check its return value
            if (file.renameTo(newName)) {
                System.out.println("Directory has been renamed to " + newDirName);
            } else {
                System.out.println("Error in renaming the directory.");
            }
        } else {
            System.out.println("Invalid file path or No such directory exists");
        }
    }

    public static void viewDirectory(String inputDirectory) {
        //get the file in the File object
        File directory = new File(inputDirectory);
        //check if it is a directory 
        if (directory.isDirectory()) {
            //check if the directory has children 
            if (directory.listFiles().length == 0) {
                System.out.println("Sorry, directory is empty!");
            } //separate files and print [Folder] or [File]
            else {
                String listFiles[] = directory.list();
                System.out.println("Following are files in the directory " + inputDirectory + " :\n ");
                for (String listFile : listFiles) {
                    File allFiles = new File(listFile);
                    System.out.println("-> " + allFiles);
                }
            }
        } else {
            System.out.println("Invalid directory or path");
        }
    }
    
    public static void deleteDirectory(String inputDirectory) {
        //get the file path in the File object
        File directory = new File(inputDirectory);
        //check if it is a directory
        if (directory.isDirectory()) {
            //check if the directory has children
            if (directory.listFiles().length == 0) {
                directory.delete();
                System.out.println("Directory is deleted: "
                        + directory.getAbsolutePath());
            } else {
                //ask user whether he wants to delete the directory
                System.out.println("The chosen directory contains few files.");
                viewDirectory(inputDirectory);
                System.out.println("Do you really want to delete the whole directory: \n 1.Yes\n 2.No");
                Scanner userInput = new Scanner(System.in);
                String userRes = userInput.nextLine();
                if (userRes.equalsIgnoreCase("yes") || userRes.equalsIgnoreCase("1")) {
                    //delete files inside the directory one by one
                    deleteFilesInsideDirectory(directory);
                    //delete parent directory
                    directory.delete();
                    if (!directory.exists()) {
                        System.out.println("Directory has been deleted.");
                    } else {
                        System.out.println("Deletion failed");
                    }
                } else if (userRes.equalsIgnoreCase("no") || userRes.equalsIgnoreCase("2")) {
                    System.out.println("Delete directory request cancelled by user.");
                } else {
                    deleteDirectory(inputDirectory);
                }
            }
        } else {
            System.out.println("Invalid path or directory.");
        }
    }
    private static void deleteFilesInsideDirectory(File element) {
        if (element.isDirectory()) {                  //if file inside the main directory is itself a directory 
            if (element.listFiles().length == 0) {    //and is an empty directory, then delete it
                //delete directory
                element.delete();
            } else {
                //delete files one by one
                for (File file : element.listFiles()) {
                    deleteFilesInsideDirectory(file);
                }
            }//E.O. else
        }// E.O. outer IF
        element.delete();
    }
    public static void copyFile(String inputPath, String outputPath) throws IOException{
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath + "/" + inputFile.getName());
        //check if same fileName or directoryName does not exist
        if(inputFile.isFile()){
            InputStream is = new FileInputStream(inputFile);
            OutputStream os = new FileOutputStream(outputFile);
            byte [] buffer = new byte[1024];
            int length;
            //write the streams to OutputStream to copy the data
            while((length = is.read(buffer)) > 0){
                os.write(buffer, 0, length);
            }
        }else
            System.out.println("Please enter a valid file path");
    }
    public static void copy(File inputPath, File outputPath) throws IOException {
        if (inputPath.isDirectory()) {
            copyDirectory(inputPath, outputPath);
        } else {
            copyFile(inputPath, outputPath);
        }
    }
    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String file : source.list()) {
            copy(new File(source, file), new File(target, file));
        }
    }
    private static void copyFile(File source, File target) throws IOException {
        try (
                InputStream in = new FileInputStream(source);
                OutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
    
}
