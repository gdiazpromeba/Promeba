package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.SpdXSolicitud;



public class SpdXSolicitudDaoImpl implements SpdXSolicitudDao  {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public SpdXSolicitudDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO SPD_X_SOLICITUD (\n");
		sb.append("  SPDXS_ID, \n");
		sb.append("  SPD_ID, \n");
		sb.append("  SOLICITUD_ID, \n");
		sb.append("  SPDXS_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE SPD_X_SOLICITUD  SET \n");
		sb.append("  SPD_ID=?, \n");
		sb.append("  SOLICITUD_ID=?, \n");
		sb.append("  SPDXS_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  SPDXS_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM SPD_X_SOLICITUD   \n");
		sb.append("WHERE  \n");
		sb.append("  SPDXS_ID=? \n");
		sqlBorra=sb.toString();
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXSolicitudDao#inserta(ar.org.promeba.beans.SpdXSolicitud)
	 */
	@Override
	public void inserta(SpdXSolicitud bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getSpdId() , bean.getSolicitudId(),
			    bean.getDescripcion()
		  });
	}
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXSolicitudDao#modifica(ar.org.promeba.beans.SpdXSolicitud)
	 */
	@Override
	public void modifica(SpdXSolicitud bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getSpdId() , bean.getSolicitudId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class SpdXSolicitudMapper implements RowMapper<SpdXSolicitud>{
		  public SpdXSolicitud mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  SpdXSolicitud bean=new SpdXSolicitud();
			  bean.setId(resultSet.getString("SPDXS_ID"));
			  bean.setSpdId(resultSet.getString("SPD_ID"));
			  bean.setSpdNombre(resultSet.getString("SEPUDI_NOMBRE"));
			  bean.setSolicitudId(resultSet.getString("SOLICITUD_ID"));
			  bean.setDescripcion(resultSet.getString("SPDXS_DESCRIPCION"));
			  return bean;
		  }
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXSolicitudDao#seleccionaSpdXSolicitud(int, int, java.lang.String)
	 */
	@Override
	public List<SpdXSolicitud> seleccionaSpdXSolicitud(int offset, int limit, String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.SPDXS_ID,  \n");
		sqlSelect.append(" REL.SPD_ID,  \n");
		sqlSelect.append(" REL.SOLICITUD_ID,  \n");
		sqlSelect.append(" REL.SPDXS_DESCRIPCION,  \n");
		sqlSelect.append(" SPD.SEPUDI_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" SPD_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN SERVICIOS_PUBLICOS_DISPONIBLES SPD ON SPD.SEPUDI_ID=REL.SPD_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.SPDXS_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new SpdXSolicitudMapper());
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXSolicitudDao#cuentaSpdXSolicitud(java.lang.String)
	 */
	@Override
	public int cuentaSpdXSolicitud(String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" SPD_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN SERVICIOS_PUBLICOS_DISPONIBLES SPD ON SPD.SEPUDI_ID=REL.SPD_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SpdXSolicitudDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
