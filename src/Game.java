import java.util.Scanner;

public class Game {
    Player user = new Player();
    BasicMonster basicMonster = new BasicMonster();
    EliteMonster eliteMonster = new EliteMonster();
    BossMonster bossMonster = new BossMonster();
    String position; //for location
    int floor, room;
    int[][] gameBoard;

    // Use hashmap to store weapons and if weapon equipped add the atk stats buff from that     ArrayHashmap
    public static void main(String[] args) {
        new Game();
    }
    public Game(){
        gameBoard = new int[][]{
                {randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), 3},
                {randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), 3},
                {randomEvent(), randomEvent(), randomEvent(), 3, randomEvent(), 3},
                {randomEvent(), randomEvent(), 3, randomEvent(), randomEvent(), 3},
                {randomEvent(), 3, randomEvent(), 3, randomEvent(), 3},
                {3, randomEvent(), 3, randomEvent(), 3, 4}
        };
        floor = 0;
        room = 0;
        System.out.println("Welcome to City of Chaos");
        System.out.println("The goddess blesses you with 5 healing potions each healing you by 15*floor hp.\n" +
                "Make sure to save the potions for when you need them the most.");

        int heals = 5;
        while(floor < gameBoard.length - 1) {
            while (room < gameBoard[room].length - 1) {
                position = getPosition(gameBoard[floor][room]);
                if (position.equals("supplyDrop")) {
                    supply();
                }
                if (position.equals("basicMonster")) {
                    basicMonster.update(floor);
                    System.out.println("An intense fight ensues.");
                    combat(basicMonster);
                }
                if (position.equals("eliteMonster")) {
                    eliteMonster.update(floor);
                    System.out.println("An intense fight ensues.");
                    combat(eliteMonster);
                }
                if (position.equals("bossMonster")) {
                    bossMonster.update(floor);
                    System.out.println("An intense fight ensues.");
                    combat(bossMonster);
                }
                if (position.equals("survived")) {
                    System.out.println("Victory");
                    System.exit(0);
                }
                Scanner input = new Scanner(System.in);
                int moveOn = 0;
                while (moveOn != 1) {
                    System.out.println("Your hp is: " + user.getHp());
                    System.out.println("What do you want to do? (forward, heal)");
                    String choice = input.next();
                    if (choice.equals("forward")) {
                        room++;
                        moveOn = 1;
                    } else if (choice.equals("heal")) {
                        heals--;
                        if (heals < 0) {
                            System.out.println("Can not heal. You are out of potions.");
                        } else {
                            user.setHp(user.getHp() + 15 * floor);
                            moveOn = 1;
                        }
                    }
                }
            }
            room = 0;
            System.out.println("Healing you!");
            user.setHp(user.getHp() + 15 * floor);
            System.out.println("Now onto the next floor! Floor:" + floor);
            floor++;
        }
    }
    public int randomEvent(){
        return (int)(Math.random()*3);
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
            return "Escaped";
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
    }
    public void gameOver(){
        position = "gameOver";
        System.out.println("GAME OVER!"); //NOT WORKING
        System.exit(0);
    }

}
