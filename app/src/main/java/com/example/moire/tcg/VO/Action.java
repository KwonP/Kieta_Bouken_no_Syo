package com.example.moire.tcg.VO;

import java.io.Serializable;

/**
 * Created by Moire on 2018/03/05.
 */

public class Action implements Serializable{
    String actionName;
    int DMG;

    public Action(String actionName,int DMG){
        this.DMG=DMG;
        this.actionName=actionName;
    }
    public String getActionName(){return actionName;}
    public int getDMG(){
        return DMG;
    }
}
