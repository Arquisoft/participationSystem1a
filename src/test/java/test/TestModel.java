package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import asw.dbupdate.model.Category;
import asw.dbupdate.model.Comment;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;
import asw.dbupdate.model.Word;

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

	@Test
	public void testCategory() {

		Category c = new Category("categoria");
		assertNull(c.getId());
		assertEquals(c.getSuggestions().size(), 0);
		assertEquals(c.getName(), "categoria");
		assertEquals(c.toString(), "Category [name=categoria, suggestions=[]]");
	}

	@Test
	public void testWord() {

		Word w = new Word("palabra");
		assertNull(w.getId());
		assertEquals(w.getWord(), "palabra");
		w.setWord("hola");
		assertEquals("Word [name=hola]", w.toString());
	}

	@Test
	public void testParticipant() {
		Participant p = new Participant("dani", "duque", "12345", new Date(123), "dani@domain.com", "77777777F",
				"direccion", "español");

		assertNull(p.getId());
		assertEquals(p.getComentarios().size(), 0);
		p.setId(new Long(2));
		p.setNombre("pepe");
		p.setApellidos("lopez");
		p.setFechaNacimiento(new Date(2));
		p.setDNI("34455543E");
		p.setDireccion("calle");
		p.setNacionalidad("aleman");
		p.setPassword("123");
		p.setAdmin(false);
		assertEquals("pepe", p.getNombre());
		assertEquals("lopez", p.getApellidos());
		assertEquals(new Date(2), p.getFechaNacimiento());
		assertEquals("34455543E", p.getDNI());
		assertEquals("calle", p.getDireccion());
		assertEquals("aleman", p.getNacionalidad());
		assertEquals("123", p.getPassword());
		assertEquals(0, p.getPropias().size());
		assertEquals(0, p.getVotComentarios().size());
		assertEquals(0, p.getVotSugerencias().size());
		assertFalse(p.isAdmin());
		assertEquals(p.toString(),
				"Participant [nombre=pepe, apellidos=lopez, fechaNacimiento=Thu Jan 01 01:00:00 CET 1970, email=dani@domain.com, DNI=34455543E, direccion=calle, nacionalidad=aleman]");

	}

}
