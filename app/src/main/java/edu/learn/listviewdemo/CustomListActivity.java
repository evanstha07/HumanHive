package edu.learn.listviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Human> humanList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_list);
        listView = findViewById(R.id.humanList);
        humanList = new ArrayList<Human>();

        fetchData();




    }



    void fetchData() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://randomuser.me/api/?results=30";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");

                            for (int i = 0; i < results.length(); i++) {
                                JSONObject humanObj = results.getJSONObject(i);

                                String gender = humanObj.getString("gender");
                                JSONObject nameObj = humanObj.getJSONObject("name");

                                String title = nameObj.getString("title");
                                String first = nameObj.getString("first");
                                String last = nameObj.getString("last");

                                JSONObject locationObj = humanObj.getJSONObject("location");

                                String city = locationObj.getString("city");
                                String country = locationObj.getString("country");
                                String state = locationObj.getString("state");
                                String postcode = locationObj.getString("postcode");

                                JSONObject streetObj = locationObj.getJSONObject("street");
                                String number = streetObj.getString("number");
                                String streetName = streetObj.getString("name");

                                JSONObject coordinate = locationObj.getJSONObject("coordinates");
                                String latitude = coordinate.getString("latitude");
                                String longitude = coordinate.getString("longitude");

                                JSONObject timeZone = locationObj.getJSONObject("timezone");
                                String offset = timeZone.getString("offset");
                                String description = timeZone.getString("description");

                                JSONObject dob = humanObj.getJSONObject("dob");
                                int age = dob.getInt("age");
                                String date = dob.getString("date");

                                String email = humanObj.getString("email");
                                String phone = humanObj.getString("phone");
                                String cell = humanObj.getString("cell");

                                JSONObject loginObj = humanObj.getJSONObject("login");
                                String username = loginObj.getString("username");
                                String password = loginObj.getString("password");

                                JSONObject pictureObj = humanObj.getJSONObject("picture");
                                String large = pictureObj.getString("large");
                                String medium = pictureObj.getString("medium");
                                String thumbnail = pictureObj.getString("thumbnail");

                                String nat = humanObj.getString("nat");


                                Human human = new Human();
                                human.setGender(gender);
                                human.setTitle(title);
                                human.setFirstName(first);
                                human.setLastName(last);
                                human.setStreetName(streetName);
                                human.setStreetNumber(Integer.parseInt(number));
                                human.setCity(city);
                                human.setState(state);
                                human.setCountry(country);
                                human.setPostcode(postcode);
                                human.setLatitude(latitude);
                                human.setLongitude(longitude);
                                human.setTimezoneOffset(offset);
                                human.setTimezoneDescription(description);
                                human.setEmail(email);
                                human.setPhone(phone);
                                human.setCell(cell);
                                human.setUsername(username);
                                human.setPassword(password);

                                human.setPictureLarge(large);
                                human.setPictureMedium(medium);
                                human.setPictureThumbnail(thumbnail);
                                human.setNationality(nat);


                                humanList.add(human);


                            }




                            //we do adapter here
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(stringRequest);
    }

}