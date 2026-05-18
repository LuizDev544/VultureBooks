package BookDonation.demo.Domain.Service;

import BookDonation.demo.Domain.Model.Admin;
import BookDonation.demo.Domain.Model.ValueObjects.Email;
import BookDonation.demo.Domain.Repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean validarAcesso(String emailDigitado, String senhaDigitada) {
        Optional<Admin> adminOptional = this.adminRepository.findByEmail(new Email(emailDigitado));

        return adminOptional
                .map((Admin admin) -> admin.autenticarComCriptografia(senhaDigitada, this.passwordEncoder))
                .orElse(false);
    }

    public Admin buscarPorEmail(String emailDigitado) {
        return this.adminRepository.findByEmail(new Email(emailDigitado))
                .orElseThrow(() -> new IllegalArgumentException("Administrador nao encontrado no banco de dados."));
    }
}