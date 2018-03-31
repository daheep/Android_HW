package com.ebay.dahepark.dicegame;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<View> diceImgList;

    FloatingActionButton fab;
    private static int DICE_VISIBLE_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dice 틀이 되는 이미지뷰 셋팅
        diceImgList = new ArrayList<View>();
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
