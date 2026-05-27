package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;
import BookDonation.demo.Domain.Service.EmailService;

public class EmailDecorator implements Notificador {
    
    private Notificador wrapper;
    private EmailService emailService;
    private String emailDestino;

    public EmailDecorator(Notificador wrapper, EmailService emailService, String emailDestino) {
        this.wrapper = wrapper;
        this.emailService = emailService;
        this.emailDestino = emailDestino;
    }

    @Override
    public void enviar(Historico log) {
        wrapper.enviar(log);
        
        System.out.println("[E-MAIL] Enviando e-mail formatado para: " + emailDestino);
        
        String texto = "Olá!\n\n"
                     + "Uma nova ação foi registrada no sistema:\n\n"
                     + "Ação: " + log.getAcao() + "\n"
                     + "ID do Livro: " + log.getIdLivro() + "\n"
                     + "Data: " + log.getDataHora() + "\n\n"
                     + "Atenciosamente,\n"
                     + "Equipe Vulture Books";
        
        emailService.enviarEmailSimples(emailDestino, "Alerta de Auditoria - Log #" + log.getId(), texto);
    }
}