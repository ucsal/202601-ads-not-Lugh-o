package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;

public class Seeder {
    public final ProvaRepository provaRepository;
    public final QuestaoRepository questaoRepository;
    public final ParticipanteRepository participanteRepository;

    public Seeder(ProvaRepository provaRepository, QuestaoRepository questaoRepository, ParticipanteRepository participanteRepository) {
        this.provaRepository = provaRepository;
        this.questaoRepository = questaoRepository;
        this.participanteRepository = participanteRepository;
    }

    public void seed() {
        Participante participante = new Participante.ParticipanteBuilder()
                .id(participanteRepository.getProximoId())
                .nome("Lucas Falcão")
                .email("email@email.com")
                .build();
        this.participanteRepository.adicionar(participante);

        Prova prova = new Prova.ProvaBuilder()
                .id(provaRepository.getProximoId())
                .titulo("Olimpíada 2026 • Nível 1 • Prova A")
                .build();
        this.provaRepository.adicionar(prova);

        Questao q1 = new Questao.QuestaoBuilder()
                .id(questaoRepository.getProximoId())
                .provaId(prova.getId())
                .enunciado("""
                        Questão 1 — Mate em 1.
                        É a vez das brancas.
                        Encontre o lance que dá mate imediatamente.
                        """)
                .fenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1")
                .alternativas(new String[]{"A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#"})
                .alternativaCorreta('C')
                .build();
        this.questaoRepository.adicionar(q1);
    }
}
