package redis.com.RankRedis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("token_blacklist")
public class TokenBlackList {

    private static final long serialVersionUID = 1L;

    @Id
    private String token;

    @TimeToLive
    private Long expirationInSeconds;

}
