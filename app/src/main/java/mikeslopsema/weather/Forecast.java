package mikeslopsema.weather;

import android.util.Log;

/**
 * Created by Mike on 1/21/2018.
 */

public class Forecast {
    String title;
    String detail;
    String imgUrl;

    void print() {
        Log.i("MIKE", "FORECAST : " + title + " : " + detail + " : " + imgUrl);
    }
}
