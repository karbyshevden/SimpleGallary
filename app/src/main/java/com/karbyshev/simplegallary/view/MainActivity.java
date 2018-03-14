package com.karbyshev.simplegallary.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.karbyshev.simplegallary.R;
import com.karbyshev.simplegallary.adapter.MyAdapter;
import com.karbyshev.simplegallary.adapter.MyItem;
import com.karbyshev.simplegallary.model.MyParser;
import com.karbyshev.simplegallary.presenter.IPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainView{
    private EditText mEditText;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private MyAdapter myAdapter;
    private ArrayList<MyItem> mItemArrayList;
    private IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText)findViewById(R.id.my_editText);
        mButton = (Button)findViewById(R.id.my_button);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mItemArrayList = new ArrayList<>();

        iPresenter = new MyParser(MainActivity.this);
        iPresenter.parseJason(MainActivity.this, mItemArrayList);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newSearch = mEditText.getText().toString();
                iPresenter.perfomText(newSearch);
            }
        });

        myAdapter = new MyAdapter(MainActivity.this, mItemArrayList);
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(MainActivity.this);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(MainActivity.this, "Was Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editTextIsEmpty() {
        Toast.makeText(MainActivity.this, "Please, enter some word!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isOk() {

    }
}
