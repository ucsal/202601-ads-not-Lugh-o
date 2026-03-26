package br.com.ucsal.olimpiadas.view;

import br.com.ucsal.olimpiadas.common.ParticipanteUtils;
import br.com.ucsal.olimpiadas.common.ProvaUtils;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import br.com.ucsal.olimpiadas.service.AplicarProvaService;

public class AplicarProvaCommand extends MenuCommand {
    private final ParticipanteRepository participanteRepository;
    private final ProvaRepository provaRepository;
    private final ParticipanteUtils participanteUtils;
    private final ProvaUtils provaUtils;
    private final AplicarProvaService aplicarProvaService;

    public AplicarProvaCommand(
            ParticipanteRepository participanteRepository,
            ProvaRepository provaRepository,
            TentativaRepository tentativaRepository,
            QuestaoRepository questaoRepository
    ) {
        super("Aplicar prova (selecionar participante + prova)");
        this.participanteRepository = participanteRepository;
        this.provaRepository = provaRepository;
        this.participanteUtils = new ParticipanteUtils(participanteRepository);
        this.provaUtils = new ProvaUtils(provaRepository);
        this.aplicarProvaService = new AplicarProvaService(questaoRepository, tentativaRepository);
    }

    @Override
    void executar(Input in) {
        if (participanteRepository.getLista().isEmpty()) {
            System.out.println("Cadastre participantes primeiro.");
            return;
        }
        if (provaRepository.getLista().isEmpty()) {
            System.out.println("Cadastre provas primeiro.");
            return;
        }

        Long participanteId = participanteUtils.escolherParticipante(in);
        if (participanteId == null) return;

        Prova prova = provaUtils.escolherProva(in);
        if (prova == null) return;

        Tentativa tentativa = aplicarProvaService.executar(participanteId, prova.getId(), in);

        int nota = provaUtils.calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }
}