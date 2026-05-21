package redis.com.RankRedis.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.com.RankRedis.model.Candidato;
import redis.com.RankRedis.model.Usuario;
import redis.com.RankRedis.service.CandidatoService;
import redis.com.RankRedis.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UsuarioService usuarioService;
    private CandidatoService candidatoService;
    private StringRedisTemplate redisTemplate;
    private static final String RANKING_KEY = "ranking_eleicao";

    public AdminController(UsuarioService usuarioService, CandidatoService candidatoService, StringRedisTemplate redisTemplate) {
        this.usuarioService = usuarioService;
        this.candidatoService = candidatoService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public String painelAdm() {
        return "Painel administrativo do RankRedis";
    }


    // Candidato
    @PostMapping("/candidato/new")
    public Candidato salvarCandidato(@RequestBody Candidato candidato) {
        return candidatoService.salvar(candidato);
    }

    @GetMapping("/candidatos")
    public List<Candidato> listarCandidatos() {
        return candidatoService.listarTodos();
    }

    @GetMapping("/candidato/{id}")
    public Optional<Candidato> buscarCandidato(@PathVariable String id){
        return candidatoService.buscarPorId(id);
    }

    @DeleteMapping("/candidato/{id}")
    public void deletarCandidato(@PathVariable String id) {
        candidatoService.deletar(id);
    }


    // Usuario
    @PostMapping("/usuario/new")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuario/{id}")
    public Optional<Usuario> buscarUsuario(@PathVariable String id){
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/usuario/{id}")
    public void deletarUsuario(@PathVariable String id){
        usuarioService.deletar(id);
    }


    // Votacao
    @PostMapping("/reset")
    public String resetarEleicao() {
        return candidatoService.resetar();
    }

    // logout
}