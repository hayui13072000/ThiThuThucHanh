package com.example.thithuthuchanh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListApi extends AppCompatActivity {

    private EditText txtName, txtAge, txtClass, txtId;
    private Button btnAdd, btnUpdate;
    private ArrayList<User> listUser=new ArrayList<User>();
    private UserAdater adapter;
    private RecyclerView rclView;

    String url = "https://60ada4a180a61f0017331601.mockapi.io/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_api);

        txtId=findViewById(R.id.txtId);
        txtName=findViewById(R.id.txtName);
        txtAge=findViewById(R.id.txtAge);
        txtClass=findViewById(R.id.txtClass);
        btnAdd=findViewById(R.id.btnAdd);
        btnUpdate=findViewById(R.id.btnUpdate);
        rclView = findViewById(R.id.rclView);
        //txtId.setEnabled(false);


        GetData(url);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PortApi(url, txtName.getText().toString().trim(), Integer.parseInt(txtAge.getText().toString().trim()), txtClass.getText().toString().trim());
                Intent intent1= new Intent(ListApi.this,ListApi.class);
                startActivity(intent1);
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi(url, txtId.getText().toString().trim(), txtName.getText().toString().trim(), Integer.parseInt(txtAge.getText().toString().trim()), txtClass.getText().toString().trim());
                Intent intent1= new Intent(ListApi.this,ListApi.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void PortApi(String url, String name, int age, String iclass){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ListApi.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListApi.this, "error", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> params=new HashMap<>();
                params.put("name", name);
                params.put("age", String.valueOf(age));
                params.put("class", iclass);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void PutApi(String url, String id, String name, int age, String iclass){
        StringRequest stringRequest=new StringRequest(Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ListApi.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListApi.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> params=new HashMap<>();
                params.put("name", name);
                params.put("age", String.valueOf(age));
                params.put("class", iclass);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void GetData(String url){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET,url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object =(JSONObject) response.get(i);
                                User user = new User();
                                user.setId(object.getString("id"));
                                user.setName(object.getString("name"));
                                user.setAge(object.getInt("age"));
                                user.setIclass(object.getString("class"));
                                System.out.println(user);
                                listUser.add(user);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        buildRecyclerView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListApi.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {

        adapter = new UserAdater(listUser,ListApi.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rclView.setLayoutManager(manager);
        rclView.setAdapter(adapter);
    }
}