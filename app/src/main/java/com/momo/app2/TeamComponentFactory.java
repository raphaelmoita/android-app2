package com.momo.app2;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.momo.app2.domain.Team;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static java.lang.String.valueOf;

public class TeamComponentFactory {

    private Context context;

    public TeamComponentFactory(Context context) {
        this.context = context;
    }

    public List<RelativeLayout> create(List<Team> teams) {
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WRAP_CONTENT, 150);

        final List<RelativeLayout> container = new ArrayList<>();

        for (Team team : teams) {
            final RelativeLayout teamContainer = new RelativeLayout(context);
            teamContainer.setLayoutParams(params);

            ImageView ball = getBall();
            TextView player1 = createPlayer(valueOf(team.getPlayer1()), ball.getId());
            TextView splitter = createSplitter("&", player1.getId());
            TextView player2 = createPlayer(valueOf(team.getPlayer2()), splitter.getId());

            teamContainer.addView(ball);
            teamContainer.addView(player1);
            teamContainer.addView(splitter);
            teamContainer.addView(player2);

            container.add(teamContainer);
        }
        return container;
    }

    public RelativeLayout create(String sparePlayer) {
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WRAP_CONTENT, 150);

        final RelativeLayout container = new RelativeLayout(context);
        container.setLayoutParams(params);

        ImageView ball = getRedBall();
        TextView player1 = createPlayer(valueOf(sparePlayer), ball.getId());

        container.addView(ball);
        container.addView(player1);

        return container;
    }

    private ImageView getBall() {
        return createBall(R.drawable.soccer_ball);
    }

    private ImageView getRedBall() {
        return createBall(R.drawable.soccer_red_ball);
    }

    private ImageView createBall(@DrawableRes int resId) {
        final RelativeLayout.LayoutParams ballLayoutParams =
                new RelativeLayout.LayoutParams(150, 150);

        int id = View.generateViewId();
        Log.i("createBall", "Ball id: {}" + id);
        ImageView imageView = new ImageView(context);
        imageView.setId(id);
        imageView.setLayoutParams(ballLayoutParams);
        imageView.setImageResource(resId);

        return imageView;
    }

    private TextView createPlayer(String player, int rightOf) {
        final TextView textView = createTextView(rightOf);

        textView.setText(player);
        textView.setTextSize(20);

        return textView;
    }

    private TextView createSplitter(String splliter, int rightOf) {
        final TextView textView = createTextView(rightOf);

        textView.setText(splliter);
        textView.setTextSize(15);
        textView.setTextColor(Color.GRAY);

        return textView;
    }

    private TextView createTextView(int rightOf) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        params.addRule(RelativeLayout.RIGHT_OF, rightOf);
        params.leftMargin = 25;

        int id = View.generateViewId();
        Log.i("createTextView", "Text id: {}" + id);

        final TextView textView = new TextView(context);
        textView.setId(id);
        textView.setTextColor(Color.BLACK);

        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_VERTICAL);

        return textView;
    }
}
