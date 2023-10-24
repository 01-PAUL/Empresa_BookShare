package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centroinformacion.entity.Autor;
import com.centroinformacion.entity.Usuario;
import com.centroinformacion.service.AutorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AutorCrudController {

	@Autowired
	private AutorService autorService;
	
	
	@ResponseBody
	@GetMapping("/consultaCrudAutor")
	public List<Autor> consulta(String filtro){
		 List<Autor> lstSalida = autorService.listPorNombreYApellidoLike("%"+filtro+"%");
		 return lstSalida;
	}
	
	
	@ResponseBody
	@PostMapping("/registraCrudAutor")
	public Map<?, ?> registra(Autor obj , HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Usuario objUsuario = (Usuario)session.getAttribute("objUsuario");
		obj.setEstado(1);
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());
		obj.setUsuarioRegistro(objUsuario);
		obj.setUsuarioActualiza(objUsuario);
		
		
		Autor objSalida = autorService.registraAutor(obj);
		if (objSalida == null) {
			map.put("mensaje", "Error en el registro");
		} else {
			List<Autor> lista = autorService.listPorNombreYApellidoLike("%");
			map.put("lista", lista);
			map.put("mensaje", "Registro exitoso");
		}
		return map;
	}
}
