package interfaces;

import java.util.List;

import models.Picture;

public interface IPictureContext {
	
	public int countPictures();
	
	public List<String> getWrongAnswers(int rightAnswerId);
	
	public Picture getPictureById(int id);
}
