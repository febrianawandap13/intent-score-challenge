package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {
    private EditText scoreName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        scoreName = findViewById(R.id.editText);
    }


    public void handleSubmit(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText1 = (EditText) findViewById(R.id.editText2);
        String stringToPassBack = editText.getText().toString();
        String stringToPassBack1 = editText1 .getText().toString();

        // Put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putExtra("keyName", stringToPassBack);
        intent.putExtra("keyminute", stringToPassBack1);
        setResult(RESULT_OK, intent);
        finish();
    }
}
