package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.common.CommonUtils;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

public class ListarTentativasCommand extends MenuCommand {
    private final TentativaRepository tentativaRepository;
    private final CommonUtils commonUtils;

    public ListarTentativasCommand(TentativaRepository tentativaRepository, ProvaRepository provaRepository) {
        super("Listar tentativas (resumo)");
        this.tentativaRepository = tentativaRepository;
        this.commonUtils = new CommonUtils(provaRepository);
    }

    @Override
    void executar(Input in) {
        System.out.println("\n--- Tentativas ---");
        for (Tentativa t : tentativaRepository.getLista()) {
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(),
                    t.getProvaId(),
                    commonUtils.calcularNota(t),
                    t.getRespostas().size()
            );
        }
    }
}
