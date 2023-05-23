//
//public class OldGame {
//    //Player information
//    //For Main Test
//    Player user = new Player();
//    Enemies enemy = new Enemies();
//    BasicMonster basicMonster = new BasicMonster();
//    EliteMonster eliteMonster = new EliteMonster();
//    BossMonster bossMonster = new BossMonster();
//    String position; //for location
//
//    int room;
//    int[][] gameBoard;
//    //Main Method
//    public static void main(String[] args){
//        new OldGame();
//    }
//    public OldGame(){
//        // 0 is supply drop
//        // 1 is basic monster
//        // 2 is elite monster
//        // 3 is boss monster
//        // 4 is game end
//        gameBoard = new int[][]{
//                {randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), 3},
//                {randomEvent(), randomEvent(), randomEvent(), randomEvent(), randomEvent(), 3},
//                {randomEvent(), randomEvent(), randomEvent(), 3, randomEvent(), 3},
//                {randomEvent(), randomEvent(), 3, randomEvent(), randomEvent(), 3},
//                {randomEvent(), 3, randomEvent(), 3, randomEvent(), 3},
//                {3, randomEvent(), 3, randomEvent(), 3, 4}
//        };
//        floor = 0;
//        room = 0;
//        while(enemy.getFloor() < gameBoard.length - 1){
//            while (room <gameBoard[room].length - 1){
//                position = getPosition(gameBoard[enemy.getFloor()][room]);
//                if(position.equals("supplyDrop")){
//                    supplyDrop();
//                }
//                if(position.equals("basicMonster")){
//                    basicMonster.update();
//                    combat(basicMonster);
//                }
//                if(position.equals("eliteMonster")){
//                    eliteMonster.update();
//                    combat(eliteMonster);
//                }
//                if(position.equals("bossMonster")){
//                    bossMonster.update();
//                    combat(bossMonster);
//                }
//                if(position.equals("survived")){
//                    System.out.println("Victory");
//                    System.exit(0);
//                }
//                room++;
//            }
//            room = 0;
//            System.out.println("Healing you!");
//            user.setHp(user.getHp()+15);
//            System.out.println("Now onto the next floor! Floor:" + enemy.getFloor());
//            enemy.setFloor(enemy.getFloor() + 1);
//        }
//    }
//    public int randomEvent(){
//        return (int)(Math.random()*3);
//    }
//    public String getPosition(int gameSpot) {
//        if(gameSpot == 0){
//            return "supplyDrop";
//        }
//        if(gameSpot == 1){
//            return "basicMonster";
//        }
//        if(gameSpot == 2){
//            return "eliteMonster";
//        }
//        if(gameSpot == 3){
//            return "bossMonster";
//        }
//        if(gameSpot == 4){
//            return "Escaped";
//        }
//        System.out.println("crash");
//        return "null";
//    }
//
//    public void gameOver(){
//        position = "gameOver";
//        System.out.println("GAME OVER!"); //NOT WORKING
//        System.exit(0);
//    }
//
//    public void combat(Enemies enemy){
//        System.out.println("An intense fight ensues.");
//        System.out.println("The enemy stats are:\n HP:" + enemy.getHp()
//                + "\nATK:" + enemy.getAtk()
//                + "\nSP:" + enemy.getSp());
//        if(user.getHp() <= 0){
//            gameOver();
//        } else if(enemy.getHp() <=0){
//            System.out.println("Victory!");
//        } else {
//            if((Math.random() * user.getSp()) > (Math.random() * user.getSp())){
//                enemy.setHp(enemy.getHp() - user.getAtk());
//                System.out.println("You get to atk!\nEnemy HP:" + enemy.getHp());
//            } else {
//                System.out.println("You get attacked!\n");
//                user.setHp(user.getHp() - enemy.getAtk());
//            }
//            combat(enemy);
//        }
//
//    }
//    public void supplyDrop(){
//        int supply = randomEvent();
//        if(supply == 0 ){
//            System.out.println("You got a piece of armor");
//            user.setHp(user.getHp() + (int)(Math.random()*15*enemy.getFloor()*enemy.getFloor()));
//        }
//        if(supply == 0 ){
//            System.out.println("You got a new weapon");
//            user.setAtk(user.getAtk() + (int)(Math.random()*5*enemy.getFloor()*enemy.getFloor()));
//        }
//        if(supply == 0 ){
//            System.out.println("You got a pair of boots");
//            user.setSp(user.getSp() + (int)(Math.random()*5*enemy.getFloor()*enemy.getFloor()));
//        }
//    }
//
//}