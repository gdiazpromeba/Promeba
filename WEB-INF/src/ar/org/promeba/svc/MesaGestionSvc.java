package ar.org.promeba.svc;

import java.util.Date;
import java.util.List;

import ar.org.promeba.beans.MesaGestion;

public interface MesaGestionSvc {

	public abstract List<MesaGestion> selecciona(int desde, int hasta,
			Date fechaActaAcuerdo, Date fechaMesaGestion, String estado);

	public abstract int cuenta(Date fechaActaAcuerdo, Date fechaMesaGestion,
			String estado);

	public abstract MesaGestion obtiene(String id);

	public abstract void inserta(MesaGestion bean);

	public abstract void actualiza(MesaGestion bean);

	public abstract void borra(String id);

}