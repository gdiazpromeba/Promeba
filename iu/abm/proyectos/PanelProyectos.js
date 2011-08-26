PanelProyectos = Ext.extend(Ext.TabPanel, {
  constructor : function(config) {
    PanelProyectos.superclass.constructor.call(this, Ext.apply({
      id: 'panelproyectos',
      region: 'center',
      activeItem: 0, 
      deferredRender : false,
      listeners: {
        render: function(me) {
          var cabecera=me.creaTabCabecera(me);
          var eqSoc=me.creaTabEqSoc(me);
          var esm=me.creaTabEsm(me);
          var spd=me.creaTabSpd(me);
          var tir=me.creaTabTir(me);
          me.agrega(cabecera, eqSoc, esm, spd, tir, me);
          
        }
      }  
 
    }, config));
    
    var panForm;
    var panBusqueda;
    var panGrilla;
    //equipamientos sociales
    var formEqSoc;
    var grillaEqSoc;
    //estados mensura
    var formEsm;
    var grillaEsm;
    //servicios públicos disponibles
    var formSpd;
    var grillaSpd;
    //tipos Riesgo
    var formTir;
    var grillaTir;
    
    
    
    
  }, //constructor
  
  creaTabCabecera :  function(me){
    me.panForm=new FormProyectos({
      region: 'north',
      height: 300
    });
    me.panGrilla = new GrillaProyectos({
       region: 'center'
    });
    var panCentral=new Ext.Container({
        layout: 'border',
        region: 'center',
        //height: 430,
        items: [me.panForm, me.panGrilla]
     });
    me.panBusqueda=new  BusquedaProyectos({
      region: 'west',
      width: 400,
    });
     var panelAbm=new Ext.Panel({
         title: 'Proyectos',
         id: 'panAbmProyectos',
         name: 'panAbmProyectos',
         //height: 500,
         layout: 'border',
         items: [
           panCentral,      
           me.panBusqueda
         ]
     });
    return panelAbm;
  },
  
  creaTabEqSoc : function(me){
     me.formEqSoc=new FormEqSocXProyecto({
       region: 'north',
       height: 170
     });
     me.grillaEqSoc = new GrillaEqSocXProyecto({
       region:'center'
     });
     var panelDet=new Ext.Panel({
       title: '[Equipamientos Sociales]',
       id: 'eqSocXProyecto',
       name: 'eqSocXProyecto',
       height: 500,
       layout: 'border',
       items: [
         me.formEqSoc,      
	     me.grillaEqSoc
       ]
     });
     return panelDet;      
  }, 
  
  creaTabEsm : function(me){
     me.formEsm=new FormEsmXProyecto({
       region: 'north',
       height: 170
     });
     me.grillaEsm = new GrillaEsmXProyecto({
       region:'center'
     });
     var panelDet=new Ext.Panel({
       title: '[Estados de mensura]',
       id: 'esmXProyecto',
       name: 'esmXProyecto',
       height: 500,
       layout: 'border',
       items: [
         me.formEsm,      
	     me.grillaEsm
       ]
     });
     return panelDet;      
  }, 
  
  creaTabSpd : function(me){
     me.formSpd=new FormSpdXProyecto({
       region: 'north',
       height: 170
     });
     me.grillaSpd = new GrillaSpdXProyecto({
       region:'center'
     });
     var panelDet=new Ext.Panel({
       title: '[Servicios Públicos Disponibles]',
       id: 'spdXProyecto',
       name: 'spdXProyecto',
       height: 500,
       layout: 'border',
       items: [
         me.formSpd,      
	     me.grillaSpd
       ]
     });
     return panelDet;      
  }, 
  
  creaTabTir : function(me){
     me.formTir=new FormTirXProyecto({
       region: 'north',
       height: 170
     });
     me.grillaTir = new GrillaTirXProyecto({
       region:'center'
     });
     var panelDet=new Ext.Panel({
       title: '[Tipos de riesgo]',
       id: 'tirXProyecto',
       name: 'tirXProyecto',
       height: 500,
       layout: 'border',
       items: [
         me.formTir,      
	     me.grillaTir
       ]
     });
     return panelDet;      
  },   
      
    
  
  
  agrega : function(cabecera, eqSoc, esm, spd, tir, me){
    me.add(cabecera);
    me.add(eqSoc);
    me.add(esm);
    me.add(spd);
    me.add(tir);
    
    abmIza(me.panGrilla, me.panForm, me.panBusqueda);
    cabeceraDetalle(me.grillaEqSoc, me.formEqSoc, me.panGrilla);
    cabeceraDetalle(me.grillaEsm, me.formEsm, me.panGrilla);
    cabeceraDetalle(me.grillaSpd, me.formSpd, me.panGrilla);
    cabeceraDetalle(me.grillaTir, me.formTir, me.panGrilla);
  },
  

 
  
  
     
  
});