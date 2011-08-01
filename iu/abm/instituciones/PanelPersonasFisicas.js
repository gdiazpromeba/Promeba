PanelPersonasFisicas = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelPersonasFisicas.superclass.constructor.call(this, Ext.apply({
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
    me.panForm=new FormPersonasFisicas({
      id: 'formPersonasFisicas',	
      region: 'center'
    });
    me.panBusqueda=new  BusquedaPersonasFisicas({
      id: 'busquedaPersonasFisicas', 	
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 330,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaPersonasFisicas({
       id: 'grillaPersonasFisicas',	
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Personas Físicas',
         id: 'panAbmPersonasFisicas',
         name: 'panAbmPersonasFisicas',
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