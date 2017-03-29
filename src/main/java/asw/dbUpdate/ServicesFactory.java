package asw.dbUpdate;

import asw.dbUpdate.impl.GetCommentImpl;
import asw.dbUpdate.impl.GetParticipantImpl;
import asw.dbUpdate.impl.GetSuggestionImpl;
import asw.dbUpdate.impl.SaveCommentImpl;
import asw.dbUpdate.impl.SaveSuggestionImpl;

public class ServicesFactory {

	public static GetSuggestion getSuggestionService() {
		return new GetSuggestionImpl();
	}

	public GetComment getComentService() {
		return new GetCommentImpl();
	}

	public static GetParticipant getParticipantService() {
		return new GetParticipantImpl();
	}

	public static SaveSuggestion saveSuggestionService() {
		return new SaveSuggestionImpl();
	}

	public static SaveComment saveCommentService() {
		return new SaveCommentImpl();
	}
}
