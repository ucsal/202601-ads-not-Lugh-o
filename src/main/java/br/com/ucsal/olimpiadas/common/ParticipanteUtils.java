package br.com.ucsal.olimpiadas.common;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;

public class ParticipanteUtils {
    public final ParticipanteRepository participanteRepository;

    public ParticipanteUtils(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public Long escolherParticipante(Input in) {
        System.out.println("\nParticipantes:");
        for (Participante p : participanteRepository.getLista()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = participanteRepository.getLista().stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("Id inválido.");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            return null;
        }
    }
}
