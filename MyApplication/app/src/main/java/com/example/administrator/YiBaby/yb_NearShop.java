package com.example.administrator.YiBaby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class yb_NearShop extends AppCompatActivity {
    private ImageView returns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__near_shop);
        returns=(ImageView)super.findViewById(R.id.returns);
        initReturnsListener();
    }
    private void initReturnsListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_NearShop.this.finish();
                overridePendingTransition(R.anim.change_activity_in,R.anim.change_activity_out);
            }
        });
    }
}
