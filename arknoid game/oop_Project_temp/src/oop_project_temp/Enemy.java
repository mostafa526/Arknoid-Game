/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project_temp;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Enemy {
    private static int Xpos;
    private static int Ypos;
    private static int Xdir;
    private static int Ydir;
    private static int Width;
    private static int Hight;
    private static int XposCap ;
    private static int YposCap ;
   // private static int Speed;
    private static int hitCounter;
    private static int WeaponID;
    private File EnemyImage;
    private File CapImage;
    private Image im2;
    public boolean Left;
    public boolean Right;
    public static boolean paddle;

    private Image im;
    
     public Enemy(){
      Xpos = 800;
      Ypos = 10;
      XposCap = 100 ;
      YposCap = 100 ;
      Width = 500;
      Hight = 200;
      Xdir = -1;
      Ydir = -2;
      hitCounter = 0;
      EnemyImage = new File("Enemy.png");
      CapImage = new File("rocket.png");
      Left = false;
      Right = false;
      
      try{
                im = ImageIO.read(EnemyImage);
                im2 = ImageIO.read(CapImage);
      }catch(IOException ex){}
    }
     
     
    public void draw(Graphics g , GamePlay game)
    {
       g.drawImage(im,Xpos,Ypos ,Width,Hight,game);
       if(Xpos <= 0){
           Left =true;
           Right = false;
       }else if(Xpos >= 1000){
       Right = true;
       Left = false;
       }
    }
    public void move()
    {
            Xpos += Xdir;
           if(Xpos > 1000){
           Xdir = -Xdir;
           }else if(Xpos < 0){
           Xdir = -Xdir;
           }
           
    }
   
    public void hit(Graphics g , GamePlay game)
    {
        if(Left ){
           if(Xpos <= 0){
           XposCap = 100;
           YposCap = 100;
           }        
          g.drawImage(im2,XposCap,YposCap ,100,150,game);
        // XposCap += 3;
        YposCap += 1;
        }
        else if(Right){
           if(Xpos >= 1000){
           XposCap = Xpos+200;
           YposCap = 100;
           }
        // XposCap -= 3;
        YposCap += 1;
                  g.drawImage(im2,XposCap,YposCap ,100,150,game);
        }
        
        if(XposCap <= Paddle.getPosX() - 100 && (YposCap < Paddle.getPosY() - 300)){
                     XposCap += 1;
        }else if(XposCap >= Paddle.getPosX() - 100 && (YposCap < Paddle.getPosY() - 300)){
                     XposCap -= 1;
        }
        Rectangle PaddleRec = new Rectangle(Paddle.getPosX(),Paddle.getPosY(),Paddle.getWidth(),Paddle.getHeight());
        Rectangle EnemyRec = new Rectangle(XposCap,YposCap,50 , 50);
       if(EnemyRec.intersects(PaddleRec)){
        paddle = true;
       }
        
    }
    public static int getXpos() {
        return Xpos;
    }

    public static void setXpos(int Xpos) {
        Enemy.Xpos = Xpos;
    }

    public static int getYpos() {
        return Ypos;
    }

    public static void setYpos(int Ypos) {
        Enemy.Ypos = Ypos;
    }

    public static int getXdir() {
        return Xdir;
    }

    public static void setXdir(int Xdir) {
        Enemy.Xdir = Xdir;
    }

    public static int getYdir() {
        return Ydir;
    }

    public static void setYdir(int Ydir) {
        Enemy.Ydir = Ydir;
    }

    public static int getWidth() {
        return Width;
    }

    public static void setWidth(int Width) {
        Enemy.Width = Width;
    }

    public static int getHight() {
        return Hight;
    }

    public static void setHight(int Hight) {
        Enemy.Hight = Hight;
    }

    public static int getHitCounter() {
        return hitCounter;
    }

    public static void setHitCounter(int hitCounter) {
        Enemy.hitCounter = hitCounter;
    }

    public static int getWeaponID() {
        return WeaponID;
    }

    public static void setWeaponID(int WeaponID) {
        Enemy.WeaponID = WeaponID;
    }

    public File getEnemyImage() {
        return EnemyImage;
    }

    public void setEnemyImage(File EnemyImage) {
        this.EnemyImage = EnemyImage;
    }

    public boolean isDied() {
        return Left;
    }

    public void setDied(boolean Died) {
        this.Left = Died;
    }
  
}
