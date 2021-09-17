package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.entity.Medicamento;
import com.empresa.repository.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

	@Autowired
	private MedicamentoRepository medrepository;
	
	@Override
	public Medicamento insertaActualizaMedicamento(Medicamento obj) {
		// TODO Auto-generated method stub
		return medrepository.save(obj);
	}

	@Override
	public List<Medicamento> listaMedicamento() {
		// TODO Auto-generated method stub
		return medrepository.findAll();
	}

	@Override
	public Optional<Medicamento> buscaMedPorId(int idMedicamento) {
		// TODO Auto-generated method stub
		return medrepository.findById(idMedicamento);
	}

	@Override
	public List<Medicamento> buscarPorStock(int stock) {
		// TODO Auto-generated method stub
		return medrepository.findByStock(stock);
	}

	@Override
	public List<Medicamento> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return medrepository.findBynombreContaining(nombre);
	}

	
	
	

}
