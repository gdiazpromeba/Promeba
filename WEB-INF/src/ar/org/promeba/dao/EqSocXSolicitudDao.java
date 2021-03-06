package ar.org.promeba.dao;

import java.util.List;

import ar.org.promeba.beans.EqSocXSolicitud;

public interface EqSocXSolicitudDao {

	public abstract void inserta(EqSocXSolicitud bean);

	public abstract void modifica(EqSocXSolicitud bean);

	public abstract List<EqSocXSolicitud> seleccionaEqSocXSolicitud(
			int offset, int limit, String solicitudId);

	public abstract int cuentaEqSocXSolicitud(String solicitudId);

	public abstract void borra(String id);

}