package edu.uco.hsung.m02_3startingactivities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button startActivityButton;
    private Button startActionEmailButton;
    private Button startActionBrowserButton;
    private Button startForResultButton;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivityButton = (Button) findViewById(R.id.exact_activity);
        startActionEmailButton = (Button) findViewById(R.id.action_email);
        startActionBrowserButton = (Button) findViewById(R.id.action_browse);
        startForResultButton = (Button) findViewById(R.id.start_for_result);

        startActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // starting a specific activity
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        startActionEmailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // starting an activity implicitly by providing an action type (SEND)
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                String[] recipientArray = {"john@uco.edu", "mary@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipientArray);
                intent.putExtra(Intent.EXTRA_SUBJECT,
                        "This is the subject of the email");
                intent.putExtra(Intent.EXTRA_TEXT,
                        "This is the body of the email.......... blah blah");
                startActivity(intent);
            }
        });

        startActionBrowserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // starting an activity implicitly by providing an action type (BROWSE)
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://cs.uco.edu"));
                startActivity(intent);
            }
        });

        startForResultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // starting an activity which returns a result
                Intent intent = new Intent(MainActivity.this, MultiplyActivity.class);
                EditText num1 = (EditText) findViewById(R.id.num1);
                EditText num2 = (EditText) findViewById(R.id.num2);
                intent.putExtra("NUM1", num1.getText().toString());
                intent.putExtra("NUM2", num2.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || requestCode != REQUEST_CODE) {
            // no result back or request_code does not match
            return;
        }

        String multiplyResult = data.getStringExtra("RESULT");

        TextView resultView = (TextView) findViewById(R.id.result_view);
        Resources res = getResources();
        String text = String.format(res.getString(R.string.product_result), multiplyResult);
        resultView.setText(text);
    }

}
