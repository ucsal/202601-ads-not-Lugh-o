package br.com.ucsal.olimpiadas.common;

import br.com.ucsal.olimpiadas.input.Input;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.interfaces.IProvaRepository;

public class ProvaUtils {
    private final IProvaRepository provaRepository;

    public ProvaUtils(IProvaRepository provaRepository) {
        this.provaRepository = provaRepository;
    }

    public int calcularNota(Tentativa tentativa) {
        int acertos = 0;
        for (Resposta r : tentativa.getRespostas()) {
            if (r.isCorreta()) acertos++;
        }
        return acertos;
    }

    public Prova escolherProva(Input in) {
        System.out.println("\nProvas:");
        for (Prova p : provaRepository.getLista()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }
        System.out.print("Escolha o id da prova: ");

        try {
            long id = Long.parseLong(in.nextLine());

            for (Prova p : provaRepository.getLista()) {
                if (p.getId() == id) {
                    return p;
                }
            }
            System.out.println("id inválido");
            return null;

        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }
}
