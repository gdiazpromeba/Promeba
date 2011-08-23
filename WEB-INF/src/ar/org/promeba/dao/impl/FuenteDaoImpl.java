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

import ar.org.promeba.beans.Fuente;
import ar.org.promeba.dao.FuenteDao;

public class FuenteDaoImpl implements FuenteDao     {
	
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
	
	
	
	public FuenteDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.FUENTE_ID, \n");
		sql.append("  CAR.FUENTE_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  FUENTES CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.FUENTE_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO FUENTES ( \n");
	  sqlInsert.append("  FUENTE_ID, \n");
	  sqlInsert.append("  FUENTE_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE FUENTES SET  \n");
	  sqlActualiza.append("  FUENTE_NOMBRE=? \n");
	  sqlActualiza.append("WHERE FUENTE_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM FUENTES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" FUENTE_ID=?  \n");
	  
	  
	}
	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.FuenteDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.FuenteDao#inserta(ar.org.promeba.beans.Fuente)
	 */
	@Override
	public void inserta(Fuente bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.FuenteDao#actualiza(ar.org.promeba.beans.Fuente)
	 */
	@Override
	public void actualiza(Fuente bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class FuenteMapper implements RowMapper<Fuente>{
		  public Fuente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Fuente bean=new Fuente();
			  bean.setId(resultSet.getString("FUENTE_ID"));
			  bean.setNombre(resultSet.getString("FUENTE_NOMBRE"));
			  return bean;
		  }
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.FuenteDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<Fuente> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.FUENTE_ID, \n");
		sql.append("  CAR.FUENTE_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  FUENTES CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.FUENTE_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.FUENTE_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new FuenteMapper());
	}





	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.FuenteDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  FUENTES CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.FUENTE_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.FuenteDao#obtiene(java.lang.String)
	 */
	@Override
	public Fuente obtiene(String id) {
		Fuente resultado=(Fuente)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new FuenteMapper());
		return resultado;
	}	
	


	

	

}
