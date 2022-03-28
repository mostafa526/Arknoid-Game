/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project_temp;
import sun.audio.*;
import java.io.*;


/**
 *
 * @author OWNER
 */
public class Sound {
 private InputStream music1 ;  
  private InputStream music2 ;
   private InputStream music3 ;  
 private InputStream music4 ;  
 private InputStream music5 ;
 private AudioStream audio1;
  private AudioStream audio2;
 private AudioStream audio3;
 private AudioStream audio4;
 private AudioStream audio5;


public  void ball_paddle(){
    try{
music1=new FileInputStream(new File("Sound Effect - Kick.Wav"));
audio1 =new AudioStream(music1);
AudioPlayer.player.start(audio1);
    }
    catch(IOException E){
    }

}

public void game_over(){

  try{
music2=new FileInputStream(new File("gameover sound.wav"));
audio2 =new AudioStream(music2);
AudioPlayer.player.start(audio2);
    }
    catch(IOException E){
    }

}

public void shoot_gun(){

 try{
music3=new FileInputStream(new File("pomb sound.wav"));
audio3 =new AudioStream(music3);
AudioPlayer.player.start(audio3);
    }
    catch(IOException E){
    }
}
    
 public void ball_bricks(){
  try{
music4=new FileInputStream(new File("brick sound.wav"));
audio4 =new AudioStream(music4);
AudioPlayer.player.start(audio4);
    }
    catch(IOException E){
    }
 
 
 }
 
 
}
