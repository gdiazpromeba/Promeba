PanelMesasGestion = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelMesasGestion.superclass.constructor.call(this, Ext.apply({
      region: 'center',
      layout: 'fit',
      listeners: {
        render: function(me) {
          var cabecera=me.creaCabecera(me);
          me.agrega(cabecera);
        }
      }  
 
    }, config));
    
    var panForm;
    var panBusqueda;
    var panGrilla;
  }, //constructor
  
  creaCabecera :  function(me){
    me.panForm=new FormMesasGestion({
      id: 'formMesasGestion',	
      region: 'center'
    });
    me.panBusqueda=new  BusquedaMesasGestion({
      id: 'busquedaMesasGestion', 	
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 230,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaMesasGestion({
       id: 'grillaMesasGestion',	
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Mesas de Gestión',
         id: 'panAbmMesasGestion',
         name: 'panAbmMesasGestion',
         height: 500,
         layout: 'border',
         items: [
           panSuperior,      
           me.panGrilla
         ]
     });
     return panelAbm;
  },
  
  agrega : function(cabecera){
    this.add(cabecera);
    abmIza(this.panGrilla, this.panForm, this.panBusqueda);
  }
  
     
  
});