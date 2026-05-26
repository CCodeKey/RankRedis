package redis.com.RankRedis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redis.com.RankRedis.model.Candidato;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends CrudRepository<Candidato, String> {
    Optional<Candidato> findByNome(String nome);
}