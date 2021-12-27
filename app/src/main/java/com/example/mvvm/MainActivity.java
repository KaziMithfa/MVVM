package com.example.mvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ResultViewModel resultViewModel;

    public static final int ADD_RESULT_REQUEST_ = 1;
    public static final int EDIT_REQUEST_ = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.button_addnote);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddResultEditActivity.class);
                startActivityForResult(intent,ADD_RESULT_REQUEST_);

            }
        });

          RecyclerView recyclerView = findViewById(R.id.recycler_view);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setHasFixedSize(true);

         final ResultAdapter adapter = new ResultAdapter();
         recyclerView.setAdapter(adapter);

        resultViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ResultViewModel.class);

        resultViewModel.getAllNotes().observe(this, new Observer<List<ClassTest>>() {
            @Override
            public void onChanged(List<ClassTest> classTests) {

                adapter.setNotes(classTests);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

               resultViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Result is deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListner(new ResultAdapter.onItemClickListner() {
            @Override
            public void onItemClick(ClassTest classTest) {
                Intent intent = new Intent(MainActivity.this, AddResultEditActivity.class);
                intent.putExtra(AddResultEditActivity.EXTRA_ID,classTest.getId());
                intent.putExtra(AddResultEditActivity.EXTRA_NAME,classTest.getName());
                intent.putExtra(AddResultEditActivity.EXTRA_ROLL,classTest.getRoll());
                intent.putExtra(AddResultEditActivity.EXTRA_RESULT,classTest.getNumber());
                startActivityForResult(intent,EDIT_REQUEST_);





            }
        });

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_RESULT_REQUEST_&& resultCode == RESULT_OK)
        {
            String name = data.getStringExtra(AddResultEditActivity.EXTRA_NAME);
            int roll = data.getIntExtra(AddResultEditActivity.EXTRA_ROLL,1);
            double result = data.getDoubleExtra(AddResultEditActivity.EXTRA_RESULT,2.00);

            ClassTest classTest = new ClassTest(name,roll,result);
            resultViewModel.insert(classTest);

            Toast.makeText(this, "Result saved", Toast.LENGTH_SHORT).show();



        }

        else if(requestCode== EDIT_REQUEST_&& resultCode == RESULT_OK)
        {

            int id = data.getIntExtra(AddResultEditActivity.EXTRA_ID,-1);
            if(id == -1)
            {
                Toast.makeText(this, "Results cant be updated", Toast.LENGTH_SHORT).show();

            }

            String name = data.getStringExtra(AddResultEditActivity.EXTRA_NAME);
            int roll = data.getIntExtra(AddResultEditActivity.EXTRA_ROLL,1);
            double result = data.getDoubleExtra(AddResultEditActivity.EXTRA_RESULT,2.00);

            ClassTest classTest = new ClassTest(name,roll,result);
            classTest.setId(id);
            resultViewModel.update(classTest);
            Toast.makeText(this, "Result has been updated", Toast.LENGTH_SHORT).show();






        }

        else{
            Toast.makeText(this, "result is not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.delete_all_results:
                resultViewModel.deleteAll();
                Toast.makeText(this, "Delete ALL RESULTS", Toast.LENGTH_SHORT).show();
                return true;

                default:
                    return super.onOptionsItemSelected(item);

        }

    }
}
