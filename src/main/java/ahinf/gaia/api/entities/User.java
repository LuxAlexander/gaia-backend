package ahinf.gaia.api.entities;

import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
    @Email
    private String email;
    private String username;
    private String passwordHash;

    public void setPasswordHash(String password) {
        password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
    public String getPassword(){
        return this.passwordHash;
    }
}