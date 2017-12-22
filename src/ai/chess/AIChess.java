package ai.chess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.JPanel;

public class AIChess {
    public static boolean isPlayerWhite = false;
    public static transient ArrayList<SavedPanel> savedPanelsList= new ArrayList<SavedPanel>();
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        // TODO code application logic here
        PanelsReaderWriter.load();
        GameIntro GI = new GameIntro();
        GI.setVisible(true);

    }
    
}
