package com.godsamix.cryptopricewidget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t3 = (TextView) findViewById(R.id.text);
        t3.setText(
                Html.fromHtml(
                        "Made by " +
                                "<a href='https://github.com/SamixDev'>Samer</a>" +
                                " for testing purposes =)"));
        t3.setMovementMethod(LinkMovementMethod.getInstance());

    }
}