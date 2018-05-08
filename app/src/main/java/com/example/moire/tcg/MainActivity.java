package com.example.moire.tcg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView fview1,fview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnOnClick(final View view){
        switch (view.getId()){
            case R.id.startbtn:
                Intent intent = new Intent(
                        getApplicationContext(),
                        JobSelect.class);
                startActivity(intent);
                finish();
                break;
            case R.id.endbtn:
                new AlertDialog.Builder(this)
                        .setTitle("プログラム")
                        .setMessage("終了しますか?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK button pressed
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus==true) {
            Log.i("2","애니메이션 스타트");
            fview1=findViewById(R.id.fview1);
            fview2=findViewById(R.id.fview2);
            fview1.setBackgroundResource(R.drawable.fire_animation);
            fview2.setBackgroundResource(R.drawable.fire_animation);
            AnimationDrawable ad= (AnimationDrawable) fview1.getBackground();
            AnimationDrawable ad2= (AnimationDrawable) fview2.getBackground();
            ad.start();ad2.start();
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
