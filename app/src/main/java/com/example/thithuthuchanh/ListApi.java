package com.example.thithuthuchanh;

import androidx.appcompat.app.AppCompatActivity;

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

    EditText txtName, txtAge, txtClass, txtId;
    Button btnAdd, btnUpdate;
    TextView tvName, tvId, tvClass, tvAge;
    ArrayList<User> listUser;
    ListView listView;

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
        tvName=findViewById(R.id.tvName);
        tvId=findViewById(R.id.tvId);
        tvClass=findViewById(R.id.tvClass);
        tvAge=findViewById(R.id.tvAge);
        listView = findViewById(R.id.listView);

//        listUser=GetArrayJson(url);
//        System.out.println(listUser);
//        UserAdater userAdater=new UserAdater(getApplicationContext(), listUser);
//        listView.setAdapter(userAdater);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PortApi(url, txtName.getText().toString().trim(), Integer.parseInt(txtAge.getText().toString().trim()), txtClass.getText().toString().trim());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi(url, txtId.getText().toString().trim(), txtName.getText().toString().trim(), Integer.parseInt(txtAge.getText().toString().trim()), txtClass.getText().toString().trim());
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

    private ArrayList<User> GetArrayJson(String url){
        ArrayList<User> list=null;
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i=0; i<response.length(); i++){
                                    try {
                                        JSONObject object = (JSONObject) response.get(i);

                                        System.out.println(object.toString());
                                        User user=new User(object.getString("name"), object.getString("id"), object.getInt("age"), object.getString("class"));
                                        list.add(user);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListApi.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
        return list;
    }


}