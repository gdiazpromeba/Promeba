package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import ar.org.promeba.beans.Rol;
import ar.org.promeba.beans.RolXUsuario;
import ar.org.promeba.beans.TipoDocumento;
import ar.org.promeba.beans.Usuario;
import ar.org.promeba.dao.RolDaoImpl.RolMapper;
import ar.org.promeba.dao.UsuarioDaoImpl.UsuarioMapper;

public class RolesXUsuarioDaoImpl implements RolesXUsuarioDao{
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInserta;
	private StringBuffer sqlBorra;
	private StringBuffer sqlExiste;

	
    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	

	public RolesXUsuarioDaoImpl(){
	  //borra
	  sqlBorra = new StringBuffer();
	  sqlBorra.append("DELETE FROM ROLES_X_USUARIO \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" ROL_X_USUARIO_ID=?  \n");
	  //inserta 
	  sqlInserta = new StringBuffer();
	  sqlInserta.append("INSERT INTO ROLES_X_USUARIO ( \n");
	  sqlInserta.append(" ROL_X_USUARIO_ID,  \n");
	  sqlInserta.append(" USUARIO_ID,  \n");
	  sqlInserta.append(" ROL_ID  \n");
	  sqlInserta.append(" ) VALUES (?, ?, ?)  \n");
	  //existe 
	  sqlExiste = new StringBuffer();
	  sqlExiste.append("SELECT COUNT(*) FROM  \n");
	  sqlExiste.append(" ROLES_X_USUARIO  \n");
	  sqlExiste.append("WHERE  \n");
	  sqlExiste.append(" USUARIO_ID=?  \n");
	  sqlExiste.append(" AND ROL_ID=?)  \n");

	}
	
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), new Object[] { id});
		}	

	public boolean existe(String usuarioId, String rolId) {
	  int filas=jdbcTemplate.queryForInt(sqlExiste.toString(), new Object[] { usuarioId, rolId});
	  return filas>0;
	}
	
	public void inserta(RolXUsuario rolXUsuario) {
		  jdbcTemplate.update(sqlInserta.toString(), new Object[] { rolXUsuario.getId(), rolXUsuario.getUsuarioId(), rolXUsuario.getRolId()});
	}
	
	

	
	public int cuentaRolesPorUsuario(String usuarioId, boolean relacionados) {
		  StringBuffer sqlSelect =new StringBuffer();
		  sqlSelect.append("SELECT  COUNT(*) \n");
		  sqlSelect.append("FROM  \n");
		  sqlSelect.append(" ROLES  \n");
		  sqlSelect.append("WHERE  \n");
		  sqlSelect.append(" ROL_HABILITADO = TRUE  \n");
		  if (relacionados){
			  sqlSelect.append(" AND ROL_ID IN (SELECT ROL_ID FROM ");
			  sqlSelect.append(" ROLES_X_USUARIO WHERE USUARIO_ID='" + usuarioId  + "') \n");
		  }else{
			  sqlSelect.append(" AND ROL_ID NOT IN (SELECT ROL_ID FROM ");
			  sqlSelect.append(" ROLES_X_USUARIO WHERE USUARIO_ID='" + usuarioId  + "') \n");
		  }
	      return jdbcTemplate.queryForInt(sqlSelect.toString());
		}	
	
	static class RolXUsuarioMapper implements org.springframework.jdbc.core.RowMapper<RolXUsuario>{
		  public RolXUsuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  RolXUsuario bean=new RolXUsuario();
			  bean.setId(resultSet.getString("ROL_X_USUARIO_ID"));
			  bean.setUsuarioLogin(resultSet.getString("USUARIO_LOGIN"));
			  bean.setUsuarioId(resultSet.getString("USUARIO_ID"));
			  bean.setRolNombre(resultSet.getString("ROL_NOMBRE"));
			  bean.setRolId(resultSet.getString("ROL_ID"));
			  return bean;
		  }
	}
	
	
	public List<RolXUsuario> seleccionaRolesXUsuario(int offset, int limit, String usuarioId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" ROU.ROL_X_USUARIO_ID,  \n");
		sqlSelect.append(" USU.USUARIO_ID,  \n");
		sqlSelect.append(" USU.USUARIO_LOGIN,  \n");
		sqlSelect.append(" ROL.ROL_ID,  \n");
		sqlSelect.append(" ROL.ROL_NOMBRE  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" USUARIOS USU \n");
		sqlSelect.append(" INNER JOIN ROLES_X_USUARIO ROU ON ROU.USUARIO_ID=USU.USUARIO_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON ROU.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" USU.USUARIO_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND USU.USUARIO_ID='" + usuarioId + "' \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new RolXUsuarioMapper());
	}	
	
	public int cuentaRolesXUsuario(String usuarioId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  COUNT(*) \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" USUARIOS USU \n");
		sqlSelect.append(" INNER JOIN ROLES_X_USUARIO ROU ON ROU.USUARIO_ID=USU.USUARIO_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON ROU.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" USU.USUARIO_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND USU.USUARIO_ID='" + usuarioId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}		
	
	
	public List<RolXUsuario> seleccionaUsuariosXRol(int offset, int limit, String rolId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" ROU.ROL_X_USUARIO_ID,  \n");
		sqlSelect.append(" USU.USUARIO_ID,  \n");
		sqlSelect.append(" USU.USUARIO_LOGIN,  \n");
		sqlSelect.append(" ROL.ROL_ID,  \n");
		sqlSelect.append(" ROL.ROL_NOMBRE  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" USUARIOS USU \n");
		sqlSelect.append(" INNER JOIN ROLES_X_USUARIO ROU ON ROU.USUARIO_ID=USU.USUARIO_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON ROU.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" USU.USUARIO_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND ROL.ROL_ID='" + rolId + "' \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new RolXUsuarioMapper());
	}
	
	public int cuentaUsuariosXRol(String rolId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  COUNT(*) \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" USUARIOS USU \n");
		sqlSelect.append(" INNER JOIN ROLES_X_USUARIO ROU ON ROU.USUARIO_ID=USU.USUARIO_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON ROU.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" USU.USUARIO_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND ROL.ROL_ID='" + rolId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}	
	
	
	

}
