public class EliteMonster extends Enemies{
    public EliteMonster(){
        this.setHp((int)(Math.random()*15*this.getFloor()*this.getFloor()));
        this.setAtk((int)(Math.random()*5*this.getFloor()*this.getFloor()));
        this.setSp((int)(Math.random()*5*this.getFloor()*this.getFloor()));
    }
    public void update(){
        this.setHp((int)(Math.random()*15*this.getFloor()*this.getFloor()));
        this.setAtk((int)(Math.random()*5*this.getFloor()*this.getFloor()));
        this.setSp((int)(Math.random()*5*this.getFloor()*this.getFloor()));
    }

}
