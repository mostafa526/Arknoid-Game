
package oop_project_temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ChooseDevice extends JFrame implements ActionListener{
    private Image phone ;
    private Image LabTop ;
    private Image GameLogoImage;
    private JButton Phone;
    private JButton Lab;
   
    
    public ChooseDevice(){
        this.setSize(2000,2000);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(null);
        Phone = new JButton("Mobile");
        Lab = new JButton("DeskTop");
        Phone.setBounds(320,880,200,80);
        Lab.setBounds(Phone.getBounds().x + 870,Phone.getBounds().y ,200,80);
        
        Phone.setBackground(Color.BLACK);
        Phone.setForeground(Color.WHITE);
         Lab.setBackground(Color.BLACK);
        Lab.setForeground(Color.WHITE);
        
        Phone.addActionListener(this);
        Lab.addActionListener(this);
        
        this.add(Phone);
        this.add(Lab);

        try{
            phone = ImageIO.read(new File("iphone-Screen1.png"));
            LabTop = ImageIO.read(new File("LabTop.png"));
            GameLogoImage  = ImageIO.read(new File("Logo5.jpg") );
        }catch(IOException ex){}
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object temp = e.getSource();
        if(temp.equals(Phone)){
         MainMenu.setPhoneBool(true);
         MainMenu Menu = new MainMenu();
        Menu.setVisible(true);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        }else{
             MainMenu Menu = new MainMenu();
        Menu.setVisible(true);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        }
        repaint();
    }
    
    
 @Override
    public void paint(Graphics graph){
        super.paint(graph);
        graph.drawImage(phone,200,80,450,800,this);
        graph.drawImage(LabTop,1000,200,600,600,this);
        graph.drawImage(GameLogoImage,330,250,200,200,this);
    }
    
    
}
