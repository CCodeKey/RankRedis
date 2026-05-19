package redis.com.RankRedis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redis.com.RankRedis.model.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    // Fundamental para a lógica do Spring Security encontrar o usuário no login
    Optional<Usuario> findByUsername(String username);
}