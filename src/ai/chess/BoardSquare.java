/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.chess;

import java.io.Serializable;

/**
 *
 * @author root
 */
enum BoardSquareColor {
    Black, White
};

public class BoardSquare implements Serializable{

    public BoardSquareColor color;
    public boolean ContainPiece;

    public BoardSquare(BoardSquareColor color, boolean Contain) {
        this.color = color;
        this.ContainPiece = Contain;
    }

    public String toString() {
        return "color=" + this.color + ", Contain=" + this.ContainPiece + '}';
    }

}
