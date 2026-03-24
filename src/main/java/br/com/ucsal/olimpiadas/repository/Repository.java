package br.com.ucsal.olimpiadas.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Repository<T> {
    private long proximoId = 1;
    private final List<T> lista = new ArrayList<>();

    public long getProximoId() {
        return this.proximoId++;
    }

    public List<T> getLista() {
        return Collections.unmodifiableList(this.lista);
    }

    public void adicionar(T t) {
        this.lista.add(t);
    }
}
