package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.Localidad;
import ar.org.promeba.dao.LocalidadDao;

public class LocalidadDaoImpl implements LocalidadDao   {
	
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
	
	
	
	public LocalidadDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO LOCALIDADES VALUES (?, ?, ?) \n");
	  //cuenta
	  sqlCuenta =new StringBuffer();
	  sqlCuenta.append("SELECT  COUNT(*) \n");
	  sqlCuenta.append("FROM  \n");
	  sqlCuenta.append(" LOCALIDADES  \n");
	  sqlCuenta.append("WHERE  \n");
	  sqlCuenta.append(" DEPARTAMENTO_ID=?  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" LOCALIDAD_ID,  \n");
	  sqlObtiene.append(" LOCALIDAD_NOMBRE,  \n");
	  sqlObtiene.append(" DEPARTAMENTO_ID  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" LOCALIDADES  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" LOCALIDAD_ID=?  \n");
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE LOCALIDADES SET  \n");
	  sqlActualiza.append(" LOCALIDAD_NOMBRE=?,  \n");
	  sqlActualiza.append(" DEPARTAMENTO_ID=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" LOCALIDAD_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM LOCALIDADES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" LOCALIDAD_ID=?  \n");
	  
	  
	  
	}
	

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.LocalidadDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.LocalidadDao#inserta(ar.org.promeba.beans.Localidad)
	 */
	@Override
	public void inserta(Localidad bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre(), bean.getDepartamentoId()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.LocalidadDao#actualiza(ar.org.promeba.beans.Localidad)
	 */
	@Override
	public void actualiza(Localidad bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(), bean.getDepartamentoId(), bean.getId() });
		}	
	
	
	static class LocalidadMapper implements RowMapper<Localidad>{
		  public Localidad mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Localidad bean=new Localidad();
			  bean.setId(resultSet.getString("LOCALIDAD_ID"));
			  bean.setNombre(resultSet.getString("LOCALIDAD_NOMBRE"));
			  bean.setDepartmanetoId(resultSet.getString("DEPARTAMENTO_ID"));
			  return bean;
		  }
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.LocalidadDao#selecciona(java.lang.String)
	 */
	@Override
	public List<Localidad> selecciona(String departamentoId) {
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" LOCALIDAD_ID,  \n");
	  sqlSelect.append(" LOCALIDAD_NOMBRE,  \n");
	  sqlSelect.append(" DEPARTAMENTO_ID  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" LOCALIDADES  \n");
	  sqlSelect.append("WHERE   \n");
	  sqlSelect.append(" DEPARTAMENTO_ID='"+ departamentoId  +"'  \n");
	  sqlSelect.append("ORDER BY   \n");
	  sqlSelect.append(" LOCALIDAD_NOMBRE  \n");
      return jdbcTemplate.query(sqlSelect.toString(), new LocalidadMapper());
	}
	


	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.LocalidadDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String departamentoId) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" LOCALIDADES  \n");
		  sqlSelect.append("WHERE   \n");
		  sqlSelect.append(" DEPARTAMENTO_ID='"+ departamentoId  +"'  \n");
	      return jdbcTemplate.queryForInt(sqlSelect.toString());
		}
	

	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.LocalidadDao#obtiene(java.lang.String)
	 */
	@Override
	public Localidad obtiene(String id) {
		List<Localidad> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new LocalidadMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
