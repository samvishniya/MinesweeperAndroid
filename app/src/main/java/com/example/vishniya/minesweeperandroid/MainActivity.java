package com.example.vishniya.minesweeperandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // int size = getUserInputGridSize();
 //       populateButtons(6);


    }

    private int getUserInputGridSize(){

       EditText userInputField = findViewById(R.id.chooseGameSizeText);

       Editable gridSize = userInputField.getText();

       int gridSizeString = Integer.valueOf(gridSize.toString());

       return gridSizeString;
    }


    private void populateButtons(int size) {

        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

        for (int rowNum = 0; rowNum <= size - 1; rowNum++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for (int columnNum = 0; columnNum <= size - 1; columnNum++) {
                ImageButton imageButton = new ImageButton(this);


                imageButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                tableRow.addView(imageButton);
            }
        }

    }


}
