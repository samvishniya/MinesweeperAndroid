package Logic;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Minefield {

    private int mineCreationCount;
    private int totalMinesNeeded;

    private Minecell[][] fieldArray;
    private List<Point> listOfMines;
    private int dimension;
    private Context context;


    // TODO ADD DIFFICULTY AS A PARAMETER AND CODE IT
    public Minefield(int dimension, Context context){
        this.context=context;
        this.dimension=dimension;
        totalMinesNeeded=dimension;
        fieldArray = new Minecell[dimension][dimension];
        listOfMines = new ArrayList();

        createMinefield();

      while(mineCreationCount < totalMinesNeeded) {
          generateMine();
        }
    }

    // creation of 2d array of minecells
    // using a table

    public Minecell getCellContent(int row, int column){

        return fieldArray[row][column];

    }


    private void createMinefield(){

        // creates non-mined minecells and tells them their location in the grid (Point)


        for(int y=0; y<=dimension-1;y++){

            for(int x = 0;x<=dimension-1;x++) {

              fieldArray[x][y]= new Minecell(new Point(x,y),context);

            }
        }
    }

    // generates 1 mine at a random non-mine location
    private void generateMine(){


        int randomNum1 = (int) (Math.random()*dimension);
        int randomNum2 = (int) (Math.random()*dimension);
      //  System.out.println(randomNum1);

        Point mineCoord = new Point ( randomNum1, randomNum2);

        // now flag this randomcell as having a mine in the array
        // also add it to a seperate list of MINEcells for easy reference


        Minecell currentMine = fieldArray[randomNum1][randomNum2];

        if (!currentMine.isHasMine()) {
            currentMine.setHasMine(true);
            listOfMines.add(mineCoord);
            mineCreationCount++;
          //  System.out.println("mine generated at " + "[" + randomNum1 + "]" + "[" + randomNum2 + "]");


        // this loop checks for adjacent cells and ++s their mineadjacencycounter

        for(int y=-1; y<=1; y++) {
            for (int x = -1; x<=1; x++) {
                //System.out.println("check" + x + "," + y);
                // skip check current cell
                if (y == 0 && x == 0) {
                    continue;
                }
                // check for >=1 , ensures that were staying inside bounds of minefield, since mine could be topleft,
                // and we could be trying to look to the left of it
                if (randomNum1 + x >= 0 && randomNum1 + x <= dimension - 1 && randomNum2 + y <= dimension - 1 && randomNum2 +y >= 0) {
                  //  System.out.print("Adding to mine adjcounter at ");
                  //  System.out.print(randomNum1 + x);
                   // System.out.println(randomNum2 + y);
                    fieldArray[randomNum1 + x][randomNum2 + y].incrementAdjacentMineCounter();


                }
            }
            }
        }



    }
    // sets actual cell image according to amount of neighbouring mines




}
