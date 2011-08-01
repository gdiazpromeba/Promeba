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

import ar.org.promeba.beans.AreaFuncionalXRol;
import ar.org.promeba.beans.Region;
import ar.org.promeba.beans.Rol;
import ar.org.promeba.beans.RolXUsuario;
import ar.org.promeba.dao.RolesXUsuarioDaoImpl.RolXUsuarioMapper;

public class RegionDaoImpl implements RegionDao  {
	
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
	
	
	
	public RegionDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO REGIONES VALUES (?, ?, ?) \n");
	  //cuenta
	  sqlCuenta =new StringBuffer();
	  sqlCuenta.append("SELECT  COUNT(*) \n");
	  sqlCuenta.append("FROM  \n");
	  sqlCuenta.append(" REGIONES  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" REGION_ID,  \n");
	  sqlObtiene.append(" REGION_NOMBRE,  \n");
	  sqlObtiene.append(" REGION_COLOR  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" ROLES  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" ROL_ID=?  \n");
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE REGION SET  \n");
	  sqlActualiza.append(" REGION_NOMBRE=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" REGION_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM REGIONES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" REGION_ID=?  \n");
	  
	  
	  
	}
	

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.RegionDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.RegionDao#inserta(ar.org.promeba.beans.Region)
	 */
	@Override
	public void inserta(Region bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre(), bean.getColor()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.RegionDao#actualiza(ar.org.promeba.beans.Region)
	 */
	@Override
	public void actualiza(Region bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(), bean.getColor(), bean.getId() });
		}	
	
	
	static class RegionMapper implements RowMapper<Region>{
		  public Region mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Region bean=new Region();
			  bean.setId(resultSet.getString("REGION_ID"));
			  bean.setNombre(resultSet.getString("REGION_NOMBRE"));
			  bean.setColor(resultSet.getString("REGION_COLOR"));
			  return bean;
		  }
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.RegionDao#selecciona()
	 */
	@Override
	public List<Region> selecciona() {
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" REGION_ID,  \n");
	  sqlSelect.append(" REGION_NOMBRE,  \n");
	  sqlSelect.append(" REGION_COLOR  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" REGIONES  \n");
	  sqlSelect.append("ORDER BY   \n");
	  sqlSelect.append(" REGION_NOMBRE  \n");
      return jdbcTemplate.query(sqlSelect.toString(), new RegionMapper());
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.RegionDao#cuenta(java.lang.String, java.lang.String)
	 */
	@Override
	public int cuenta() {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" REGIONES \n");
	      return jdbcTemplate.queryForInt(sqlSelect.toString());
		}
	

	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.RegionDao#obtiene(java.lang.String)
	 */
	@Override
	public Region obtiene(String id) {
		List<Region> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new RegionMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
