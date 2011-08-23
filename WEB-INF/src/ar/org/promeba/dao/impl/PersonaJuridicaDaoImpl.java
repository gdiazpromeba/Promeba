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
import ar.org.promeba.beans.PersonaJuridica;
import ar.org.promeba.dao.PersonaJuridicaDao;

public class PersonaJuridicaDaoImpl implements PersonaJuridicaDao {

	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sql;
	private String sqlInsert;
	private String sqlObtiene;
	private String sqlActualiza;

	
	
	public PersonaJuridicaDaoImpl() {
		// inserta
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO PERSONAS_JURIDICAS ( \n");
		sql.append(" PEJ_ID,  \n");
		sql.append(" PEJ_NOMBRE,  \n");
		sql.append(" FECHA_INSCRIPCION,  \n");
		sql.append(" PEJ_PERSONERIA,  \n");
		sql.append(" CUIT,  \n");
		sql.append(" DOMICILIO_ID  \n");
		sql.append(" ) VALUES (?, ?, ?, ?, ?, ?)  \n");
		sqlInsert = sql.toString();

		// obtiene
		sql = new StringBuffer();
		sql.append("SELECT  \n");
		sql.append(" USU.PEJ_ID,  \n");
		sql.append(" USU.PEJ_NOMBRE,  \n");
		sql.append(" USU.FECHA_INSCRIPCION,  \n");
		sql.append(" USU.PEJ_PERSONERIA,  \n");
		sql.append(" USU.CUIT,  \n");
		sql.append(" DOM.DOMICILIO_ID,  \n");
		sql.append(" DOM.REGION_ID,  \n");
		sql.append(" REG.REGION_NOMBRE,  \n");
		sql.append(" DOM.PROVINCIA_ID,  \n");
		sql.append(" PRO.PROVINCIA_NOMBRE,  \n");
		sql.append(" DOM.LOCALIDAD_ID,  \n");
		sql.append(" LOC.LOCALIDAD_NOMBRE,  \n");
		sql.append(" DOM.DEPARTAMENTO_ID,  \n");
		sql.append(" DEP.DEPARTAMENTO_NOMBRE,  \n");
		sql.append(" DOM.DOMICILIO_CALLE,  \n");
		sql.append(" DOM.DOMICILIO_NRO,  \n");
		sql.append(" DOM.DOMICILIO_PISO,  \n");
		sql.append(" DOM.DOMICILIO_DEPARTAMENTO,  \n");
		sql.append(" DOM.DOMICILIO_TIPO,  \n");
		sql.append(" DOM.CODIGO_POSTAL,  \n");
		sql.append(" DOM.DOMICILIO_BARRIO,  \n");
		sql.append(" DOM.DOMICILIO_MANZANA,  \n");
		sql.append(" DOM.DOMICILIO_SECTOR  \n");
		sql.append("FROM  \n");
		sql.append(" PERSONAS_JURIDICAS USU \n");
		sql.append(" INNER JOIN TIPOS_DOCUMENTO TID ON USU.TIPO_DOCUMENTO_ID=TID.TIPO_DOCUMENTO_ID  \n");
		sql.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
		sql.append(" LEFT JOIN REGIONES REG ON DOM.REGION_ID=REG.REGION_ID  \n");
		sql.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		sql.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
		sql.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
		sql.append("WHERE  \n");
		sql.append(" USU.PEJ_ID=?  \n");
		sqlObtiene = sql.toString();

		// modifica
		sql = new StringBuffer();
		sql.append("UPDATE PERSONAS_JURIDICAS SET  \n");
		sql.append(" PEJ_NOMBRE=?,  \n");
		sql.append(" FECHA_INSCRIPCION=?,  \n");
		sql.append(" PEJ_PERSONERIA=?,  \n");
		sql.append(" CUIT=?,  \n");
		sql.append(" DOMICILIO_ID=?  \n");
		sql.append("WHERE  \n");
		sql.append(" PEJ_ID=?  \n");
		sqlActualiza = sql.toString();

		// borra
		sql = new StringBuffer();
		sql.append("DELETE FROM PERSONAS_JURIDICAS \n");
		sql.append("WHERE  \n");
		sql.append(" PEJ_ID=?  \n");
	}
	
	
    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}	
	
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sql.toString(), 
				  new Object[] { id});

	}

	@Override
	public void inserta(PersonaJuridica bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { bean.getId(), bean.getNombre(), bean.getFechaInscripcion(),  
			                     bean.getPersoneria(),  bean.getCuit(),  
			                     bean.getDomicilio().getId()});

	}

	@Override
	public void actualiza(PersonaJuridica bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getNombre(), bean.getFechaInscripcion(),  
			                     bean.getPersoneria(),  bean.getCuit(),  
			                     bean.getDomicilio().getId(), bean.getId()});
	}
	
	
	static class PersonaJuridicaMapper implements org.springframework.jdbc.core.RowMapper<PersonaJuridica>{
		  public PersonaJuridica mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  PersonaJuridica bean=new PersonaJuridica();
			  bean.setId(resultSet.getString("PEJ_ID"));
			  bean.setNombre(resultSet.getString("PEJ_NOMBRE"));
			  bean.setFechaInscripcion(resultSet.getDate("FECHA_INSCRIPCION"));
			  bean.setPersoneria(resultSet.getString("PEJ_PERSONERIA"));
			  bean.setCuit(resultSet.getString("CUIT"));
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
			  return bean;
		  }
	}	
	

	@Override
	public List<PersonaJuridica> selecciona(int offset, int limit, String nombre) {
		sql = new StringBuffer();
		sql.append("SELECT  \n");
		sql.append(" USU.PEJ_ID,  \n");
		sql.append(" USU.PEJ_NOMBRE,  \n");
		sql.append(" USU.FECHA_INSCRIPCION,  \n");
		sql.append(" USU.PEJ_PERSONERIA,  \n");
		sql.append(" USU.CUIT,  \n");
		sql.append(" DOM.DOMICILIO_ID,  \n");
		sql.append(" DOM.REGION_ID,  \n");
		sql.append(" REG.REGION_NOMBRE,  \n");
		sql.append(" DOM.PROVINCIA_ID,  \n");
		sql.append(" PRO.PROVINCIA_NOMBRE,  \n");
		sql.append(" DOM.LOCALIDAD_ID,  \n");
		sql.append(" LOC.LOCALIDAD_NOMBRE,  \n");
		sql.append(" DOM.DEPARTAMENTO_ID,  \n");
		sql.append(" DEP.DEPARTAMENTO_NOMBRE,  \n");
		sql.append(" DOM.DOMICILIO_CALLE,  \n");
		sql.append(" DOM.DOMICILIO_NRO,  \n");
		sql.append(" DOM.DOMICILIO_PISO,  \n");
		sql.append(" DOM.DOMICILIO_DEPARTAMENTO,  \n");
		sql.append(" DOM.DOMICILIO_TIPO,  \n");
		sql.append(" DOM.CODIGO_POSTAL,  \n");
		sql.append(" DOM.DOMICILIO_BARRIO,  \n");
		sql.append(" DOM.DOMICILIO_MANZANA,  \n");
		sql.append(" DOM.DOMICILIO_SECTOR  \n");
		sql.append("FROM  \n");
		sql.append(" PERSONAS_JURIDICAS USU \n");
		sql.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
		sql.append(" LEFT JOIN REGIONES REG ON DOM.REGION_ID=REG.REGION_ID  \n");
		sql.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		sql.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
		sql.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
		sql.append("WHERE 1=1  \n");
		if (!StringUtils.isEmpty(nombre)){
			  sql.append("AND USU.PEJ_NOMBRE LIKE '%" + nombre + "%'  \n"); 
		}
		sql.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new PersonaJuridicaMapper());		
	}

	@Override
	public int cuenta(String nombre) {
		sql = new StringBuffer();
		sql.append("SELECT  COUNT(*) \n");
		sql.append("FROM  \n");
		sql.append(" PERSONAS_JURIDICAS USU \n");
		sql.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
		sql.append(" LEFT JOIN REGIONES REG ON DOM.REGION_ID=REG.REGION_ID  \n");
		sql.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		sql.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
		sql.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
		sql.append("WHERE 1=1  \n");
		if (!StringUtils.isEmpty(nombre)){
			  sql.append("AND USU.PEJ_NOMBRE LIKE '%" + nombre + "%'  \n"); 
		}		
		return jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public PersonaJuridica obtiene(String id) {
		PersonaJuridica resultado=(PersonaJuridica)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new PersonaJuridicaMapper());
		return resultado;
	}

}
