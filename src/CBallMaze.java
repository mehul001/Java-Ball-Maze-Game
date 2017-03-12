/*
Program: Assignment 2: Application - Ball Navigation of a Maze
Filename: CBallMaze.java
@author: © Mehul Chamunda (14406068)
Course: BSc Computing (Software Engineering) Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Gary Hill
@version: 1.0 Incorporates Artificial Intelligence!
Date: 29/04/2015
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CBallMaze extends JFrame implements ActionListener, ChangeListener
{
	private JButton jBGrid[][] = new JButton[13][16]/*2D Array*/, jBOption1, jBOption2, jBOption3, jBExit, jBUp, jBDown, JBLeft, JBRight, JBAct, JBReset, 
			JBRun, jBSpace1,jBSpace2,jBSpace3,jBSpace4,jBSpace5, jBPicture;
	private JPanel jPRight, jPLeft, jPBottom, jPRight1, jPRight2, jPRight3,  jPRight4, jPBottom1, jPBottom2;
	private JTextField jTOption, jTSquare, jTDirection; 
	private JLabel jOption,jSquare, jDirection, jSpeed;
	private	JSlider jSSpeed;
	private JMenuBar JMBMenubar;
	private JMenu JMControl, JMHelp, JMScenario, JMEdit ;
	private JMenuItem jMIAbout, jMIHowto, jMILeft, jMIRight, jMIUp, jMIDown, 
			jMIAct, jMIRun, JMIexit, jMIReset, JMIOption1, JMIOption2, JMIOption3;
	private int xBall =0, yBall=15, nBall=15, checkOption;
	private ImageIcon logo;
	Timer timer = new javax.swing.Timer (600, this);   

	public static void main(String[] args)
	{
		CBallMaze frame = new CBallMaze();
		frame.setSize(775, 650); // main window size
		frame.createGUI();
		frame.setVisible(true);
		frame.setIcon();

	}

	private void setIcon(){
		logo = new ImageIcon("images/greenfoot.png");
		 setIconImage(logo.getImage());
	}

	private void createGUI()
	{   
		//https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html [Accessed 29/04/2015]
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container window = getContentPane();
		window.setLayout(new FlowLayout());
		setTitle("CBallMaze Ball Maze Application");
		setResizable(false);  //cannot resize the window
		setLocationRelativeTo(null);  //takes the window to the centre of the screen


		JMBMenubar = new JMenuBar();    
		setJMenuBar(JMBMenubar);

		JMScenario = new JMenu("Scenario");
		JMBMenubar.add(JMScenario);
		
		JMEdit= new JMenu("Edit");
		JMBMenubar.add(JMEdit);

		JMControl = new JMenu("Controls");
		JMBMenubar.add(JMControl);

		JMHelp = new JMenu("Help");
		JMBMenubar.add(JMHelp); 
		
		JMIOption1 = new JMenuItem("Option 1");
		JMEdit.add(JMIOption1);
		JMIOption1.addActionListener(this);
		
		JMIOption2 = new JMenuItem("Option 2");
		JMEdit.add(JMIOption2);
		JMIOption2.addActionListener(this);
		
		JMIOption3 = new JMenuItem("Option 3");
		JMEdit.add(JMIOption3);
		JMIOption3.addActionListener(this);

		jMIAbout = new JMenuItem("About");
		JMHelp.add(jMIAbout);
		jMIAbout.addActionListener(this);

		jMIHowto = new JMenuItem("How To Play");
		JMHelp.add(jMIHowto);
		jMIHowto.addActionListener(this);

		jMILeft = new JMenuItem("Left");
		JMControl.add(jMILeft);
		jMILeft.addActionListener(this);       

		jMIRight = new JMenuItem("Right");
		JMControl.add(jMIRight);
		jMIRight.addActionListener(this);

		jMIUp = new JMenuItem("Up");
		JMControl.add(jMIUp);
		jMIUp.addActionListener(this);

		jMIDown = new JMenuItem("Down");
		JMControl.add(jMIDown);
		jMIDown.addActionListener(this);

		jMIAct = new JMenuItem("Act");
		JMScenario.add(jMIAct);
		jMIAct.addActionListener(this);

		jMIRun = new JMenuItem("Run");
		JMScenario.add(jMIRun);
		jMIRun.addActionListener(this);        

		jMIReset = new JMenuItem("Reset");
		JMScenario.add(jMIReset);
		jMIReset.addActionListener(this);

		JMIexit = new JMenuItem("Exit");
		JMScenario.add(JMIexit);
		JMIexit.addActionListener(this);

		jPLeft = new JPanel();
		jPLeft.setPreferredSize(new Dimension(610, 520));
		jPLeft.setLayout(new GridLayout(13,16));
		//jPLeft.setBackground(Color.GREEN);
		window.add(jPLeft);   



		jPRight = new JPanel();
		jPRight.setPreferredSize(new Dimension(135, 525));
		// jPRight.setBackground(Color.RED); //delete after setting up
		window.add(jPRight);

		jPRight1 = new JPanel();
		jPRight1.setPreferredSize(new Dimension(131, 95)); //top Right panel with all the text fields
		// jPRight1.setBackground(Color.YELLOW);
		jPRight.add(jPRight1);

		jPRight2 = new JPanel();
		jPRight2.setPreferredSize(new Dimension(135, 95));
		//  jPRight2.setBackground(Color.BLUE);//delete after setting up
		jPRight.add(jPRight2);

		jPRight3 = new JPanel();
		jPRight3.setPreferredSize(new Dimension(135, 150));
		//jPRight3.setBackground(Color.YELLOW);//delete after setting up
		jPRight.add(jPRight3);

		jPRight4 = new JPanel();
		jPRight4.setPreferredSize(new Dimension(135, 60));
		//jPRight4.setBackground(Color.BLUE);//delete after setting up
		jPRight.add(jPRight4);

		jBPicture = new JButton();      
		jBPicture.setPreferredSize(new Dimension(80, 80));
		jBPicture.setBorderPainted(false);
		jBPicture.setIcon(new ImageIcon("images\\north.jpg"));       
		jPRight.add(jBPicture, jBPicture);  

		jPBottom = new JPanel();
		window.add(jPBottom);
		jPBottom.setPreferredSize(new Dimension (752, 50));
		// jPBottom.setBackground(Color.BLACK);//delete after setting up

		jOption = new JLabel ("Option    ");
		jPRight1.add(jOption);

		jTOption = new JTextField ();
		jTOption.setPreferredSize(new Dimension (70, 22));
		jTOption.setEditable(false);
		jTOption.setBackground(Color.WHITE);
		jPRight1.add(jTOption);

		jSquare = new JLabel ("Square   ");
		jPRight1.add(jSquare);

		jTSquare = new JTextField ();
		jTSquare.setPreferredSize(new Dimension (70, 22));
		jTSquare.setEditable(false);
		jTSquare.setBackground(Color.WHITE);
		jPRight1.add(jTSquare);

		jDirection = new JLabel ("Direction");
		jPRight1.add(jDirection);

		jTDirection = new JTextField ();
		jTDirection.setPreferredSize(new Dimension (70, 22));
		jTDirection.setEditable(false);
		jTDirection.setBackground(Color.WHITE);
		jPRight1.add(jTDirection);

		jPRight4.setLayout(new GridLayout(2,1)); // 2 by 1 Grid for arrow keys   

		jBOption1 = new JButton("Option 1");
		jPRight4.add(jBOption1);
		jBOption1.addActionListener(this);
		jBOption1.setFont(new Font ("Arial", Font.BOLD, 9));
		jBOption1.setBackground(Color.LIGHT_GRAY);

		jBOption2 = new JButton("Option 2");
		jPRight4.add(jBOption2);
		jBOption2.addActionListener(this);
		jBOption2.setFont(new Font ("Arial", Font.BOLD, 9));
		jBOption2.setBackground(Color.LIGHT_GRAY);

		jBOption3 = new JButton("Option 3");
		jPRight4.add(jBOption3);
		jBOption3.addActionListener(this);
		jBOption3.setFont(new Font ("Arial", Font.BOLD, 9));
		jBOption3.setBackground(Color.LIGHT_GRAY);

		jBExit = new JButton("Exit");
		jPRight4.add(jBExit);
		jBExit.addActionListener(this);
		jBExit.setBackground(Color.LIGHT_GRAY);

		////////////////////////////////////////////////

		jPRight2.setLayout(new GridLayout(3,3)); // 3 by 3 Grid for arrow keys        

		jBSpace1 = new JButton();
		jPRight2.add(jBSpace1);
		jBSpace1.setEnabled(false);

		jBUp = new JButton("^");
		jPRight2.add(jBUp);
		jBUp.addActionListener(this);
		jBUp.setBackground(Color.LIGHT_GRAY);
		jBUp.setFont(new Font ("Arial", Font.BOLD, 19));

		jBSpace2 = new JButton();
		jPRight2.add(jBSpace2);
		jBSpace2.setEnabled(false);

		JBLeft = new JButton("<");
		jPRight2.add(JBLeft);
		JBLeft.addActionListener(this);
		JBLeft.setBackground(Color.LIGHT_GRAY);
		JBLeft.setFont(new Font ("Arial", Font.BOLD, 15));

		jBSpace3 = new JButton();
		jPRight2.add(jBSpace3);
		jBSpace3.setEnabled(false);

		JBRight = new JButton(">");
		jPRight2.add(JBRight);
		JBRight.addActionListener(this);
		JBRight.setBackground(Color.LIGHT_GRAY);
		JBRight.setFont(new Font ("Arial", Font.BOLD, 15));

		jBSpace4 = new JButton();
		jPRight2.add(jBSpace4);
		jBSpace4.setEnabled(false);

		jBDown = new JButton("v");
		jPRight2.add(jBDown); 
		jBDown.addActionListener(this);
		jBDown.setBackground(Color.LIGHT_GRAY);
		jBDown.setFont(new Font ("Arial", Font.BOLD, 15));

		jBSpace5 = new JButton();
		jPRight2.add(jBSpace5);
		jBSpace5.setEnabled(false);

		////////////////////////////////////////////////

		jPBottom1 = new JPanel();
		jPBottom1.setPreferredSize(new Dimension(450, 40)); //change after sizing
		//jPBottom1.setBackground(Color.MAGENTA);
		jPBottom.add(jPBottom1);

		jPBottom2 = new JPanel();
		jPBottom2.setPreferredSize(new Dimension(250, 30)); //change after sizing
		//jPBottom2.setBackground(Color.RED);
		jPBottom.add(jPBottom2);

		jSpeed = new JLabel ("Speed");
		jPBottom2.add(jSpeed);        

		jSSpeed = new JSlider(JSlider.HORIZONTAL, 5, 1000, 500); 
		jPBottom2.add(jSSpeed);

		jSSpeed.setMajorTickSpacing(500);
		jSSpeed.setPaintTicks(true);
		jSSpeed.addChangeListener(this);

		JBAct = new JButton("Act");
		JBAct.setIcon(new ImageIcon("images\\act.jpg"));
		jPBottom1.add(JBAct);
		JBAct.addActionListener(this);

		JBReset = new JButton("Reset");
		JBReset.setIcon(new ImageIcon("images\\reset.jpg"));
		jPBottom1.add(JBReset);
		JBReset.addActionListener(this);

		JBRun = new JButton("Run");
		JBRun.setIcon(new ImageIcon("images\\run.jpg"));
		jPBottom1.add(JBRun);
		JBRun.addActionListener(this);

		option1();      


		ball_square();


	}

	public void stateChanged(ChangeEvent e)
	{
		jSSpeed.setInverted(true);//http://docs.oracle.com/javase/6/docs/api/javax/swing/JSlider.html#getInverted() [Accessed 29/04/2015]
		int timeGap = jSSpeed.getValue();
		timer.setDelay(timeGap);
	}

	private void option1() 
	{
		System.out.println("Option 1");
		jPLeft.removeAll(); //clears the pannel	     
		jPLeft.updateUI();// Refreshes Panel
		jTOption.setText("1");
		jTOption.setHorizontalAlignment(JTextField.CENTER);
		jTDirection.setText("North");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);
		grid(); 
		jBGrid[0][15].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		xBall =0;
		yBall=15;
		nBall=15;
		checkOption=1;
	}

	private void option2() 
	{
		System.out.println("Option 2");
		jPLeft.removeAll(); //clears the pannel	     
		jPLeft.updateUI();// Refreshes Panel

		jTOption.setText("2");
		jTOption.setHorizontalAlignment(JTextField.CENTER);
		jTDirection.setText("North");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);
		grid2(); 
		jBGrid[0][0].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		xBall =0;
		yBall=0;
		nBall=0;
		ball_square();
		checkOption=2;
	}

	private void option3() 
	{
		System.out.println("Option 3");
		jPLeft.removeAll(); //clears the pannel	     
		jPLeft.updateUI();// Refreshes Panel

		jTOption.setText("3");
		jTOption.setHorizontalAlignment(JTextField.CENTER);
		jTDirection.setText("North");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);
		grid3(); 
		jBGrid[12][0].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		xBall =12;
		yBall=0;
		nBall=192;
		ball_square();
		checkOption=3;
	}

	private void ball_square() {
		jTSquare.setText(Integer.toString(nBall));
		jTSquare.setHorizontalAlignment(JTextField.CENTER);	
	}

	private void grid(){
		for(int x=0; x<13; x++){
			for(int y=0; y<16; y++){
				jBGrid[x][y] = new JButton();
				jPLeft.add(jBGrid[x][y]);
				jBGrid[x][y].setBorderPainted(false); /*gets rid of border 
				http://stackoverflow.com/questions/2713190/how-to-remove-border-around-buttons [Accessed 29/04/2015]*/ 
				//http://stackoverflow.com/questions/13810213/java-stretch-icon-to-fit-button  [Accessed 29/04/2015]
				jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\white32x32.jpg").getImage().getScaledInstance(64, 64,java.awt.Image.SCALE_SMOOTH)))));

				if ((((((x==0||x==3||x==6||x==9||x==12)||((x==1||x==2||x==7||x==8 ))&& y==1)||((x==1||x==2||x==7||x==8 ))&& y==5)||((x==1||x==2))&& y==9)||((x==4||x==5))&& y==11)||((x==7||x==8))&& y==12)
				{jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));

				jBGrid[x][y].setName("sand");

				}
				else {
					jBGrid[x][y].setName("wall");
				}

				if (((((x==0||x==3||x==6||x==9||x==12 )&& y<=15 )||((x==4||x==5||x==10||x==11 ))&& y==2)||((x==4||x==5||x==10||x==11 ))&& y==6)||((x==4||x==5 ))&& y==11)
				{jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));

				jBGrid[x][y].setName("sand");

				}

				if (x ==12 && y==0){ //sandstone on button 192
					jBGrid[x][y].setIcon(new ImageIcon("images\\sandstone.jpg"));
					jBGrid[x][y].setName("finish");
				}
			}//end for "for loop"
		}//end for "for loop"	
	}// end of method

	private void grid2(){
		for(int x=0; x<13; x++){
			for(int y=0; y<16; y++){
				jBGrid[x][y] = new JButton();
				jPLeft.add(jBGrid[x][y]);
				jBGrid[x][y].setBorderPainted(false); /*gets rid of border 
				http://stackoverflow.com/questions/2713190/how-to-remove-border-around-buttons [Accessed 29/04/2015]*/ 

				jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\white32x32.jpg").getImage().getScaledInstance(64, 64,java.awt.Image.SCALE_SMOOTH)))));
				//http://stackoverflow.com/questions/13810213/java-stretch-icon-to-fit-button   [Accessed 29/04/2015]
				if (((((((((((x==0||x==3||x==6||x==9||x==12 )&& y<=15 )||((x==1||x==2||x==7||x==8||x==10||x==11 ))&& y==3)||((x==1||x==2||x==4||x==5||x==10||x==11))&& y==5)||
						((x==1||x==2||x==10||x==11))&& y==9)||((x==1||x==2||x==4||x==5||x==10||x==11))&& y==14)||((x==7||x==8))&& y==7)||((x==7||x==8))&& y==10)||
						((x==7||x==8))&& y==15)||((x==4||x==5))&& y==12)||((x==4||x==5))&& y==6)
				{jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));

				jBGrid[x][y].setName("sand");

				}
				else {
					jBGrid[x][y].setName("wall");
				}

				if (x ==12 && y==15){ 
					jBGrid[x][y].setIcon(new ImageIcon("images\\sandstone.jpg"));
					jBGrid[x][y].setName("finish"); 
				}

				if (x ==9 && y==10){ 
					jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\obstacle.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));
					jBGrid[x][y].setName("wall"); 
				}

				if (x ==4 && y==6){ 
					jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\obstacle.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));
					jBGrid[x][y].setName("wall");	 	          		
				}

				if (x ==2 && y==14){ 
					jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\obstacle.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));
					jBGrid[x][y].setName("wall"); 
				}	
			}//end for for loop
		}//end for for loop
	}// end of method

	private void grid3(){
		for(int x=0; x<13; x++){//going down 
			for(int y=0; y<16; y++){//going across
				jBGrid[x][y] = new JButton();
				jPLeft.add(jBGrid[x][y]);
				jBGrid[x][y].setBorderPainted(false); /*gets rid of border 
				http://stackoverflow.com/questions/2713190/how-to-remove-border-around-buttons [Accessed 29/04/2015]*/  
				//http://stackoverflow.com/questions/13810213/java-stretch-icon-to-fit-button   [Accessed 29/04/2015]
				jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\white32x32.jpg").getImage().getScaledInstance(64, 64,java.awt.Image.SCALE_SMOOTH)))));

				if (((((((((((((x==0||x==3||x==6||x==9||x==12 )&& y<=15 )||((x==1||x==2||x==10||x==11 ))&& y==2)||(x==4||x==5)&& y==3)||
						(x==7||x==8)&& y==4)||((x==10||x==11))&& y==5)||((x==1||x==2))&& y==6)||((x==7||x==8))&& y==7)||
						((x==7||x==8))&& y==8)||((x==10||x==11))&& y==10)||((x==1||x==2||x==4||x==5))&& y==11)||((x==1||x==2||x==7||x==8))&& y==12)
						||((x==10||x==11||x==7||x==8))&& y==13)
				{jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));

				jBGrid[x][y].setName("sand");

				}
				else {
					jBGrid[x][y].setName("wall");
				}

				if (x ==0 && y==15){ //sandstone on button 192
					jBGrid[x][y].setIcon(new ImageIcon("images\\sandstone.jpg"));
					jBGrid[x][y].setName("finish");
				}

				if (x ==8 && y==7){ //sandstone on button 192
					jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\obstacle.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));
					jBGrid[x][y].setName("wall"); 
				}

				if (x ==1 && y==2){ //sandstone on button 192
					jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\obstacle.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));
					jBGrid[x][y].setName("wall"); 
				}

				if (x ==12 && y==13){ //sandstone on button 192
					jBGrid[x][y].setIcon(new ImageIcon(((new ImageIcon("images\\obstacle.jpg").getImage().getScaledInstance(42, 42,java.awt.Image.SCALE_SMOOTH)))));
					jBGrid[x][y].setName("wall"); 
				}
			}//end for for loop
		}//end for for loop
	}// end of method

	private void reset() {
		System.out.println("Reset");
		//http://www.coderanch.com/t/345317/GUI/java/clear-JPanel-repainting   [Accessed 29/04/2015]
		jPLeft.removeAll(); //clears the pannel	     
		jPLeft.updateUI();// Refreshes Panel

		grid();
		option1();	        
		jBGrid[0][15].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		xBall =0;
		yBall=15;
		nBall=15;
		jBPicture.setIcon(new ImageIcon("images\\north.jpg"));
		ball_square();
		timer.stop();
		System.out.println("Timer Stopped");
	}

	private void down(){
		jBGrid[xBall+1][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		jBGrid[xBall][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(60, 60,java.awt.Image.SCALE_SMOOTH)))));

		xBall = xBall +1;

		System.out.println("Down");
		jBPicture.setIcon(new ImageIcon("images\\south.jpg"));
		jTDirection.setText("South");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);  
		nBall = nBall +16;
	}

	private void left(){
		jBGrid[xBall][yBall-1].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		jBGrid[xBall][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(60, 60,java.awt.Image.SCALE_SMOOTH)))));

		yBall = yBall -1;

		System.out.println("Left");	
		jBPicture.setIcon(new ImageIcon("images\\west.jpg")); 
		jTDirection.setText("West");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);	
		nBall = nBall -1;
	}

	private void left_finish(){
		jBGrid[xBall][yBall-1].setIcon(new ImageIcon(((new ImageIcon("images\\finish.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		jBGrid[xBall][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(60, 60,java.awt.Image.SCALE_SMOOTH)))));

		yBall = yBall -1;

		System.out.println("Left");	
		jBPicture.setIcon(new ImageIcon("images\\west.jpg")); 
		jTDirection.setText("West");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);	
		nBall = nBall -1;
	}

	private void right(){
		jBGrid[xBall][yBall+1].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		jBGrid[xBall][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(60, 60,java.awt.Image.SCALE_SMOOTH)))));

		System.out.println("Right");
		jBPicture.setIcon(new ImageIcon("images\\east.jpg"));
		jTDirection.setText("East");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);
		nBall = nBall +1;

		yBall = yBall +1;
	}

	private void right_finish(){
		jBGrid[xBall][yBall+1].setIcon(new ImageIcon(((new ImageIcon("images\\finish.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		jBGrid[xBall][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(60, 60,java.awt.Image.SCALE_SMOOTH)))));

		yBall = yBall +1;

		System.out.println("Right");	
		jBPicture.setIcon(new ImageIcon("images\\east.jpg")); 
		jTDirection.setText("East");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);	
		nBall = nBall +1;
	}

	private void up(){
		jBGrid[xBall-1][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		jBGrid[xBall][yBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(60, 60,java.awt.Image.SCALE_SMOOTH)))));

		System.out.println("Up");
		jBPicture.setIcon(new ImageIcon("images\\north.jpg"));
		jTDirection.setText("North");
		jTDirection.setHorizontalAlignment(JTextField.CENTER);
		xBall = xBall - 1;
		nBall = nBall -16;
	}

	private void act(){

		if(checkOption==0){
			System.out.println("Check Option = 0 (Unavailable)");
		}

		else if (checkOption==1){
			if (xBall < 12){				
				if (jBGrid[xBall+1][yBall].getName()=="sand"){	

					down();

				} else  {				
					left();
				}//end of else			
			} //end of if (xBall < 12)	

			else if (jBGrid[xBall][yBall-1].getName()=="finish"){

				left_finish();

				ball_square();
				JOptionPane.showMessageDialog(null, "Congratulations, You have successfully completed this game!", 
						"CBallMaze Ball Maze Application", JOptionPane.INFORMATION_MESSAGE);

				reset();			
			}

			else if(!( xBall < 12)){
				left();			
			}
		}// END OF else if (checkOption==1){

		else if (checkOption==2){
			if (xBall < 12){				
				if (jBGrid[xBall+1][yBall].getName()=="sand"){	

					down();

				} else  {				
					right();
				}//end of else			
			} //end of if (xBall < 12)	

			else if (jBGrid[xBall][yBall+1].getName()=="finish"){

				right_finish();

				ball_square();
				JOptionPane.showMessageDialog(null, "Congratulations, You have successfully completed this game!", 
						"CBallMaze Ball Maze Application", JOptionPane.INFORMATION_MESSAGE);

				reset();			
			}
			else if(!( xBall < 12)){
				right();
			}

		}//else if (checkOption==2){

		else if (checkOption==3){
			if (xBall > 0){				
				if (jBGrid[xBall-1][yBall].getName()=="sand"){	

					up();

				} else  {				
					right();
				}//end of else			
			} //end of if (xBall < 12)	

			else if (jBGrid[xBall][yBall+1].getName()=="finish"){

				right_finish();

				ball_square();
				JOptionPane.showMessageDialog(null, "Congratulations, You have successfully completed this game!", 
						"CBallMaze Ball Maze Application", JOptionPane.INFORMATION_MESSAGE);

				reset();			
			}
			else if(!( xBall > 0)){
				right();
			}

		}//END OF else if (checkOption==3){

	}

	public void actionPerformed(ActionEvent event)
	{    	    	

		System.out.println("ActionListener Triggered");

		Object source = event.getSource();


		if(source==jMIHowto){
			System.out.println("HowTo");

			JOptionPane.showMessageDialog(null, "Aim of the game is to move the golden ball to the grey tile \n located on the maze using the onscreen arrow buttons to \n move in a given direction."
					+ "There are three options/levels to \n complete which you will have to work through. There is also \n the 'Act' button where this will show you one step at a time \n on how to complete this maze."
					+ "There is also a 'Run' button \n included which will  make the golden ball move itself from \n start to finish, using the speed slider to control how fast \n you want the ball to move in 'Run' mode ONLY", 
					"About CBallMaze", JOptionPane.INFORMATION_MESSAGE);

		}

		if(source==jMIAbout){
			System.out.println("About");

			ImageIcon icon = new ImageIcon(((new ImageIcon("images\\icon.jpg").getImage().getScaledInstance(100, 100,java.awt.Image.SCALE_SMOOTH))));

			JOptionPane.showMessageDialog(null, "CBallMaze Ball Maze Application \n Program: Assignment 2: Application - Ball Navigation of a Maze \n "
					+ "Filename: CBallMaze.java\n Author: © Mehul Chamunda (14406068)\n "
					+ "Course: BSc Computing (Software Engineering) Year 1\n "
					+ "Module: CSY1020 Problem Solving & Programming\n "
					+ "Version: 1.0 Incorporates Artificial Intelligence!", 
					"About CBallMaze", JOptionPane.INFORMATION_MESSAGE,icon);

		}

		if(source==timer){
			System.out.println("Timer Counting...");
			act();
			ball_square();

		}

		if((source == jBOption1)||(source ==JMIOption1)){
			reset();
		}

		if((source == jBOption2)||(source ==JMIOption2)){
			System.out.println("Option 2");
			jTOption.setText("2");
			jTOption.setHorizontalAlignment(JTextField.CENTER);

			System.out.println("Clear");

			jPLeft.removeAll(); //clears the panel	     
			jPLeft.updateUI();// Refreshes Panel
			option2() ;
		}

		if((source == jBOption3)||(source ==JMIOption3)){
			System.out.println("Option 3");		
			jTOption.setText("3");
			jTOption.setHorizontalAlignment(JTextField.CENTER);

			System.out.println("Clear");

			jPLeft.removeAll(); //clears the panel	     
			jPLeft.updateUI();// Refreshes Panel
			option3() ;
		}

		if((source == jBExit)||(source == JMIexit)){
			System.out.println("Exit");
			System.exit(0);
		}

		if((source == jBUp)||(source == jMIUp)){
			if (!(xBall==0)){
				if (jBGrid[xBall-1][yBall].getName()=="wall"){
					System.out.println("Cannot Move");
				}else {
					up();			
				}
				ball_square();
			}
		}					

		if((source == jBDown)||(source == jMIDown)){  					
			if (!(xBall==12)){
				if (jBGrid[xBall+1][yBall].getName()=="wall"){
					System.out.println("Cannot Move");
				}else {
					down();
				}

				ball_square();
			} 
		}

		if((source == JBLeft)||(source == jMILeft)){					
			if (!(yBall==0)){

				if (jBGrid[xBall][yBall-1].getName()=="finish"){

					left_finish();
					ball_square();
					JOptionPane.showMessageDialog(null, "Congratulations, You have successfully completed this game!", 
							"CBallMaze Ball Maze Application", JOptionPane.INFORMATION_MESSAGE);
					reset();

				}

				else if (jBGrid[xBall][yBall-1].getName()=="wall"){
					System.out.println("Cannot Move");
				}else {
					left();
				}

				ball_square();
			}
		}

		if((source == JBRight)||(source == jMIRight)){
			if (!(yBall==15)){
				if (jBGrid[xBall][yBall+1].getName()=="finish"){

					right_finish();
					ball_square();
					JOptionPane.showMessageDialog(null, "Congratulations, You have successfully completed this game!", 
							"CBallMaze Ball Maze Application", JOptionPane.INFORMATION_MESSAGE);
					reset();

				}
				else if (jBGrid[xBall][yBall+1].getName()=="wall"){
					System.out.println("Cannot Move");
				}else {
					right();
				}

				ball_square();
			}
		}

		if((source == JBAct)||(source == jMIAct)){	
			System.out.println("Act");

			act();

			ball_square();	
		}//end of if(source == JBAct){	

		if((source == JBReset)||(source == jMIReset)){
			reset() ;

		}

		if((source == JBRun)||(source == jMIRun)){
			System.out.println("Run");	
			timer.start();

		}    	

	}

}