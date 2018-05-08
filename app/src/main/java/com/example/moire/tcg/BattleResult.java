package com.example.moire.tcg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Moire on 2018/03/09.
 */

public class BattleResult extends Activity {

    ImageView resultView;
    TextView result,resultText;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        result=findViewById(R.id.result);
        resultText=findViewById(R.id.msg);


        intent=getIntent();
        String str1,str2;
        str1=(String) intent.getSerializableExtra("result");
        str2=(String) intent.getSerializableExtra("msg");
        result.setText(str1);
        resultText.setText(str2);
    }

    public void ending(View view){
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        resultView = findViewById(R.id.resultView);
        String str=(String) intent.getSerializableExtra("result");
        if (str.equals("Win")){
            resultView.setBackgroundResource(R.drawable.door_open);
        }else {
            resultView.setBackgroundResource(R.drawable.door_close);
        }
        AnimationDrawable ad=(AnimationDrawable) resultView.getBackground();
        ad.start();
    }
}
