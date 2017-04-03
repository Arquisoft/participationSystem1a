package asw.dbupdate.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TComments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String texto;
	private int votosPositivos;
	private int votosNegativos;
	private int valoracion;
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	@ManyToOne
	@JoinColumn(name = "id_participant", referencedColumnName = "id")
	private Participant participant;
	@ManyToOne
	@JoinColumn(name = "id_suggestion", referencedColumnName = "id")
	private Suggestion suggestion;

	@OneToMany(mappedBy = "comment")
	private Set<VoteComment> votComentarios = new HashSet<VoteComment>();

	Comment() {
	}

	public Comment(String texto, Participant participant, Suggestion suggestion) {
		this.texto = texto;
		this.votosPositivos = 0;
		this.votosNegativos = 0;
		this.valoracion = this.votosPositivos - this.votosNegativos;
		this.fechaCreacion = Calendar.getInstance().getTime();
		this.participant = participant;
		this.suggestion = suggestion;
		Associations.Comentar.link(this, participant, suggestion);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Participant getParticipant() {
		return participant;
	}

	protected void _setParticipant(Participant user) {
		this.participant = user;
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

	public int getVotosPositivos() {
		return votosPositivos;
	}

	public void setVotosPositivos(int votosPositivos) {
		this.votosPositivos = votosPositivos;
	}

	public int getVotosNegativos() {
		return votosNegativos;
	}

	public void setVotosNegativos(int votosNegativos) {
		this.votosNegativos = votosNegativos;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	protected Set<VoteComment> _getVotComentarios() {
		return votComentarios;
	}

	public Set<VoteComment> getVotComentarios() {
		return new HashSet<>(votComentarios);
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
		return "Comment [texto=" + texto + ", votosPositivos=" + votosPositivos + ", votosNegativos=" + votosNegativos
				+ ", valoracion=" + valoracion + ", fechaCreacion=" + fechaCreacion + "]";
	}

	public void incrementVotes(int value) {
		if (value == -1)
			this.votosNegativos++;
		else
			this.votosPositivos++;
		this.valoracion = this.votosPositivos - this.votosNegativos;

	}

	public void decrementVotes(int value) {
		if (value == -1)
			this.votosNegativos--;
		else
			this.votosPositivos--;
		this.valoracion = this.votosPositivos - this.votosNegativos;
	}

}
