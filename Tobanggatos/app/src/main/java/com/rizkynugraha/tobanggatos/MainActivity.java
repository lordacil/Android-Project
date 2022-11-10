package com.rizkynugraha.tobanggatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView,textView2;
    Button btn,btn2,btnUbah,btnUbah2;
    String edit,test;
    TextView notif,notif2;
    //String[] arrOfStr;
    ImageView imageView,imageView2;
    Boolean activeNotif = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnSalin);
        btn2 = findViewById(R.id.btnSalin2);
        btnUbah = findViewById(R.id.btn);
        btnUbah2 = findViewById(R.id.btn2);
        editText = findViewById(R.id.et);
        textView = findViewById(R.id.tv);
        textView2 = findViewById(R.id.tv2);
        notif = findViewById(R.id.notify);
        notif2 = findViewById(R.id.notify2);
        imageView = findViewById(R.id.swaptos);
        imageView2 = findViewById(R.id.swaptos2);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                textView.setText("");
               // textView2.setText("")
            }
        });
        if (activeNotif) {
            notif.setVisibility(View.VISIBLE);
            notif.startAnimation(fadeIn2);
            notif.startAnimation(fadeOut2);
            fadeIn2.setDuration(1200);
            fadeIn2.setFillAfter(true);
            fadeOut2.setDuration(1200);
            fadeOut2.setFillAfter(true);
            fadeOut2.setStartOffset(1000+fadeIn2.getStartOffset());
            activeNotif = false;
        }

    }

    public void setClipboard(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("EditText", textView.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(MainActivity.this, "Tersalin",Toast.LENGTH_SHORT).show();
    }

    public void setClipboard2(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("EditText", textView2.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(MainActivity.this, "Tersalintos",Toast.LENGTH_SHORT).show();
    }

    public void displayEditText(View view){
        if (textView.getText().toString().equals("")){
            edit =  editText.getText().toString();
            textView.setText(edit.replaceAll("([^\\s])(\\s|$)", "$1tos$2"));
            btn.setVisibility(View.VISIBLE);
            btnUbah.setEnabled(false);
            btnUbah.setBackground(getDrawable(R.drawable.btn_nonactive));
        }else{
            editText.setText("");
            textView.setText("");
        }
    }


    public void displayEditText2(View view){
        if (textView2.getText().toString().equals("")){
            edit =  editText.getText().toString();
            String[] arrOfStr = edit.split(" ");
            String result_string = "";
            for(String s : arrOfStr)
            {
                result_string += s.substring(0, s.length() -3) + " ";
            }
            //textView2.setText(arrOfStr[1]);
            textView2.setText(result_string);
            //textView2.setText(removeFirst(edit));
            btn2.setVisibility(View.VISIBLE);
            editText.setText("");
//            btnUbah2.setEnabled(false);
//            btnUbah2.setBackground(getDrawable(R.drawable.btn_nonactive));
        }else{
            editText.setText("");
            textView2.setText("");
        }
    }

    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f,0.0f);
    protected AlphaAnimation fadeIn2 = new AlphaAnimation(0.0f, 1.0f);
    protected AlphaAnimation fadeOut2 = new AlphaAnimation(1.0f,0.0f);

    public void swapTos(View view){
        btn.setVisibility(View.GONE);
        btnUbah.setVisibility(View.GONE);
        btnUbah2.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
        textView2.setVisibility(View.VISIBLE);
        textView2.setText("");
        editText.clearFocus();
        imageView2.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        notif.setVisibility(View.VISIBLE);
        notif.startAnimation(fadeIn2);
        notif.startAnimation(fadeOut2);
        fadeIn2.setDuration(1200);
        fadeIn2.setFillAfter(true);
        fadeOut2.setDuration(1200);
        fadeOut2.setFillAfter(true);
        fadeOut2.setStartOffset(1000+fadeIn2.getStartOffset());

//        notif.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                notif.setVisibility(View.VISIBLE);
//            }
//        }, 3000);
        //Toast.makeText(MainActivity.this, "Bahasa Indo -> Bahasatos Purbatos",Toast.LENGTH_SHORT).show();
    }


    public void swapTos2(View view){
        btn2.setVisibility(View.GONE);
        btnUbah.setVisibility(View.VISIBLE);
        btnUbah2.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.GONE);
        textView.setText("");
        editText.clearFocus();
        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.GONE);
        notif.setVisibility(View.GONE);
        notif2.setVisibility(View.VISIBLE);
        notif2.startAnimation(fadeIn);
        notif2.startAnimation(fadeOut);
        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(1200);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(1000+fadeIn.getStartOffset());

        }
//        notif2.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                notif2.setVisibility(View.VISIBLE);
//            }
//        }, 3000);
        //Toast.makeText(MainActivity.this, "Bahasatos Purbatos -> Bahasa Indo",Toast.LENGTH_SHORT).show();

}