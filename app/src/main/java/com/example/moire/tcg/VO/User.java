package com.example.moire.tcg.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Moire on 2018/03/05.
 */

public abstract class User implements Serializable{
    String job;
    int hitPoint;
    int str=10;
    int dex=10;
    int vit=10;
    String name;
    ArrayList<Action> list=new ArrayList<>(10);
    Action action;
    public User(String job,String name) {
        this.name=name;
        this.job=job;
        if (job.equals("W")){
            this.str+=(this.str*1.5);
            this.vit+=(this.vit*1.3);
            this.dex+=(this.dex*0.5);
        }else if (job.equals("A")){
            this.str+=(this.str*0.5);
            this.vit+=(this.vit*1.2);
            this.dex+=(this.dex*1.7);
        }
        hitPoint=(int)(100+vit*1.5);
        action=new Action("回復",20);
        list.add(0,action);
    }


    public void addAction(int i,Action action){
        list.add(i,action);
    }

    public int action(int str){Action ac;
        if (str<list.size()) {
            ac = list.get(str);
        }else{return 0;}
        if (job.equals("戦士")||job.equals("전사")){
        ac.DMG=(int)((ac.DMG+(this.str*1.3)+(this.dex*0.8)*1.3));
        }else if (job.equals("アーチャー")||job.equals("궁수")){
        ac.DMG=(int)((ac.DMG+(this.str*0.8)+(this.dex*1.5)*1.6));
        }else if (ac.actionName.equals("回復")){
           hitPoint+=ac.DMG;
           ac.DMG=0;
        }
        return ac.DMG;
    }

    public ArrayList<Action> getList(){return this.list;}

    public String getJob(){return job;}

    public int getHitPoint(){return hitPoint;}

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setHitPoint(int hitPoint){
        this.hitPoint=hitPoint;
    }

    @Override
    public String toString() {
        return "名前: "+name+" クラス: "+job;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }
        User user = (User) obj;
        if (job == null) {
            return false;
        } else if (job.equals("")){
            return false;
        }
        if (name == null) {
                return false;
        }
        return true;
    }
    public void def(int DMG){
        this.hitPoint-=(DMG-list.get(2).DMG);
    }
}
