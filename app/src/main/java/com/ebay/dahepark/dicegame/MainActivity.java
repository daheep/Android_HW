package com.ebay.dahepark.dicegame;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ImageView> diceImgList;
    private FloatingActionButton fab;

    private View touchLayout;

    private static int DICE_VISIBLE_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dice 틀이 되는 이미지뷰 셋팅
        diceImgList = new ArrayList<ImageView>();
        diceImgList.add((ImageView) this.findViewById(R.id.dice1));
        diceImgList.add((ImageView) this.findViewById(R.id.dice2));
        diceImgList.add((ImageView) this.findViewById(R.id.dice3));

        setVisibleIV();

        // 추가 버튼 셋팅
        fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDice();
            }
        });

        // 주사위 애니메이션 추가
        touchLayout = (ConstraintLayout) this.findViewById(R.id.touchLayout);
        touchLayout.setOnClickListener(new ConstraintLayout.OnClickListener(){
            @Override
            public void onClick(View view) {
                goDiceGame();
                Toast.makeText(getApplicationContext(), "Go Game!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 주사위 게임
    private void goDiceGame(){
        for(int i = 0 ; i < DICE_VISIBLE_NUM; i++){
            // 애니메이션 효과
            ImageView iv = diceImgList.get(i);
            iv.startAnimation(getDiceAnimation());
        }
    }

    private AnimationSet getDiceAnimation(){
        // 애니메이션 효과 구현
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new AccelerateInterpolator());

        Animation scaleAnim = new ScaleAnimation(1.0f,1.2f,1.0f,1.2f);
        scaleAnim.setDuration(1800);
        Animation rotateAnim = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnim.setDuration(2000);

        set.addAnimation(scaleAnim);
        set.addAnimation(rotateAnim);

        return set;
    }

    private void setVisibleIV(){
        allInvisibleIV();

        for(int i = 0; i < DICE_VISIBLE_NUM; i++){
            diceImgList.get(i).setVisibility(View.VISIBLE);
        }
    }

    private void allInvisibleIV(){
        for(View iv : diceImgList){
            iv.setVisibility(View.INVISIBLE);
        }
    }

    // 주사위 추가하는 로직
    private void addDice(){
        // 초기화
        if(DICE_VISIBLE_NUM >= diceImgList.size()) DICE_VISIBLE_NUM = 1;
        // 추가
        else DICE_VISIBLE_NUM++;

        setVisibleIV();
    }
}
