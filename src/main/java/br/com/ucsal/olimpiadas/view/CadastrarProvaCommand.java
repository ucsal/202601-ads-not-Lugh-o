package br.com.ucsal.olimpiadas.view;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.repository.interfaces.IProvaRepository;

public class CadastrarProvaCommand extends MenuCommand {
    private final IProvaRepository provaRepository;

    public CadastrarProvaCommand(IProvaRepository provaRepository) {
        super("Cadastrar prova");
        this.provaRepository = provaRepository;
    }

    @Override
    void executar(Input in) {
        System.out.print("Título da prova: ");
        String titulo = in.nextLine();
        try {
            Prova prova = new Prova.ProvaBuilder()
                    .id(provaRepository.getProximoId())
                    .titulo(titulo)
                    .build();
            provaRepository.adicionar(prova);
            System.out.println("Prova criada: " + prova.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar prova: " + e.getMessage());
        }
    }
}
