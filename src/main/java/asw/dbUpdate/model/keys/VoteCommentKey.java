package asw.dbUpdate.model.keys;

import java.io.Serializable;

public class VoteCommentKey implements Serializable {

	private static final long serialVersionUID = 1L;
	Long parcipant;
	Long comment;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((parcipant == null) ? 0 : parcipant.hashCode());
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
		VoteCommentKey other = (VoteCommentKey) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (parcipant == null) {
			if (other.parcipant != null)
				return false;
		} else if (!parcipant.equals(other.parcipant))
			return false;
		return true;
	}

}
