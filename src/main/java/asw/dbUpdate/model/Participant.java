package asw.dbUpdate.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TParticipants")
public class Participant {

	@Id
	@GeneratedValue
	private Long id;

	private String nombre;
	private String apellidos;
	private String password;
	private Date fechaNacimiento;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String DNI;
	private String direccion;
	private String nacionalidad;
	@OneToMany(mappedBy = "user")
	private Set<Comment> comentarios = new HashSet<Comment>();
	@ManyToMany(mappedBy = "participantes")
	private Set<Suggestion> sugerencias = new HashSet<Suggestion>();
	
	@OneToMany(mappedBy = "participant")
	private Set<VoteSuggestion> votSugerencias = new HashSet<VoteSuggestion>();
	@OneToMany(mappedBy = "participant")
	private Set<VoteComment> votComentarios = new HashSet<VoteComment>();

	Participant() {
	}

	public Participant(String nombre, String apellidos, String password,
			Date fechaNacimiento, String email, String dNI, String direccion,
			String nacionalidad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.DNI = dNI;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
	}

	public Long getId() {
		return id;
	}

	protected Set<Comment> _getComentarios() {
		return comentarios;
	}

	public Set<Comment> getComentarios() {
		return new HashSet<>(comentarios);
	}

	protected Set<Suggestion> _getSugerencias() {
		return sugerencias;
	}

	public Set<Suggestion> getSugerencias() {
		return new HashSet<>(sugerencias);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDNI() {
		return DNI;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	
	protected Set<VoteComment> _getVotComentarios() {
		return votComentarios;
	}

	public Set<VoteComment> getVotComentarios() {
		return new HashSet<>(votComentarios);
	}

	protected Set<VoteSuggestion> _getVotSugerencias() {
		return votSugerencias;
	}

	public Set<VoteSuggestion> getVotSugerencias() {
		return new HashSet<>(votSugerencias);
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
		Participant other = (Participant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Participant [nombre=" + nombre + ", apellidos=" + apellidos
				+ ", fechaNacimiento=" + fechaNacimiento + ", email=" + email
				+ ", DNI=" + DNI + ", direccion=" + direccion
				+ ", nacionalidad=" + nacionalidad + "]";
	}

}