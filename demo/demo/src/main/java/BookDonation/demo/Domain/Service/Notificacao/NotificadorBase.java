package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;

public class NotificadorBase implements Notificador {
    @Override
    public void enviar(Historico log) {
        System.out.println("Iniciando o disparo de notificacoes para o Log #" + log.getId());
    }
}