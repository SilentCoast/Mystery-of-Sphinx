package com.template;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView txtMoney;
    TextView txtBet;
    boolean isRecyclerView1StopScrolling = true;
    boolean isRecyclerView2StopScrolling = true;
    boolean isRecyclerView3StopScrolling = true;
    String TAG = "MyDebug";
    AsyncTask<Void,Void,Void> MyAsyncTask;
    int offset = -60;
    int bonusMoney = 500;
    public void GiveMoney(){
        int betAmount =Integer.valueOf(txtBet.getText().toString());
        Random rand = new Random();
        int randomMoney = rand.nextInt(((betAmount*2) - 1) + 1);
        int moneyAfter =Integer.valueOf(txtMoney.getText().toString());
        txtMoney.setText(String.valueOf(moneyAfter + randomMoney));
        if(betAmount<=randomMoney){
            Toast.makeText(getApplicationContext(),"You win " + (randomMoney-betAmount) +" " + new String(Character.toChars(0x1F603)),Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"You lost "+(betAmount-randomMoney)+" " + new String(Character.toChars(0x1F612)),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        txtMoney = findViewById(R.id.txtMoney);
        txtBet   = findViewById(R.id.txtBet);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(getResources().getDrawable(R.drawable.ico_1)));
        items.add(new Item(getResources().getDrawable(R.drawable.ico_2)));
        items.add(new Item(getResources().getDrawable(R.drawable.ico_3)));
        items.add(new Item(getResources().getDrawable(R.drawable.ico_4)));
        items.add(new Item(getResources().getDrawable(R.drawable.ico_5)));
        items.add(new Item(getResources().getDrawable(R.drawable.ico_6)));
        items.add(new Item(getResources().getDrawable(R.drawable.ico_7)));
        items.add(new Item(getResources().getDrawable(R.drawable.ico_8)));

        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ItemsRecyclerAdapter adapter = new ItemsRecyclerAdapter(this,items);
        recyclerView1.setAdapter(adapter);
        recyclerView1.getLayoutManager().scrollToPosition(Integer.MAX_VALUE/2+150);

        RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ItemsRecyclerAdapter adapter2 = new ItemsRecyclerAdapter(this,items);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.getLayoutManager().scrollToPosition(Integer.MAX_VALUE/2+50);

        RecyclerView recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        ItemsRecyclerAdapter adapter3 = new ItemsRecyclerAdapter(this,items);
        recyclerView3.setAdapter(adapter3);
        recyclerView3.getLayoutManager().scrollToPosition(Integer.MAX_VALUE/2-100);

        ImageButton btnSpin = findViewById(R.id.btnSpin);
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(isRecyclerView1StopScrolling && isRecyclerView2StopScrolling && isRecyclerView3StopScrolling)){
                    if(recyclerView1.getScrollState()==RecyclerView.SCROLL_STATE_IDLE&&
                            recyclerView2.getScrollState()==RecyclerView.SCROLL_STATE_IDLE&&
                            recyclerView3.getScrollState()==RecyclerView.SCROLL_STATE_IDLE){

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Please wait till slots stop scrolling",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                int money =Integer.valueOf(txtMoney.getText().toString());
                int betAmount =Integer.valueOf(txtBet.getText().toString());
                if(money>=betAmount){
                    txtMoney.setText(String.valueOf(money - betAmount));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Not enough money",Toast.LENGTH_SHORT).show();
                    return;
                }

                isRecyclerView1StopScrolling =false;
                isRecyclerView2StopScrolling =false;
                isRecyclerView3StopScrolling =false;

                int randomInt =   new Random().nextInt((Integer.MAX_VALUE/2+100) - (Integer.MAX_VALUE/2-100)) + (Integer.MAX_VALUE/2-100);
                int randomInt2 =  new Random().nextInt((Integer.MAX_VALUE/2+100) - (Integer.MAX_VALUE/2-100)) + (Integer.MAX_VALUE/2-100);
                int randomInt3 =  new Random().nextInt((Integer.MAX_VALUE/2+100) - (Integer.MAX_VALUE/2-100)) + (Integer.MAX_VALUE/2-100);
                recyclerView1.smoothScrollToPosition(randomInt);
                recyclerView2.smoothScrollToPosition(randomInt2);
                recyclerView3.smoothScrollToPosition(randomInt3);



                recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState==RecyclerView.SCROLL_STATE_IDLE){
                            isRecyclerView1StopScrolling = true;
                            LinearLayoutManager llr = (LinearLayoutManager)recyclerView1.getLayoutManager();
                            llr.scrollToPositionWithOffset(((LinearLayoutManager) recyclerView1.getLayoutManager()).findFirstVisibleItemPosition()
                                    ,offset);
                        }
                    }
                });
                recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState==RecyclerView.SCROLL_STATE_IDLE){
                            isRecyclerView2StopScrolling = true;
                            LinearLayoutManager llr2 = (LinearLayoutManager)recyclerView2.getLayoutManager();
                            llr2.scrollToPositionWithOffset(((LinearLayoutManager) recyclerView2.getLayoutManager()).findFirstVisibleItemPosition()
                                    ,offset);
                        }
                    }
                });
                recyclerView3.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState==RecyclerView.SCROLL_STATE_IDLE){
                            isRecyclerView3StopScrolling = true;
                            LinearLayoutManager llr3 = (LinearLayoutManager)recyclerView3.getLayoutManager();
                            llr3.scrollToPositionWithOffset(((LinearLayoutManager) recyclerView3.getLayoutManager()).findFirstVisibleItemPosition()
                                    ,offset);
                        }
                    }
                });

                MyAsyncTask = new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... voids) {

                        while (!isCancelled()){
                            if((isRecyclerView1StopScrolling && isRecyclerView2StopScrolling && isRecyclerView3StopScrolling)){
                                break;
                            }
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);
                        GiveMoney();
                        int moneyAfter =Integer.valueOf(txtMoney.getText().toString());
                        if(moneyAfter<10){
                            CustomDialogLose customDialogLose = new CustomDialogLose(GameActivity.this);
                            customDialogLose.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            customDialogLose.setClicklistener(new CustomDialogClass.ClickListener() {
                                @Override
                                public void onClick(View view) {
                                    txtMoney.setText(String.valueOf(250));
                                    customDialogLose.dismiss();
                                }
                            });
                            customDialogLose.show();
                        }

                    }
                }.execute();
            }
        });

        ImageButton btnBetPlus = findViewById(R.id.btnBetPlus);
        btnBetPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int g =Integer.valueOf(txtBet.getText().toString());
                txtBet.setText(String.valueOf( g + 10));
            }
        });

        ImageButton btnBetMinus = findViewById(R.id.btnBetMinus);
        btnBetMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int g =Integer.valueOf(txtBet.getText().toString());
                if(g>10){
                txtBet.setText(String.valueOf( g - 10));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Mimimum bet is 10", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton btnGoBack = findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton btnBonus = findViewById(R.id.btnBonus);
        btnBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogClass customDialogClass = new CustomDialogClass(GameActivity.this);
                customDialogClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialogClass.setClicklistener(new CustomDialogClass.ClickListener() {
                    @Override
                    public void onClick(View view) {
                        int moneyAfter =Integer.valueOf(txtMoney.getText().toString());
                        txtMoney.setText(String.valueOf(moneyAfter + bonusMoney));
                        customDialogClass.dismiss();
                    }
                });
                customDialogClass.show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(MyAsyncTask!=null){
            MyAsyncTask.cancel(true);
        }
    }
}
