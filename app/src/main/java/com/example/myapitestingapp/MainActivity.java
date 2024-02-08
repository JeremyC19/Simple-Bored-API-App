package com.example.myapitestingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create TextView instances
        TextView activityTv = findViewById(R.id.activity);
        TextView typeTv = findViewById(R.id.type);
        TextView participantsTv = findViewById(R.id.participants);
        TextView priceTv = findViewById(R.id.price);
        TextView accessibilityTv = findViewById(R.id.accessibility);

        String url = "https://www.boredapi.com/api/activity";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parse the response
                    String activity = response.getString("activity");
                    String type = response.getString("type");
                    int participants = response.getInt("participants");
                    double price = response.getDouble("price");
                    double accessibility = response.getDouble("accessibility");

                    // Update the TextViews
                    activityTv.setText("Activity: " + activity);
                    typeTv.setText("Type: " + type);
                    participantsTv.setText("Participants: " + participants);
                    priceTv.setText("Price: " + price);
                    accessibilityTv.setText("Accessibility: " + accessibility);
                } catch (JSONException e) {
                    e.printStackTrace();
                    activityTv.setText("Error parsing JSON response");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                activityTv.setText("Error: " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}

