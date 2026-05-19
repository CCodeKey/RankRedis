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
@RedisHash("candidato") // Define a "pasta/prefixo" das chaves no Redis (ex: candidato:1)
public class Candidato {

    private static final long serialVersionUID = 1L;

    @Id // O Spring Data Redis usa isso para gerar ou gerenciar a chave única
    private String id;

    @Indexed // Permite que o Spring busque candidatos pelo nome se necessário
    private String nome;

    private String partido;

    // Mantemos o campo aqui para exibir no Front, sincronizado com o ZSET
    private Long votos = 0L;

}
