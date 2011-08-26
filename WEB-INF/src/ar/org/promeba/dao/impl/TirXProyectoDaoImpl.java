package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.TirXProyecto;
import ar.org.promeba.dao.TirXProyectoDao;


public class TirXProyectoDaoImpl implements TirXProyectoDao  {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public TirXProyectoDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO TIR_X_PROYECTO (\n");
		sb.append("  TIRXS_ID, \n");
		sb.append("  TIR_ID, \n");
		sb.append("  PROYECTO_ID, \n");
		sb.append("  TIRXS_DESCRIPCION \n");
		sb.append(") VALUES (?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE TIR_X_PROYECTO  SET \n");
		sb.append("  TIR_ID=?, \n");
		sb.append("  PROYECTO_ID=?, \n");
		sb.append("  TIRXS_DESCRIPCION=? \n");
		sb.append("WHERE  \n");
		sb.append("  TIRXS_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM TIR_X_PROYECTO   \n");
		sb.append("WHERE  \n");
		sb.append("  TIRXS_ID=? \n");
		sqlBorra=sb.toString();
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.impl.TirXProyectoDao#inserta(ar.org.promeba.beans.TirXProyecto)
	 */
	@Override
	public void inserta(TirXProyecto bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { 
			    bean.getId(), bean.getTirId() , bean.getProyectoId(),
			    bean.getDescripcion()
		  });
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.impl.TirXProyectoDao#modifica(ar.org.promeba.beans.TirXProyecto)
	 */
	@Override
	public void modifica(TirXProyecto bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			    bean.getTirId() , bean.getProyectoId(),
			    bean.getDescripcion(), 
			    bean.getId()
		  });
	}	
	

	static class TirXProyectoMapper implements RowMapper<TirXProyecto>{
		  public TirXProyecto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  TirXProyecto bean=new TirXProyecto();
			  bean.setId(resultSet.getString("TIRXS_ID"));
			  bean.setTirId(resultSet.getString("TIR_ID"));
			  bean.setTirNombre(resultSet.getString("TIPO_RIESGO_NOMBRE"));
			  bean.setProyectoId(resultSet.getString("PROYECTO_ID"));
			  bean.setDescripcion(resultSet.getString("TIRXS_DESCRIPCION"));
			  return bean;
		  }
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.impl.TirXProyectoDao#seleccionaTirXProyecto(int, int, java.lang.String)
	 */
	@Override
	public List<TirXProyecto> seleccionaTirXProyecto(int offset, int limit, String proyectoId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" REL.TIRXS_ID,  \n");
		sqlSelect.append(" REL.TIR_ID,  \n");
		sqlSelect.append(" REL.PROYECTO_ID,  \n");
		sqlSelect.append(" REL.TIRXS_DESCRIPCION,  \n");
		sqlSelect.append(" EQS.TIPO_RIESGO_NOMBRE \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" TIR_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN TIPOS_RIESGO EQS ON EQS.TIPO_RIESGO_ID=REL.TIR_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + proyectoId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" REL.TIRXS_DESCRIPCION  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new TirXProyectoMapper());
	}	
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.impl.TirXProyectoDao#cuentaTirXProyecto(java.lang.String)
	 */
	@Override
	public int cuentaTirXProyecto(String proyectoId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" TIR_X_PROYECTO REL \n");
		sqlSelect.append(" INNER JOIN TIPOS_RIESGO EQS ON EQS.TIPO_RIESGO_ID=REL.TIR_ID \n");
		sqlSelect.append("WHERE 1=1  \n");
		sqlSelect.append(" AND REL.PROYECTO_ID='" + proyectoId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.impl.TirXProyectoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
