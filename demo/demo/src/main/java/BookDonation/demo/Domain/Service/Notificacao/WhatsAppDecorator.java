package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;

public class WhatsAppDecorator extends NotificadorDecorator {

    public WhatsAppDecorator(Notificador wrapper) {
        super(wrapper); 
    }

    @Override
    public void enviar(Historico log) {
        super.enviar(log); 
        
        System.out.println("[WHATSAPP] Registrando notificacao para o Log #" + log.getId());
    }
}