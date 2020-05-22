package Logic;

import android.graphics.Point;

import java.util.ArrayList;

public class Minefield {

    private int mineCreationCount;
    private int totalMinesNeeded;

    private Minecell[][] fieldArray;
    private ArrayList<Point> listOfMines;
    private int dimension;

    // TODO ADD DIFFICULTY AS A PARAMETER AND CODE IT
    public Minefield(int dimension){
        this.dimension=dimension;
        totalMinesNeeded=dimension;
        fieldArray = new Minecell[dimension][dimension];

        createMinefield();

        while(mineCreationCount < totalMinesNeeded ) {
            generateMine();
        }

    }

    // creation of 2d array of minecells
    // using a table NOT a grid, so start at top left , 1,1 is top left

// TODO try to handle all location information of cells, outside of minecell class i.e keep inside this class

    private void createMinefield(){

        // the array address tells you the location
        // TODO none of this is added to ui... do that later

        for(int rowNum=1; rowNum<=dimension;rowNum++){

            for(int columnNum = 1;columnNum<=dimension;columnNum++) {
                fieldArray[rowNum][columnNum]= new Minecell();


            }


        }

    }

    // generates 1 mine at a random non-mine location
    public void generateMine(){

        // TODO TEST these give right coordinates to create the point
        int randomNum1 = (int) (Math.random()*dimension)+1;
        int randomNum2 = (int) (Math.random()*dimension)+1;
        System.out.println(randomNum1);

        Point mineCoord = new Point ( randomNum1, randomNum2);

        // now flag this randomcell as having a mine in the array
        // also add it to a seperate list of MINEcells for easy reference
    // TODO FIGURE BEST WAY OF STORING THIS LIST, use Point as reference? or reference the actual minecell

        Minecell currentMine = fieldArray[randomNum1][randomNum2];

        if (!currentMine.isHasMine()) {
            currentMine.setHasMine(true);
            listOfMines.add(mineCoord);
            mineCreationCount++;
        }

        // this loop checks for adjacent cells and ++s their mineadjacencycounter

        for(int y=-1; y<=1; y++) {
            for (int x = -1; x<=1; x++){
                // skip check current cell
                if (y==0 && x==0){
                    continue;
                }
                // check for >=1 , ensures that were staying inside bounds of minefield, since mine could be topleft,
                // and we could be trying to look to the left of it
                if(randomNum1+x >= 1 && randomNum1 +x <=dimension && randomNum2 + y <=dimension && randomNum2 >=1) {
                    // increasing teh adjacencycounter
                    currentMine.incrementAdjacentMineCounter();


                }

            }
        }



    }



}
