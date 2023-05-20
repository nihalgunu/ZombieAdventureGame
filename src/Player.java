public class Player {
    //Variables
    private int hp;
    private int atk;
    private int sp;
    //Constructor
    public Player(){
        hp = 15;
        atk = 5;
        sp = 5;
    }
    //Getters
    public int getHp(){
        return hp;
    }
    public int getAtk(){
        return atk;
    }
    public int getSp(){
        return sp;
    }

    //Setters
    public void setHp(int hp){
        this.hp = hp;
    }
    public void setAtk(int atk){
        this.atk = atk;
    }
    public void setSp(int sp){
        this.sp = sp;
    }
}
