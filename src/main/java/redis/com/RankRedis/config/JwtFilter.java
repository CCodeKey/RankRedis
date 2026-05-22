package redis.com.RankRedis.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import redis.com.RankRedis.model.repository.TokenBlackListRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final TokenBlackListRepository tokenBlackListRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        // ROTAS PÚBLICAS
        if (path.equals("/auth/login") || path.equals("/") || path.startsWith("/votar")) {
            filterChain.doFilter(request, response);
            return;
        }
        String auth = request.getHeader("Authorization");

        // sem token
        if (auth == null || !auth.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token não enviado!");
            return;
        }
        String token = auth.replace("Bearer ", "");

        // token blacklistado
        if (tokenBlackListRepository.existsById(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token invalidado!");
            return;
        }

        filterChain.doFilter(request, response);
    }
}