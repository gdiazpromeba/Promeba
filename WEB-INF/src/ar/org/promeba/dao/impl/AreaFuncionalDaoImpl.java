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

import ar.org.promeba.beans.AreaFuncional;
import ar.org.promeba.dao.AreaFuncionalDao;

public class AreaFuncionalDaoImpl implements AreaFuncionalDao  {
	
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
	
	
	
	public AreaFuncionalDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO AREAS_FUNCIONALES VALUES (?, ?, ?) \n");
	  //cuenta
	  sqlCuenta =new StringBuffer();
	  sqlCuenta.append("SELECT  COUNT(*) \n");
	  sqlCuenta.append("FROM  \n");
	  sqlCuenta.append(" AREAS_FUNCIONALES  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" AREA_FUNCIONAL_ID,  \n");
	  sqlObtiene.append(" AREA_FUNCIONAL_NOMBRE,  \n");
	  sqlObtiene.append(" AREA_FUNCIONAL_ORDEN  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" AREAS_FUNCIONALES  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" AREA_FUNCIONAL_ID=?  \n");
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE AREAS_FUNCIONALES SET  \n");
	  sqlActualiza.append(" AREA_FUNCIONAL_NOMBRE=?,  \n");
	  sqlActualiza.append(" AREA_FUNCIONAL_ORDEN=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" AREA_FUNCIONAL_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM AREAS_FUNCIONALES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" AREA_FUNCIONAL_ID=?  \n");
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaFuncional#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaFuncional#inserta(ar.org.promeba.beans.AreaFuncional)
	 */
	@Override
	public void inserta(AreaFuncional bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre(), bean.getOrden()});
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaFuncional#actualiza(ar.org.promeba.beans.AreaFuncional)
	 */
	@Override
	public void actualiza(AreaFuncional bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(), bean.getOrden(), bean.getId() });
		}	
	
	
	static class AreaFuncionalMapper implements RowMapper<AreaFuncional>{
		  public AreaFuncional mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  AreaFuncional bean=new AreaFuncional();
			  bean.setId(resultSet.getString("AREA_FUNCIONAL_ID"));
			  bean.setNombre(resultSet.getString("AREA_FUNCIONAL_NOMBRE"));
			  bean.setOrden(resultSet.getInt("AREA_FUNCIONAL_ORDEN"));
			  return bean;
		  }
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaFuncional#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<AreaFuncional> selecciona(int offset, int limit, String nombre) {
      sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" AREA_FUNCIONAL_ID,  \n");
	  sqlSelect.append(" AREA_FUNCIONAL_NOMBRE,  \n");
	  sqlSelect.append(" AREA_FUNCIONAL_ORDEN  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" AREAS_FUNCIONALES  \n");
	  sqlSelect.append("WHERE  1=1 \n");
	  if (!StringUtils.isEmpty(nombre)){
		  sqlSelect.append(" AND AREA_FUNCIONAL_NOMBRE LIKE '%" + nombre + "%' \n");
	  }
	  sqlSelect.append("ORDER BY  \n");
	  sqlSelect.append(" AREA_FUNCIONAL_ORDEN  \n");
	  sqlSelect.append("OFFSET "  + offset + " LIMIT " + limit  + " \n");

      return jdbcTemplate.query(sqlSelect.toString(), new AreaFuncionalMapper());
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaFuncional#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" AREAS_FUNCIONALES  \n");
		  sqlSelect.append("WHERE  1=1 \n");
		  if (!StringUtils.isEmpty(nombre)){
			  sqlSelect.append(" AND AREA_FUNCIONAL_NOMBRE LIKE '" + nombre + "' \n");
		  }
	      return jdbcTemplate.queryForInt(sqlSelect.toString());
	 }
	
	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaFuncional#obtiene(java.lang.String)
	 */
	@Override
	public AreaFuncional obtiene(String id) {
		AreaFuncional  bean=(AreaFuncional)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new AreaFuncionalMapper());
		return bean;
	}
	

}
