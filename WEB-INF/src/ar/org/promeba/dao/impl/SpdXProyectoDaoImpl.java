package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.SpdXProyecto;
import ar.org.promeba.dao.SpdXProyectoDao;



public class SpdXProyectoDaoImpl implements SpdXProyectoDao   {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public SpdXProyectoDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO SPD_X_PROYECTO (\n");
		sb.append("  SPDXP_ID, \n");
		sb.append("  SPD_ID, \n");
		sb.append("  PROYECTO_ID, \n");
		sb.append("  SPDXP_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE SPD_X_PROYECTO  SET \n");
		sb.append("  SPD_ID=?, \n");
		sb.append("  PROYECTO_ID=?, \n");
		sb.append("  SPDXP_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  SPDXP_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM SPD_X_PROYECTO   \n");
		sb.append("WHERE  \n");
		sb.append("  SPDXP_ID=? \n");
		sqlBorra=sb.toString();
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXProyectoDao#inserta(ar.org.promeba.beans.SpdXProyecto)
	 */
	@Override
	public void inserta(SpdXProyecto bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getSpdId() , bean.getProyectoId(),
			    bean.getDescripcion()
		  });
	}
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXProyectoDao#modifica(ar.org.promeba.beans.SpdXProyecto)
	 */
	@Override
	public void modifica(SpdXProyecto bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getSpdId() , bean.getProyectoId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class SpdXProyectoMapper implements RowMapper<SpdXProyecto>{
		  public SpdXProyecto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  SpdXProyecto bean=new SpdXProyecto();
			  bean.setId(resultSet.getString("SPDXP_ID"));
			  bean.setSpdId(resultSet.getString("SPD_ID"));
			  bean.setSpdNombre(resultSet.getString("SEPUDI_NOMBRE"));
			  bean.setProyectoId(resultSet.getString("PROYECTO_ID"));
			  bean.setDescripcion(resultSet.getString("SPDXP_DESCRIPCION"));
			  return bean;
		  }
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXProyectoDao#seleccionaSpdXProyecto(int, int, java.lang.String)
	 */
	@Override
	public List<SpdXProyecto> seleccionaSpdXProyecto(int offset, int limit, String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.SPDXP_ID,  \n");
		sqlSelect.append(" REL.SPD_ID,  \n");
		sqlSelect.append(" REL.PROYECTO_ID,  \n");
		sqlSelect.append(" REL.SPDXP_DESCRIPCION,  \n");
		sqlSelect.append(" SPD.SEPUDI_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" SPD_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN SERVICIOS_PUBLICOS_DISPONIBLES SPD ON SPD.SEPUDI_ID=REL.SPD_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + solicitudId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.SPDXP_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new SpdXProyectoMapper());
	}	
	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXProyectoDao#cuentaSpdXProyecto(java.lang.String)
	 */
	@Override
	public int cuentaSpdXProyecto(String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" SPD_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN SERVICIOS_PUBLICOS_DISPONIBLES SPD ON SPD.SEPUDI_ID=REL.SPD_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + solicitudId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXProyectoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
