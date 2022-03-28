package oop_project_temp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ShootGun extends Capsules {
    public int intensity;
    public static int numOfPistols;
    public int shootXpos;
    public int shootYpos;
    //private boolean CurrentPaddleXpos;
    public static  int CurrentPaddleXpos;
    private Image shootimage;
  

    public ShootGun(int ID){
        super(ID);
        intensity = 1;
        this.shootXpos = Paddle.getPosX(); 
        this.shootYpos =  Paddle.getPosY();
        CurrentPaddleXpos = 0;
        numOfPistols = 5 ;
         try{
               shootimage = ImageIO.read(new File("Commando_2_Shape_1346.png"));
                super.image= ImageIO.read(new File("ShootGun.png"));
             }catch(IOException ex){}
    }
    
    @Override
    public void Pressed(Graphics graph , GamePlay g){
            if(numOfPistols > 0){
                graph.drawImage(shootimage,CurrentPaddleXpos,shootYpos,super.width,super.height,g);
                this.shootYpos -=10;  
            }
            else{
                                Paddle.setPaddleImage("Normal-Paddle.png");

            }
            
    }
       @Override
    public void drawCapsule(Graphics graph , int posx , int posy, GamePlay g){
                graph.drawImage(super.image, posx,posy,super.width,super.height,g);
                Rectangle imgrec = new Rectangle(posx,posy,super.width,super.height);
                Rectangle paddlerec = new Rectangle(Paddle.getPosX(), Paddle.getPosY(),Paddle.getWidth(), Paddle.getHeight());
                if( paddlerec.intersects(imgrec)){
                    Paddle.setPaddleImage("Shoot-Paddle.png");
                    numOfPistols = 5;
                    Capsules.GunBool = true;
                    Capsules.LaserBool = false;
                }
                       super.posY += 1;
    }


}
