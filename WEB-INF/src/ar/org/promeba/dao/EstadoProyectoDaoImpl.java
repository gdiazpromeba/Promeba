package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.EstadoProyecto;

public class EstadoProyectoDaoImpl implements EstadoProyectoDao   {
	
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
	
	
	
	public EstadoProyectoDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.ESTADO_PROYECTO_ID, \n");
		sql.append("  CAR.ESTADO_PROYECTO_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  ESTADOS_PROYECTO CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.ESTADO_PROYECTO_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO ESTADOS_PROYECTO ( \n");
	  sqlInsert.append("  ESTADO_PROYECTO_ID, \n");
	  sqlInsert.append("  ESTADO_PROYECTO_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE ESTADOS_PROYECTO SET  \n");
	  sqlActualiza.append("  ESTADO_PROYECTO_NOMBRE=? \n");
	  sqlActualiza.append("WHERE ESTADO_PROYECTO_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM ESTADO_PROYECTO \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" ESTADO_PROYECTO_ID=?  \n");
	  
	  
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoProyectoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoProyectoDao#inserta(ar.org.promeba.beans.EstadoProyecto)
	 */
	@Override
	public void inserta(EstadoProyecto bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoProyectoDao#actualiza(ar.org.promeba.beans.EstadoProyecto)
	 */
	@Override
	public void actualiza(EstadoProyecto bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class EstadoProyectoMapper implements RowMapper<EstadoProyecto>{
		  public EstadoProyecto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  EstadoProyecto bean=new EstadoProyecto();
			  bean.setId(resultSet.getString("ESTADO_PROYECTO_ID"));
			  bean.setNombre(resultSet.getString("ESTADO_PROYECTO_NOMBRE"));
			  return bean;
		  }
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoProyectoDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<EstadoProyecto> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.ESTADO_PROYECTO_ID, \n");
		sql.append("  CAR.ESTADO_PROYECTO_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  ESTADOS_PROYECTO CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.ESTADO_PROYECTO_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.ESTADO_PROYECTO_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new EstadoProyectoMapper());
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoProyectoDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  ESTADOS_PROYECTO CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.ESTADOS_PROYECTO_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoProyectoDao#obtiene(java.lang.String)
	 */
	@Override
	public EstadoProyecto obtiene(String id) {
		EstadoProyecto resultado=(EstadoProyecto)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new EstadoProyectoMapper());
		return resultado;
	}	
	


	

	

}
