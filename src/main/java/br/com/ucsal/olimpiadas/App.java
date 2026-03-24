package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.input.ConsoleInput;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.menu.*;
import br.com.ucsal.olimpiadas.repository.*;

import java.util.List;
import java.util.Scanner;

public class App {
    static void main() {
        ParticipanteRepository participanteRepository = new ParticipanteRepository();
        ProvaRepository provaRepository = new ProvaRepository();
        QuestaoRepository questaoRepository = new QuestaoRepository();
        TentativaRepository tentativaRepository = new TentativaRepository();

        Seeder repository = new Seeder(
                new ProvaRepository(),
                new QuestaoRepository()
        );

        repository.seed();

        Input in = new ConsoleInput(new Scanner(System.in));
        List<MenuCommand> opcoes = List.of(
                new CadastrarParticipanteCommand(participanteRepository),
                new CadastrarProvaCommand(provaRepository),
                new CadastrarQuestaoCommand(provaRepository, questaoRepository),
                new AplicarProvaCommand(participanteRepository, provaRepository, tentativaRepository, questaoRepository),
                new ListarTentativasCommand(tentativaRepository, provaRepository),
                new SairCommand());
        Menu menu = new Menu(in, opcoes);

        while (true) {
            menu.executar();
        }
    }
}