package com.example.steve.hello;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity {

    private EditText etAppName, etAppDesc;
    private String FILENAME = "/storage/emulated/0/AppIdeas.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Toast.makeText(this, "menu clicked ", Toast.LENGTH_LONG).show();
        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.action_add_idea:
                break;
            case R.id.action_refresh:
                readAppData();
                break;
        }

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    public void onClickSave(View view) {
        etAppName = (EditText) findViewById(R.id.edit_app_name);
        etAppDesc = (EditText) findViewById(R.id.edit_app_desc);

        if (isEditTextEmpty(etAppName)) {
            Toast.makeText(this, R.string.toast_err_app_name, Toast.LENGTH_SHORT).show();
        } else if (isEditTextEmpty(etAppDesc)) {
            Toast.makeText(this, R.string.toast_err_app_desc, Toast.LENGTH_SHORT).show();
        } else {

            saveAppData( etAppName.getText().toString() );
            Toast.makeText(this, R.string.toast_app_saved, Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEditTextEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    private void saveAppData(String s) {
        try {
            FileOutputStream f = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            f.write(s.getBytes());
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void readAppData() {
        try {
            FileInputStream f = openFileInput(FILENAME);
            int c;
            String temp = "";
            while ( (c=f.read()) != 1 ) {
                temp = temp + Character.toString((char)c);
            }
            //appName.setText(temp);
            Toast.makeText(this, "read in ", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "read failed ", Toast.LENGTH_SHORT).show();
        }
    }
}
