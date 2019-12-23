package com.saroj.employeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saroj.employeapi.api.EmployeeAPI;
import com.saroj.employeapi.model.EmployeeCUD;
import com.saroj.employeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterEmployeeActivity extends AppCompatActivity {

    private EditText etName,etSalary,etAge;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);


        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etSalary = findViewById(R.id.etSalary);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                Float salary = Float.parseFloat(etSalary.getText().toString());
                int age = Integer.parseInt(etAge.getText().toString());

                EmployeeCUD employee = new EmployeeCUD(name,salary,age);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

                Call<Void> voidCall = employeeAPI.registerEmployee(employee);

                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(RegisterEmployeeActivity.this, "You have been successfully registered", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
