package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;

public abstract class NotificadorDecorator implements Notificador {
    
    protected Notificador wrapper;

    public NotificadorDecorator(Notificador wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void enviar(Historico log) {
        if (wrapper != null) {
            wrapper.enviar(log);
        }
    }
}