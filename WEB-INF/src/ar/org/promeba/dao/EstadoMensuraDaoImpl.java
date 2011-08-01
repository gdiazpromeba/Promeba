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

import ar.org.promeba.beans.EstadoMensura;

public class EstadoMensuraDaoImpl implements EstadoMensuraDao    {
	
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
	
	
	
	public EstadoMensuraDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.ESTADO_MENSURA_ID, \n");
		sql.append("  CAR.ESTADO_MENSURA_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  ESTADOS_MENSURA CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.ESTADO_MENSURA_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO ESTADOS_MENSURA ( \n");
	  sqlInsert.append("  ESTADO_MENSURA_ID, \n");
	  sqlInsert.append("  ESTADO_MENSURA_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE ESTADOS_MENSURA SET  \n");
	  sqlActualiza.append("  ESTADO_MENSURA_NOMBRE=? \n");
	  sqlActualiza.append("WHERE ESTADO_MENSURA_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM ESTADO_PROYECTO \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" ESTADO_MENSURA_ID=?  \n");
	  
	  
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoMensuraDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoMensuraDao#inserta(ar.org.promeba.beans.EstadoMensura)
	 */
	@Override
	public void inserta(EstadoMensura bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoMensuraDao#actualiza(ar.org.promeba.beans.EstadoMensura)
	 */
	@Override
	public void actualiza(EstadoMensura bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class EstadoMensuraMapper implements RowMapper<EstadoMensura>{
		  public EstadoMensura mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  EstadoMensura bean=new EstadoMensura();
			  bean.setId(resultSet.getString("ESTADO_MENSURA_ID"));
			  bean.setNombre(resultSet.getString("ESTADO_MENSURA_NOMBRE"));
			  return bean;
		  }
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoMensuraDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<EstadoMensura> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.ESTADO_MENSURA_ID, \n");
		sql.append("  CAR.ESTADO_MENSURA_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  ESTADOS_MENSURA CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.ESTADO_MENSURA_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.ESTADO_MENSURA_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new EstadoMensuraMapper());
	}



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoMensuraDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  ESTADOS_MENSURA CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.ESTADOS_MENSURA_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.EstadoMensuraDao#obtiene(java.lang.String)
	 */
	@Override
	public EstadoMensura obtiene(String id) {
		EstadoMensura resultado=(EstadoMensura)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new EstadoMensuraMapper());
		return resultado;
	}	
	


	

	

}
