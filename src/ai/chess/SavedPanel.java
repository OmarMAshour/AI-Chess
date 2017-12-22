/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.chess;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class SavedPanel implements Serializable {

    private static final long serialVersionUID = 1L;

    ChessBoard board;
    boolean isPlayerWhite;
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj = new Date();
    String dateTime;
    String Type;

    public SavedPanel() {
    }

    public SavedPanel(ChessBoard board, boolean isPlayerWhite, String Type) {
        this.board = board;
        this.isPlayerWhite = isPlayerWhite;
        dateTime = df.format(dateobj);
        this.Type=Type;
    }

}
