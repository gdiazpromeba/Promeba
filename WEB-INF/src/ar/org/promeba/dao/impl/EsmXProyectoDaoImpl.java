package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.EsmXProyecto;
import ar.org.promeba.dao.EsmXProyectoDao;

public class EsmXProyectoDaoImpl implements EsmXProyectoDao  {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public EsmXProyectoDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO ESM_X_PROYECTO (\n");
		sb.append("  ESMXP_ID, \n");
		sb.append("  ESM_ID, \n");
		sb.append("  PROYECTO_ID, \n");
		sb.append("  ESMXP_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE ESM_X_PROYECTO  SET \n");
		sb.append("  ESM_ID=?, \n");
		sb.append("  PROYECTO_ID=?, \n");
		sb.append("  ESMXP_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  ESMXP_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM ESM_X_PROYECTO   \n");
		sb.append("WHERE  \n");
		sb.append("  ESMXP_ID=? \n");
		sqlBorra=sb.toString();
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXProyectoDao#inserta(ar.org.promeba.beans.EsmXProyecto)
	 */
	@Override
	public void inserta(EsmXProyecto bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getEsmId() , bean.getProyectoId(),
			    bean.getDescripcion()
		  });
	}
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXProyectoDao#modifica(ar.org.promeba.beans.EsmXProyecto)
	 */
	@Override
	public void modifica(EsmXProyecto bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getEsmId() , bean.getProyectoId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class EsmXProyectoMapper implements RowMapper<EsmXProyecto>{
		  public EsmXProyecto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  EsmXProyecto bean=new EsmXProyecto();
			  bean.setId(resultSet.getString("ESMXP_ID"));
			  bean.setEsmId(resultSet.getString("ESM_ID"));
			  bean.setEsmNombre(resultSet.getString("ESTADO_MENSURA_NOMBRE"));
			  bean.setProyectoId(resultSet.getString("PROYECTO_ID"));
			  bean.setDescripcion(resultSet.getString("ESMXP_DESCRIPCION"));
			  return bean;
		  }
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXProyectoDao#seleccionaEsmXProyecto(int, int, java.lang.String)
	 */
	@Override
	public List<EsmXProyecto> seleccionaEsmXProyecto(int offset, int limit, String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.ESMXP_ID,  \n");
		sqlSelect.append(" REL.ESM_ID,  \n");
		sqlSelect.append(" REL.PROYECTO_ID,  \n");
		sqlSelect.append(" REL.ESMXP_DESCRIPCION,  \n");
		sqlSelect.append(" EQS.ESTADO_MENSURA_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" ESM_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN ESTADOS_MENSURA EQS ON EQS.ESTADO_MENSURA_ID=REL.ESM_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + solicitudId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.ESMXP_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new EsmXProyectoMapper());
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXProyectoDao#cuentaEsmXProyecto(java.lang.String)
	 */
	@Override
	public int cuentaEsmXProyecto(String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" ESM_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN ESTADOS_MENSURA EQS ON EQS.ESTADO_MENSURA_ID=REL.ESM_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + solicitudId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXProyectoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
