package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.common.FenUtils;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.interfaces.IQuestaoRepository;
import br.com.ucsal.olimpiadas.repository.interfaces.ITentativaRepository;

import java.util.List;

public class AplicarProvaService {
    private final IQuestaoRepository questaoRepository;
    private final ITentativaRepository tentativaRepository;

    public AplicarProvaService(
            IQuestaoRepository questaoRepository,
            ITentativaRepository tentativaRepository
    ) {
        this.questaoRepository = questaoRepository;
        this.tentativaRepository = tentativaRepository;
    }

    public Tentativa executar(Long participanteId, Long provaId, Input in) {
        List<Questao> questoesDaProva = questaoRepository.getQuestoesPorProva(provaId);

        if (questoesDaProva.isEmpty()) {
            System.out.println("Esta prova não possui questões cadastradas.");
            return new Tentativa.TentativaBuilder()
                    .id(tentativaRepository.getProximoId())
                    .participanteId(participanteId)
                    .provaId(provaId)
                    .build();
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

        return tentativa;
    }
}