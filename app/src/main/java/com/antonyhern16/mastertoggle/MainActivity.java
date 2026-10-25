package com.antonyhern16.mastertoggle;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
    private SharedPreferences prefs;
    private boolean allOn;
    private TextView status;
    private Button masterButton;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("master_toggle", MODE_PRIVATE);
        allOn = prefs.getBoolean("all_on", false);
        buildUi();
        updateUi();
    }

    private void buildUi() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(28, 32, 28, 32);
        root.setBackgroundColor(Color.rgb(15, 20, 32));

        TextView title = new TextView(this);
        title.setText("ANTONYHERN16");
        title.setTextColor(Color.WHITE);
        title.setTextSize(25);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1, -2));

        TextView subtitle = new TextView(this);
        subtitle.setText("MASTER SETTINGS");
        subtitle.setTextColor(Color.rgb(155, 177, 210));
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(-1, -2);
        sp.topMargin = 8;
        root.addView(subtitle, sp);

        status = new TextView(this);
        status.setTextSize(20);
        status.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        status.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams stp = new LinearLayout.LayoutParams(-1, -2);
        stp.topMargin = 44;
        root.addView(status, stp);

        masterButton = new Button(this);
        masterButton.setTextSize(18);
        masterButton.setAllCaps(false);
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, 72);
        bp.topMargin = 20;
        root.addView(masterButton, bp);
        masterButton.setOnClickListener(v -> {
            allOn = !allOn;
            prefs.edit().putBoolean("all_on", allOn).apply();
            updateUi();
        });

        TextView info = new TextView(this);
        info.setText("This master switch toggles this app's saved profile state. Android system settings and COD Mobile settings are not changed automatically; restricted settings require supported APIs and user permission.");
        info.setTextColor(Color.rgb(190, 199, 216));
        info.setTextSize(14);
        info.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(-1, -2);
        ip.topMargin = 28;
        root.addView(info, ip);

        TextView footer = new TextView(this);
        footer.setText("POCO X7 Pro • CODM helper");
        footer.setTextColor(Color.rgb(120, 140, 170));
        footer.setTextSize(12);
        footer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams fp = new LinearLayout.LayoutParams(-1, -2);
        fp.topMargin = 40;
        root.addView(footer, fp);

        setContentView(root);
    }

    private void updateUi() {
        if (allOn) {
            status.setText("STATUS: ALL ON");
            status.setTextColor(Color.rgb(90, 230, 150));
            masterButton.setText("TURN ALL OFF");
            masterButton.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(190, 55, 65)));
            masterButton.setTextColor(Color.WHITE);
        } else {
            status.setText("STATUS: ALL OFF");
            status.setTextColor(Color.rgb(255, 175, 95));
            masterButton.setText("TURN ALL ON");
            masterButton.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(25, 155, 100)));
            masterButton.setTextColor(Color.WHITE);
        }
    }
}
