package com.example.moire.tcg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moire.tcg.VO.Action;
import com.example.moire.tcg.VO.BasicMonster;
import com.example.moire.tcg.VO.User;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Moire on 2018/03/07.
 */

public class BattlePage extends Activity{

    ProgressBar enemy,user;
    TextView userName, enemyName, userHpView, enemyHpView,battlelog;
    BasicMonster monster;
    int userMaxHp, enemyMaxHp;
    int userNowHp, enemyNowHp;
    ImageButton[] btn=new ImageButton[4];
    boolean turn = true;
    boolean guard=false;
    ArrayList<Action> ulist,mlist;
    StringBuilder sb=new StringBuilder();
    String name;
    User player;
    ImageView imageView;
    ImageView effect;
    Vibrator vi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
        user = findViewById(R.id.userHP);
        userName = findViewById(R.id.userName);
        userHpView = findViewById(R.id.userHP_num);
        enemyName = findViewById(R.id.enemyName);
        enemyHpView = findViewById(R.id.enemyHP_num);
        enemy=findViewById(R.id.enemyHP);
        battlelog=findViewById(R.id.battlelog);
        vi=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        Intent intent = getIntent();
        player=(User) intent.getSerializableExtra("U");
        name = player.getName();
        userName.setText(name);
        userMaxHp = player.getHitPoint();
        userNowHp = player.getHitPoint();
        userHpView.setText(userNowHp+"/"+userMaxHp);
        ulist=player.getList();



        this.user.setMax(userMaxHp);
        this.user.setProgress(userNowHp);

        setAction();


        if (intent.getSerializableExtra("M")!=null){
            monster = (BasicMonster) intent.getSerializableExtra("M");
            enemyMaxHp=monster.getHitPoint();
            enemyNowHp=monster.getHitPoint();
            enemyName.setText(monster.getName());
            enemyHpView.setText(enemyNowHp+"/"+enemyMaxHp);
            mlist=monster.getList();
            sb.append("野生の "+monster.getName()+"が\n現れた!!\n");
            battlelog.setText(sb.toString());
        }

        enemy.setMax(enemyMaxHp);
        enemy.setProgress(enemyNowHp);
        setAction();
    }

    class clickHandler implements View.OnClickListener{


        @Override
        public void onClick(View view) {
            sb=new StringBuilder();
            if (turn){
                guard=false;
                turn=false;
                sb.append(name+"の行動!\n");
                battlelog.setText(sb.toString());
                int action=Integer.parseInt(view.getTag().toString());
                Action ac=ulist.get(action);
                int dmgcheck=0;
                switch (action){
                    case 0:
                        sb.append(name+"は休憩をとった!!");
                        int heal=userNowHp+ac.getDMG();
                        if (!(heal<=userMaxHp)){
                            player.setHitPoint(userMaxHp);
                            sb.append("体力がすべて回復した!!");
                        }else {
                            player.setHitPoint(heal);
                            sb.append(ac.getDMG()+"の体力を回復!!");
                        }
                        battlelog.setText(sb.toString());
                        userNowHp=player.getHitPoint();
                        user.setProgress(userNowHp);
                        break;
                    case 1:
                        sb.append(name+"はスキルを使った!!\n");
                        dmgcheck=enemyNowHp-ac.getDMG();
                        if (dmgcheck<=0){
                            monster.setHitPoint(0);
                        }else {
                            monster.setHitPoint(enemyNowHp - ac.getDMG());
                        }
                        sb.append(ac.getActionName()+"を使用!\n");
                        sb.append(ac.getDMG()+"のダメージ!");
                        vi.vibrate(500);

                        battlelog.setText(sb.toString());
                        enemyNowHp=monster.getHitPoint();
                        enemy.setProgress(enemyNowHp);
                        break;
                    case 2:
                        sb.append(name+"はガードした!!");
                        battlelog.setText(sb.toString());
                        guard=true;
                        break;
                    case 3:
                        sb.append(name+"の攻撃!!\n");
                        dmgcheck=enemyNowHp-ac.getDMG();
                        if (dmgcheck<=0){
                            monster.setHitPoint(0);
                        }else {
                            monster.setHitPoint(enemyNowHp - ac.getDMG());
                        }
                        sb.append(ac.getDMG()+"のダメージ!");
                        vi.vibrate(500);
                        battlelog.setText(sb.toString());
                        enemyNowHp=monster.getHitPoint();
                        enemy.setProgress(enemyNowHp);
                        break;
                }
                setHp();

                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable()  {
                    public void run() {
                        //#명령어
                        monsterTurn();
                    }
                }, 2000);
                setAction();
                gameover();
            }
        }
    }


    public void setAction(){
        Random uran=new Random();
        for (int i=0;i<btn.length;i++){
            int k=uran.nextInt(ulist.size());
            String name="btn"+i;
            int strId = getResources().getIdentifier(name, "id",getPackageName());
            ImageButton bt=(ImageButton)findViewById(strId);
            clickHandler cl=new clickHandler();
            bt.setOnClickListener(cl);
            switch (k){
                case 0://휴식
                    bt.setImageResource(R.drawable.heal);
                    bt.setTag(0);
                    break;
                case 1://특수공격
                    bt.setImageResource(R.drawable.skill);
                    bt.setTag(1);
                    break;
                case 2://막기행동
                    bt.setImageResource(R.drawable.def);
                    bt.setTag(2);
                    break;
                case 3://일반공격
                    bt.setImageResource(R.drawable.attck);
                    bt.setTag(3);
                    break;
            }
        }
    }

    public void monsterTurn(){
        Random ran=new Random();
        sb=new StringBuilder();
        int action=ran.nextInt(btn.length);
        Action ac=monster.action(action);
        sb.append(monster.getName()+"の行動\n");
        battlelog.setText(sb.toString());
        sb.append(monster.getName()+"の"+ac.getActionName()+"!!\n");
        battlelog.setText(sb.toString());
        switch (ac.getActionName()){
            case "回復":
                int heal=enemyNowHp+ac.getDMG();
                if (!(heal<=enemyMaxHp)){
                    monster.setHitPoint(enemyMaxHp);
                    sb.append("体力をすべて回復した");
                }else {
                    monster.setHitPoint(heal);
                    sb.append(ac.getDMG()+"の体力を回復した");
                }
                battlelog.setText(sb.toString());
                enemyNowHp=monster.getHitPoint();
                enemy.setProgress(enemyNowHp);
                break;
            case "チャージ":
                monster.setHitPoint(monster.getHitPoint()+ac.getDMG());
                sb.append(monster.getName()+"は力を溜めている");
                battlelog.setText(sb.toString());
                enemyNowHp=monster.getHitPoint();
                enemy.setProgress(enemyNowHp);
                break;
            case "見守る":
                sb.append(monster.getName()+"は\nただこちらを観察している...");
                battlelog.setText(sb.toString());
                break;
            case "攻撃":
                int dmgcheck;
                if (guard) {
                    dmgcheck=(ac.getDMG()-ulist.get(2).getDMG());
                    Log.i("1","가드 활성");
                    Log.i("1",dmgcheck+"");
                }else{
                    dmgcheck=ac.getDMG();
                    Log.i("1","가드 비활성");
                    Log.i("1",dmgcheck+"");
                }
                if (dmgcheck<=0){
                    dmgcheck=0;
                    sb.append(name+"は攻撃をすべてガードした\n");
                    battlelog.setText(sb.toString());
                    vi.vibrate(1000);
                }

                if ((userNowHp-dmgcheck)<=0){
                    player.setHitPoint(0);
                }else {
                    player.setHitPoint(userNowHp-ac.getDMG());
                }

                sb.append(dmgcheck+"のダメージ!!");
                vi.vibrate(500);
                battlelog.setText(sb.toString());
                userNowHp=player.getHitPoint();
                user.setProgress(userNowHp);
                break;
        }
        setHp();
        turn=true;
        sb=new StringBuilder();
    }

    public void gameover(){
        boolean win = (enemyNowHp<=0)? true:false;
        boolean gameOver = (userNowHp<=0)? true:false;
        sb=new StringBuilder();
        if(win||gameOver){
            Intent intent=new Intent(getApplicationContext(),BattleResult.class);
            if (win){
                sb.append(player.getName()+"は "+monster.getName()+"に\n勝利した!!");
                intent.putExtra("result","Win");

            }else if (gameOver){
                sb.append(player.getName()+"は"+monster.getName()+"に\n負けてしまった......");
                battlelog.setText(sb.toString());
                intent.putExtra("result","GameOver");
            }
            intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);

            intent.putExtra("msg",sb.toString());
            startActivity(intent);
            BattlePage.this.finish();

        }
    }
    void setHp(){
        userHpView.setText(userNowHp+"/"+userMaxHp);
        enemyHpView.setText(enemyNowHp+"/"+enemyMaxHp);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus==true) {
            Log.i("2","애니메이션 스타트");
            imageView = findViewById(R.id.enemyView);
            imageView.setBackgroundResource(R.drawable.slime_animation);

            AnimationDrawable ad = (AnimationDrawable) imageView.getBackground();
            ad.start();

            /*effect=findViewById(R.id.effect);
            effect.setBackgroundResource(R.drawable.hit_effect);
            AnimationDrawable ha = (AnimationDrawable) effect.getBackground();
            ha.start();*/
        }

    }


}
