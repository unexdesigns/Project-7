package com.stundys.project7;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int SPEECH_REQUEST_CODE = 0;

    private CheckBox checkbox_left;
    private CheckBox checkbox_right;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        author         = findViewById(R.id.author);
        checkbox_left  = findViewById(R.id.checkbox_left);
        checkbox_right = findViewById(R.id.checkbox_right);
    }

    public void listen(View view){
        displaySpeechRecognizer();
    }

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);

            for(String result : results){
                if(result.toLowerCase().contains("left")){
                    toggleChecked(checkbox_left);
                }

                if (result.toLowerCase().contains("right")) {
                    toggleChecked(checkbox_right);
                }

                if (
                        result.toLowerCase().contains("jaundice") ||
                        result.toLowerCase().contains("jonas") ||
                        result.toLowerCase().contains("drumless") ||
                        result.toLowerCase().contains("janice") ||
                        result.toLowerCase().contains("genres") ||
                        result.toLowerCase().contains("john")
                ){
                    if(author.getVisibility() == View.VISIBLE){
                        author.setVisibility(View.INVISIBLE);
                    } else {
                        author.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void toggleChecked(CheckBox checkbox){
        checkbox.setChecked(!checkbox.isChecked());
    }
}
