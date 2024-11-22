package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")

public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    //@RequestMapping(value = "/listarpacientes", method = RequestMethod.GET)
    @GetMapping
    public Page<DadosListagemPaciente> listar(Pageable pagination){
        return repository.findAll(pagination).map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaInformacoes(dados);
    }


}
