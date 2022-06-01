package ahinf.gaia.api;

import ahinf.gaia.api.db.AnimalRepository;
import ahinf.gaia.api.db.UserRepository;
import ahinf.gaia.api.entities.Animal;
import ahinf.gaia.api.entities.User;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
@AnonymousAllowed
public class APIController {

    private static final Logger log = LoggerFactory.getLogger(APIController.class);

    private final AnimalRepository animalrepo;
    private final UserRepository userRepo;

    public APIController(AnimalRepository ar, UserRepository ur) {
        this.animalrepo = ar;
        this.userRepo = ur;
    }

    @ApiResponse(responseCode = "200", description = "Returns the Useremail for a given Username")
    @ApiResponse(responseCode = "400", description = "No user found for given Username")
    @GetMapping("getUsername/{username}")
    public ResponseEntity<String> getUserId(@PathVariable String username) {
        if (!userRepo.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("No user found for given Name");
        }

        List<String> userId =
                userRepo.findAll().stream()
                        .map(User::getEmail)
                        .filter(userUsername -> userUsername.equals(username))
                        .toList();

        return ResponseEntity.ok(userId.get(0));
    }

    @ApiResponse(responseCode = "200", description = "Returns the top 20 scores")
    @GetMapping("/getAnimalTypes/{animalType}")
    public ResponseEntity<String> getTypes(@PathVariable String animalType) {
        var all = animalrepo.findAll();
        List<String> types =
                all.stream()
                        .map(Animal::getAnimalType)
                        .filter(animal -> animalType.equals(animalType))
                        .toList();
        if (types.size() == 0) {
            return ResponseEntity.ok("There are none animals with the type: " + animalType);
        } else if (types.size() == 1)
            return ResponseEntity.ok("Their is " + types.size() + " animal with the type: " + types.get(0));
        return ResponseEntity.ok("Their are " + types.size() + " animals with the type: " + types.get(0) + ".");
    }

    @ApiResponse(responseCode = "200", description = "Saves a new Animal from GAIA in the database (Fields:animalId animalType)")
    @PostMapping("/saveAnimal")
    public ResponseEntity<String> saveAnimal(@RequestBody String json){
        try{
            String[] temp = json.split("%3B");
            String id = temp[0];
            String type = temp[1].substring(0, temp[1].length() - 1);
            log.info("New Animal added..." + id + "," + type);
            Animal newAnimal = new Animal(id, type);
            animalrepo.save(newAnimal);
        }catch(Exception e){
            log.error(String.valueOf(e));

        }
        //ID muss nicht ueberprueft werden,ob es vorhanden ist, da es einen Schluessel namens ID gibt der vergliechen wird
        return ResponseEntity.ok("Animal saved");
    }
    @ApiResponse(responseCode = "200", description = "Saves a new Animal (Fields:animalId animalType)")
    @PostMapping("/savenewAnimalShema")
    public ResponseEntity<String> saveAnimalShema(@RequestBody Animal json){

        animalrepo.save(json);
        //ID muss nicht ueberprueft werden,ob es vorhanden ist, da es einen Schluessel namens ID gibt der vergliechen wird
        return ResponseEntity.ok("Animal saved");
    }


    @ApiResponse(responseCode = "200", description = "Returns all the Animals found in the database")
    @GetMapping("/getAnimals")
    public ResponseEntity<List<Animal>> getAnimals() {
        return ResponseEntity.ok(animalrepo.findAll());
    }

    @ApiResponse(responseCode = "200", description = "Returns all the users found in the database")
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }

}
