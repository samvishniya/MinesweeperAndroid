package Logic;

import android.content.Context;

import android.graphics.Point;


import android.widget.ImageView;

import com.example.vishniya.minesweeperandroid.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Minecell {


    private Context context;
    private boolean hasMine = false;
    // holds
    private Point rowColumn;
    private int adjacentMineCounter;
    private boolean pressed = false;

    private int[] mCellIcons = {

            R.drawable.gridsquare0,
            R.drawable.gridsquare1,
            R.drawable.gridsquare2,
            R.drawable.gridsquare3,
            R.drawable.gridsquare4,
            R.drawable.gridsquare5,
            R.drawable.gridsquare6,
            R.drawable.gridsquare7,
            R.drawable.gridsquare7,
            R.drawable.gridsquare7,

    };
    // this is the only way ive found to get a resource outside of an activity (using getSystem(


    // Drawable defaultCellImage= R.drawable.gridsquare4040;e

    private ImageView displayedImage;
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


    public Minecell(Point rowColumn, Context context) {
        this.context = context;

        this.rowColumn = rowColumn;

        adjacentMineCounter = 0;
        displayedImage = new ImageView(context);

        displayedImage.setImageResource(R.drawable.gridsquare4040);
        //displayedImage.setBackgroundResource(R.drawable.gridsquare4040);
    }


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
        adjacentMineCounter = adjacentMineCounter + 1;
    }

    public boolean isPressed() {
        return pressed;
    }

    public Point getRowColumn(){
        return rowColumn;
    }

    public void callByName(String funcName) {
        try {
            Method method = getClass().getDeclaredMethod(funcName);
            method.invoke(this, new Object[]{});
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    // todo sort out stackoverflow caused by cascade of setActualImages (where most of the board has no cells and theres lots of cells to laod at once .e.g 20x20)
    private void setActualCellImage() {


        if (isHasMine()) {

            displayedImage.setImageResource(R.drawable.gridsquaremine);

        } else {


            displayedImage.setImageResource(mCellIcons[adjacentMineCounter]);


        }
    }


    public ImageView getDisplayedImage() {

        // displayedImage.setAdjustViewBounds(true);
        // displayedImage.setScaleType(ImageView.ScaleType.FIT_XY);
        return displayedImage;

    }

    public void togglePressed() {
        pressed = !pressed;
        if (pressed) {
            // System.out.println("press detection working");
            setActualCellImage();

        }

    }


}

