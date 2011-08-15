package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.EsmXSolicitud;

public class EsmXSolicitudDaoImpl implements EsmXSolicitudDao {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public EsmXSolicitudDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO ESM_X_SOLICITUD (\n");
		sb.append("  ESMXS_ID, \n");
		sb.append("  ESM_ID, \n");
		sb.append("  SOLICITUD_ID, \n");
		sb.append("  ESMXS_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE ESM_X_SOLICITUD  SET \n");
		sb.append("  ESM_ID=?, \n");
		sb.append("  SOLICITUD_ID=?, \n");
		sb.append("  ESMXS_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  ESMXS_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM ESM_X_SOLICITUD   \n");
		sb.append("WHERE  \n");
		sb.append("  ESMXS_ID=? \n");
		sqlBorra=sb.toString();
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXSolicitudDao#inserta(ar.org.promeba.beans.EsmXSolicitud)
	 */
	@Override
	public void inserta(EsmXSolicitud bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getEsmId() , bean.getSolicitudId(),
			    bean.getDescripcion()
		  });
	}
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXSolicitudDao#modifica(ar.org.promeba.beans.EsmXSolicitud)
	 */
	@Override
	public void modifica(EsmXSolicitud bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getEsmId() , bean.getSolicitudId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class EsmXSolicitudMapper implements RowMapper<EsmXSolicitud>{
		  public EsmXSolicitud mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  EsmXSolicitud bean=new EsmXSolicitud();
			  bean.setId(resultSet.getString("ESMXS_ID"));
			  bean.setEsmId(resultSet.getString("ESM_ID"));
			  bean.setEsmNombre(resultSet.getString("ESTADO_MENSURA_NOMBRE"));
			  bean.setSolicitudId(resultSet.getString("SOLICITUD_ID"));
			  bean.setDescripcion(resultSet.getString("ESMXS_DESCRIPCION"));
			  return bean;
		  }
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXSolicitudDao#seleccionaEsmXSolicitud(int, int, java.lang.String)
	 */
	@Override
	public List<EsmXSolicitud> seleccionaEsmXSolicitud(int offset, int limit, String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.ESMXS_ID,  \n");
		sqlSelect.append(" REL.ESM_ID,  \n");
		sqlSelect.append(" REL.SOLICITUD_ID,  \n");
		sqlSelect.append(" REL.ESMXS_DESCRIPCION,  \n");
		sqlSelect.append(" EQS.ESTADO_MENSURA_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" ESM_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN ESTADOS_MENSURA EQS ON EQS.ESTADO_MENSURA_ID=REL.ESM_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.ESMXS_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new EsmXSolicitudMapper());
	}	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXSolicitudDao#cuentaEsmXSolicitud(java.lang.String)
	 */
	@Override
	public int cuentaEsmXSolicitud(String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" ESM_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN ESTADOS_MENSURA EQS ON EQS.ESTADO_MENSURA_ID=REL.ESM_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EsmXSolicitudDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
