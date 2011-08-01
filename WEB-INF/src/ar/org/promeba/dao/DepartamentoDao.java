package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.Departamento;

public interface DepartamentoDao {

	public abstract void borra(String id);

	public abstract void inserta(Departamento bean);

	public abstract void actualiza(Departamento bean);

	public abstract List<Departamento> selecciona(String provinciaId);

	public abstract int cuenta(String provinciaId);

	public abstract Departamento obtiene(String id);

}