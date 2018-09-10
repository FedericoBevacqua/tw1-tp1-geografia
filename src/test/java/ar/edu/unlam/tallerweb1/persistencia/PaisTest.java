package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class PaisTest {

	@Test @Transactional @Rollback //Test 1- Hacer con junit un test que busque todos los países de habla inglesa.
	public void testBusquePaisesHablaInglesa() {

	}
	
	@Test @Transactional @Rollback //Test 2- Hacer con junit un test que busque todos los países del continente europeo.
	public void testBusquePaisesContinenteEuropeo() {

	}

	
	@Test @Transactional @Rollback //Test 3- Hacer con junit un test que busque todos los países cuya capital están al norte del trópico de cáncer.
	public void testBusquePaisesCapitalNorte() {

	}

}
