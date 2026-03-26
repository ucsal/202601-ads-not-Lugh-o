package br.com.ucsal.olimpiadas.repository.interfaces;

import java.util.List;

public interface IRepository<T> {
    long getProximoId();
    List<T> getLista();
    void adicionar(T t);
}