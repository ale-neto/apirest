package com.unimed.apirest.service;

import static com.unimed.apirest.service.ServiceUtil.resourceUri;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.unimed.apirest.exception.ResourceNotFoundException;
import com.unimed.apirest.model.Especialidade;
import com.unimed.apirest.model.Medico;
import com.unimed.apirest.repository.EspecialidadeRepository;
import com.unimed.apirest.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	MedicoRepository medicoRepository;

	@Autowired
	EspecialidadeRepository especialidadeRepository;

	public List<Medico> getAllMedicosByEspecialidadeId(Long especialidadeId) {
		List<Medico> medicos = new ArrayList<>();
		for (Medico medico : medicoRepository.findByEspecialidadeId(especialidadeId))
			if (medico.isAtivo())
				medicos.add(medico);

		if (medicos.isEmpty())
			throw new ResourceNotFoundException("Nenhum resultado encontrado!");
		
		return medicos;
	}

	public ResponseEntity<Medico> createMedico(Long especialidadeId, Medico medicoRequest) {
		return especialidadeRepository.findById(especialidadeId).map(especialidade -> {
			medicoRequest.setEspecialidade(especialidade);
			return medicoRepository.save(medicoRequest);
		}).map(medico -> ResponseEntity.created(resourceUri(medico.getId())).body(medico))
				.orElseThrow(() -> new ResourceNotFoundException("MedicoId " + especialidadeId + " não encontrado"));
	}

	public ResponseEntity<Medico> updateMedico(Long especialidadeId, Long medicoId, Medico medicoRequest) {
		Especialidade especialidade = especialidadeRepository.findById(especialidadeId).orElseThrow(
				() -> new ResourceNotFoundException("especialidadeId " + especialidadeId + " não encontrado"));

		return medicoRepository.findById(medicoId).map(medico -> {
			medico.setNome(medicoRequest.getNome());
			medico.setNascimento(medico.getNascimento());
			medico.setAtivo(medico.isAtivo());
			medico.setEspecialidade(especialidade);
			return medicoRepository.save(medico);
		}).map(medico -> ResponseEntity.ok().location(resourceUri(medico.getId())).body(medico))
				.orElseThrow(() -> new ResourceNotFoundException("medicoId " + medicoId + " não encontrado"));
	}

	public ResponseEntity<?> deleteMedico(Long especialidadeId, Long medicoId) {
		return medicoRepository.findByIdAndEspecialidadeId(medicoId, especialidadeId).map(medico -> {
			medicoRepository.delete(medico);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				"Medico não encontrado com id " + medicoId + " e especialidade Id " + especialidadeId));
	}

}
