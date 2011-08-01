package ar.org.promeba.oad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ar.org.promeba.beans.Rol;

public class RolDaoImpl implements ar.org.promeba.dao.RolDao {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlSelect;
	private StringBuffer sqlObtiene;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	

	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public RolDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO ROLES VALUES (?, ?, true) \n");
	  //selecciona
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" ROL_ID,  \n");
	  sqlSelect.append(" ROL_NOMBRE,  \n");
	  sqlSelect.append(" ROL_HABILITADO  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" ROLES  \n");
	  sqlSelect.append("WHERE  \n");
	  sqlSelect.append(" ROL_HABILITADO = TRUE  \n");
	  sqlSelect.append("OFFSET ? LIMIT ?  \n");
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
			  new Object[] { rol.getId(), rol.getNombre(), true});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.Rol#actualiza(ar.org.promeba.beans.Rol)
	 */
	@Override
	public void actualiza(Rol rol) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { rol.getNombre(), rol.isHabilitado()});
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
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.Rol#selecciona(int, int)
	 */
	@Override
	public List<Rol> selecciona(int offset, int limit) {
		return jdbcTemplate.query(sqlSelect.toString(), new Object[] { offset, limit }, new RolMapper());
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
