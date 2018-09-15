package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorOperacion {
	
	@RequestMapping("/operacion/{operacionDeseada}/{cadenaRecibida}")
	public ModelAndView realizarOperacion(@PathVariable("operacionDeseada") String operacionDeseada, 
									@PathVariable("cadenaRecibida") String cadenaRecibida
									) {
		
		ModelMap modelo = new ModelMap();
		modelo.put("operacionDeseada", operacionDeseada);
		modelo.put("cadenaRecibida", cadenaRecibida);
		
		if(operacionDeseada.equalsIgnoreCase("pasarAMayuscula")) {
			String resultado = cadenaRecibida.toUpperCase();
			modelo.put("resultado", resultado);
			return new ModelAndView("resultado-operacion", modelo);
		}
		
		if(operacionDeseada.equalsIgnoreCase("pasarAMinuscula")) {
			String resultado = cadenaRecibida.toLowerCase();
			modelo.put("resultado", resultado);
			return new ModelAndView("resultado-operacion", modelo);
		}
		
		if(operacionDeseada.equalsIgnoreCase("invertirOrden")) {
			String resultado = "";
			for (int x=cadenaRecibida.length()-1;x>=0;x--) {
				resultado = resultado + cadenaRecibida.charAt(x);
			}
			modelo.put("resultado", resultado);
			return new ModelAndView("resultado-operacion", modelo);
		}
		
		if(operacionDeseada.equalsIgnoreCase("cantidadDeCaracteres")) {
			Integer resultado = cadenaRecibida.length();
			modelo.put("resultado", resultado);
			return new ModelAndView("resultado-operacion", modelo);
		}
		
		//Si la operación indicada no existe, muestro pantalla de error
		return new ModelAndView("operacion-invalida", modelo);
}

}
