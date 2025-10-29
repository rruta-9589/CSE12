//Rakendu Ruta
//5-16-22
//ScaleOfKnowledge.java
///This is the work for the final week of the game project.
///This program tests our knowledge of everything we have learnt so far
///and includes things from Components to Graphics. This program
///will teach you the composers and terminology of the different
///musical time periods. This will be done through information panels 
///and two games, match and test. 

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;

import java.lang.Math;

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ScaleOfKnowledge
{
	public ScaleOfKnowledge()
	{
	}
	
	public static void main(String [] args)
	{
		ScaleOfKnowledge sok = new ScaleOfKnowledge();
		sok.runIt();
	}
	
	public void runIt()
	{
		
		JFrame frame = new JFrame("Scale Of Knowledge");
		frame.setSize(1000,700);				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocation(100,10);
		frame.setResizable(true);
		GameStartHolder gsh = new GameStartHolder(); 		
		frame.getContentPane().add( gsh );		
		frame.setVisible(true);	
	}
}

//This holds all the starting cards which make up the starting panels 
//and all its connecting panels
class GameStartHolder extends JPanel 
{
	public GameStartHolder()
	{
		setBackground(Color.RED);

		CardLayout startingcards = new CardLayout();
		setLayout(startingcards);
		
		StartingPage sp = new StartingPage(startingcards, this);
		InformationPage ip = new InformationPage(startingcards, this);
		GameSelectionHolder gsh = new GameSelectionHolder(startingcards, this);
		LeaderBoardPanel lbp = new LeaderBoardPanel(startingcards, this);
		
		add(sp, "StartingPage");
		add(ip, "InformationPage");
		add(gsh, "GameSelectionHolder");
		add(lbp, "LeaderBoard");
	}
}

//This is the creation of the starting panel
class StartingPage extends JPanel
{
	private CardLayout listOfCards;//This is the cards name for starting panel
	private GameStartHolder primaryPanel;//This is the cards holder for starting panel
	private String pictName; //This is the name for the file a picture will be called from later.
	private Image scaleImage; //This is an image variable so I can use the image in my program
	
	public StartingPage(CardLayout c, GameStartHolder p)
	{
		listOfCards = c;
		primaryPanel = p;
		
		setBackground(Color.BLACK);
		setLayout(null);
		Font title = new Font("SansSerif", Font.BOLD, 30);
		Font general = new Font("SansSerif", Font.PLAIN, 22);
		
				
		JLabel gameTitle = new JLabel("Scale Of Knowledge");
		gameTitle.setForeground(Color.WHITE);
		gameTitle.setFont(title);
		gameTitle.setBounds(320,50,400,50);
		add(gameTitle);
		
		JButtonListener startingjbl = new JButtonListener();
		
		JButton information = new JButton("Information");
		information.setFont(general);
		information.setForeground(Color.WHITE);
		information.addActionListener(startingjbl);
		information.setBounds(380,150,200,25);
		information.setOpaque(false);
		information.setContentAreaFilled(false);
		add(information);
		
		scaleImage = null;
		pictName = new String ("cMajInvert.png");
		getMyImage();
		
		JButton games = new JButton("Games");
		games.setFont(general);
		games.setForeground(Color.WHITE);
		games.addActionListener(startingjbl);
		games.setBounds(380,250,200,25);
		games.setOpaque(false);
		games.setContentAreaFilled(false);
		add(games);
		
		JButton leaderBoard = new JButton("Leaderboard");
		leaderBoard.setFont(general);
		leaderBoard.setForeground(Color.WHITE);
		leaderBoard.addActionListener(startingjbl);
		leaderBoard.setBounds(380,350,200,25);
		leaderBoard.setOpaque(false);
		leaderBoard.setContentAreaFilled(false);
		add(leaderBoard);
		
		JLabel creditsHeader = new JLabel("Credits:");
		creditsHeader.setFont(general);
		creditsHeader.setForeground(Color.WHITE);
		creditsHeader.setBounds(440,450,100,25);
		add(creditsHeader);
		
		JLabel credits = new JLabel("Rakendu Ruta");
		credits.setFont(general);
		credits.setForeground(Color.WHITE);
		credits.setBounds(407,480,200,25);
		add(credits);
		
		JButton exit = new JButton("Exit");
		exit.setFont(general);
		exit.setForeground(Color.WHITE);
		exit.addActionListener(startingjbl);
		exit.setBounds(420,600,120,25);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		add(exit);	
	}
	
	//This gets an image for me to use
	public void getMyImage() 
	{	
		File pictFile = new File(pictName);
		try
		{
			scaleImage = ImageIO.read(pictFile);
		}
		catch (IOException e)
		{
			System.err.println("\n\n" + pictName + " can't be found.\n\n");
			e.printStackTrace();
		}
	}
	
	//This draws the image using graphics
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.BLACK);
		g.drawImage(scaleImage, 10,250, 350, 140,this);
		g.drawImage(scaleImage, 600,250, 350, 140,this);
	}

	
	//This checks which button is pressed and moves you through the program accordingly
	class JButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt) 
		{
			String command = evt.getActionCommand();
			
			if(command.equals("Information"))
			{
				listOfCards.show(primaryPanel, "InformationPage");
			}
			else if(command.equals("Games"))
			{
				listOfCards.show(primaryPanel, "GameSelectionHolder");
			}
			else if(command.equals("Leaderboard"))
			{
				listOfCards.show(primaryPanel, "LeaderBoard");
			}
			else if(command.equals("Exit"))
			{
				System.exit(1);
			}
		}
	}
}

//This is the creation of the information panel
class InformationPage extends JPanel 
{	
	private Font title; // This is a font
	private Font general;// This is a font
	private CardLayout listOfCards; //This is the card group name for starting panel
	private GameStartHolder primaryPanel; //This is the card holder for starting panel
	
	public InformationPage(CardLayout c, GameStartHolder p)
	{
		
		listOfCards = c;
		primaryPanel = p;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		title = new Font("SansSerif", Font.BOLD, 30);
		general = new Font("SansSerif", Font.PLAIN, 22);
		
		InfoTitlePanel itp = new InfoTitlePanel();
		RightInfoPanel rip = new RightInfoPanel();
		InfoSouthButton isb = new InfoSouthButton(listOfCards, primaryPanel);
		
		add(itp, BorderLayout.NORTH);
		add(rip, BorderLayout.CENTER);
		add(isb, BorderLayout.SOUTH);
	}
	//This contains the title of the information page
	class InfoTitlePanel extends JPanel
	{
		public InfoTitlePanel()
		{
			setBackground(Color.BLACK);
			setLayout(new FlowLayout());
			JLabel infotitle = new JLabel("Information");
			infotitle.setForeground(Color.WHITE);
			infotitle.setFont(title);
			add(infotitle);
		}
	}
	
	//This ocntains the entirety of the information on the information page
	//This is made using gridlayout
	class RightInfoPanel extends JPanel
	{
		public RightInfoPanel()
		{
			setBackground(Color.BLACK);
			setLayout(new GridLayout(5,4));
			Font infoFont = new Font("Sans Serif", Font.PLAIN, 17);
			Font titleInfo = new Font("Sans Serif", Font.BOLD, 17);
			
			JTextArea baroque = new JTextArea("Baroque(1600-1740)");
			baroque.setForeground(Color.WHITE);
			baroque.setFont(titleInfo);
			baroque.setLineWrap(true);
			baroque.setWrapStyleWord(true);
			baroque.setOpaque(true);
			baroque.setMargin(new Insets(10,10,10,10));
			baroque.setEditable(false);
			baroque.setBackground(Color.BLACK);
			
			JTextArea ph1 = new JTextArea("Composers From The Era:\n - Bach\t - Corelli\n - Handel\t - Vivaldi");
			ph1.setForeground(Color.WHITE);
			ph1.setFont(infoFont);
			ph1.setLineWrap(true);
			ph1.setWrapStyleWord(true);
			ph1.setOpaque(true);
			ph1.setEditable(false);
			ph1.setMargin(new Insets(10,10,10,10));
			ph1.setBackground(Color.BLACK);
			
			JTextArea ph2 = new JTextArea("Characteristics:\n- Use of polyphonic texture:\nThe use of multiple lines of melody");
			ph2.setForeground(Color.WHITE);
			ph2.setFont(infoFont);
			ph2.setLineWrap(true);
			ph2.setWrapStyleWord(true);
			ph2.setOpaque(true);
			ph2.setEditable(false);
			ph2.setMargin(new Insets(10,10,10,10));
			ph2.setBackground(Color.BLACK);
			
			JTextArea ph3 = new JTextArea(" - Use of figured bass:\nFigured bass is musical notation which indicates intervals");
			ph3.setForeground(Color.WHITE);
			ph3.setFont(infoFont);
			ph3.setLineWrap(true);
			ph3.setWrapStyleWord(true);
			ph3.setOpaque(true);
			ph3.setEditable(false);
			ph3.setMargin(new Insets(10,10,10,10));
			ph3.setBackground(Color.BLACK);
			
			JTextArea ph4 = new JTextArea(" - Use of terraced dynamics:\nThese are used to shift volume from soft to loud abrupty(ie: p, mp, mf, f)");
			ph4.setForeground(Color.WHITE);
			ph4.setFont(infoFont);
			ph4.setLineWrap(true);
			ph4.setWrapStyleWord(true);
			ph4.setOpaque(true);
			ph4.setEditable(false);
			ph4.setMargin(new Insets(10,10,10,10));
			ph4.setBackground(Color.BLACK);
			
			JTextArea classical = new JTextArea("Classical(1750-1830)");
			classical.setForeground(Color.WHITE);
			classical.setFont(titleInfo);
			classical.setLineWrap(true);
			classical.setWrapStyleWord(true);
			classical.setOpaque(true);
			classical.setEditable(false);
			classical.setBackground(Color.BLACK);
			
			JTextArea cph1 = new JTextArea("Composers From The Era:\n - Beethoven\t - Hayden\n - Czerny\t - Mozart");
			cph1.setForeground(Color.WHITE);
			cph1.setFont(infoFont);
			cph1.setLineWrap(true);
			cph1.setWrapStyleWord(true);
			cph1.setOpaque(true);
			cph1.setEditable(false);
			cph1.setMargin(new Insets(10,10,10,10));
			cph1.setBackground(Color.BLACK);
			
			JTextArea cph2 = new JTextArea("Characteristics:\n - Homophonic texture:\nWhen different notes are played but based on the same melody");
			cph2.setForeground(Color.WHITE);
			cph2.setFont(infoFont);
			cph2.setLineWrap(true);
			cph2.setWrapStyleWord(true);
			cph2.setOpaque(true);
			cph2.setEditable(false);
			cph2.setBackground(Color.BLACK);
			
			JTextArea cph3 = new JTextArea(" - Alberti bass:\nThis is used to create a rythmic accompainment to the main melody");
			cph3.setForeground(Color.WHITE);
			cph3.setFont(infoFont);
			cph3.setLineWrap(true);
			cph3.setWrapStyleWord(true);
			cph3.setOpaque(true);
			cph3.setEditable(false);
			cph3.setBackground(Color.BLACK);
			
			JTextArea cph4 = new JTextArea(" - Obvious cadence points:\nThese are used to create tension at the end of a phrase");
			cph4.setForeground(Color.WHITE);
			cph4.setFont(infoFont);
			cph4.setLineWrap(true);
			cph4.setWrapStyleWord(true);
			cph4.setOpaque(true);
			cph4.setEditable(false);
			cph4.setBackground(Color.BLACK);
				
			JTextArea romantic = new JTextArea("Romantic(1830-1900)");
			romantic.setForeground(Color.WHITE);
			romantic.setFont(titleInfo);
			romantic.setLineWrap(true);
			romantic.setWrapStyleWord(true);
			romantic.setOpaque(true);
			romantic.setEditable(false);
			romantic.setBackground(Color.BLACK);
			
			JTextArea rph1 = new JTextArea("Composers From The Era:\n - Grieg\t - Chopin\n - Tchaikovsky - Brhahms");
			rph1.setForeground(Color.WHITE);
			rph1.setForeground(Color.WHITE);
			rph1.setFont(infoFont);
			rph1.setLineWrap(true);
			rph1.setWrapStyleWord(true);
			rph1.setOpaque(true);
			rph1.setEditable(false);
			rph1.setMargin(new Insets(10,10,10,10));
			rph1.setBackground(Color.BLACK);
			
			JTextArea rph2 = new JTextArea("Characteristics:\n - Lyrical Melodies:\nThis is a flowing melody that is song like");
			rph2.setForeground(Color.WHITE);
			rph2.setForeground(Color.WHITE);
			rph2.setFont(infoFont);
			rph2.setLineWrap(true);
			rph2.setWrapStyleWord(true);
			rph2.setOpaque(true);
			rph2.setEditable(false);
			rph2.setBackground(Color.BLACK);
			
			JTextArea rph3 = new JTextArea(" - Complex rhythmic patterns:\nThese are melodies which tend to accent certian beats to provide a rhythmic effect");
			rph3.setForeground(Color.WHITE);
			rph3.setForeground(Color.WHITE);
			rph3.setFont(infoFont);
			rph3.setLineWrap(true);
			rph3.setWrapStyleWord(true);
			rph3.setOpaque(true);
			rph3.setEditable(false);
			rph3.setBackground(Color.BLACK);
			
			JTextArea rph4 = new JTextArea(" - Programme music:\nThis is music with the goal of suggesting a visual image or telling a story");
			rph4.setForeground(Color.WHITE);
			rph4.setFont(infoFont);
			rph4.setLineWrap(true);
			rph4.setWrapStyleWord(true);
			rph4.setOpaque(true);
			rph4.setEditable(false);
			rph4.setBackground(Color.BLACK);
			
			JTextArea twenty = new JTextArea("20th & 21st Century(1900-Present)");
			twenty.setForeground(Color.WHITE);
			twenty.setFont(titleInfo);
			twenty.setLineWrap(true);
			twenty.setWrapStyleWord(true);
			twenty.setOpaque(true);
			twenty.setEditable(false);
			twenty.setBackground(Color.BLACK);
						
			JTextArea tph1 = new JTextArea("Composers From The Era:\n - Bartok\t - Copland\n - Britten    - Shostakovich");
			tph1.setForeground(Color.WHITE);
			tph1.setFont(infoFont);
			tph1.setLineWrap(true);
			tph1.setWrapStyleWord(true);
			tph1.setOpaque(true);
			tph1.setMargin(new Insets(10,10,10,10));
			tph1.setEditable(false);
			tph1.setBackground(Color.BLACK);
			
			JTextArea tph2 = new JTextArea("Characteristics:\n - Quartal harmony:\nThe building of structures from the intervals of a perfect fourth");
			tph2.setForeground(Color.WHITE);
			tph2.setFont(infoFont);
			tph2.setLineWrap(true);
			tph2.setWrapStyleWord(true);
			tph2.setOpaque(true);
			tph2.setEditable(false);
			tph2.setBackground(Color.BLACK);
			
			JTextArea tph3 = new JTextArea(" - Neo-Classical Writing:\nA return to classical forms such as sonata form in the 21th & 21st centuries");
			tph3.setForeground(Color.WHITE);
			tph3.setFont(infoFont);
			tph3.setLineWrap(true);
			tph3.setWrapStyleWord(true);
			tph3.setOpaque(true);
			tph3.setEditable(false);
			tph3.setBackground(Color.BLACK);
			
			JTextArea tph4 = new JTextArea(" - Twelve Tone music:\nThis is music that is based on a series which contains all twelve pitch classes in a particular order");
			tph4.setForeground(Color.WHITE);
			tph4.setFont(infoFont);
			tph4.setLineWrap(true);
			tph4.setWrapStyleWord(true);
			tph4.setOpaque(true);
			tph4.setEditable(false);
			tph4.setBackground(Color.BLACK);
			
			add(baroque);
			add(classical);
			add(romantic);
			add(twenty);
			
			add(ph1);
			add(cph1);
			add(rph1);
			add(tph1);
			
			add(ph2);
			add(cph2);
			add(rph2);
			add(tph2);
			
			add(ph3);
			add(cph3);
			add(rph3);
			add(tph3);
			
			add(ph4);
			add(cph4);
			add(rph4);
			add(tph4);
		}
	}
	//This contains the back button to leave the information page
	class InfoSouthButton extends JPanel
	{
		private CardLayout listOfCards;//This is the cards name for starting panel
		private GameStartHolder primaryPanel;//This is the cards holder for starting panel
		
		public InfoSouthButton(CardLayout c, GameStartHolder p)
		{
		
			listOfCards = c;
			primaryPanel = p;
			setBackground(Color.BLACK);
			setLayout(new FlowLayout());
			
			JButtonListener backListenInfo = new JButtonListener();
			
			JButton infoBack = new JButton("Back");
			infoBack.setFont(general);
			infoBack.setForeground(Color.WHITE);
			infoBack.addActionListener(backListenInfo);
			infoBack.setOpaque(false);
			infoBack.setContentAreaFilled(false);
			add(infoBack);
		}
		//This takes you out of the information page and back to the starting page
		class JButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					listOfCards.show(primaryPanel, "StartingPage");
				}
			}
		}
	}
}

//This is the creation of the holder of the game selection panel and
//the actual games
class GameSelectionHolder extends JPanel
{
	private CardLayout listOfCards;//This is the cards name for starting panel
	private GameStartHolder primaryPanel;//This is the cards holder for starting panel

	public GameSelectionHolder(CardLayout c, GameStartHolder p)
	{
		
		listOfCards = c;
		primaryPanel = p;
		
		CardLayout gameCards = new CardLayout();
		
		setLayout(gameCards);
		
		GameChoosePanel gcp = new GameChoosePanel(listOfCards, primaryPanel, gameCards, this);
		MatchGame mg = new MatchGame(gameCards, this);
		TestGameHolder tg = new TestGameHolder(listOfCards, primaryPanel, gameCards, this);
		
		add(gcp, "GameChoosePanel");
		add(mg, "MatchGame");
		add(tg, "TestGameHolder");
	}
}

//This is the holder of the test game difficulty panel and all the test
//game panels.
class TestGameHolder extends JPanel
{
	private CardLayout gameCards;//This is the cards name for the test game
	private GameSelectionHolder gamePanels;//This is the cards holder for test game
	private CardLayout listOfCards;//This is the cards name for the starting panel
	private GameStartHolder primaryPanel;//This is the cards holder for starting panel
	private Font title; //This is a font
	private Font general; //This is a font
	private Boolean easyHard; //This is a boolean which decides if the hard or easy difficult is selected for the test based on
		//if its equal to true or false
	private int val; //This is the value of slider to determine whether easyHard should be true or false based on its value
	
	private CardLayout gameCards2;
	private GameData data;
	
	public TestGameHolder(CardLayout c, GameStartHolder p, CardLayout cg, GameSelectionHolder pg)
	{
		
		data = new GameData();
		data.grabQuestionFromFile();
		
		gameCards = cg;
		gamePanels = pg;
		listOfCards = c;
		primaryPanel = p;
		title = new Font("SansSerif", Font.BOLD, 30);
		general = new Font("SansSerif", Font.PLAIN, 22);
		
		setBackground(Color.RED);
		gameCards2 = new CardLayout();
		setLayout(gameCards2);
		
		TestGamePanel tgp = new TestGamePanel(listOfCards, primaryPanel, gameCards, gamePanels, data, gameCards2, this);
		QuestionPanel qp = new QuestionPanel(data, gameCards2, this);
		FinalScorePane fsp = new FinalScorePane(listOfCards, primaryPanel, data, gameCards2, this);
		
		add(tgp, "TestGamePage");
		add(qp, "QuestionPanel");
		add(fsp, "FinalScorePane");
	}
	//This is the final page displayed to you once you finish the test
	class FinalScorePane extends JPanel implements ActionListener
	{
		private GameData data; //This is a data variable which stores data about the game like the answers, questions, and more
		private CardLayout gameCards2; //This is cardlayout for the testgame
		private TestGameHolder gamePanel2;//This is the holder for the test game cards
		private CardLayout listOfCards; //This is a cardlayout for the programs starting cards
		private GameStartHolder primaryPanel;//This is the holder for the programs starting cards
		JTextArea scoreInfo, highScoresArea;
		
		public FinalScorePane(CardLayout ps, GameStartHolder gs, GameData d, CardLayout c, TestGameHolder p)
		{
			data = d;
			gameCards2 = c;
			gamePanel2 = p;
			primaryPanel = gs;
			listOfCards = ps;
			
			Font myFont = new Font("San Serif", Font.BOLD, 22);
			
			setLayout(new BorderLayout(20,20));
			setBackground(new Color(46, 45, 48));
			setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			setFont(myFont);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new GridLayout(1,2,10,10));
			centerPanel.setBackground(new Color(46, 45, 48));
			add(centerPanel, BorderLayout.CENTER);
			
			JPanel leftSidePanel = new JPanel();
			leftSidePanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
			leftSidePanel.setLayout(new BorderLayout());
			leftSidePanel.setBackground(Color.BLACK);
			centerPanel.add(leftSidePanel, BorderLayout.CENTER);
			
			scoreInfo = new JTextArea(""+ data.getCorrectCount(), 10,20);
			scoreInfo.setFont(myFont);
			scoreInfo.setLineWrap(true);
			scoreInfo.setWrapStyleWord(true);
			scoreInfo.setOpaque(false);
			scoreInfo.setForeground(Color.WHITE);
			scoreInfo.setEditable(false);
			leftSidePanel.add(scoreInfo);
			
			JPanel rightSidePanel = new JPanel();
			rightSidePanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
			rightSidePanel.setLayout(new BorderLayout());
			rightSidePanel.setBackground(Color.BLACK);
			centerPanel.add(rightSidePanel, BorderLayout.CENTER);
			
			highScoresArea = new JTextArea("" + data.getHighScores(), 10,20);
			highScoresArea.setFont(myFont);
			highScoresArea.setForeground(Color.BLACK);
			highScoresArea.setLineWrap(true);
			highScoresArea.setWrapStyleWord(true);
			highScoresArea.setOpaque(false);
			highScoresArea.setEditable(false);
			highScoresArea.setMargin(new Insets(10,10,10,10));
			JScrollPane scroller = new JScrollPane(highScoresArea);
			scroller.setOpaque(false);
			rightSidePanel.add(scroller);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,3));
			buttonPanel.setBackground(Color.BLACK);
			add(buttonPanel, BorderLayout.SOUTH);
			
			JButton playAgain = new JButton("Play Again");
			playAgain.setFont(myFont);
			playAgain.setForeground(Color.WHITE);
			playAgain.setOpaque(false);
			playAgain.setContentAreaFilled(false);
			playAgain.addActionListener(this);
			buttonPanel.add(playAgain);
			
			JButton exit = new JButton("Exit Program");
			exit.setFont(myFont);
			exit.setForeground(Color.WHITE);
			exit.setOpaque(false);
			exit.setContentAreaFilled(false);
			exit.addActionListener(this);
			buttonPanel.add(exit);
			
			JButton returns = new JButton ("Exit Test");
			returns.setFont(myFont);
			returns.setForeground(Color.WHITE);
			returns.setOpaque(false);
			returns.setContentAreaFilled(false);
			returns.addActionListener(this);
			buttonPanel.add(returns);
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			scoreInfo.setText("" + data.toString());
			highScoresArea.setText("" + data.getHighScores());
			highScoresArea.setCaretPosition(0);
		}
		//This checks what button you pressed and lets you play again, exit the program, or exit the test
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			
			if (command.equals("Play Again"))
			{
				data.saveToHighScores();
				gameCards2.next(gamePanel2);
			}
			else if(command.equals("Exit Program"))
			{
				data.saveToHighScores();
				System.exit(0);
			}
			else if(command.equals("Exit Test"))
			{
				listOfCards.show(primaryPanel, "StartingPage");
			}
		}
	}
	//This is where the question panels for the test are created and formatted
	//Here the questions and answer choices are passed in and compared to the true answer to see
	//which answer choice is correct
	class QuestionPanel extends JPanel implements ActionListener
	{
		private GameData data; //This is a data variable which stores data about the game like the answers, questions, and more
		private CardLayout gameCards2; //This is a cardlayout for the testgame cards
		private TestGameHolder gamePanel2;//This is the holder of the test game cards
		private ButtonGroup group; //This is used to hold the jbuttons which help progress through the questions
		private JTextArea questionArea; //This is where the question for the test is asked
		private JRadioButton [] answer; //This array holds 4 jradiobuttons which are used as the answer choices
		private JButton submit, nextQuestion, nextPanel; //These are jbuttons which are used to progress through the game questions
		
		public QuestionPanel(GameData d, CardLayout c, TestGameHolder p)
		{
			data = d;
			gameCards2 = c;
			gamePanel2 = p;
			
			setBackground(Color.BLACK);
			setLayout(new BorderLayout(10,10));
			Font myFont = new Font("Sans Serif", Font.BOLD, 22);
			
			answer = new JRadioButton[4];
			
			JPanel question = new JPanel();
			question.setBackground(Color.BLACK);
			question.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			question.setLayout(new BorderLayout());
			question.setForeground(Color.BLACK);
			add(question, BorderLayout.NORTH);
			
			questionArea = new JTextArea(data.getQuestion(), 3,30);
			questionArea.setFont(myFont);
			questionArea.setLineWrap(true);
			questionArea.setForeground(Color.WHITE);
			questionArea.setWrapStyleWord(true);
			questionArea.setOpaque(false);
			questionArea.setEditable(false);
			
			question.add(questionArea, BorderLayout.CENTER);
			
			JPanel answers = new JPanel();
			answers.setBackground(Color.GRAY);
			answers.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			answers.setLayout(new GridLayout(2,2,20,20));
			add (answers, BorderLayout.CENTER);
			
			group = new ButtonGroup();
			
			for(int i = 0; i < answer.length; i++)
			{
				answer[i] = new JRadioButton("" + (char)(65 + i) +". " + data.getAnswer(i));
				group.add(answer[i]);
				answer[i].setBackground(Color.BLACK);
				answer[i].setFont(myFont);
				answer[i].setForeground(Color.WHITE);
				answer[i].addActionListener(this);
				answers.add(answer[i]);
			}
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.BLACK);
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20,30));
			add(buttonPanel, BorderLayout.SOUTH);
			
			submit = new JButton("Check Answer");
			submit.setFont(myFont);
			submit.addActionListener(this);
			submit.setForeground(Color.WHITE);
			submit.setOpaque(false);
			submit.setContentAreaFilled(false);
			submit.setEnabled(false);
			buttonPanel.add(submit);
			
			nextQuestion = new JButton("Go To Next Question");
			nextQuestion.setFont(myFont);
			nextQuestion.addActionListener(this);
			nextQuestion.setForeground(Color.WHITE);
			nextQuestion.setOpaque(false);
			nextQuestion.setContentAreaFilled(false);
			nextQuestion.setEnabled(false);
			buttonPanel.add(nextQuestion);
			
			nextPanel = new JButton("Finish Test");
			nextPanel.setFont(myFont);
			nextPanel.addActionListener(this);
			nextPanel.setForeground(Color.WHITE);
			nextPanel.setOpaque(false);
			nextPanel.setContentAreaFilled(false);
			nextPanel.setEnabled(false);
			buttonPanel.add(nextPanel);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();
			
			if(group.getSelection() != null)
			{
				submit.setEnabled(true);
			}
			
			if(command.equals("Check Answer"))
			{
				answer[data.getCorrectAnswer()].setBackground(Color.GREEN);
				for(int i =0; i < answer.length; i++)
				{
					if(answer[i].isSelected())
					{
						if(i != data.getCorrectAnswer())
						{
							answer[i].setBackground(Color.RED);
						}
						else
						{
							data.addOneToCorrectCount();
						}
					}
				}
				group.clearSelection();
				for(int i = 0; i <  answer.length; i++)
				{
					answer[i].setEnabled(false);
				}
				submit.setEnabled(false);
				if(data.getQuestionCount() == 4)
				{
					nextPanel.setEnabled(true);
				}
				else
				{
					nextQuestion.setEnabled(true);
				}
			}
			else if(command.equals("Go To Next Question"))
			{
				resetQuestion();
				nextQuestion.setEnabled(false);
			}
			else if(command.equals("Finish Test"))
			{
				data.resetAll();
				resetQuestion();
				nextPanel.setEnabled(false);
				gameCards2.next(gamePanel2);
			}
		}
		
		public void resetQuestion()
		{
			data.grabQuestionFromFile();
			questionArea.setText(data.getQuestion());
			answer[0].setText("A. " + data.getAnswer(0));
			answer[1].setText("B. " + data.getAnswer(1));
			answer[2].setText("C. " + data.getAnswer(2));
			answer[3].setText("D. " + data.getAnswer(3));
			for(int i = 0; i <answer.length; i++)
			{
				answer[i].setEnabled(true);
				answer[i].setBackground(Color.BLACK);
				answer[i].setForeground(Color.WHITE);
			}
		}
	}
	//This is where the test difficulty selection panel is created
	class TestGamePanel extends JPanel
	{
		private CardLayout listOfCards; //This is a cardlayout for the starting page cards
		private GameStartHolder primaryPanel; //This is the holder for the starting page cards
		private CardLayout gameCards; //This is a cardlayout for the types of games
		private GameSelectionHolder gamePanels;//This is the holder for the types of games
		private CardLayout gameCards2;//this is a cardlayout for the test game
		private TestGameHolder gamePanel2;//this is a holder for the test game panels

		public TestGamePanel(CardLayout c, GameStartHolder p, CardLayout cg, GameSelectionHolder pg, GameData d, CardLayout cg2, TestGameHolder pg2)
		{
			gameCards = cg;
			gamePanels = pg; 
			listOfCards = c;
			primaryPanel = p;
			
			data = d;
			gameCards2 = cg2;
			gamePanel2 = pg2;
			
			setBackground(Color.RED);
			setLayout(new BorderLayout());
			
			TopLabel tl = new TopLabel();
			CenterSelections cs = new CenterSelections();
			BottomExit be = new BottomExit(gameCards, gamePanels);
			
			add(tl,BorderLayout.NORTH);
			add(cs,BorderLayout.CENTER);
			add(be,BorderLayout.SOUTH);
			
		}
	
	
		//This contains the exit button at the bottom of the page which 
		//returns you to the game selection panel
		class BottomExit extends JPanel
		{
			private CardLayout gameCards; //This is a cardlayout for the types of games
			private GameSelectionHolder gamePanels;//This is the holder for the types of games
			public BottomExit(CardLayout cg, GameSelectionHolder pg)
			{
				gameCards = cg;
				gamePanels = pg;
				setLayout(new FlowLayout());
				setBackground(Color.BLACK);
				
				JButtonListener backListenExit = new JButtonListener();
				
				JButton gameBack = new JButton("Back");
				gameBack.setFont(general);
				gameBack.setForeground(Color.WHITE);
				gameBack.addActionListener(backListenExit);
				gameBack.setOpaque(false);
				gameBack.setContentAreaFilled(false);
				add(gameBack);
			}
			
			class JButtonListener implements ActionListener
			{
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("Back"))
					{
						gameCards.show(gamePanels, "GameChoosePanel");
					}
				}
			}
		}
		//This calls right and left selection which allow you to customize your experience
		class CenterSelections extends JPanel
		{
			
			public CenterSelections()
			{
				
				setLayout(new GridLayout(1,2));
				LeftSelection ls = new LeftSelection();
				RightSelection rs = new RightSelection();
				
				add(ls);
				add(rs);
			}
		}
			//Here the option to enter your first and last name is displayed
			//Here the button to enter the test is also displayed
		class RightSelection extends JPanel
		{
			private JTextField firstNameField; //This is the textfield in which you enter your first name
			private JTextField lastNameField;//This is the textfield in which you enter your last name
			private Font nameFont;
			
			public RightSelection()
			{
				nameFont = new Font("Sans Serif", Font.PLAIN, 30);
				
				setBackground(Color.BLACK);
				setLayout(new GridLayout(5,1));
				
				JTextArea firstNamePrompt = new JTextArea("First Name: ");
				firstNamePrompt.setForeground(Color.WHITE);
				firstNamePrompt.setFont(general);
				firstNamePrompt.setMargin(new Insets(10,10,10,10));
				firstNamePrompt.setLineWrap(true);
				firstNamePrompt.setWrapStyleWord(true);
				firstNamePrompt.setOpaque(false);
				firstNamePrompt.setEditable(false);
				add(firstNamePrompt);
				
				firstNameField = new JTextField(16);
				firstNameField.setMargin(new Insets(10,10,10,10));
				firstNameField.setFont(nameFont);
				add(firstNameField);	
				
				JTextArea lastNamePrompt = new JTextArea("Last Name: ");
				lastNamePrompt.setForeground(Color.WHITE);
				lastNamePrompt.setFont(general);
				lastNamePrompt.setMargin(new Insets(10,10,10,10));
				lastNamePrompt.setLineWrap(true);
				lastNamePrompt.setWrapStyleWord(true);
				lastNamePrompt.setOpaque(false);
				lastNamePrompt.setEditable(false);
				add(lastNamePrompt);
				
				lastNameField = new JTextField(16);
				lastNameField.setMargin(new Insets(10,10,10,10));
				lastNameField.setFont(nameFont);
				add(lastNameField); 
				
				JButton enterTest = new JButton("Enter The Test");
				JButtonListener backListenGame = new JButtonListener();
				enterTest.setForeground(Color.WHITE);
				enterTest.addActionListener(backListenGame);
				enterTest.setOpaque(false);
				enterTest.setFont(title);
				enterTest.setContentAreaFilled(false);
				add(enterTest);
			}
			
			class JButtonListener implements ActionListener
			{
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
		
					if(command.equals("Enter The Test") && !firstNameField.getText().equals("")&& !lastNameField.getText().equals(""))
					{
						data.setName(firstNameField.getText(), lastNameField.getText());
						gameCards2.next(gamePanel2);
					}
				}
			}
			
		}
		//Here the difficulty slider is located and also what you will be quizzed on is told as well
		class LeftSelection extends JPanel
		{
			private JSlider diffslide; //This is the slider used for determiniting the difficulty of the game
			public LeftSelection()
			{
				setBackground(Color.BLACK);
				setLayout(new GridLayout(8,1));
				
				diffslide = new JSlider(0,10,0);
				diffslide.setMajorTickSpacing(1);	
				diffslide.setPaintTicks(true);
				diffslide.setLabelTable( diffslide.createStandardLabels(1) ); 
				diffslide.setPaintLabels(true);
				diffslide.setOrientation(JSlider.HORIZONTAL);
				diffslide.setForeground(Color.WHITE);
				diffslide.setOpaque(false);
				SliderListener slistener1 = new SliderListener();
				diffslide.addChangeListener(slistener1);
				
				JTextArea easyhard = new JTextArea("Easy To Hard Slider(0-4=easy) (5-10=Hard)");
				easyhard.setFont(general);
				easyhard.setMargin(new Insets(10,10,10,10));
				easyhard.setLineWrap(true);
				easyhard.setWrapStyleWord(true);
				easyhard.setOpaque(false);
				easyhard.setEditable(false);
				easyhard.setForeground(Color.WHITE);
				
				add(diffslide);
				add(easyhard);
				
				JTextArea line = new JTextArea("_________________________________");
				line.setForeground(Color.WHITE);
				line.setFont(general);
				line.setMargin(new Insets(10,10,10,10));
				line.setLineWrap(true);
				line.setWrapStyleWord(true);
				line.setOpaque(false);
				line.setEditable(false);
				add(line);
				
				JTextArea topicchoose = new JTextArea("These Are The Topics You Will Be Tested On");
				topicchoose.setForeground(Color.WHITE);
				topicchoose.setFont(general);
				topicchoose.setLineWrap(true);
				topicchoose.setWrapStyleWord(true);
				topicchoose.setOpaque(false);
				topicchoose.setEditable(false);
				topicchoose.setMargin(new Insets(10,10,10,10));
				add(topicchoose);
				
				JTextArea baroque = new JTextArea("- Baroque");
				baroque.setForeground(Color.WHITE);
				baroque.setFont(general);
				baroque.setLineWrap(true);
				baroque.setWrapStyleWord(true);
				baroque.setOpaque(false);
				baroque.setEditable(false);
				baroque.setMargin(new Insets(10,10,10,10));
				add(baroque);
				
				JTextArea classical = new JTextArea("- Classical");
				classical.setForeground(Color.WHITE);
				classical.setLineWrap(true);
				classical.setWrapStyleWord(true);
				classical.setOpaque(false);
				classical.setEditable(false);
				classical.setMargin(new Insets(10,10,10,10));
				classical.setFont(general);
				add(classical);
				
				JTextArea romantic = new JTextArea("- Romantic");
				romantic.setForeground(Color.WHITE);
				romantic.setFont(general);
				romantic.setLineWrap(true);
				romantic.setWrapStyleWord(true);
				romantic.setOpaque(false);
				romantic.setEditable(false);
				romantic.setMargin(new Insets(10,10,10,10));
				add(romantic);
				
				JTextArea twenty = new JTextArea("- 20th & 21st");
				twenty.setForeground(Color.WHITE);
				twenty.setFont(general);
				twenty.setLineWrap(true);
				twenty.setWrapStyleWord(true);
				twenty.setOpaque(false);
				twenty.setEditable(false);
				twenty.setMargin(new Insets(10,10,10,10));
				add(twenty);
			}
			
			
			class SliderListener implements ChangeListener 
			{
				public void stateChanged (ChangeEvent evt) 
				{
					val = diffslide.getValue();
					//System.out.print(val);
					if(val >= 5)
					{
						easyHard = true;
					}
				}
			}
		}
		//This is where the title of the page is displayed
		class TopLabel extends JPanel
		{
			public TopLabel()
			{
				setLayout(new FlowLayout());
				setBackground(Color.BLACK);
				
				JLabel top = new JLabel("Select Test Difficulity");
				top.setFont(title);
				top.setForeground(Color.WHITE);
				add(top);
			}
		}
	}
	//This contains the information for the test and here all the textfiles are called and 
	//all the work for different parts of the test is worked on here. This can be tracked
	//by seeing what in GameData is called from different parts of the code.
	class GameData
	{
		private String first, last; //This is the first and last name values
		private String question; //This is contains the question taken from the text file
		private String [] answerSet;//This contains the answer choices taken from the text file
		private int correctAnswer; //This contains an int which indicates which answer choice is correct
		private boolean [] chosenQuestions; //This is used to indicate which question is correct or incorrect
		private int questionCount; //This indicates how any questions have been asked
		private int correctCount, lastGameCorrectCount; //This indicates how many questions have been answered
			//correctly in the current and last game
		private int questionsInFile; //This indicatates how many questions are in the file so more can be added 
		
		public GameData()
		{
			first = "";
			last = "";
			questionsInFile = 18;
			correctCount = 0;
			resetAll();
		}
		
		public void resetAll()
		{
			lastGameCorrectCount =  correctCount;
			answerSet = new String[4];
			question = "";
			for(int i =0; i < answerSet.length; i++)
			{
				answerSet[i] = "";
			}
			correctAnswer = -1;
			chosenQuestions = new boolean[30];
			questionCount = correctCount = 0;
		}
		
		public void grabQuestionFromFile()
		{
			String fileName = "";
			Scanner inFile = null;
			if (val < 5)
			{
				fileName = "easyQuestions.txt";
			}
			else if (val >= 5)
			{
				fileName = "hardQuestions.txt";
			}
			File inputFile = new File(fileName);
			try
			{
				inFile = new Scanner(inputFile);
			}
			catch (FileNotFoundException e)
			{
				System.err.printf("ERROR: Cannot open %s\n", fileName);
				System.err.println(e);
				System.exit(1);
			}
			int questionNumber = (int)(Math.random() *questionsInFile);
			while(chosenQuestions[questionNumber] == true)
			{
				questionNumber = (int)(Math.random() *questionsInFile);
			}
			chosenQuestions[questionNumber] = true;
			questionCount++;
			int counter = 0;
			while(inFile.hasNext() && counter < 6 * questionNumber)
			{
				String line = inFile.nextLine();
				counter++;
			}
			question = inFile.nextLine();
			
			counter = 0;
			while(inFile.hasNext() && counter < 4)
			{
				answerSet[counter] = inFile.nextLine();
				counter++;
			}
			correctAnswer = inFile.nextInt();
			inFile.close();
		}
		
		public void setName(String f, String l)
		{
			first = f;
			last = l;
		}
		
		public String getQuestion()
		{
			return "" + questionCount + ". "+ question;
		}
		
		public String getAnswer(int index)
		{
			return answerSet[index];
		}
		
		public int getCorrectAnswer()
		{
			return correctAnswer;
		}
		
		public int getQuestionCount()
		{
			return questionCount;
		}
		
		public int getCorrectCount()
		{
			return lastGameCorrectCount;
		}
		
		public void addOneToCorrectCount()
		{
			correctCount++;
		}
		
		public String toString()
		{
			if(lastGameCorrectCount > 2)
			{ 
				return "Great Job, " + first + " " + last + ", you answered " + lastGameCorrectCount +
					" out of 4 of the questions correctly. After exiting the game or replaying it your name will" +
					" be added to the highscore board on the right. ";
			}
			else
			{
				return "Good attempt " + first + " " + last + " but, you only answered " + lastGameCorrectCount +
				" out of 4 questions correctly." +
				". Be sure to review for the test by going to the home page and clicking the information tab to see information regarding the test";
			}
		}
		
		public String getHighScores()
		{
			String result = "";
			String fileName = "highScores2.txt";
			Scanner inFile = null;
			File inputFile = new File(fileName);
			try
			{
				inFile = new Scanner(inputFile);
			}
			catch(FileNotFoundException e)
			{
				System.err.printf("ERROR: Cannot open %s\n", fileName);
				System.out.println(e);
				System.exit(1);
			}
			while (inFile.hasNext())
			{
				String line = inFile.nextLine();
				result += line + "\n";
			}
			return result;
		}
		
		public void saveToHighScores()
		{
			if (lastGameCorrectCount >= 3)
			{
				String result = "";
				boolean hasBeenAdded = false;
				String fileName = "highScores2.txt";
				Scanner inFile = null;
				File inputFile = new File(fileName);
				try
				{
					inFile = new Scanner(inputFile);
				}
				catch(FileNotFoundException e)
				{
					System.err.printf("ERROR: Cannot open %s\n", fileName);
					System.out.println(e);
					System.exit(1);
				}
				while(inFile.hasNext())
				{
					String line = inFile.nextLine();
					if (!hasBeenAdded && Integer.parseInt("" + line.charAt(line.indexOf("/")-1)) <= lastGameCorrectCount)
					{
						result += first + " " + last + " " + lastGameCorrectCount + "/4\n";
						hasBeenAdded = true;
					}
					result += line + "\n";
				}
				if (!hasBeenAdded)
				{
					result += first + " " + last + " " + lastGameCorrectCount + "/4\n";
				}
				inFile.close();
				
				File ioFile = new File("highScores2.txt");
				PrintWriter outFile = null;
				try
				{
					outFile = new PrintWriter(ioFile);
				}
				catch(IOException e)
				{
					e.printStackTrace();
					System.exit(1);
				}
				outFile.print(result);
				outFile.close();
			}
		}
	}
}

//This is the creation of the game selection panel and depending on the
//buttons clicked in this panel it will either send you to the match game
//or the test game.
class GameChoosePanel extends JPanel
{
	private Font title;  //This is a font
	private Font general;//This is a font
	private CardLayout listOfCards;//This is the cards name for starting panel
	private GameStartHolder primaryPanel; //This is the cards Holder for for starting panel
	private CardLayout gameCards; //This is the cards name for game panels
	private GameSelectionHolder gamePanels;//This is the cards name for game panels

	public GameChoosePanel(CardLayout c, GameStartHolder p, CardLayout cg, GameSelectionHolder pg)
	{
		
		listOfCards = c;
		primaryPanel = p;
		gameCards = cg;
		gamePanels = pg;
		
		title = new Font("SansSerif", Font.BOLD, 30);
		general = new Font("SansSerif", Font.PLAIN, 22);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		
		GameSelectionTop gst = new GameSelectionTop();
		MatchAndTestHolder math = new MatchAndTestHolder();
		GameSouthButton gsb = new GameSouthButton();
		
		add(gst, BorderLayout.NORTH);
		add(math,BorderLayout.CENTER);
		add(gsb, BorderLayout.SOUTH);
	}
	//this is the title of the game selection panel
	class GameSelectionTop extends JPanel
	{
		public GameSelectionTop()
		{
			setBackground(Color.BLACK);
			setLayout(new FlowLayout());
			JLabel gameSelectionTitle = new JLabel ("Choose A Game Below");
			gameSelectionTitle.setForeground(Color.WHITE);
			gameSelectionTitle.setFont(title);
			add(gameSelectionTitle);
		}
	}
	//This contains the holder for the match game and the test
	class MatchAndTestHolder extends JPanel
	{
		public MatchAndTestHolder()
		{
			setBackground(Color.BLACK);
			setLayout(new GridLayout(1,2));
			MatchInfoAndSend mias = new MatchInfoAndSend();
			TestInfoAndSend tias =  new TestInfoAndSend();
			
			add(mias);
			add(tias);
		}
		//This contains the button which takes you to match and the information about match
		class MatchInfoAndSend extends JPanel
		{
			public MatchInfoAndSend()
			{
				setBackground(Color.BLACK);
				setLayout(new GridLayout(2,1));
				
				JButtonListener matchListenerGame = new JButtonListener();
				
				JButton matchSend = new JButton("Match");
				matchSend.setFont(general);
				matchSend.setForeground(Color.WHITE);
				matchSend.addActionListener(matchListenerGame);
				matchSend.setOpaque(false);
				matchSend.setContentAreaFilled(false);
				add(matchSend);
				
				String matchDesc = "In the match game you will be tested on only the composers and which musical time period they are from." +
					"\n\nTo begin the match section you should press the start button and then you will have to begin matching the composer to the time period by selecting the correct button at" +
					" the bottom when the name is displayed and moving around the screen, once you are confident in your answer press answer you belive to be correct to submit and check, if it is correct the next name will popup if incorrect the name will stay the same.\n\n"+
					"During the game if you get things correct, your score increases and you have infinite guesses to try.\n\nIn addition to this the timer will count down during " +
					"the game. Once the timer hits zero the name on the screen and the timer will turn red letting you know your time is up.\n\nYou will also be able to reset the game when done so you can be tested as many times as you like.\n\nIf you press the back button the timer will freeze" +
					" and you will have to press start again to continue the timer or reset to start testing again.\n\nThis part of the game is based around the users willingness to " +
					"learn and have fun.";
				JTextArea textArea = new JTextArea(matchDesc,10,25);
				textArea.setEditable(false);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				JScrollPane matchScroll = new JScrollPane(textArea);
				
				matchScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				add(matchScroll);
			}
			
			class JButtonListener implements ActionListener
			{
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("Match"))
					{
						gameCards.show(gamePanels, "MatchGame");
					}
				}
			}
		}
		//This contains the button which takes you to the test and contains info about the test
		class TestInfoAndSend extends JPanel
		{
			public TestInfoAndSend()
			{
				setBackground(Color.BLACK);
				setLayout(new GridLayout(2,1));
				
				JButtonListener testListenerGame = new JButtonListener();
				
				JButton testSend = new JButton("Test");
				testSend.setFont(general);
				testSend.setForeground(Color.WHITE);
				testSend.addActionListener(testListenerGame);
				testSend.setOpaque(false);
				testSend.setContentAreaFilled(false);
				add(testSend);
				
				String testDesc = "In this section of the game you will take a 4 question test based on the information in the infomation section and you will be able to choose your difficulty" +
					".\n\n Once you choose your difficulty on the next page with the slider on the top left enter your first and last name and only then will the test open to you.\n\nWhen in the test click your answer then click the" +
					" check answer button. \n\nIf the answer selected lights up green then your answer was correct, but if its red then you were incorrect.\n\nAfter getting your result click the next question" +
					" button and procceed till the end of the test. At the end you will be able to press the finish the test button and see your results and the leaderboard. \n\nThen you can choose to go again or quit."+
					"\n\nThis part of the game is meant to test the user's knowledge of the given information";
				JTextArea textArea = new JTextArea(testDesc,10,25);
				textArea.setEditable(false);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				JScrollPane testScroll = new JScrollPane(textArea);
				
				testScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				add(testScroll);
			}
			
			class JButtonListener implements ActionListener
			{
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("Test"))
					{
						gameCards.show(gamePanels, "TestGameHolder");
					}	
				}
			}
		}
	}
	//This contains the button which returns you to the starting page
	class GameSouthButton extends JPanel
	{
		public GameSouthButton()
		{
			setBackground(Color.BLACK);
			setLayout(new FlowLayout());
			
			JButtonListener backListenGame = new JButtonListener();
			
			JButton gameBack = new JButton("Back");
			gameBack.setFont(general);
			gameBack.setForeground(Color.WHITE);
			gameBack.addActionListener(backListenGame);
			gameBack.setOpaque(false);
			gameBack.setContentAreaFilled(false);
			add(gameBack);
		}
		
		class JButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					listOfCards.show(primaryPanel, "StartingPage");
				}
			}
		}
	}
}

//This is the creation of the match game and is where the game will be
//played. This contains a right and left panel. The left lets you start the game
//The right lets you play it.
class MatchGame extends JPanel
{
	private CardLayout gameCards; //This is the cards name for game panel
	private GameSelectionHolder gamePanels;//This is the cards name for game panel
	private Font title; //This is a font
	private Font general; //This is a font
	private Timer countDowntimer; //This is a timer variable so it can be stopped and started anywhere in the class
	private boolean running; //This is a variable for if the timer is running so it can be taken into account anywhere
	private int x, y; //This indicates the x & y values of the moving string
	private int scoreVal; //This indicates the score of the game 
	private String composerName;//This string contains the name on the moving string
	private String classicalTime; //This contains the correct answer which corrolates to the moving string
	private JLabel score; //This is used to update the score
	private double secondsDecimal, secondsDisplay; //These are double values for the displayed seconds on the timer
	private int elapsedMinutes; //This is how many minutes are displayed on the timer
			
	public MatchGame(CardLayout c, GameSelectionHolder p)
	{
		title = new Font("SansSerif", Font.BOLD, 30);
		general = new Font("SansSerif", Font.PLAIN, 18);
		gameCards = c;
		gamePanels = p;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		MatchGamePanel mgp = new MatchGamePanel();
		
		MatchInfoSection mis = new MatchInfoSection();
		
		add(mgp, BorderLayout.WEST);
		add(mis, BorderLayout.EAST);
	}
	//This forms the different sections of the match game on the left side
	class MatchGamePanel extends JPanel
	{
		public MatchGamePanel()
		{
			setLayout(new BorderLayout());
			setBackground(Color.LIGHT_GRAY);
			
			MatchGameMoving mgm = new MatchGameMoving();
			MatchGameEnterText mget = new MatchGameEnterText();
			DrawPanel dp = new DrawPanel();
			
			add(mgm, BorderLayout.NORTH);
			add(dp, BorderLayout.CENTER);
			add(mget, BorderLayout.SOUTH);
		}
	}
	//Here the moving components of the match game are created and drawn
	class DrawPanel extends JPanel 
	{
		private int count;
		private boolean left, up;
		private Timer timer;
	
		
		public DrawPanel() 
		{
			x = 0; y = 30;	
			count = 0;
			up = false;
			Mover mover = new Mover();
			timer = new Timer(5, mover);
			timer.start();
			
			nameChoose();
		}
		
		public void nameChoose()
		{
			int nameNum =(int)(Math.random() * 4) + 1;
			
			if (nameNum == 1)
			{
				classicalTime = "Baroque";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Handel";
				else if(compName == 2)
					composerName = "Bach";
				else if(compName == 3)
					composerName = "Vivaldi";
				else if(compName == 4)
					composerName = "Corelli";
			}
			else if (nameNum == 2)
			{
				classicalTime = "Classical";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Beethoven";
				else if(compName == 2)
					composerName = "Mozart";
				else if(compName == 3)
					composerName = "Hayden";
				else if(compName == 4)
					composerName = "Czerny";
			}
			else if (nameNum == 3)
			{
				classicalTime = "Romantic";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Brahms";
				else if(compName == 2)
					composerName = "Chopin";
				else if(compName == 3)
					composerName = "Tchaikovsky";
				else if(compName == 4)
					composerName = "Schubert";
			}
			else if (nameNum == 4)
			{
				classicalTime = "20th & 21st Centuries";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Bartok";
				else if(compName == 2)
					composerName = "Britten";
				else if(compName == 3)
					composerName = "Copland";
				else if(compName == 4)
					composerName = "Shostakovich";
			}
		}
		//This allows us to make sure that while the moving components move
		//they don't continuosly write on themseleves and the old position gets wiped
		private class Mover implements ActionListener
		{
			public Mover()
			{
				setBackground(Color.BLACK);
			}
			public void actionPerformed(ActionEvent evt)
			{
				//requestFocusInWindow();
				repaint();
			}

		}
		//This decides the direction of movment of the moving components when
		//they reach the edge of the drawing area
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			setBackground(Color.BLACK);
			if (secondsDisplay == 0 && elapsedMinutes == 0)
				g.setColor(Color.RED);
			else
				g.setColor(Color.WHITE);
			g.setFont(title);
			g.drawString(composerName, x+20, y+20);
			// moves the string up and down
			if (!up && y < getHeight()-90)
			{ 
				y++;
				x++;
			}
			else 
			{ 
				up = true; 
				y--; 
			}
			if ( up && y > 0 ) 
				y--;
			else 
			{ 
				up = false; 
				y++;
			}
			if (!left && x < this.getWidth()-200) 
			x++;
			else 
			{ 
				left = true; 
				x--; 
			}
			if ( left && x > 0 ) 
				x--;
			else 
			{ 
				left = false; 
				y++;
				x++;
			}
		}
	}
	//This contains the Jbuttons at the bottom of match which are used to play the game
	class MatchGameEnterText extends JPanel
	{
			
		public MatchGameEnterText()
		{
			setLayout(new FlowLayout());
			setBackground(Color.BLACK);
			
			JButtonListener answerListener = new JButtonListener();
				
			JButton baroque = new JButton("Baroque");
			baroque.setFont(general);
			baroque.setForeground(Color.WHITE);
			baroque.addActionListener(answerListener);
			baroque.setOpaque(false);
			baroque.setContentAreaFilled(false);
			add(baroque);
			
			JButton classical = new JButton("Classical");
			classical.setFont(general);
			classical.setForeground(Color.WHITE);
			classical.addActionListener(answerListener);
			classical.setOpaque(false);
			classical.setContentAreaFilled(false);
			add(classical);
			
			JButton roman = new JButton("Romantic");
			roman.setFont(general);
			roman.setForeground(Color.WHITE);
			roman.addActionListener(answerListener);
			roman.setOpaque(false);
			roman.setContentAreaFilled(false);
			add(roman);
			
			JButton twenty = new JButton("20th & 21st Centuries");
			twenty.setFont(general);
			twenty.setForeground(Color.WHITE);
			twenty.addActionListener(answerListener);
			twenty.setOpaque(false);
			twenty.setContentAreaFilled(false);
			add(twenty);
		}
		
		//This checks to see if the answer selected is correct
		class JButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				
				if(command.equals(classicalTime) && running == true)
				{
					scoreVal = scoreVal+100;
					String scorevalue = " " + scoreVal ;
					score.setText("Score:" + scorevalue);
					
					nameChoose();
				}
			}
			
			public void nameChoose()
		{
			int nameNum =(int)(Math.random() * 4) + 1;
			
			if (nameNum == 1)
			{
				classicalTime = "Baroque";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Handel";
				else if(compName == 2)
					composerName = "Bach";
				else if(compName == 3)
					composerName = "Vivaldi";
				else if(compName == 4)
					composerName = "Corelli";
			}
			else if (nameNum == 2)
			{
				classicalTime = "Classical";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Beethoven";
				else if(compName == 2)
					composerName = "Mozart";
				else if(compName == 3)
					composerName = "Hayden";
				else if(compName == 4)
					composerName = "Czerny";
			}
			else if (nameNum == 3)
			{
				classicalTime = "Romantic";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Brahms";
				else if(compName == 2)
					composerName = "Chopin";
				else if(compName == 3)
					composerName = "Tchaikovsky";
				else if(compName == 4)
					composerName = "Schubert";
			}
			else if (nameNum == 4)
			{
				classicalTime = "20th & 21st Centuries";
				
				int compName =(int)(Math.random() * 4) + 1;
				if (compName == 1)
					composerName = "Bartok";
				else if(compName == 2)
					composerName = "Britten";
				else if(compName == 3)
					composerName = "Copland";
				else if(compName == 4)
					composerName = "Shostakovich";
			}
		}
		}
	}
	
	
	//This is the header of the match game
	class MatchGameMoving extends JPanel
	{
		public MatchGameMoving()
		{
			setBackground(Color.BLACK);
			
			String matchTempShow = "Welcome To Match Game, Press The Start Button To Begin The Game, For More Instructions Look On The Prior Panel";
			JLabel matchTemp = new JLabel(matchTempShow);
			matchTemp.setForeground(Color.WHITE);
			add(matchTemp);
		}
	}

	//This builds the right side of the match panel
	class MatchInfoSection extends JPanel
	{
		public MatchInfoSection()
		{
			setBackground(Color.BLACK);
			setLayout(new BorderLayout());
			
			CDTimer timer = new CDTimer();
			
			Font scoreTitle = new Font("SansSerif", Font.BOLD, 25);
			
			score = new JLabel ("Score:\n" + scoreVal);
			score.setForeground(Color.WHITE);
			score.setFont(scoreTitle);
			
			add(score, BorderLayout.NORTH);
			add(timer, BorderLayout.CENTER);			
			
			BackSelection bs = new BackSelection();
			add(bs, BorderLayout.SOUTH);
		}
		
		
		//This contains a button which takes you to the game selection page
		class BackSelection extends JPanel
		{
			public BackSelection()
			{
				setLayout(new BorderLayout());
				setBackground(Color.BLACK);
				
				JButtonListener backListenGame = new JButtonListener();
				
				JButton gameBack = new JButton("Back");
				gameBack.setFont(general);
				gameBack.setForeground(Color.WHITE);
				gameBack.addActionListener(backListenGame);
				gameBack.setOpaque(false);
				gameBack.setContentAreaFilled(false);
				
				add(gameBack, BorderLayout.SOUTH);
			}
		}
		//This creates a count down timer 
		class CDTimer extends JPanel implements ActionListener
		{
			private JButton starter, reset; //These are jbuttons to start and reset the timer 
			private int time; //This has the value of the timer 
			private int tenthSec; //This is how many miliseconds are on the timer
			private int elapsedSeconds; //This is how many seconds have passed with  the timer 

			public CDTimer()
			{
				setBackground(Color.BLACK);
				setLayout(new FlowLayout());
				
				initialValues();
				countDowntimer = new Timer ( 100, this );
				
				starter = new JButton ( "START" );
				starter.addActionListener ( this );
				add(starter);
				
				reset = new JButton ( "RESET" );
				reset.addActionListener ( this );
				this.add ( reset );
			}
			
			public void initialValues()
			{
				time = 30; //This is the length of the timer.
				tenthSec = elapsedSeconds = elapsedMinutes = 0;
				secondsDecimal = 0.1;
				running = false;
			}
			
			public void paintComponent ( Graphics g )   
			{
				super.paintComponent ( g );
				if (secondsDecimal == 0 && elapsedMinutes == 0)
				{
					g.setColor(Color.RED);
				}
				else
					g.setColor ( Color.WHITE);
				g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
				secondsDecimal = time - tenthSec / 10.0;
				secondsDisplay = secondsDecimal % 60; 	
				elapsedMinutes = (int)secondsDecimal / 60;
				g.setFont(title);
				g.drawString (elapsedMinutes + ":" + 
					String.format("%.1f", secondsDisplay) + "" , 20, 140 );
				g.setColor ( Color.WHITE );
			}
			
			public void nameChoose()
			{
				int nameNum =(int)(Math.random() * 4) + 1;
				
				if (nameNum == 1)
				{
					classicalTime = "Baroque";
					
					int compName =(int)(Math.random() * 4) + 1;
					if (compName == 1)
						composerName = "Handel";
					else if(compName == 2)
						composerName = "Bach";
					else if(compName == 3)
						composerName = "Vivaldi";
					else if(compName == 4)
						composerName = "Corelli";
				}
				else if (nameNum == 2)
				{
					classicalTime = "Classical";
					
					int compName =(int)(Math.random() * 4) + 1;
					if (compName == 1)
						composerName = "Beethoven";
					else if(compName == 2)
						composerName = "Mozart";
					else if(compName == 3)
						composerName = "Hayden";
					else if(compName == 4)
						composerName = "Czerny";
				}
				else if (nameNum == 3)
				{
					classicalTime = "Romantic";
					
					int compName =(int)(Math.random() * 4) + 1;
					if (compName == 1)
						composerName = "Brahms";
					else if(compName == 2)
						composerName = "Chopin";
					else if(compName == 3)
						composerName = "Tchaikovsky";
					else if(compName == 4)
						composerName = "Schubert";
				}
				else if (nameNum == 4)
				{
					classicalTime = "20th & 21st Centuries";
					
					int compName =(int)(Math.random() * 4) + 1;
					if (compName == 1)
						composerName = "Bartok";
					else if(compName == 2)
						composerName = "Britten";
					else if(compName == 3)
						composerName = "Copland";
					else if(compName == 4)
						composerName = "Shostakovich";
				}
			}
			
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if ( command != null )   
				{
					if ( command.equals("START") )
					{
						running = true;
						countDowntimer.start();

					}
					else if ( command.equals("RESET") )
					{
						initialValues();
						x=0;
						y=30;
						scoreVal = 0;
						String scorevalue = " " + scoreVal ;
						
						score.setText("Score:" + scorevalue);
						nameChoose();
					}
				}

				if (secondsDisplay == 0 && elapsedMinutes == 0)
				{
					countDowntimer.stop();
					running = false;
					this.repaint();
				}
				if ( running )
					tenthSec++;
				this.repaint ( );
			}
		}
		//This takes you back to the game selection page and freezes the timer position and 
		//also resets the score if the timer is stopped
		class JButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					gameCards.show(gamePanels, "GameChoosePanel");
					countDowntimer.stop();
					scoreVal = 0;	
					running = false;
				}
			}
		}
	}
}	

//This is the creation of the leaderboard and has two panels to hold the
//match game winners and test game winners 
class LeaderBoardPanel extends JPanel
{
	private CardLayout listOfCards; //This is the name of cards for the starting panels
	private GameStartHolder primaryPanel;//This is the starting panel card holder
	private Font title; //This is a font
	private Font general;//This is a font
	public LeaderBoardPanel(CardLayout c, GameStartHolder p)
	{
		
		listOfCards = c;
		primaryPanel = p;
		title = new Font("SansSerif", Font.BOLD, 30);
		general = new Font("SansSerif", Font.PLAIN, 22);
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		
		LeaderBoardsPanel lbp = new LeaderBoardsPanel();
		LeaderSouthButton lsb = new LeaderSouthButton();
		
		add(lbp, BorderLayout.CENTER);
		add(lsb, BorderLayout.SOUTH);
		
	}
	
	class LeaderBoardsPanel extends JPanel
	{
		public LeaderBoardsPanel()
		{
			setBackground(Color.BLACK);
			setLayout(new FlowLayout());
			

			TestLeaderBoard tlb = new TestLeaderBoard();

			add(tlb);
		}

		//This calls the highscore values from the text file and displays them in a 
		//scrollpane
		class TestLeaderBoard extends JPanel
		{
			public TestLeaderBoard()
			{
				setBackground(Color.BLACK);
				setLayout(new BorderLayout());
					
				JLabel testBoard = new JLabel ("HighScores:");
				testBoard.setForeground(Color.WHITE);
				testBoard.setFont(title);
				add(testBoard, BorderLayout.NORTH);
				
				JTextArea highScoresArea = new JTextArea("" + getHighScores(), 10,20);
				highScoresArea.setForeground(Color.BLACK);
				highScoresArea.setFont(general);
				highScoresArea.setLineWrap(true);
				highScoresArea.setWrapStyleWord(true);
				highScoresArea.setOpaque(false);
				highScoresArea.setEditable(false);
				highScoresArea.setMargin(new Insets(10,10,10,10));
				JScrollPane scroller = new JScrollPane(highScoresArea);
				scroller.setOpaque(false);
				add(scroller, BorderLayout.SOUTH);
			}
			
			public String getHighScores()
			{
				String result = "";
				String fileName = "highScores2.txt";
				Scanner inFile = null;
				File inputFile = new File(fileName);
				try
				{
					inFile = new Scanner(inputFile);
				}
				catch(FileNotFoundException e)
				{
					System.err.printf("ERROR: Cannot open %s\n", fileName);
					System.out.println(e);
					System.exit(1);
				}
				while (inFile.hasNext())
				{
					String line = inFile.nextLine();
					result += line + "\n";
				}
				return result;
			}
		}
	}
	//This contains the back button
	class LeaderSouthButton extends JPanel
	{
		public LeaderSouthButton()
		{
			setBackground(Color.BLACK);
			setLayout(new FlowLayout());
			
			JButtonListener backListenLead = new JButtonListener();
			
			JButton leadBack = new JButton("Back");
			leadBack.setFont(general);
			leadBack.setForeground(Color.WHITE);
			leadBack.addActionListener(backListenLead);
			leadBack.setOpaque(false);
			leadBack.setContentAreaFilled(false);
			add(leadBack);
		}
		//this takes you back to the game home page
		class JButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					listOfCards.show(primaryPanel, "StartingPage");
				}
			}
		}
	}
}
