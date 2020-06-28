package com.example.vishniya.minesweeperandroid;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Logic.InputFilterMinMax;
// todo make separate view for pre-game, game, and post-game (highscore) - this will make the view.gone stuff redundant i think

public class MainActivity extends AppCompatActivity {

    private EditText userInputField;
    private Button submitGridSizeButton;
    private TextView gridSizeQuestionText;
    private ViewGroup layout;
    private static int MIN_GRID_SIZE = 3;
    private static int MAX_GRID_SIZE =20;
    public static final String EXTRA_GRID_SIZE = "com.example.vishniya.minesweeperandroid.mainactivity.EXTRA.int";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // assign id values to each variable using findviewbyid to initialize them
        // remember to typecast always when using findviewbyid
        userInputField = findViewById(R.id.chooseGameSizeText);
        // this ensures user only enters num between 1-20 BUT HAVING difficulty:/
        //TODO    want to ensure no entry less than 3, but need them to be able to type the digits 1 and 2...
        // TODO add toast upon entering wrong number
        userInputField.setFilters(new InputFilter[]{new InputFilterMinMax("3", "20")});

        submitGridSizeButton = findViewById(R.id.submitButton);
        gridSizeQuestionText = findViewById(R.id.gridSizeQuestionText);


        // submit button
        submitGridSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override

            // start minesweepergame if submitted gridsize is acceptable
            public void onClick(View v) {

                int gridSizeNumber = Integer.parseInt(userInputField.getText().toString());
                if (gridSizeNumber >= MIN_GRID_SIZE && gridSizeNumber <= MAX_GRID_SIZE) {

              Intent intent = new Intent(MainActivity.this, MinesweeperGameActivity.class);

              intent.putExtra(EXTRA_GRID_SIZE, gridSizeNumber);

                startActivity(intent);

                }

                else {
                    Toast.makeText(MainActivity.this, "Please choose a size between"+MIN_GRID_SIZE + " and " + MAX_GRID_SIZE,Toast.LENGTH_SHORT );
                }
            }

        });

    }

}
