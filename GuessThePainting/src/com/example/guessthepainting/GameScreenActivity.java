package com.example.guessthepainting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Picture;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guessthepainting.businesslogic.PictureContext;

public class GameScreenActivity extends Activity implements OnClickListener {

	protected Picture rightAnswer;
	
	protected int rightAnswerPosition;
	
	protected int rightChoosesCounter;
	
	protected int wrongChoosesCounter;
	
	protected int amountOfPictures;
	
	protected List<Integer> playedPictures;
	
	protected List<Button> buttons;
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_gamescreen);
	    
	    playedPictures = new ArrayList<Integer>();
	    buttons = new ArrayList<Button>();
	    
	    buttons.add((Button) findViewById(R.id.button5));
	    buttons.add((Button) findViewById(R.id.button6));
	    buttons.add((Button) findViewById(R.id.button7));
	    buttons.add((Button) findViewById(R.id.button8));
	    for (Button b : buttons)
	    {
	        b.setOnClickListener(this);
	    }
	    
	    PictureContext context = new PictureContext(this);
		amountOfPictures = context.countPictures();
	    
	    getRound();
	  }
	
	@SuppressWarnings("static-access")
	protected void getRound()
	{
		PictureContext context = new PictureContext(this);
		do
		{
			Random rand = new Random();
			int random = rand.nextInt(amountOfPictures);
			rightAnswer = context.getPictureById(random);
		}
		while (playedPictures.contains(rightAnswer));

        List<String> wrongAnswers = context.getWrongAnswers(rightAnswer.getId());
		
		Random random = new Random();
		rightAnswerPosition = random.nextInt(4);
		
		buttons.get(rightAnswerPosition).setText(rightAnswer.getAuthor());
		
        for (int i = 1; i < 4; i++)
        {
            buttons.get((i + rightAnswerPosition) % 4).setText(wrongAnswers.get(i-1));
        }
		
        playedPictures.add(rightAnswer.getId());
        
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
       
        Drawable picture = getResources().getDrawable(R.drawable.novojamoskva);
        int resourceID = getResources().getIdentifier(rightAnswer.getFile(), "drawable", getPackageName());
        picture = getResources().getDrawable(resourceID);
        
        imageView.setImageDrawable(picture);
	}
	
	protected void checkAnswer(int checkedAnswerPosition)
	{
		TextView rw = (TextView) findViewById(R.id.rightwrong);
		if (checkedAnswerPosition == rightAnswerPosition)
		{
			rightChoosesCounter++;
			rw.setText("Правильно!");
		}
		else
		{
			rw.setText("Нет, " + rightAnswer.getAuthor());
			wrongChoosesCounter++;
		}
		TextView calc = (TextView) findViewById(R.id.calc);
		calc.setText(rightChoosesCounter + "/" + (rightChoosesCounter + wrongChoosesCounter));
		getRound();
	}
	
	public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button5:
        	checkAnswer(0);
          break;
        case R.id.button6:
        	checkAnswer(1);
          break;
        case R.id.button7:
        	checkAnswer(2);
          break;
        case R.id.button8:
        	checkAnswer(3);
          break;
        default:
          break;
        }
      }
}
