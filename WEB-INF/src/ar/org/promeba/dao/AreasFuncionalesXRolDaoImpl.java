package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.AreaFuncionalXRol;

public class AreasFuncionalesXRolDaoImpl implements AreasFuncionalesXRolDao {
	
	private JdbcTemplate jdbcTemplate;
	private String sqlInsert;
	private String sqlActualiza;
	private String sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public AreasFuncionalesXRolDaoImpl(){
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO AREAS_FUNCIONALES_X_ROL (\n");
		sb.append("  AFXR_ID, \n");
		sb.append("  AREA_FUNCIONAL_ID, \n");
		sb.append("  ROL_ID, \n");
		sb.append("  AFXR_VISION, \n");
		sb.append("  AFXR_ESCRITURA, \n");
		sb.append("  AFXR_LECTURA, \n");
		sb.append("  AFXR_EJECUCION, \n");
		sb.append("  AFXR_IMPRESION \n");
		sb.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?) \n");
		sqlInsert=sb.toString();
		
		sb=new StringBuilder();
		sb.append("UPDATE AREAS_FUNCIONALES_X_ROL  SET \n");
		sb.append("  AREA_FUNCIONAL_ID=?, \n");
		sb.append("  ROL_ID=?, \n");
		sb.append("  AFXR_VISION=?, \n");
		sb.append("  AFXR_ESCRITURA=?, \n");
		sb.append("  AFXR_LECTURA=?, \n");
		sb.append("  AFXR_EJECUCION=?, \n");
		sb.append("  AFXR_IMPRESION=? \n");
		sb.append("WHERE  \n");
		sb.append("  AFXR_ID=? \n");
		sqlActualiza=sb.toString();

		sb=new StringBuilder();
		sb.append("DELETE FROM AREAS_FUNCIONALES_X_ROL   \n");
		sb.append("WHERE  \n");
		sb.append("  AFXR_ID=? \n");
		sqlBorra=sb.toString();
	}
	
	public void inserta(AreaFuncionalXRol bean) {
		  jdbcTemplate.update(sqlInsert.toString(), 
				  new Object[] { bean.getId(), bean.getAreaId(), bean.getRolId(),
			        bean.isVision(), bean.isEscritura(), bean.isLectura(), 
			        bean.isEjecucion(), bean.isImpresion()
		  });
	}
	
	public void modifica(AreaFuncionalXRol bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] { bean.getAreaId(), bean.getRolId(),
			        bean.isVision(), bean.isEscritura(), bean.isLectura(), bean.isEjecucion(),
			        bean.isImpresion(), bean.getId()
		  });
	}	
	

	static class AreaFuncionalXRolMapper implements RowMapper<AreaFuncionalXRol>{
		  public AreaFuncionalXRol mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  AreaFuncionalXRol bean=new AreaFuncionalXRol();
			  bean.setId(resultSet.getString("AFXR_ID"));
			  bean.setAreaId(resultSet.getString("AREA_FUNCIONAL_ID"));
			  bean.setAreaNombre(resultSet.getString("AREA_FUNCIONAL_NOMBRE"));
			  bean.setRolId(resultSet.getString("ROL_ID"));
			  bean.setRolNombre(resultSet.getString("ROL_NOMBRE"));
			  bean.setVision(resultSet.getBoolean("AFXR_VISION"));
			  bean.setEscritura(resultSet.getBoolean("AFXR_ESCRITURA"));
			  bean.setLectura(resultSet.getBoolean("AFXR_LECTURA"));
			  bean.setEjecucion(resultSet.getBoolean("AFXR_EJECUCION"));
			  bean.setImpresion(resultSet.getBoolean("AFXR_IMPRESION"));
			  return bean;
		  }
	}	
	
	public List<AreaFuncionalXRol> seleccionaAreasFuncionalesXRol(int offset, int limit, String rolId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" AXR.AFXR_ID,  \n");
		sqlSelect.append(" ROL.ROL_ID,  \n");
		sqlSelect.append(" ROL.ROL_NOMBRE,  \n");
		sqlSelect.append(" AFU.AREA_FUNCIONAL_ID,  \n");
		sqlSelect.append(" AFU.AREA_FUNCIONAL_NOMBRE,  \n");
		sqlSelect.append(" AXR.AFXR_VISION,  \n");
		sqlSelect.append(" AXR.AFXR_ESCRITURA,  \n");
		sqlSelect.append(" AXR.AFXR_LECTURA,  \n");
		sqlSelect.append(" AXR.AFXR_EJECUCION,  \n");
		sqlSelect.append(" AXR.AFXR_IMPRESION  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" AREAS_FUNCIONALES AFU \n");
		sqlSelect.append(" INNER JOIN AREAS_FUNCIONALES_X_ROL AXR ON AXR.AREA_FUNCIONAL_ID=AFU.AREA_FUNCIONAL_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON AXR.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" ROL.ROL_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND ROL.ROL_ID='" + rolId + "' \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" AFU.AREA_FUNCIONAL_NOMBRE  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new AreaFuncionalXRolMapper());
	}	
	
	public List<AreaFuncionalXRol> areasFuncionalesXRolVisibles(int offset, int limit, String rolId){
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT  \n");
		sqlSelect.append(" AXR.AFXR_ID,  \n");
		sqlSelect.append(" ROL.ROL_ID,  \n");
		sqlSelect.append(" ROL.ROL_NOMBRE,  \n");
		sqlSelect.append(" AFU.AREA_FUNCIONAL_ID,  \n");
		sqlSelect.append(" AFU.AREA_FUNCIONAL_NOMBRE,  \n");
		sqlSelect.append(" AXR.AFXR_VISION,  \n");
		sqlSelect.append(" AXR.AFXR_ESCRITURA,  \n");
		sqlSelect.append(" AXR.AFXR_LECTURA,  \n");
		sqlSelect.append(" AXR.AFXR_EJECUCION,  \n");
		sqlSelect.append(" AXR.AFXR_IMPRESION  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" AREAS_FUNCIONALES AFU \n");
		sqlSelect.append(" INNER JOIN AREAS_FUNCIONALES_X_ROL AXR ON AXR.AREA_FUNCIONAL_ID=AFU.AREA_FUNCIONAL_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON AXR.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" ROL.ROL_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND ROL.ROL_ID='" + rolId + "' \n");
		sqlSelect.append(" AND AXR.AFXR_VISION=TRUE \n");
		sqlSelect.append("ORDER BY   \n");
		sqlSelect.append(" AFU.AREA_FUNCIONAL_NOMBRE  \n");
		sqlSelect.append("OFFSET " + offset + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sqlSelect.toString(), new AreaFuncionalXRolMapper());
	}
	
	public int cuentaAreasFuncionalesXRol(String rolId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" AREAS_FUNCIONALES AFU \n");
		sqlSelect.append(" INNER JOIN AREAS_FUNCIONALES_X_ROL AXR ON AXR.AREA_FUNCIONAL_ID=AFU.AREA_FUNCIONAL_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON AXR.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" ROL.ROL_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND ROL.ROL_ID='" + rolId + "' \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	public int cuentaAreasFuncionalesXRolVisibles(String rolId) {
		StringBuffer sqlSelect = new StringBuffer();
		sqlSelect.append("SELECT COUNT(*)  \n");
		sqlSelect.append("FROM  \n");
		sqlSelect.append(" AREAS_FUNCIONALES AFU \n");
		sqlSelect.append(" INNER JOIN AREAS_FUNCIONALES_X_ROL AXR ON AXR.AREA_FUNCIONAL_ID=AFU.AREA_FUNCIONAL_ID \n");
		sqlSelect.append(" INNER JOIN ROLES ROL ON AXR.ROL_ID=ROL.ROL_ID \n");
		sqlSelect.append("WHERE   \n");
		sqlSelect.append(" ROL.ROL_HABILITADO = TRUE  \n");
		sqlSelect.append(" AND ROL.ROL_ID='" + rolId + "' \n");
		sqlSelect.append(" AND AXR.AFXR_VISION=TRUE \n");
		return jdbcTemplate.queryForInt(sqlSelect.toString());
	}
	
	
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	
	
	

}
