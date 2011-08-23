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

import ar.org.promeba.beans.Persona;
import ar.org.promeba.beans.Subejecutor;
import ar.org.promeba.dao.SubejecutorDao;

public class SubejecutorDaoImpl implements SubejecutorDao  {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	private StringBuffer sqlObtiene;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public SubejecutorDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  SUB.SUBEJECUTOR_ID, \n");
		sql.append("  PER.DENOMINACION AS PERSONA_DENOMINACION, \n");
		sql.append("  PER.TIPO AS PERSONA_TIPO, \n");
		sql.append("  SUB.PERSONA_ID, \n");
		sql.append("  SUB.SUBEJECUTOR_EMAIL, \n");
		sql.append("  SUB.SUBEJECUTOR_CARACTER, \n");
		sql.append("  SUB.FECHA_LEGITIMACION, \n");
		sql.append("  SUB.SUBEJECUTOR_NOMBRE, \n");
		sql.append("  SUB.SUBEJECUTOR_CONTACTO, \n");
		sql.append("  SUB.SUBEJECUTOR_CONVENIO \n");
		sql.append("FROM     \n");
		sql.append("  SUBEJECUTORES SUB     \n");
		sql.append("  LEFT JOIN VW_PERSONAS PER ON SUB.PERSONA_ID=PER.ID     \n");
		sql.append("WHERE     \n");
		sql.append(" SUB.SUBEJECUTOR_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO SUBEJECUTORES ( \n");
	  sqlInsert.append("  SUBEJECUTOR_ID, \n");
	  sqlInsert.append("  PERSONA_ID, \n");
	  sqlInsert.append("  SUBEJECUTOR_EMAIL, \n");
	  sqlInsert.append("  SUBEJECUTOR_CARACTER, \n");
	  sqlInsert.append("  FECHA_LEGITIMACION, \n");
	  sqlInsert.append("  SUBEJECUTOR_NOMBRE, \n");
	  sqlInsert.append("  SUBEJECUTOR_CONTACTO, \n");
	  sqlInsert.append("  SUBEJECUTOR_CONVENIO \n");
	  sqlInsert.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?) \n");
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE SUBEJECUTORES SET  \n");
	  sqlActualiza.append("  PERSONA_ID=?, \n");
	  sqlActualiza.append("  SUBEJECUTOR_EMAIL=?, \n");
	  sqlActualiza.append("  SUBEJECUTOR_CARACTER=?, \n");
	  sqlActualiza.append("  FECHA_LEGITIMACION=?, \n");
	  sqlActualiza.append("  SUBEJECUTOR_NOMBRE=?, \n");
	  sqlActualiza.append("  SUBEJECUTOR_CONTACTO=?, \n");
	  sqlActualiza.append("  SUBEJECUTOR_CONVENIO=? \n");
	  sqlActualiza.append("WHERE SUBEJECUTOR_ID=?  \n");
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM SUBEJECUTORES \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" SUBEJECUTOR_ID=?  \n");
	  
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SubejecutorDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SubejecutorDao#inserta(ar.org.promeba.beans.Subejecutor)
	 */
	@Override
	public void inserta(Subejecutor bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), 
		  bean.getPersona().getId(), bean.getEmail(), bean.getCaracter(), bean.getFechaLegitimacion(), bean.getNombre(), 
		  bean.getContacto(), bean.getConvenio()});
	}	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SubejecutorDao#actualiza(ar.org.promeba.beans.Subejecutor)
	 */
	@Override
	public void actualiza(Subejecutor bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getPersona().getId(),   
			  bean.getEmail(), bean.getCaracter(), bean.getFechaLegitimacion(), bean.getNombre(), 
			  bean.getContacto(), bean.getConvenio(), bean.getId()});
		}	
	
	
	static class SubejecutorMapper implements RowMapper<Subejecutor>{
		  public Subejecutor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Subejecutor bean=new Subejecutor();
			  bean.setId(resultSet.getString("SUBEJECUTOR_ID"));
			  bean.setNombre(resultSet.getString("SUBEJECUTOR_NOMBRE"));
			  bean.setCaracter(resultSet.getString("SUBEJECUTOR_CARACTER"));
			  bean.setFechaLegitimacion(resultSet.getDate("FECHA_LEGITIMACION"));
			  bean.setContacto(resultSet.getString("SUBEJECUTOR_CONTACTO"));
			  bean.setConvenio(resultSet.getString("SUBEJECUTOR_CONVENIO"));
			  bean.setEmail(resultSet.getString("SUBEJECUTOR_EMAIL"));
			  Persona persona=new Persona();
			  persona.setId(resultSet.getString("PERSONA_ID"));
			  persona.setNombre(resultSet.getString("PERSONA_DENOMINACION"));
			  persona.setTipo(resultSet.getString("PERSONA_TIPO"));
			  bean.setPersona(persona);
			  return bean;
		  }
	}
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SubejecutorDao#selecciona(int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Subejecutor> selecciona(int start, int limit, String nombre,  String regionId){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  SUB.SUBEJECUTOR_ID, \n");
		sql.append("  SUB.PERSONA_ID, \n");
		sql.append("  PER.DENOMINACION AS PERSONA_DENOMINACION, \n");
		sql.append("  PER.TIPO AS PERSONA_TIPO, \n");
		sql.append("  SUB.SUBEJECUTOR_EMAIL, \n");
		sql.append("  SUB.SUBEJECUTOR_CARACTER, \n");
		sql.append("  SUB.FECHA_LEGITIMACION, \n");
		sql.append("  SUB.SUBEJECUTOR_NOMBRE, \n");
		sql.append("  SUB.SUBEJECUTOR_CONTACTO, \n");
		sql.append("  SUB.SUBEJECUTOR_CONVENIO \n");
		sql.append("FROM     \n");
		sql.append("  SUBEJECUTORES SUB     \n");
		sql.append("  LEFT JOIN VW_PERSONAS PER ON SUB.PERSONA_ID=PER.ID     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(regionId)){
			sql.append("  AND DOM.REGION_ID='" + regionId + "'     \n");
		}
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND SUB.SUBEJECUTOR_NOMBRE LIKE '%" + nombre + "%'     \n");
		}
		
		
		return jdbcTemplate.query(sql.toString(), new SubejecutorMapper());
	}

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SubejecutorDao#cuenta(java.lang.String)
	 */
	@Override
	public int cuenta(String nombre,  String regionId){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  SUBEJECUTORES SUB     \n");
		sql.append("  LEFT JOIN VW_PERSONAS PER ON SUB.PERSONA_ID=PER.ID     \n");
		sql.append("WHERE 1=1    \n");
		if (!StringUtils.isEmpty(regionId)){
			sql.append("  AND DOM.REGION_ID='" + regionId + "'     \n");
		}
		if (!StringUtils.isEmpty(nombre)){
			sql.append("  AND SUB.SUBEJECUTOR_NOMBRE LIKE '%" + nombre + "%'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}


	public Subejecutor obtiene(String id) {
		Subejecutor resultado=(Subejecutor)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new SubejecutorMapper());
		return resultado;
	}	
	


	

	

}
