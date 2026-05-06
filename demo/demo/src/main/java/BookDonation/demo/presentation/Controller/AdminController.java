package BookDonation.demo.presentation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import BookDonation.demo.Domain.Service.*;
import BookDonation.demo.Domain.Model.Admin;
import java.util.Map;

@RestController // MUDOU AQUI: Agora retorna JSON
@RequestMapping("/api/admin") // Sugestão: adicione /api para organizar
@CrossOrigin(origins = "http://localhost:5173") // Libera o React
public class AdminController {

    @Autowired
    private AdminService adminService;

    // O React não precisa do GetMapping /login, pois ele mesmo renderiza a tela

    @PostMapping("/login")
    public ResponseEntity<?> realizarLogin(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String senha = payload.get("senha");
        
        if (adminService.validarAcesso(email, senha)) {
            Admin adminLogado = adminService.buscarPorEmail(email);
            
            // Retorna os dados do admin em vez de redirecionar
            return ResponseEntity.ok(adminLogado);
        }
        
        // Retorna erro 401 (Não autorizado) se falhar
        return ResponseEntity.status(401).body("Email ou senha incorretos!");
    }
}