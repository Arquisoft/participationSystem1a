package asw.dbUpdate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TComments")
public class Comment {
	@Id
	@GeneratedValue
	private Long id;
	private String texto;
	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private Participant user;
	@ManyToOne
	@JoinColumn(name = "id_suggestion", referencedColumnName = "id")
	private Suggestion suggestion;

	Comment() {
	}

	public Comment(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Participant getUser() {
		return user;
	}

	protected void _setUser(Participant user) {
		this.user = user;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	protected void _setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [texto=" + texto + ", user=" + user + ", suggestion="
				+ suggestion + "]";
	}

}
