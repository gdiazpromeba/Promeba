package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.EqSocXSolicitud;
import ar.org.promeba.dao.EqSocXSolicitudDao;

public class EqSocXSolicitudDaoImpl implements EqSocXSolicitudDao  {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public EqSocXSolicitudDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO EQ_SOC_X_SOLICITUD (\n");
		sb.append("  ESXS_ID, \n");
		sb.append("  EQ_SOC_ID, \n");
		sb.append("  SOLICITUD_ID, \n");
		sb.append("  ESXS_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE EQ_SOC_X_SOLICITUD  SET \n");
		sb.append("  EQ_SOC_ID=?, \n");
		sb.append("  SOLICITUD_ID=?, \n");
		sb.append("  ESXS_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  ESXS_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM EQ_SOC_X_SOLICITUD   \n");
		sb.append("WHERE  \n");
		sb.append("  ESXS_ID=? \n");
		sqlBorra=sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXSolicitudDao#inserta(ar.org.promeba.beans.EqSocXSolicitud)
	 */
	@Override
	public void inserta(EqSocXSolicitud bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getEqSocId() , bean.getSolicitudId(),
			    bean.getDescripcion()
		  });
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXSolicitudDao#modifica(ar.org.promeba.beans.EqSocXSolicitud)
	 */
	@Override
	public void modifica(EqSocXSolicitud bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getEqSocId() , bean.getSolicitudId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class EqSocXSolicitudMapper implements RowMapper<EqSocXSolicitud>{
		  public EqSocXSolicitud mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  EqSocXSolicitud bean=new EqSocXSolicitud();
			  bean.setId(resultSet.getString("ESXS_ID"));
			  bean.setEqSocId(resultSet.getString("EQ_SOC_ID"));
			  bean.setEqSocNombre(resultSet.getString("EQ_SOC_NOMBRE"));
			  bean.setSolicitudId(resultSet.getString("SOLICITUD_ID"));
			  bean.setDescripcion(resultSet.getString("ESXS_DESCRIPCION"));
			  return bean;
		  }
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXSolicitudDao#seleccionaAreasFuncionalesXRol(int, int, java.lang.String)
	 */
	@Override
	public List<EqSocXSolicitud> seleccionaEqSocXSolicitud(int offset, int limit, String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.ESXS_ID,  \n");
		sqlSelect.append(" REL.EQ_SOC_ID,  \n");
		sqlSelect.append(" REL.SOLICITUD_ID,  \n");
		sqlSelect.append(" REL.ESXS_DESCRIPCION,  \n");
		sqlSelect.append(" EQS.EQ_SOC_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" EQ_SOC_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN EQUIPAMIENTOS_SOCIALES EQS ON EQS.EQ_SOC_ID=REL.EQ_SOC_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.ESXS_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new EqSocXSolicitudMapper());
	}	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXSolicitudDao#cuentaAreasFuncionalesXRol(java.lang.String)
	 */
	@Override
	public int cuentaEqSocXSolicitud(String solicitudId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" EQ_SOC_X_SOLICITUD REL \n");
		sqlSelect.append(" INNER JOIN EQUIPAMIENTOS_SOCIALES EQS ON EQS.EQ_SOC_ID=REL.EQ_SOC_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.SOLICITUD_ID='" + solicitudId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EqSocXSolicitudDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
