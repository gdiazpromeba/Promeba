package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.TipoInversion;

public class TipoInversionDaoImpl implements TipoInversionDao  {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	private StringBuffer sqlObtiene;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public TipoInversionDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.TIPO_INVERSION_ID, \n");
		sql.append("  CAR.TIPO_INVERSION_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  TIPOS_INVERSION CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.TIPO_INVERSION_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO TIPOS_INVERSION ( \n");
	  sqlInsert.append("  TIPO_INVERSION_ID, \n");
	  sqlInsert.append("  TIPO_INVERSION_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE TIPOS_INVERSION SET  \n");
	  sqlActualiza.append("  TIPO_INVERSION_NOMBRE=? \n");
	  sqlActualiza.append("WHERE TIPO_INVERSION_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM ESTADO_PROYECTO \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" TIPO_INVERSION_ID=?  \n");
	  
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoInversionDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoInversionDao#inserta(ar.org.promeba.beans.TipoInversion)
	 */
	@Override
	public void inserta(TipoInversion bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoInversionDao#actualiza(ar.org.promeba.beans.TipoInversion)
	 */
	@Override
	public void actualiza(TipoInversion bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class TipoInversionMapper implements RowMapper<TipoInversion>{
		  public TipoInversion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  TipoInversion bean=new TipoInversion();
			  bean.setId(resultSet.getString("TIPO_INVERSION_ID"));
			  bean.setNombre(resultSet.getString("TIPO_INVERSION_NOMBRE"));
			  return bean;
		  }
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoInversionDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<TipoInversion> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.TIPO_INVERSION_ID, \n");
		sql.append("  CAR.TIPO_INVERSION_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  TIPOS_INVERSION CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.TIPO_INVERSION_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.TIPO_INVERSION_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new TipoInversionMapper());
	}




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoInversionDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  TIPOS_INVERSION CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.TIPOS_INVERSION_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}





	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoInversionDao#obtiene(java.lang.String)
	 */
	@Override
	public TipoInversion obtiene(String id) {
		TipoInversion resultado=(TipoInversion)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new TipoInversionMapper());
		return resultado;
	}	
	


	

	

}
