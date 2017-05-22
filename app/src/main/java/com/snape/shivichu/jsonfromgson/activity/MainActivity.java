package com.snape.shivichu.jsonfromgson.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.snape.shivichu.jsonfromgson.R;
import com.snape.shivichu.jsonfromgson.adapter.RecyclerViewAdapter;
import com.snape.shivichu.jsonfromgson.holder.view_holder.Datalist;
import com.snape.shivichu.jsonfromgson.utils.RecyclerTouchListener;
import com.snape.shivichu.jsonfromgson.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Shivichu on 22-05-2017.
 */

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    public  static  final String URL ="http://toscanyacademy.com/blog/mp.php";

    private RecyclerView recyclerView;

    List<Datalist> posts = new ArrayList<Datalist>();

    private LinearLayoutManager layoutManager;

    private RecyclerViewAdapter adapter;

    CoordinatorLayout coordinatorLayout;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;

        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        }


        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        requestJsonObject();



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

               Datalist datalist = posts.get(position);


                Snackbar snackbar = Snackbar.make(coordinatorLayout,"Clicked :"+datalist.getSongTitle(),Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));


    }

    private void requestJsonObject(){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                posts = Arrays.asList(mGson.fromJson(response, Datalist[].class));

                adapter = new RecyclerViewAdapter(MainActivity.this, posts);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error " + error.getMessage());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}

