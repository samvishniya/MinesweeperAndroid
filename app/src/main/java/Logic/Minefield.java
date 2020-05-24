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

        setCellImages();
    }

    // creation of 2d array of minecells
    // using a table NOT a grid, so start at top left , 1,1 is top left




    public Minecell getCellContent(int row, int column){

        return fieldArray[row][column];

    }



    private void createMinefield(){

        // creates non-mined minecells and tells them their location in the grid (Point)
        // TODO none of this is added to ui... do that later

        for(int rowNum=1; rowNum<=dimension;rowNum++){

            for(int columnNum = 1;columnNum<=dimension;columnNum++) {


                fieldArray[rowNum][columnNum]= new Minecell(new Point(rowNum,columnNum));


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
    // sets actual cell image according to amount of neighbouring mines
    public void setCellImages(){

        // 2d array version of for-each loop ( the outer loop goes through each (row) minecell array,
        // inner loop goes through the objects stored inside each array (column))
// todo practice streams inplace of foreach loop

        for(Minecell[] row : fieldArray){
            for (Minecell cell: row){
                cell.setActualCellImage();
            }

        }


    }




}
