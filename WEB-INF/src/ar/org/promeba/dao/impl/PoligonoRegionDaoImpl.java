package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.Coordenada;
import ar.org.promeba.dao.PoligonoRegionDao;

public class PoligonoRegionDaoImpl implements PoligonoRegionDao    {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuilder sqlInsert;
	private StringBuilder sqlObtiene;
	private StringBuilder sqlActualiza;
	private StringBuilder sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public PoligonoRegionDaoImpl(){
	  StringBuilder sb;
	  //inserta
	  sb=new StringBuilder();
	  sb.append("INSERT INTO POLIGONOS_REGIONES ( \n");
	  sb.append("  POLIGONO_REGION_ID, \n");
	  sb.append("  REGION_ID, \n");
	  sb.append("  LATITUD, \n");
	  sb.append("  LONGITUD, \n");
	  sb.append("  ORDEN \n");
	  sb.append(") VALUES (?, ?, ?, ?, ?) \n");
	  sqlInsert=new StringBuilder(sb.toString());
	  

	  //OBTIENE
	  sb=new StringBuilder();
	  sb.append("SELECT  \n");
	  sb.append("  POLIGONO_REGION_ID, \n");
	  sb.append("  REGION_ID, \n");
	  sb.append("  LATITUD, \n");
	  sb.append("  LONGITUD, \n");
	  sb.append("  ORDEN \n");
	  sb.append("WHERE   \n");
	  sb.append("  POLIGONO_REGION_ID=? \n");
	  sqlObtiene=new StringBuilder(sb.toString());
	  
	  
	  
	  //MODIFICA
	  sb=new StringBuilder();
	  sb.append("UPDATE POLIGONOS_REGIONES SET  \n");
	  sb.append("  REGION_ID=?, \n");
	  sb.append("  LATITUD=?, \n");
	  sb.append("  LONGITUD=?, \n");
	  sb.append("  ORDEN=? \n");
	  sb.append("WHERE    \n");
	  sb.append("  POLIGONO_REGION_ID=? \n");
	  sqlActualiza=new StringBuilder(sb.toString());

	  
	  //borra
	  sb =new StringBuilder();
	  sb.append("DELETE FROM POLIGONOS_REGIONES \n");
	  sb.append("WHERE  \n");
	  sb.append(" POLIGONO_REGION_ID=?  \n");
	  sqlBorra=new StringBuilder(sb.toString());
	  
	}
	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoRegionDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoRegionDao#inserta(java.lang.String, java.lang.String, ar.org.promeba.beans.Coordenada, int)
	 */
	@Override
	public void inserta(String pk, String provinciaId, Coordenada bean, int orden) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { 
		        pk, provinciaId, bean.getLatitud(), bean.getLongitud(), orden
		      });
	}

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoRegionDao#modifica(java.lang.String, java.lang.String, ar.org.promeba.beans.Coordenada, int)
	 */
	@Override
	public void modifica(String pk, String provinciaId, Coordenada bean, int orden) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			        provinciaId, bean.getLatitud(), bean.getLongitud(), orden,
			        pk
			      });
		}	
	
	
	

	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoRegionDao#obtiene(java.lang.String)
	 */
	@Override
	public Coordenada obtiene(String id) {
		Coordenada res=jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new RowMapper<Coordenada>(){
			public Coordenada mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Coordenada bean=new Coordenada();
				bean.setLatitud(resultSet.getBigDecimal("latitud"));
				bean.setLongitud(resultSet.getBigDecimal("longitud"));
				return bean;
			}
		});
		return res;
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoRegionDao#selecciona(java.lang.String)
	 */
	@Override
	public List<Coordenada> selecciona(String provinciaId) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT  \n");
		sql.append("  LATITUD,  \n");
		sql.append("  LONGITUD  \n");
		sql.append("FROM  \n");
		sql.append("  POLIGONOS_REGIONES   \n");
		sql.append("WHERE   \n");
		sql.append("  REGION_ID='" + provinciaId + "'   \n");
		sql.append("ORDER BY   \n");
		sql.append("  ORDEN   \n");
		List<Coordenada> res=jdbcTemplate.query(sql.toString(), new RowMapper<Coordenada>(){
			public Coordenada mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Coordenada bean=new Coordenada();
				bean.setLatitud(resultSet.getBigDecimal("latitud"));
				bean.setLongitud(resultSet.getBigDecimal("longitud"));
				return bean;
			}
		});
		return res;
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoRegionDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String provinciaId){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT COUNT(*) \n");
		sql.append("FROM  \n");
		sql.append("  POLIGONOS_REGIONES   \n");
		sql.append("WHERE   \n");
		sql.append("  REGION_ID='" + provinciaId + "'   \n");
		int res=jdbcTemplate.queryForInt(sql.toString());
		return res;
	}
	

}
