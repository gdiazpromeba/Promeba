package ar.org.promeba.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ar.org.promeba.beans.MesaGestion;
import ar.org.promeba.dao.MesaGestionDao;

public class MesaGestionDaoImpl implements MesaGestionDao  {
	
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
	
	
	
	public MesaGestionDaoImpl(){
	  //obtiene	
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  MEG.MESA_GESTION_ID, \n");
		sql.append("  MEG.FECHA_ACTA_ACUERDO, \n");
		sql.append("  MEG.FECHA_MESA_GESTION, \n");
		sql.append("  MEG.MESA_GESTION_ESTADO \n");
		sql.append("FROM     \n");
		sql.append("  MESAS_GESTION MEG     \n");
		sql.append("WHERE     \n");
		sql.append(" MEG.MESA_GESTION_ID=?     \n");
		sqlObtiene=new StringBuffer(sql.toString());
		
	  //inserta
	  sqlInsert=new StringBuffer();
	  sqlInsert.append("INSERT INTO MESAS_GESTION ( \n");
	  sqlInsert.append("  MESA_GESTION_ID, \n");
	  sqlInsert.append("  FECHA_ACTA_ACUERDO, \n");
	  sqlInsert.append("  FECHA_MESA_GESTION, \n");
	  sqlInsert.append("  MESA_GESTION_ESTADO \n");
	  sqlInsert.append(") VALUES (?, ?, ?, ?) \n");
	  
	  //modifica
	  sqlActualiza=new StringBuffer();
	  sqlActualiza.append("UPDATE MESAS_GESTION SET  \n");
	  sqlActualiza.append("  FECHA_ACTA_ACUERDO=?, \n");
	  sqlActualiza.append("  FECHA_MESA_GESTION=?, \n");
	  sqlActualiza.append("  MESA_GESTION_ESTADO=? \n");
	  sqlActualiza.append("WHERE \n");
	  sqlActualiza.append("  MESA_GESTION_ID=?  \n");
	  
	  //borra
	  sqlBorra =new StringBuffer();
	  sqlBorra.append("DELETE FROM MESAS_GESTION \n");
	  sqlBorra.append("WHERE  \n");
	  sqlBorra.append(" MESA_GESTION_ID=?  \n");
	  
	  
	}
	


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.MesaGestionDao#borra(java.lang.String)
	 */
	@Override
	public void borra(String id) {
		  jdbcTemplate.update(sqlBorra.toString(), 
				  new Object[] { id});
		}	




	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.MesaGestionDao#inserta(ar.org.promeba.beans.MesaGestion)
	 */
	@Override
	public void inserta(MesaGestion bean) {
	  jdbcTemplate.update(sqlInsert.toString(), 
			  new Object[] { bean.getId(), bean.getFechaActaAcuerdo(), bean.getFechaMesaGestion(), bean.getEstado()});
	}	
	



	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.MesaGestionDao#actualiza(ar.org.promeba.beans.MesaGestion)
	 */
	@Override
	public void actualiza(MesaGestion bean) {
		  jdbcTemplate.update(sqlActualiza.toString(), 
				  new Object[] {bean.getFechaActaAcuerdo(), bean.getFechaMesaGestion(), bean.getEstado(), bean.getId()});
		}	
	
	
	static class MesaGestionMapper implements RowMapper<MesaGestion>{
		  public MesaGestion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			  MesaGestion bean=new MesaGestion();
			  bean.setId(resultSet.getString("MESA_GESTION_ID"));
			  bean.setFechaActaAcuerdo(resultSet.getDate("FECHA_ACTA_ACUERDO"));
			  bean.setFechaMesaGestion(resultSet.getDate("FECHA_MESA_GESTION"));
			  bean.setEstado(resultSet.getString("MESA_GESTION_ESTADO"));
			  return bean;
		  }
	}
	
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.MesaGestionDao#selecciona(int, int, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<MesaGestion> selecciona(int start, int limit, Date fechaActaAcuerdo, Date fechaMesaGestion, String estado){ 
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT     \n");
		sql.append("  MEG.MESA_GESTION_ID, \n");
		sql.append("  MEG.FECHA_ACTA_ACUERDO, \n");
		sql.append("  MEG.FECHA_MESA_GESTION, \n");
		sql.append("  MEG.MESA_GESTION_ESTADO \n");
		sql.append("FROM     \n");
		sql.append("  MESAS_GESTION MEG     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(estado)){
			sql.append("  AND MEG.MESA_GESTION_ESTADO='" + estado + "'     \n");
		}
		if (fechaActaAcuerdo!=null){
			sql.append("  AND MEG.FECHA_ACTA_ACUERDO='" + sdf.format(fechaActaAcuerdo) + "'     \n");
		}
		if (fechaMesaGestion!=null){
			sql.append("  AND MEG.FECHA_MESA_GESTION='" + sdf.format(fechaMesaGestion) + "'     \n");
		}
		sql.append("ORDER BY     \n");
		sql.append(" MEG.FECHA_ACTA_ACUERDO     \n");
		sql.append("OFFSET " + start + " LIMIT " + limit + "  \n");
		return jdbcTemplate.query(sql.toString(), new MesaGestionMapper());
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.MesaGestionDao#cuenta(java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public int cuenta(Date fechaActaAcuerdo, Date fechaMesaGestion, String estado){
		StringBuilder sql= new StringBuilder();
		sql.append("SELECT COUNT(*)    \n");
		sql.append("FROM     \n");
		sql.append("  MESAS_GESTION MEG     \n");
		sql.append("WHERE 1=1     \n");
		if (!StringUtils.isEmpty(estado)){
			sql.append("  AND MEG.MESA_GESTION_ESTADO='" + estado + "'     \n");
		}
		if (fechaActaAcuerdo!=null){
			sql.append("  AND MEG.FECHA_ACTA_ACUERDO='" + sdf.format(fechaActaAcuerdo) + "'     \n");
		}
		if (fechaMesaGestion!=null){
			sql.append("  AND MEG.FECHA_MESA_GESTION='" + sdf.format(fechaMesaGestion) + "'     \n");
		}

		return jdbcTemplate.queryForInt(sql.toString());
	}


	/* (non-Javadoc)
	 * @see ar.org.promeba.dao.MesaGestionDao#obtiene(java.lang.String)
	 */
	@Override
	public MesaGestion obtiene(String id) {
		MesaGestion resultado=(MesaGestion)jdbcTemplate.queryForObject(sqlObtiene.toString(), new Object[] { id }, new MesaGestionMapper());
		return resultado;
	}	
	


	

	

}
