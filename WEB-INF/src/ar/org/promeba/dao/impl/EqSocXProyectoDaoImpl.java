package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.EqSocXProyecto;
import ar.org.promeba.dao.EqSocXProyectoDao;

public class EqSocXProyectoDaoImpl implements EqSocXProyectoDao   {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public EqSocXProyectoDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO EQ_SOC_X_PROYECTO (\n");
		sb.append("  ESXP_ID, \n");
		sb.append("  EQ_SOC_ID, \n");
		sb.append("  PROYECTO_ID, \n");
		sb.append("  ESXP_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE EQ_SOC_X_PROYECTO  SET \n");
		sb.append("  EQ_SOC_ID=?, \n");
		sb.append("  PROYECTO_ID=?, \n");
		sb.append("  ESXP_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  ESXP_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM EQ_SOC_X_PROYECTO   \n");
		sb.append("WHERE  \n");
		sb.append("  ESXP_ID=? \n");
		sqlBorra=sb.toString();
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXProyectoDao#inserta(ar.org.promeba.beans.EqSocXProyecto)
	 */
	@Override
	public void inserta(EqSocXProyecto bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getEqSocId() , bean.getProyectoId(),
			    bean.getDescripcion()
		  });
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXProyectoDao#modifica(ar.org.promeba.beans.EqSocXProyecto)
	 */
	@Override
	public void modifica(EqSocXProyecto bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getEqSocId() , bean.getProyectoId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class EqSocXProyectoMapper implements RowMapper<EqSocXProyecto>{
		  public EqSocXProyecto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  EqSocXProyecto bean=new EqSocXProyecto();
			  bean.setId(resultSet.getString("ESXP_ID"));
			  bean.setEqSocId(resultSet.getString("EQ_SOC_ID"));
			  bean.setEqSocNombre(resultSet.getString("EQ_SOC_NOMBRE"));
			  bean.setProyectoId(resultSet.getString("PROYECTO_ID"));
			  bean.setDescripcion(resultSet.getString("ESXP_DESCRIPCION"));
			  return bean;
		  }
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXProyectoDao#seleccionaEqSocXProyecto(int, int, java.lang.String)
	 */
	@Override
	public List<EqSocXProyecto> seleccionaEqSocXProyecto(int offset, int limit, String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.ESXP_ID,  \n");
		sqlSelect.append(" REL.EQ_SOC_ID,  \n");
		sqlSelect.append(" REL.PROYECTO_ID,  \n");
		sqlSelect.append(" REL.ESXP_DESCRIPCION,  \n");
		sqlSelect.append(" EQS.EQ_SOC_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" EQ_SOC_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN EQUIPAMIENTOS_SOCIALES EQS ON EQS.EQ_SOC_ID=REL.EQ_SOC_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + solicitudId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.ESXP_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new EqSocXProyectoMapper());
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXProyectoDao#cuentaEqSocXProyecto(java.lang.String)
	 */
	@Override
	public int cuentaEqSocXProyecto(String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" EQ_SOC_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN EQUIPAMIENTOS_SOCIALES EQS ON EQS.EQ_SOC_ID=REL.EQ_SOC_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + solicitudId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXProyectoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
