package com.example.arnold.pocketmedicine;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


public class MainActivity extends ListActivity
{

    private DBAdapter dbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbadapter = new DBAdapter(this);
        dbadapter.open();

        final List<Medicine> IfExist = dbadapter.getAllMedicine();

        if (IfExist.isEmpty())
        {
            dbadapter.createMedicine("Alemtuzumab", "Mabcampath", "Antineoplastic", "Y", "Y", "N", "N", "IM, SC", "None");
            dbadapter.createMedicine("Amikacin", "Amikin", "Aminoglycoside Antibiotic", "N", "Y", "N", "N", "IV, IM","See Guidelines for Aminoglycoside Antibiotic Monitoring\n" +
                    "Increased fluid intake required.");
            dbadapter.createMedicine("Aminophylline", "Aminophylline", "Bronchodilator", "N", "Y", "N", "N", "IV","None");
            dbadapter.createMedicine("Amiodarone", "Cordarone Pacerone", "Antiarrhythmic", "Y", "Y", "N", "N", "IV","None");
            dbadapter.createMedicine("Amphotericin B", "Fungizone", "Antifungal", "N", "Y", "N", "N", "IV","Consult with CRN prior to administration. Consider shift nursing. \n" +
                    "Special monitoring and preparation required. Ensure informed \n" +
                    "consent has been obtained. Consider client knowledge related to \n" +
                    "the nature of the treatment, expected benefits of the treatment, \n" +
                    "material risks and side effects of the treatment, alternative \n" +
                    "courses of action and likely consequences of not having the \n" +
                    "treatment. Ensure that this drug and this dose are appropriate for \n" +
                    "this specific client. Many common adverse effects. Consider \n" +
                    "advocating for medications such as an antiemetic or aspirin to \n" +
                    "ease drug discomfort.\n" +
                    "Amphotericin B is not compatible with electrolyte containing \n" +
                    "solutions (2/31/3, LR, NS), however, D5W is not recommended \n" +
                    "as a locking solution. This means that the nurse will need to flush \n" +
                    "and check patency with NS, flush with D5W, infuse the \n" +
                    "amphotericin B, flush with D5W and lock the line with NS. An inline membrane filter may be used; however, the mean pore \n" +
                    "diameter of the filter should not be less than 1 micron. This \n" +
                    "medication must be administered by slow IV infusion (2-6 hours) \n" +
                    "via a central line. Infusion pump recommended. \n" +
                    "Check dose; two bags may be required.\n" +
                    "Alert: ensure differientation between Amphotericin B and \n" +
                    "(Liposomal) Amphotericin B – dosing is vastly different.");
        }

        final List<Medicine> values = dbadapter.getAllMedicine();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        //ArrayAdapter<Medicine> adapter = new ArrayAdapter<Medicine>(this, android.R.layout.simple_list_item_2, values);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_2, android.R.id.text1, values)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position,convertView,parent);
                Medicine data = values.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(data.getGenericName());
                text2.setText(data.getTradeName());

                return view;
            }
        };
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Medicine> adapter = (ArrayAdapter<Medicine>) getListAdapter();
        Medicine medicine = null;
        switch (view.getId()) {
            case R.id.add:
                Intent intent = new Intent(this, addNewMedicine.class);
                startActivity(intent);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume()
    {
        dbadapter.open();
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        dbadapter.close();
        super.onPause();
    }

    public void onListItemClick(ListView parent, View v, int position, long id )
    {
        Intent intent = new Intent(this, SingleMedicine.class);
        Medicine val=(Medicine)(parent.getItemAtPosition(position));
        String mainName = val.getGenericName();
        intent.putExtra("Name", mainName);
        startActivity(intent);
    }

}