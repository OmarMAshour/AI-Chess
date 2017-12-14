/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.chess;

import Pieces.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author omarashour
 */
public class SingleBoardPanel extends javax.swing.JPanel {

    /**
     * Creates new form SingleBoardPanel
     */
    public ChessBoard chessBoard;

    private Timer piecesDrawingTimer;
    private boolean isPlayerWhite;
    private boolean playerSelectedOneOfHisPieces = false;
    private int selectedPieceIndex = -1;

    public SingleBoardPanel(ChessBoard chessBoard, boolean isPlayerWhite) {
        initComponents();
        this.chessBoard = chessBoard;
        this.isPlayerWhite = isPlayerWhite;
        piecesDrawingTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                System.out.println(chessBoard.pieces.size());
            }
        });
        piecesDrawingTimer.start();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                int clickedX = e.getX();
                int clickedY = e.getY();
                
                int pieceX = (clickedX - 10) / 60;
                int pieceY = (clickedY - 10) / 60;

                System.out.println("Mouse Clicked!!!!! x= "+pieceX+" y= "+pieceY);
                
                //by this whenever the player choose one of his own pieces he will get the available options to move within them
                for (int i = 0; i < chessBoard.pieces.size(); i++) {
                    if (isPlayerWhite) {
                        if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.White) {
                            playerSelectedOneOfHisPieces = true;
                            selectedPieceIndex = i;
                            //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                            return;
                        }
                    } else if (!isPlayerWhite) {
                        if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.Black) {
                            playerSelectedOneOfHisPieces = true;
                            selectedPieceIndex = i;
                            //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                            return;
                        }
                    }
                }

//                //in case that the playe just clicked on a piece
//                if (selectedPieceIndex != -1 && !playerSelectedOneOfHisPieces) {
//                    playerSelectedOneOfHisPieces = true;
//
//                    return;
//                }
                //in case that the player clicked on a piece before and now is taking the next action
                if (selectedPieceIndex != -1 && playerSelectedOneOfHisPieces) {
                    try {
                        if (chessBoard.pieces.get(selectedPieceIndex).move(pieceX, pieceY, chessBoard)) {
//                            chessBoard.Squares[chessBoard.pieces.get(selectedPieceIndex).yPos][chessBoard.pieces.get(selectedPieceIndex).xPos].ContainPiece = false;
                            chessBoard.pieces.get(selectedPieceIndex).xPos = pieceX;
                            chessBoard.pieces.get(selectedPieceIndex).yPos = pieceY;
//                            chessBoard.Squares[chessBoard.pieces.get(selectedPieceIndex).yPos][chessBoard.pieces.get(selectedPieceIndex).xPos].ContainPiece = true;
                            selectedPieceIndex = -1;
                            playerSelectedOneOfHisPieces = false;
                        }

                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    } finally {
                        return;
                    }
                }

            }

        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
//        for (Piece piece : chessBoard.pieces) {
//            if (piece.color == Piece.Black) {
//                g.drawImage(piece.blackImage, (piece.xPos * 60) + 10, (piece.yPos * 60) + 10, 60, 60, null);
//            } else if (piece.color == Piece.White) {
//                g.drawImage(piece.whiteImage, (piece.xPos * 60) + 10, (piece.yPos * 60) + 10, 60, 60, null);
//            }
//        }
        
        
        for(int i =0;i<chessBoard.pieces.size();i++){
            if (chessBoard.pieces.get(i).color == PieceColor.Black) {
                g.drawImage(chessBoard.pieces.get(i).blackImage, (chessBoard.pieces.get(i).xPos * 60) + 10, (chessBoard.pieces.get(i).yPos * 60) + 10, 60, 60, null);
            } else if (chessBoard.pieces.get(i).color == PieceColor.White) {
                g.drawImage(chessBoard.pieces.get(i).whiteImage, (chessBoard.pieces.get(i).xPos * 60) + 10, (chessBoard.pieces.get(i).yPos * 60) + 10, 60, 60, null);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardLabel = new javax.swing.JLabel();

        boardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/board.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(boardLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(boardLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boardLabel;
    // End of variables declaration//GEN-END:variables
}