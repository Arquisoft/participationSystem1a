package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import asw.dbupdate.model.Comment;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;

public class TestModel {

	@Test
	public void testComment() {

		Participant p = new Participant("dani", "duque", "12345", new Date(123), "dani@domain.com", "77777777F",
				"direccion", "español");
		Suggestion s = new Suggestion("prueba", "test", p);
		Comment c = new Comment("test", p, s);
		c.decrementVotes(2);
		c.incrementVotes(2);
		assertNull(c.getId());
		c.setTexto("test");
		assertEquals(c.getTexto(), "test");
		c.setValoracion(2);
		c.setVotosNegativos(1);
		c.setVotosPositivos(1);
		assertEquals(c.getParticipant(), p);
		assertEquals(c.getSuggestion(), s);
		assertEquals(c.getValoracion(), 2);
		assertEquals(c.getVotosNegativos(), 1);
		assertEquals(c.getVotosPositivos(), 1);
		assertEquals(c.getVotComentarios().size(), 0);
		assertTrue(c.toString()
				.equals("Comment [texto=test, votosPositivos=1, votosNegativos=1, valoracion=2, fechaCreacion="
						+ c.getFechaCreacion() + "]"));
	}

}
