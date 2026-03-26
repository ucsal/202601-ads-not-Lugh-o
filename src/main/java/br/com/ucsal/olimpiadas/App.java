package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.factory.MenuFactory;
import br.com.ucsal.olimpiadas.repository.*;
import br.com.ucsal.olimpiadas.repository.interfaces.IParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.interfaces.IProvaRepository;
import br.com.ucsal.olimpiadas.repository.interfaces.IQuestaoRepository;
import br.com.ucsal.olimpiadas.repository.interfaces.ITentativaRepository;
import br.com.ucsal.olimpiadas.view.Menu;

public class App {
    static void main() {
        IParticipanteRepository participanteRepository = new ParticipanteRepository();
        IProvaRepository provaRepository = new ProvaRepository();
        IQuestaoRepository questaoRepository = new QuestaoRepository();
        ITentativaRepository tentativaRepository = new TentativaRepository();

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