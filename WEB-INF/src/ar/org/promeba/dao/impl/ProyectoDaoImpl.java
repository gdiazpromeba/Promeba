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

import ar.org.promeba.beans.Proyecto;
import ar.org.promeba.dao.ProyectoDao;

public class ProyectoDaoImpl implements ProyectoDao    {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuilder sqlInsert;
	private StringBuilder sqlActualiza;
	private StringBuilder sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public ProyectoDaoImpl(){
	  StringBuilder sb;
	  //inserta
	  sb=new StringBuilder();
	  sb.append("INSERT INTO PROYECTOS ( \n");
	  sb.append("  PROTECTO_ID, \n");
	  sb.append("  PROYECTO_DESCRIPCION, \n");
	  sb.append("  SOLICITUD_ID, \n");
	  sb.append("  SUBEJECUTOR_ID, \n");
	  sb.append("  PRESUPUESTO_ESTIMADO, \n");
	  sb.append("  CANTIDAD_LOTES, \n");
	  sb.append("  PROYECTO_ESTADO, \n");
	  sb.append("  FECHA_DESDE, \n");
	  sb.append("  FECHA_HASTA, \n");
	  sb.append("  MESA_GESTION_ID, \n");
	  sb.append("  SIT_DOM_ID, \n");
	  sb.append("  TIPO_INVERSION_ID \n");
	  sb.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");
	  sqlInsert=new StringBuilder(sb.toString());
	  
  
	  //modifica
	  sb=new StringBuilder();
	  sb.append("UPDATE PROYECTOS SET \n");
	  sb.append("  PROYECTO_DESCRIPCION=?, \n");
	  sb.append("  SOLICITUD_ID=?, \n");
	  sb.append("  SUBEJECUTOR_ID=?, \n");
	  sb.append("  PRESUPUESTO_ESTIMADO=?, \n");
	  sb.append("  CANTIDAD_LOTES=?, \n");
	  sb.append("  PROYECTO_ESTADO=?, \n");
	  sb.append("  FECHA_DESDE=?, \n");
	  sb.append("  FECHA_HASTA=?, \n");
	  sb.append("  MESA_GESTION_ID=?, \n");
	  sb.append("  SIT_DOM_ID=?, \n");
	  sb.append("  TIPO_INVERSION_ID=? \n");
	  sb.append("WHERE \n");
	  sb.append("  PROYECTO_ID=? \n");
	  sqlActualiza=new StringBuilder(sb.toString());

	  //borra
	  sb =new StringBuilder();
	  sb.append("DELETE FROM PROYECTOS \n");
	  sb.append("WHERE  \n");
	  sb.append(" PROYECTO_ID=?  \n");
	  sqlBorra=new StringBuilder(sb.toString());
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProyectoDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	




	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProyectoDao#inserta(ar.org.promeba.beans.Proyecto)
	 */
	@Override
	public void inserta(Proyecto bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getDescripcion(), bean.getSolicitudId(), bean.getSubejecutorId(),
		  		bean.getPresupuestoEstimado(), bean.getCantidadLotes(), bean.getEstado(), 
		  		bean.getFechaDesde(), bean.getFechaHasta(), bean.getMesaGestionId(),
		  		bean.getSituacionDominialId(), bean.getTipoInversionId()
	  });
	}	
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProyectoDao#actualiza(ar.org.promeba.beans.Proyecto)
	 */
	@Override
	public void actualiza(Proyecto bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				new Object[] {
			                bean.getDescripcion(), bean.getSolicitudId(), bean.getSubejecutorId(),
			  		bean.getPresupuestoEstimado(), bean.getCantidadLotes(), bean.getEstado(), 
			  		bean.getFechaDesde(), bean.getFechaHasta(), bean.getMesaGestionId(),
			  		bean.getSituacionDominialId(), bean.getTipoInversionId(),
			  		bean.getId(), 
			  		
	  });

        }	
	
	
	static class ProyectoMapper implements RowMapper<Proyecto>{
		  public Proyecto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		          Proyecto bean=new Proyecto();
			  bean.setId(resultSet.getString("PROYECTO_ID"));
			  bean.setDescripcion(resultSet.getString("PROYECTO_DESCRIPCION"));
			  bean.setSolicitudId(resultSet.getString("SOLICITUD_ID"));
			  bean.setSolicitudDescripcion(resultSet.getString("SOLICITUD_DESCRIPCION"));
			  bean.setSubejecutorId(resultSet.getString("SUBEJECUTOR_ID"));
			  bean.setSubejecutorNombre(resultSet.getString("SUBEJECUTOR_NOMBRE"));
			  bean.setPresupuestoEstimado(resultSet.getBigDecimal("PRESUPUESTO_ESTIMADO"));
			  bean.setCantidadLotes(resultSet.getInt("CANTIDAD_LOTES"));
			  bean.setEstado(resultSet.getString("SOLICITUD_ESTADO"));
			  bean.setFechaDesde(resultSet.getDate("FECHA_DESDE"));
			  bean.setFechaHasta(resultSet.getDate("FECHA_HASTA"));
			  bean.setSituacionDominialId(resultSet.getString("SIT_DOM_ID"));
			  bean.setSituacionDominialNombre(resultSet.getString("SIT_DOM_NOMBRE"));
			  bean.setTipoInversionId(resultSet.getString("TIPO_INVERSION_ID"));
			  bean.setTipoInversionNombre(resultSet.getString("TIPO_INVERSION_NOMBRE"));
			  bean.setMesaGestionId(resultSet.getString("MESA_GESTION_ID"));
			  return bean;
		  }
	}
	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProyectoDao#selecciona(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Proyecto> selecciona(int start, int limit,  String subejecutorId, String estado, String provinciaId, String regionId) {
		  StringBuilder sb=new StringBuilder(); 
		  sb.append("SELECT  \n");
		  sb.append("  PROY.PROYECTO_ID, \n");
		  sb.append("  PROY.PROYECTO_DESCRIPCION, \n");
		  sb.append("  PROY.SOLICITUD_ID, \n");
		  sb.append("  PROY.SOLICITUD_DESCRIPCION, \n");
		  sb.append("  PROY.SUBEJECUTOR_ID, \n");
		  sb.append("  SUB.SUBEJECUTOR_NOMBRE, \n");
		  sb.append("  PROY.PRESUPUESTO_ESTIMADO, \n");
		  sb.append("  PROY.CANTIDAD_LOTES, \n");
		  sb.append("  PROY.PROYECTO_ESTADO, \n");
		  sb.append("  PROY.FECHA_DESDE, \n");
		  sb.append("  PROY.FECHA_HASTA, \n");
		  sb.append("  PROY.SIT_DOM_ID, \n");
		  sb.append("  SID.SIT_DOM_NOMBRE, \n");
		  sb.append("  PROY.TIPO_INVERSION_ID, \n");
		  sb.append("  TIN.TIPO_INVERSION_NOMBRE \n");
		  sb.append("  PROY.MESA_GESTION_ID \n");
		  sb.append("FROM  \n");
		  sb.append(" PROYECTOS PROY  \n");
		  sb.append(" INNER JOIN SOLICITUDES SOL ON SOL.SOLICITUD_ID = PROY.SOLICITUD_ID  \n");
		  sb.append(" INNER JOIN SUBEJECUTORES SUB ON PROY.SUBEJECUTOR_ID = SUB.SUBEJECUTOR_ID  \n");
		  sb.append(" INNER JOIN PERSONAS_JURIDICAS PEJ ON SUB.PERSONA_ID = PEJ.PEJ_ID  \n");
		  sb.append(" INNER JOIN DOMICILIOS DOM ON PEJ.DOMICILIO_ID = DOM.DOMICILIO_ID  \n");
		  sb.append(" INNER JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID = DOM.PROVINCIA_ID  \n");
		  sb.append(" INNER JOIN REGIONES REG ON REG.REGION_ID = PRO.REGION_ID  \n");
		  sb.append(" INNER JOIN SITUACIONES_DOMINIALES SID ON PROY.SIT_DOM_ID=SID.SIT_DOM_ID  \n");
		  sb.append(" INNER JOIN TIPOS_INVERSION TIN ON PROY.TIPO_INVERSION_ID = TIN.TIPO_INVERSION_ID  \n");
		  sb.append("WHERE 1=1 \n");
		  if (!StringUtils.isEmpty(subejecutorId)){
			  sb.append(" AND PROY.SUBEJECUTOR_ID='" + subejecutorId + "' \n");
		  }
		  if (!StringUtils.isEmpty(estado)){
			  sb.append(" AND PROY.SOLICITUD_ESTADO='" + estado + "' \n");
		  }
		  if (!StringUtils.isEmpty(provinciaId)){
			  sb.append(" AND PRO.PROVINCIA_ID='" + provinciaId + "' \n");
		  }
		  if (!StringUtils.isEmpty(regionId)){
			  sb.append(" AND REG.REGION_ID='" + regionId + "' \n");
		  }
		  sb.append("ORDER BY  \n");
		  sb.append(" PROY.PROYECTO_DESCRIPCION,  \n");
		  sb.append(" PROY.PRESUPUESTO_ESTIMADO  \n");
		  sb.append("OFFSET " + start + "  LIMIT " + limit + "  \n");
          return jdbcTemplate.query(sb.toString(), new ProyectoMapper());
	}
	


	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProyectoDao#cuenta(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int cuenta(String subejecutorId, String estado, String provinciaId, String regionId) {
		  StringBuilder sb=new StringBuilder(); 
		  sb.append("SELECT COUNT(*)  \n");
		  sb.append("FROM  \n");
		  sb.append(" PROYECTOS PROY  \n");
		  sb.append(" INNER JOIN SOLICITUDES SOL ON SOL.SOLICITUD_ID = PROY.SOLICITUD_ID  \n");
		  sb.append(" INNER JOIN SUBEJECUTORES SUB ON PROY.SUBEJECUTOR_ID = SUB.SUBEJECUTOR_ID  \n");
		  sb.append(" INNER JOIN PERSONAS_JURIDICAS PEJ ON SUB.PERSONA_ID = PEJ.PEJ_ID  \n");
		  sb.append(" INNER JOIN DOMICILIOS DOM ON PEJ.DOMICILIO_ID = DOM.DOMICILIO_ID  \n");
		  sb.append(" INNER JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID = DOM.PROVINCIA_ID  \n");
		  sb.append(" INNER JOIN REGIONES REG ON REG.REGION_ID = PRO.REGION_ID  \n");
		  sb.append(" INNER JOIN SITUACIONES_DOMINIALES SID ON PROY.SIT_DOM_ID=SID.SIT_DOM_ID  \n");
		  sb.append(" INNER JOIN TIPOS_INVERSION TIN ON PROY.TIPO_INVERSION_ID = TIN.TIPO_INVERSION_ID  \n");
		  sb.append("WHERE 1=1 \n");
		  if (!StringUtils.isEmpty(subejecutorId)){
			  sb.append(" AND PROY.SUBEJECUTOR_ID='" + subejecutorId + "' \n");
		  }
		  if (!StringUtils.isEmpty(estado)){
			  sb.append(" AND PROY.SOLICITUD_ESTADO='" + estado + "' \n");
		  }
		  if (!StringUtils.isEmpty(provinciaId)){
			  sb.append(" AND PRO.PROVINCIA_ID='" + provinciaId + "' \n");
		  }
		  if (!StringUtils.isEmpty(regionId)){
			  sb.append(" AND REG.REGION_ID='" + regionId + "' \n");
		  }
	      return jdbcTemplate.queryForInt(sb.toString());
		}



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.ProyectoDao#obtiene(java.lang.String)
	 */
	@Override
	public Proyecto obtiene(String id) {
	    throw new RuntimeException("método no implementado");
	}
	

}
