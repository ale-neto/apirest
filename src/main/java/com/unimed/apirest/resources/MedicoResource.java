package com.unimed.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimed.apirest.model.Medico;
import com.unimed.apirest.service.MedicoService;

@RestController
@RequestMapping(value = "/api-medico")
public class MedicoResource {

	@Autowired
	MedicoService medicoService;
	
	// PEGAR MEDICOS POR ESPECIALIDADES
	@GetMapping("/especialidade/{especialidadeId}/medicos")
	public List<Medico> getAllMedicosByEspecialidadeId(@PathVariable(value = "especialidadeId") Long especialidadeId) {
		return medicoService.getAllMedicosByEspecialidadeId(especialidadeId);
	}
	
	// CADASTRAR NOVOS MEDICOS
	@PostMapping("/especialidade/{especialidadeId}/medicos")
	public ResponseEntity<Medico> createMedico(@PathVariable(value = "especialidadeId") Long especialidadeId,
			@Validated @RequestBody Medico medicoRequest) {
		return medicoService.createMedico(especialidadeId, medicoRequest);
	}
	
	//ALTERAR MEDICOS
	@PutMapping("/especialidade/{especialidadeId}/medicos/{medicoId}")
	public ResponseEntity<Medico> updateMedico(@PathVariable(value = "especialidadeId") Long especialidadeId,
			@PathVariable(value = "medicoId") Long medicoId,
			@Validated @RequestBody Medico medicoRequest) {
		return medicoService.updateMedico(especialidadeId, medicoId, medicoRequest);	
	}

	//DELETAR MEDICOS
	@DeleteMapping("/especialidade/{especialidadeId}/medicos/{medicoId}")
	public ResponseEntity<?> deleteMedico(@PathVariable(value = "especialidadeId") Long especialidadeId,
			@PathVariable(value = "especialidadeId") Long medicoId) {
		return medicoService.deleteMedico(especialidadeId, medicoId);
	}
}
