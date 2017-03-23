package asw.dbUpdate.model;

public class Associations {
	// TODO mantener las relaciones entre tablas
	public static class Comentar {
		public static void link(Comment c, Participant p, Suggestion s) {
			c._setUser(p);
			c._setSuggestion(s);
			s._getComentarios().add(c);
			p._getComentarios().add(c);
		}

		public static void unlink(Comment c, Participant p, Suggestion s) {
			p._getComentarios().remove(c);
			s._getComentarios().remove(c);
			c._setUser(null);
			c._setSuggestion(null);
		}
	}

	public static class Apoyar {
		public static void link(Participant p, Suggestion s) {
			
		}

		public static void unlink(Participant p, Suggestion s) {

		}
	}
}
