/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.chess;

import Pieces.Piece;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static ai.chess.AIChess.savedPanelsList;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class PanelsReaderWriter implements Serializable{
    
    public static void clear(){
        try{
        FileWriter fwOb = new FileWriter("src/txtfiles/savedPanels.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        }
        catch(Exception e){
            System.out.println("Error Clearing File HighScores.txt");
        }
            	  System.out.println("****************************************************************");

    }

    public static void save() throws IOException {
        clear();
        File file = new File("src/txtfiles/savedPanels.txt");

        /* This logic is to create the file if the
    	 * file is not already present
         */
        if (!file.exists()) {
            file.createNewFile();
        }
//        if (!savedPanelsList.isEmpty()) {
//            FileWriter fwOb = new FileWriter(file, false);
//            PrintWriter pwOb = new PrintWriter(fwOb, false);
//            pwOb.flush();
//            pwOb.close();
//            fwOb.close();
//        }
        //Here true is to append the content to file
        for(SavedPanel sp:savedPanelsList){
            for(Piece piece:sp.board.pieces){
                piece.clearImages();
            }
        }
        FileOutputStream fos= new FileOutputStream(file);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(savedPanelsList);
//         oos.writeObject(savedPanelsList.get(0).board);//.pieces.get(0).availableDes);
//         oos.writeObject(savedPanelsList.get(0).isPlayerWhite);
         oos.close();
         fos.close();
         
         for(SavedPanel sp:savedPanelsList){
            for(Piece piece:sp.board.pieces){
                piece.setImages();
            }
        }
         
//        savedPanelsList.clear();

      
        System.err.println("saved");
    }

    public static void load() throws FileNotFoundException, IOException, ClassNotFoundException {

        File file = new File("src/txtfiles/savedPanels.txt");

         FileInputStream fis = null;  
         ObjectInputStream in = null;  
         try {  
             fis = new FileInputStream("src/txtfiles/savedPanels.txt");  
             in = new ObjectInputStream(fis);  
             savedPanelsList = (ArrayList) in.readObject();  
             in.close();  
         System.err.println("LOADED");
         } catch (IOException ex) {  
             System.out.println("NO Data to be loaded!!");  
         } catch (ClassNotFoundException ex) {  
             ex.printStackTrace();  
         }  
         
         for(SavedPanel sp:savedPanelsList){
            for(Piece piece:sp.board.pieces){
                piece.setImages();
            }
        }
    }
}
