package oop_project_temp;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener , KeyListener , MouseListener{

    //Data Members
   
    private boolean start;
    private int score;
    private int delay ;
    private boolean drawCap;
    private boolean drawLaserCap;
    private boolean drawGunCap;
    private boolean drawLifeCap;
    private boolean drawPaddleSizeCap;
    private boolean pressed ;
    private boolean DrawBall ;
    private boolean drawBall ;
    private boolean drawPaddle ;
    private boolean nextLevel ;
    public static boolean ShowEnemy;
    public static boolean DrawBricks;
    private boolean repaintBool ;
    private int StartLabelFont;
     private int countFont;
    private int CapsulesCounter;
    private String checkLevel;
    private int [][] brickstemp;

    
    
    
    
    
    private Sound sound;

    private Ball ball;
    private Bricks bricks ;
    private Levels level;
    private Files file;
    private ArrayList<Capsules> cap;
    private ShootGun[] shootGun;
    private LaserBeam[] Laser;
    private ChangePaddleSize[] newPadSize;
    private StickyBall[] sticky;
    private MoreLife[] Life;
    private Paddle paddle;
    private Enemy enemy;
    public static Image Arrow;
    public static Image BackImage;
   

    private Random rand ;
    private Timer timer;
    
    private JButton Back;
    private JButton Pause;
    private JButton Continue;
    private JButton Next;
    private JButton End;
    
    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //requestFocus();
        
         // m = new MainMenu();
        Back = new JButton("Menu");
        Back.setBackground(Color.WHITE);
        //add pause button
        Pause = new JButton("Pause");
        Pause.setBackground(Color.WHITE);
        Continue = new JButton("Continue");
        Continue.setBackground(Color.WHITE);
         Next = new JButton("Next");
         Next.setBounds(800,800,100,100);
         End = new JButton("End");
         End.setBounds(1050 , 800 , 100 , 100);
        sound=new Sound();
        start = false;
        repaintBool = true;
        StartLabelFont =20;
        countFont = 0;
        score = 0;
        CapsulesCounter = 0;
        delay = 7 ;//miliSecond
        paddle = new Paddle();
        ball = new Ball();
        shootGun = new ShootGun[4];
        sticky = new StickyBall[4];
        Laser = new LaserBeam[4];
        Life = new MoreLife[4];
        newPadSize = new ChangePaddleSize[4];
        for(int i = 0 ; i < 4 ; i++){
            shootGun[i] = new ShootGun(1);
            Laser[i] = new LaserBeam(2);
           newPadSize[i] = new ChangePaddleSize(3);
           Life[i] = new MoreLife(4);
           sticky[i] = new StickyBall(5);
         }
        pressed =false;
        rand = new Random();
        drawCap =false;
        DrawBall = true;
        nextLevel = false;
        ShowEnemy = false;
        DrawBricks = true;
        drawBall = true ;
        drawPaddle = true ;
        checkLevel = "";
        level = new Levels();
        enemy = new Enemy();
       cap = new ArrayList<Capsules>(5);
        ClearCapsules();
         
         if(Files.CurrentLevel.equals("1") || Levels.getChooseLevel().equals("1")){
            level.Level1();
        }else if(Files.CurrentLevel.equals("2") || Levels.getChooseLevel().equals("2")){
            level.Level2();

        }
        else if(Files.CurrentLevel.equals("3") || Levels.getChooseLevel().equals("3")){
            level.Level3();

        }
        else if(Files.CurrentLevel.equals("4") || Levels.getChooseLevel().equals("4")){
            level.Level4();

        }
        else if(Files.CurrentLevel.equals("5") || Levels.getChooseLevel().equals("5")){
            level.Level5();

        }else if(Files.CurrentLevel.equals("Enemy") || Levels.getChooseLevel().equals("Enemy")){
            level.LevelEnemy();

        }

        bricks = new Bricks();
        file = new Files();
        try{
            Arrow = ImageIO.read(new File("arrow_up.png"));
            BackImage = ImageIO.read(new File("Back.png"));
        }catch(IOException ex){}
        
        timer = new Timer(delay,this);
        timer.start();
     
    }//End of Constructor
    
    @Override
    public void paintComponent(Graphics graph){
        //Background
        super.paintComponent(graph);
        graph.setColor(Color.WHITE);
        graph.fillRect(1,1,this.getSize().width,this.getSize().height);
      
        
        //borders
        graph.setColor(Color.RED);
        if(MainMenu.getPhoneBool()){
        graph.fillRect(750,120,3,800);//Left
        graph.fillRect(750,120,410,3);//Top
        graph.fillRect(1160,120,3,800);//Right
        }else{
        graph.fillRect(0,0,3,this.getSize().height);//Left
        graph.fillRect(0,0,this.getSize().width,3);//Top
        graph.fillRect(this.getSize().width-24,0,3,this.getSize().height);//Right
        }
      

        if(MainMenu.getPhoneBool()){
         graph.drawImage(MainMenu.getPhoneImage(),680,20,550,1050, this);
        }
        
        
         graph.setColor(Color.red);
        graph.drawRect(550,10, 70, 20);
        graph.drawRect(650,10, 70, 20);
        graph.drawRect(750,10, 70, 20);
        graph.drawString("Menu",550,20);
        graph.drawString("Pause",650,20);
        graph.drawString("Continue",750,20);

        if(nextLevel){
        graph.setFont(new Font("serif",Font.BOLD,34));
        graph.drawString("Let's Play Next Level:" + Files.CurrentLevel,550,500);
        graph.setColor(Color.RED);
        graph.setFont(new Font("serif",Font.BOLD,20));
        graph.drawRect(550 , 550 , 100, 30);
        graph.drawString("Next" , 550+10 , 550 + 20);
         graph.drawRect(700 , 550 , 100, 30);
        graph.drawString("End" , 700+10 , 550 + 20);
        }
        
        add(Back);
        Back.addMouseListener(this);
        Back.setBackground(Color.WHITE);
        add(Pause);
        Pause.addMouseListener(this);
        Pause.setBackground(Color.WHITE);
         add(Continue);
        Continue.addMouseListener(this);  
        add(Next);
        Next.addMouseListener(this);
        Next.setBounds(550,550,100,30);
        add(End);
        End.addMouseListener(this);
        End.setBounds(700 , 550 , 100 , 30);


        
       /* if(!StartGame()){
             graph.setFont(new Font("serif",Font.BOLD,StartLabelFont));
            graph.setColor(Color.BLACK);
            graph.drawString("Click To Start",500,600);
            if(StartLabelFont >= 100 && (countFont % 200 == 0)){
                StartLabelFont = 20;
                countFont = 0;
            }else{
              StartLabelFont += 1;
              countFont++;
            }
        }
               */
            
         
        
        //drawBricks()
        if(DrawBricks){
        bricks.draw((Graphics2D)graph , this);
       //graph.drawRect(650 , 100 , Bricks.getBricksWidth() - 5 ,Bricks.getBricksHeight());
        }

        //drawLabels()
        if(MainMenu.getPhoneBool()){
        graph.setColor(Color.BLACK);
        graph.setFont(new Font("serif",Font.BOLD + Font.ITALIC,17) );
        graph.drawString("Score:"+(score ) ,1080,140);
        graph.drawString("Life:"+(MoreLife.LifeCounter ) ,940,140);
          if(Files.CurrentLevel.equals("Enemy")){
                   checkLevel = "6";
               }else{
              checkLevel = Files.CurrentLevel;
          }
        if(Levels.getChooseLevel().equals("0")){
               graph.drawString("Level:"+Files.CurrentLevel,780,140);
             
        }else if(Integer.valueOf(checkLevel) >= Integer.valueOf(Levels.getChooseLevel())){
        graph.drawString("Level:"+Levels.getChooseLevel(),780,140);
        }
        
        }else{
        graph.setColor(Color.BLACK);
        graph.setFont(new Font("serif",Font.BOLD + Font.ITALIC,25) );
        graph.drawString("Score:"+(score ) ,1400,30);
        graph.drawString("Life:"+(MoreLife.LifeCounter ) ,960,30);
          if(Files.CurrentLevel.equals("Enemy")){
                   checkLevel = "6";
               }else{
              checkLevel = Files.CurrentLevel;
          }
        if(Levels.getChooseLevel().equals("0")){
               graph.drawString("Level:"+Files.CurrentLevel,200,30);
             
        }else if(Integer.valueOf(checkLevel) >= Integer.valueOf(Levels.getChooseLevel())){
        graph.drawString("Level:"+Levels.getChooseLevel(),200,30);
        }
        }
        
        //drawPaddle 
        if(!Enemy.paddle){
        paddle.draw(graph,this);
        graph.setColor(Color.RED);
        graph.drawRect(Paddle.getPosX(),Paddle.getPosY() + 20,Paddle.getWidth(),Paddle.getHeight() - 25);
        //graph.setColor(Color.GREEN);
        //graph.drawRect(Paddle.getPosX(),Paddle.getPosY()+5 + 20,Paddle.getWidth()-5,Paddle.getHeight() - 25);

        }else{
            graph.setColor(Color.BLACK);
            if(MainMenu.getPhoneBool()){
            graph.setFont(new Font("serif",Font.BOLD,50));
            graph.drawString("Game Over ",825,700);
            }else{
            graph.setFont(new Font("serif",Font.BOLD,100));
            graph.drawString("Game Over ",700,700);
            }
            start = false;
            repaintBool = false;
        }

        //drawBall()
        if(EndGame()){
            if(MoreLife.LifeCounter > 0){
                Ball.setPosX(Paddle.getPosX() + 80);
                Ball.setPosY(Paddle.getPosY() - 10);
                start = false;
                MoreLife.LifeCounter--;
            }else{
                sound.game_over();
            if(MainMenu.getPhoneBool()){
            graph.setFont(new Font("serif",Font.BOLD,50));
            graph.drawString("Game Over ",825,700);
            }else{
            graph.setFont(new Font("serif",Font.BOLD,100));
            graph.drawString("Game Over ",700,700);
            }
            start = false;
            repaintBool = false;    
            
            }
        }else{
           ball.draw(graph);
        } 
          
        //draw Arrow
       if(StickyBall.isSticky){
           Rectangle BallRec = new Rectangle(Ball.getPosX() , Ball.getPosY() , Ball.getWidth() , Ball.getHeight());
           Rectangle PaddleRec = new Rectangle(Paddle.getPosX() , Paddle.getPosY() + 20, Paddle.getWidth() , Paddle.getHeight() - 20);
           if(BallRec.intersects(PaddleRec)){
                  Ball.setPosY(Ball.getPosY() - 20);
                  Ball.setDirY(-2);
                  //System.out.println(Paddle.getPosX());
                  start = false;
           }
           if(!StartGame()){
                StickyBall.ArrowXpos = Ball.getPosX() - 18;
               graph.drawImage(Arrow,StickyBall.ArrowXpos , Paddle.getPosY() - 100 , 50 , 50 , this);
           }
        }
        
        
              if(drawCap){
              for(int c = 0 ; c < cap.size(); c++){
                 if(cap.get(c).posX != 0 && cap.get(c).posY <= Paddle.getPosY() && cap.get(c) != null){
                    cap.get(c).drawCapsule(graph, cap.get(c).posX, cap.get(c).posY,this);   
                    if( cap.get(c).posY > Paddle.getPosY()){
                             cap.get(c).equals(null);
                    }
                  }
               }
              }
              
              //draw Enemy
              if(ShowEnemy){
                  enemy.draw(graph, this);
                  enemy.hit(graph, this);
                  graph.drawRect(Enemy.getXpos(), Enemy.getYpos(),Enemy.getWidth(), Enemy.getHight());  
              }
              
             if(pressed && Capsules.LaserBool){
                    Laser[0].Pressed(graph ,this);
                    if(Laser[0].BeamYpos <=  0 ){
                        pressed = false;
                        Laser[0].BeamYpos = Paddle.getPosY() ;
                        Laser[0].BeamXpos = Paddle.getPosX() ;
                    }
             }else if(pressed && Capsules.GunBool){
                        shootGun[0].Pressed(graph ,this);
                        if(  shootGun[0].shootYpos <= 0 ){
                        pressed = false;
                          shootGun[0].shootYpos = Paddle.getPosY() ;
                          shootGun[0].shootXpos = Paddle.getPosX() ;
                        }
             }
             
             

        graph.dispose();
        
    }
    

    //Ball Actions
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(StartGame()){
            Rectangle paddlerec = new Rectangle(Paddle.getPosX(),Paddle.getPosY() + 20,Paddle.getWidth() - 5 ,Paddle.getHeight());
            Rectangle ballrec = new Rectangle(Ball.getPosX(),Ball.getPosY(),Ball.getWidth(),Ball.getHeight());    
            Rectangle enemyRec = new Rectangle(Enemy.getXpos(), Enemy.getYpos(),Enemy.getWidth(), Enemy.getHight());
            Rectangle beamrec = new Rectangle(LaserBeam.CurrentPaddleXpos,Laser[0].BeamYpos,20,20);
            Rectangle gunrec = new Rectangle(ShootGun.CurrentPaddleXpos,shootGun[0].shootYpos,20,20);
            if(ballrec.intersects(paddlerec) ){
                sound.ball_paddle();
                
               Ball.setDirY(-Ball.getDirY());  
               if((Ball.getPosY() + 1) >= Paddle.getPosY() + 5){
                   Ball.setDirX(Ball.getDirX());
               }
               //System.out.println(Ball.getPosY() +" "+ Paddle.getPosY());
            }
           
            if(ShowEnemy){
                enemy.move();
                if(ballrec.intersects(enemyRec)){
                   Ball.setDirY( -Ball.getDirY());
                   if(Ball.getPosX() <= Enemy.getXpos() || Ball.getPosX() == Enemy.getXpos() + Enemy.getWidth()){
                           System.out.println(Ball.getPosX() + " H " +Enemy.getXpos() + " " + Enemy.getXpos() + Enemy.getWidth());
                       Ball.setDirX(-1);
                   }
                 
                  Enemy.setHitCounter(Enemy.getHitCounter()+1);
                  score += 200;
                  if(Enemy.getHitCounter() == 5){
                     System.out.println(Enemy.getHitCounter());
                     ShowEnemy = false;
                     Files.HighScore = score ;
                     file.ReloadLevel();
                     JOptionPane.showMessageDialog(this , "done" , "deid" , JOptionPane.INFORMATION_MESSAGE);               
                  }
               }else if(beamrec.intersects(enemyRec) || gunrec.intersects(enemyRec)){
                  Gun_Laser_Collision();
                  Enemy.setHitCounter(Enemy.getHitCounter()+1);
                  score += 200;
                  if(Enemy.getHitCounter() == 5){
                     //System.out.println(Enemy.getHitCounter());
                     ShowEnemy = false;
                     Files.HighScore = score ;
                     file.ReloadLevel();
                     JOptionPane.showMessageDialog(this , "done" , "deid" , JOptionPane.INFORMATION_MESSAGE);
                  }
         }   
            }//End If ShowEnemy
            if(DrawBricks){
                 int bricksXpos = 0 , bricksYpos = 0  ;
                bricksLoop:for(int i = 0; i < Bricks.getBricksMap().length ; i++){
                                     for(int j = 0 ; j < Bricks.getBricksMap()[0].length;j++){
                                       if(Bricks.getBricksMap()[i][j] > 0){
                                           bricksXpos = (j * Bricks.getBricksWidth()+Bricks.xPos);
                                           bricksYpos = (i * Bricks.getBricksHeight() + Bricks.yPos);
                                           
                                           Rectangle bricksRec = new Rectangle(bricksXpos , bricksYpos , Bricks.getBricksWidth() - 5,Bricks.getBricksHeight());
                                           Rectangle ballRec = new Rectangle(Ball.getPosX(),Ball.getPosY(),Ball.getWidth(),Ball.getHeight());
                                            if(ballRec.intersects(bricksRec)){
                                                sound.ball_bricks();
                                              Ball_Bricks_Collision(i ,j ,bricksXpos ,bricksYpos);
                                               //Handle Bricks Border Collision
                                               if( (Ball.getPosX() + 19) <= bricksRec.x || (Ball.getPosX() +1) >= (bricksRec.x+bricksRec.width) ){
                                                   Ball.setDirX(-Ball.getDirX());  
                                               }else{
                                                   Ball.setDirY(-Ball.getDirY()); 
                                               }
                                           break bricksLoop;
                                       }//End If intersercts
                                            
                                             if(beamrec.intersects(bricksRec) || gunrec.intersects(bricksRec)){
                                                 sound.shoot_gun();
                                                 
                                                      Gun_Laser_Collision();
                                                      Ball_Bricks_Collision(i , j , bricksXpos , bricksYpos);
                                                      
                                             }
                                           
                                   }// End if (map[i][j] > 0)
                           }//End For J
                       }//End For i           
            }//End If Draw Bricks
       
            Ball.setPosY(Ball.getPosY() +Ball.getDirY());
            Ball.setPosX(Ball.getPosX() +Ball.getDirX()); 
             
             Borders_Collision();
        }
        if(repaintBool){
               repaint();
        }
    }
    
    
    //Player Actions 
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(MainMenu.getPhoneBool()){
                if( (Paddle.getPosX() + Paddle.getWidth()) - 100  >= 1030){
                if(Paddle.getWidth() > 125 ){
                Paddle.setPosX(1000);
                }else{
                   Paddle.setPosX(1030);
                }
            }else{
                MoveRight(20);
            }
            }else{
                if( (Paddle.getPosX() + Paddle.getWidth() ) - 100 >=this.getSize().width - 180){
                    if(Paddle.getWidth() > 175 ){
                    Paddle.setPosX(this.getSize().width - 400 );
                    }else{
                       Paddle.setPosX(this.getSize().width - 200);
                    }
               }else{
                MoveRight(50);
              }   
       
            }
            
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(Paddle.getPosX() < 25){
              Paddle.setPosX(7);
            }else{
                MoveLeft(50);
            }
            
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                 ShootGun.CurrentPaddleXpos = Paddle.getPosX();
                LaserBeam.CurrentPaddleXpos = Paddle.getPosX();
                pressed = true;
                if(Capsules.GunBool){
                ShootGun.numOfPistols--;
                }else if(Capsules.LaserBool){
                LaserBeam.NumOfBeam--;
                }
        }else if(e.getKeyCode() == KeyEvent.VK_A){
            Arrow = StickyBall.Left_Arrow;
                Ball.setDirX(-1);
            
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            Arrow = StickyBall.Right_Arrow;
                Ball.setDirX(1);
        }else if(e.getKeyCode() == KeyEvent.VK_W){
               Arrow = StickyBall.Up_Arrow;
                 Ball.setDirX(0);
                 Ball.setPosX(Ball.getPosX() +Ball.getDirX());
        }
    }
    
    public void MoveRight(int Speed){
        start = true;
       Paddle.setPosX( Paddle.getPosX() + Speed);
    }
    
    public void MoveLeft(int Speed){
        start = true;
        //BackGround = false;
         Paddle.setPosX( Paddle.getPosX() - Speed);
    }

    public boolean EndGame(){
        return (Ball.getPosY() >= Paddle.getPosY()+Paddle.getHeight());
    }
    public boolean StartGame(){
        return start;
    }
    
    public void ClearCapsules(){
        this.cap.clear();
        this.CapsulesCounter = 0 ;
         ArrayList <Capsules> NewList = new ArrayList<Capsules>(4);
         cap = NewList;
         // Choose id of Capsules
        for(int i = 0 ; i < 4 ; i++){
            int id = rand.nextInt(5) + 1;
           if(id == 1){
               cap.add(shootGun[i]); //polymorphism shootGun
           }else if(id == 2){
            cap.add(shootGun[i]); //polymorphism Laser
           }else if(id == 4){
            cap.add(shootGun[i]); //polymorphism Life
           }else if(id == 3){
            cap.add(shootGun[i]); //polymorphism ChangePaddleSize
           }else if(id == 5 ){
            cap.add(shootGun[i]); //polymorphism Sticky
           }
        }

        
    }//End Clear Function

    public void LevelFinished(){
        
        if( Bricks.getBricksNum() == 0){
            nextLevel = true;
            drawPaddle = false;
            drawBall = false;
              if(Files.CurrentLevel .equals("1") || Levels.getChooseLevel().equals("1")){
                      if(Levels.getChooseLevel().equals("0") || ( Integer.valueOf(Levels.getChooseLevel()) == Integer.valueOf(checkLevel) ) ){
                        Files.CurrentLevel = "2";
                        Levels.setChooseLevel("0");
                       }else{
                       Levels.setChooseLevel("2");
                       }
                       Files.HighScore = score ;
                       System.out.println(Files.HighScore + " " +score );
                       file.ReloadLevel();
                       this.score = 0;
                       level.Level2();
             }else if(Files.CurrentLevel .equals("2") || Levels.getChooseLevel().equals("2")){
                  if(Levels.getChooseLevel().equals("0") || ( Integer.valueOf(Levels.getChooseLevel()) == Integer.valueOf(checkLevel) ) ){
                       Files.CurrentLevel = "3";
                       Levels.setChooseLevel("0");
                  }else{
                       Levels.setChooseLevel("3");
                  }
                       Files.HighScore = score ;
                       file.ReloadLevel();
                        this.score = 0;
                        level.Level3();

                  }else if(Files.CurrentLevel .equals("3") || Levels.getChooseLevel().equals("3")){
                            if(Levels.getChooseLevel().equals("0") || ( Integer.valueOf(Levels.getChooseLevel()) == Integer.valueOf(checkLevel) ) ){
                            Files.CurrentLevel = "4";
                            Levels.setChooseLevel("0");
                            }else{
                            Levels.setChooseLevel("4");
                             }
                           Files.HighScore = score ;
                            file.ReloadLevel();
                            this.score = 0;
                            level.Level4();
                  }else if(Files.CurrentLevel .equals("4") || Levels.getChooseLevel().equals("4")){
                      if(Levels.getChooseLevel().equals("0") || ( Integer.valueOf(Levels.getChooseLevel()) == Integer.valueOf(checkLevel) ) ){
                       Files.CurrentLevel = "5";
                       Levels.setChooseLevel("0");
                  }else{
                       Levels.setChooseLevel("5");
                  }
                       Files.HighScore = score ;
                       file.ReloadLevel();
                       this.score = 0;
                       level.Level5();
                    //Calling Enemy Function
              }else if(Files.CurrentLevel.equals("5") || Levels.getChooseLevel().equals("5")){
                   if(Levels.getChooseLevel().equals("0") || ( Integer.valueOf(Levels.getChooseLevel()) == Integer.valueOf(checkLevel) ) ){
                       Files.CurrentLevel = "Enemy";
                       Levels.setChooseLevel("0");
                  }else{
                       Levels.setChooseLevel("5");
                  } 
                  Files.HighScore = score ;
                  file.ReloadLevel();
                  this.score = 0;
                  ShowEnemy = true;
                  DrawBricks = false;
              }
              
              if(MainMenu.getPhoneBool()){
          Paddle.setPosX(880);
          Paddle.setPosY(900);
          Paddle.setPaddleImage("Normal-Paddle.png");
          Ball.setPosX(Paddle.getPosX() + 80);
          Ball.setPosY(Paddle.getPosY() - 10);
              }else{
          Paddle.setPosX(900);
          Paddle.setPosY(900);
          Paddle.setPaddleImage("Normal-Paddle.png");
          Ball.setPosX(Paddle.getPosX() + 80);
          Ball.setPosY(Paddle.getPosY() - 10);
              }
         
           start = false;
          drawCap = false;
          Capsules.GunBool = false;
          Capsules.LaserBool = false;
          StickyBall.isSticky = false;
          ClearCapsules();
          bricks = new Bricks();
        }//End If  BricksNum == 0
    }
    
    public void Gun_Laser_Collision(){
            pressed = false;
            Laser[0].BeamXpos = Paddle.getPosX();
            Laser[0].BeamYpos = Paddle.getPosY();
            shootGun[0].shootXpos = Paddle.getPosX();
            shootGun[0].shootYpos = Paddle.getPosY();
    }
    
    public void Ball_Bricks_Collision( int i , int j , int bricksXpos ,int bricksYpos ){
          if(Bricks.hitCounter[i][j] > 1){
                Bricks.hitCounter[i][j]--;
                }else{
                Bricks.setBricksNum(Bricks.getBricksNum() - 1);
                 bricks.setBrickValue(0, i, j);
                 FindCapsules(bricksXpos , bricksYpos);
                score+=5;
                LevelFinished();
                     
              }
    }
    
    public void Borders_Collision(){
        if(MainMenu.getPhoneBool()){
            //Left Collision
            if(Ball.getPosX() <= 750 ){
                Ball.setDirX(-Ball.getDirX());

            }
            //Top Collision
            if(Ball.getPosY() <= 120){
                Ball.setDirY(-Ball.getDirY());

            }
            //Right Collision
            if(Ball.getPosX() > 1145){
                Ball.setDirX(-Ball.getDirX());
            }
        }else{
            //Left Collision
            if(Ball.getPosX() <= 0 ){
                Ball.setDirX(-Ball.getDirX());

            }
            //Top Collision
            if(Ball.getPosY() <= 0){
                Ball.setDirY(-Ball.getDirY());

            }
            //Right Collision
            if(Ball.getPosX() > this.getSize().width-38){
                Ball.setDirX(-Ball.getDirX());
            }
        }
         
    }
    
    public void FindCapsules(int bricksXpos , int bricksYpos){
          for(int z = 0 ; z < 4;z++){
            if( (Bricks.getRandomCapposX()[z] == bricksXpos) && (Bricks.getRandomCapposY()[z] == bricksYpos)  && (CapsulesCounter < 4)){
             System.out.println(CapsulesCounter );
             cap.get(CapsulesCounter).posX = Bricks.getRandomCapposX()[z];
             cap.get(CapsulesCounter).posY = Bricks.getRandomCapposY()[z];
             drawCap = true;   
             CapsulesCounter++;
             break;
             }
       }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
      @Override
    public void keyReleased(KeyEvent e) {}

    
    @Override
public void mouseClicked(MouseEvent e) {
       Object obj = e.getSource();
        if(obj.equals(Pause)){
            drawBall = false;
            drawPaddle = false;
        }else if(obj.equals(Continue)){
            drawBall = true;
            drawPaddle = true;
        }else if(obj.equals(Back)){
          this.setVisible(false);
          new MainMenu().setVisible(true);
        }
        else if(obj.equals(Next)){
            
             drawBall = true;
            drawPaddle = true;
            if(nextLevel){
             if(Files.CurrentLevel.equals("1")){
                                   // Files.setCurrentLevel("2");
                                        Files.CurrentLevel = "2";
                                         level.Level2();
                                }else if(Files.CurrentLevel .equals("2")){
                                       //Files.setCurrentLevel("3");
                                         Files.CurrentLevel = "3";
                                          level.Level3();

                                }else if(Files.CurrentLevel .equals("3")){
                                       //Files.setCurrentLevel("4");
                                         Files.CurrentLevel = "4";
                                         level.Level4();

                                }else if(Files.CurrentLevel.equals("4")){
                                       //Files.setCurrentLevel("5");
                                         Files.CurrentLevel = "5";
                                         level.Level5();
                                }else if(Files.CurrentLevel.equals("5")){
                                    ShowEnemy = true;
                                    DrawBricks = false;
                                    Ball.setPosX(Paddle.getPosX());
                                    Ball.setPosY(Paddle.getPosY() - 500 );
                                    //Ball.setDirY( -Ball.getDirY());
                                }
                                                   this.score = 0;
                                                   file.ReloadLevel();
                                                   bricks = new Bricks();
             nextLevel = false;
            }
        }
        else if(obj.equals(End)){
            this.setVisible(false);
           new MainMenu().setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
