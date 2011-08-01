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

import ar.org.promeba.beans.TipoRiesgo;

public class TipoRiesgoDaoImpl implements TipoRiesgoDao {
	
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
	
	
	
	public TipoRiesgoDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.TIPO_RIESGO_ID, \n");
		sql.append("  CAR.TIPO_RIESGO_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  TIPOS_RIESGO CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.TIPO_RIESGO_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO TIPOS_RIESGO ( \n");
	  sqlInsert.append("  TIPO_RIESGO_ID, \n");
	  sqlInsert.append("  TIPO_RIESGO_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE TIPOS_RIESGO SET  \n");
	  sqlActualiza.append("  TIPO_RIESGO_NOMBRE=? \n");
	  sqlActualiza.append("WHERE TIPO_RIESGO_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM ESTADO_PROYECTO \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" TIPO_RIESGO_ID=?  \n");
	  
	  
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoRiesgoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoRiesgoDao#inserta(ar.org.promeba.beans.TipoRiesgo)
	 */
	@Override
	public void inserta(TipoRiesgo bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoRiesgoDao#actualiza(ar.org.promeba.beans.TipoRiesgo)
	 */
	@Override
	public void actualiza(TipoRiesgo bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class TipoRiesgoMapper implements RowMapper<TipoRiesgo>{
		  public TipoRiesgo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  TipoRiesgo bean=new TipoRiesgo();
			  bean.setId(resultSet.getString("TIPO_RIESGO_ID"));
			  bean.setNombre(resultSet.getString("TIPO_RIESGO_NOMBRE"));
			  return bean;
		  }
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoRiesgoDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<TipoRiesgo> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.TIPO_RIESGO_ID, \n");
		sql.append("  CAR.TIPO_RIESGO_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  TIPOS_RIESGO CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.TIPO_RIESGO_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.TIPO_RIESGO_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new TipoRiesgoMapper());
	}




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoRiesgoDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  TIPOS_RIESGO CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.TIPOS_RIESGO_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}





	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TipoRiesgoDao#obtiene(java.lang.String)
	 */
	@Override
	public TipoRiesgo obtiene(String id) {
		TipoRiesgo resultado=(TipoRiesgo)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new TipoRiesgoMapper());
		return resultado;
	}	
	


	

	

}
