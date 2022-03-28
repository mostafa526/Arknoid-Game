package oop_project_temp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Paddle {
    private static int paddleXpos; 
    private static int paddleYpos;
    private static int paddleWidth;
    private static int paddleHeight;
    private static String paddleimg ;
    private static Image im;
    
    public Paddle(){
        if(MainMenu.getPhoneBool()){
            paddleXpos = 880;
            paddleYpos = 900;
            paddleWidth = 125;
           paddleHeight = 25;
        }else{
         paddleXpos = 880;
        paddleYpos = 900;
        paddleWidth = 175;
        paddleHeight = 30;
        }
        
       paddleimg = "Normal-Paddle.png"; 
       try {
               im =ImageIO.read(new File(paddleimg));
       } catch (IOException ex) {}

    }
    
   public void draw(Graphics graph,GamePlay game){
          graph.drawImage(im,paddleXpos,paddleYpos ,paddleWidth ,paddleHeight,game);
   }
   
   public static int getWidth(){
       return paddleWidth;
   }
   
   public static void setWidth( int width){
       paddleWidth = width;
   }
   
   public static int getHeight(){
       return paddleHeight;
   }
   
   public static void setHeight( int Height){
       paddleHeight = Height;
   }
   
   public static int getPosX(){
       return paddleXpos;
   }
   
   public static void setPosX( int Xpos){
       paddleXpos = Xpos;
   }
   
   
    public static int getPosY(){
       return paddleYpos;
   }
   
   public static void setPosY( int Ypos){
       paddleYpos = Ypos;
   }
 
   public static String getPaddleImage(){
       return paddleimg;
   }
   
   public static void setPaddleImage( String NewImage){
       try{
       im = ImageIO.read(new File(NewImage));
       }catch(IOException ex){}
   }
          
}
