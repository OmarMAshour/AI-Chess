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
import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author omarashour
 */
public class SingleBoardPanel extends JPanel implements Serializable {

    /**
     * Creates new form SingleBoardPanel
     */
    public ChessBoard chessBoard;

    private Timer piecesDrawingTimer;

    private boolean playerSelectedOneOfHisPieces = false;
    private int selectedPieceIndex = -1;
    private boolean AIWhiteFirstTurn = true;
    public BoardController boardController;
    public boolean canPlayerPlay;

    public String className = "Single";
    public ArrayList<Points> availableMoves = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public SingleBoardPanel(ChessBoard chessBoard) {
        initComponents();
        if (isPlayerWhite) {
            canPlayerPlay = true;
        } else {
            canPlayerPlay = false;
        }
        this.chessBoard = chessBoard;
        boardController = new BoardController();

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

                if (!isPlayerWhite && AIWhiteFirstTurn) {
                    try {
                        AIWhiteFirstTurn = false;
                        try {
                            if (chessBoard.checkMate(PieceColor.Black)) {
                                JOptionPane.showMessageDialog(null, PieceColor.White + " Wins");
                                System.exit(0);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        setChessBoard(boardController.BoardToDraw(getChessBoard()));
                        verboseStrings.add("Branching Factor : " + boardController.GetNPluesOneNodes() / boardController.GetNNodes());
                        canPlayerPlay = true;
                        return;
                    } catch (Exception ex) {
                        Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (canPlayerPlay) {
                    if (true) {

                        try {
                            if (chessBoard.checkMate(PieceColor.White)) {
                                JOptionPane.showMessageDialog(null, PieceColor.Black + " Wins");
                                System.exit(0);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        int clickedX = e.getX();
                        int clickedY = e.getY();

                        int pieceX = (clickedX - 10) / 60;
                        int pieceY = (clickedY - 10) / 60;

                        verboseStrings.add("Mouse Clicked!!!!! x= " + pieceX + " y= " + pieceY);

                        //by this whenever the player choose one of his own pieces he will get the available options to move within them
                        for (int i = 0; i < chessBoard.pieces.size(); i++) {
                            if (isPlayerWhite) {
                                if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.White) {
                                    playerSelectedOneOfHisPieces = true;
                                    selectedPieceIndex = i;
                                    //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                                    if (playerSelectedOneOfHisPieces) {
                                        availableMoves.clear();
                                        chessBoard.pieces.get(i).CalculateAllPossibleMoves(chessBoard);
                                        availableMoves = chessBoard.pieces.get(i).availableDes;
                                    }
                                    return;
                                }
                            } else if (!isPlayerWhite) {
                                if (chessBoard.pieces.get(i).xPos == pieceX && chessBoard.pieces.get(i).yPos == pieceY && chessBoard.pieces.get(i).color == PieceColor.Black) {
                                    playerSelectedOneOfHisPieces = true;
                                    selectedPieceIndex = i;
                                    //SHOW THE PLAYER THE AVAILABLE POSITIONS TO MOVE TO
                                    if (playerSelectedOneOfHisPieces) {
                                        availableMoves.clear();
                                        chessBoard.pieces.get(i).CalculateAllPossibleMoves(chessBoard);
                                        availableMoves = chessBoard.pieces.get(i).availableDes;
                                    }
                                    return;
                                }
                            }
                        }
                        //in case that the player clicked on a piece before and now is taking the next action
                        if (selectedPieceIndex != -1 && playerSelectedOneOfHisPieces) {
                            try {
                                boolean check = chessBoard.pieces.get(selectedPieceIndex).move(pieceX, pieceY, chessBoard);
                                if (check) {
                                    canPlayerPlay = false;
                                    selectedPieceIndex = -1;
                                    playerSelectedOneOfHisPieces = false;
                                    availableMoves.clear();

                                }

                            } catch (Exception ex) {
                                System.err.println(ex.getMessage());
                            } finally {
                                try {
                                    //                        canPlayerPlay = true;
                                    //   
                                } catch (Exception ex) {
                                    Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                return;
                            }
                        }

                    }
                }
//                if (!canPlayerPlay) {
//
//                    try {
//                        try {
//                            if (isPlayerWhite) {
//                                if (chessBoard.checkMate(PieceColor.Black)) {
//                                    JOptionPane.showMessageDialog(null, PieceColor.White + " Wins");
//                                    System.exit(0);
//                                } else if (chessBoard.checkMate(PieceColor.White)) {
//                                    JOptionPane.showMessageDialog(null, PieceColor.Black + " Wins");
//                                    System.exit(0);
//                                }
//                            }
//                        } catch (IOException ex) {
//                            Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        availableMoves.clear();
//                        setChessBoard(boardController.BoardToDraw(chessBoard));
//                        verboseStrings.add("Branching Factor : " + boardController.GetNPluesOneNodes() / boardController.GetNNodes());
//                        canPlayerPlay = true;
//
//                    } catch (Exception ex) {
//                        Logger.getLogger(SingleBoardPanel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    System.out.println("Branching Factor : " + boardController.GetNPluesOneNodes() / boardController.GetNNodes());
//                    canPlayerPlay = true;
//
//                }
            }
//            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
//        for (Piece piece : chessBoard.pieces) {
//            if (piece.color == Piece.Black) {
//                g.drawImage(piece.blackImage, (piece.xPos * 60) + 10, (piece.yPos * 60) + 10, 60, 60, null);
//            } else if (piece.color == Piece.White) {
//                g.drawImage(piece.whiteImage, (piece.xPos * 60) + 10, (piece.yPos * 60) + 10, 60, 60, null);
//            }
//        }

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
                g.drawRect((move.xPos * 60) + 10, (move.yPos * 60) + 10, 60, 60);
            }
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard board) {
        try {
            board.copyBoard2(this.chessBoard);
        } catch (Exception e) {
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(boardLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardLabel)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boardLabel;
    // End of variables declaration//GEN-END:variables
}
