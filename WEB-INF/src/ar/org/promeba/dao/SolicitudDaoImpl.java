package ar.org.promeba.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.Solicitud;

public class SolicitudDaoImpl implements SolicitudDao   {
	
	private JdbcTemplate jdbcTemplate;
	private StringBuilder sqlInsert;
	private StringBuilder sqlSelect;
	private StringBuilder sqlObtiene;
	private StringBuilder sqlActualiza;
	private StringBuilder sqlBorra;
	

    @Autowired
    @Qualifier("basicDataSource")
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	public SolicitudDaoImpl(){
	  StringBuilder sb;
	  //inserta
	  sb=new StringBuilder();
	  sb.append("INSERT INTO SOLICITUDES ( \n");
	  sb.append("  SOLICITUD_ID, \n");
	  sb.append("  SOLICITUD_DESCRIPCION, \n");
	  sb.append("  SUBEJECUTOR_ID, \n");
	  sb.append("  SOLICITUD_VINCULO, \n");
	  sb.append("  PRESUPUESTO_ESTIMADO, \n");
	  sb.append("  CANTIDAD_LOTES, \n");
	  sb.append("  SOLICITUD_ESTADO, \n");
	  sb.append("  FECHA_DESDE, \n");
	  sb.append("  FECHA_HASTA, \n");
	  sb.append("  FECHA_INGRESO_POA, \n");
	  sb.append("  FECHA_INGRESO_PGEP, \n");
	  sb.append("  FECHA_INGRESO_PA \n");
	  sb.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");
	  sqlInsert=new StringBuilder(sb.toString());
	  
	  
	  //obtiene
	  sb=new StringBuilder(); 
	  sb.append("SELECT  \n");
	  sb.append("  SOL.SOLICITUD_ID, \n");
	  sb.append("  SOL.SOLICITUD_DESCRIPCION, \n");
	  sb.append("  SOL.SUBEJECUTOR_ID, \n");
	  sb.append("  SUB.SUBEJECUTOR_NOMBRE, \n");
	  sb.append("  SOL.SOLICITUD_VINCULO, \n");
	  sb.append("  SOL.PRESUPUESTO_ESTIMADO, \n");
	  sb.append("  SOL.CANTIDAD_LOTES, \n");
	  sb.append("  SOL.SOLICITUD_ESTADO, \n");
	  sb.append("  SOL.FECHA_DESDE, \n");
	  sb.append("  SOL.FECHA_HASTA, \n");
	  sb.append("  SOL.FECHA_INGRESO_POA, \n");
	  sb.append("  SOL.FECHA_INGRESO_PGEP, \n");
	  sb.append("  SOL.FECHA_INGRESO_PA \n");
	  sb.append("FROM  \n");
	  sb.append(" SOLICITUDES SOL  \n");
	  sb.append(" INNER JOIN SUBEJECUTORES SUB ON SOL.SUBEJECUTOR_ID=SUB.SUBEJECUTOR_ID  \n");
	  sb.append("WHERE  \n");
	  sb.append(" SUB.SOLICITUD_ID=?  \n");
	  sqlObtiene=new StringBuilder(sb.toString());

	  
	  //modifica
	  sb=new StringBuilder();
	  sb.append("UPDATE SOLICITUDES SET \n");
	  sb.append("  SOLICITUD_DESCRIPCION=?, \n");
	  sb.append("  SUBEJECUTOR_ID=?, \n");
	  sb.append("  SOLICITUD_VINCULO=?, \n");
	  sb.append("  PRESUPUESTO_ESTIMADO=?, \n");
	  sb.append("  CANTIDAD_LOTES=?, \n");
	  sb.append("  SOLICITUD_ESTADO=?, \n");
	  sb.append("  FECHA_DESDE=?, \n");
	  sb.append("  FECHA_HASTA=?, \n");
	  sb.append("  FECHA_INGRESO_POA=?, \n");
	  sb.append("  FECHA_INGRESO_PGEP=?, \n");
	  sb.append("  FECHA_INGRESO_PA=? \n");
	  sb.append("WHERE \n");
	  sb.append("  SOLICITUD_ID=? \n");
	  sqlActualiza=new StringBuilder(sb.toString());

	  //borra
	  sb =new StringBuilder();
	  sb.append("DELETE FROM SOLICITUDES \n");
	  sb.append("WHERE  \n");
	  sb.append(" SOLICITUD_ID=?  \n");
	  sqlBorra=new StringBuilder(sb.toString());
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SolicitudDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SolicitudDao#inserta(ar.org.promeba.beans.Solicitud)
	 */
	@Override
	public void inserta(Solicitud bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getDescripcion(), bean.getSubejecutorId(),
		  		bean.getLink(), bean.getPresupuestoEstimado(), bean.getCantidadLotes(), bean.getEstado(), 
		  		bean.getFechaDesde(), bean.getFechaHasta(), bean.getFechaIngresoPOA(), 
		  		bean.getFechaIngresoPGEP(), bean.getFechaIngresoPA()
	  });
	}	
	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SolicitudDao#actualiza(ar.org.promeba.beans.Solicitud)
	 */
	@Override
	public void actualiza(Solicitud bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				new Object[] { bean.getDescripcion(), bean.getSubejecutorId(),
		  		bean.getLink(), bean.getPresupuestoEstimado(), bean.getCantidadLotes(), bean.getEstado(), 
		  		bean.getFechaDesde(), bean.getFechaHasta(), bean.getFechaIngresoPOA(), 
		  		bean.getFechaIngresoPGEP(), bean.getFechaIngresoPA(), 
		  		bean.getId(), 
	  });

        }	
	
	
	static class SolicitudMapper implements RowMapper<Solicitud>{
		  public Solicitud mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  Solicitud bean=new Solicitud();
			  bean.setId(resultSet.getString("SOLICITUD_ID"));
			  bean.setDescripcion(resultSet.getString("SOLICITUD_DESCRIPCION"));
			  bean.setSubejecutorId(resultSet.getString("SUBEJECUTOR_ID"));
			  bean.setSubejecutorNombre(resultSet.getString("SUBEJECUTOR_NOMBRE"));
			  bean.setLink(resultSet.getString("SOLICITUD_VINCULO"));
			  bean.setPresupuestoEstimado(resultSet.getBigDecimal("PRESUPUESTO_ESTIMADO"));
			  bean.setCantidadLotes(resultSet.getInt("CANTIDAD_LOTES"));
			  bean.setEstado(resultSet.getString("SOLICITUD_ESTADO"));
			  bean.setFechaDesde(resultSet.getDate("FECHA_DESDE"));
			  bean.setFechaHasta(resultSet.getDate("FECHA_HASTA"));
			  bean.setFechaIngresoPOA(resultSet.getDate("FECHA_INGRESO_POA"));
			  bean.setFechaIngresoPGEP(resultSet.getDate("FECHA_INGRESO_PGEP"));
			  bean.setFechaIngresoPA(resultSet.getDate("FECHA_INGRESO_PA"));
			  return bean;
		  }
	}
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SolicitudDao#selecciona(int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Solicitud> selecciona(int start, int limit,  String subejecutorId, String estado, String provinciaId, String regionId) {
		  StringBuilder sb=new StringBuilder(); 
		  sb.append("SELECT  \n");
		  sb.append("  SOL.SOLICITUD_ID, \n");
		  sb.append("  SOL.SOLICITUD_DESCRIPCION, \n");
		  sb.append("  SOL.SUBEJECUTOR_ID, \n");
		  sb.append("  SUB.SUBEJECUTOR_NOMBRE, \n");
		  sb.append("  SOL.SOLICITUD_VINCULO, \n");
		  sb.append("  SOL.PRESUPUESTO_ESTIMADO, \n");
		  sb.append("  SOL.CANTIDAD_LOTES, \n");
		  sb.append("  SOL.SOLICITUD_ESTADO, \n");
		  sb.append("  SOL.FECHA_DESDE, \n");
		  sb.append("  SOL.FECHA_HASTA, \n");
		  sb.append("  SOL.FECHA_INGRESO_POA, \n");
		  sb.append("  SOL.FECHA_INGRESO_PGEP, \n");
		  sb.append("  SOL.FECHA_INGRESO_PA \n");
		  sb.append("FROM  \n");
		  sb.append(" SOLICITUDES SOL  \n");
		  sb.append(" INNER JOIN SUBEJECUTORES SUB ON SOL.SUBEJECUTOR_ID=SUB.SUBEJECUTOR_ID  \n");
		  sb.append(" INNER JOIN PERSONAS_JURIDICAS PEJ ON SUB.PERSONA_ID=PEJ.PEJ_ID  \n");
		  sb.append(" INNER JOIN DOMICILIOS DOM ON PEJ.DOMICILIO_ID=DOM.DOMICILIO_ID  \n");
		  sb.append(" INNER JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		  sb.append(" INNER JOIN REGIONES REG ON REG.REGION_ID=PRO.REGION_ID  \n");
		  sb.append("WHERE 1=1 \n");
		  if (!StringUtils.isEmpty(subejecutorId)){
			  sb.append(" AND SOL.SUBEJECUTOR_ID='" + subejecutorId + "' \n");
		  }
		  if (!StringUtils.isEmpty(estado)){
			  sb.append(" AND SOL.SOLICITUD_ESTADO='" + estado + "' \n");
		  }
		  if (!StringUtils.isEmpty(provinciaId)){
			  sb.append(" AND PRO.PROVINCIA_ID='" + provinciaId + "' \n");
		  }
		  if (!StringUtils.isEmpty(regionId)){
			  sb.append(" AND REG.REGION_ID='" + regionId + "' \n");
		  }
		  sb.append("ORDER BY  \n");
		  sb.append(" SOL.FECHA_INGRESO_POA,  \n");
		  sb.append(" SOL.PRESUPUESTO_ESTIMADO  \n");
		  sb.append("OFFSET " + start + "  LIMIT " + limit + "  \n");
          return jdbcTemplate.query(sb.toString(), new SolicitudMapper());
	}
	


	

	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.SolicitudDao#cuenta(java.lang.String, java.lang.String)
	 */
	@Override
	public int cuenta(String subejecutorId, String estado, String provinciaId, String regionId) {
		  StringBuilder sb=new StringBuilder(); 
		  sb.append("SELECT COUNT(*)  \n");
		  sb.append("FROM  \n");
		  sb.append(" SOLICITUDES SOL  \n");
		  sb.append(" INNER JOIN SUBEJECUTORES SUB ON SOL.SUBEJECUTOR_ID=SUB.SUBEJECUTOR_ID  \n");
		  sb.append(" INNER JOIN PERSONAS_JURIDICAS PEJ ON SUB.PERSONA_ID=PEJ.PEJ_ID  \n");
		  sb.append(" INNER JOIN DOMICILIOS DOM ON PEJ.DOMICILIO_ID=DOM.DOMICILIO_ID  \n");
		  sb.append(" INNER JOIN PROVINCIAS PRO ON PRO.PROVINCIA_ID=DOM.PROVINCIA_ID  \n");
		  sb.append(" INNER JOIN REGIONES REG ON REG.REGION_ID=PRO.REGION_ID  \n");
		  sb.append("WHERE 1=1 \n");
		  if (!StringUtils.isEmpty(subejecutorId)){
			  sb.append(" AND SOL.SUBEJECUTOR_ID='" + subejecutorId + "' \n");
		  }
		  if (!StringUtils.isEmpty(estado)){
			  sb.append(" AND SOL.SOLICITUD_ESTADO='" + estado + "' \n");
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
	 * @see ar.org.promeba.dao.SolicitudDao#obtiene(java.lang.String)
	 */
	@Override
	public Solicitud obtiene(String id) {
		List<Solicitud> resultado=jdbcTemplate.query(sqlObtiene.toString(), new Object[] { id }, new SolicitudMapper());
		if (resultado.size()>0){
			return resultado.get(0);
		}else{
			return null;
		}
	}
	

}
