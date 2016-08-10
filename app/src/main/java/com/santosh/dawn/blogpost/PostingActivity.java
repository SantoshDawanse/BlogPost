package com.santosh.dawn.blogpost;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class PostingActivity extends AppCompatActivity {

    private EditText etPostArea;

    private BlogpostDB mBlogpostDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        etPostArea = (EditText) findViewById(R.id.etPostArea);

        mBlogpostDB = new BlogpostDB(this);
    }

    public void onClickPost(View view) {
        String post = etPostArea.getText().toString();

        if(!post.equals("")){
            try {
                mBlogpostDB.open();
                mBlogpostDB.insertData(post);
                mBlogpostDB.close();
                startActivity(new Intent(PostingActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "You haven't written anything!!!", Toast.LENGTH_LONG).show();
        }

    }
}
