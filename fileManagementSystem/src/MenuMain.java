package filemanagementsystem;

import java.util.*;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author aakashkanvinde
 */
public class MenuMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Declare a scanner object for getting an input from user
        
        Scanner scanner = new Scanner(System.in);
        String choice;
        String strInputFile = "";
        do {
        //display the selection menu
        System.out.println("\n\n*** Welcome to File Management System ***");
        System.out.println("1. Create File");
        System.out.println("2. Rename File");
        System.out.println("3. Delete File");
        System.out.println("4. Create Directory");
        System.out.println("5. Rename Directory");
        System.out.println("6. Delete Directory");
        System.out.println("7. View Files in a Directory");
        System.out.println("8. Copy Directory");
        System.out.println("9. Copy File");
        System.out.println("10. Encrypt");
        System.out.println("11. Decrypt");
        System.out.println("12. Compress");
        System.out.println("13. Decompress");
        System.out.println("14. Exit");
        System.out.println("*****************************************");
        System.out.println("\n\n Choose an option: ");
        choice = scanner.nextLine();
            if (choice.matches("[0-9]+")) {         //check if the user entered input is bwtween 1 and 14
//            scanner = new Scanner(System.in);
                switch(choice){
                    case "1":
                        //ask user for path where the file should be created
                        System.out.println("Enter the path where you want to create the file: ");
                        //get the path as input in strInputFile variable
                        strInputFile = scanner.nextLine();
                        //ask user for content that should be in the file
                        System.out.println("Enter the content you want in the file: ");
                        //get their content in strContent variable
                        String strContent = scanner.nextLine();
                        fileUtility.createFile(strInputFile,strContent);
                        break;
                    case "2":
                        //ask user for path where the file should be created
                        System.out.println("Enter the path of the file you want to rename: ");
                        //get the path as input in strInputFile variable
                        strInputFile = scanner.nextLine();
                        //ask user for new name of the file should be
                        System.out.println("Enter the new name of the file. Specify the new name with extension( Eg: test.txt): ");
                        //get the new file name in strNewFileName variable
                        String strNewFileName = scanner.nextLine();
                        fileUtility.renameFile(strInputFile,strNewFileName);
                        break;
                    case "3":
                        //ask user for path where the file should be created
                        System.out.println("Enter the path of the file you want to delete: ");
                        //get the path as input in strInputFile variable
                        strInputFile = scanner.nextLine();
                        fileUtility.deleteFile(strInputFile);
                        break;
                    default:
                        //default
                        System.out.println("Invalid choice. Please enter a number between 1 to 14\n");
                }
            } else {
                System.out.println("Please enter a number. ");
            }
        }while (choice != "3");
    }//E.O.Main
}//E.O.class
