package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.Cargo;
import ar.org.promeba.dao.CargoDao;

public class CargoDaoImpl implements CargoDao  {
	
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
	
	
	
	public CargoDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.CARGO_ID, \n");
		sql.append("  CAR.CARGO_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  CARGOS CAR     \n");
		sql.append("WHERE     \n");
		sql.append(" CAR.CARGO_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO CARGOS ( \n");
	  sqlInsert.append("  CARGO_ID, \n");
	  sqlInsert.append("  CARGO_NOMBRE \n");
	  sqlInsert.append(") VALUES (?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE CARGOS SET  \n");
	  sqlActualiza.append("  CARGO_NOMBRE=? \n");
	  sqlActualiza.append("WHERE CARGO_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM CARGOS \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" CARGO_ID=?  \n");
	  
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.CargoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.CargoDao#inserta(ar.org.promeba.beans.Cargo)
	 */
	@Override
	public void inserta(Cargo bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre()});
	}	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.CargoDao#actualiza(ar.org.promeba.beans.Cargo)
	 */
	@Override
	public void actualiza(Cargo bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(),  bean.getId()});
		}	
	
	
	static class CargoMapper implements RowMapper<Cargo>{
		  public Cargo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Cargo bean=new Cargo();
			  bean.setId(resultSet.getString("CARGO_ID"));
			  bean.setNombre(resultSet.getString("CARGO_NOMBRE"));
			  return bean;
		  }
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.CargoDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<Cargo> selecciona(int start, int limit, String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  CAR.CARGO_ID, \n");
		sql.append("  CAR.CARGO_NOMBRE \n");
		sql.append("FROM     \n");
		sql.append("  CARGOS CAR     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.CARGO_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" CAR.CARGO_NOMBRE     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new CargoMapper());
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.CargoDao#cuenta(java.lang.String, java.lang.String)
	 */
	@Override
	public int cuenta(String nombre){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  CARGOS CAR     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND CAR.CARGO_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.CargoDao#obtiene(java.lang.String)
	 */
	@Override
	public Cargo obtiene(String id) {
		Cargo resultado=(Cargo)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new CargoMapper());
		return resultado;
	}	
	


	

	

}
