package com.lastwave.app;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.Activity;

/** Launcher screen for AURA Flash. */
public class MainActivity extends Activity {

    private TextView status;
    private TextView track;
    private boolean playing = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int pad = dp(24);
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.TOP);
        content.setPadding(pad, dp(40), pad, pad);
        content.setBackgroundColor(getResources().getColor(R.color.aura_background));

        TextView title = text("AURA Flash", 30, R.color.aura_secondary, true);
        TextView subtitle = text("On-device player shell", 15, R.color.aura_primary, false);
        subtitle.setPadding(0, dp(4), 0, dp(24));

        status = text("Choose a section", 16, R.color.aura_primary_dark, false);
        track = text("Now playing: nothing selected", 16, android.R.color.white, false);
        track.setPadding(0, dp(12), 0, dp(12));

        LinearLayout libraryRow = row();
        libraryRow.addView(button("Songs", v -> status.setText("Songs selected")));
        libraryRow.addView(button("Albums", v -> status.setText("Albums selected")));
        libraryRow.addView(button("Playlists", v -> status.setText("Playlists selected")));

        LinearLayout playerRow = row();
        Button play = button("Play / Pause", v -> {
            playing = !playing;
            track.setText(playing ? "Now playing: demo track" : "Now playing: paused");
        });
        Button next = button("Next", v -> track.setText("Now playing: next demo track"));
        playerRow.addView(play);
        playerRow.addView(next);

        content.addView(title);
        content.addView(subtitle);
        content.addView(status);
        content.addView(header("Library"));
        content.addView(libraryRow);
        content.addView(header("Player"));
        content.addView(track);
        content.addView(playerRow);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(getResources().getColor(R.color.aura_background));
        scroll.addView(content, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(scroll);
    }

    private LinearLayout row() {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        return row;
    }

    private TextView header(String value) {
        TextView h = text(value, 20, R.color.aura_secondary, true);
        h.setPadding(0, dp(24), 0, dp(8));
        return h;
    }

    private TextView text(String value, int sizeSp, int colorRes, boolean bold) {
        TextView tv = new TextView(this);
        tv.setText(value);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
        tv.setTextColor(getResources().getColor(colorRes));
        if (bold) {
            tv.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return tv;
    }

    private Button button(String label, android.view.View.OnClickListener listener) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextColor(Color.WHITE);
        b.setBackgroundColor(getResources().getColor(R.color.aura_primary_dark));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, dp(4), dp(12), dp(4));
        b.setLayoutParams(lp);
        b.setOnClickListener(listener);
        return b;
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }
}
