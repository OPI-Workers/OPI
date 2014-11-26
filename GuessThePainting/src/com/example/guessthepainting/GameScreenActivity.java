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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guessthepainting.businesslogic.PictureContext;

public class GameScreenActivity extends Activity implements OnClickListener {

	protected Picture rightAnswer;
	
	protected int rightAnswerPosition;
	
	protected int rightChoosesCounter;
	
	protected int wrongChoosesCounter;
	
	protected int amountOfPictures;
	
	protected int currentRandom;
	
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
	    ImageButton hint = (ImageButton) findViewById(R.id.hint50);
	    hint.setOnClickListener(this);
	    
	    PictureContext context = new PictureContext(this);
		amountOfPictures = context.countPictures();
	    
	    getRound();
	  }
	
	@SuppressWarnings("static-access")
	protected void getRound()
	{
		for (Button b : buttons)
	    {
	        b.setVisibility(View.VISIBLE);
	    }
		
		PictureContext context = new PictureContext(this);
		do
		{
			Random rand = new Random();
			currentRandom = rand.nextInt(amountOfPictures);
		}
		while (playedPictures.contains(currentRandom));
		
		rightAnswer = context.getPictureById(currentRandom);
		
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
		try
		{
			getRound();
		}
		catch (Exception ex)
		{
			String s;
		}
	}
	
	private void getHint50and50()
	{
		boolean done = false;
		int firstHidden = -1;
		while(!done)
		{
			Random rand = new Random();
			int random = rand.nextInt(3);
			if (random != rightAnswerPosition && random != firstHidden)
			{
				buttons.get(random).setVisibility(View.INVISIBLE);
				if (firstHidden == -1)
					firstHidden = random;
				else
					done = true;
			}
		}
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
        case R.id.hint50:
        	this.getHint50and50();
        	// Убрать на релиз
        	//((ImageButton) findViewById(R.id.hint50)).setVisibility(View.INVISIBLE);
        	break;
        default:
          break;
        }
      }
}
