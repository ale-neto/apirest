package com.unimed.apirest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.unimed.apirest.model.Especialidade;
import com.unimed.apirest.service.EspecialidadeService;

@RestController
@RequestMapping(value = "/api-especialidade")
public class EspecialidadeResource {
    
    @Autowired
    EspecialidadeService especialidadeService;
    
    //LISTAR ESPECIALIDADES
    @GetMapping("/especialidades")
    public Page<Especialidade> getAllEspecialidade(Pageable pageable) {
        return especialidadeService.getAllEspecialidade(pageable);
    }
    
    //CADASTRAR ESPECIALIDADES
    @PostMapping("/especialidades")
    public ResponseEntity<Especialidade> saveEspecialidade(@Validated @RequestBody Especialidade request) {
        return especialidadeService.saveEspecialidade(request);
    }

    //ALTERAR ESPECIALIDADES
    @PutMapping("/especialidades/{especialidadeId}")
    public ResponseEntity<Especialidade> updateEspecialidade(@PathVariable final Long especialidadeId,
            @Validated @RequestBody Especialidade request) {
        return especialidadeService.updateEspecialidade(especialidadeId, request);
    }
    
    //DELETAR ESPECIALIDADES
    @DeleteMapping("/especialidades/{especialidadeId}")
    public ResponseEntity<?> deleteEspecialidade(@PathVariable Long especialidadeId) {
        return especialidadeService.deleteEspecialidade(especialidadeId);
    }
}
