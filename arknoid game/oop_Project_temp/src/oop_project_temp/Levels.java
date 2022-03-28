package oop_project_temp;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Levels {

private static String ChooseLevel;
//private String []temp;
//private int counter;

public Levels(){
    setChooseLevel(getChooseLevel());
    /*counter = 0 ;
    temp = new String[10];
    try{
     FileReader ReadPattern = new FileReader("Level1-Pattern.txt");
     BufferedReader read = new BufferedReader(ReadPattern);
     while((temp[counter] = read.readLine()) != null){
         counter++;
     }
    }catch(IOException ex){}
  */
  //Bricks.setBricksMap(map);
}


public void Level1(){
   Bricks.setRow(1);
   Bricks.setCol(2) ;
   }

public void Level2(){

   Bricks.setRow(1);
   Bricks.setCol(2) ;
   Ball.setPosX(Paddle.getPosX());
   Ball.setPosY(Paddle.getPosY() - 50);
}


public void Level3(){
    Bricks.setRow(7);
   Bricks.setCol(7) ;
   Ball.setPosX(Paddle.getPosX());
   Ball.setPosY(Paddle.getPosY() - 50);
}

public void Level4(){
    Bricks.setRow(8);
   Bricks.setCol(8) ;
   Ball.setPosX(Paddle.getPosX());
   Ball.setPosY(Paddle.getPosY() - 50);
}

public void Level5(){
   Bricks.setRow(9);
   Bricks.setCol(9) ;
   Ball.setPosX(Paddle.getPosX());
   Ball.setPosY(Paddle.getPosY() - 50);
}

public void LevelEnemy(){
    GamePlay.ShowEnemy = true;
   GamePlay.DrawBricks = false;
    Bricks.setRow(1);
   Bricks.setCol(1) ;
   Ball.setPosX(Paddle.getPosX());
   Ball.setPosY(Paddle.getPosY() - 50);
}

public static String getChooseLevel(){
    return ChooseLevel;
}

public static void setChooseLevel(String level){
     ChooseLevel = level;
}
}
