package Logic;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.media.Image;
import android.widget.ToggleButton;

import com.example.vishniya.minesweeperandroid.MainActivity;
import com.example.vishniya.minesweeperandroid.R;

public class MineCell {

    private boolean hasMine = false;
    private Point gridCoord = new Point();
    private int adjacentMineCounter=0;

    ToggleButton

/*
handle the images inside an activity, getresoureces doesnt work outside of an activity
      Drawable defaultCellImage = getResources().getDrawable(R.drawable.gridsquare4040);
*/


    // creates location for the object based on input ints?



    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }


    public int getAdjacentMineCounter() {
        return adjacentMineCounter;
    }

    public void setAdjacentMineCounter(int adjacentMineCounter) {
        this.adjacentMineCounter = adjacentMineCounter;
    }




}

