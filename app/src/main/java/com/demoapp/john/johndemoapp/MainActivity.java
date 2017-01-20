package com.demoapp.john.johndemoapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.demoapp.john.johndemoapp.models.Product;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> products;
    private RecyclerView rv;
    private FirebaseAuth mAuth;
    private ProgressDialog pDialog;
    RVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        pDialog = new ProgressDialog(this);
        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        products = new ArrayList<>();
        initializeData();
        initializeAdapter();
    }

    private void initializeData() {
        final String[] jsonResponse = new String[1];
        final String TAG = "ProductsJSON";

        String url = "http://johnny.stevandoh.webfactional.com/getProducts.php";

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        Log.d(TAG, response.toString());
                        Log.d(TAG, response.toString());
                        Log.d(TAG, response.toString());
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse[0] = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject product = (JSONObject) response
                                        .get(i);

                                String name = product.getString("name");
                                String price = product.getString("price");
                                String image = product.getString("image");
                                Product productObject = new Product(name,price,image);
                                products.add(productObject);

                            }

                            showpDialog();
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();

                            VolleyLog.d(TAG, "Error: " + e.getMessage());
                            VolleyLog.d(TAG, "Error: " + e.getMessage());
                            VolleyLog.d(TAG, "Error: " + e.getMessage());
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();

            }


        });


        // Adding request to request queue
        Volley.newRequestQueue(getApplicationContext()).add(req);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    private void initializeAdapter() {
        adapter = new RVAdapter(products);
        adapter.setContext(getApplicationContext());
        rv.setAdapter(adapter);
    }

}