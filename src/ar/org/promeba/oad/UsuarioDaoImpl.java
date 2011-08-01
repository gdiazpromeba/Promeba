package ar.org.promeba.oad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ar.org.promeba.beans.Rol;
import ar.org.promeba.beans.TipoDocumento;
import ar.org.promeba.beans.Usuario;
import ar.org.promeba.dao.TipoDocumentoDaoImpl.TipoDocumentoMapper;

public class UsuarioDaoImpl implements UsuarioDao{
	
	private JdbcTemplate jdbcTemplate;
	private StringBuffer sqlInsert;
	private StringBuffer sqlSelect;
	private StringBuffer sqlObtiene;
	private StringBuffer sqlActualiza;
	private StringBuffer sqlBorra;
	

	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public UsuarioDaoImpl(){
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO USUARIOS VALUES (?, ?, ?, ?, ?, ?, ?) \n");
	  //selecciona
	  sqlSelect =new StringBuffer();
	  sqlSelect.append("SELECT  \n");
	  sqlSelect.append(" USU.USUARIO_ID,  \n");
	  sqlSelect.append(" USU.USUARIO_NOMBRE,  \n");
	  sqlSelect.append(" USU.USUARIO_APELLIDO,  \n");
	  sqlSelect.append(" USU.ROL_ID,  \n");
	  sqlSelect.append(" ROL.ROL_NOMBRE,  \n");
	  sqlSelect.append(" USU.TIPO_DOCUMENTO_ID,  \n");
	  sqlSelect.append(" USU.DOCUMENTO_NUMERO,  \n");
	  sqlSelect.append(" TID.TIPO_DOCUMENTO_NOMBRE,  \n");
	  sqlSelect.append(" USU.USUARIO_HABILITADO  \n");
	  sqlSelect.append("FROM  \n");
	  sqlSelect.append(" USUARIOS USU \n");
	  sqlSelect.append(" INNER JOIN TIPOS_DOCUMENTO TID ON USU.TIPO_DOCUMENTO_ID=TID.TIPO_DOCUMENTO_ID  \n");
	  sqlSelect.append(" INNER JOIN ROLES ROL ON USU.ROL_ID=ROL.ROL_ID  \n");
	  sqlSelect.append("OFFSET ? LIMIT ?  \n");
	  //obtiene
	  sqlObtiene =new StringBuffer();
	  sqlObtiene.append("SELECT  \n");
	  sqlObtiene.append(" USU.USUARIO_ID,  \n");
	  sqlObtiene.append(" USU.USUARIO_NOMBRE,  \n");
	  sqlObtiene.append(" USU.USUARIO_APELLIDO,  \n");
	  sqlObtiene.append(" USU.ROL_ID,  \n");
	  sqlObtiene.append(" ROL.ROL_NOMBRE,  \n");
	  sqlObtiene.append(" USU.TIPO_DOCUMENTO_ID,  \n");
	  sqlObtiene.append(" USU.DOCUMENTO_NUMERO,  \n");
	  sqlObtiene.append(" TID.TIPO_DOCUMENTO_NOMBRE,  \n");
	  sqlObtiene.append(" USU.USUARIO_HABILITADO  \n");
	  sqlObtiene.append("FROM  \n");
	  sqlObtiene.append(" USUARIOS USU \n");
	  sqlObtiene.append(" INNER JOIN TIPOS_DOCUMENTO TID ON USU.TIPO_DOCUMENTO_ID=TID.TIPO_DOCUMENTO_ID  \n");
	  sqlObtiene.append(" INNER JOIN ROLES ROL ON USU.ROL_ID=ROL.ROL_ID  \n");
	  sqlObtiene.append("WHERE  \n");
	  sqlObtiene.append(" USUARIO_ID=?  \n");  
	  //modifica
	  sqlActualiza =new StringBuffer();
	  sqlActualiza.append("UPDATE USUARIOS SET  \n");
	  sqlActualiza.append(" USUARIO_NOMBRE=?,  \n");
	  sqlActualiza.append(" USUARIO_APELLIDO=?,  \n");
	  sqlActualiza.append(" ROL_ID=?,  \n");
	  sqlActualiza.append(" TIPO_DOCUMENTO_ID=?,  \n");
	  sqlActualiza.append(" DOCUMENTO_NUMERO=?,  \n");
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
			  new Object[] { usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getRol().getId(), usuario.getTipoDocumento().getId(),  Long.parseLong(usuario.getDocumentoNumero()),  usuario.isHabilitado()});
	}	
	
	public void actualiza(Usuario usuario) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { usuario.getNombre(), usuario.getApellido(), usuario.getRol().getId(), usuario.getTipoDocumento().getId(), Long.parseLong(usuario.getDocumentoNumero()),  usuario.isHabilitado(),  usuario.getId()});
		}	
	
	
	static class UsuarioMapper implements org.springframework.jdbc.core.RowMapper<Usuario>{
		  public Usuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Usuario bean=new Usuario();
			  TipoDocumento tid=new TipoDocumento();
			  Rol rol=new Rol();
			  tid.setId(resultSet.getString("TIPO_DOCUMENTO_ID"));
			  tid.setNombre(resultSet.getString("TIPO_DOCUMENTO_NOMBRE"));
			  bean.setId(resultSet.getString("USUARIO_ID"));
			  bean.setNombre(resultSet.getString("USUARIO_NOMBRE"));
			  bean.setApellido(resultSet.getString("USUARIO_APELLIDO"));
			  rol.setId(resultSet.getString("ROL_ID"));
			  rol.setNombre(resultSet.getString("ROL_NOMBRE"));
			  bean.setRol(rol);
			  bean.setTipoDocumento(tid);
			  bean.setDocumentoNumero(String.valueOf(resultSet.getLong("DOCUMENTO_NUMERO")));
			  bean.setHabilitado(resultSet.getBoolean("USUARIO_HABILITADO"));
			  return bean;
		  }
	}
	
	public List<Usuario> selecciona(int offset, int limit) {
		return jdbcTemplate.query(sqlSelect.toString(), new Object[] { offset, limit }, new UsuarioMapper());
	}	
	
	public Usuario obtiene(String id) {
		List<Usuario> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new UsuarioMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}	
	
	

}
