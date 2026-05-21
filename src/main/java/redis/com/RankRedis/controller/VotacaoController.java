package redis.com.RankRedis.controller;


import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;
import redis.com.RankRedis.service.CandidatoService;

import java.util.Set;

@RestController
@RequestMapping("/votar")
public class VotacaoController {
    private final CandidatoService candidatoService;

    public VotacaoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping("/{id}")
    public void votar(@PathVariable String id) {
        candidatoService.votar(id);
    }

    @GetMapping("/ranking")
    public Set<ZSetOperations.TypedTuple<String>> ranking() {
        return candidatoService.ranking();
    }

}