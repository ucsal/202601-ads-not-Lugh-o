package br.com.ucsal.olimpiadas.repository.interfaces;

import br.com.ucsal.olimpiadas.model.Questao;

import java.util.List;

public interface IQuestaoRepository extends IRepository<Questao> {
    List<Questao> getQuestoesPorProva(long provaId);
}
