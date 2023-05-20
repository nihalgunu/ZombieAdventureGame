import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    //Creates Window
    JFrame window; //window
    //Creates title frame
    JPanel titleNamePanel; //title panel
    //Display Text
    JLabel titleNameLabel; //title text
    Font titleFont = new Font("Times New Roman", Font.PLAIN,96); //font
    //Creates Start Button
    JPanel startButtonPanel; //button panel
    JButton startButton; //button
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 24); //font

    //Create Game Lore Text
    JPanel mainTextPanel; //text panel
    JTextArea mainTextArea; //text area

    //Button Function
    TitleScreenHandler tsHandler = new TitleScreenHandler(); //initializes handler for button functionality

    //Choices Buttons
    JPanel choiceButtonPanel; //creates panel
    JButton choice1, choice2, choice3, choice4; //creates four buttons

    //Player information
    JPanel playerPanel;
    JLabel hpLabel, hpLabelNumber, atkLabel, atkLabelNumber, speedLabel, speedLabelNumber;
    int playerHP, playerATK, playerSP; //create int variables

    //Main Method
    public static void main(String[] args) {
        new Game();
    }
    //Game Window
    public Game(){
        //Make Window
        window = new JFrame(); //initializes Window
        window.setSize(800, 600); //sets window size
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //enables close button
        window.getContentPane().setBackground(Color.black); //set background color
        window.setLayout(null); //removes default layout


        //Creates title
        titleNamePanel = new JPanel(); //initializes title panel
        titleNamePanel.setBounds(100,100,600,150); //sets title panel location & size
        titleNamePanel.setBackground(Color.black); //sets text panel background
        titleNameLabel = new JLabel("City in Chaos"); //creates title text
        titleNameLabel.setForeground(Color.green); //text Color
        titleNameLabel.setFont(titleFont); //set Font

        //Creates start button
        startButtonPanel = new JPanel(); //initializes start button panel
        startButtonPanel.setBounds(300,400,200,100); //sets start button location & size
        startButtonPanel.setBackground(Color.black); //sets start button background color
        startButton = new JButton("START"); //creates start button
        //UNSURE HOW TO CHANGE BACKGROUND OF BUTTON (start.Button.setBackground() does not work)
        startButton.setForeground(Color.black); //text color of button
        startButton.setFont(normalFont); //set Font
        startButton.addActionListener(tsHandler); //adds functionality to button

        //Displays
        titleNamePanel.add(titleNameLabel); //add title text to title panel
        window.add(titleNamePanel); //adds title panel with text to window
        startButtonPanel.add(startButton); //adds button to button panel
        window.add(startButtonPanel); //adds button to window
        window.setVisible(true); //makes window appear on screen
    }
    //Game Screen
    public void createGameScreen(){
        //Disable Starting Window Panels
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        //Create Game Lore Text
        mainTextPanel = new JPanel(); //initializes panel
        mainTextPanel.setBounds(100,100,600,250); //sets locations & size
        mainTextPanel.setBackground(Color.black); //sets background color
        mainTextArea = new JTextArea("Test Text. More Test information shaba daba doo"); //initializes text
        mainTextArea.setBounds(100,100,600,250); //sets location & size
        mainTextArea.setBackground(Color.black); //set background color
        mainTextArea.setForeground(Color.white); //set text color
        mainTextArea.setFont(normalFont); //set font
        mainTextArea.setLineWrap(true); //line wrap

        //Choices Buttons
        choiceButtonPanel = new JPanel(); //initializes panel
        choiceButtonPanel.setBounds(250,350,300,150); //sets location & size
        choiceButtonPanel.setBackground(Color.black); //sets background color
        choiceButtonPanel.setLayout(new GridLayout(4,1)); //changes button layout on panel

        choice1 = new JButton("Choice 1"); //initializes button
        choice1.setForeground(Color.black); //sets text color
        choice1.setFont(normalFont); //sets font

        choice2 = new JButton("Choice 2"); //initializes button
        choice2.setForeground(Color.black); //sets text color
        choice2.setFont(normalFont); //sets font

        choice3 = new JButton("Choice 3"); //initializes button
        choice3.setForeground(Color.black); //sets text color
        choice3.setFont(normalFont); //sets font

        choice4 = new JButton(" Choice 4"); //initializes button
        choice4.setForeground(Color.black); //sets text color
        choice4.setFont(normalFont); //sets font

        //Player Information
        playerPanel = new JPanel(); //initializes panel
        playerPanel.setBounds(100,15,600,50); //sets locations & size
        playerPanel.setBackground(Color.black); //sets background color
        playerPanel.setLayout(new GridLayout(1,6)); //sets panel layout

        hpLabel = new JLabel("HP:"); //initializes label
        hpLabel.setFont(normalFont); //set font
        hpLabel.setForeground(Color.white); //set text color

        hpLabelNumber = new JLabel(); //initializes label
        hpLabelNumber.setFont(normalFont); //set font
        hpLabelNumber.setForeground(Color.white); //set text color

        atkLabel = new JLabel("ATK:"); //initializes label
        atkLabel.setFont(normalFont); //set font
        atkLabel.setForeground(Color.white); //set text color

        atkLabelNumber = new JLabel(); //initializes label
        atkLabelNumber.setFont(normalFont); //set font
        atkLabelNumber.setForeground(Color.white); //set text color

        speedLabel = new JLabel("SP:"); //initializes label
        speedLabel.setFont(normalFont); //set font
        speedLabel.setForeground(Color.white); //set text color

        speedLabelNumber = new JLabel(); //initializes label
        speedLabelNumber.setFont(normalFont); //set font
        speedLabelNumber.setForeground(Color.white); //set text color

        //Display
        mainTextPanel.add(mainTextArea); //adds text area to panel
        window.add(mainTextPanel); //adds main text panel to window

        choiceButtonPanel.add(choice1); //adds button1 to button panel
        choiceButtonPanel.add(choice2); //adds button2 to button panel
        choiceButtonPanel.add(choice3); //adds button3 to button panel
        choiceButtonPanel.add(choice4); //adds button4 to button panel
        window.add(choiceButtonPanel); //adds button panel to window

        playerPanel.add(hpLabel); //add hp label to
        playerPanel.add(hpLabelNumber); //add hp label number to panel
        playerPanel.add(atkLabel); //add atk label to panel
        playerPanel.add(atkLabelNumber); //add atk label number to panel
        playerPanel.add(speedLabel); //add speed label to panel
        playerPanel.add(speedLabelNumber); //add speed label number to panel

        window.add(playerPanel); //adds player panel to window

        playerSetup(); //run player setup

    }

    //Player setup
    public void playerSetup(){
        Player user = new Player();

        //Set labels to base stats
        hpLabelNumber.setText(String.valueOf(user.getHp())); //set text to hp
        atkLabelNumber.setText(String.valueOf(user.getAtk())); //set text to atk
        speedLabelNumber.setText(String.valueOf(user.getSp())); //set text to speed

    }

    //Button Function
    public class TitleScreenHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            createGameScreen();
        }
    }
}