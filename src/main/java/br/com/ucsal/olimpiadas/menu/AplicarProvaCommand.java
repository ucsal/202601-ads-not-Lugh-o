package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.common.CommonUtils;
import br.com.ucsal.olimpiadas.common.FenUtils;
import br.com.ucsal.olimpiadas.common.ParticipanteUtils;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.*;

import java.util.List;

public class AplicarProvaCommand extends MenuCommand {
    private final ParticipanteRepository participanteRepository;
    private final ProvaRepository provaRepository;
    private final TentativaRepository tentativaRepository;
    private final ParticipanteUtils participanteUtils;
    private final QuestaoRepository questaoRepository;
    private final CommonUtils commonUtils;

    public AplicarProvaCommand(
            ParticipanteRepository participanteRepository,
            ProvaRepository provaRepository,
            TentativaRepository tentativaRepository,
            QuestaoRepository questaoRepository
    ) {
        super("Aplicar prova (selecionar participante + prova)");
        this.participanteRepository = participanteRepository;
        this.provaRepository = provaRepository;
        this.tentativaRepository = tentativaRepository;
        this.questaoRepository = questaoRepository;
        this.participanteUtils = new ParticipanteUtils(participanteRepository);
        this.commonUtils = new CommonUtils(provaRepository);
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

        Prova prova = commonUtils.escolherProva(in);
        if (prova == null) return;

        long provaId = prova.getId();

        List<Questao> questoesDaProva = questaoRepository.getQuestoesPorProva(provaId);

        if (questoesDaProva.isEmpty()) {
            System.out.println("Esta prova não possui questões cadastradas.");
            return;
        }

        Tentativa tentativa = new Tentativa.TentativaBuilder()
                .id(tentativaRepository.getProximoId())
                .participanteId(participanteId)
                .provaId(provaId)
                .build();

        System.out.println("\n--- Início da Prova ---");

        for (Questao q : questoesDaProva) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            System.out.println("Posição inicial:");
            FenUtils.imprimirTabuleiroFen(q.getFenInicial());

            for (String alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");

            char marcada;
            try {
                marcada = in.nextLine().trim().toUpperCase().charAt(0);
            } catch (Exception e) {
                marcada = 'X';
            }

            boolean correta = Resposta.verificar(q, marcada);

            Resposta r = new Resposta.RespostaBuilder()
                    .questaoId(q.getId())
                    .alternativaMarcada(marcada)
                    .correta(correta)
                    .build();

            tentativa.adicionarResposta(r);
        }

        tentativaRepository.adicionar(tentativa);

        int nota = commonUtils.calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }
}
