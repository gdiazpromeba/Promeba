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

import ar.org.promeba.beans.Rol;
import ar.org.promeba.dao.RolDao;

public class RolDaoImpl implements ar.org.promeba.dao.RolDao {
	
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
	
	
	
	public RolDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO ROLES VALUES (?, ?, true) \n");
	  //cuenta
	  sqlCuenta =new StringBuffer();
	  sqlCuenta.append("SELECT  COUNT(*) \n");
	  sqlCuenta.append("FROM  \n");
	  sqlCuenta.append(" ROLES  \n");
	  sqlCuenta.append("WHERE  \n");
	  sqlCuenta.append(" ROL_HABILITADO = TRUE  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" ROL_ID,  \n");
	  sqlObtiene.append(" ROL_NOMBRE,  \n");
	  sqlObtiene.append(" ROL_HABILITADO  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" ROLES  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" ROL_ID=?  \n");
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE ROLES SET  \n");
	  sqlActualiza.append(" ROL_NOMBRE=?,  \n");
	  sqlActualiza.append(" ROL_HABILITADO=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" ROL_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM ROLES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" ROL_ID=?  \n");
	  
	  
	  
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.Rol#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.Rol#inserta(ar.org.promeba.beans.Rol)
	 */
	@Override
	public void inserta(Rol rol) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { rol.getId(), rol.getNombre()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.Rol#actualiza(ar.org.promeba.beans.Rol)
	 */
	@Override
	public void actualiza(Rol rol) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { rol.getNombre(), rol.isHabilitado(), rol.getId() });
		}	
	
	
	static class RolMapper implements RowMapper<Rol>{
		  public Rol mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Rol bean=new Rol();
			  bean.setId(resultSet.getString("ROL_ID"));
			  bean.setNombre(resultSet.getString("ROL_NOMBRE"));
			  bean.setHabilitado(resultSet.getBoolean("ROL_HABILITADO"));
			  return bean;
		  }
	}
	

	public List<Rol> selecciona(int offset, int limit, String nombre, String usuarioId) {
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" ROL_ID,  \n");
	  sqlSelect.append(" ROL_NOMBRE,  \n");
	  sqlSelect.append(" ROL_HABILITADO  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" ROLES  \n");
	  sqlSelect.append("WHERE  \n");
	  sqlSelect.append(" ROL_HABILITADO = TRUE  \n");
	  if (!StringUtils.isEmpty(nombre)){
		  sqlSelect.append(" AND ROL_NOMBRE LIKE '%" + nombre + "%' \n");
	  }
	  if (!StringUtils.isEmpty(usuarioId)){
		  sqlSelect.append(" AND ROL_ID IN (SELECT ROL_ID FROM ");
		  sqlSelect.append(" ROLES_X_USUARIO WHERE USUARIO_ID='" + usuarioId  + "') \n");
	  }	  
	  sqlSelect.append("OFFSET " + offset + "  LIMIT " + limit + "  \n");
      return jdbcTemplate.query(sqlSelect.toString(), new RolMapper());
	}
	
	public int cuenta(String nombre, String usuarioId) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" ROLES  \n");
		  sqlSelect.append("WHERE  \n");
		  sqlSelect.append(" ROL_HABILITADO = TRUE  \n");
		  if (!StringUtils.isEmpty(nombre)){
			  sqlSelect.append(" AND ROL_NOMBRE LIKE '" + nombre + "' \n");
		  }
		  if (!StringUtils.isEmpty(usuarioId)){
			  sqlSelect.append(" AND ROL_ID IN (SELECT ROL_ID FROM ");
			  sqlSelect.append(" ROLES_X_USUARIO WHERE USUARIO_ID='" + usuarioId  + "') \n");
		  }		  
	      return jdbcTemplate.queryForInt(sqlSelect.toString());
		}
	
	

	public int selecciona(String nombre) {
		return jdbcTemplate.queryForInt(sqlSelect.toString(), new Object[] { nombre });
	}
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.Rol#obtiene(java.lang.String)
	 */
	@Override
	public Rol obtiene(String id) {
		List<Rol> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new RolMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
