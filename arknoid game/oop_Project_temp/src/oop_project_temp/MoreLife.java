/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project_temp;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MoreLife extends Capsules{
        public static int LifeCounter;

    public MoreLife(int ID){
        super(4);
        LifeCounter = 2 ;
           try{
                   super.image = ImageIO.read(new File("life-icon.png"));
               }catch(IOException ex){}

    }
    @Override
    public void drawCapsule(Graphics graph, int posx, int posy, GamePlay g) {
                  graph.drawImage(image, posx,posy,super.width,super.height,g);
                  Rectangle recCap = new Rectangle(super.posX , super.posY , super.width , super.height);
                  Rectangle recPaddle = new Rectangle(Paddle.getPosX(),Paddle.getPosY() , Paddle.getWidth() ,Paddle.getHeight());
                  if(recCap.intersects(recPaddle)){
                      LifeCounter++;
                      System.out.println("Life "+ LifeCounter);
                      super.posY = Paddle.getPosY() + 100;
                  }
                  super.posY += 1;
                                                                                
    }

    @Override
    public void Pressed(Graphics Graph,GamePlay g) {}
    
}
