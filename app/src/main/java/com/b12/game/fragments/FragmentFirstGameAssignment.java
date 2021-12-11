package com.b12.game.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.LevelCompeletActivity;
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
    private TextView levelTxt, circler_progress_txt, levelInLevel;
    private ImageView imageViewInCard;
    private ProgressBar progressBarHorizontal;
    private CardView cardView;
    private final Random rnd = new Random();
    private int counter = 0, itemCount = 0;
    private int levelCount, randomItem;
    private final Handler handler = new Handler();
    private String levelTxtBundle;
    private SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_game_assignment, container, false);
        SplashActivity splashActivity = new SplashActivity();
        splashActivity.changeStatusBarColor(getActivity());
        sharedPreferences = getActivity().getSharedPreferences("LEVELSNUMBER", MODE_PRIVATE);
        levelTxtBundle = sharedPreferences.getString("levelnum", "");
        levelCount = sharedPreferences.getInt("levelCount", 0);
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
        levelInLevel = view.findViewById(R.id.first_game_level_in_level_txt);
        progressBar = view.findViewById(R.id.progress_circular);
        relativeLayout = view.findViewById(R.id.first_game_answer_relative_layout);
        levelTxt.setText("Level " + levelTxtBundle);
        levelInLevel.setText(levelCount + "/" + levelTxtBundle);
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
        Random rnd = new Random();
        answersList.add(new FirstGameItem(itemCount));
        while (answersList.size() < 4) {
            int random = rnd.nextInt(5) + 1;
            if (!answersList.contains(new FirstGameItem(random)))
                answersList.add(new FirstGameItem(random));
        }
        Collections.shuffle(answersList);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            imageViewInCard.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icons_background));
        } else {
            imageViewInCard.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.icons_background));
        }
        imageViewInCard.setImageResource(randomItem);

        relativeLayout.setVisibility(View.VISIBLE);
        recyclerViewCount.setHasFixedSize(true);
        recyclerViewCount.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewCount.setItemAnimator(null);
        firstGameItemCountAdapter = new FirstGameItemCountAdapter(answersList, this);
        recyclerViewCount.setAdapter(firstGameItemCountAdapter);


    }

    @Override
    public void onAnswerClicked(int answer, LinearLayout layout) {
        checkAnswer(answer, layout);
    }

    @SuppressLint("ResourceAsColor")
    private void checkAnswer(int answer, LinearLayout layout) {
        if (answer == itemCount) {
            layout.setBackgroundColor(Color.parseColor("#4ECB71"));
            imageViewInCard.setImageResource(R.drawable.succesfull_img);
            playSuccesSound();
            nextLevel();
        } else {
            layout.setBackgroundColor(Color.parseColor("#F24E1E"));
            imageViewInCard.setImageResource(R.drawable.wrong_img);
            playWrongSound();
            nextLevel();
        }
    }

    private void playWrongSound() {
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.wrong_sound);
        mp.start();
    }

    private void nextLevel() {
        if (levelCount < Integer.parseInt(levelTxtBundle)) {
            SharedPreferences.Editor editorLevelNumber = getActivity().getSharedPreferences("LEVELSNUMBER", MODE_PRIVATE).edit();
            editorLevelNumber.putInt("levelCount", levelCount + 1);
            editorLevelNumber.apply();
            Fragment someFragment = new FragmentFirstGameAssignment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.game1_linear1, someFragment);
            transaction.commit();
        } else {
            Intent intent = new Intent(getContext(), LevelCompeletActivity.class);
            startActivity(intent);
        }


    }

    private void playSuccesSound() {
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.succesfully_sound);
        mp.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        handler.removeCallbacksAndMessages(null);
    }

}
