PanelPersonasJuridicas = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelPersonasJuridicas.superclass.constructor.call(this, Ext.apply({
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
    me.panForm=new FormPersonasJuridicas({
      id: 'formPersonasJuridicas',	
      region: 'center'
    });
    me.panBusqueda=new  BusquedaPersonasJuridicas({
      id: 'busquedaPersonasJuridicas', 	
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 330,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaPersonasJuridicas({
       id: 'grillaPersonasJuridicas',	
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Personas Jurídicas',
         id: 'panAbmPersonasJuridicas',
         name: 'panAbmPersonasJuridicas',
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