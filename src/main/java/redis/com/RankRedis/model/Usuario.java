package redis.com.RankRedis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("usuario") // Chaves geradas como usuario:id
public class Usuario {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Indexed // Fundamental para o Spring Security achar o usuário pelo login
    private String username;

    private String password;

    private String role; // "ROLE_USER" ou "ROLE_ADMIN"

}
