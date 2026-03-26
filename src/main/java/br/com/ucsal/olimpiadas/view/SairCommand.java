package br.com.ucsal.olimpiadas.view;

import br.com.ucsal.olimpiadas.input.Input;

public class SairCommand extends MenuCommand {
    public SairCommand() {
        super("Sair");
    }

    @Override
    void executar(Input in) {
        System.out.println("Encerrando...");
        System.exit(0);
    }
}
