package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import ar.org.promeba.beans.Persona;

public class PersonaDaoImpl implements PersonaDao  {

	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sql;
	private String sqlObtiene;

	
	
	public PersonaDaoImpl() {

		// obtiene
		sql = new StringBuffer();
		sql.append("SELECT  \n");
		sql.append(" ID,  \n");
		sql.append(" DENOMINACION,   \n");
		sql.append(" TIPO   \n");
		sql.append("FROM  \n");
		sql.append(" VW_PERSONAS \n");
		sql.append("WHERE  \n");
		sql.append(" ID=?  \n");
		sqlObtiene = sql.toString();

	}
	
    @Autowired
    @Qualifier("basicDataSource")	
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}	
	
	
	
	static class PersonaMapper implements org.springframework.jdbc.core.RowMapper<Persona>{
		  public Persona mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Persona bean=new Persona();
			  bean.setId(resultSet.getString("ID"));
			  bean.setNombre(resultSet.getString("DENOMINACION"));
			  bean.setTipo(resultSet.getString("TIPO"));
			  return bean;
		  }
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaDao#selecciona(int, int, java.lang.String, boolean, boolean)
	 */
	@Override
	public List<Persona> selecciona(int offset, int limit, String denominacion, boolean fisica, boolean juridica) {
		sql = new StringBuffer();
		sql.append("SELECT  \n");
		sql.append(" ID,  \n");
		sql.append(" DENOMINACION,  \n");
		sql.append(" TIPO  \n");
		sql.append("FROM  \n");
		sql.append(" VW_PERSONAS \n");
		sql.append("WHERE 1=1  \n");
		if (fisica ^ juridica){
			if (fisica){
				sql.append("AND TIPO='Física'  \n");
			}else{
				sql.append("AND TIPO='Jurídica'  \n");
			}
		}else if (!fisica && !juridica){
			sql.append("AND FALSE  \n");
		}
		if (!StringUtils.isEmpty(denominacion)){
			  sql.append("AND DENOMINACION LIKE '%" + denominacion + "%'  \n"); 
		}		
		sql.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new PersonaMapper());		
	}

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaDao#cuenta(java.lang.String, boolean, boolean)
	 */
	@Override
	public int cuenta(String denominacion, boolean fisica, boolean juridica) {
		sql = new StringBuffer();
		sql.append("SELECT  COUNT(*) \n");
		sql.append("FROM  \n");
		sql.append(" VW_PERSONAS \n");
		sql.append("WHERE 1=1  \n");
		if (fisica ^ juridica){
			if (fisica){
				sql.append("AND TIPO='Física'  \n");
			}else{
				sql.append("AND TIPO='Jurídica'  \n");
			}
		}else if (!fisica && !juridica){
			sql.append("AND FALSE  \n");
		}
		if (!StringUtils.isEmpty(denominacion)){
			  sql.append("AND DENOMINACION LIKE '%" + denominacion + "%'  \n"); 
		}		
		return jdbcTemplate.queryForInt(sql.toString());
	}

	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.PersonaDao#obtiene(java.lang.String)
	 */
	@Override
	public Persona obtiene(String id) {
		Persona resultado=(Persona)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new PersonaMapper());
		return resultado;
	}

}
