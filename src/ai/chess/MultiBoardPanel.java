/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.chess;

import Pieces.*;
import static ai.chess.AIChess.verboseStrings;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 *
 * @author omarashour
 */
public class MultiBoardPanel extends JPanel implements Serializable {

    /**
     * Creates new form BoardPanel
     */
    public ChessBoard chessBoard;

    private Timer piecesDrawingTimer;

    private boolean playerSelectedOneOfHisPieces = false;
    private int selectedPieceIndex = -1;
    public boolean whitePlayerTurn;
    public String className = "Multi";
    private ArrayList<Points> availableMoves = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public MultiBoardPanel(ChessBoard chessBoard) {
        initComponents();
        this.chessBoard = chessBoard;
        this.whitePlayerTurn = true;
        piecesDrawingTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();

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

                verboseStrings.add("Mouse Clicked!!!!! x= " + pieceX + " y= " + pieceY);

                //by this whenever the player choose one of his own pieces he will get the available options to move within them
                for (int i = 0; i < chessBoard.pieces.size(); i++) {
                    if (whitePlayerTurn) {

                        try {
                            if (chessBoard.checkMate(PieceColor.White)) {
                                JOptionPane.showMessageDialog(null, PieceColor.Black + " Wins");
                                System.exit(0);
                            }
                            
                            if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.White) {
                                playerSelectedOneOfHisPieces = true;
                                selectedPieceIndex = i;
                                //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                                availableMoves.clear();
                                chessBoard.pieces.get(i).CalculateAllPossibleMoves(chessBoard);
                                availableMoves = chessBoard.pieces.get(i).availableDes;
                                return;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(MultiBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (!whitePlayerTurn) {
                        try {
                            if (chessBoard.checkMate(PieceColor.Black)) {
                                JOptionPane.showMessageDialog(null, PieceColor.White + " Wins");
                                System.exit(0);
                            }
                            
                            if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.Black) {
                                playerSelectedOneOfHisPieces = true;
                                selectedPieceIndex = i;
                                //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                                availableMoves.clear();
                                chessBoard.pieces.get(i).CalculateAllPossibleMoves(chessBoard);
                                availableMoves = chessBoard.pieces.get(i).availableDes;
                                return;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(MultiBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
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
                        if(whitePlayerTurn){
                             if(chessBoard.checkMate(PieceColor.White)){
                                JOptionPane.showMessageDialog(null, PieceColor.Black + " Wins");
                                System.exit(0);
                            }
                        }
                else{
                            if(chessBoard.checkMate(PieceColor.Black)){
                                JOptionPane.showMessageDialog(null, PieceColor.White + " Wins");
                                System.exit(0);
                            }
                            
                        }
                        if (chessBoard.pieces.get(selectedPieceIndex).move(pieceX, pieceY, chessBoard)) {
                            if(!availableMoves.isEmpty()){
                                availableMoves.clear();
                            }
                            selectedPieceIndex = -1;
                            playerSelectedOneOfHisPieces = false;
                            if (whitePlayerTurn) {
                                whitePlayerTurn = false;
                            } else if (!whitePlayerTurn) {
                                whitePlayerTurn = true;
                            }
                            System.out.println("Pieces numbers: " + chessBoard.pieces.size());
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(MultiBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
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
        for (int i = 0; i < chessBoard.pieces.size(); i++) {
            if (chessBoard.pieces.get(i).color == PieceColor.Black) {
                g.drawImage(chessBoard.pieces.get(i).blackImage, (chessBoard.pieces.get(i).xPos * 60) + 10, (chessBoard.pieces.get(i).yPos * 60) + 10, 60, 60, null);
            } else if (chessBoard.pieces.get(i).color == PieceColor.White) {
                g.drawImage(chessBoard.pieces.get(i).whiteImage, (chessBoard.pieces.get(i).xPos * 60) + 10, (chessBoard.pieces.get(i).yPos * 60) + 10, 60, 60, null);
            }
        }

        if (!availableMoves.isEmpty()) {
            for (Points move : availableMoves) {
                g.setColor(Color.BLUE);
                g.drawRect((move.xPos*60)+10, (move.yPos*60)+10, 60, 60);
            }
        }

//        paintComponent(g);
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//        for (Piece piece : chessBoard.pieces) {
//            if (piece.color == Piece.Black) {
//                g.drawImage(piece.blackImage, (piece.xPos * 60) + 10, (piece.yPos * 60) + 10, 60, 60, null);
//            } else if (piece.color == Piece.White) {
//                g.drawImage(piece.whiteImage, (piece.xPos * 60) + 10, (piece.yPos * 60) + 10, 60, 60, null);
//            }
//        }
//    }
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
