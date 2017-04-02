package asw.dbUpdate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import asw.dbUpdate.model.keys.VoteCommentKey;

@Entity
@Table(name = "TVoteComment")
@IdClass(VoteCommentKey.class)
public class VoteComment {
	@Id
	@ManyToOne
	@JoinColumn(name = "id_participant", referencedColumnName = "id")
	private Participant participant;
	@Id
	@ManyToOne
	@JoinColumn(name = "id_comment", referencedColumnName = "id")
	private Comment comment;

	VoteComment() {

	}

	public VoteComment(Participant participant, Comment comment) {
		// setTipoVoto(tipoVoto);
		Associations.VotarComentario.link(participant, this, comment);
	}

	public Participant getParticipant() {
		return participant;
	}

	protected void _setParticipant(Participant p) {
		this.participant = p;
	}

	public Comment getComment() {
		return comment;
	}

	protected void _setComment(Comment comment) {
		this.comment = comment;
	}

	// public void setTipoVoto(int tipoVoto) {
	// this.tipoVoto = tipoVoto;
	// }
	//
	// public int getTipoVoto() {
	// return tipoVoto;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
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
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VoteComment [participant=" + participant.getId() + ", comment=" + comment.getId() + "]";
	}

}
