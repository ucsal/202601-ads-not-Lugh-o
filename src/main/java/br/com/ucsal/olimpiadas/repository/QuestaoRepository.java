package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.repository.interfaces.IQuestaoRepository;

import java.util.List;

public class QuestaoRepository extends Repository<Questao> implements IQuestaoRepository {
    public List<Questao> getQuestoesPorProva(long provaId) {
        return this.getLista().stream()
                .filter(q -> q.getProvaId() == provaId)
                .toList();
    }
}
