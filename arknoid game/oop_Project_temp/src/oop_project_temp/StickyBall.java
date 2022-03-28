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

/**
 *
 * @author tride
 */
public class StickyBall  extends Capsules{
        public static boolean isSticky;
        public static int ArrowXpos;
        public static Image Up_Arrow;
        public static Image Left_Arrow;
        public static Image Right_Arrow;



   public StickyBall(int id) {
        super(id);
        isSticky = false;
        try{
                 super.image = ImageIO.read(new File("sticky.jpg"));
                 Up_Arrow = ImageIO.read(new File("arrow_up.png"));
                 Left_Arrow =  ImageIO.read(new File("arrow_up_left.png"));
                 Right_Arrow = ImageIO.read(new File("arrow_up_right.png"));
        }catch(IOException e){}
    }

    @Override
   public void drawCapsule(Graphics graph , int posx , int posy, GamePlay g){
                 graph.drawImage(image, posx,posy,super.width,super.height,g);
                 Rectangle Arrowrec = new Rectangle(posx,posy,super.width,super.height);
                 Rectangle Paddlerec = new Rectangle(Paddle.getPosX(),Paddle.getPosY(),Paddle.getWidth(),Paddle.getHeight());
                 if(Arrowrec.intersects(Paddlerec)){
                     isSticky =true;
                 }
                 super.posY += 1 ;
    }

    @Override
    public void Pressed(Graphics graph, GamePlay g) {
    }
}


