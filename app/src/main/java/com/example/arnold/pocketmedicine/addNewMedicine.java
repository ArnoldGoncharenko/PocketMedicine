package com.example.arnold.pocketmedicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;


public class addNewMedicine extends Activity {

    private DBAdapter dbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medicine);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_medicine, menu);
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
            case R.id.submit:
                EditText inputStream = (EditText)findViewById(R.id.genericnameinput);
                String mGenericName = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.tradenameinput);
                String mTradeName = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.classificationinput);
                String mClassification = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.highriskinput);
                String mHighRisk = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.authforcommadmininput);
                String mAuthForCommadmin = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.inputauthforfirstdose);
                String mFirstDose = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.rpnappropinput);
                String mRpnApprop = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.approverouteinput);
                String mApprovedRoute = inputStream.getText().toString();
                inputStream = (EditText)findViewById(R.id.specialinput);
                String mSpecial = inputStream.getText().toString();

                dbadapter = new DBAdapter(this);
                dbadapter.open();

                dbadapter.createMedicine(mGenericName, mTradeName, mClassification, mHighRisk, mAuthForCommadmin, mFirstDose, mRpnApprop, mApprovedRoute, mSpecial);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
        }
    }

}
