package com.example.administrator.YiBaby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class YiBaby_Login_Activity_NoNeed extends AppCompatActivity {
    private ImageView returns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_baby__login_no_need);
        returns=(ImageView)super.findViewById(R.id.returns);
        initLisener();
    }
    private void initLisener(){
        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YiBaby_Login_Activity_NoNeed.this.finish();
                overridePendingTransition(R.anim.change_activity_in, R.anim.change_activity_out);
            }
        });
    }
}
