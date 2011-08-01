package ar.org.promeba.dao;

import ar.org.promeba.beans.Domicilio;

public interface DomicilioDao {

	public abstract void borra(String id);

	public abstract void inserta(Domicilio bean);

	public abstract void modifica(Domicilio bean);

	public abstract Domicilio obtiene(String id);

}