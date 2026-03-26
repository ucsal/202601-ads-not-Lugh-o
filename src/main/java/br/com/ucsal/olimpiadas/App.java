package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.factory.MenuFactory;
import br.com.ucsal.olimpiadas.repository.*;
import br.com.ucsal.olimpiadas.view.Menu;

public class App {
    static void main() {
        ParticipanteRepository participanteRepository = new ParticipanteRepository();
        ProvaRepository provaRepository = new ProvaRepository();
        QuestaoRepository questaoRepository = new QuestaoRepository();
        TentativaRepository tentativaRepository = new TentativaRepository();

        Seeder seeder = new Seeder(provaRepository, questaoRepository, participanteRepository);
        seeder.seed();

        Menu menu = MenuFactory.createMenu(
                participanteRepository,
                provaRepository,
                questaoRepository,
                tentativaRepository
        );

        while (true) {
            menu.executar();
        }
    }
}