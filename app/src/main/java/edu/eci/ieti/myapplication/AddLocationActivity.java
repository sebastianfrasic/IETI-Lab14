package edu.eci.ieti.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

public class AddLocationActivity extends AppCompatActivity {

    private EditText editNameText;
    private EditText editDescriptionText;
    private EditText editLatitudeText;
    private EditText editLongitudeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        editNameText = findViewById(R.id.name);
        editDescriptionText = findViewById(R.id.description);
        editLatitudeText = findViewById(R.id.latitude);
        editLongitudeText = findViewById(R.id.longitude);
    }

    public void onAddClicked(View view) {
        String name = editNameText.getText().toString();
        String description = editDescriptionText.getText().toString();
        String latitudeText = editLatitudeText.getText().toString();
        String longitudeText = editLongitudeText.getText().toString();
        boolean error = validateField(editNameText, name) || validateField(editDescriptionText, description) || validateField(editLatitudeText, latitudeText) || validateField(editLongitudeText, longitudeText);
        if (!error) {
            double latitude = Double.parseDouble(latitudeText);
            double longitude = Double.parseDouble(longitudeText);
            PersonalizedLocation location = new PersonalizedLocation(name, description, longitude, latitude);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(getString(R.string.response), location);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    private boolean validateField(EditText editText, String text) {
        boolean isValid = false;
        if (text.isEmpty()) {
            editText.setError(getString(R.string.empty_field_error));
            isValid = true;
        }
        return isValid;
    }
}