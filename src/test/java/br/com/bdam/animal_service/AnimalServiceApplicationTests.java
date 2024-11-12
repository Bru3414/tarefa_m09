package br.com.bdam.animal_service;

import br.com.bdam.animal_service.controllers.FuncionarioController;
import br.com.bdam.animal_service.entidades.Animal;
import br.com.bdam.animal_service.entidades.Funcionario;
import br.com.bdam.animal_service.repositorios.AnimalRepository;
import br.com.bdam.animal_service.repositorios.FuncionarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class AnimalServiceApplicationTests {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private AnimalRepository animalRepository;


	@Test
	void contextLoads() {
	}

	@Test
	public void exercicioTest() {
		Funcionario funcionario = createFuncionario("Roberto");

		Funcionario funcionario2 = createFuncionario("Ana");

		Animal animal = createAnimal("CACHORRO", "Spyke", 4, "SRD", Date.valueOf("2024-11-10"),
				"Com fome", funcionario, "MEDIO");

		Animal animal2 = createAnimal("GATO", "Mia", 2, "SRD", Date.valueOf("2024-11-14"),
				"Com frio", funcionario, "PEQUENO");

		Animal animal3 = createAnimal("GATO", "Tico", 6, "SRD", Date.valueOf("2024-08-10"),
				"Com sede", funcionario2, "Pequeno");

		Animal animal4 = createAnimal("CACHORRO", "Lisa", 6, "SRD", Date.valueOf("2024-09-13"),
				"Com ferimentos nas patas", funcionario2, "GRANDE");

		Animal animal5 = createAnimal("CACHORRO", "Thunder", 3, "SRD", Date.valueOf("2024-07-08"),
				"Ofegante", funcionario2, "GRANDE");

		System.out.println(animaisResgatadosPorFunionario());

		Assertions.assertEquals(animal.getId(), 1);

	}

	public String animaisResgatadosPorFunionario() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
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

	public Funcionario createFuncionario(String nome) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);

		return funcionarioRepository.save(funcionario);
	}

	public Animal createAnimal(String tipoAnimal, String nomeProvisorio, Integer idadeEstimada, String raca, Date dataEntrada,
							   String condicoesChegada, Funcionario funcionarioRecebedor, String porte) {
		Animal animal = new Animal();
		animal.setAnimal(tipoAnimal);
		animal.setNomeProvisorio(nomeProvisorio);
		animal.setIdadeEstimada(idadeEstimada);
		animal.setRaca(raca);
		animal.setDataEntrada(dataEntrada);
		animal.setCondicoesChegada(condicoesChegada);
		animal.setFuncionarioRecebedor(funcionarioRecebedor);
		animal.setPorte(porte);

		return animalRepository.save(animal);
	}
}