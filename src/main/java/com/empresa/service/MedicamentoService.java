package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {
	
	public Medicamento insertaActualizaMedicamento(Medicamento obj); 
	public List<Medicamento> listaMedicamento();
	public Optional<Medicamento> buscaMedPorId(int idMedicamento);
	public abstract List<Medicamento> buscarPorStock(int stock);
	public abstract List<Medicamento> buscarPorNombre(String nombre);
	

}
