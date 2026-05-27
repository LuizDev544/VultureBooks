package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;

public class WhatsAppDecorator implements Notificador {
    
    private Notificador wrapper;

    public WhatsAppDecorator(Notificador wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void enviar(Historico log) {
        wrapper.enviar(log);
        
        System.out.println("[WHATSAPP] Registrando notificacao para o Log #" + log.getId());
    }
}