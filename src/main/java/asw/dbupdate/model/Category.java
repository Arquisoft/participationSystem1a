package asw.dbupdate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCategories")
public class Category {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy = "category")
	private Set<Suggestion> suggestions = new HashSet<Suggestion>();

	Category() {
	}

	public Category(String name) {
		this.name = name;
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", suggestions=" + suggestions + "]";
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public Set<Suggestion> getSuggestions() {
		return new HashSet<Suggestion>(suggestions);
	}

	protected Set<Suggestion> _getSuggestions() {
		return suggestions;
	}

}
