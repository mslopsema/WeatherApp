package mikeslopsema.weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherMain extends AppCompatActivity {
    private String mUrl = "http://api.wunderground.com/api/24f0b7e4ed53f605/forecast/q/MI/Detroit.json";
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new CustomAdapter(new ArrayList<ListItemModel>(), getApplicationContext());
        final ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSnackbar(view, "List View : " + adapter.get(position).getName());
            }
        });

        final EditText cityEditText = (EditText) findViewById(R.id.cityText);
        cityEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO) {
                    addCity(textView, cityEditText);
                    return true;
                }
                return false;
            }
        });

        Button okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCity(view, cityEditText);
            }
        });


    }

    private void addCity(View view, final EditText et) {
        if (et.getText().length() < 1) return;
        setSnackbar(view, "'OK' button clicked : " + et.getText());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                mUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("MIKE", "HTTP BODY : " + response.toString());

                try {
                    JSONArray ja = response.getJSONObject("forecast")
                            .getJSONObject("txt_forecast")
                            .getJSONArray("forecastday");
                    String s = ja.getJSONObject(0).getString("icon");
                    String imgUrl = ja.getJSONObject(0).getString("icon_url");
                    Log.i("MIKE", s + " : " + imgUrl);

                    ListItemModel lim = new ListItemModel(et.getText().toString());
                    for (int i = 0; i < ja.length(); i++) {
                        Forecast f = new Forecast();
                        JSONObject obj = ja.getJSONObject(i);
                        f.title = obj.getString("title");
                        f.detail = obj.getString("fcttext");
                        f.imgUrl = obj.getString("icon_url");
                        f.print();
                        lim.addForecast(f);
                    }
                    adapter.add(lim);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        HttpThread.getInstance(this).addRequest(request);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            adapter.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setSnackbar(View v, String s) {
        Snackbar.make(v, s, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
