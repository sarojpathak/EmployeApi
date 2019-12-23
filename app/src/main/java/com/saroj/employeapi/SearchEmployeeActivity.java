package com.saroj.employeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saroj.employeapi.api.EmployeeAPI;
import com.saroj.employeapi.model.Employee;
import com.saroj.employeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {

    private EditText etEmployeeNo;
    private TextView result;
    private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etEmployeeNo = findViewById(R.id.etEmployeeNo);
        result = findViewById(R.id.result);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);

                Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmployeeNo.getText().toString()));

                listCall.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        String content = "";
                        content += " Id : " +response.body().getId() + "\n";
                        content += " Name : " +response.body().getEmployee_name() + "\n";
                        content += " Age : " +response.body().getEmployee_age()+ "\n";
                        content += " Salary : " +response.body().getEmployee_salary();
                        result.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(SearchEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
