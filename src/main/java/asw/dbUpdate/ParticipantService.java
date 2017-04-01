package asw.dbUpdate;

import asw.dbUpdate.model.Participant;

public interface ParticipantService {
	public Participant getParticipant(String email, String password);

	boolean supportSuggestion(Long participant, Long suggestion);

	boolean supportCommentPositive(Long participant, Long comment);

	boolean supportCommentNegative(Long participant, Long comment);
}
