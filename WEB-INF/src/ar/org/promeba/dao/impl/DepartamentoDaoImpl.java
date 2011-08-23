package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.Departamento;
import ar.org.promeba.dao.DepartamentoDao;

public class DepartamentoDaoImpl implements DepartamentoDao    {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlSelect;
	private StringBuffer sqlCuenta;
	private StringBuffer sqlObtiene;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public DepartamentoDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO DEPARTAMENTOS VALUES (?, ?, ?) \n");
	  //cuenta
	  sqlCuenta =new StringBuffer();
	  sqlCuenta.append("SELECT  COUNT(*) \n");
	  sqlCuenta.append("FROM  \n");
	  sqlCuenta.append(" DEPARTAMENTOS  \n");
	  sqlCuenta.append("WHERE  \n");
	  sqlCuenta.append(" PROVINCIA_ID=?  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" DEPARTAMENTO_ID,  \n");
	  sqlObtiene.append(" DEPARTAMENTO_NOMBRE,  \n");
	  sqlObtiene.append(" PROVINCIA_ID  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" DEPARTAMENTOS  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" DEPARTAMENTO_ID=?  \n");
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE DEPARTAMENTOS SET  \n");
	  sqlActualiza.append(" DEPARTAMENTO_NOMBRE=?,  \n");
	  sqlActualiza.append(" PROVINCIA_ID=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" DEPARTAMENTO_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM DEPARTAMENTOS \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" DEPARTAMENTO_ID=?  \n");
	  
	  
	  
	}
	

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DepartamentoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DepartamentoDao#inserta(ar.org.promeba.beans.Departamento)
	 */
	@Override
	public void inserta(Departamento bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre(), bean.getProvinciaId()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DepartamentoDao#actualiza(ar.org.promeba.beans.Departamento)
	 */
	@Override
	public void actualiza(Departamento bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(), bean.getProvinciaId(), bean.getId() });
		}	
	
	
	static class DepartamentoMapper implements RowMapper<Departamento>{
		  public Departamento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Departamento bean=new Departamento();
			  bean.setId(resultSet.getString("DEPARTAMENTO_ID"));
			  bean.setNombre(resultSet.getString("DEPARTAMENTO_NOMBRE"));
			  bean.setProvinciaId(resultSet.getString("PROVINCIA_ID"));
			  return bean;
		  }
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DepartamentoDao#selecciona(java.lang.String)
	 */
	@Override
	public List<Departamento> selecciona(String provinciaId) {
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" DEPARTAMENTO_ID,  \n");
	  sqlSelect.append(" DEPARTAMENTO_NOMBRE,  \n");
	  sqlSelect.append(" PROVINCIA_ID  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" DEPARTAMENTOS  \n");
	  sqlSelect.append("WHERE   \n");
	  sqlSelect.append(" PROVINCIA_ID='"+ provinciaId  +"'  \n");
	  sqlSelect.append("ORDER BY   \n");
	  sqlSelect.append(" DEPARTAMENTO_NOMBRE  \n");
      return jdbcTemplate.query(sqlSelect.toString(), new DepartamentoMapper());
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DepartamentoDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String provinciaId) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" DEPARTAMENTOS  \n");
		  sqlSelect.append("WHERE   \n");
		  sqlSelect.append(" PROVINCIA_ID='"+ provinciaId  +"'  \n");
	      return jdbcTemplate.queryForInt(sqlSelect.toString());
		}
	

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DepartamentoDao#obtiene(java.lang.String)
	 */
	@Override
	public Departamento obtiene(String id) {
		List<Departamento> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new DepartamentoMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
