package ai.chess;

import java.io.Serializable;

public class Points implements Serializable{
    public int xPos;
    public int yPos;

    public Points(int yPos, int xPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    
}
