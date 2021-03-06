package com.example.newpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Map;

import static android.view.Gravity.CENTER;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {
    TimePicker picker;
    TextView tvw;
    TextView tvw2;
    TextView text;

    int Hour = 0, Minute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        TimePicker timepicker;

        findViewById(R.id.btn1).setOnClickListener(this);

        tvw = (TextView) findViewById(R.id.textViewTime);
        tvw2 = (TextView) findViewById(R.id.textViewTime2);
       // text = (TextView) findViewById(R.id.textViewTime3);


        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog
                        = new TimePickerDialog(AlarmActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvw.setText(String.format("%02d" + " : " + "%02d", hourOfDay, minute));
                        tvw2.setText(String.format("%02d%02d00", hourOfDay, minute));

                    }

                }, Hour, Minute, false);
                timePickerDialog.show();
            }
        });


        Button back = findViewById(R.id.btn3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //???????????? ?????? main-sub ??????

                Intent intent = new Intent(AlarmActivity.this, HomeActivity.class);

                startActivity(intent);
            }
        });


    }


    @Override
    public void onClick(View v) {

        int hour, minute;
        String am_pm;
        switch (v.getId()) {
            case R.id.btn1:
                new AlertDialog.Builder(this)
                        .setTitle("????????? ??????")
                        .setMessage("????????? ????????? ?????????????????? \n?????????????????? ????????? ???????????? ?????? \n3???????????? ????????? ??????????????? ????????? \n???????????????.")
                        .setNeutralButton("??????", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                            }
                        })
                        .show(); // ????????? ?????????
                break;
        }
    }

    public void clickGet(View view){
        String time1 = tvw2.getText().toString();

//Get ??????
        String serverUrl = "http://15.164.218.79:8080/test_table/settime.jsp";

        try {
            time1 = URLEncoder.encode(time1, "utf-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String getUrl = serverUrl + "?time1=" + time1;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //while????????? line?????? ???????????? ????????? UI??? ?????????????????? runOnUiThread??? ??????????????? ??????.
               // tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlarmActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });
        //????????? ???????????? ?????? ?????? ?????? ?????????
        //????????? ????????? ????????? ?????? ????????? ??????
        //?????????(RequestQueue)
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //???????????? ?????? ?????? ??????
        //???????????? ?????? ?????? ??????..
        requestQueue.add(stringRequest);
    }

    public void clickPost(View view) {

        final String time =tvw2.getText().toString();;

        //Post ???????????? ?????? ?????? ??????
        String serverUrl= "http://15.164.218.79:8080/test_table/settime.jsp";

        //PostTest.php??? ?????? ?????? ?????? ??????
        //????????? String?????? ?????? ??????
        StringRequest stringRequest= new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  text.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlarmActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }){
            //POST ???????????? ?????? ????????????
            //??????????????? ?????? ?????????

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> data= new HashMap<>();
                data.put("time1",time);
                System.out.println(time);
                return data;

            }

        };


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);





    }


}



