package com.template;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    ClickListener mClickListener;
    public Activity activity;
    public ImageButton btnClaim;

    public CustomDialogClass(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_bonus);
        btnClaim = findViewById(R.id.btnClaimBonus);

        btnClaim.setOnClickListener(this);

    }

    public void setClicklistener(CustomDialogClass.ClickListener clickListener){
        this.mClickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnClaimBonus) {
            if(mClickListener!=null) mClickListener.onClick(v);
        }

    }
    public interface ClickListener{
        void onClick(View view);
    }
}
