package Logic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Point;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.ToggleButton;

import com.example.vishniya.minesweeperandroid.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Minecell {


    Context context;
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

    ImageView defaultCellImage;

    // Drawable defaultCellImage= R.drawable.gridsquare4040;e
    ImageView actualCellImage;
    ImageView displayedImage;
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

        adjacentMineCounter=0;
        defaultCellImage = new ImageView(context);

        defaultCellImage.setImageResource(R.drawable.gridsquare4040);

        actualCellImage=defaultCellImage;

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
        adjacentMineCounter = adjacentMineCounter+1;
    }

    public boolean isPressed() {
        return pressed;
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

    // todo trying to dynamically set the cellimage using the number of adjacent mines to set the correct image
    //

    public void setActualCellImage() {



        if (isHasMine()) {

            actualCellImage.setImageResource(R.drawable.gridsquaremine);
        } else {

           // actualCellImage.setImageResource(R.drawable.gridsquare1big);
            actualCellImage.setImageResource(mCellIcons[adjacentMineCounter]);

        /*
        switch(adjacentMineCounter){

            case 0: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare0);
                break;


            case 1: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare7);
                break;


            case 2: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare2);
                break;

            case 3:  actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare3);
                break;

            case 4: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare4);
                break;

            case 5: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare5);
                break;

            case 6: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare7);
                break;

            case 7: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare7);
                break;

            case 8: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare7);
                break;

            case 9: actualCellImage= Resources.getSystem().getDrawable(R.drawable.gridsquare7);
                break;


            default:



        }
*/


        }

/*


    else {

      //  int idOfImageResourceFile = new String();
        nameOfImageFile = "Resources.getSystem().getDrawable(R.drawable.gridsquare"
                + adjacentMineCounter;

    // only necessary to use big if the want big icons
    //+ "Big"

        callByName(actualCellImage);


    //    actualCellImage= Resources.getSystem().getDrawable(nameOfImageFile);
    }
*/

    }

    public ImageView getActualCellImage() {
        return actualCellImage;
    }

    public void togglePressed() {
        pressed = !pressed;
        displayedImage = actualCellImage;


    }


}

