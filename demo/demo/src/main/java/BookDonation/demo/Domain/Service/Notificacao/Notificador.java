package BookDonation.demo.Domain.Service.Notificacao;

import BookDonation.demo.Domain.Model.Historico;

public interface Notificador {
    void enviar(Historico log);
}