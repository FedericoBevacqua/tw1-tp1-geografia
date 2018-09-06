package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;  //libreria de asserts
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

//todas las clases de persistencia de usuario
public class UsuarioTest extends SpringTest {
	
	@Test @Transactional @Rollback  //rollback toda la info al finalizar el test deja la base de datos como estaba antes
	public void guardarUsuarioTest() {
		Usuario pepe = new Usuario();
		
		pepe.setEmail("pepe@hotmail.com");
		pepe.setPassword("123456");
		
		Session session = getSession();//ahora tiene valor null pepe en id
		session.save(pepe); //devuelve un serializable el ID con el que lo guarda // A pepe le crea un id y te lo devuelve //Aca tiene valor no null el id
		
		//Recibo el id del usuario pepe
		Usuario buscado = session.get(Usuario.class, pepe.getId());
		assertThat(buscado).isNotNull();  //o es equals to = tal id
		
		
	}
	@Test @Transactional @Rollback 
	public void updateUsuarioTest() {
		Usuario pepe = new Usuario();
		
		String email1 = "pepe@hotmail.com";
		
		String email2 = "pepe2@gmail.com";
		
		
		
		pepe.setEmail(email1);
		pepe.setPassword("123456");
		
		Session session = getSession();
		session.save(pepe); 
		
		pepe.setEmail(email2);
		session.update(pepe);
		
		//Recibo el email de PEPE
		Usuario buscado = session.get(Usuario.class, pepe.getId()); //o recibo el getEmail
		
		//Comparo que pepe tenga el email2
		//assertThat(buscado).isEqualTo(pepe.getId());
		
		
		
		
	}
	@Test @Transactional @Rollback 
	public void deleteUsuarioTest() {
		Usuario pepe = new Usuario();
		
		pepe.setEmail("pepe@hotmail.com");
		pepe.setPassword("123456");
		
		Session session = getSession();
		session.save(pepe); 
		
		session.delete(pepe);
		
		//Recibo el id del usuario pepe
		Usuario buscado = session.get(Usuario.class, pepe.getId());
		assertThat(buscado).isNull();
		
		
	}

	
}
