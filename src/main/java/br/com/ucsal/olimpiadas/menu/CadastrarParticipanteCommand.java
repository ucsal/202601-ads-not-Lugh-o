package br.com.ucsal.olimpiadas.menu;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;

public class CadastrarParticipanteCommand extends MenuCommand {
    private final ParticipanteRepository participanteRepository;

    public CadastrarParticipanteCommand(ParticipanteRepository participanteRepository) {
        super("Cadastrar participante");
        this.participanteRepository = participanteRepository;
    }

    @Override
    void executar(Input in) {
        System.out.print("Nome: ");
        String nome = in.nextLine();
        System.out.print("Email (opcional): ");
        String email = in.nextLine();

        try {
            Participante p = new Participante.ParticipanteBuilder()
                    .id(participanteRepository.getProximoId())
                    .nome(nome)
                    .email(email)
                    .build();
            participanteRepository.adicionar(p);
            System.out.println("Participante cadastrado: " + p.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar participante: " + e.getMessage());
        }
    }
}
