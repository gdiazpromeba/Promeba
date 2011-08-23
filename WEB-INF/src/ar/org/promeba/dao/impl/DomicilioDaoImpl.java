package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.Domicilio;
import ar.org.promeba.dao.DomicilioDao;

public class DomicilioDaoImpl implements DomicilioDao   {
	
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
	
	
	
	public DomicilioDaoImpl(){
	  StringBuilder sb;
	  //inserta
	  sb=new StringBuilder();
	  sb.append("INSERT INTO DOMICILIOS ( \n");
	  sb.append("  DOMICILIO_ID, \n");
	  sb.append("  REGION_ID, \n");
	  sb.append("  PROVINCIA_ID, \n");
	  sb.append("  DEPARTAMENTO_ID, \n");
	  sb.append("  LOCALIDAD_ID, \n");
	  sb.append("  DOMICILIO_CALLE, \n");
	  sb.append("  DOMICILIO_NRO, \n");
	  sb.append("  DOMICILIO_PISO, \n");
	  sb.append("  DOMICILIO_DEPARTAMENTO, \n");
	  sb.append("  DOMICILIO_TIPO, \n");
	  sb.append("  CODIGO_POSTAL, \n");
	  sb.append("  DOMICILIO_BARRIO, \n");
	  sb.append("  DOMICILIO_MANZANA, \n");
	  sb.append("  DOMICILIO_SECTOR \n");
	  sb.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");
	  sqlInsert=new StringBuilder(sb.toString());
	  

	  //OBTIENE
	  sb=new StringBuilder();
	  sb.append("SELECT  \n");
	  sb.append("  DOM.DOMICILIO_ID, \n");
	  sb.append("  DOM.REGION_ID, \n");
	  sb.append("  REG.REGION_NOMBRE, \n");
	  sb.append("  DOM.PROVINCIA_ID, \n");
	  sb.append("  PRO.PROVINCIA_NOMBRE, \n");
	  sb.append("  DOM.DEPARTAMENTO_ID, \n");
	  sb.append("  DEP.DEPARTAMENTO_NOMBRE, \n");
	  sb.append("  DOM.LOCALIDAD_ID, \n");
	  sb.append("  LOC.LOCALIDAD_NOMBRE, \n");
	  sb.append("  DOM.DOMICILIO_CALLE, \n");
	  sb.append("  DOM.DOMICILIO_NRO, \n");
	  sb.append("  DOM.DOMICILIO_PISO, \n");
	  sb.append("  DOM.DOMICILIO_DEPARTAMENTO, \n");
	  sb.append("  DOM.DOMICILIO_TIPO, \n");
	  sb.append("  DOM.CODIGO_POSTAL, \n");
	  sb.append("  DOM.DOMICILIO_BARRIO, \n");
	  sb.append("  DOM.DOMICILIO_MANZANA, \n");
	  sb.append("  DOM.DOMICILIO_SECTOR \n");
	  sb.append("FROM  \n");
	  sb.append("  DOMICILIOS DOM  \n");
	  sb.append("  INNER JOIN REGIONES REG ON DOM.REGION_ID=REG.REGION_ID  \n");
	  sb.append("  INNER JOIN PROVINCIAS PRO ON DOM.PROVINCIA_ID=PRO.PROVINCIA_ID  \n");
	  sb.append("  INNER JOIN DEPARTAMENTOS DEP ON DOM.DEPARTAMENTO_ID=DEP.DEPARTAMENTO_ID  \n");
	  sb.append("WHERE  \n");
	  sb.append("  DOM.DOMICILIO_ID=?  \n");
	  sqlObtiene=new StringBuilder(sb.toString());
	  
	  
	  
	  //MODIFICA
	  sb=new StringBuilder();
	  sb.append("UPDATE DOMICILIOS SET   \n");
	  sb.append("  REGION_ID=?, \n");
	  sb.append("  PROVINCIA_ID=?, \n");
	  sb.append("  DEPARTAMENTO_ID=?, \n");
	  sb.append("  LOCALIDAD_ID=?, \n");
	  sb.append("  DOMICILIO_CALLE=?, \n");
	  sb.append("  DOMICILIO_NRO=?, \n");
	  sb.append("  DOMICILIO_PISO=?, \n");
	  sb.append("  DOMICILIO_DEPARTAMENTO=?, \n");
	  sb.append("  DOMICILIO_TIPO=?, \n");
	  sb.append("  CODIGO_POSTAL=?, \n");
	  sb.append("  DOMICILIO_BARRIO=?, \n");
	  sb.append("  DOMICILIO_MANZANA=?, \n");
	  sb.append("  DOMICILIO_SECTOR=? \n");
	  sb.append("WHERE    \n");
	  sb.append("  DOMICILIO_ID=? \n");
	  sqlActualiza=new StringBuilder(sb.toString());

	  
	  //borra
	  sb =new StringBuilder();
	  sb.append("DELETE FROM DOMICILIOS \n");
	  sb.append("WHERE  \n");
	  sb.append(" DOMICILIO_ID=?  \n");
	  sqlBorra=new StringBuilder(sb.toString());
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DomicilioDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DomicilioDao#inserta(ar.org.promeba.beans.Domicilio)
	 */
	@Override
	public void inserta(Domicilio bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { 
		        bean.getId(), bean.getRegionId(), bean.getProvinciaId(), bean.getDepartamentoId(), bean.getLocalidadId(),
		        bean.getCalle(), bean.getNumero(), bean.getPiso(), bean.getDepartamento(),
		        bean.getTipo(), bean.getCodigoPostal(), bean.getBarrio(), bean.getManzana(),
		        bean.getSector()});
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.DomicilioDao#modifica(ar.org.promeba.beans.Domicilio)
	 */
	@Override
	public void modifica(Domicilio bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { 
			        bean.getRegionId(), bean.getProvinciaId(), bean.getDepartamentoId(), bean.getLocalidadId(),
			        bean.getCalle(), bean.getNumero(), bean.getPiso(), bean.getDepartamento(),
			        bean.getTipo(), bean.getCodigoPostal(), bean.getBarrio(), bean.getManzana(),
			        bean.getSector(),  bean.getId()});
		}	
	
	
	
	static class DomicilioMapper implements RowMapper<Domicilio>{
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
	 * @see ar.org.promeba.dao.DomicilioDao#obtiene(java.lang.String)
	 */
	@Override
	public Domicilio obtiene(String id) {
		List<Domicilio> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new DomicilioMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
