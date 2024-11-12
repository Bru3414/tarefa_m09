package br.com.bdam.animal_service.controllers;

import br.com.bdam.animal_service.entidades.Animal;
import br.com.bdam.animal_service.entidades.Funcionario;
import br.com.bdam.animal_service.repositorios.AnimalRepository;
import br.com.bdam.animal_service.repositorios.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    private List<Funcionario> findAll() {
        return repository.findAll();
    }

    @PostMapping
    private Funcionario create(@RequestBody Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @GetMapping("/animais-resgatados-por-funcionario")
    private String animaisResgatadosPorFunionario() {
        List<Funcionario> funcionarios = repository.findAll();
        List<Animal> animais = animalRepository.findAll();
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < funcionarios.size(); i++) {
            Integer qtd = 0;

            for (int j = 0; j < animais.size(); j++) {
                if (Objects.equals(funcionarios.get(i).getId(), animais.get(j).getFuncionarioRecebedor().getId())) {
                    qtd++;
                }
            }
            sb.append("Funcionário(a) " + funcionarios.get(i).getNome() + " resgatou: " + qtd + " animais." + System.lineSeparator());
        }

        return sb.toString();
    }
}
