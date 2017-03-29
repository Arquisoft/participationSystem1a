package asw.dbUpdate;

import asw.dbUpdate.impl.CategoryServiceImpl;
import asw.dbUpdate.impl.CommentServiceImpl;
import asw.dbUpdate.impl.ParticipantServiceImpl;
import asw.dbUpdate.impl.SuggestionServiceImpl;

public class ServicesFactory {

	public static CommentService getCommentService(){
		return new CommentServiceImpl();
	}
	
	public static SuggestionService getSuggestionService(){
		return new SuggestionServiceImpl();
	}
	
	public static ParticipantService getParticipantService(){
		return new ParticipantServiceImpl();
	}
	
	public static CategoryService getCategoryService(){
		return new CategoryServiceImpl();
	}
	
}
