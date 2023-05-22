public class BasicMonster extends Enemies {
    public BasicMonster(){
        this.setHp((int)(Math.random()*15*getFloor()));
        this.setAtk((int)(Math.random()*5*getFloor()));
        this.setSp((int)(Math.random()*5*getFloor()));
    }
    public void update(){
        this.setHp((int)(Math.random()*15*getFloor()));
        this.setAtk((int)(Math.random()*5*getFloor()));
        this.setSp((int)(Math.random()*5*getFloor()));
    }

}
