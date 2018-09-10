package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class CiudadTest extends SpringTest {

	@Test
	@Transactional
	@Rollback(true) // Test 4- Hacer con junit un test que busque todas las ciudades del hemisferio
					// sur
	public void testBusqueCiudadesHemisferioSur() {

		// Declaramos
		Ubicacion ubicacion1, ubicacion2;
		Ciudad ciudad1, ciudad2;
		Pais pais1, pais2;
		Continente continente1, continente2;

		Session session;

		// Inicializamos
		ubicacion1 = new Ubicacion();
		ubicacion2 = new Ubicacion();

		ciudad1 = new Ciudad();
		ciudad2 = new Ciudad();

		pais1 = new Pais();
		pais2 = new Pais();

		continente1 = new Continente();
		continente2 = new Continente();

		session = getSession();
		// session=null;

		// Settear
		ubicacion1.setLatitud(-40);
		ubicacion2.setLatitud(60);

		continente1.setNombre("America");
		continente2.setNombre("Africa");

		pais1.setNombre("Argentina");
		pais2.setNombre("Egipto");
		pais1.setContinente(continente1);
		pais2.setContinente(continente2);

		ciudad1.setPais(pais1);
		ciudad2.setPais(pais2);
		ciudad1.setUbicacionGeografica(ubicacion1);
		ciudad2.setUbicacionGeografica(ubicacion2);

		// Guardar
		session.save(ubicacion1);
		session.save(ubicacion2);
		session.save(continente1);
		session.save(continente2);
		session.save(pais1);
		session.save(pais2);
		session.save(ciudad1);
		session.save(ciudad2);

		List<Ciudad> resultado;
		resultado = session
				.createCriteria(Ciudad.class)
				.createAlias("ubicacionGeografica", "ubi")
				.add(Restrictions.le("ubi.latitud", 0))
				.add(Restrictions.ge("ubi.latitud", -90))
				.list();

		assertTrue(resultado.size() == 1);
	}
}
