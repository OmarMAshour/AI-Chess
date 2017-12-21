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
import static ai.chess.AIChess.*;

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
    private Timer playAITimer;
    private Timer playerTimer;
    private boolean playerSelectedOneOfHisPieces = false;
    private int selectedPieceIndex = -1;
    private int previousPieceindex = -1;
    private boolean AIWhiteFirstTurn = true;
    private BoardController boardController = new BoardController();
    private boolean playerTurn1;
    private boolean playerTurn2;
    private boolean AITurn;
    private int pieceX;
    private int pieceY;

    public SingleBoardPanel(ChessBoard chessBoard) {
        initComponents();
        if (isPlayerWhite) {
            playerTurn1 = true;
            AITurn = false;
        } else {
            playerTurn1 = false;
            AITurn = true;
        }
        this.chessBoard = chessBoard;

        piecesDrawingTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        piecesDrawingTimer.start();

        playAITimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (AITurn) {
                    try {
                        AITurn = false;
                        System.err.println("ALGO STARTED!");
                        try {
                            //                        SingleBoardPanel.chessBoard=null;
                            boardController.nextStepBoard(getChessBoard()); 
                        } catch (Exception ex) {
                            Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.err.println("ALGO ENDEDD!!!!!!!!");
                        boardController.boardsCollected.clear();
                        playerTurn1 = true;
                        playAITimer.stop();
                        playerTimer.start();
                        return;
                    } catch (Exception ex) {
                        Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        playAITimer.start();

        playerTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (playerTurn1) {

                    System.out.println("Mouse Clicked!!!!! x= " + pieceX + " y= " + pieceY);

                    //by this whenever the player choose one of his own pieces he will get the available options to move within them
                    for (int i = 0; i < chessBoard.pieces.size(); i++) {
                        if (isPlayerWhite) {
                            if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.White) {
                                playerSelectedOneOfHisPieces = true;
                                selectedPieceIndex = i;
                                //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                                if (previousPieceindex != selectedPieceIndex) {
                                    previousPieceindex = selectedPieceIndex;
                                    playerTurn1 = false;
                                    playerTurn2 = true;
                                }
                                return;
                            }
                        } else if (!isPlayerWhite) {
                            if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.Black) {
                                playerSelectedOneOfHisPieces = true;
                                selectedPieceIndex = i;
                                //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                                if (previousPieceindex != selectedPieceIndex) {
                                    previousPieceindex = selectedPieceIndex;
                                    playerTurn1 = false;
                                    playerTurn2 = true;
                                }
                                return;
                            }
                        }
                    }
                }

                if (playerTurn2) {
                    //in case that the player clicked on a piece before and now is taking the next action
                    System.out.println("Mouse Clicked!!!!! x= " + pieceX + " y= " + pieceY);
                    if (selectedPieceIndex != -1 && playerSelectedOneOfHisPieces) {
                        try {
                            boolean check = chessBoard.pieces.get(selectedPieceIndex).move(pieceX, pieceY, chessBoard);
                            System.err.println(check);
                            if (check) {

                                selectedPieceIndex = -1;
                                playerSelectedOneOfHisPieces = false;
                                playerTurn2 = false;
                                AITurn = true;
                                playerTimer.stop();
                                playAITimer.start();
                                System.err.println("DA5AL GOWA");

                            }

                        } catch (Exception ex) {
                            System.err.println(ex.getMessage());
                        } finally {
                            return;
                        }
                    }
                }

            }
        });
        playerTimer.start();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.

                int clickedX = e.getX();
                int clickedY = e.getY();

                pieceX = (clickedX - 10) / 60;
                pieceY = (clickedY - 10) / 60;

//                if (playerTurn1) {
//                    
//                }
//
//                if (!playerTurn1) {
//                    try {
////                        setChessBoard(boardController.BoardToDraw(chessBoard));
//                        AITurn = true;
//                        playerTurn1 = true;
//                    } catch (Exception ex) {
//                        Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
            }
//            }
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
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard board) {
        this.chessBoard = board;
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
