package com.santosh.dawn.blogpost;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{

    private ListView listView;

    private BlogpostDB mBlogpostDB;
    private BlogpostAdapter mBlogpostAdapter;

    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        mBlogpostDB = new BlogpostDB(this);
        mBlogpostAdapter = new BlogpostAdapter(this, mBlogpostDB.getAllData());

        actionButton = (FloatingActionButton) findViewById(R.id.actionButton);

        listView.setAdapter(mBlogpostAdapter);
        mBlogpostAdapter.notifyDataSetChanged();
        mBlogpostDB.close();

    }

    public void onClickActionButton(View view) {
        startActivity(new Intent(MainActivity.this, PostingActivity.class));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
