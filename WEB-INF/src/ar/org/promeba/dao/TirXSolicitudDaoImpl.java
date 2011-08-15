package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.TirXSolicitud;

public class TirXSolicitudDaoImpl implements TirXSolicitudDao {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public TirXSolicitudDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO TIR_X_SOLICITUD (\n");
		sb.append("  TIRXS_ID, \n");
		sb.append("  TIR_ID, \n");
		sb.append("  SOLICITUD_ID, \n");
		sb.append("  TIRXS_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE TIR_X_SOLICITUD  SET \n");
		sb.append("  TIR_ID=?, \n");
		sb.append("  SOLICITUD_ID=?, \n");
		sb.append("  TIRXS_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  TIRXS_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM TIR_X_SOLICITUD   \n");
		sb.append("WHERE  \n");
		sb.append("  TIRXS_ID=? \n");
		sqlBorra=sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TirXSolicitudDao#inserta(ar.org.promeba.beans.TirXSolicitud)
	 */
	@Override
	public void inserta(TirXSolicitud bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getTirId() , bean.getSolicitudId(),
			    bean.getDescripcion()
		  });
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TirXSolicitudDao#modifica(ar.org.promeba.beans.TirXSolicitud)
	 */
	@Override
	public void modifica(TirXSolicitud bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getTirId() , bean.getSolicitudId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class TirXSolicitudMapper implements RowMapper<TirXSolicitud>{
		  public TirXSolicitud mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  TirXSolicitud bean=new TirXSolicitud();
			  bean.setId(resultSet.getString("TIRXS_ID"));
			  bean.setTirId(resultSet.getString("TIR_ID"));
			  bean.setTirNombre(resultSet.getString("TIPO_RIESGO_NOMBRE"));
			  bean.setSolicitudId(resultSet.getString("SOLICITUD_ID"));
			  bean.setDescripcion(resultSet.getString("TIRXS_DESCRIPCION"));
			  return bean;
		  }
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TirXSolicitudDao#seleccionaTirXSolicitud(int, int, java.lang.String)
	 */
	@Override
	public List<TirXSolicitud> seleccionaTirXSolicitud(int offset, int limit, String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.TIRXS_ID,  \n");
		sqlSelect.append(" REL.TIR_ID,  \n");
		sqlSelect.append(" REL.SOLICITUD_ID,  \n");
		sqlSelect.append(" REL.TIRXS_DESCRIPCION,  \n");
		sqlSelect.append(" EQS.TIPO_RIESGO_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" TIR_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN TIPOS_RIESGO EQS ON EQS.TIPO_RIESGO_ID=REL.TIR_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.TIRXS_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new TirXSolicitudMapper());
	}	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TirXSolicitudDao#cuentaTirXSolicitud(java.lang.String)
	 */
	@Override
	public int cuentaTirXSolicitud(String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" TIR_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN TIPOS_RIESGO EQS ON EQS.TIPO_RIESGO_ID=REL.TIR_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.TirXSolicitudDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
