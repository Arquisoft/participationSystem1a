package asw.dbupdate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import asw.dbupdate.model.keys.VoteSuggestionKey;

@Entity
@Table(name = "TVoteSuggestion")
@IdClass(VoteSuggestionKey.class)
public class VoteSuggestion {
	@Id
	@ManyToOne
	@JoinColumn(name = "id_participant", referencedColumnName = "id")
	private Participant participant;
	@Id
	@ManyToOne
	@JoinColumn(name = "id_suggestion", referencedColumnName = "id")
	private Suggestion suggestion;

	VoteSuggestion() {

	}

	public VoteSuggestion(Participant participant, Suggestion suggestion) {
		Associations.VotarPropuesta.link(participant, this, suggestion);
	}

	public Participant getParticipant() {
		return participant;
	}

	protected void _setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	protected void _setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
		result = prime * result + ((suggestion == null) ? 0 : suggestion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteSuggestion other = (VoteSuggestion) obj;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		if (suggestion == null) {
			if (other.suggestion != null)
				return false;
		} else if (!suggestion.equals(other.suggestion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VoteSuggestion [participant=" + participant.getId() + ", suggestion=" + suggestion.getId() + "]";
	}

}
