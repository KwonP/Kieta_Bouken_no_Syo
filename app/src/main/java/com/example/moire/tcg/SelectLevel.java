package com.example.moire.tcg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moire.tcg.VO.Archer;
import com.example.moire.tcg.VO.BasicMonster;
import com.example.moire.tcg.VO.User;
import com.example.moire.tcg.VO.Warrior;

/**
 * Created by Moire on 2018/03/06.
 */

public class SelectLevel extends Activity {
    Warrior warrior;
    Archer archer;
    BasicMonster monster;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_level);
        Intent intent = getIntent();
        if (intent.getSerializableExtra("W")!=null) {
            warrior = (Warrior) intent.getSerializableExtra("W");
        }else if (intent.getSerializableExtra("A")!=null){
            archer = (Archer) intent.getSerializableExtra("A");
        }
    }

    public void levelbtn(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.lv0btn:
                monster=new BasicMonster.Builder("スライム",1).build();
                intent = new Intent(
                        getApplicationContext()
                        ,BattlePage.class);
                intent.putExtra("M",monster);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                if (warrior!=null) intent.putExtra("U",warrior);
                else
                if (archer!=null) intent.putExtra("U",archer);
                startActivity(intent);
                finishActivity(8848);
                finish();
                break;
            case R.id.lv1btn:
                Toast.makeText(this,"開発中です",Toast.LENGTH_SHORT).show();
                break;
            case R.id.lv2btn:
                Toast.makeText(this,"開発中です",Toast.LENGTH_SHORT).show();
                break;
            case R.id.lv3btn:
                monster=new BasicMonster.Builder("キングスライム",10).build();
                intent = new Intent(
                        getApplicationContext()
                        ,BattlePage.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("M",monster);
                if (warrior!=null) intent.putExtra("U",warrior);
                else
                if (archer!=null) intent.putExtra("U",archer);
                startActivity(intent);
                finishActivity(8848);
                finish();
                break;
            default:
                finish();
                break;
        }
    }
}
