
 function creaPanRoles(){
     var pan=new PanelRoles();
     quitaDePanel();
     muestraEnPanel(pan);
 } 
 
 function creaPanAreasGeograficas(){
     var pan=new PanelAreasGeograficas();
     quitaDePanel();
     muestraEnPanel(pan);
 } 
 
 function creaPanCargos(){
     var pan=new PanelCargos({id: 'panelCargos'});
     quitaDePanel();
     muestraEnPanel(pan);
 } 
 
 
  function creaPanMesasGestion(){
     var pan=new PanelMesasGestion({id: 'panelMesasGestion'});
     quitaDePanel();
     muestraEnPanel(pan);
 } 
 
function creaPanPersonasFisicas(){
     var pan=new PanelPersonasFisicas({id: 'personasFisicas'});
     quitaDePanel();
     muestraEnPanel(pan);
 }
 
 function creaPanAreasFuncionalesXRol(){
     var pan=new PanelAreasFuncionalesXRol({id: 'personasFisicas'});
     quitaDePanel();
     muestraEnPanel(pan);
 } 
 
 function creaPanPersonasJuridicas(){
     var pan=new PanelPersonasJuridicas({id: 'personasJuridicas'});
     quitaDePanel();
     muestraEnPanel(pan);
  }  

  function creaPanAreasFuncionales(){
    var panForm=new FormAreasFuncionales({
      region: 'center'
    });
    var panBusqueda=new  BusquedaAreasFuncionales({
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 270,
        items: [panForm, panBusqueda]
     });
     var panGrilla = new GrillaAreasFuncionales({
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Todos',
         id: 'panAbmAreasFuncionales',
         name: 'panAbmUsers',
         height: 500,
         layout: 'border',
         items: [
           panSuperior,      
           panGrilla
         ]
     });
   
     quitaDePanel();
     muestraEnPanel(panelAbm);
     abmIza(panGrilla, panForm, panBusqueda);
  } 

  function creaPanUsers(){
    var pan=new PanelUsuarios();
    
     quitaDePanel();
     muestraEnPanel(pan);  
  
  }
  
  function creaPanSolicitudes(){
    var pan=new PanelSolicitudes();
    
     quitaDePanel();
     muestraEnPanel(pan);  
  
  }
  
  function creaPanSubejecutores(){
    var pan=new PanelSubejecutores();
    
     quitaDePanel();
     muestraEnPanel(pan);  
  
  }  

function creaPanAbmUsuarios() {
	var panForm = getPanFormAbmUsuarios();
	var panBusqueda = getPanBusquedaAbmUsuarios();
	var panSuperior = new Ext.Container({
				layout : 'border',
				region : 'north',
				height : 200,
				items : [panForm, panBusqueda]
			});
	var panGrilla = generaGrillaAbmUsuarios();
	var panelAbm = new Ext.Panel({
				id : 'panAbmUsuarios',
				name : 'panAbmUsuarios',
				height : 500,
				layout : 'border',
				items : [panSuperior, panGrilla]
			});
	quitaDePanel();
	muestraEnPanel(panelAbm);
	abmIza(panGrilla, panForm, panBusqueda);
}


function creaCambioDeClave() {
  var cambioDeClave = new CambioClave();
  quitaDePanel();
  muestraEnPanel(cambioDeClave);
}




