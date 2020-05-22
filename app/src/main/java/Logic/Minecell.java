package Logic;

import android.graphics.Point;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.media.Image;
import android.widget.ToggleButton;


public class Minecell {

    private boolean hasMine = false;
    private Point gridCoord = new Point();
    private int adjacentMineCounter=0;
    // private ImageIcon
    // border?//
/*
handle the images inside an activity, getresoureces doesnt work outside of an activity
      Drawable defaultCellImage = getResources().getDrawable(R.drawable.gridsquare4040);
*/


    // creates location for the object based on input ints?
   /*  public Minecell(int rowNum, int columnNum){
// TODO watch out for column/row vs x,y
        gridCoord.setLocation(columnNum, rowNum);

    }
*/


// getters and setters
    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public int getAdjacentMineCounter() {
        return adjacentMineCounter;
    }

    public void incrementAdjacentMineCounter() {
        adjacentMineCounter++;
    }




}

