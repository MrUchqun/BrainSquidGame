package com.b12.game.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.b12.game.R;
import com.b12.game.SplashActivity;
import com.view.circulartimerview.CircularTimerListener;
import com.view.circulartimerview.CircularTimerView;
import com.view.circulartimerview.TimeFormatEnum;

import java.util.Random;

public class FragmentFirstGameAssignment extends Fragment {

    private CircularTimerView progressBar;
    private RelativeLayout relativeLayout;
    private TextView levelTxt;
    private ImageView imageViewInCard;
    private final Random rnd = new Random();
    private ImageView img_1, img_2, img_3, img_4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_game_assignment, container, false);
        SplashActivity splashActivity = new SplashActivity();
        splashActivity.changeStatusBarColor(getActivity());
        levelTxt = view.findViewById(R.id.first_game_level_txt);
        progressBar = view.findViewById(R.id.progress_circular);
        relativeLayout = view.findViewById(R.id.first_game_answer_relative_layout);
        progressBar.setProgress(0);
        countDownTimer();
        generateImage(view);


        return view;
    }

    private void generateImage(View view) {
        ImageView[] images = new ImageView[]{img_1, img_2, img_3, img_4};
        int[] res = new int[]{R.id.image_1, R.id.image_2, R.id.image_3, R.id.image_4,};
        int[] drawables = new int[]{R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,};
        for (int i = 0; i < images.length; i++) {
            images[i] = (ImageView) view.findViewById(res[i]);
            images[i].setImageResource(drawables[i]);
        }
    }


    private void countDownTimer() {
        progressBar.setCircularTimerListener(new CircularTimerListener() {
            @Override
            public String updateDataOnTick(long remainingTimeInMs) {

                return String.valueOf((int) Math.ceil((remainingTimeInMs / 1000.f)));
            }

            @Override
            public void onTimerFinished() {
                Toast.makeText(getContext(), "FINISHED", Toast.LENGTH_SHORT).show();

                progressBar.setText("FINISHED THANKS!");
                progressBar.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);

            }
        }, 5, TimeFormatEnum.SECONDS, 5);


// To start timer
        progressBar.startTimer();
    }
}
