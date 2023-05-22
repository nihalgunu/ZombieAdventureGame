import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

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
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 26); //font

    //Create Game Lore Text
    JPanel mainTextPanel; //text panel
    JTextArea mainTextArea; //text area

    //Button Functions
    ChoiceHandler choiceHandler = new ChoiceHandler(); //initialized handler for button functionality

    //Choices Buttons
    JPanel choiceButtonPanel; //creates panel
    JButton choice1, choice2, choice3, choice4; //creates four buttons

    //Player information
    Player user = new Player();
    Enemies enemy = new Enemies();
    BasicMonster basicMonster = new BasicMonster();
    EliteMonster eliteMonster = new EliteMonster();
    BossMonster bossMonster = new BossMonster();
    JPanel playerPanel;
    JLabel hpLabel, hpLabelNumber, atkLabel, atkLabelNumber, speedLabel, speedLabelNumber;
    int playerHP, playerATK, playerSP; //create int variables

    int room;

    //For Main Test
    String position; //for location

    static int[][] gameBoard;
    boolean moveOn = false;
    boolean skip = false;
    //Main Method
    public static void main(String[] args) throws InterruptedException {
        // 0 is supply drop
        // 1 is basic monster
        // 2 is elite monster
        // 3 is boss monster
        // 4 is game end
        gameBoard = new int[][]{
                {randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), 3},
                {randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), 3},
                {randomEvent(), randomEvent(), randomEvent(), 3, randomEvent(), randomEvent(), 3},
                {randomEvent(), randomEvent(), 3, randomEvent(), randomEvent(), randomEvent(), 3},
                {randomEvent(), 3, randomEvent(), 3, randomEvent(), randomEvent(), 3},
                {3, randomEvent(), 3, randomEvent(), randomEvent(), 3, 4}
        };

        new Game();


    }
    public static int randomEvent(){
        return (int)(Math.random()*3);
    }

    public String getPosition(int gameSpot) {
        if(gameSpot == 0){
            return "supplyDrop";
        }
        if(gameSpot == 1){
            basicMonster.update();
            return "basicMonster";
        }
        if(gameSpot == 2){
            eliteMonster.update();
            return "eliteMonster";
        }
        if(gameSpot == 3){
            bossMonster.update();
            return "bossMonster";
        }
        if(gameSpot == 4){
            return "Escaped";
        }
        return "null";
    }

    //Game Window
    public Game() throws InterruptedException {
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
        startButton.addActionListener(choiceHandler); //adds functionality to button
        startButton.setActionCommand("c1"); //individualizes the button


        //Displays
        titleNamePanel.add(titleNameLabel); //add title text to title panel
        window.add(titleNamePanel); //adds title panel with text to window
        startButtonPanel.add(startButton); //adds button to button panel
        window.add(startButtonPanel); //adds button to window
        window.setVisible(true); //makes window appear on screen

        waitForUser();
        createGameScreen();
    }
    //Game Screen
    public void createGameScreen() throws InterruptedException {
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
        choice1.addActionListener(choiceHandler); //sets button functionality
        choice1.setActionCommand("c1"); //individualizes the button

        choice2 = new JButton("Choice 2"); //initializes button
        choice2.setForeground(Color.black); //sets text color
        choice2.setFont(normalFont); //sets font
        choice2.addActionListener(choiceHandler); //sets button functionality
        choice2.setActionCommand("c2"); //individualizes the button

        choice3 = new JButton("Choice 3"); //initializes button
        choice3.setForeground(Color.black); //sets text color
        choice3.setFont(normalFont); //sets font
        choice3.addActionListener(choiceHandler); //sets button functionality
        choice3.setActionCommand("c3"); //individualizes the button

        choice4 = new JButton("Choice 4"); //initializes button
        choice4.setForeground(Color.black); //sets text color
        choice4.setFont(normalFont); //sets font
        choice4.addActionListener(choiceHandler); //sets button functionality
        choice4.setActionCommand("c4"); //individualizes the button

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
    public void playerSetup() throws InterruptedException {
        //Set labels to base stats
        hpLabelNumber.setText(String.valueOf(user.getHp())); //set text to hp
        atkLabelNumber.setText(String.valueOf(user.getAtk())); //set text to atk
        speedLabelNumber.setText(String.valueOf(user.getSp())); //set text to speed
        cityGate();

    }

    //Main Story
    public void cityGate() throws InterruptedException {
        position = "cityGate";
        mainTextArea.setText("You are the gate of the City. \n" +
                "A guard is standing in front of you. \n\nWhat do you do?"); //text set
        choice1.setText("Talk to the guard");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setActionCommand("c1"); //individualizes the button
        waitForUser();
        position = "talkGuard";
        mainTextArea.setText("Hello stranger.\nYou must be the chosen hero meant to save the city of mayhem" +
                "\nYou must traverse the city ahead making sure to pick up supplies and defeat the evil creatures." +
                "\nYOU ARE OUR LAST HOPE!");
        choice1.setText(">");
        choice2.setText(">>");
        waitForUser();
        city();


    }

    public void runGuard() throws InterruptedException {
        position = "runGuard";
        gameOver();
    }
    public void waitForUser() throws InterruptedException {
        moveOn = false;
        while (moveOn = false){
            TimeUnit.SECONDS.sleep(999999999);
            System.exit(0);
        }
    }

    public void city() throws InterruptedException {
        enemy.setFloor(0);
        room = 0;
        while(enemy.getFloor() < gameBoard.length - 1){
            while (room <gameBoard[room].length - 1){
                waitForUser();
                position = getPosition(gameBoard[enemy.getFloor()][room]);
                if(position == "supplyDrop"){
                    supplyDrop();
                }
                if(position == "basicMonster"){
                    combat(basicMonster);
                }
                if(position == "eliteMonster"){
                    combat(eliteMonster);
                }
                if(position == "bossMonster"){
                    combat(bossMonster);
                }
                if(position == "Survived"){
                    mainTextArea.setText("Victory");
                    waitForUser();
                }
                waitForUser();
                room++;
                if(skip){
                    if(getPosition(gameBoard[enemy.getFloor()][room+1]) == "bossMonster"){
                        mainTextArea.setText("Can not skip.\nBoss monster ahead.");
                        skip = false;
                    }else{
                        room++;
                        room++;
                        skip = false;
                    }
                }
            }
        }
        waitForUser();
        mainTextArea.setText("Healing you!");
        waitForUser();
        user.setHp(user.getHp()+15);
        mainTextArea.setText("Now onto the next floor!");
        waitForUser();
        enemy.setFloor(enemy.getFloor() + 1);
    }


    public void gameOver() throws InterruptedException {
        position = "gameOver";
        mainTextArea.setText("GAME OVER!"); //NOT WORKING
        waitForUser();
        System.exit(0);
    }

    public void combat(Enemies enemy) throws InterruptedException {
        mainTextArea.setText("An intense fight ensues.");
        waitForUser();
        mainTextArea.setText("The enemy stats are:\n HP:" + enemy.getHp()
                + "\nATK:" +enemy.getAtk()
                + "\nSP:" + enemy.getSp());
        if(user.getHp() <= 0){
            gameOver();
        } else if(enemy.getHp() <=0){
            mainTextArea.setText("Victory!");
            waitForUser();
        } else {
            waitForUser();
            if((Math.random() * user.getSp()) > (Math.random() * user.getSp())){
                enemy.setHp(enemy.getHp() - user.getAtk());
                mainTextArea.setText("You get to atk!\nEnemy HP:" + enemy.getHp());
            } else {
                mainTextArea.setText("You get attacked!\n");
                user.setHp(user.getHp() - enemy.getAtk());
            }
            combat(enemy);
        }

    }
    public void supplyDrop(){
        int supply = randomEvent();
        if(supply ==0 ){
            mainTextArea.setText("You got a piece of armor");
            user.setHp(user.getHp() + (int)(Math.random()*15*enemy.getFloor()*enemy.getFloor()));
        }
        if(supply == 0 ){
            mainTextArea.setText("You got a new weapon");
            user.setAtk(user.getAtk() + (int)(Math.random()*5*enemy.getFloor()*enemy.getFloor()));
        }
        if(supply == 0 ){
            mainTextArea.setText("You got a pair of boots");
            user.setSp(user.getSp() + (int)(Math.random()*5*enemy.getFloor()*enemy.getFloor()));
        }
    }

    //Four choices button
    public class ChoiceHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String yourChoice = event.getActionCommand(); //gets button clicked
            if(position != "cityGuard"){
                if(yourChoice == "c1"){
                    moveOn = true;
                }
                if(yourChoice == "c2"){
                    skip = true;
                }
            }

        }

    }
}