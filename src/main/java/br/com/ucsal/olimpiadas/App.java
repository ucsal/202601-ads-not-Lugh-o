package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.input.ConsoleInput;
import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.menu.*;
import br.com.ucsal.olimpiadas.repository.Store;

import java.util.List;
import java.util.Scanner;

public class App {
    static void main() {
        Store repository = new Store();
        repository.seed();

        Input in = new ConsoleInput(new Scanner(System.in));
        List<MenuCommand> opcoes = List.of(
                new CadastrarParticipanteCommand(repository),
                new CadastrarProvaCommand(repository),
                new CadastrarQuestaoCommand(repository),
                new AplicarProvaCommand(repository),
                new ListarTentativasCommand(repository),
                new SairCommand());
        Menu menu = new Menu(in, opcoes);

        while (true) {
            menu.executar();
        }
    }
}