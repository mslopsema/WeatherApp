package mikeslopsema.weather;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;

public class ListItemModel {
    private String name = "Default Name";
    private String description = "Default Description";
    private String imageUrl = "http://icons.wxug.com/i/c/k/mostlycloudy.gif";
    private ArrayList<Forecast> forecasts = new ArrayList<>();

    public ListItemModel(String name) {
        this.name = name;
    }

    public ListItemModel(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public ListItemModel(String name, String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

    public void addForecast(Forecast f) {
        forecasts.add(f);
    }

    public String getName() {
        return name;
    }

    public String getTitle(int i) {
        return forecasts.get(i).title;
    }

    public String getDescription(int i) {
        return forecasts.get(i).detail;
    }

    public String getImageUrl(int i) {
        return forecasts.get(i).imgUrl;
    }

    public GifDrawable getImageGifDrawable(int i) {
        try {
            InputStream is = (InputStream) new URL(forecasts.get(i).imgUrl).getContent();
            GifDrawable gd = new GifDrawable(is);
            return gd;
        } catch (Exception e) {
            return null;
        }
    }
}
