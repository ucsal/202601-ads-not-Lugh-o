package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.repository.Store;

public class CadastrarParticipanteCommand extends MenuCommand {
    private final Store repository;

    public CadastrarParticipanteCommand(Store repository) {
        super("Cadastrar participante");
        this.repository = repository;
    }

    @Override
    void executar(Input in) {
        System.out.print("Nome: ");
        String nome = in.nextLine();
        System.out.print("Email (opcional): ");
        String email = in.nextLine();

        try {
            Participante p = new Participante.ParticipanteBuilder()
                    .id(repository.getProximoParticipanteId())
                    .nome(nome)
                    .email(email)
                    .build();
            repository.adicionarParticipante(p);
            System.out.println("Participante cadastrado: " + p.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar participante: " + e.getMessage());
        }
    }
}
