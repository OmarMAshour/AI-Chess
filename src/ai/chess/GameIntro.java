/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.chess;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author omarashour
 */
public class GameIntro extends javax.swing.JFrame {

    /**
     * Creates new form GameIntro
     */
    public GameIntro() {
        initComponents();
            this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        startBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        startBtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/intro.gif"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(20, 2, 2));
        jLabel2.setText("AI CHESS");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(194, 36, 36));
        jLabel3.setText("Legends Only Can Defeat This ;) ");

        startBtn.setFont(new java.awt.Font("Ubuntu", 3, 18)); // NOI18N
        startBtn.setForeground(new java.awt.Color(195, 134, 45));
        startBtn.setText("Start ???!!");
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("AlHor", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(55, 217, 25));
        jLabel4.setText("الشقاوة فينا بس ربنا هادينا");

        startBtn1.setFont(new java.awt.Font("AlHor", 3, 24)); // NOI18N
        startBtn1.setForeground(new java.awt.Color(30, 26, 21));
        startBtn1.setText("معادى ؟؟");
        startBtn1.setToolTipText("");
        startBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(startBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel2)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtnActionPerformed
        try {
            // TODO add your handling code here:
            String[] options = {"White", "Black"};
            int choice = JOptionPane.showOptionDialog(null, "Choose Whether you want to play with White or Black?", "",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            
            if(choice==0)
            {                  
                AIChess.isPlayerWhite=true;
                ChessBoard chessBoard = new ChessBoard();
                GameBoard gb = new GameBoard(chessBoard);
                gb.setVisible(true);
            }else if(choice==1){
                AIChess.isPlayerWhite=false;
                ChessBoard chessBoard = new ChessBoard();
                GameBoard gb = new GameBoard(chessBoard);
                gb.setVisible(true);
            }else{
                System.exit(0);
            }
            
            
        } catch (IOException ex) {
            System.out.println("Exception in Class GameIntro.java in the part from line 123 to 144");
        }
    }//GEN-LAST:event_startBtnActionPerformed

    private void startBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtn1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_startBtn1ActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton startBtn;
    private javax.swing.JButton startBtn1;
    // End of variables declaration//GEN-END:variables
}
