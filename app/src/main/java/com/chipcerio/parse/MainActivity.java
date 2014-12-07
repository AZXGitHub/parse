package com.chipcerio.parse;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends ActionBarActivity {

    interface Category {
        String TECHNOLOGY  = "5JimgqyJB1";
        String SPORTS      = "a3k1grX5pU";
        String EDUCATIONAL = "cENWL5By5v";
        String OTHERS      = "3btYSTqO7T";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get a category name
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Category");
        query.getInBackground(Category.TECHNOLOGY, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject category, ParseException e) {
                if (category == null) return;

                ParseObject event = new ParseObject("Event");
                event.put("event_detail", "Eat, Skate, Repeat");
                event.put("event_image", "http://i.imgur.com/57EFL2y.jpg");
                event.put("event_location", "Fuente Osmena Rotunda, Cebu City");
                event.put("event_name", "Cebu Skate Festival 2014");
                event.put("event_latitude", new ParseGeoPoint(10.3098, 123.8932)); // GeoPoint

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    event.put("event_date", sdf.parse("09/19/2014"));
                } catch (java.text.ParseException e1) {
                    e1.printStackTrace();
                }

                event.put("category", category);

                event.saveInBackground();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
