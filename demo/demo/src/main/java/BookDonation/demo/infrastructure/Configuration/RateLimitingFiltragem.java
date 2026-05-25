package BookDonation.demo.infrastructure.Configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitingFiltragem implements Filter {

    private final Map<String, UserRequestInfo> ipCache = new ConcurrentHashMap<>();
    private static final int MAX_REQUISICOES = 5;
    private static final long TEMPO_LIMITE_MS = TimeUnit.MINUTES.toMillis(1);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ("/admin/login".equals(httpRequest.getRequestURI()) && "POST".equalsIgnoreCase(httpRequest.getMethod())) {
            String ipCliente = httpRequest.getRemoteAddr();
            long agora = System.currentTimeMillis();

            UserRequestInfo info = ipCache.computeIfAbsent(ipCliente, k -> new UserRequestInfo(agora));

            if (agora - info.getTempoInicial() > TEMPO_LIMITE_MS) {
                info.resetar(agora);
            }

            if (info.getContador() >= MAX_REQUISICOES) {
                httpResponse.setStatus(429);
                httpResponse.setContentType("text/html;charset=UTF-8");
                httpResponse.getWriter().write("<h2>Muitas tentativas! Seu acesso foi bloqueado por 1 minuto por seguranca.</h2>");
                return;
            }

            info.incrementar();
        }

        chain.doFilter(request, response);
    }

    private static class UserRequestInfo {
        private long tempoInicial;
        private int contador;

        public UserRequestInfo(long tempoInicial) {
            this.tempoInicial = tempoInicial;
            this.contador = 0;
        }

        public long getTempoInicial() { return tempoInicial; }
        public int getContador() { return contador; }
        
        public void incrementar() { this.contador++; }
        public void resetar(long novoTempo) {
            this.tempoInicial = novoTempo;
            this.contador = 0;
        }
    }
}