package ar.org.promeba.dao;

import java.util.Date;
import java.util.List;

import ar.org.promeba.beans.MesaGestion;

public interface MesaGestionDao {

	public abstract void borra(String id);

	public abstract void inserta(MesaGestion bean);

	public abstract void actualiza(MesaGestion bean);

	public abstract List<MesaGestion> selecciona(int start, int limit,
			Date fechaActaAcuerdo, Date fechaMesaGestion, String estado);

	public abstract int cuenta(Date fechaActaAcuerdo, Date fechaMesaGestion,
			String estado);

	public abstract MesaGestion obtiene(String id);

}