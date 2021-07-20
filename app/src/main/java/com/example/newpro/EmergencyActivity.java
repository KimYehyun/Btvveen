package com.example.newpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EmergencyActivity extends AppCompatActivity {


    private EditText emergency;
    private Button bntsend;
    private ListView listView2;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItem1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        emergency = findViewById(R.id.EditText_send);
        //  bntsend = findViewById(R.id.btntosend);
/*
        listItem1 = new ArrayList<String>();
        // 아이템 추가한다.
        bntsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listItem1.add(emergency.getText().toString());
                emergency.setTextColor(Color.parseColor("#000000"));
                adapter.notifyDataSetChanged(); // 변경되었음을 어답터에 알려준다.
                emergency.setText("");

            }
        });


        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item_1, listItem1);
        //list_item_1 : 글자색 검정으로 변경 (수민추가 0912)

        listView2 = findViewById(R.id.listview2);

        // listView1.setTextColor(Color.parseColor("#000000"));
        listView2.setAdapter(adapter);

        // 각 아이템 클릭시 해당 아이템 삭제한다.
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 콜백매개변수는 순서대로 어댑터뷰, 해당 아이템의 뷰, 클릭한 순번, 항목의 아이디
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), listItem1.get(i).toString() + " 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                listItem1.remove(i);
                adapter.notifyDataSetChanged();

            }

        });

 */


        Button bnt_back_emergency = findViewById(R.id.btn_back_emergency);
        bnt_back_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 통해 main-sub 연결
                Intent intent = new Intent(EmergencyActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        Button bnt_menu_emergency = findViewById(R.id.btn_menu_emergency);
        bnt_menu_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트를 통해 main-sub 연결
                Intent intent = new Intent(EmergencyActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
    public void clickGet_E(View view){
        String content = emergency.getText().toString();

//Get 방식
        String serverUrl = "http://15.164.218.79:8080/test_table/getEmerAlarm.jsp";

        try {
            content = URLEncoder.encode(content, "utf-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String getUrl = serverUrl + "?content=" + content;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //while문으로 line해서 읽어오는 작업과 UI에 업데이트하는 runOnUiThread도 안만들어도 된다.
                // tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EmergencyActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });
        //서버와 데이터를 주고 받는 요청 객체를
        //서버로 보내줄 우체통 같은 역할의 객체
        //요청큐(RequestQueue)
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //우체통에 요청 편지 넣기
        //요청큐에 요청 객체 추가..
        requestQueue.add(stringRequest);
    }





    public void clickPost_E(View view) {

      //   String content = emergency.getText().toString();


        //Post 방식으로 보낼 서버 주소
        String serverUrl = "http://15.164.218.79:8080/test_table/getEmerAlarm.jsp";

        //PostTest.php로 보낼 요청 객체 생성
        //결과를 String으로 받는 객체
        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  text.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EmergencyActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }) {
            //POST 방식으로 보낼 데이터를
            //리턴해주는 콜백 메소드

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> data = new HashMap<>();
                data.put("content", emergency.getText().toString());
                System.out.println(emergency.getText().toString());
                return data;

            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}


