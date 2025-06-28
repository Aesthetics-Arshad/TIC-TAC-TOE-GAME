/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public  class MyGame extends JFrame implements ActionListener{
    JLabel heading,clockLabel;
    Font font=new Font("",Font.BOLD,40);
    JPanel mainPanel;
    JButton []btns=new JButton[9];
    
    //game instance variable..
    
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int wps[][]={
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}

    };
    
    int winner=2;
    boolean GameOver=false;
    int playerXwins=0;
    int player0wins=0;
    JLabel scorelabel;
    JPanel headerPanel;
    
    
    MyGame(){
        System.out.println("creating instance");
        setTitle("My Tic Tac Toe Game..");
        setSize(850,850);
        ImageIcon icon =new ImageIcon("src/img/images.png");
        setIconImage(icon.getImage());
        setLayout(new BorderLayout());
       
        
        heading = new JLabel("TIC TAC TOE");
heading.setFont(font);
heading.setHorizontalAlignment(SwingConstants.CENTER);
heading.setForeground(Color.white);
        
        
// Create and add the score label at the top
scorelabel = new JLabel("Player X: 0 | Player 0: 0");
scorelabel.setFont(new Font("Arial", Font.BOLD, 18));
scorelabel.setHorizontalAlignment(SwingConstants.CENTER);
scorelabel.setForeground(Color.white);
// ✅ Header Panel for Score Label
headerPanel = new JPanel();
headerPanel.setLayout(new GridLayout(2, 1));
headerPanel.setBackground(Color.decode("#2196f3"));
headerPanel.add(heading);
headerPanel.add(scorelabel);

add(headerPanel, BorderLayout.NORTH); // Heading section

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
        // scoreboard
        
    }
    
    // function to set border layout
     private void createGUI(){
         // to set the color of background
         this.getContentPane().setBackground(Color.decode("#2196f3"));
         //this.setLayout(new BorderLayout());
         // north heading
//         heading=new JLabel("TIC TAC TOE");
//         
//         heading.setFont(font);
//         heading.setHorizontalAlignment(SwingConstants.CENTER);
//         heading.setForeground(Color.white);
//         
//
//        this.add(heading,BorderLayout.NORTH);
         
         
// layout & add buttons...


         // creating onject of JLabel for clock
         clockLabel=new JLabel("clock");
         clockLabel.setForeground(Color.white);

         clockLabel.setFont(font);
         clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
         this.add(clockLabel,BorderLayout.SOUTH);
         
         
         // thread to make a clock 
          Thread t=new Thread(){
              public void run(){
                  try{
                      while(true){
                          String datetime=new Date().toLocaleString();
                          clockLabel.setText(datetime);
                          Thread.sleep(1000);
                      }
                  }
                  catch(Exception e){
                      e.printStackTrace();
                  }
              }
          };
          t.start();
          
          /// panel section mid section
          mainPanel=new JPanel();
          mainPanel.setLayout(new GridLayout(3,3));
          for(int i=1;i<=9;i++){
              JButton btn=new JButton();
             // btn.setIcon(new ImageIcon("src/img/download.png"));
              btn.setBackground(Color.white);
              btn.setFont(font);
              mainPanel.add(btn);
              btns[i-1]=btn;// store btn in an array.
              btn.addActionListener(this);
              btn.setName(String.valueOf(i-1));
          }
          this.add(mainPanel,BorderLayout.CENTER
          );
         
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton=(JButton)e.getSource();
        String nameStr=currentButton.getName();
        System.out.println(nameStr);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int name=Integer.parseInt(nameStr.trim());
        
        
        
       
        
        // draw logic
         if(GameOver){
             
             JOptionPane.showMessageDialog(this,"Game Over...");
            
             return;
         }
        
        
         if(gameChances[name]==2){
             if(activePlayer==1){
                 currentButton.setIcon(new ImageIcon("src/img/download.png"));
                 gameChances[name]=activePlayer;
                 activePlayer=0;
             }
             else
             {
                 currentButton.setIcon(new ImageIcon("src/img/o-300x300.png"));
                 gameChances[name]=activePlayer;
                 activePlayer=1;
             }
             // finding winner 
             
             for(int[]temp:wps)
             {
                 if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]])&&gameChances[temp[2]]!=2){
                 winner=gameChances[temp[0]];
                 GameOver=true;
                  if(winner== 1)
        {
            playerXwins++;
        }
             else if(winner== 0)
        {
            player0wins++;
        }// update score label
                     scorelabel.setText("Player X: " + playerXwins + " | Player 0: " + player0wins);          
                 JOptionPane.showMessageDialog(null,"player "+ winner + " has won the game..");
                 // show score
                 
       
                 int i=JOptionPane.showConfirmDialog(this, "Do you Want To Play Again");
                 if(i==0){
                     this.setVisible(false);
                     new MyGame();
                 }
                 else
                 {
                     if(i==1){
                         System.exit(34234);
                     }else
                     {
                         
                     }
                 }
                 System.out.println(i);
                 break;
                 }
             }
             
         
         
         
         /// draw logic
         ///
         int c=0;
         for(int x:gameChances){
             if(x==2){
                 c++;
                 break;
             }
         }
         if(c==0&&GameOver==false)
         {
             JOptionPane.showMessageDialog(null, "Match Draw...");
             int i=JOptionPane.showConfirmDialog(null, "Do You Want To Play Again?..");
             if(i==0){
                 this.setVisible(false);
                 new MyGame();
             }
             else if(i==1){
                     System.exit(1212);
                 }else{
                     
                 }
                 GameOver=true;
             }

         
            }
                 
         else
         {
             JOptionPane.showMessageDialog(this,"position already occupied....");
         }
    }
     
    
}
