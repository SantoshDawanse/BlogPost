package com.santosh.dawn.blogpost;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by dawn on 8/10/2016.
 */
public class BlogpostAdapter extends CursorAdapter{
    public BlogpostAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.row_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
        TextView tvPost = (TextView) view.findViewById(R.id.tvPost);

        tvId.setText(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
        tvDate.setText(cursor.getString(cursor.getColumnIndexOrThrow("date")));
        tvTime.setText(cursor.getString(cursor.getColumnIndexOrThrow("time")));
        tvPost.setText(cursor.getString(cursor.getColumnIndexOrThrow("post")));
    }
}
