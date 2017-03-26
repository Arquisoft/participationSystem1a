package asw.dbUpdate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TVoteSuggestion")
public class VoteSuggestion {
	@Id
	@GeneratedValue
	public long id;
	@ManyToOne
	private Participant participant;
	@ManyToOne
	private Suggestion suggestion;
	
	VoteSuggestion() {
		
	}

	public VoteSuggestion(Participant participant, Suggestion suggestion) {
		Associations.VotarPropuesta.link(participant, this, suggestion);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Participant getParticipant() {
		return participant;
	}

	// Creo que este no debería estar
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}
	
	public void _setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	// Creo que este no debería estar
	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public void _setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VoteSuggestion [participant=" + participant + ", suggestion=" + suggestion + "]";
	}
	
}
