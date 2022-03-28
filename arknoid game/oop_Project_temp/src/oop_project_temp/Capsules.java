package oop_project_temp;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;


abstract public class Capsules{
 protected int width;
 protected int  height;
 protected int posX;
 protected int posY;
 protected Image image;
 protected int ID;
 protected int Duration;
 public static boolean GunBool;
 public static boolean LaserBool ;
 



    public Capsules( int ID){
        this.width = 50 ; 
        this.height = 50;
        Duration = 15; // seconds
        this.ID = ID;
        //paddle = new Paddle();
        this.LaserBool = false;
        this.GunBool = false;
      
    }
    
    public abstract void drawCapsule(Graphics graph ,int posx , int posy , GamePlay g);
    public abstract void Pressed(Graphics graph ,GamePlay g);
 

}
