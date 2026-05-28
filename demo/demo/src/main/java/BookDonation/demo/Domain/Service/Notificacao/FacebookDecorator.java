package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;

public class FacebookDecorator extends NotificadorDecorator {

    public FacebookDecorator(Notificador wrapper) {
        super(wrapper);
    }

    @Override
    public void enviar(Historico log) {
        super.enviar(log);
        
        System.out.println("[FACEBOOK] Publicando notificacao na pagina para o Log #" + log.getId());
    }
}