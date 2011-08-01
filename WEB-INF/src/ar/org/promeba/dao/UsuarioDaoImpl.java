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
import ar.org.promeba.beans.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlSelect;
	private StringBuffer sqlObtiene;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	private StringBuffer sqlVerifica;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	

	public UsuarioDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO USUARIOS ( \n");
	  sqlInsert.append(" USUARIO_ID,  \n");
	  sqlInsert.append(" USUARIO_LOGIN,  \n");
	  sqlInsert.append(" USUARIO_CLAVE,  \n");
	  sqlInsert.append(" USUARIO_EMAIL,  \n");
	  sqlInsert.append(" PERSONA_ID,  \n");
	  sqlInsert.append(" USUARIO_HABILITADO  \n");
	  sqlInsert.append(" ) VALUES (?, ?, ?, ?, ?, ?)  \n");

	  //obtiene
	  StringBuffer sb =new StringBuffer();
	  sb.append("SELECT  \n");
	  sb.append(" USU.USUARIO_ID,  \n");
	  sb.append(" USU.USUARIO_LOGIN,  \n");
	  sb.append(" USU.USUARIO_EMAIL,  \n");
	  sb.append(" USU.USUARIO_CLAVE,  \n");
	  sb.append(" USU.USUARIO_HABILITADO,  \n");
	  sb.append(" PER.ID,  \n");
	  sb.append(" PER.DENOMINACION,  \n");
	  sb.append(" PER.TIPO  \n");
	  sb.append("FROM  \n");
	  sb.append(" USUARIOS USU \n");
	  sb.append(" INNER JOIN VW_PERSONAS PER ON USU.PERSONA_ID=PER.ID  \n");
	  sb.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
	  sb.append(" LEFT JOIN REGIONES REG ON DOM.REGION_ID=REG.REGION_ID  \n");
	  sb.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
	  sb.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
	  sb.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
	  sb.append("WHERE  \n");
	  sb.append(" USUARIO_ID=?  \n");
	  sqlObtiene=new StringBuffer(sb.toString());
	  
	  //verifica
	  sb =new StringBuffer();
	  sb.append("SELECT  \n");
	  sb.append(" USU.USUARIO_ID,  \n");
	  sb.append(" USU.USUARIO_LOGIN,  \n");
	  sb.append(" USU.USUARIO_EMAIL,  \n");
	  sb.append(" USU.USUARIO_CLAVE,  \n");
	  sb.append(" USU.USUARIO_HABILITADO,  \n");
	  sb.append(" PER.ID,  \n");
	  sb.append(" PER.DENOMINACION,  \n");
	  sb.append(" PER.TIPO  \n");
	  sb.append("FROM  \n");
	  sb.append(" USUARIOS USU \n");
	  sb.append(" INNER JOIN VW_PERSONAS PER ON USU.PERSONA_ID=PER.ID  \n");
	  sb.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
	  sb.append(" LEFT JOIN REGIONES REG ON DOM.REGION_ID=REG.REGION_ID  \n");
	  sb.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
	  sb.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
	  sb.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
	  sb.append("WHERE \n");
	  sb.append("  USUARIO_LOGIN=? \n");
	  sb.append("  AND USUARIO_CLAVE=? \n");
	  sqlVerifica=new StringBuffer(sb.toString());
	  
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE USUARIOS SET  \n");
	  sqlActualiza.append(" USUARIO_LOGIN=?,  \n");
	  sqlActualiza.append(" USUARIO_CLAVE=?,  \n");
	  sqlActualiza.append(" USUARIO_EMAIL=?,  \n");
	  sqlActualiza.append(" PERSONA_ID=?,  \n");
	  sqlActualiza.append(" USUARIO_HABILITADO=?  \n");
	  sqlActualiza.append("WHERE  \n");
	  sqlActualiza.append(" USUARIO_ID=?  \n");
	  
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM USUARIOS \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" USUARIO_ID=?  \n");

	}
	
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	

	public void inserta(Usuario usuario) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { usuario.getId(), usuario.getLogin(), usuario.getClave(), 
		                     usuario.getEmail(), usuario.getPersona().getId(),  usuario.isHabilitado()});
	}	
	
	public void actualiza(Usuario usuario) {
		  jdbcTemplate.update(sqlActualiza.toString(),
			  new Object[] { usuario.getLogin(), usuario.getClave(), 
                             usuario.getEmail(), usuario.getPersona().getId(),
                             usuario.isHabilitado(), usuario.getId()});
	}
	


	
	
	static class UsuarioMapper implements org.springframework.jdbc.core.RowMapper<Usuario>{
		  public Usuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Usuario bean=new Usuario();
			  bean.setId(resultSet.getString("USUARIO_ID"));
			  bean.setLogin(resultSet.getString("USUARIO_LOGIN"));
			  bean.setEmail(resultSet.getString("USUARIO_EMAIL"));
			  bean.setClave(resultSet.getString("USUARIO_CLAVE"));
			  bean.setHabilitado(resultSet.getBoolean("USUARIO_HABILITADO"));
			  Persona per=new Persona();
			  per.setId(resultSet.getString("ID"));
			  per.setNombre(resultSet.getString("DENOMINACION"));
			  per.setTipo(resultSet.getString("TIPO"));
			  bean.setPersona(per);
			  return bean;
		  }
	}
	
	public List<Usuario> selecciona(int offset, int limit, String email, String rolId) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  \n");
		  sqlSelect.append(" USU.USUARIO_ID,  \n");
		  sqlSelect.append(" USU.USUARIO_LOGIN,  \n");
		  sqlSelect.append(" USU.USUARIO_EMAIL,  \n");
		  sqlSelect.append(" USU.USUARIO_CLAVE,  \n");
		  sqlSelect.append(" USU.USUARIO_HABILITADO,  \n");
		  sqlSelect.append(" PER.ID,  \n");
		  sqlSelect.append(" PER.DENOMINACION,  \n");
		  sqlSelect.append(" PER.TIPO  \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" USUARIOS USU \n");
		  sqlSelect.append(" INNER JOIN VW_PERSONAS PER ON USU.PERSONA_ID=PER.ID  \n");
		  sqlSelect.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
		  sqlSelect.append(" LEFT JOIN REGIONES REG ON REG.REGION_ID=DOM.REGION_ID  \n");
		  sqlSelect.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		  sqlSelect.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
		  sqlSelect.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
		  sqlSelect.append("WHERE 1=1  \n");
		  if (!StringUtils.isEmpty(rolId)){
			  sqlSelect.append("AND USU.USUARIO_ID IN (SELECT USUARIO_ID FROM ROLES_POR_USUARIO  ");
			  sqlSelect.append("WHERE ROL_ID='" + rolId + "')  \n");
		  }		  
		  if (!StringUtils.isEmpty(email)){
			  sqlSelect.append("AND USU.USUARIO_EMAIL LIKE '%" + email + "%'  \n"); 
		  }
		  sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		  return jdbcTemplate.query(sqlSelect.toString(), new UsuarioMapper());
	}	
	
	
	public int cuenta(String email, String rolId) {
		  sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" USUARIOS USU \n");
		  sqlSelect.append(" INNER JOIN VW_PERSONAS PER ON USU.PERSONA_ID=PER.ID  \n");
		  sqlSelect.append(" LEFT JOIN DOMICILIOS DOM ON DOM.DOMICILIO_ID=USU.DOMICILIO_ID  \n");
		  sqlSelect.append(" LEFT JOIN REGIONES REG ON REG.REGION_ID=DOM.REGION_ID  \n");
		  sqlSelect.append(" LEFT JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		  sqlSelect.append(" LEFT JOIN DEPARTAMENTOS DEP ON DEP.DEPARTAMENTO_ID=DOM.DEPARTAMENTO_ID  \n");
		  sqlSelect.append(" LEFT JOIN LOCALIDADES LOC ON LOC.LOCALIDAD_ID=DOM.LOCALIDAD_ID  \n");
		  sqlSelect.append("WHERE 1=1  \n");
		  if (!StringUtils.isEmpty(email)){
			  sqlSelect.append("AND USU.USUARIO_EMAIL LIKE '%" + email + "%'  \n"); 
		  }
		  return jdbcTemplate.queryForInt(sqlSelect.toString());
	}	
	
	public Usuario obtiene(String id) {
		Usuario resultado=(Usuario)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new UsuarioMapper());
		return resultado;
	}	
	
	public Usuario verifica(String login, String clave) {
		List<Usuario> beans=jdbcTemplate.query(sqlVerifica.toString(), new Object[] { login, clave }, new UsuarioMapper());
		if (beans.size()>0){
			return beans.get(0);
		}else{
			return null;
		}
	}
	
	

}
