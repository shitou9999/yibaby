package com.example.administrator.YiBaby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class yb_Energy_Exchange extends AppCompatActivity {
    private ImageView returns;
    private RadioGroup rGoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__energy__exchange);
        returns=(ImageView)super.findViewById(R.id.returns);
        rGoup=(RadioGroup)super.findViewById(R.id.rGoup);
        rGoup.check(R.id.bt1);
        initReturnsListener();
    }
    private void initReturnsListener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yb_Energy_Exchange.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
}
