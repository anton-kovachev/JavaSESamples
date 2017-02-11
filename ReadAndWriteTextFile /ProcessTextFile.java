/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package readandwritetextfile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;


/**
 *
 * @author Anton
 */
public final class ProcessTextFile {
    
    private final String fileName;
    
    public ProcessTextFile(String fileName){
        this.fileName = fileName;
    }
    
    public void readFile() {
        
        try {
            Scanner fileReader = new Scanner(new FileReader(fileName));
            
            while(fileReader.hasNextLine() == true){
                
                String nextLine = fileReader.nextLine();
                System.out.println(nextLine);
            }
        } 
        catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    public void appendToFile(String message){
        try {
            
            boolean append = true;
            PrintWriter fileWriter  = new PrintWriter(new FileWriter(fileName, append));
            fileWriter.println(message);
            fileWriter.close();
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) throws NoArgumentsException {

        if(args.length == 0){
            throw new NoArgumentsException();
        }
       
        for(String fileName : args){
            
            ProcessTextFile processFile =  new ProcessTextFile(fileName);
        
            processFile.readFile();
            processFile.appendToFile("This file has been red on" + (new Date()) + " !");
            processFile.readFile();
        }
    }
}

