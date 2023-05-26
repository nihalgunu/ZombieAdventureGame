public class EliteMonster extends Enemies{
    public EliteMonster(){
        this.setHp((int)(Math.random()*15));
        this.setAtk((int)(Math.random()*5));
        this.setSp((int)(Math.random()*5));
    }
    public void update(int floor){
        this.setHp((int)(Math.random()*15*floor*floor + 1));
        this.setAtk((int)(Math.random()*5*floor*floor + 1));
        this.setSp((int)(Math.random()*5*floor*floor + 1));
    }

}
