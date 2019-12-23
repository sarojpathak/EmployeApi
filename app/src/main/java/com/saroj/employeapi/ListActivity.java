package com.saroj.employeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.saroj.employeapi.Adapter.EmployeeAdapter;
import com.saroj.employeapi.api.EmployeeAPI;
import com.saroj.employeapi.model.Employee;
import com.saroj.employeapi.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.saroj.employeapi.url.URL.*;

public class ListActivity extends AppCompatActivity {
RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        recyclerView=findViewById(R.id.recyclerView);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall=employeeAPI.getAllEmployees();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ListActivity.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee>employeeList=response.body();



                EmployeeAdapter employeeAdapter = new EmployeeAdapter(ListActivity.this, employeeList);
                recyclerView.setAdapter(employeeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
            }


            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(ListActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
