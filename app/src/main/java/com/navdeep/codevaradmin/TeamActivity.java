package com.navdeep.codevaradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Button consumablesTeam = findViewById(R.id.consumablesTeam);
        Button codeparkRelated = findViewById(R.id.codeparkRelated);
        Button venueIssues = findViewById(R.id.venueIssues);
        Button others = findViewById(R.id.others);
        consumablesTeam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), TeamListActivity.class);
                intent.putExtra("Node","ConsumableTeam");
                startActivity(intent);
            }
        });

        codeparkRelated.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), TeamListActivity.class);
                intent.putExtra("Node","CodeparkRelated");
                startActivity(intent);
            }
        });

        venueIssues.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), TeamListActivity.class);
                intent.putExtra("Node","VenueIssues");
                startActivity(intent);
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeamListActivity.class);
                intent.putExtra("Node","Others");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
        startActivity(intent);
    }
}