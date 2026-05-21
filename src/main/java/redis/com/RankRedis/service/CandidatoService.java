package redis.com.RankRedis.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import redis.com.RankRedis.model.Candidato;
import redis.com.RankRedis.model.repository.CandidatoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CandidatoService {
    private CandidatoRepository candidatoRepository;
    private StringRedisTemplate redisTemplate;
    private static final String RANKING_KEY = "ranking_eleicao";

    public CandidatoService(CandidatoRepository candidatoRepository, StringRedisTemplate redisTemplate) {
        this.candidatoRepository = candidatoRepository;
        this.redisTemplate = redisTemplate;
    }

    public Candidato salvar(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    public List<Candidato> listarTodos() {
        return (List<Candidato>) candidatoRepository.findAll();
    }

    public Optional<Candidato> buscarPorId(String id) {
        return candidatoRepository.findById(id);
    }

    public void deletar(String id) {
        candidatoRepository.deleteById(id);
    }

    public void votar(String candidatoId) {

        Optional<Candidato> optional = candidatoRepository.findById(candidatoId);

        if (optional.isEmpty()) {
            throw new RuntimeException("Candidato não encontrado");
        }

        Candidato candidato = optional.get();

        redisTemplate.opsForZSet().incrementScore(RANKING_KEY, candidato.getNome(), 1);

        candidato.setVotos(candidato.getVotos() + 1);

        candidatoRepository.save(candidato);
    }

    public Set<ZSetOperations.TypedTuple<String>> ranking (){
        return redisTemplate.opsForZSet().reverseRangeWithScores(RANKING_KEY, 0, 9);
    }

    public String resetar() {
        redisTemplate.delete(RANKING_KEY);

        for (Candidato candidato : this.listarTodos()) {
            candidato.setVotos(0L);
            this.salvar(candidato);
        }
        return "Ranking da eleição resetado!";
    }
}
