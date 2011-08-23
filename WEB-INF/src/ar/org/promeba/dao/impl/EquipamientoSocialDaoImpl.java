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

import ar.org.promeba.beans.EquipamientoSocial;
import ar.org.promeba.dao.EquipamientoSocialDao;

public class EquipamientoSocialDaoImpl implements EquipamientoSocialDao    {
	
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
	
	
	
	public EquipamientoSocialDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.EQ_SOC_ID, \n");
		sql.append("  CAR.EQ_SOC_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  EQUIPAMIENTOS_SOCIALES CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.EQ_SOC_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO EQUIPAMIENTOS_SOCIALES ( \n");
	  sqlInsert.append("  EQ_SOC_ID, \n");
	  sqlInsert.append("  EQ_SOC_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE EQUIPAMIENTOS_SOCIALES SET  \n");
	  sqlActualiza.append("  EQ_SOC_NOMBRE=? \n");
	  sqlActualiza.append("WHERE EQ_SOC_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM EQUIPAMIENTOS_SOCIALES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" EQ_SOC_ID=?  \n");
	  
	  
	}
	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EquipamientoSocialDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EquipamientoSocialDao#inserta(ar.org.promeba.beans.EquipamientoSocial)
	 */
	@Override
	public void inserta(EquipamientoSocial bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EquipamientoSocialDao#actualiza(ar.org.promeba.beans.EquipamientoSocial)
	 */
	@Override
	public void actualiza(EquipamientoSocial bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class EquipamientoSocialMapper implements RowMapper<EquipamientoSocial>{
		  public EquipamientoSocial mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  EquipamientoSocial bean=new EquipamientoSocial();
			  bean.setId(resultSet.getString("EQ_SOC_ID"));
			  bean.setNombre(resultSet.getString("EQ_SOC_NOMBRE"));
			  return bean;
		  }
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EquipamientoSocialDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<EquipamientoSocial> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.EQ_SOC_ID, \n");
		sql.append("  CAR.EQ_SOC_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  EQUIPAMIENTOS_SOCIALES CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.EQ_SOC_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.EQ_SOC_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new EquipamientoSocialMapper());
	}




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EquipamientoSocialDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  EQUIPAMIENTOS_SOCIALES CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.EQ_SOC_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EquipamientoSocialDao#obtiene(java.lang.String)
	 */
	@Override
	public EquipamientoSocial obtiene(String id) {
		EquipamientoSocial resultado=(EquipamientoSocial)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new EquipamientoSocialMapper());
		return resultado;
	}	
	


	

	

}
