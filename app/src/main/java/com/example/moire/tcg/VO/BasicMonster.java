package com.example.moire.tcg.VO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Moire on 2018/03/05.
 */

public class BasicMonster implements Serializable{
    String name="";
    StringBuilder sb;
    int str=0;
    int dex=0;
    int vit=0;
    int hitPoint=0;
    int maxHp;
    ArrayList<Action> list=new ArrayList<>();
    static int pow=1;
    Action action;

    private BasicMonster(Builder builder){
        Random ran=new Random();
        sb=new StringBuilder();
        switch ((int)ran.nextInt(11)){
            case 0:
                this.str=builder.str;
                this.dex=builder.dex;
                this.vit=builder.vit;
                break;
            case 1: case 2: case 3:
                this.str=(int)(builder.str*1.5);
                this.dex=builder.dex;
                this.vit=builder.vit;
                sb.append("力強い ");
                //name.concat();
                break;
            case 4: case 5: case 6:
                this.dex=(int)(builder.dex*1.5);
                this.str=builder.str;
                this.vit=builder.vit;
                sb.append("素早い ");
                //name.concat("날쎈 ");
                break;
            case 7:case 8:case 9:
                this.vit=(int)(builder.vit*1.5);
                this.str=builder.str;
                this.dex=builder.dex;
                sb.append("頑丈な ");
                //name.concat("튼튼한 ");
                break;
            case 10:
                this.str=(int)(builder.str*1.5);
                this.dex=(int)(builder.dex*1.5);
                this.vit=(int)(builder.vit*1.5);
                sb.append("強い ");
                //name.concat("강한 ");
                break;
        }
        sb.append(builder.name);
        name=sb.toString();
        //this.name.concat(builder.name);

        hitPoint=(int)(50+vit*1.5);
        maxHp=hitPoint;
        action=new Action("回復",20);
        list.add(0,action);
        action=new Action("チャージ",1);
        list.add(1,action);
        action=new Action("見守る",0);
        list.add(2,action);
        action=new Action("攻撃",15);
        list.add(3,action);
    }
    public int getHitPoint(){
        return hitPoint;
    }

    public String getName(){return name;}

    public Action action(int str){
        Action ac;
       if (str<list.size()) {
           ac = list.get(str);
       }else{return null;}
       if (ac.actionName.equals("攻撃")){
           ac.DMG+=(int)(this.str*1.5+this.dex*1.5+this.vit+1.5)*pow/2;
           pow=1;
           return ac;
       }else if (ac.actionName.equals("回復")){
            hitPoint+=ac.DMG+this.str*1.3+this.dex*0.5+this.vit*1.6*(pow/4+0.5);
            pow=1;
            return ac;
       }else if (ac.actionName.equals("見守る")){//막기
            return ac;
       }else{
           pow+=1;
       }
        return ac;
    }

    public ArrayList<Action> getList(){return this.list;}

    public void def(int DMG){
        hitPoint-=DMG;
    }

    public void setHitPoint(int hitPoint){
        this.hitPoint=hitPoint;
    }

    public static class Builder{
        private int str=5;
        private int dex=5;
        private int vit=6;
        private String name;
        public Builder(String name,int lv){
            this.name=name;
            this.str*=lv;
            this.dex*=lv;
            this.vit*=lv;
        }

        public BasicMonster build(){
            return new BasicMonster(this);
        }

    }
}
