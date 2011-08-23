package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import ar.org.promeba.beans.Domicilio;
import ar.org.promeba.beans.PersonaFisica;
import ar.org.promeba.beans.TipoDocumento;
import ar.org.promeba.dao.PersonaFisicaDao;

public class PersonaFisicaDaoImpl implements PersonaFisicaDao {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlSelect;
	private StringBuffer sqlObtiene;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	

	public PersonaFisicaDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO PERSONAS_FISICAS ( \n");
	  sqlInsert.append(" PEF_ID,  \n");
	  sqlInsert.append(" PEF_NOMBRE,  \n");
	  sqlInsert.append(" PEF_APELLIDO,  \n");
	  sqlInsert.append(" TIPO_DOCUMENTO_ID,  \n");
	  sqlInsert.append(" DOCUMENTO_NUMERO,  \n");
	  sqlInsert.append(" DOMICILIO_ID,  \n");
	  sqlInsert.append(" PEF_OCUPACION,  \n");
	  sqlInsert.append(" PEF_SEXO,  \n");
	  sqlInsert.append(" FECHA_NACIMIENTO  \n");
	  sqlInsert.append(" ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)  \n");

	  //obtiene
	  StringBuffer sb =new StringBuffer();
	  sb.append("SELECT  \n");
	  sb.append(" USU.PEF_ID,  \n");
	  sb.append(" USU.PEF_NOMBRE,  \n");
	  sb.append(" USU.PEF_APELLIDO,  \n");
	  sb.append(" USU.TIPO_DOCUMENTO_ID,  \n");
	  sb.append(" USU.DOCUMENTO_NUMERO,  \n");
	  sb.append(" TID.TIPO_DOCUMENTO_NOMBRE,  \n");
	  sb.append(" DOM.DOMICILIO_ID,  \n");
	  sb.append(" DOM.REGION_ID,  \n");
	  sb.append(" REG.REGION_NOMBRE,  \n");
	  sb.append(" DOM.PROVINCIA_ID,  \n");
	  sb.append(" PRO.PROVINCIA_NOMBRE,  \n");
	  sb.append(" DOM.LOCALIDAD_ID,  \n");
	  sb.append(" LOC.LOCALIDAD_NOMBRE,  \n");
	  sb.append(" DOM.DEPARTAMENTO_ID,  \n");
	  sb.append(" DEP.DEPARTAMENTO_NOMBRE,  \n");
	  sb.append(" DOM.DOMICILIO_CALLE,  \n");
	  sb.append(" DOM.DOMICILIO_NRO,  \n");
	  sb.append(" DOM.DOMICILIO_PISO,  \n");
	  sb.append(" DOM.DOMICILIO_DEPARTAMENTO,  \n");
	  sb.append(" DOM.DOMICILIO_TIPO,  \n");
	  sb.append(" DOM.CODIGO_POSTAL,  \n");
	  sb.append(" DOM.DOMICILIO_BARRIO,  \n");
	  sb.append(" DOM.DOMICILIO_MANZANA,  \n");
	  sb.append(" DOM.DOMICILIO_SECTOR,  \n");
	  sb.append(" USU.PEF_OCUPACION,  \n");
	  sb.append(" USU.PEF_SEXO,  \n");
	  sb.append(" USU.FECHA_NACIMIENTO  \n");
	  sb.append("FROM  \n");
	  sb.append(" PERSONAS_FISICAS USU \n");
	  sb.append(" INNER JOIN TIPOS_DOCUMENTO TID ON USU.TIPO_DOCUMENTO_ID=TID.TIPO_DOCUMENTO_ID  \n");
	  sb.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
	  sb.append(" LEFT JOIN REGIONES REG ON DOM.REGION_ID=REG.REGION_ID  \n");
	  sb.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
	  sb.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
	  sb.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
	  sb.append("WHERE  \n");
	  sb.append(" USU.PEF_ID=?  \n");
	  sqlObtiene=new StringBuffer(sb.toString());
	  
	  
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE PERSONAS_FISICAS SET  \n");
	  sqlActualiza.append(" PEF_NOMBRE=?,  \n");
	  sqlActualiza.append(" PEF_APELLIDO=?,  \n");
	  sqlActualiza.append(" TIPO_DOCUMENTO_ID=?,  \n");
	  sqlActualiza.append(" DOCUMENTO_NUMERO=?,  \n");
	  sqlActualiza.append(" DOMICILIO_ID=?,  \n");
	  sqlActualiza.append(" PEF_OCUPACION=?,  \n");
	  sqlActualiza.append(" PEF_SEXO=?,  \n");
	  sqlActualiza.append(" FECHA_NACIMIENTO=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" PEF_ID=?  \n");
	  
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM PERSONAS_FISICAS \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" PEF_ID=?  \n");

	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaFisicaDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaFisicaDao#inserta(ar.org.promeba.beans.PersonaFisica)
	 */
	@Override
	public void inserta(PersonaFisica bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getNombre(), bean.getApellido(),  
		                     bean.getTipoDocumento().getId(),  bean.getDocumentoNumero(),  
		                     bean.getDomicilio().getId(), bean.getOcupacion(), bean.getSexo(),
		                     bean.getNacimiento()});
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaFisicaDao#actualiza(ar.org.promeba.beans.PersonaFisica)
	 */
	@Override
	public void actualiza(PersonaFisica bean) {
		  jdbcTemplate.update(sqlActualiza.toString(),
			  new Object[] { bean.getNombre(), bean.getApellido(),  
                             bean.getTipoDocumento().getId(),  bean.getDocumentoNumero(),  
                             bean.getDomicilio().getId(), bean.getOcupacion(), 
                             bean.getSexo(), bean.getNacimiento(), bean.getId()});
	}
	


	
	
	static class PersonaFisicaMapper implements org.springframework.jdbc.core.RowMapper<PersonaFisica>{
		  public PersonaFisica mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  PersonaFisica bean=new PersonaFisica();
			  TipoDocumento tid=new TipoDocumento();
			  tid.setId(resultSet.getString("TIPO_DOCUMENTO_ID"));
			  tid.setNombre(resultSet.getString("TIPO_DOCUMENTO_NOMBRE"));
			  bean.setId(resultSet.getString("PEF_ID"));
			  bean.setNombre(resultSet.getString("PEF_NOMBRE"));
			  bean.setApellido(resultSet.getString("PEF_APELLIDO"));
			  bean.setTipoDocumento(tid);
			  bean.setDocumentoNumero(resultSet.getLong("DOCUMENTO_NUMERO"));
			  Domicilio dom=new Domicilio();
			  dom.setId(resultSet.getString("DOMICILIO_ID"));
			  dom.setCalle(resultSet.getString("DOMICILIO_CALLE"));
			  dom.setNumero(resultSet.getInt("DOMICILIO_NRO"));
			  dom.setPiso(resultSet.getString("DOMICILIO_PISO"));
			  dom.setDepartamento(resultSet.getString("DOMICILIO_DEPARTAMENTO"));
			  dom.setRegionId(resultSet.getString("REGION_ID"));
			  dom.setRegionNombre(resultSet.getString("REGION_NOMBRE"));
			  dom.setProvinciaId(resultSet.getString("PROVINCIA_ID"));
			  dom.setProvinciaNombre(resultSet.getString("PROVINCIA_NOMBRE"));
			  dom.setDepartamentoId(resultSet.getString("DEPARTAMENTO_ID"));
			  dom.setDepartamentoNombre(resultSet.getString("DEPARTAMENTO_NOMBRE"));
			  dom.setLocalidadId(resultSet.getString("LOCALIDAD_ID"));
			  dom.setLocalidadNombre(resultSet.getString("LOCALIDAD_NOMBRE"));
			  dom.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
			  dom.setTipo(resultSet.getString("DOMICILIO_TIPO"));
			  dom.setBarrio(resultSet.getString("DOMICILIO_BARRIO"));
			  dom.setManzana(resultSet.getString("DOMICILIO_MANZANA"));
			  dom.setSector(resultSet.getString("DOMICILIO_SECTOR"));
			  bean.setDomicilio(dom);
			  bean.setOcupacion(resultSet.getString("PEF_OCUPACION"));
			  bean.setSexo(resultSet.getString("PEF_SEXO"));
			  bean.setNacimiento(resultSet.getDate("FECHA_NACIMIENTO"));
			  return bean;
		  }
	}
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaFisicaDao#selecciona(int, int, java.lang.String)
	 */
	@Override
	public List<PersonaFisica> selecciona(int offset, int limit, String apellido) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  \n");
		  sqlSelect.append(" USU.PEF_ID,  \n");
		  sqlSelect.append(" USU.PEF_NOMBRE,  \n");
		  sqlSelect.append(" USU.PEF_APELLIDO,  \n");
		  sqlSelect.append(" USU.TIPO_DOCUMENTO_ID,  \n");
		  sqlSelect.append(" USU.DOCUMENTO_NUMERO,  \n");
		  sqlSelect.append(" TID.TIPO_DOCUMENTO_NOMBRE,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_ID,  \n");
		  sqlSelect.append(" DOM.REGION_ID,  \n");
		  sqlSelect.append(" REG.REGION_NOMBRE,  \n");
		  sqlSelect.append(" DOM.PROVINCIA_ID,  \n");
		  sqlSelect.append(" PRO.PROVINCIA_NOMBRE,  \n");
		  sqlSelect.append(" DOM.LOCALIDAD_ID,  \n");
		  sqlSelect.append(" LOC.LOCALIDAD_NOMBRE,  \n");
		  sqlSelect.append(" DOM.DEPARTAMENTO_ID,  \n");
		  sqlSelect.append(" DEP.DEPARTAMENTO_NOMBRE,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_CALLE,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_NRO,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_PISO,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_DEPARTAMENTO,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_TIPO,  \n");
		  sqlSelect.append(" DOM.CODIGO_POSTAL,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_BARRIO,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_MANZANA,  \n");
		  sqlSelect.append(" DOM.DOMICILIO_SECTOR,   \n");
		  sqlSelect.append(" USU.PEF_OCUPACION,   \n");
		  sqlSelect.append(" USU.PEF_SEXO,   \n");
		  sqlSelect.append(" USU.FECHA_NACIMIENTO   \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" PERSONAS_FISICAS USU \n");
		  sqlSelect.append(" INNER JOIN TIPOS_DOCUMENTO TID ON USU.TIPO_DOCUMENTO_ID=TID.TIPO_DOCUMENTO_ID  \n");
		  sqlSelect.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
		  sqlSelect.append(" LEFT JOIN REGIONES REG ON REG.REGION_ID=DOM.REGION_ID  \n");
		  sqlSelect.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		  sqlSelect.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
		  sqlSelect.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
		  sqlSelect.append("WHERE 1=1  \n");
		  if (!StringUtils.isEmpty(apellido)){
			  sqlSelect.append("AND USU.PEF_APELLIDO LIKE '%" + apellido + "%'  \n"); 
		  }
		  sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		  return jdbcTemplate.query(sqlSelect.toString(), new PersonaFisicaMapper());
	}	
	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaFisicaDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String apellido) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" PERSONAS_FISICAS USU \n");
		  sqlSelect.append(" INNER JOIN TIPOS_DOCUMENTO TID ON USU.TIPO_DOCUMENTO_ID=TID.TIPO_DOCUMENTO_ID  \n");
		  sqlSelect.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
		  sqlSelect.append(" LEFT JOIN REGIONES REG ON REG.REGION_ID=DOM.REGION_ID  \n");
		  sqlSelect.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		  sqlSelect.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
		  sqlSelect.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
		  sqlSelect.append("WHERE 1=1  \n");
		  if (!StringUtils.isEmpty(apellido)){
			  sqlSelect.append("AND USU.PEF_APELLIDO LIKE '%" + apellido + "%'  \n"); 
		  }
		  return jdbcTemplate.queryForInt(sqlSelect.toString());
	}	
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaFisicaDao#obtiene(java.lang.String)
	 */
	@Override
	public PersonaFisica obtiene(String id) {
		PersonaFisica resultado=(PersonaFisica)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new PersonaFisicaMapper());
		return resultado;
	}	
	

	
	

}
