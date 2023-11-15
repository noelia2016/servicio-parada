package ps;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import ps.model.ParadaM;
import ps.repository.CustomItemRepository;
import ps.repository.ParadaRepositoryM;

@SpringBootApplication
@EnableMongoRepositories
public class ClaseSpringApplication {

	@Autowired
	ParadaRepositoryM groceryItemRepo;

	@Autowired
	CustomItemRepository customRepo;

	List<ParadaM> itemList = new ArrayList<ParadaM>();

	public static void main(String[] args) {
		SpringApplication.run(ClaseSpringApplication.class, args);
	}

}
