package com.example.guessthepainting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	Button start_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        start_butt = (Button) findViewById(R.id.start_butt);
        start_butt.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {
        switch (v.getId()) {
        case R.id.start_butt:
        	Intent intent = new Intent(this, ModePickerActivity.class);
            startActivity(intent);
          break;
        default:
          break;
        }
      }
}
