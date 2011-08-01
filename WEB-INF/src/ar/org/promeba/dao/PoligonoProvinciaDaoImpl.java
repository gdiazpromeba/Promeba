package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.Coordenada;
import ar.org.promeba.beans.Domicilio;

public class PoligonoProvinciaDaoImpl implements PoligonoProvinciaDao   {
	
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
	
	
	
	public PoligonoProvinciaDaoImpl(){
	  StringBuilder sb;
	  //inserta
	  sb=new StringBuilder();
	  sb.append("INSERT INTO POLIGONOS_PROVINCIAS ( \n");
	  sb.append("  POLIGONO_PROVINCIA_ID, \n");
	  sb.append("  PROVINCIA_ID, \n");
	  sb.append("  LATITUD, \n");
	  sb.append("  LONGITUD, \n");
	  sb.append("  ORDEN \n");
	  sb.append(") VALUES (?, ?, ?, ?, ?) \n");
	  sqlInsert=new StringBuilder(sb.toString());
	  

	  //OBTIENE
	  sb=new StringBuilder();
	  sb.append("SELECT  \n");
	  sb.append("  POLIGONO_PROVINCIA_ID, \n");
	  sb.append("  PROVINCIA_ID, \n");
	  sb.append("  LATITUD, \n");
	  sb.append("  LONGITUD, \n");
	  sb.append("  ORDEN \n");
	  sb.append("WHERE   \n");
	  sb.append("  POLIGONO_PROVINCIA_ID=? \n");
	  sqlObtiene=new StringBuilder(sb.toString());
	  
	  
	  
	  //MODIFICA
	  sb=new StringBuilder();
	  sb.append("UPDATE POLIGONOS_PROVINCIAS SET  \n");
	  sb.append("  PROVINCIA_ID=?, \n");
	  sb.append("  LATITUD=?, \n");
	  sb.append("  LONGITUD=?, \n");
	  sb.append("  ORDEN=? \n");
	  sb.append("WHERE    \n");
	  sb.append("  POLIGONO_PROVINCIA_ID=? \n");
	  sqlActualiza=new StringBuilder(sb.toString());

	  
	  //borra
	  sb =new StringBuilder();
	  sb.append("DELETE FROM POLIGONOS_PROVINCIAS \n");
	  sb.append("WHERE  \n");
	  sb.append(" POLIGONO_PROVINCIA_ID=?  \n");
	  sqlBorra=new StringBuilder(sb.toString());
	  
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoProvinciaDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoProvinciaDao#inserta(java.lang.String, java.lang.String, ar.org.promeba.beans.Coordenada, int)
	 */
	@Override
	public void inserta(String pk, String provinciaId, Coordenada bean, int orden) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { 
		        pk, provinciaId, bean.getLatitud(), bean.getLongitud(), orden
		      });
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoProvinciaDao#modifica(java.lang.String, java.lang.String, ar.org.promeba.beans.Coordenada, int)
	 */
	@Override
	public void modifica(String pk, String provinciaId, Coordenada bean, int orden) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			        provinciaId, bean.getLatitud(), bean.getLongitud(), orden,
			        pk
			      });
		}	
	
	
	
	static class CoordenadasMapper implements RowMapper<Domicilio>{
		  public Domicilio mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Domicilio bean=new Domicilio();
			  bean.setId(resultSet.getString("DOMICILIO_ID"));
			  bean.setRegionId(resultSet.getString("REGION_ID"));
			  bean.setRegionNombre(resultSet.getString("REGION_NOMBRE"));
			  bean.setProvinciaId(resultSet.getString("PROVINCIA_ID"));
			  bean.setProvinciaNombre(resultSet.getString("PROVINCIA_NOMBRE"));
			  bean.setDepartamentoId(resultSet.getString("DEPARTAMENTO_ID"));
			  bean.setDepartamentoNombre(resultSet.getString("DEPARTAMENTO_NOMBRE"));
			  bean.setLocalidadId(resultSet.getString("LOCALIDAD_ID"));
			  bean.setLocalidadNombre(resultSet.getString("LOCALIDAD_NOMBRE"));
			  bean.setCalle(resultSet.getString("DOMICILIO_CALLE"));
			  bean.setNumero(resultSet.getInt("DOMICILIO_NUMERO"));
			  bean.setPiso(resultSet.getString("DOMICILIO_PISO"));
			  bean.setDepartamento(resultSet.getString("DOMICILIO_DEPARTAMENTO"));
			  bean.setTipo(resultSet.getString("DOMICILIO_TIPO"));
			  bean.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
			  bean.setBarrio(resultSet.getString("DOMICILIO_BARRIO"));
			  bean.setManzana(resultSet.getString("DOMICILIO_MANZANA"));
			  return bean;
		  }
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PoligonoProvinciaDao#obtiene(java.lang.String)
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
	 * @see ar.org.promeba.dao.PoligonoProvinciaDao#selecciona(java.lang.String)
	 */
	@Override
	public List<Coordenada> selecciona(String provinciaId) {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT  \n");
		sql.append("  LATITUD,  \n");
		sql.append("  LONGITUD  \n");
		sql.append("FROM  \n");
		sql.append("  POLIGONOS_PROVINCIAS   \n");
		sql.append("WHERE   \n");
		sql.append("  PROVINCIA_ID='" + provinciaId + "'   \n");
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
	
	public int cuenta(String provinciaId){
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT COUNT(*) \n");
		sql.append("FROM  \n");
		sql.append("  POLIGONOS_PROVINCIAS   \n");
		sql.append("WHERE   \n");
		sql.append("  PROVINCIA_ID='" + provinciaId + "'   \n");
		int res=jdbcTemplate.queryForInt(sql.toString());
		return res;
	}
	

}
