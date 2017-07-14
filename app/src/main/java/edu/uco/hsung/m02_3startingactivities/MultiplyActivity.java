package edu.uco.hsung.m02_3startingactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MultiplyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiply);

        TextView textview = (TextView) findViewById(R.id.textview);

        // better check if n1 and n2 are not null
        String n1 = getIntent().getStringExtra("NUM1");
        String n2 = getIntent().getStringExtra("NUM2");

        String result;

        try {
            double d1 = Double.parseDouble(n1);
            double d2 = Double.parseDouble(n2);
            result = "" + (d1 * d2);
        } catch (NumberFormatException e) {
            result = "Number Format Exception";
        }

        textview.setText(result);

        // send the result back to the caller activity
        Intent data = new Intent();
        data.putExtra("RESULT", result);
        setResult(RESULT_OK, data);
    }


}
