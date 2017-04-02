package asw.dbUpdate.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TSuggestions")
public class Suggestion {
	public static int DIAS_ABIERTA = 7;
	public static final int MIN_VOTOS_DEFECTO = 100;
	@Id
	@GeneratedValue
	public Long id;
	private int minVotos; // Numero minimo de votos necesarios para que se envie
							// la propuesta al parlamento. Se configura
							// exteriormente
	private int popularidad;
	private int votosPositivos;
	@ManyToOne
	@JoinColumn(name = "id_creator", referencedColumnName = "id")
	private Participant creator;
	private String titulo;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	@Temporal(TemporalType.DATE)
	private Date fecha_fin;
	@ManyToOne
	@JoinColumn(name = "id_category", referencedColumnName = "id")
	private Category category;
	@Enumerated(EnumType.STRING)
	private SuggestionState estado; // El estado de la propuesta

	@OneToMany(mappedBy = "suggestion")
	private Set<Comment> comentarios = new HashSet<Comment>();

	@OneToMany(mappedBy = "suggestion")
	private Set<VoteSuggestion> votSugerencias = new HashSet<VoteSuggestion>();

	Suggestion() {

	}

	public Suggestion(String titulo, String descripcion, Participant creator) {
		this.votosPositivos = 0;
		this.popularidad = this.votosPositivos;
		this.titulo = titulo;
		this.descripcion = descripcion;
		Associations.Crear.link(creator, this);
	}

	public Suggestion(String titulo, String descripcion, Participant creator, Category category) {

		this.votosPositivos = 0;
		this.creator = creator;
		this.popularidad = this.votosPositivos;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha_creacion = Calendar.getInstance().getTime();
		this.estado = SuggestionState.BuscandoApoyo;
		this.category = category;
		this.minVotos = MIN_VOTOS_DEFECTO;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, DIAS_ABIERTA); // Pone la fecha de finalización
											// DIAS_ABIERTA mas tarde
		this.fecha_fin = c.getTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getMinVotos() {
		return minVotos;
	}

	public Participant getCreator() {
		return creator;
	}

	protected void _setCreator(Participant creator) {
		this.creator = creator;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
				+ votosPositivos + ", titulo=" + titulo + ", descripcion=" + descripcion + ", categoria="
				+ category.getId() + ", estado=" + estado + "]";
	}

	protected Set<Comment> _getComentarios() {
		return comentarios;
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
		this.popularidad++;
	}

	public void reduceVotes() {
		this.votosPositivos--;
		this.popularidad--;
	}
}
