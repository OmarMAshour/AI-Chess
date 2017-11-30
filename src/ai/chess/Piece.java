package ai.chess;
import java.math.*;
import ai.chess.*;
import java.util.*;
//chess pieces model

enum PieceColor {Black,White};

abstract public class Piece {
    public int xPos;
    public int yPos;
    public PieceColor color;
    public int priority;
    
    //piece motion
    //change xpos w ypos if possible
    abstract public boolean move(int xdespos , int ydespos,ChessBoard board,ArrayList<Piece>pieceslist,boolean Kingcheck);
    //piece killing other one
    //killer take killed's place
    abstract public boolean kill(Piece killed,ChessBoard board,ArrayList<Piece>pieceslist); 
    public boolean KingCheckThreat (int curx,int cury,ChessBoard board,ArrayList<Piece>pieceslist){
        ChessBoard tempboard = new ChessBoard ();
        for (int i=0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                if(i==curx && j==cury){
                    tempboard.Squares[i][j].ContainPiece=false;
                }
                else{
                    tempboard.Squares[i][j].ContainPiece = board.Squares[i][j].ContainPiece;
                    
                }
            }
        }
        int tmpKingXpos=-1,tmpKingYpos=-1;
        for (int i = 0; i < pieceslist.size(); i++) {
            if(pieceslist.get(i).priority==30){
                tmpKingXpos=pieceslist.get(i).xPos;
                tmpKingYpos=pieceslist.get(i).yPos;
            }
        }
        for (int i=0;i<pieceslist.size();i++){
            if (move(tmpKingXpos,tmpKingYpos,tempboard,pieceslist,true)){
                return false;
            }
        }
        return true;
    }
}

class Pawn extends Piece{    
    public boolean canMoveTwice;
    public Pawn(int xpos , int ypos , PieceColor color , int priority) {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.canMoveTwice = true;
    }
    public boolean move(int xdespos , int ydespos,ChessBoard board,ArrayList<Piece>pieceslist,boolean Kingcheck) {
        if(canMoveTwice && Math.abs(xdespos-this.xPos)==2 && !board.Squares[xdespos][ydespos].ContainPiece && !board.Squares[xdespos-1][ydespos-1].ContainPiece && !Kingcheck){
            if(!KingCheckThreat(this.xPos,this.yPos,board,pieceslist)){
                return false;
            }
            this.canMoveTwice=false;
            this.xPos = xdespos;
            board.Squares[xdespos][ydespos].ContainPiece=true;
            return true;
            //check lw eletnen ele odamy fdeen 34an 2t7rk
        }
        //for all moves
        //check lw eletnen ele odamy fdeen 34an 2t7rk 7araka aw etnen
        else if (Math.abs(xdespos-xPos)==1 && !board.Squares[xdespos][ydespos].ContainPiece && !Kingcheck){
            if(!KingCheckThreat(this.xPos,this.yPos,board,pieceslist)){
                return false;
            }
            board.Squares[this.xPos][this.yPos].ContainPiece=false;
            this.xPos=xdespos;
            board.Squares[xdespos][ydespos].ContainPiece=true;
            this.canMoveTwice=false;
            return true;
        }
        return false;
    }
    public boolean kill(Piece killed,ChessBoard board,ArrayList<Piece>pieceslist) {
        if(!KingCheckThreat(this.xPos,this.yPos,board,pieceslist)){
                return false;
        }
        if(Math.abs(killed.xPos-this.xPos)==1 && Math.abs(killed.yPos-this.yPos)==1 && board.Squares[killed.xPos][killed.yPos].ContainPiece && killed.color!=this.color ){
            if(!KingCheckThreat(this.xPos,this.yPos,board,pieceslist)){
                return false;
            }
            board.Squares[this.xPos][this.yPos].ContainPiece=false;
            this.xPos=killed.xPos;
            this.yPos=killed.yPos;
            for (int i=0;i<pieceslist.size();i++){
                if(pieceslist.get(i).xPos==killed.xPos && pieceslist.get(i).yPos==killed.yPos){
                    pieceslist.remove(pieceslist.get(i));
                }
            }
        }
        return true;
    }
}



class Rook extends Piece {
    public Rook(int xpos , int ypos , PieceColor color , int priority) {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
    }
    public boolean move (int xdespos,int ydespos , ChessBoard board,ArrayList<Piece>pieceslist,boolean Kingcheck){
        if(this.yPos-ydespos==0){
        if (xdespos>this.xPos){
            for (int i=this.xPos;i<=xdespos;i++){
                if (board.Squares[i][this.yPos].ContainPiece){
                    return false;
                }
            }
        }
          if (xdespos<this.xPos){
            for (int i=this.xPos;i>=xdespos;i--){
                if (board.Squares[i][this.yPos].ContainPiece){
                    return false ;
                }
            }
    }
        }
        else if(this.xPos-xdespos==0){
            if (ydespos>this.yPos){
            for (int i=this.yPos;i<=ydespos;i++){
                if (board.Squares[this.xPos][i].ContainPiece){
                    return false ;
                }
            }
            }  
            if (ydespos<this.yPos){
            for (int i=this.yPos;i>=ydespos;i--){
                if (board.Squares[this.xPos][i].ContainPiece){
                    return false ;
                }
            }
            }
        }
        else{
            return false;
        }
        if(!Kingcheck){
            board.Squares[this.xPos][this.yPos].ContainPiece=false;
            this.xPos=xdespos;
            this.yPos=ydespos;
            board.Squares[xdespos][ydespos].ContainPiece=true;
            return true;
        }
        return true;
}
    public boolean kill(Piece killed,ChessBoard board,ArrayList<Piece>pieceslist){
        if(!KingCheckThreat(this.xPos,this.yPos,board,pieceslist)){
                return false;
        }
        if (killed.color!=this.color && move(killed.xPos,killed.yPos,board,pieceslist,false)){
            for (int i=0;i<pieceslist.size();i++){
            if(pieceslist.get(i).xPos==killed.xPos && pieceslist.get(i).yPos==killed.yPos){
                    pieceslist.remove(pieceslist.get(i));
                }
            }
        }
    } 
}

class Bishop extends Piece{
    public Bishop(int xpos , int ypos , PieceColor color , int priority) {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
    }
    public boolean move(int xdespos, int ydespos, ChessBoard board,ArrayList<Piece>pieceslist,boolean Kingcheck) {
        if(Math.abs(this.yPos-ydespos)/Math.abs(this.xPos-xdespos)==1){
        if(xdespos > this.xPos && ydespos > this.yPos){
            int start = this.xPos;
            int end = xdespos;
            for (int counter = start; counter <= end; counter++) {
                if(board.Squares[counter][counter].ContainPiece)
                    return false;
            }
        }
        //up left
        else if(xdespos > this.xPos && ydespos < this.yPos){
            int start = this.xPos;
            int end = xdespos;
            for (int i = start,negCounter=start ; i <= end; i++,negCounter--) {
                if(board.Squares[i][negCounter].ContainPiece)
                    return false;
            }
        }
        //down right
        else if(xdespos < this.xPos && ydespos > this.yPos){
            int start = this.yPos;
            int end = ydespos;
            for (int i = start,negCounter=start ; i <= end; i++,negCounter--) {
                if(board.Squares[negCounter][i].ContainPiece)
                    return false;
            }
            
        }
        //down left
        else if(xdespos < this.xPos && ydespos < this.yPos){
            int start = xdespos;
            int end = this.xPos;
            for (int counter = start; counter <= end; counter--) {
                if(board.Squares[counter][counter].ContainPiece)
                    return false;
            }
        }
        }
        else{
            return false;
        }
        if(!Kingcheck){
        board.Squares[this.xPos][this.yPos].ContainPiece=false;
        this.xPos=xdespos;
        this.yPos=ydespos;
        board.Squares[xdespos][ydespos].ContainPiece=true;
        return true;
        }
        return true;
    }
    public boolean kill(Piece killed, ChessBoard board, ArrayList<Piece> pieceslist) {
        if(!KingCheckThreat(this.xPos,this.yPos,board,pieceslist)){
                return false;
        }
        if(killed.color!=this.color && move(killed.xPos,killed.yPos,board,pieceslist,false)){
            for (int i=0;i<pieceslist.size();i++){
            if(pieceslist.get(i).xPos==killed.xPos && pieceslist.get(i).yPos==killed.yPos){
                    pieceslist.remove(pieceslist.get(i));
                }
            }
        }
    }
}


class King extends Piece{
    
    
    public King(int xpos , int ypos , PieceColor color , int priority) {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
    }
    public boolean move(int xdespos, int ydespos, ChessBoard board,ArrayList<Piece>pieceslist,boolean Kingcheck) {
       if (Math.abs(xdespos-this.xPos)==1 || Math.abs(ydespos-this.yPos)==1 ||(Math.abs(ydespos-this.yPos)==1 && Math.abs(xdespos-this.xPos)==1))
       if(!board.Squares[xdespos][ydespos].ContainPiece){
           board.Squares[this.xPos][this.yPos].ContainPiece=false;
           this.xPos=xdespos;
           this.yPos=ydespos;
           board.Squares[xdespos][ydespos].ContainPiece=true;
           return true;
       }
          return false;
    }
    public boolean kill(Piece killed, ChessBoard board, ArrayList<Piece> pieceslist) {
        if(killed.color!=this.color && move(killed.xPos,killed.yPos,board,pieceslist,false)){
            for(int i=0;i<pieceslist.size();i++){
                if(pieceslist.get(i).move(killed.xPos, killed.yPos, board,pieceslist,true)){
                    return false;
                }
            }
        
        }
        else
            return false;
        board.Squares[this.xPos][this.yPos].ContainPiece=false;
        this.xPos=killed.xPos;
        this.yPos=killed.yPos;
        board.Squares[killed.xPos][killed.yPos].ContainPiece=true;
            for (int i=0;i<pieceslist.size();i++){
            if(pieceslist.get(i).xPos==killed.xPos && pieceslist.get(i).yPos==killed.yPos){
                    pieceslist.remove(pieceslist.get(i));
                }
            }
        
      }
    
}

