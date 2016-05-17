package com.example.administrator.YiBaby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class yb_Mum_Circle extends AppCompatActivity {
    private ImageView returns;
    private RelativeLayout rlAllClub;
    private RelativeLayout rlNearClub;
    private RelativeLayout rlNearShop;
    private RelativeLayout energy_exchange;
    private RelativeLayout BabyGrow;
    private RelativeLayout BabyWall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__mum__circle);
        returns=(ImageView)super.findViewById(R.id.returns);
        rlAllClub=(RelativeLayout)super.findViewById(R.id.rlAllClub);
        rlNearClub=(RelativeLayout)super.findViewById(R.id.rlNearClub);
        rlNearShop=(RelativeLayout)super.findViewById(R.id.rlNearShop);
        energy_exchange=(RelativeLayout)super.findViewById(R.id.energy_exchange);
        BabyGrow=(RelativeLayout)super.findViewById(R.id.BabyGrow);
        BabyWall=(RelativeLayout)super.findViewById(R.id.BabyWall);
        initViewListener();
    }
    private void initViewListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_Mum_Circle.this.finish();
                overridePendingTransition(R.anim.change_activity_in,R.anim.change_activity_out);
            }
        });
        rlAllClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Mum_Circle.this,yb_BBS_FragmentManager.class);
                startActivity(intent);
            }
        });
        rlNearClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Mum_Circle.this,yb_BBS_FragmentManager.class);
                startActivity(intent);
            }
        });
        rlNearShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Mum_Circle.this,yb_NearShop.class);
                startActivity(intent);
            }
        });
        energy_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Mum_Circle.this,yb_Energy_Exchange.class);
                startActivity(intent);
            }
        });
        BabyGrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Mum_Circle.this,yb_BabyGrown.class);
                startActivity(intent);
            }
        });
        BabyWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(yb_Mum_Circle.this,yb_BabyWall.class);
                startActivity(intent);
            }
        });
    }
}
