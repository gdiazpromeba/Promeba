package ar.org.promeba.util.init;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ar.org.promeba.beans.Domicilio;
import ar.org.promeba.beans.Persona;
import ar.org.promeba.beans.PersonaJuridica;
import ar.org.promeba.beans.Provincia;
import ar.org.promeba.beans.Solicitud;
import ar.org.promeba.beans.Subejecutor;
import ar.org.promeba.svc.PersonaJuridicaSvcImpl;
import ar.org.promeba.svc.ProvinciaSvcImpl;
import ar.org.promeba.svc.SolicitudSvcImpl;
import ar.org.promeba.svc.SubejecutorSvcImpl;

public class ActualizacionDirecta {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws Exception{
		fechaSolicitudes();
	    //insertaPoligonoRegion();
		//insertaPoligono();
		//quiebraArchivo();
		//ueps();
		//solicitudes();
	}
	
	public static void leeArchivo() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WEB-INF/datos.xml");
		DataSource ds=(DataSource)ctx.getBean("basicDataSource");
		Connection con=ds.getConnection();
		Statement stm=con.createStatement();
		PreparedStatement prs=con.prepareStatement("INSERT INTO LOCALIDADES (LOCALIDAD_ID, LOCALIDAD_NOMBRE, DEPARTAMENTO_ID) VALUES (?, ?, ?) ");
		BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream("Localidad.txt")));
		String linea=bur.readLine();
		while (linea!=null){
			String[] campos=linea.split(",");
			String id=campos[0]; //id=id.substring(1, id.length()-1);
			prs.setString(1, id);
			String nombre=campos[1]; nombre=nombre.substring(1, nombre.length()-1);
			nombre=nombre.trim();
			prs.setString(2, nombre);
			String departamentoId=campos[2]; //departamentoId=departamentoId.substring(1, departamentoId.length()-1);
			prs.setString(3, departamentoId);
			prs.executeUpdate();
			linea=bur.readLine();
		}
		con.close();
	}
	
	public static void quiebraArchivo() throws Exception{
		BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Gonzalo\\Downloads\\patagonia.kml")));
		String linea=bur.readLine();
		String[] lineas=linea.split(" ");
		BufferedWriter buw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Gonzalo\\Downloads\\patagonia.txt")));
		for (String string : lineas) {
			buw.write(string+"\n");
		}
		buw.close();
	}
	
	public static void insertaPoligonoProvincia() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WEB-INF/datos.xml");
		DataSource ds=(DataSource)ctx.getBean("basicDataSource");
		Connection con=ds.getConnection();
		Statement stm=con.createStatement();
		PreparedStatement prs=con.prepareStatement("INSERT INTO POLIGONOS_PROVINCIAS (POLIGONO_PROVINCIA_ID, PROVINCIA_ID, LATITUD, LONGITUD, ORDEN) VALUES (?, ?, ?, ?, ?) ");
		BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Gonzalo\\Downloads\\corrientes.txt")));
		String linea=bur.readLine();
		int i=0;
		while (linea!=null){
			String[] campos=linea.split(",");
			BigDecimal latitud=new BigDecimal(campos[1]);
			BigDecimal longitud=new BigDecimal(campos[0]);
			String pk=UUID.randomUUID().toString().substring(0, 32);
			String provinciaId="bb760b143f18d39a999c571445ec4a4e";
			prs.setString(1, pk);
			prs.setString(2, provinciaId);
			prs.setBigDecimal(3, latitud);
			prs.setBigDecimal(4, longitud);
			prs.setInt(5, i++);
			prs.executeUpdate();
			linea=bur.readLine();
		}
		con.close();
	}
	
	public static void insertaPoligonoRegion() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WEB-INF/datos.xml");
		DataSource ds=(DataSource)ctx.getBean("basicDataSource");
		Connection con=ds.getConnection();
		Statement stm=con.createStatement();
		PreparedStatement prs=con.prepareStatement("INSERT INTO POLIGONOS_REGIONES (POLIGONO_REGION_ID, REGION_ID, LATITUD, LONGITUD, ORDEN) VALUES (?, ?, ?, ?, ?) ");
		BufferedReader bur=new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Gonzalo\\Downloads\\patagonia.txt")));
		String linea=bur.readLine();
		int i=0;
		while (linea!=null){
			String[] campos=linea.split(",");
			BigDecimal latitud=new BigDecimal(campos[1]);
			BigDecimal longitud=new BigDecimal(campos[0]);
			String pk=UUID.randomUUID().toString().substring(0, 32);
			String regionId="0f472fecfd1c43cf1b9224de48f75fde";
			prs.setString(1, pk);
			prs.setString(2, regionId);
			prs.setBigDecimal(3, latitud);
			prs.setBigDecimal(4, longitud);
			prs.setInt(5, i++);
			prs.executeUpdate();
			linea=bur.readLine();
		}
		con.close();
	}	
	
	public static void minusculas() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WEB-INF/datos.xml");
		DataSource ds=(DataSource)ctx.getBean("basicDataSource");
		Connection con=ds.getConnection();
		Statement stm=con.createStatement();
		PreparedStatement prs=con.prepareStatement("UPDATE DEPARTAMENTOS SET DEPARTAMENTO_NOMBRE=? WHERE DEPARTAMENTO_ID=?");
		ResultSet rs=stm.executeQuery("SELECT * FROM DEPARTAMENTOS");
		while(rs.next()){
			String depNombre=rs.getString("DEPARTAMENTO_NOMBRE");
			String depId=rs.getString("DEPARTAMENTO_ID");
			if (depNombre.equals(depNombre.toUpperCase())){
				depNombre=depNombre.toLowerCase();
				depNombre=StringUtils.capitaliseAllWords(depNombre);
				System.out.println(depNombre);
				prs.setString(1, depNombre);
				prs.setString(2, depId);
				prs.executeUpdate();
			}
		}
		con.close();
	}	
	
	
	public static void solicitudes() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[]{"WEB-INF/datos.xml", "WEB-INF/servicios.xml"});
		SubejecutorSvcImpl subejecutorSvc=(SubejecutorSvcImpl)ctx.getBean("subejecutorSvc");
		SolicitudSvcImpl solSvc=(SolicitudSvcImpl)ctx.getBean("solicitudSvc");

		//personaJuridicaSvc.setDatasource(ds);
		
		List<Subejecutor> subejs=subejecutorSvc.selecciona(0, 1000, null, null);
		for (int i=0; i<1000; i++){
			Solicitud sol=new Solicitud();
			sol.setCantidadLotes(2+ enteroAleatorio(120));
			sol.setDescripcion("Solicitud " + i);
			sol.setEstado("Ingresada");
			sol.setFechaDesde(new Date());
			sol.setFechaHasta(null);
			sol.setFechaIngresoPA(new Date());
			sol.setFechaIngresoPGEP(new Date());
			sol.setFechaIngresoPGEP(new Date());
			sol.setPresupuestoEstimado(new BigDecimal(5000 + enteroAleatorio(100000)));
			sol.setSubejecutorId(subejs.get(enteroAleatorio(subejs.size())).getId());
			solSvc.inserta(sol);
			//sol.setCantidadLotes(Math.random() cantidadLotes)
		}
		

	}
	
	/**
	 * actualiza la "fecha de ingreso al POA" de todas las solicitudes
	 * @throws Exception
	 */
	public static void fechaSolicitudes() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[]{"WEB-INF/datos.xml", "WEB-INF/servicios.xml"});
		SolicitudSvcImpl solSvc=(SolicitudSvcImpl)ctx.getBean("solicitudSvc");
		List<Solicitud> solicitudes=solSvc.selecciona(0, 30000000, null, null, null, null);
		for (Solicitud solicitud : solicitudes) {
		  int año=2008 +  enteroAleatorio(4);
		  int mes=enteroAleatorio(11);
		  int dia = 1+ enteroAleatorio(28); 
		  GregorianCalendar cal=new GregorianCalendar(año, mes, dia);
		  solicitud.setFechaIngresoPOA(cal.getTime());
		  solSvc.actualiza(solicitud);
		}
	}	
	
	
	
	public static void ueps() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[]{"WEB-INF/datos.xml", "WEB-INF/servicios.xml"});
		DataSource ds=(DataSource)ctx.getBean("basicDataSource");
		ProvinciaSvcImpl provinciaSvc=(ProvinciaSvcImpl)ctx.getBean("provinciaSvc");
		SubejecutorSvcImpl subejecutorSvc=(SubejecutorSvcImpl)ctx.getBean("subejecutorSvc");
		PersonaJuridicaSvcImpl personaJuridicaSvc=(PersonaJuridicaSvcImpl)ctx.getBean("personaJuridicaSvc");
		//personaJuridicaSvc.setDatasource(ds);
		
		List<Provincia> provs=provinciaSvc.selecciona(null);
		//List<PersonaJuridica> perj=personaJuridicaDao.selecciona(0, 1000, "");
		for (Provincia provincia : provs) {
			PersonaJuridica perj=new PersonaJuridica();
			perj.setCuit("30-" + cadenaAleatoriaNumerica(8)  + "-" + cadenaAleatoriaNumerica(1) );
			Domicilio dom=new Domicilio();
			dom.setId("0906ecf0-bd5a-409a-91db-77e42e65");
			perj.setDomicilio(dom);
			perj.setFechaInscripcion(new Date());
			perj.setNombre("UEP " + provincia.getNombre());
			perj.setPersoneria( new BigInteger(130, new SecureRandom()).toString(7));
			personaJuridicaSvc.inserta(perj);
			
			Subejecutor sub=new Subejecutor();
			sub.setId(UUID.randomUUID().toString().substring(0, 32));
			sub.setNombre("UEP " + provincia.getNombre());
			sub.setCaracter("Provincial");
			sub.setContacto("un contacto ");
			sub.setFechaLegitimacion(new Date());
			Persona per=new Persona();
			per.setId(perj.getId());
			sub.setPersona(per);
			sub.setDomicilio(dom);
			subejecutorSvc.inserta(sub);
		}
	}
	
	
	private static String cadenaAleatoriaAlfanumerica(int medida){
		  SecureRandom random = new SecureRandom();
		  String res=new BigInteger(130, random).toString(32);
		  return res.substring(0, medida);
	}
	
	private static String cadenaAleatoriaNumerica(int medida){
		  SecureRandom random = new SecureRandom();
		  String res=new BigInteger(130, random).toString(10);
		  return res.substring(0, medida);
	}
	
	private static int enteroAleatorio(int maximo){
		  SecureRandom random = new SecureRandom();
		  int res=random.nextInt(maximo);
		  return res;
	}

	
	
	public static void reemplazaPalabra() throws Exception{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WEB-INF/datos.xml");
		DataSource ds=(DataSource)ctx.getBean("basicDataSource");
		Connection con=ds.getConnection();
		Statement stm=con.createStatement();
		PreparedStatement prs=con.prepareStatement("UPDATE DEPARTAMENTOS SET DEPARTAMENTO_NOMBRE=? WHERE DEPARTAMENTO_ID=?");
		ResultSet rs=stm.executeQuery("SELECT * FROM DEPARTAMENTOS");
		while(rs.next()){
			String depNombre=rs.getString("DEPARTAMENTO_NOMBRE");
			String depId=rs.getString("DEPARTAMENTO_ID");
			depNombre=depNombre.replace("Capitan", "Capitán");
			depNombre=depNombre.replace("Rio", "Río");
			depNombre=depNombre.replace("Martin", "Martín");
			depNombre=depNombre.replace("Fernandez", "Fernández");
			depNombre=depNombre.replace("German", "Germán");
			depNombre=depNombre.replace("Estacion", "Estación");
			depNombre=depNombre.replace("Perez", "Pérez");
			depNombre=depNombre.replace("America", "América");
			depNombre=depNombre.replace("Fortin", "Fortín");
			depNombre=depNombre.replace("Valentin", "Valentín");
			depNombre=depNombre.replace("Alvarez", "Álvarez");
			depNombre=depNombre.replace("Nicolas", "Nicolás");
			depNombre=depNombre.replace("Lopez", "López");
			depNombre=depNombre.replace("Peron", "Perón");
			depNombre=depNombre.replace("Ramon", "Ramón");
			depNombre=depNombre.replace("Ramóna", "Ramona");
			depNombre=depNombre.replace("Maria ", "María ");
			depNombre=depNombre.replace("Garcia", "García");
			depNombre=depNombre.replace("Belen", "Belén");
			depNombre=depNombre.replace("Concepcion", "Concepción");
			depNombre=depNombre.replace("Medano", "Médano");
			depNombre=depNombre.replace("Geronimo", "Gerónimo");
			depNombre=depNombre.replace("Jeronimo", "Jerónimo");
			depNombre=depNombre.replace("Rincon", "Rincón");
			depNombre=depNombre.replace("Mejia", "Mejía");
			depNombre=depNombre.replace("Sarandi", "Sarandí");
			depNombre=depNombre.replace("Monica", "Mónica");
			depNombre=depNombre.replace("Rincon", "Rincón");
			depNombre=depNombre.replace("Agustin", "Agustín");
			depNombre=depNombre.replace("Rodriguez", "Rodríguez");
			depNombre=depNombre.replace("Gutierrez", "Gutiérrez");
			depNombre=depNombre.replace("Jesus", "Jesús");
			depNombre=depNombre.replace("Gomez", "Gómez");
			depNombre=depNombre.replace("Cristobal", "Cristóbal");
			depNombre=depNombre.replace("Corazon", "Corazón");
			depNombre=depNombre.replace("Hipolito", "Hipólito");
			depNombre=depNombre.replace("Arbol", "Árbol");
			depNombre=depNombre.replace("Dean", "Deán");
			depNombre=depNombre.replace("Juarez", "Juárez");
			depNombre=depNombre.replace("Ejercito", "Ejército");
			depNombre=depNombre.replace("Diaz", "Díaz");
			depNombre=depNombre.replace("Joaquin", "Joaquín");
			depNombre=depNombre.replace("Dario", "Darío");
			depNombre=depNombre.replace("Aguila", "Águila");
			depNombre=depNombre.replace("Veronica", "Verónica");
			depNombre=depNombre.replace("Julian", "Julián");
			depNombre=depNombre.replace("Maximo", "Máximo");
			depNombre=depNombre.replace("Constitucion", "Constitución");
			depNombre=depNombre.replace("Ituzaingo", "Ituzaingó");
			depNombre=depNombre.replace("Ombu", "Ombú");
			depNombre=depNombre.replace("Antartida", "Antártida");
			depNombre=depNombre.replace("Moises", "Moisés");
			depNombre=depNombre.replace("Duran", "Durán");
			depNombre=depNombre.replace("Bartolome", "Bartolomé");
			depNombre=depNombre.replace("Lujan", "Luján");
			depNombre=depNombre.replace("Geronimo", "Gerónimo");
			depNombre=depNombre.replace("Aristobulo", "Aristóbulo");
			depNombre=depNombre.replace("Cetrangolo", "Cetrángolo");
			depNombre=depNombre.replace("Velez", "Vélez");
			depNombre=depNombre.replace("Lujan", "Luján");
			depNombre=depNombre.replace("Cordoba", "Córdoba");
			depNombre=depNombre.replace("Saenz", "Sáenz");
			depNombre=depNombre.replace("Benitez", "Benítez");
			depNombre=depNombre.replace("Águilar", "Aguilar");
			depNombre=depNombre.replace("Union", "Unión");
			depNombre=depNombre.replace("Vazquez", "Vázquez");
			depNombre=depNombre.replace("Leguizamon", "Leguizamón");
			depNombre=depNombre.replace("Federacion", "Federación");
			depNombre=depNombre.replace("Asuncion", "Asunción");
			depNombre=depNombre.replace("Avila", "Ávila");
			depNombre=depNombre.replace("Alferez", "Alférez");
			depNombre=depNombre.replace("Hernandez", "Hernández");
			depNombre=depNombre.replace("Guemes", "Güemes");
			depNombre=depNombre.replace("Parana", "Paraná");
			depNombre=depNombre.replace("Dominguez", "Domínguez");
			depNombre=depNombre.replace("Esquiu", "Esquiú");
			depNombre=depNombre.replace("Porteno", "Porteño");
			depNombre=depNombre.replace("Leon", "León");
			depNombre=depNombre.replace("Leónardo", "Leonardo");
			depNombre=depNombre.replace("Maria", "María");
			depNombre=depNombre.replace("Marían", "Marian");
			depNombre=depNombre.replace("Cienaga", "Ciénaga");
			depNombre=depNombre.replace("Arguello", "Argüello");
			depNombre=depNombre.replace("Azucar", "Azúcar");
			depNombre=depNombre.replace("Tio ", "Tío ");
			depNombre=depNombre.replace("Condor", "Cóndor");
			depNombre=depNombre.replace("Tomas ", "Tomás ");
			depNombre=depNombre.replace("Unzue", "Unzué");
			depNombre=depNombre.replace("Vertiz", "Vértiz");
			depNombre=depNombre.replace("Joséf", "Josef");
			depNombre=depNombre.replace("Cienaga", "Ciénaga");
			depNombre=depNombre.replace(" Del ", " del ");
			depNombre=depNombre.replace(" De ", " de ");
			depNombre=depNombre.replace(" De La ", " de la ");
			depNombre=depNombre.replace(" De Las ", " de las ");
			depNombre=depNombre.replace("Romulo", "Rómulo");
			depNombre=depNombre.replace("Junin", "Junín");
			depNombre=depNombre.replace("Jaguel", "Jagüel");
			depNombre=depNombre.replace("Reduccion", "Reducción");
			depNombre=depNombre.replace("Ovejeria", "Ovejería");
			depNombre=depNombre.replace("Sebastian", "Sebastián");
			depNombre=depNombre.replace("Tropezon", "Tropezón");
			depNombre=depNombre.replace("Nestor", "Néstor");
			depNombre=depNombre.replace("Apostol", "Apóstol");
			depNombre=depNombre.replace("Martir", "Mártir");
			depNombre=depNombre.replace("Husar", "Húsar");
			depNombre=depNombre.replace("Iguazu", "Iguazú");
			depNombre=depNombre.replace("Ines", "Inés");
			depNombre=depNombre.replace("Guarani", "Guaraní");
			depNombre=depNombre.replace("Echeverria", "Echeverría");
			depNombre=depNombre.replace("Jardin", "Jardín");
			depNombre=depNombre.replace("Cajon", "Cajón");
			depNombre=depNombre.replace("Alarcon", "Alarcón");
			depNombre=depNombre.replace("Benjamin", "Benjamín");
			depNombre=depNombre.replace("Beltran", "Beltrán");
			depNombre=depNombre.replace("Guzman", "Guzmán");
			depNombre=depNombre.replace("Falcon", "Falcón");
			depNombre=depNombre.replace("Matias", "Matías");
			depNombre=depNombre.replace("Sanchez", "Sánchez");
			depNombre=depNombre.replace("Roldan", "Roldán");
			depNombre=depNombre.replace("Jimenez", "Jiménez");
			depNombre=depNombre.replace("Araoz", "Aráoz");
			depNombre=depNombre.replace("Tucuman", "Tucumán");
			depNombre=depNombre.replace("Rincóna", "Rincona");
			
			
			prs.setString(1, depNombre);
			prs.setString(2, depId);
			prs.executeUpdate();
		}
		con.close();
	}	

}
