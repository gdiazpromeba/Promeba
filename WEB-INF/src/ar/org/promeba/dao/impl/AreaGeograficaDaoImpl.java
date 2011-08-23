package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import ar.org.promeba.beans.AreaGeografica;
import ar.org.promeba.dao.AreaGeograficaDao;

public class AreaGeograficaDaoImpl implements AreaGeograficaDao {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlObtiene;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	

	public AreaGeograficaDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO AREAS_GEOGRAFICAS ( \n");
	  sqlInsert.append(" AREA_GEOGRAFICA_ID,  \n");
	  sqlInsert.append(" AREA_GEOGRAFICA_NOMBRE,  \n");
	  sqlInsert.append(" FECHA_DESDE,  \n");
	  sqlInsert.append(" FECHA_HASTA,  \n");
	  sqlInsert.append(" ARCHIVO_GE  \n");
	  sqlInsert.append(" ) VALUES (?, ?, ?, ?, ?)  \n");

	  //obtiene
	  StringBuffer sb =new StringBuffer();
	  sb.append("SELECT  \n");
	  sb.append(" AGE.AREA_GEOGRAFICA_ID,  \n");
	  sb.append(" AGE.AREA_GEOGRAFICA_NOMBRE,  \n");
	  sb.append(" AGE.FECHA_DESDE,  \n");
	  sb.append(" AGE.FECHA_HASTA,  \n");
	  sb.append(" AGE.ARCHIVO_GE  \n");
	  sb.append("FROM  \n");
	  sb.append(" AREAS_GEOGRAFICAS AGE \n");
	  sb.append("WHERE  \n");
	  sb.append(" AGE.AREA_GEOGRAFICA_ID=?  \n");
	  sqlObtiene=new StringBuffer(sb.toString());
	  
	  
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE AREAS_GEOGRAFICAS SET  \n");
	  sqlActualiza.append(" AREAS_GEOGRAFICAS_NOMBRE=?,  \n");
	  sqlActualiza.append(" FECHA_DESDE=?,  \n");
	  sqlActualiza.append(" FECHA_HASTA=?,  \n");
	  sqlActualiza.append(" ARCHIVO_GE=?,  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" AREA_GEOGRAFICA_ID=?  \n");
	  
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM AREAS_GEOGRAFICAS \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" AREA_GEOGRAFICA_ID=?  \n");

	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaGeograficaDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaGeograficaDao#inserta(ar.org.promeba.beans.AreaGeografica)
	 */
	@Override
	public void inserta(AreaGeografica bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre(), bean.getFechaDesde(),  bean.getFechaHasta(),
		        bean.getArchivoGE()});
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaGeograficaDao#actualiza(ar.org.promeba.beans.AreaGeografica)
	 */
	@Override
	public void actualiza(AreaGeografica bean) {
		  jdbcTemplate.update(sqlActualiza.toString(),
				  new Object[] { bean.getNombre(), bean.getFechaDesde(),  bean.getFechaHasta(),
		            bean.getArchivoGE(), bean.getId()});
	}
	


	
	
	static class AreaGeograficaMapper implements org.springframework.jdbc.core.RowMapper<AreaGeografica>{
		  public AreaGeografica mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  AreaGeografica bean=new AreaGeografica();
			  bean.setId(resultSet.getString("AREA_GEOGRAFICA_ID"));
			  bean.setNombre(resultSet.getString("AREA_GEOGRAFICA_NOMBRE"));
			  bean.setFechaDesde(resultSet.getDate("FECHA_DESDE"));
			  bean.setFechaHasta(resultSet.getDate("FECHA_HASTA"));
			  bean.setArchivoGE(resultSet.getString("ARCHIVO_GE"));
			  return bean;
		  }
	}
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaGeograficaDao#selecciona(int, int, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<AreaGeografica> selecciona(int offset, int limit, String nombre, Date fechaDesde, Date fechaHasta) {
		  StringBuffer sb =new StringBuffer();
		  sb.append("SELECT  \n");
		  sb.append(" AGE.AREA_GEOGRAFICA_ID,  \n");
		  sb.append(" AGE.AREA_GEOGRAFICA_NOMBRE,  \n");
		  sb.append(" AGE.FECHA_DESDE,  \n");
		  sb.append(" AGE.FECHA_HASTA,  \n");
		  sb.append(" AGE.ARCHIVO_GE  \n");
		  sb.append("FROM  \n");
		  sb.append(" AREAS_GEOGRAFICAS AGE \n");
		  sb.append("WHERE 1=1  \n");
		  if (!StringUtils.isEmpty(nombre)){
			  sb.append("AND AGE.AREA_GEOGRAFICA_NOMBRE LIKE '%" + nombre + "%'  \n"); 
		  }
		  if (fechaDesde!=null){
			  sb.append("AND AGE.FECHA_DESDE >='" + sdf.format(fechaDesde) + "' \n");
		  }
		  if (fechaHasta!=null){
			  sb.append("AND AGE.FECHA_HASTA <='" + sdf.format(fechaHasta) + "' \n");
		  }		  
		  sb.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		  return jdbcTemplate.query(sb.toString(), new AreaGeograficaMapper());
	}	
	
	

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaGeograficaDao#cuenta(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public int cuenta(String nombre, Date fechaDesde, Date fechaHasta) {
		StringBuffer sb =new StringBuffer();
		sb.append("SELECT  COUNT(*) \n");
		  sb.append("FROM  \n");
		  sb.append(" AREAS_GEOGRAFICAS AGE \n");
		  sb.append("WHERE 1=1  \n");
		  if (!StringUtils.isEmpty(nombre)){
			  sb.append("AND AGE.AREA_GEOGRAFICA_NOMBRE LIKE '%" + nombre + "%'  \n"); 
		  }
		  if (fechaDesde!=null){
			  sb.append("AND AGE.FECHA_DESDE >='" + sdf.format(fechaDesde) + "' \n");
		  }
		  if (fechaHasta!=null){
			  sb.append("AND AGE.FECHA_HASTA <='" + sdf.format(fechaHasta) + "' \n");
		  }		  
		  return jdbcTemplate.queryForInt(sb.toString());
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.AreaGeograficaDao#obtiene(java.lang.String)
	 */
	@Override
	public AreaGeografica obtiene(String id) {
		AreaGeografica resultado=(AreaGeografica)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new AreaGeograficaMapper());
		return resultado;
	}	
	
	

}
