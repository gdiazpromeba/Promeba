package ar.org.promeba.oad;

import java.util.List;

import ar.org.promeba.beans.TipoDocumento;

public interface TipoDocumentoDao {

	public abstract void borra(String id);

	public abstract void inserta(TipoDocumento tipoDocumento);

	public abstract void actualiza(TipoDocumento tipoDocumento);

	public abstract List<TipoDocumento> selecciona(int offset, int limit);
	
	public TipoDocumento obtiene(String id);

}