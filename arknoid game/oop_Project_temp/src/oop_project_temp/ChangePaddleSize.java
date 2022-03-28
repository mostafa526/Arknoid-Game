package oop_project_temp;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ChangePaddleSize extends Capsules {
    public int newSize;
    
    
    public ChangePaddleSize(int ID){
        super(3);
        this.newSize = Paddle.getWidth() +200;
           try{
                  super.image = ImageIO.read(new File("2309_-_Full_Screen-512.png"));
                }catch(IOException ex){}

        
    }
    @Override
    public void drawCapsule(Graphics graph, int posx, int posy,  GamePlay g) {
             
                                         graph.drawImage(image, posx,posy,super.width,super.height,g);
                                          Rectangle imgrec = new Rectangle(posx,posy,super.width,super.height);
                                         Rectangle paddlerec = new Rectangle(Paddle.getPosX(), Paddle.getPosY(), Paddle.getWidth(), Paddle.getHeight());
                                         if( paddlerec.intersects(imgrec)){
                                             Paddle.setWidth( newSize );
                                         }
                                        super.posY +=1;
    }

    @Override
    public void Pressed(Graphics Graph,GamePlay g) {}
}
