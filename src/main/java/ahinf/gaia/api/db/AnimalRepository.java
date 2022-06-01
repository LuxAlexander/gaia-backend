package ahinf.gaia.api.db;

import ahinf.gaia.api.entities.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimalRepository extends MongoRepository<Animal, String> {

    Optional<Animal> findAnimalByAnimalId(String aid);

    Optional<Animal> findAnimalsByAnimalType(String atype);


}
