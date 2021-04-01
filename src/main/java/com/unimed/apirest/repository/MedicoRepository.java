package com.unimed.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unimed.apirest.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
		
    List<Medico> findByEspecialidadeId(Long especialidadeId);

    Optional<Medico> findByIdAndEspecialidadeId(Long id, Long especialidadeId);
    
	
}
