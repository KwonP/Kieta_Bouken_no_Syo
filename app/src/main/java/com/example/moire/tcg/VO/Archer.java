package com.example.moire.tcg.VO;

/**
 * Created by Moire on 2018/03/05.
 */

public class Archer extends User {
    Action action;
    public Archer(String job,String name) {
        super(job,name);
        action=new Action("ダブルショット",30);
        super.list.add(1,action);
        action=new Action("煙幕",(int)(10+this.dex*1.5+this.vit*0.8));
        super.addAction(2,action);
        action=new Action("攻撃",20);
        super.addAction(3,action);
    }

}
