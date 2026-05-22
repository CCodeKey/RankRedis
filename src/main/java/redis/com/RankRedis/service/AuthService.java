package redis.com.RankRedis.service;

import org.springframework.stereotype.Service;
import redis.com.RankRedis.model.TokenBlackList;
import redis.com.RankRedis.model.Usuario;
import redis.com.RankRedis.model.dto.LoginDTO;
import redis.com.RankRedis.model.repository.TokenBlackListRepository;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final TokenBlackListRepository tokenBlackListRepository;
    private UsuarioService usuarioService;

    public AuthService(JwtService jwtService, TokenBlackListRepository tokenBlackListRepository, UsuarioService usuarioService) {
        this.jwtService = jwtService;
        this.tokenBlackListRepository = tokenBlackListRepository;
        this.usuarioService = usuarioService;
    }

    public String loginUser(LoginDTO dto) {
        Usuario user = null;

        if (dto.getPassword().equals("admin") && dto.getUsername().equals("admin")){
            return jwtService.gerarToken(dto.getUsername());
        } else {
            dto.setUsername(dto.getUsername().toLowerCase());
            dto.setPassword(dto.getPassword().toLowerCase());

            for(Usuario u : usuarioService.listarTodos()){
                u.setUsername(u.getUsername().toLowerCase());
                u.setPassword(u.getPassword().toLowerCase());
                if(u.getUsername().equals(dto.getUsername())){
                    user = u;
                    break;
                }
            }
        }
        if (user == null) {
            throw new RuntimeException("Credenciais inválidas");
        }
        return jwtService.gerarToken(dto.getUsername());
    }


    public String logoutUser(String authorization) {
        String token = authorization.replace("Bearer ", "");
        TokenBlackList blacklist = new TokenBlackList();
        blacklist.setToken(token);

        // TTL = 1h
        blacklist.setExpirationInSeconds(3600L);
        tokenBlackListRepository.save(blacklist);
        return "Logout realizado!";
    }
}
