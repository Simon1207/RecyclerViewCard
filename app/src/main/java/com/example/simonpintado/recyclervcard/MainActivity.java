package com.example.simonpintado.recyclervcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names=this.getAllNames();
        mRecyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);

        //Agrega un nuevo gridLayoutManager con un limite de 2 columnas
        mLayoutManager=new LinearLayoutManager(this);
        mLayoutManager=new GridLayoutManager(this,2);
        mLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

       // mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                //Toast.makeText(MainActivity.this,name+" - "+position, Toast.LENGTH_SHORT).show();
                deleteName(position);
            }
        });

        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_name:
                this.addName(0);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }



    }




    private List<String> getAllNames(){
        return  new ArrayList<String>(){{
            add("Simon");
            add("Alex");
            add("Mayra");
            add("Luis");
        }};
    }

    private void addName(int position) {
        names.add(position,"new Name"+(++counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);

    }

    private void deleteName(int position){
        names.remove(position);
        mAdapter.notifyItemRemoved(position);

    }
}
