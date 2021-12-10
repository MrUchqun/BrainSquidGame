package com.b12.game.fragments;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.R;
import com.b12.game.SplashActivity;
import com.b12.game.adapters.FirstGameAdapter;
import com.b12.game.adapters.FirstGameItemCountAdapter;
import com.b12.game.getset.FirstGameItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentFirstGameAssignment extends Fragment implements FirstGameItemCountAdapter.OnAnswerClickListener {
    private FirstGameAdapter firstGameAdapter;
    private FirstGameItemCountAdapter firstGameItemCountAdapter;
    private ArrayList<Integer> gameItems;
    private ArrayList<Integer> tempItems;
    private ArrayList<FirstGameItem> answersList;
    private RecyclerView recyclerViewImages, recyclerViewCount;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private TextView levelTxt, circler_progress_txt;
    private ImageView imageViewInCard;
    private ProgressBar progressBarHorizontal;
    private CardView cardView;
    private final Random rnd = new Random();
    int counter = 0, itemCount = 0;
    int countRandom, randomItem;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_game_assignment, container, false);
        SplashActivity splashActivity = new SplashActivity();
        splashActivity.changeStatusBarColor(getActivity());
        answersList = new ArrayList<>();
        tempItems = new ArrayList<>();
        gameItems = new ArrayList<>();
        tempItems.add(R.drawable.img_1);
        tempItems.add(R.drawable.img_2);
        tempItems.add(R.drawable.img_3);
        tempItems.add(R.drawable.img_4);
        cardView = view.findViewById(R.id.first_game_assignment_image_view_card);
        circler_progress_txt = view.findViewById(R.id.progress_circular_text);
        progressBarHorizontal = view.findViewById(R.id.horizontal_progress_bar);
        recyclerViewImages = view.findViewById(R.id.first_game_recycler);
        recyclerViewCount = view.findViewById(R.id.first_game_item_count_recycler);
        imageViewInCard = view.findViewById(R.id.first_game_card_image);
        levelTxt = view.findViewById(R.id.first_game_level_txt);
        progressBar = view.findViewById(R.id.progress_circular);
        relativeLayout = view.findViewById(R.id.first_game_answer_relative_layout);
        progressBarHorizontal.setVisibility(View.GONE);
        imageViewInCard.setVisibility(View.GONE);
//        cardView.setCardBackgroundColor(Color.WHITE);

        countDownTimer();


        generateImage();

        return view;
    }


    private void generateImage() {
        for (int i = 0; i < 11; i++) {
            int value = rnd.nextInt(4);
            gameItems.add(tempItems.get(value));

        }
        recyclerViewImages.setHasFixedSize(true);
        recyclerViewImages.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerViewImages.setItemAnimator(null);
        firstGameAdapter = new FirstGameAdapter(gameItems);
        recyclerViewImages.setAdapter(firstGameAdapter);


    }

    private void countDownTimer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                if (counter <= 10) {
                    circler_progress_txt.setText("" + counter);
                    progressBar.setProgress(counter);
                    counter++;
                    handler.postDelayed(this::run, 1000);
                } else {
                    handler.removeCallbacks(this::run);
                    progressBar.setVisibility(View.GONE);
                    recyclerViewImages.setVisibility(View.GONE);
                    circler_progress_txt.setVisibility(View.GONE);
                    progressBarHorizontal.setVisibility(View.VISIBLE);
                    imageViewInCard.setVisibility(View.VISIBLE);
                    cardView.setCardBackgroundColor(R.drawable.icons_background);
                    recyclerViewCount.setVisibility(View.VISIBLE);

                    progressBarHorizontalTimer();
                    getRandomImageFromArray();
                }
            }
        }, 100);
    }


    private void progressBarHorizontalTimer() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBarHorizontal.setProgress(counter);
                if (counter == 100) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 0, 100);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getRandomImageFromArray() {

        randomItem = gameItems.get(0);
        for (int i = 0; i < gameItems.size(); i++) {
            if (gameItems.get(i).equals(randomItem)) {
                itemCount = itemCount + 1;
            }
        }
        answersList.add(new FirstGameItem(itemCount));
//        answersList.add(new FirstGameItem(1));
//        answersList.add(new FirstGameItem(2));
//        answersList.add(new FirstGameItem(3));
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        for (int i = 1; i < 10; i++) {
//            list.add(i);
//        }
//        Collections.shuffle(list);
//        for (int i = 0; i < 3; i++) {
//            if (list.get(i).equals(itemCount)) {
//                list.get(i + 1);
//            }
//            answersList.add(new FirstGameItem(list.get(i)));
//        }


        imageViewInCard.setImageResource(randomItem);

        relativeLayout.setVisibility(View.VISIBLE);
        recyclerViewCount.setHasFixedSize(true);
        recyclerViewCount.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewCount.setItemAnimator(null);
        firstGameItemCountAdapter = new FirstGameItemCountAdapter(answersList, this);
        recyclerViewCount.setAdapter(firstGameItemCountAdapter);


    }

    @Override
    public void onAnswerClicked(int answer) {
        checkAnswer(answer);
    }

    private void checkAnswer(int answer) {
        if (answer == itemCount) {
            imageViewInCard.setImageResource(R.drawable.succesfull_img);
            playSound();
            nextLevel();
        }
    }

    private void nextLevel() {

    }

    private void playSound() {
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.succesfully_sound);
        mp.start();
    }
}
