package com.unimed.apirest.service;

import static com.unimed.apirest.service.ServiceUtil.resourceUri;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimed.apirest.exception.ResourceNotFoundException;
import com.unimed.apirest.model.Especialidade;
import com.unimed.apirest.repository.EspecialidadeRepository;

@RestController
@RequestMapping(value = "/api-especialidade")
public class EspecialidadeService {

	@Autowired
	EspecialidadeRepository especialidadeRepository;

	public Page<Especialidade> getAllEspecialidade(Pageable pageable) {
		return especialidadeRepository.findAll(pageable);
	}

	public ResponseEntity<Especialidade> saveEspecialidade(Especialidade request) {
		return Optional.of(request).map(especialidadeRepository::save)
				.map(especialidade -> ResponseEntity.created(resourceUri(especialidade.getId())).body(especialidade))
				.orElseThrow(IllegalArgumentException::new);
	}

	public ResponseEntity<Especialidade> updateEspecialidade(final Long especialidadeId, Especialidade request) {
		return especialidadeRepository.findById(especialidadeId).map(especialidade -> {
			especialidade.setNome(request.getNome());
			especialidade.setDescricao(request.getDescricao());
			especialidade.setAtivo(request.isAtivo());
			return especialidade;
		}).map(especialidadeRepository::save)
				.map(especialidade -> ResponseEntity.ok().location(resourceUri(especialidadeId)).body(especialidade))
				.orElseThrow(() -> new ResourceNotFoundException("EspecialidadeID " + especialidadeId + " nao encontrado"));
	}

	public ResponseEntity<?> deleteEspecialidade(Long especialidadeId) {
		return especialidadeRepository.findById(especialidadeId).map(especialidade -> {
			especialidadeRepository.delete(especialidade);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("EspecialidadeID " + especialidadeId + " nao encontrado"));
	}
}
