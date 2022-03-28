package oop_project_temp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MainMenu extends JFrame implements ActionListener {
    private JButton newPlayer;
    private JButton SelectPlayer;
    private JButton highScores;
    private JButton exit;
    private Image LogoImage;
    private static Image PhoneImage;
    private Files File;
    private static boolean PhoneScreen;
    private  JFrame myGameframe;


    //private Image IphoneImage; 
    
    public MainMenu(){
        this.setTitle("Main-Menu");
        this.setSize(2000,2000);
        this.setLayout(null);
        File = new Files();
        myGameframe = new JFrame();
    newPlayer=new JButton("New Player");
    newPlayer.setFont(new Font("Arial", Font.PLAIN, 30));
    if(PhoneScreen){
    newPlayer.setBounds(750, 600, 400, 50);
    }else{
           newPlayer.setBounds(700, 600, 500, 50);
    }
    newPlayer.setBackground(Color.BLACK);
    newPlayer.setForeground(Color.WHITE);
    
    SelectPlayer=new JButton ("Select Player");
    SelectPlayer.setFont(new Font("Arial", Font.PLAIN, 30));
    SelectPlayer.setBounds(newPlayer.getBounds().x,newPlayer.getBounds().y+80, newPlayer.getBounds().width, newPlayer.getBounds().height);
    SelectPlayer.setBackground(Color.BLACK);
    SelectPlayer.setForeground(Color.WHITE);
    
    highScores=new JButton ("High Scores");
    highScores.setBounds(SelectPlayer.getBounds().x, SelectPlayer.getBounds().y + 80, SelectPlayer.getBounds().width, SelectPlayer.getBounds().height);
    highScores.setFont(new Font("Arial", Font.PLAIN, 30));
     highScores.setBackground(Color.BLACK);
    highScores.setForeground(Color.WHITE);
    
    exit=new JButton ("Exit");
    exit.setBounds(highScores.getBounds().x,highScores.getBounds().y+80, highScores.getBounds().width, highScores.getBounds().height);
    exit.setFont(new Font("Arial", Font.PLAIN, 30));
    exit.setBackground(Color.BLACK);
    exit.setForeground(Color.WHITE);
    try{
        LogoImage = ImageIO.read(new File("Logo5.jpg") );
        PhoneImage = ImageIO.read(new File("iphone-Screen1.png"));
    }catch(IOException e){}
    exit.setBorder(new LineBorder(Color.WHITE));
    SelectPlayer.addActionListener(this);
    newPlayer.addActionListener(this);
    highScores.addActionListener(this);
    exit.addActionListener(this);
    
   Container put = getContentPane();
    put.setBackground(Color.BLACK);
    put.add(newPlayer);
    put.add(SelectPlayer);
    put.add(highScores);
    put.add(exit);
    
    
    
    }
    
   
    @Override
    public void paint(Graphics graph){
        super.paint(graph);
        if(PhoneScreen){
        graph.drawImage(PhoneImage,680,60,550,1000, this);
        graph.drawImage(LogoImage,785,200,350,350, this);
        }else{
        graph.drawImage(LogoImage,newPlayer.getBounds().x+10,100,500,500, this);
        }

       // graph.drawImage(IphoneImage,newPlayer.getBounds().x - 500,50,500,450, this);
 }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if( obj.equals(newPlayer) ){
            JFrame newPlayerFrame = new JFrame();
            newPlayerFrame.setSize(2000,2000);
            newPlayerFrame.getContentPane().setBackground(Color.BLACK);
            newPlayerFrame.setLayout(null);
            JLabel playerName = new JLabel();
            playerName.setText("Player's Name");
            playerName.setFont(new Font("serif",Font.PLAIN,30));
            playerName.setForeground(Color.WHITE);
            playerName.setBounds(860,250,200,200);
            JTextField takePlayerName = new JTextField();
            takePlayerName.setBounds(800,400,300,50);
            JButton Ok = new JButton("OK");
            Ok.setBounds(900,470,100,100);
             Ok.setBackground(Color.BLACK);
            Ok.setForeground(Color.WHITE);
            newPlayerFrame.getContentPane().add(playerName);
            newPlayerFrame.getContentPane().add(takePlayerName);
            newPlayerFrame.getContentPane().add(Ok);
            
            JButton Back = new JButton("Back");
            Back.setBounds(50,50,100,100);
            Back.setBackground(Color.BLACK);
            Back.setForeground(Color.WHITE);
            newPlayerFrame.getContentPane().add(Back);
            
            Ok.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                    String setNewName = takePlayerName.getText();
                    boolean exist = false;
                    for(int c = 0 ; c < File.getNameData.length ; c++){
                                if(setNewName.equals(File.getNameData[c])){ 
                                    exist = true;
                                    break;
                                }
                    }
                    if(exist){
                        throw new InvalidException("Sorry Choose Another Name , It's Already Exist");
                    }
                    File.ReLoad(setNewName);
                    Levels.setChooseLevel("0");
                    PlayLevel();
                    newPlayerFrame.setVisible(false);
                    }catch(InvalidException ex){
                        JOptionPane.showMessageDialog(null , ex.getMessage() ,"Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            
          
            Back.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                        MainMenu main = new MainMenu();
                        main.setVisible(true);
                        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        newPlayerFrame.setVisible(false);
                }
            });
             
             newPlayerFrame.setVisible(true);
             this.setVisible(false);
             newPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
             
        }else if(obj.equals(SelectPlayer)){
                JFrame newPlayerFrame = new JFrame();
                newPlayerFrame.setSize(2000,2000);
                newPlayerFrame.getContentPane().setBackground(Color.BLACK);
                newPlayerFrame.setLayout(null);
                
                DefaultTableModel Model = new DefaultTableModel();;
                JTable Table =new JTable(Model);
                JScrollPane Scroll = new JScrollPane(Table);      
                
                Model.addColumn("Name");
                Model.addColumn("Level");
                Model.addColumn("High Score");
                Scroll.setBounds(700,400,500,200); 
                for(int c = 0 ; c  < File.getNameData.length ; c++){
                  Model.addRow(new Object[]{File.getNameData[c] , File.getLevelData[c] ,File.getScoreData[c]});
                }
             
             
             
             JTextField selectOne = new JTextField(10);
             selectOne.setBounds(Scroll.getBounds().x + 200 , Scroll.getBounds().y +250 , 200,50);
             JButton GO = new JButton();
             GO.setBounds(Scroll.getBounds().x + 200, Scroll.getBounds().y + 320 , 100,50);
             GO.setText("GO");
            GO.setBackground(Color.BLACK);
            GO.setForeground(Color.WHITE);
             
             
             newPlayerFrame.add(Scroll);
             newPlayerFrame.add(selectOne);
             newPlayerFrame.add(GO);
             
             
             
            JButton Back = new JButton("Back");
            Back.setBounds(50,50,100,100);
            Back.setBackground(Color.BLACK);
            Back.setForeground(Color.WHITE);
            newPlayerFrame.getContentPane().add(Back);
            Back.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                        MainMenu main = new MainMenu();
                        main.setVisible(true);
                        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        newPlayerFrame.setVisible(false);
                    }
               });
             
             
             GO.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                       
                        try{
                             String takeName = selectOne.getText() ;
                           boolean found = false;
                            for(int c = 0 ; c  < File.getNameData.length ; c++){
                                if(File.getNameData[c] != null){
                                    if(takeName.equals(File.getNameData[c])){
                                        Files.CurrentName = File.getNameData[c];
                                        Files.CurrentLevel = File.getLevelData[c];
                                        Files.HighScore =Integer.valueOf(File.getScoreData[c]);
                                        found = true;
                                        break;
                                    } } }
                                    if(!found){
                                      throw new InvalidException("Sorry, This Name Does't Exist !!!");
                                    }

                                    //Choose Level From Available Levels
                                    JFrame ChooseAvailableLevel = new JFrame();
                                    ChooseAvailableLevel.setTitle("Availabel Levels");
                                    ChooseAvailableLevel.setSize(2000,2000);
                                    ChooseAvailableLevel.getContentPane().setBackground(Color.BLACK);
                                    ChooseAvailableLevel.setLayout(null);
                                    JButton []LevelsButtons;
                                    int size = 1;
                                    if(Files.CurrentLevel.equals("Enemy")){
                                        size = 6;
                                        LevelsButtons = new JButton[size];
                                    }else{
                                        size = Integer.valueOf(Files.CurrentLevel);
                                      LevelsButtons = new JButton[size];
                                    }
                                    for(int c = 0 ; c < size;c++){
                                        LevelsButtons[c] = new JButton();
                                        if(c == 5){
                                           LevelsButtons[c].setText("ENEMY");
                                        }else{
                                        LevelsButtons[c].setText("Level "+(c+1));
                                        }
                                        LevelsButtons[c].setBounds((100 * c) + 700   , 500  , 100 , 100);
                                        LevelsButtons[c].setBackground(Color.BLACK);
                                        LevelsButtons[c].setForeground(Color.WHITE);
                                        
                                        ChooseAvailableLevel.add(LevelsButtons[c]);
                                    }
                                    
                                   for(int c = 0 ; c<size;c++){
                                         LevelsButtons[c].addActionListener(new ActionListener(){
                                             @Override
                                             public void actionPerformed(ActionEvent e) {
                                                 if(LevelsButtons[0].equals(e.getSource())){
                                                     if(Files.CurrentLevel.equals("1")){
                                                    Levels.setChooseLevel("0");
                                                     }else{
                                                    Levels.setChooseLevel("1");
                                                     }
                                                     PlayLevel();
                                                 }else if(LevelsButtons[1].equals(e.getSource())){
                                                      if(Files.CurrentLevel.equals("2")){
                                                    Levels.setChooseLevel("0");
                                                     }else{
                                                    Levels.setChooseLevel("2");
                                                     }
                                                     PlayLevel();
                                                 }else if(LevelsButtons[2].equals(e.getSource())){
                                                     if(Files.CurrentLevel.equals("3")){
                                                    Levels.setChooseLevel("0");
                                                     }else{
                                                    Levels.setChooseLevel("3");
                                                     }
                                                     PlayLevel();
                                                 }else if(LevelsButtons[3].equals(e.getSource())){
                                                      if(Files.CurrentLevel.equals("4")){
                                                    Levels.setChooseLevel("0");
                                                     }else{
                                                    Levels.setChooseLevel("4");
                                                     }
                                                    PlayLevel();
                                                 }else if(LevelsButtons[4].equals(e.getSource())){
                                                      if(Files.CurrentLevel.equals("5")){
                                                    Levels.setChooseLevel("0");
                                                     }else{
                                                    Levels.setChooseLevel("5");
                                                     }
                                                     PlayLevel();
                                                 }else if(LevelsButtons[5].equals(e.getSource())){
                                                     if(Files.CurrentLevel.equals("Enemy")){
                                                     Levels.setChooseLevel("0");
                                                     }else{
                                                     Levels.setChooseLevel("Enemy");
                                                     }
                                                     
                                                     PlayLevel();
                                                 }
                                                 ChooseAvailableLevel.setVisible(false);
                                             }
                                           });
                                      }
                                ChooseAvailableLevel.setVisible(true);
                                ChooseAvailableLevel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                
                                newPlayerFrame.setVisible(false);
                        }catch(InvalidException ex){ 
                            JOptionPane.showMessageDialog(null,ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }
             });
             newPlayerFrame.setVisible(true);
             this.hide();
             newPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
             }else if(obj.equals(highScores)){                 // Click HighScore  
            JFrame newPlayerFrame = new JFrame();
            newPlayerFrame.setSize(2000,2000);
            newPlayerFrame.getContentPane().setBackground(Color.BLACK);
            newPlayerFrame.setLayout(null);
            
             DefaultTableModel Model = new DefaultTableModel();;
             JTable Table =new JTable(Model);
             JScrollPane Scroll = new JScrollPane(Table);      
                
             Model.addColumn("Name");
             Model.addColumn("Level");
             Model.addColumn("High Score");
             Scroll.setBounds(700,400,500,200); 
             
             int [] sorting = SortingScores(File.getScoreData);
             int [] getindex = SortingNAndL(sorting);
             // System.out.println(getindex.length + " " + sorting.length);

             for(int c = 0 ; c  < sorting.length ; c++){
                  Model.addRow(new Object[]{File.getNameData[getindex[c]] , File.getLevelData[getindex[c]] ,sorting[c]});
             }
             
            newPlayerFrame.add(Scroll);
            
            JButton Back = new JButton("Back");
            Back.setBounds(50,50,100,100);
            Back.setBackground(Color.BLACK);
            Back.setForeground(Color.WHITE);
            newPlayerFrame.getContentPane().add(Back);
            
            Back.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                        MainMenu main = new MainMenu();
                        main.setVisible(true);
                        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        newPlayerFrame.setVisible(false);
                }
            
            
            });
             
             newPlayerFrame.setVisible(true);
             this.setVisible(false);
             newPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else if(obj.equals(exit)){
                 System.exit(0);
             }
        
        repaint();
        
    }
    
    public int[] SortingScores(String [] Scores){
            int [] convert ;
            int size = 0 ;
             for(int i = 0 ; i < Scores.length ; i++ ){
                   if(Scores[i] == null){
                break;
            }
                   size++;
                }
             convert = new int[size];
        for(int i = 0 ; i < size ; i++){
                convert[i] = Integer.valueOf(Scores[i]);
            }
        int temp = 0 ;
        for(int i = 0 ; i < size ; i++ ){
            for(int j = 0 ; j < size ;j++){
                if(convert[i] > convert[j]){
                    temp = convert[i];
                    convert[i] = convert[j];
                    convert[j] = temp;
                    j=0;
                }
            }
        }
                return convert;
    }
    
    public int [] SortingNAndL(int []convert){
        int []index = new int[convert.length ];
        int count = 0 ;
        String [] temp = File.getScoreData;
        //System.out.println(File.getScoreData.length +" " + index.length );
        for(int i = 0 ; i < index.length ; i++){
            for(int  j = 0 ; j < index.length ; j++){
                   if(String.valueOf(convert[i]).equals(temp[j]) ){
                    index[count] = j;
                     count++;
                     temp[j] ="-1" ;
                     break;
                   }
            }
        }
        return index;
    }
    public void PlayLevel(){
           GamePlay game = new GamePlay();
           myGameframe.setTitle("BreakOut Game");
           myGameframe.setSize(2000,2000);
           myGameframe.setResizable(false);
           myGameframe.add(game);
           myGameframe.setVisible(true);
           myGameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static boolean getPhoneBool(){
        return PhoneScreen;
    }
    
    public static void setPhoneBool(boolean Screen){
        PhoneScreen = Screen;
    }
    
    public static Image getPhoneImage(){
        return PhoneImage;
    }
    
}
