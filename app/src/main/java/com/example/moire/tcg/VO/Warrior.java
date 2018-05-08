package com.example.moire.tcg.VO;



public class Warrior extends User{
    Action action;

    public Warrior(String job,String name) {
        super(job,name);
        action=new Action("スマッシュ",30);
        super.list.add(1,action);
        action=new Action("ガード",(int)(10+this.vit*1.5+this.dex*0.8));
        super.list.add(2,action);
        action=new Action("攻撃",10);
        super.addAction(3,action);
    }
}
