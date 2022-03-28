package oop_project_temp;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Collections.list;

public class Files {
    
  //  public String []setNameData;
    public String []getNameData;
  //  public String []setLevelData;
    public String []getLevelData;
    public String []getScoreData;
    public int Namescount;
    public int Levelscount;
    public int ScoresCount;
    public static String CurrentName ;
    public static String CurrentLevel ;
    public static int HighScore ;



    
    
    public Files(){
       this.Namescount = 0;
       this.Levelscount = 0;
       this.getNameData = new String[100];
       this.getScoreData = new String[100];
       this.getLevelData = new String[100];
       this.ScoresCount = 0;
       this.Load();
    }
    //Get Data From File
    public void Load(){
        try{
        FileReader readNames = new FileReader("ReadNames.txt");
        FileReader readLevels = new FileReader("ReadLevels.txt");
        FileReader readScores = new FileReader("ReadScores.txt");
        
        BufferedReader readNamesFile = new BufferedReader(readNames);
        BufferedReader readLevelsFile = new BufferedReader(readLevels);
        BufferedReader readScoresFile = new BufferedReader(readScores);
        
        String temp;
        while((temp = readNamesFile.readLine()) != null){
            getNameData[Namescount] = temp;
          //System.out.println(   getNameData[Namescount] + " HERE 1");
            Namescount++;
        }
        temp ="";
        while((temp = readLevelsFile.readLine()) != null){
            getLevelData[Levelscount] = temp;
           //System.out.println(   getLevelData[Levelscount] + " HERE 2");
            Levelscount++;
        }
        
         temp ="";
        while((temp = readScoresFile.readLine()) != null){
            getScoreData[ScoresCount] = temp;
           // System.out.println(  getScoreData[ScoresCount] + " HERE 3");
            ScoresCount++;
        }
        
        //System.out.println(getData[count]);
        readNamesFile.close();
        readLevelsFile.close();
        readScoresFile.close();
        }catch(IOException e){}
    }
    
    //Set Data To File 
    public void ReLoad(String setName ){
             try{
        FileWriter NameFile = new FileWriter("ReadNames.txt");
        FileWriter LevelFile = new FileWriter("ReadLevels.txt");
        FileWriter ScoreFile = new FileWriter("ReadScores.txt");
        PrintWriter NamewriteFile = new PrintWriter(NameFile);
        PrintWriter LevelswriteFile = new PrintWriter(LevelFile);
        PrintWriter ScoreswriteFile = new PrintWriter(ScoreFile);

        this.getNameData[this.Namescount] = setName;
        this.getLevelData[this.Levelscount] = "1";
        this.getScoreData[this.ScoresCount] = "0";
        for(int c  = 0 ; c < this.getNameData.length ;c++){
            if(getNameData [c] != null){
               NamewriteFile.println(getNameData[c]);
               LevelswriteFile.println(getLevelData[c]);
               ScoreswriteFile.println(getScoreData[c]);
            }
            
           CurrentName =   this.getNameData[this.Namescount];
           CurrentLevel = getLevelData[this.Levelscount];
           HighScore = 0;
        }
        NamewriteFile.close();
        LevelswriteFile.close();
        ScoreswriteFile.close();
        }catch(IOException e){}
   
    }//End Reload Function
    
    
    public void ReloadLevel(){
              try{
                  FileWriter NameFile = new FileWriter("ReadNames.txt");
                  FileWriter LevelFile = new FileWriter("ReadLevels.txt");
                  FileWriter ScoreFile = new FileWriter("ReadScores.txt");
                  PrintWriter NamewriteFile = new PrintWriter(NameFile);
                  PrintWriter LevelswriteFile = new PrintWriter(LevelFile);
                  PrintWriter ScoreswriteFile = new PrintWriter(ScoreFile);
                  for(int c = 0 ; c < this.getNameData.length ; c++){
                       if(CurrentName.equals(getNameData[c]) ){
                           getLevelData[c] = CurrentLevel;
                           getScoreData[c] = String.valueOf(HighScore);
                           break;
                       }
                  }
                  
                  for(int i =0 ; i < this.getLevelData.length ; i++){
                      if(getLevelData[i] != null){
                      LevelswriteFile.println(getLevelData[i]);
                      NamewriteFile.println(getNameData[i]);
                      ScoreswriteFile.println(getScoreData[i]);
                      }
                  }
                NamewriteFile.close();
                LevelswriteFile.close();
                ScoreswriteFile.close();
            }catch(IOException e){}  
    }//End Function
}//End Class
