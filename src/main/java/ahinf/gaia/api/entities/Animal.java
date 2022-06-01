package ahinf.gaia.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Animal")
public class Animal {
    @Id
    private String animalId;
    private String animalType;

    //{"age":0.0,"health":100.0,"stamina":100.0,"speed":0.0,"kid":{"instanceID":17558},"type":1}
}
