package com.navdeep.codevaradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String Value="";
        String Key="";
        String Node="";
        Value = getIntent().getStringExtra("Value");
        Key = getIntent().getStringExtra("Key");
        Node =getIntent().getStringExtra("Node");
        final String tempNode=Node;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        TextView queryText=findViewById(R.id.queryText);
        queryText.setText(Value);

        assert Key != null;
        assert Node != null;
        final DatabaseReference delRef= FirebaseDatabase.getInstance().getReference().child(Node).child(Key);

        Button deleteQuery=findViewById(R.id.deleteQuery);
        deleteQuery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                delRef.removeValue();
                Intent intent=new Intent(getApplicationContext(),TeamListActivity.class);
                intent.putExtra("Node",tempNode);
                startActivity(intent);
            }
        });
    }
}