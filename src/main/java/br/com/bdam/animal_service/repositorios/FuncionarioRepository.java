package br.com.bdam.animal_service.repositorios;

import br.com.bdam.animal_service.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
