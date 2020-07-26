package com.navdeep.codevaradmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeamListActivity extends AppCompatActivity {

    private int position = 0;
    private LinearLayout userListView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        String Node="";
        Node = getIntent().getStringExtra("Node");
        final String tempNode=Node;

        assert Node != null;
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child(Node);
        final List<TeamListObjects> adapterList=new ArrayList<>();
        final TeamListAdapter adapter = new TeamListAdapter(getApplicationContext(), R.layout.userlist_row_layout,adapterList);

        userListView=findViewById(R.id.teamRecyclerView);
        userRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(userListView.getChildCount() > 0)
                {
                    userListView.removeAllViews();
                }
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    //key
                    final String key=ds.getKey();
                    final String value= Objects.requireNonNull(ds.getValue()).toString();

                    //AdapterList
                    final TeamListObjects listdata=new TeamListObjects(key,null);
                    adapterList.add(listdata);

                    //Adapter
                    final View item = adapter.getView(position++, null, null);
                    item.setTag(position);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {

                            Intent intent = new Intent(getApplicationContext(), TextActivity.class);
                            intent.putExtra("Key",key);
                            intent.putExtra("Value",value);
                            intent.putExtra("Node",tempNode);
                            startActivity(intent);

                        }
                    });
                    userListView.addView(item);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), TeamActivity.class);
        startActivity(intent);
    }
}
