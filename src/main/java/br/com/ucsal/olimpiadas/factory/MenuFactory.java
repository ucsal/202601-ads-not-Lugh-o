package br.com.ucsal.olimpiadas.factory;

import br.com.ucsal.olimpiadas.input.ConsoleInput;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.repository.interfaces.IParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.interfaces.IProvaRepository;
import br.com.ucsal.olimpiadas.repository.interfaces.IQuestaoRepository;
import br.com.ucsal.olimpiadas.repository.interfaces.ITentativaRepository;
import br.com.ucsal.olimpiadas.view.*;

import java.util.List;
import java.util.Scanner;

public class MenuFactory {
    public static Menu createMenu(
            IParticipanteRepository participanteRepository,
            IProvaRepository provaRepository,
            IQuestaoRepository questaoRepository,
            ITentativaRepository tentativaRepository
    ) {
        Input in = new ConsoleInput(new Scanner(System.in));
        List<MenuCommand> opcoes = List.of(
                new CadastrarParticipanteCommand(participanteRepository),
                new CadastrarProvaCommand(provaRepository),
                new CadastrarQuestaoCommand(provaRepository, questaoRepository),
                new AplicarProvaCommand(participanteRepository, provaRepository, tentativaRepository, questaoRepository),
                new ListarTentativasCommand(tentativaRepository, provaRepository),
                new SairCommand());

        return new Menu(in, opcoes);
    }
}
