package com.example.arnold.pocketmedicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;


public class SingleMedicine extends Activity {

    private DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_medicine);

        Intent intent = getIntent();
        final String position = intent.getStringExtra("Name");

        //int finalPosition = Integer.parseInt(position);

        db = new DBAdapter(this);
        db.open();

        String genericname="";
        String tradename="";
        String classification="";
        String highrisk="";
        String authcommadmin="";
        String firstdose="";
        String rpnapprop="";
        String approvedroute="";
        String special="";

        Medicine medicine;
        List<Medicine> c = db.getAllMedicine();

        for (int i=0; i<c.size(); i++)
        {
            medicine = c.get(i);
            if(medicine.getGenericName().equals(position))
            {
                genericname = medicine.getGenericName();
                tradename = medicine.getTradeName();
                classification = medicine.getClassification();
                highrisk = medicine.getHighRisk();
                authcommadmin = medicine.getAuthorizedForCommunityAdmin();
                firstdose = medicine.getAuthorizedForFirstDose();
                rpnapprop = medicine.getRPNAppropriate();
                approvedroute = medicine.getApprovedRoute();
                special = medicine.getSpecialInstructions();
                break;
            }
        }

        TextView t = (TextView)findViewById(R.id.genericname);
        t.setText(genericname);

        t = (TextView)findViewById(R.id.tradename);
        t.setText(tradename);

        t = (TextView)findViewById(R.id.classificationinput);
        t.setText(classification);

        t = (TextView)findViewById(R.id.highrisk);
        t.setText(highrisk);

        t = (TextView)findViewById(R.id.authcommadmin);
        t.setText(authcommadmin);

        t = (TextView)findViewById(R.id.firstdose);
        t.setText(firstdose);

        t = (TextView)findViewById(R.id.rpnapprop);
        t.setText(rpnapprop);

        t = (TextView)findViewById(R.id.approvedroute);
        t.setText(approvedroute);

        t = (TextView)findViewById(R.id.special);
        t.setText(special);

        t.setMovementMethod(new ScrollingMovementMethod());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_medicine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.remove:

                db = new DBAdapter(this);
                db.open();

                Intent incomingIntent = getIntent();
                final String position = incomingIntent.getStringExtra("Name");

                Medicine medicine;
                List<Medicine> c = db.getAllMedicine();

                for (int i=0; i<c.size(); i++)
                {
                    medicine = c.get(i);
                    if(medicine.getGenericName().equals(position))
                    {
                        db.deleteMedicine(medicine);
                        break;
                    }
                }

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
        }
    }
}
