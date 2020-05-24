package Logic;

import android.content.res.Resources;
import android.graphics.Point;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.widget.ToggleButton;

import com.example.vishniya.minesweeperandroid.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Minecell {

    private boolean hasMine = false;
    // holds
    private Point rowColumn;
    private int adjacentMineCounter=0;
    private boolean pressed = false;
    // this is the only way ive found to get a resource outside of an activity (using getSystem(

    Drawable defaultCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare4040);
    Drawable actualCellImage;
    Drawable displayedImage;
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

   public Minecell(Point rowColumn){
       this.rowColumn=rowColumn;



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
        adjacentMineCounter++;
    }

    public boolean isPressed(){
        return pressed;
    }


    public void callByName(String funcName) {
        try {
            Method method = getClass().getDeclaredMethod(funcName);
            method.invoke(this, new Object[] {});
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

    // todo trying to dynamically set the cellimage using the number of adjacent mines to set the correct image
    //

    public void setActualCellImage(){



    if(isHasMine()){
        actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquaremine);

    }

    else {
        String nameOfImageFile = new String();
        nameOfImageFile = "Resources.getSystem().getDrawable(R.drawable.gridsquare"
                + adjacentMineCounter;

    // only necessary to use big if the want big icons
    //+ "Big"

        callByName(actualCellImage);


        actualCellImage = nameOfImageFile.;
    }


    }

    public Drawable getActualCellImage(){
       return actualCellImage;
    }

    public void togglePressed(){
        pressed = !pressed;
        displayedImage = actualCellImage;


    }



}

