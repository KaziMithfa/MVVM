package com.example.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddResultEditActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.mvvm.name";
    public static final String EXTRA_ROLL = "com.example.mvvm.roll";
    public static final String EXTRA_RESULT = "com.example.mvvm.result";
    public static final String EXTRA_ID = "com.example.mvvm.id";
    private EditText name,roll,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        name = findViewById(R.id.add_name);
        roll = findViewById(R.id.add_roll);
        result = findViewById(R.id.add_number);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Result");

            name.setText(intent.getStringExtra(EXTRA_NAME));
            roll.setText(String.valueOf(intent.getIntExtra(EXTRA_ROLL,1)));
            result.setText(String.valueOf(intent.getDoubleExtra(EXTRA_RESULT,2.00)));




        }

        else{
            setTitle("Add Result");

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater() ;
        menuInflater.inflate(R.menu.add_result,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save_result:
                saveResult();
                return true;

                default:
                    return super.onOptionsItemSelected(item);


        }
    }

    private void saveResult() {


        String addName =  name.getText().toString();
        String addRoll =  roll.getText().toString();
        String addnumber =  result.getText().toString();

        if(TextUtils.isEmpty(addName)|| TextUtils.isEmpty(addRoll)|| TextUtils.isEmpty(addnumber))
        {
            Toast.makeText(this, "Please , insert the name ,roll and number", Toast.LENGTH_SHORT).show();
        }

        else{
            Intent data = new Intent();
            data.putExtra(EXTRA_NAME,addName);
            data.putExtra(EXTRA_ROLL,Integer.valueOf(addRoll));
            data.putExtra(EXTRA_RESULT,Double.valueOf(addnumber));

            int id = getIntent().getIntExtra(EXTRA_ID,-1);

            if(id!= -1)
            {
                data.putExtra(EXTRA_ID,id);


            }


            setResult(RESULT_OK,data);
            finish();
        }




    }
}