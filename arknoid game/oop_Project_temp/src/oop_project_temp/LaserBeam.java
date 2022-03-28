package oop_project_temp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LaserBeam extends Capsules {

    public int beamIntensity;
    public int BeamXpos;
    public int BeamYpos;
    public static int NumOfBeam;
    public static int CurrentPaddleXpos;
    private Image Beamimage;



    public LaserBeam(int ID){
        super(ID);
        beamIntensity = 2;
         this.BeamXpos = Paddle.getPosX(); 
        this.BeamYpos = Paddle.getPosY();
        this.NumOfBeam = 5;
        CurrentPaddleXpos = 0;
          try{
              super.image = ImageIO.read(new File("laser2-512.png"));
              Beamimage = ImageIO.read(new File("RedBeam.png"));
             }catch(IOException ex){}
       
    }
    
    
    
    @Override
    public void drawCapsule(Graphics graph, int posx, int posy,GamePlay g) {
                     graph.drawImage(image, posx,posy,super.width,super.height,g);
                     
                      Rectangle imgrec = new Rectangle(posx,posy,super.width,super.height);
                      Rectangle paddlerec = new Rectangle(Paddle.getPosX(),Paddle.getPosY(),Paddle.getWidth(),Paddle.getHeight());
                                         if(imgrec.intersects(paddlerec)){
                                              Paddle.setPaddleImage("Shoot-Paddle.png");
                                              Capsules.GunBool = false;
                                              Capsules.LaserBool =true ;             
                                         }
                                        super.posY += 1;
                                        //CurrentPaddleXpos = false;
    }

    @Override
    public void Pressed(Graphics graph , GamePlay g) {
        
            if(NumOfBeam > 0){
                 graph.drawImage(Beamimage, CurrentPaddleXpos,BeamYpos,super.width,super.height+20,g);
                        BeamYpos -=10;
            }
            else{
                                Paddle.setPaddleImage("Normal-Paddle.png");

            }

    }

   
}
