package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Questao;

import java.util.List;

public class QuestaoRepository extends Repository<Questao> {
    public List<Questao> getQuestoesPorProva(long provaId) {
        return this.getLista().stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();
    }
}
