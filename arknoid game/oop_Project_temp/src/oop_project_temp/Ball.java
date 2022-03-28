package oop_project_temp;
import javax.swing.*;
import java.awt.*;

public class Ball {
    private static int ballXpos;
    private static int ballYpos;
    private static int ballXdir;
    private static int ballYdir;
    private static int ballWidth;
    private static int ballHeight;
    
    public Ball(){
        if(MainMenu.getPhoneBool()){
        ballXpos = Paddle.getPosX() + 110 ;
        ballYpos = Paddle.getPosY() - 10 ;
        ballWidth = ballHeight = 15;
        }else{
        ballXpos = Paddle.getPosX() + 80 ;
        ballYpos = Paddle.getPosY() - 10 ;
        ballWidth = ballHeight = 20;
        }
       
        ballXdir = -1;
        ballYdir = -2;
    }
    
    public void draw(Graphics graph){
         graph.setColor(Color.BLUE);
        graph.fillOval(this.ballXpos, this.ballYpos, this.ballWidth, this.ballHeight);
    }
    
    public static int getPosX(){
        return ballXpos;
    }
    
    public static void setPosX(int posX){
        ballXpos = posX;
    }

    public static int getPosY(){
        return ballYpos;
    }
    
    public static void setPosY(int posY){
        ballYpos = posY;
    }
    
     public static int getDirX(){
        return ballXdir;
    }
    
    public static void setDirX(int dirX){
        ballXdir = dirX;
    }
    
    public static int getDirY(){
        return ballYdir;
    }
    
    public static void setDirY(int dirY){
        ballYdir = dirY;
    }
    
    //
    
    public static int getWidth(){
        return ballWidth;
    }
    
    public static void setWidth(int Width){
        ballWidth = Width;
    }
    
     public static int getHeight(){
        return ballHeight;
    }
    
    public static void setHeight(int Height){
        ballHeight= Height;
    }

}
