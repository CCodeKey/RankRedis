package redis.com.RankRedis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("usuario")
public class Usuario {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Indexed
    private String username;

    private String password;

    private String role;

}
