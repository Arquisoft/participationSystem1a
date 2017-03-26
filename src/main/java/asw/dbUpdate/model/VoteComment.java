package asw.dbUpdate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TVoteComment")
public class VoteComment {
	@Id
	@GeneratedValue
	public long id;
	
	@ManyToOne
	private Participant participant;
	@ManyToOne
	private Comment comment;

	VoteComment(){
		
	}
	
	public VoteComment(Participant participant, Comment comment) {
		Associations.VotarComentario.link(participant, this, comment);
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

	// Creo que este no se debería usar
	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public void _setParticipant(Participant p) {
		this.participant = participant;
	}


	public Comment getComment() {
		return comment;
	}

	// Creo que este no se debería utilizar
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public void _setComment(Comment comment) {
		this.comment = comment;
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
		VoteComment other = (VoteComment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "VoteComment [participant=" + participant + ", comment=" + comment + "]";
	}


	
}
