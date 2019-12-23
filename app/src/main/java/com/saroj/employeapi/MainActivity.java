package com.saroj.employeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.saroj.employeapi.api.EmployeeAPI;
import com.saroj.employeapi.model.Employee;
import com.saroj.employeapi.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button btnShowAllEmployee, btnRegisterEmployee, btnSearchEmployee, btnUpdateDeleteEmployee;
    TextView tvoutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowAllEmployee = findViewById(R.id.btnShowAllEmployee);
        btnRegisterEmployee = findViewById(R.id.btnRegisterEmployee);
        btnSearchEmployee = findViewById(R.id.btnSearchEmployee);
        btnUpdateDeleteEmployee = findViewById(R.id.btnUpdateDeleteEmployee);
        tvoutput=findViewById(R.id.tvOutput);


        btnShowAllEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAllEmployeeActivity.class);
                startActivity(intent);

            }
        });

        btnRegisterEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterEmployeeActivity.class);
                startActivity(intent);

            }
        });

        btnSearchEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchEmployeeActivity.class);
                startActivity(intent);

            }
        });

        btnUpdateDeleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateDeleteEmployeeActivity.class);
                startActivity(intent);

            }
        });



//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL.base_url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
//        Call<List<Employee>> call = employeeAPI.getAllEmployees();
//        call.enqueue(new Callback<List<Employee>>() {
//            @Override
//            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Employee>> call, Throwable t) {
//
//            }
//        });


    }
}
