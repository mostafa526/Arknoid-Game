package oop_project_temp;

import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bricks{
    
    private static int map [][];
    private static int bricksWidth;
    private static int bricksHeight;
    private static int capposX[] ;
    private static int capposY[] ;
    private static int  RandomcapposX[] ;
    private static int  RandomcapposY[] ;
    private Random rand;
    private static int row;
    private static int col;
    private static int BricksNum;
    public static int hitCounter [ ] [ ] ;
    public String ImageAddress ; 
    private boolean countx ;
    private Image ThreeHitBrick;
    private Image TwoHitBrick;
    private Image OneHitBrick;
    public static int  xPos ;
    public static int yPos;

    
 
    public Bricks(){
     
        capposX = new int[col];
        capposY = new int[row];
        RandomcapposY = new int[4];
        RandomcapposX = new int[4];
        countx =true;
        rand = new Random();
        map = new int[row][col];
    
        if(MainMenu.getPhoneBool()){
        xPos = 770;
        yPos = 200;      
        bricksHeight = 300 / (row * 2);
        bricksWidth = 740 / (col * 2);
        }else{
        xPos =650;
        yPos = 100;
        bricksHeight = 200 / row ;
        bricksWidth = 640 / col ;
        }
        
        
        for(int i = 0 ; i < row ; i++ ){
            for(int j = 0;j < col ; j++){
                map[i][j] = 1;
                if(countx){
                    capposX[j] = (j * bricksWidth +xPos);
                }
            }
            capposY[i] =  (i* bricksHeight +yPos);
            countx = false;
            BricksNum = row * col;
        }
        
        for(int i =0 ;i<4;i++){
                 int x = rand.nextInt(col);
                 RandomcapposX[i] = capposX[x];
                 int y =rand.nextInt(row);
                 RandomcapposY[i] = capposY[y];
        }
          
        // Handle Bricks Intensity
        this.rand = new Random();
        this.hitCounter = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0;  j < col ;j++){
                hitCounter[i][j] = 1;//rand.nextInt(3) + 1;
           }
        }
        try{
            ThreeHitBrick = ImageIO.read(new File("b9.jpg"));
            TwoHitBrick = ImageIO.read(new File("b99.jpg"));
            OneHitBrick = ImageIO.read(new File("b999.jpg"));

        }catch(IOException ex){}
                
    }
    
    public void draw(Graphics2D graph , GamePlay g){
        for(int i = 0 ; i < map.length ; i++){
            for(int j = 0 ; j < map[0].length;j++){
                if(map[i][j] > 0){
                    if(hitCounter[i][j] == 1){
                      graph.drawImage(OneHitBrick,(j * bricksWidth +xPos),(i * bricksHeight + yPos),bricksWidth ,bricksHeight,g);
                    }else if(hitCounter[i][j] == 2){
                     graph.drawImage(TwoHitBrick,(j * bricksWidth +xPos),(i * bricksHeight + yPos),bricksWidth ,bricksHeight,g);
                    }
                    else if(hitCounter[i][j] == 3){
                      graph.drawImage(ThreeHitBrick,(j * bricksWidth +xPos),(i * bricksHeight + yPos),bricksWidth ,bricksHeight,g);
                    }
                    BasicStroke stroke = new BasicStroke(3); // To make the border width of empty Rectangle 3
                    graph.setStroke(stroke);
                    graph.setColor(Color.WHITE);
                    graph.drawRect((j * bricksWidth +xPos) , (i * bricksHeight + yPos) , bricksWidth,bricksHeight);
                    //graph.drawString(""+hitCounter[i][j],(j * bricksWidth +700)+(bricksWidth/2) , (i * bricksHeight + 200) +(bricksHeight/2) );
                }
        }
        }

    }
    
    
    public void setBrickValue(int value , int row , int col){
         this.map[row][col] = value;
    }
    
    
    public static int getBricksWidth(){
        return bricksWidth;
    }
    
    public static void setBricksWidth( int Width){
          bricksWidth = Width;
    }
    
    
    public static int getBricksHeight(){
        return bricksHeight;
    }
    
    public static void setBricksHeight( int Height){
        bricksHeight = Height;
    }
    
    
    
    public static int getRow(){
        return row;
    }
    
    public static void setRow( int Row){
        row = Row;
    }
    
    public static int getCol(){
        return col;
    }
    
    public static void setCol( int Col){
        col = Col;
    }
    
    
    public static int getBricksNum(){
        return BricksNum;
    }
    
    public static void setBricksNum( int bricksNum){
        BricksNum = bricksNum;
    }
    
     public static int[] getCapPosX(){
        return capposX;
    }
    
    public static void setCapPosX( int []Xpos){
        capposX = Xpos;
    }
    
    
     public static int[] getCapPosY(){
        return capposY;
    }
    
    public static void setCapPosY( int []Ypos){
        capposY = Ypos;
    }
    
     public static int[] getRandomCapposX(){
        return RandomcapposX;
    }
    
    public static void setRandomCapposX( int []RandomCapposX){
        RandomcapposX = RandomCapposX;
    }
    
    
    public static int[] getRandomCapposY(){
        return RandomcapposY;
    }
    
    public static void setRandomCapposY( int []RandomCapposY){
        RandomcapposY = RandomCapposY;
    }
    
   public static int[][] getBricksMap(){
        return map;
    }
    
    public static void setBricksMap( int [][]Map){
        map = Map;
    }
    
}
