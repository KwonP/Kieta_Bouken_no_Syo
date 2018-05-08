package com.example.moire.tcg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moire.tcg.VO.Archer;
import com.example.moire.tcg.VO.User;
import com.example.moire.tcg.VO.Warrior;

/**
 * Created by Moire on 2018/03/06.
 */

public class ModeSelect extends Activity{

    User user;
    TextView view;
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_select);
        Log.i("1","setContent 완료");
        view=findViewById(R.id.statusview);

        Intent intent = getIntent();
        Log.i("1","getIntent 완료");
        user = (User)intent.getSerializableExtra("U");
        if (user.getJob().equals("W")) {
            view.setText("名前: "+user.getName()+" クラス: 戦士"+"\n"+"HP: "+user.getHitPoint());
        }else if (user.getJob().equals("A")){
            view.setText("名前: "+user.getName()+"クラス: アーチャー"+"\n"+"HP: "+user.getHitPoint());
        }

    }

    public void playBtnOnClick(View view){
        switch (view.getId()){
            case R.id.playbtn:
                Intent intent = new Intent(getApplicationContext(), SelectLevel.class);
                if (user.getJob().equals("W")){
                    intent.putExtra("W",user);
                }else if (user.getJob().equals("A")){
                    intent.putExtra("A",user);
                }
                startActivity(intent);
                break;
            case R.id.trainbtn:
                Toast.makeText(this,
                        "開発中です",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("プログラム")
                .setMessage("終了しますか?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                        finishAffinity();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}
