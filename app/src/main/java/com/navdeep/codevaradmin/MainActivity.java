package com.navdeep.codevaradmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView scanText;
    ArrayList<Data> a=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanText = findViewById(R.id.scanText);

        //QR Activity
        Button camera = findViewById(R.id.Scan);
        View.OnClickListener c = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanText.setText("");
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan a QR Code");
                integrator.setBeepEnabled(true);
                integrator.initiateScan();
            }
        };
        camera.setOnClickListener(c);

        //FetchDatabaseList
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Consumables");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Data d=new Data();
                    d.Key=ds.getKey();
                    d.Value= Objects.requireNonNull(ds.getValue()).toString();
                    System.out.println(d.Value);
                    a.add(d);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //SetValue=1
        Button setVal=findViewById(R.id.setValue);
        setVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Consumables");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Data d = new Data();
                            d.Key = ds.getKey();
                            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Consumables");
                            mRef.child(d.Key).setValue("1");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        final IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        scanText = findViewById(R.id.scanText);
        if (scanResult != null)
        {
            int check=0;
            for (int i = 0; i <a.size() ; i++)
                    {
                        Data d;
                        d=a.get(i);
                        if(d.Key.equals(scanResult.getContents()))
                        {
                            check=1;
                            if(d.Value.equals("1"))
                            {
                                String set="Checked";
                                scanText.setText(set);
                                Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_LONG).show();
                                d.Value="0";
                                a.set(i,d);
                                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Consumables");
                                mRef.child(d.Key).setValue("0");
                                break;
                            }
                            else
                            {
                                String set="Already Gotten Food";
                                scanText.setText(set);
                                Toast.makeText(MainActivity.this, "Already Gotten Food", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            if(check==0)
            {
                String node=scanResult.getContents();
                DatabaseReference tRef = FirebaseDatabase.getInstance().getReference().child("Consumables").child(node);
                tRef.setValue("1");
                String set="Checked";
                scanText.setText(set);
                Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_LONG).show();
            }
        }
    }
}
