public class BasicMonster extends Enemies {
    public BasicMonster(){
        this.setHp((int)(Math.random()*15));
        this.setAtk((int)(Math.random()*5));
        this.setSp((int)(Math.random()*5));
    }
    public void update(int level){
        this.setHp((int)(Math.random()*15*level + 1));
        this.setAtk((int)(Math.random()*5*level + 1));
        this.setSp((int)(Math.random()*5*level + 1));
    }

}
