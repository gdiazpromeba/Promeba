package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.TipoDocumento;
import ar.org.promeba.dao.TipoDocumentoDao;

public class TipoDocumentoDaoImpl implements TipoDocumentoDao {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlSelect;
	private StringBuffer sqlObtiene;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public TipoDocumentoDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO TIPOS_DOCUMENTO VALUES (?, ?) \n");
	  //selecciona
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" TIPO_DOCUMENTO_ID,  \n");
	  sqlSelect.append(" TIPO_DOCUMENTO_NOMBRE  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" TIPOS_DOCUMENTO  \n");
	  sqlSelect.append("OFFSET ? LIMIT ?  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" TIPO_DOCUMENTO_ID,  \n");
	  sqlObtiene.append(" TIPO_DOCUMENTO_NOMBRE  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" TIPOS_DOCUMENTO  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" TIPO_DOCUMENTO_ID=?  \n");
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE TIPOS_DOCUMENTO SET  \n");
	  sqlActualiza.append(" TIPO_DOCUMENTO_NOMBRE=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" TIPO_DOCUMENTO_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM TIPOS_DOCUMENTO \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" TIPO_DOCUMENTO_ID=?  \n");
	  
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoDocumentoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoDocumentoDao#inserta(ar.org.promeba.beans.TipoDocumento)
	 */
	@Override
	public void inserta(TipoDocumento tipoDocumento) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { tipoDocumento.getId(), tipoDocumento.getNombre()});
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoDocumentoDao#actualiza(ar.org.promeba.beans.TipoDocumento)
	 */
	@Override
	public void actualiza(TipoDocumento tipoDocumento) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { tipoDocumento.getNombre(), tipoDocumento.getId()});
		}	
	
	
	static class TipoDocumentoMapper implements RowMapper<TipoDocumento>{
		  public TipoDocumento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  TipoDocumento bean=new TipoDocumento();
			  bean.setId(resultSet.getString("TIPO_DOCUMENTO_ID"));
			  bean.setNombre(resultSet.getString("TIPO_DOCUMENTO_NOMBRE"));
			  return bean;
		  }
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoDocumentoDao#selecciona(int, int)
	 */
	@Override
	public List<TipoDocumento> selecciona(int offset, int limit) {
		return jdbcTemplate.query(sqlSelect.toString(), new Object[] { offset, limit }, new TipoDocumentoMapper());
	}
	
	public TipoDocumento obtiene(String id) {
		List<TipoDocumento> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new TipoDocumentoMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
