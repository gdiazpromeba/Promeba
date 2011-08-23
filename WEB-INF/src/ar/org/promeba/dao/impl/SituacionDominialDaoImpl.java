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

import ar.org.promeba.beans.SituacionDominial;
import ar.org.promeba.dao.SituacionDominialDao;

public class SituacionDominialDaoImpl implements SituacionDominialDao     {
	
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
	
	
	
	public SituacionDominialDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.SIT_DOM_ID, \n");
		sql.append("  CAR.SIT_DOM_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  SITUACIONES_DOMINIALES CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.SIT_DOM_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO SITUACIONES_DOMINIALES ( \n");
	  sqlInsert.append("  SIT_DOM_ID, \n");
	  sqlInsert.append("  SIT_DOM_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE SITUACIONES_DOMINIALES SET  \n");
	  sqlActualiza.append("  SIT_DOM_NOMBRE=? \n");
	  sqlActualiza.append("WHERE SIT_DOM_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM SITUACIONES_DOMINIALES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" SIT_DOM_ID=?  \n");
	  
	  
	}
	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SituacionDominialDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SituacionDominialDao#inserta(ar.org.promeba.beans.SituacionDominial)
	 */
	@Override
	public void inserta(SituacionDominial bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SituacionDominialDao#actualiza(ar.org.promeba.beans.SituacionDominial)
	 */
	@Override
	public void actualiza(SituacionDominial bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class SituacionDominialMapper implements RowMapper<SituacionDominial>{
		  public SituacionDominial mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  SituacionDominial bean=new SituacionDominial();
			  bean.setId(resultSet.getString("SIT_DOM_ID"));
			  bean.setNombre(resultSet.getString("SIT_DOM_NOMBRE"));
			  return bean;
		  }
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SituacionDominialDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<SituacionDominial> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.SIT_DOM_ID, \n");
		sql.append("  CAR.SIT_DOM_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  SITUACIONES_DOMINIALES CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.SIT_DOM_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.SIT_DOM_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new SituacionDominialMapper());
	}






	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SituacionDominialDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  SITUACIONES_DOMINIALES CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.SIT_DOM_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SituacionDominialDao#obtiene(java.lang.String)
	 */
	@Override
	public SituacionDominial obtiene(String id) {
		SituacionDominial resultado=(SituacionDominial)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new SituacionDominialMapper());
		return resultado;
	}	
	


	

	

}
