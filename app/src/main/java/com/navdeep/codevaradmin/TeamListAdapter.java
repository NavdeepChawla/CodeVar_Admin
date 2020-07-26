package com.navdeep.codevaradmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class TeamListAdapter extends ArrayAdapter<TeamListObjects>
{
    private List<TeamListObjects> userlist;
    private Context context;
    private LayoutInflater layoutInflater;
    private int layoutResource;

    public static class ViewHolder
    {
        TextView userTextViewRecyclerView;
        ViewHolder (View view)
        {
            this.userTextViewRecyclerView=view.findViewById(R.id.userTextViewRecyclerView);
        }
    }

    public TeamListAdapter(@NonNull Context context, int resource, List<TeamListObjects> templist)
    {
        super(context,R.layout.userlist_row_layout);
        this.context=context;
        this.userlist=templist;
        this.layoutInflater=LayoutInflater.from(context);
        this.layoutResource=resource;
    }

    @Override
    public int getCount()
    {
        return userlist.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent)
    {
        View view=layoutInflater.inflate(layoutResource,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        TeamListObjects tempdata=userlist.get(position);
        viewHolder.userTextViewRecyclerView.setText(tempdata.UserName);
        return view;
    }
}
