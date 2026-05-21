package BookDonation.demo.Domain.Service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import BookDonation.demo.Domain.Event.LivroAlteradoEvent;

@Service
public class AuditoriaService {
    
    @EventListener
    public void registrarAuditoria(LivroAlteradoEvent event) {
        System.out.println("LOG DE AUDITORIA: O administrador ID " + event.getAdminId() 
                + " realizou a acao [" + event.getAcao() 
                + "] no livro '" + event.getTitulo() 
                + "' (ID: " + event.getLivroId() + ")");
    }
}
