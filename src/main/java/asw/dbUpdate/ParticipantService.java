package asw.dbUpdate;

import asw.dbUpdate.model.Participant;

public interface ParticipantService {
	public Participant getParticipant(String email, String password);
	void supportSuggestion(Long participant, Long suggestion);
}
