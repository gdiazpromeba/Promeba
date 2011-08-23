package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.ServicioPublicoDisponible;
import ar.org.promeba.dao.ServicioPublicoDisponibleDao;

public class ServicioPublicoDisponibleDaoImpl implements ServicioPublicoDisponibleDao    {
	
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
	
	
	
	public ServicioPublicoDisponibleDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.SEPUDI_ID, \n");
		sql.append("  CAR.SEPUDI_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  SERVICIOS_PUBLICOS_DISPONIBLES CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.SEPUDI_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO SERVICIOS_PUBLICOS_DISPONIBLES ( \n");
	  sqlInsert.append("  SEPUDI_ID, \n");
	  sqlInsert.append("  SEPUDI_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE SERVICIOS_PUBLICOS_DISPONIBLES SET  \n");
	  sqlActualiza.append("  SEPUDI_NOMBRE=? \n");
	  sqlActualiza.append("WHERE SEPUDI_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM SERVICIOS_PUBLICOS_DISPONIBLES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" SEPUDI_ID=?  \n");
	  
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ServicioPublicoDisponibleDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ServicioPublicoDisponibleDao#inserta(ar.org.promeba.beans.ServicioPublicoDisponible)
	 */
	@Override
	public void inserta(ServicioPublicoDisponible bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ServicioPublicoDisponibleDao#actualiza(ar.org.promeba.beans.ServicioPublicoDisponible)
	 */
	@Override
	public void actualiza(ServicioPublicoDisponible bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class ServicioPublicoDisponibleMapper implements RowMapper<ServicioPublicoDisponible>{
		  public ServicioPublicoDisponible mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  ServicioPublicoDisponible bean=new ServicioPublicoDisponible();
			  bean.setId(resultSet.getString("SEPUDI_ID"));
			  bean.setNombre(resultSet.getString("SEPUDI_NOMBRE"));
			  return bean;
		  }
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ServicioPublicoDisponibleDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<ServicioPublicoDisponible> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.SEPUDI_ID, \n");
		sql.append("  CAR.SEPUDI_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  SERVICIOS_PUBLICOS_DISPONIBLES CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.SEPUDI_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.SEPUDI_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new ServicioPublicoDisponibleMapper());
	}




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ServicioPublicoDisponibleDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  SERVICIOS_PUBLICOS_DISPONIBLES CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.SEPUDI_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ServicioPublicoDisponibleDao#obtiene(java.lang.String)
	 */
	@Override
	public ServicioPublicoDisponible obtiene(String id) {
		ServicioPublicoDisponible resultado=(ServicioPublicoDisponible)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new ServicioPublicoDisponibleMapper());
		return resultado;
	}	
	


	

	

}
