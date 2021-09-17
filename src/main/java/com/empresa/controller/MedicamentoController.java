package com.empresa.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/meds")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController { 
	@Autowired
	private MedicamentoService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listarMedicamentos(){
		List<Medicamento> listameds = service.listaMedicamento(); 
		return ResponseEntity.ok(listameds);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertarMeds(@RequestBody Medicamento obj){
		Map<String,Object> salida = new HashMap<>();
		
	
			
			try {
				
				Medicamento objSalida = service.insertaActualizaMedicamento(obj); 
				if (objSalida == null) {
					salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
				}else {
					salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Medicamento> actualizaMedicamento(@RequestBody Medicamento obj){
		if(obj == null) {
			return ResponseEntity.badRequest().build();
		}else {
			Optional<Medicamento> optMedicamento = service.buscaMedPorId(obj.getIdMedicamento()); 
			if(optMedicamento.isPresent()) {
				Medicamento objSalida = service.insertaActualizaMedicamento(obj);
				return ResponseEntity.ok(objSalida);
			}else {
				return ResponseEntity.badRequest().build();
			}
		}
	}
	
	
	@GetMapping("id/{idmed}")
	@ResponseBody
	public ResponseEntity<Medicamento> buscaMedPorId(@PathVariable("idmed") int idMedicamento){
		Optional<Medicamento> optMed = service.buscaMedPorId(idMedicamento);
		if(optMed.isPresent()) {
			return ResponseEntity.ok(optMed.get());
		}else {
			return ResponseEntity.badRequest().build();
		}		
		
	}
	
	@GetMapping("stock/{stk}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>>buscarporStock(@PathVariable("stk") int stock){
		List<Medicamento> lista = service.buscarPorStock(stock);
		if(org.springframework.util.CollectionUtils.isEmpty(lista)) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(lista);
		}
		
	}
	
	@GetMapping("nombre/{nombre}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>>buscarpornombre(@PathVariable ("nombre")String nombre){
		List<Medicamento> lista = service.buscarPorNombre(nombre);
		if(org.springframework.util.CollectionUtils.isEmpty(lista)) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}
	

}
