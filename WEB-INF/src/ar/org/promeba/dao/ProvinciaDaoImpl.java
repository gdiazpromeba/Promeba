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

import ar.org.promeba.beans.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao   {
	
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
	
	
	
	public ProvinciaDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO PROVINCIAS VALUES (?, ?, ?) \n");
	  //cuenta
	  sqlCuenta =new StringBuffer();
	  sqlCuenta.append("SELECT  COUNT(*) \n");
	  sqlCuenta.append("FROM  \n");
	  sqlCuenta.append(" PROVINCIAS  \n");
	  sqlCuenta.append("WHERE  \n");
	  sqlCuenta.append(" REGION_ID=?  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" PROVINCIA_ID,  \n");
	  sqlObtiene.append(" PROVINCIA_NOMBRE,  \n");
	  sqlObtiene.append(" REGION_ID,  \n");
	  sqlObtiene.append(" PROVINCIA_COLOR  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" PROVINCIAS  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" PROVINCIA_ID=?  \n");
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE PROVINCIAS SET  \n");
	  sqlActualiza.append(" PROVINCIA_NOMBRE=?,  \n");
	  sqlActualiza.append(" REGION_ID=?,  \n");
	  sqlActualiza.append(" PROVINCIA_COLOR=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" PROVINCIA_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM PROVINCIAS \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" PROVINCIA_ID=?  \n");
	  
	  
	  
	}
	

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProvinciaDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProvinciaDao#inserta(ar.org.promeba.beans.Provincia)
	 */
	@Override
	public void inserta(Provincia bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre(), bean.getRegionId(), bean.getColor()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProvinciaDao#actualiza(ar.org.promeba.beans.Provincia)
	 */
	@Override
	public void actualiza(Provincia bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(), bean.getRegionId(), bean.getColor(), bean.getId()  });
		}	
	
	
	static class ProvinciaMapper implements RowMapper<Provincia>{
		  public Provincia mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Provincia bean=new Provincia();
			  bean.setId(resultSet.getString("PROVINCIA_ID"));
			  bean.setNombre(resultSet.getString("PROVINCIA_NOMBRE"));
			  bean.setRegionId(resultSet.getString("REGION_ID"));
			  bean.setColor(resultSet.getString("PROVINCIA_COLOR"));
			  return bean;
		  }
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProvinciaDao#selecciona(java.lang.String)
	 */
	@Override
	public List<Provincia> selecciona(String regionId) {
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" PROVINCIA_ID,  \n");
	  sqlSelect.append(" PROVINCIA_NOMBRE,  \n");
	  sqlSelect.append(" REGION_ID,  \n");
	  sqlSelect.append(" PROVINCIA_COLOR  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" PROVINCIAS  \n");
	  sqlSelect.append("WHERE 1=1  \n");
	  if (!StringUtils.isEmpty(regionId)){
	    sqlSelect.append(" AND REGION_ID='"+ regionId  +"'  \n");
	  }
	  sqlSelect.append("ORDER BY   \n");
	  sqlSelect.append(" PROVINCIA_NOMBRE  \n");
      return jdbcTemplate.query(sqlSelect.toString(), new ProvinciaMapper());
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProvinciaDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String regionId) {
		sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  COUNT(*) \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" PROVINCIAS \n");
		sqlSelect.append("WHERE 1=1  \n");
		if (!StringUtils.isEmpty(regionId)) {
			sqlSelect.append(" AND REGION_ID='" + regionId + "'  \n");
		}
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	

	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProvinciaDao#obtiene(java.lang.String)
	 */
	@Override
	public Provincia obtiene(String id) {
		List<Provincia> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new ProvinciaMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
