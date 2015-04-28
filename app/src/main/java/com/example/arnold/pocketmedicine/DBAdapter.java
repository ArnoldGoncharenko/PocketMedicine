package com.example.arnold.pocketmedicine;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arnold on 3/28/2015.
 */
public class DBAdapter {

    private EditText usernameField,passwordField;
    private TextView status,role,method;

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
                                    MySQLiteHelper.GENERIC_NAME,
                                    MySQLiteHelper.TRADE_NAME,
                                    MySQLiteHelper.CLASSIFICATION,
                                    MySQLiteHelper.HIGH_RISK,
                                    MySQLiteHelper.AUTHORIZED,
                                    MySQLiteHelper.AUTH_FIRST_DOSE,
                                    MySQLiteHelper.RPNAPPROP,
                                    MySQLiteHelper.APPROVED_ROUTE,
                                    MySQLiteHelper.SPECIAL_INSTRUCTIONS
    };

    public DBAdapter(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Medicine createMedicine(String genericName, String tradeName, String classification, String highRisk, String authForCommunityAdmin, String authFirstDose, String RPNApprop, String ApprovedRoute, String SpecialInstruct)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.GENERIC_NAME, genericName);
        values.put(MySQLiteHelper.TRADE_NAME, tradeName);
        values.put(MySQLiteHelper.CLASSIFICATION, classification);
        values.put(MySQLiteHelper.HIGH_RISK, highRisk);
        values.put(MySQLiteHelper.AUTHORIZED, authForCommunityAdmin);
        values.put(MySQLiteHelper.AUTH_FIRST_DOSE, authFirstDose);
        values.put(MySQLiteHelper.RPNAPPROP, RPNApprop);
        values.put(MySQLiteHelper.APPROVED_ROUTE, ApprovedRoute);
        values.put(MySQLiteHelper.SPECIAL_INSTRUCTIONS, SpecialInstruct);
        long insertId = database.insert(MySQLiteHelper.TABLE_MEDICINE, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDICINE,allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Medicine newMedicine = cursorToMedicine(cursor);
        cursor.close();
        return newMedicine;
    }

    public void deleteMedicine(Medicine medicine)
    {
        long id = medicine.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_MEDICINE, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Medicine> getAllMedicine() {
        List<Medicine> Medicines = new ArrayList<Medicine>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDICINE,allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Medicine medicine = cursorToMedicine(cursor);
            Medicines.add(medicine);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Medicines;
    }

    private Medicine cursorToMedicine(Cursor cursor)
    {
        Medicine medicine = new Medicine();
        medicine.setId(cursor.getLong(0));
        medicine.setGenericName(cursor.getString(1));
        medicine.setTradeName(cursor.getString(2));
        medicine.setClassification(cursor.getString(3));
        medicine.setHighRisk(cursor.getString(4));
        medicine.setAuthorizedForCommunityAdmin(cursor.getString(5));
        medicine.setAuthorizedForFirstDose(cursor.getString(6));
        medicine.setRPNAppropriate(cursor.getString(7));
        medicine.setApprovedRoute(cursor.getString(8));
        medicine.setSpecialInstructions(cursor.getString(9));
        return medicine;
    }
}