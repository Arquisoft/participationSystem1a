package asw.dbUpdate.model;

public class Associations {

	public static class Crear {
		public static void link(Participant p, Suggestion s) {
			s._setCreator(p);
			p._getPropias().add(s);
		}

		public static void unlink(Participant p, Suggestion s) {
			p._getPropias().remove(s);
			s._setCreator(null);
		}
	}

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

	public static class VotarComentario {
		public static void link(Participant p, VoteComment v, Comment c) {
			v._setParticipant(p);
			v._setComment(c);

			p._getVotComentarios().add(v);
			c._getVotComentarios().add(v);
		}

		public static void unlink(Participant p, VoteComment v, Comment c) {
			p._getVotComentarios().remove(v);
			c._getVotComentarios().remove(v);

			v._setParticipant(null);
			v._setComment(null);
		}
	}

	public static class VotarPropuesta {
		public static void link(Participant p, VoteSuggestion v, Suggestion s) {
			v._setParticipant(p);
			v._setSuggestion(s);

			p._getVotSugerencias().add(v);
			s._getVotSugerencias().add(v);
		}

		public static void unlink(Participant p, VoteSuggestion v, Suggestion s) {
			p._getVotSugerencias().remove(v);
			s._getVotSugerencias().remove(v);

			v._setParticipant(null);
			v._setSuggestion(null);
		}
	}
}
