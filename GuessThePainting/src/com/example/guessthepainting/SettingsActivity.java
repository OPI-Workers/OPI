package com.example.guessthepainting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends Activity {
	  public RadioGroup radioGroup;
	  
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_settings);
	    
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		int selectedId = radioGroup.getCheckedRadioButtonId();
	    
	  }
}
