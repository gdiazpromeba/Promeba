package ar.org.promeba.svc;

import java.util.List;

import ar.org.promeba.beans.TipoDocumento;

public interface TipoDocumentoSvc {

	public abstract List<TipoDocumento> selecciona(int desde, int hasta);

	public abstract void inserta(TipoDocumento tipoDocumento);

	public abstract void actualiza(TipoDocumento usuario);

	public abstract void borra(String id);
	
	public TipoDocumento obtiene(String id);

}