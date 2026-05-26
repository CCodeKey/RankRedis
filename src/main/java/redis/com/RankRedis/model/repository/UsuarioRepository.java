package redis.com.RankRedis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redis.com.RankRedis.model.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    Optional<Usuario> findByUsername(String username);
}