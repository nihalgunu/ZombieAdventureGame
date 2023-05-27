import java.util.Scanner;

public class Game {
    Player user = new Player();
    BasicMonster basicMonster = new BasicMonster();
    EliteMonster eliteMonster = new EliteMonster();
    BossMonster bossMonster = new BossMonster();
    String position; //for location
    int floor, room, level;
    int[][] gameBoard;
    String[][] displayBoard;

    // Use hashmap to store weapons and if weapon equipped add the atk stats buff from that     ArrayHashmap
    public static void main(String[] args) {
        new Game();
    }
    public Game(){
        System.out.println("Welcome to City of Chaos");
        System.out.println("You are the chosen one, " +
                "the goddess gifts you 5 healing potions that will get stronger each level you progress.");
        System.out.println("Within the City you will have to pass through an endless " +
                "number of floors that will constantly grow in size" +
                "best of luck young adventurer.");
        int heals = 5;

        Scanner levelAsk = new Scanner(System.in);
        System.out.println("How many levels would you like to play with: ");
        int levelSelect = levelAsk.nextInt();

        level = 1;
        while (level <= levelSelect){
            updateDisplayBoard(level);
            updateGameBoard(level);
            floor = 0;
            room = 0;
            boolean nextLevel = false;
            while (!nextLevel){
                Scanner input = new Scanner(System.in);
                boolean moveOn = false;
                while (!moveOn) {
                    displayBoard[floor][room] = "currentFloor";
                    System.out.println("Your hp is: " + user.getHp());
                    System.out.println("The map is:");
                    for (String[] strings : displayBoard) {
                        for (int j = 0; j < displayBoard[0].length; j++) {
                            System.out.print(strings[j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("What do you want to do? (up, down, left, right, stay, heal)");
                    String choice = input.next();
                    displayBoard[floor][room] = "empty";
                    switch (choice) {
                        case "up" -> {
                            if (floor - 1 < 0) {
                                System.out.println("Can not move up.");
                            } else {
                                floor--;
                                moveOn = true;
                            }
                        }
                        case "down" -> {
                            if (floor + 1 >= gameBoard.length) {
                                System.out.println("Can not move down.");
                            } else {
                                floor++;
                                moveOn = true;
                            }
                        }
                        case "right" -> {
                            if (room + 1 >= gameBoard[0].length) {
                                System.out.println("Can not move right.");
                            } else {
                                room++;
                                moveOn = true;
                            }
                        }
                        case "stay" -> moveOn = true;
                        case "heal" -> {
                            if (heals - 1 < 0) {
                                System.out.println("Can not heal. You are out of potions.");
                            } else {
                                heals--;
                                user.setHp(user.getHp() + 15 * level);
                            }
                        }
                    }

                }
                position = getPosition(gameBoard[floor][room]);
                switch (position) {
                    case "supplyDrop" -> {
                        displayBoard[floor][room] = "empty";
                        supply();
                        gameBoard[floor][room] = 5;
                    }
                    case "basicMonster" -> {
                        basicMonster.update(level);
                        System.out.println("An intense fight ensues.");
                        combat(basicMonster);
                        displayBoard[floor][room] = "empty";
                        gameBoard[floor][room] = 5;
                    }
                    case "eliteMonster" -> {
                        eliteMonster.update(level);
                        System.out.println("An intense fight ensues.");
                        combat(eliteMonster);
                        displayBoard[floor][room] = "empty";
                        gameBoard[floor][room] = 5;
                    }
                    case "bossMonster" -> {
                        bossMonster.update(level);
                        System.out.println("An intense fight ensues.");
                        combat(bossMonster);
                        displayBoard[floor][room] = "empty";
                        gameBoard[floor][room] = 5;
                    }
                    case "survived" -> {
                        System.out.println("You ran into the escape hole");
                        System.out.println("On the next floor");
                        level++;
                        nextLevel = true;
                    }
                    case "empty" -> System.out.println("Nothing is present");
                }
            }
        }

    }
    public int randomEvent(){
        return (int)(Math.random()*4);
    }
    public void updateDisplayBoard(int level){
        int count = level;
        int multiplier = 1;
        while (count >= 10){
            count = count/10;
            multiplier++;
        }
        displayBoard = new String[6*multiplier][6*multiplier];
        for(int i = 0; i < displayBoard.length; i++){
            for (int j = 0; j < displayBoard[0].length; j++){
                displayBoard[i][j] = "unknown";
            }
        }
    }
    public void updateGameBoard(int level){
        int count = level;
        int multiplier = 1;
        while (count >= 10){
            count = count/10;
            multiplier++;
        }
        gameBoard = new int[6*multiplier][6*multiplier];
        for(int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                gameBoard[i][j] = randomEvent();
            }
        }
        gameBoard[(int) (Math.random()*6*multiplier - 1)][(int) (Math.random()*6*multiplier - 1)] = 4;
    }
    public String getPosition(int gameSpot){
        if(gameSpot == 0){
            return "supplyDrop";
        }
        if(gameSpot == 1){
            return "basicMonster";
        }
        if(gameSpot == 2){
            return "eliteMonster";
        }
        if(gameSpot == 3){
            return "bossMonster";
        }
        if(gameSpot == 4){
            return "survived";
        }
        if(gameSpot == 5){
            return "empty";
        }
        System.out.println("crash");
        return "null";
    }
    public void combat(Enemies enemy){
        System.out.println("Your Stats:\nHP:" + user.getHp()
                + "\nATK:" + user.getAtk()
                + "\nSP:" + user.getSp());
        System.out.println("The enemy stats are:\n HP:" + enemy.getHp()
                + "\nATK:" + enemy.getAtk()
                + "\nSP:" + enemy.getSp());
        if(user.getHp() <= 0){
            gameOver();
        } else if(enemy.getHp() <=0){
            System.out.println("Victory!");
            supply();
        } else {
            if((Math.random() * user.getSp()) > (Math.random() * user.getSp())){
                enemy.setHp(enemy.getHp() - user.getAtk());
                System.out.println("You get to atk!\nEnemy HP:" + enemy.getHp());
            } else {
                System.out.println("You get attacked!\n");
                user.setHp(user.getHp() - enemy.getAtk());
            }
            combat(enemy);
        }
    }
    public void supply(){
        int supply = randomEvent();
        if(supply == 0 ){
            System.out.println("You got a piece of armor");
            user.setHp(user.getHp() + (int)(Math.random()*15*floor*floor + 1));
            System.out.println("Your new hp is: " + user.getHp());
        }
        if(supply == 1 ){
            System.out.println("You got a new weapon");
            user.setAtk(user.getAtk() + (int)(Math.random()*5*floor*floor + 1));
            System.out.println("Your new atk is: " + user.getAtk());

        }
        if(supply == 2 ){
            System.out.println("You got a pair of boots");
            user.setSp(user.getSp() + (int)(Math.random()*5*floor*floor + 1));
            System.out.println("Your new sp is: " + user.getAtk());

        }
        if(supply == 3){
            System.out.println("Player upgrade");
            user.setHp(user.getHp() + (int)(Math.random()*15*floor*floor + 1));
            user.setAtk(user.getAtk() + (int)(Math.random()*5*floor*floor + 1));
            user.setSp(user.getSp() + (int)(Math.random()*5*floor*floor + 1));
            System.out.println("Your new stats are:\nHP:" + user.getHp()
                    + "\nATK:" + user.getAtk()
                    + "\nSP:" + user.getSp());
        }
    }
    public void gameOver(){
        position = "gameOver";
        System.out.println("GAME OVER!"); //NOT WORKING
        System.exit(0);
    }

}
