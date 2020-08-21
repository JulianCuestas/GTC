package com.nuvu.test.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nuvu.test.business.constants.RutasFront;

@Controller
public class FrontController {
	
	@GetMapping(RutasFront.Home.HOME)
    public String home(Model model) throws Exception {
		model.addAttribute("template", "home");
		return "inicio/index";
    }
	
	@GetMapping(RutasFront.Cliente.CLIENTES)
	public String verClientes(Model model) {
		model.addAttribute("template", "clientes");
		return "inicio/index";
	}
	
	@GetMapping(RutasFront.Tope.TOPES_TC)
	public String verTopesTC(Model model) {
		model.addAttribute("template", "topestc");
		return "inicio/index";
	}
	
	@GetMapping(RutasFront.Entidad.ENTIDADES_BC)
	public String verEntidadesBC(Model model) {
		model.addAttribute("template", "entidadesbc");
		return "inicio/index";
	}

	@GetMapping(RutasFront.Simulador.SIMULADORES)
	public String verSimuladorPagos(Model model) {
		model.addAttribute("template", "simuladores");
		return "inicio/index";
	}
}
