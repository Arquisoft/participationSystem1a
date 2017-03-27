package asw.dbUpdate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TSuggestions")
public class Suggestion {
	@Id
	@GeneratedValue
	public Long id;
	@ManyToMany
	private Set<Participant> participantes = new HashSet<Participant>();
	private int minVotos; // Numero minimo de votos necesarios para que se envie
							// la propuesta al parlamento. Se configura
							// exteriormente
	private int popularidad; // Positivos - Negativos Accedemos aqui
								// directamente pero tenemos que llevar un
								// control de cada tipo de voto
	private int votosPositivos;
	private int votosNegativos;
	private String titulo;
	private String descripcion;
	private Categoria categoria;
	private SuggestionState estado; // El estado de la propuesta

	@OneToMany(mappedBy = "suggestion")
	private Set<Comment> comentarios = new HashSet<Comment>();

	@OneToMany(mappedBy = "suggestion")
	private Set<VoteSuggestion> votSugerencias = new HashSet<VoteSuggestion>();

	Suggestion() {

	}

	public Suggestion(String titulo, String descripcion) {
		this.votosPositivos = 0;
		this.votosNegativos = 0;
		this.popularidad = this.votosPositivos - this.votosNegativos;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMinVotos() {
		return minVotos;
	}

	public void setMinVotos(int minVotos) {
		this.minVotos = minVotos;
	}

	public int getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(int popularidad) {
		this.popularidad = popularidad;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public SuggestionState getEstado() {
		return estado;
	}

	public void setEstado(SuggestionState estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	protected Set<VoteSuggestion> _getVotSugerencias() {
		return votSugerencias;
	}

	public Set<VoteSuggestion> getVotSugerencias() {
		return new HashSet<>(votSugerencias);
	}

	@Override
	public String toString() {
		return "Suggestion [minVotos=" + minVotos + ", popularidad=" + popularidad + ", votosPositivos="
				+ votosPositivos + ", votosNegativos=" + votosNegativos + ", titulo=" + titulo + ", descripcion="
				+ descripcion + ", categoria=" + categoria + ", estado=" + estado + "]";
	}

	protected Set<Participant> _getParticipantes() {
		return participantes;
	}

	protected Set<Comment> _getComentarios() {
		return comentarios;
	}

	public Set<Participant> getParticipantes() {
		return new HashSet<>(participantes);
	}

	public Set<Comment> getComentarios() {
		return new HashSet<>(comentarios);
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
		Suggestion other = (Suggestion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void incrementVotes() {
		this.votosPositivos++;
		updatePopularity();
	}

	public void decrementVotes() {
		this.votosNegativos++;
		updatePopularity();
	}

	private void updatePopularity() {
		this.popularidad = this.votosPositivos - this.votosNegativos;
	}

}
