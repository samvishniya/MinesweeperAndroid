package Logic;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMax implements InputFilter {
    private int min,max;

    public InputFilterMinMax(int min, int max){
        this.min=min;
        this.max=max;
    }

    public InputFilterMinMax(String min, String max){
        this.min=Integer.parseInt(min);
        this.max=Integer.parseInt(max);
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        try {

            if(end==1){
                min = Integer.parseInt(source.toString());
            }
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input) )
                return null;
        } catch ( NumberFormatException nfe) {}
        return "";
        }

    private boolean isInRange(int min, int max, int input) {

        // this ternary operator ensure that if min and max are mixed up, the method still gives
        // the desired result
        // i.e it reads "if max> min, return boolean of input>=min && input<=max ; else if min>max do vv

        return (max > min ? input >= min && input <= max :  input <=min && input >= max) ;



}
}
