public class Enemies {
    private int hp;
    private int atk;
    private int sp;
    public Enemies(){
        this.setHp((int)(Math.random()*15));
        this.setAtk((int)(Math.random()*5));
        this.setSp((int)(Math.random()*5));
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

