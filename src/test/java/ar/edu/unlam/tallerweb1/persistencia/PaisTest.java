package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.SpringTest;

public class PaisTest extends SpringTest {

	// Test 1- Hacer con junit un test que busque todos los países de habla inglesa.
	@Test
	@Transactional
	@Rollback
	public void testBusquePaisesHablaInglesa() {

		// Declaramos
		Pais pais1, pais2;
		Session session;

		// Inicializamos
		pais1 = new Pais();
		pais2 = new Pais();
		session = getSession();

		// Settear
		pais1.setNombre("Argentina");
		pais2.setNombre("Inglaterra");
		pais1.setIdioma("Espaniol");
		pais2.setIdioma("Ingles");

		// Guardar
		session.save(pais1);
		session.save(pais2);

		List<Pais> resultado;
		resultado = session.createCriteria(Pais.class)
				.add(Restrictions.eq("idioma", "Ingles"))
				.list();

		assertTrue(resultado.size() == 1);
	}
	
	// Test 2- Hacer con junit un test que busque todos los países del continente europeo.
	@Test
	@Transactional
	@Rollback
	public void testBusquePaisesContinenteEuropeo() {

		// Declaramos
		Pais pais1, pais2;
		Continente continente1, continente2;
		Session session;

		// Inicializamos
		pais1 = new Pais();
		pais2 = new Pais();
		continente1 = new Continente();
		continente2 = new Continente();
		session = getSession();

		// Settear
		continente1.setNombre("America");
		continente2.setNombre("Europa");
		pais1.setNombre("Argentina");
		pais2.setNombre("Alemania");
		pais1.setContinente(continente1);
		pais2.setContinente(continente2);

		// Guardar
		session.save(pais1);
		session.save(pais2);
		session.save(continente1);
		session.save(continente2);

		List<Pais> resultado;
		resultado = session.createCriteria(Pais.class)
				.createAlias("continente", "cont")
				.add(Restrictions.eq("cont.nombre", "Europa"))
				.list();

		assertTrue(resultado.size() == 1);
	}
	
	// Test 3- Hacer con junit un test que busque todos los países cuya capital están al norte del trópico de cáncer.
	@Test
	@Transactional
	@Rollback
	public void testBusquePaisesCapitalNorte() {

		// Declaramos
		Pais pais1, pais2;
		Ciudad ciudad1, ciudad2, ciudad3;
		Ubicacion ubicacion1, ubicacion2, ubicacion3;
		Session session;

		// Inicializamos
		pais1 = new Pais();
		pais2 = new Pais();
		ciudad1 = new Ciudad();
		ciudad2 = new Ciudad();
		ciudad3 = new Ciudad();
		ubicacion1 = new Ubicacion();
		ubicacion2 = new Ubicacion();
		ubicacion3 = new Ubicacion();
		session = getSession();

		// Settear
		pais1.setNombre("Canada");
		pais2.setNombre("Panama");

		pais1.setCapital(ciudad1);
		pais2.setCapital(ciudad2);
		ubicacion1.setLatitud(40);
		ubicacion2.setLatitud(15);

		ciudad1.setNombre("Otawa");
		ciudad2.setNombre("PanamaViejo");
		ciudad3.setNombre("Panama");
		ciudad1.setPais(pais1);
		ciudad2.setPais(pais2);
		ciudad3.setPais(pais2);
		ciudad1.setUbicacionGeografica(ubicacion1);
		ciudad2.setUbicacionGeografica(ubicacion2);
		ciudad3.setUbicacionGeografica(ubicacion3);

		// Guardar
		session.save(ubicacion1);
		session.save(ubicacion2);
		session.save(pais1);
		session.save(pais2);
		session.save(ciudad1);
		session.save(ciudad2);

		List<Pais> resultado;
		resultado = session.createCriteria(Pais.class)
				.createAlias("capital", "cap")
				.createAlias("cap.ubicacionGeografica", "ubi")
				.add(Restrictions.le("ubi.latitud", 90))
				.add(Restrictions.ge("ubi.latitud", 30))
				.list();

		assertTrue(resultado.size() == 1);
	}

}
