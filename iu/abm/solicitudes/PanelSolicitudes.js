PanelSolicitudes = Ext.extend(Ext.TabPanel, {
  constructor : function(config) {
    PanelSolicitudes.superclass.constructor.call(this, Ext.apply({
      id: 'panelSolicitudes',
      region: 'center',
      activeItem: 0, 
      deferredRender : false,
      listeners: {
        render: function(me) {
          var cabecera=me.creaTabCabecera(me);
          var eqSoc=me.creaTabEqSoc(me);
          me.agrega(cabecera, eqSoc);
        }
      }  
 
    }, config));
    
    var panForm;
    var panBusqueda;
    var panGrilla;
    var formEqSoc;
    var grillaEqSoc;
  }, //constructor
  
  creaTabCabecera :  function(me){
    me.panForm=new FormSolicitudes({
      region: 'center'
    });
    me.panBusqueda=new  BusquedaSolicitudes({
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 330,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaSolicitudes({
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Solicitudes',
         id: 'panAbmSolicitudes',
         name: 'panAbmSolicitudes',
         height: 500,
         layout: 'border',
         items: [
           panSuperior,      
           me.panGrilla
         ]
     });
    return panelAbm;
  },
  
  creaTabEqSoc : function(me){
     me.formEqSoc=new FormEqSocXSolicitud({
       region: 'north',
       height: 170
     });
     me.grillaEqSoc = new GrillaEqSocXSolicitud({
       region:'center'
     });
     var panelDet=new Ext.Panel({
       title: '[Equipamientos Sociales]',
       id: 'eqSocXSolicitudId',
       name: 'eqSocXSolicitud',
       height: 500,
       layout: 'border',
       items: [
         me.formEqSoc,      
	     me.grillaEqSoc
       ]
     });
     return panelDet;      
  }, 
  
  agrega : function(cabecera, eqSoc){
    this.add(cabecera);
    this.add(eqSoc);
    
     abmIza(this.panGrilla, this.panForm, this.panBusqueda);
     cabeceraDetalle(this.grillaEqSoc, this.formEqSoc, this.panGrilla);
  }
  
     
  
});