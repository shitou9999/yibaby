package com.example.administrator.YiBaby.yb_Util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.administrator.YiBaby.R;
import com.example.administrator.YiBaby.ybRegister;
import com.example.administrator.YiBaby.yb_Login_Activity;

/**
 * Created by Administrator on 2016/3/30.
 */
public class DialogTool {
    private Context context;
    public DialogTool(Context context) {
        this.context = context;
    }
    //弹出登录每怡的对话框
    public static Dialog getLoginDialogView(final Context context,View view){
        final AlertDialog dialog=new AlertDialog.Builder(context).create();
        dialog.show();
        Window window=dialog.getWindow();
        window.setContentView(R.layout.custom_dialog_view_net);
        LinearLayout liLogin= (LinearLayout) window.findViewById(R.id.liLogin);
        LinearLayout liRegister= (LinearLayout) window.findViewById(R.id.liRegister);
        LinearLayout liCancel= (LinearLayout) window.findViewById(R.id.liCancel);
        liLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,yb_Login_Activity.class);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        liRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ybRegister.class);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        liCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
