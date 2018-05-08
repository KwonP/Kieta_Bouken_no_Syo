package com.example.moire.tcg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.moire.tcg.VO.Archer;
import com.example.moire.tcg.VO.User;
import com.example.moire.tcg.VO.Warrior;

/**
 * Created by Moire on 2018/03/05.
 */

public class JobSelect extends Activity{

    EditText editView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobselect);
    }


    public void jobSelOnClick(View view){
        switch (view.getId()){
            case R.id.warriorbtn:
                editView = new EditText(this);
                editView.setSingleLine(true);
                editView.setLines(1);
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("キャラクター名を入力してください。")
                        //setViewにてビューを設定します。
                        .setView(editView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                Intent intent=new Intent(
                                        getApplicationContext()
                                        ,ModeSelect.class);
                                Warrior warrior=new Warrior("W",editView.getText().toString());
                                Log.i("2",warrior.toString());
                                Log.i("1",editView.getText().toString());
                                intent.putExtra("U",warrior);
                                startActivityForResult(intent,8848);
                                finish();

                                /*//入力した文字をトースト出力する
                                new AlertDialog.Builder(JobSelect.this)
                                        .setTitle("確認")
                                        .setMessage(editView.getText().toString()+"\n"+"?")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // OK button pressed

                                                Intent intent=new Intent(
                                                        getApplicationContext()
                                                        ,ModeSelect.class);
                                                Warrior warrior=new Warrior("W",editView.getText().toString());
                                                Log.i("2",warrior.toString());
                                                Log.i("1",editView.getText().toString());
                                                intent.putExtra("U",warrior);
                                                startActivityForResult(intent,8848);
                                                finish();
                                            }
                                        })
                                        .setNegativeButton("Cancel", null)
                                        .show();*/
                            }
                        })
                        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
                break;
            case R.id.archerbtn:
                editView = new EditText(this);
                editView.setSingleLine(true);
                editView.setLines(1);
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("名前を入力してください。")
                        //setViewにてビューを設定します。
                        .setView(editView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //入力した文字をトースト出力する
                                new AlertDialog.Builder(JobSelect.this)
                                        .setTitle("確認")
                                        .setMessage(editView.getText().toString()+"\n"+"この名前にしますか?")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // OK button pressed

                                                Intent intent=new Intent(
                                                        getApplicationContext()
                                                        ,ModeSelect.class);
                                                Archer archer=new Archer("A",editView.getText().toString());
                                                Log.i("2",archer.toString());
                                                Log.i("1",editView.getText().toString());
                                                intent.putExtra("U",archer);
                                                startActivityForResult(intent,8848);
                                                finish();
                                            }
                                        })
                                        .setNegativeButton("Cancel", null)
                                        .show();
                            }
                        })
                        .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
                break;
            case R.id.helpbtn:
                Intent intent = new Intent(getApplicationContext(), Help.class);
                startActivity(intent);
                break;
        }

    }

}
