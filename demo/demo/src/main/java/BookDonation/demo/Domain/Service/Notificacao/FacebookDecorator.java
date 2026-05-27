package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;

public class FacebookDecorator implements Notificador {
    
    private Notificador wrapper;

    public FacebookDecorator(Notificador wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void enviar(Historico log) {
        wrapper.enviar(log);
        
        System.out.println("[FACEBOOK] Publicando notificacao na pagina para o Log #" + log.getId());
    }
}