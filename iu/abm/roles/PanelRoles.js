PanelRoles = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelRoles.superclass.constructor.call(this, Ext.apply({
      id: 'panelRoles',
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
    me.panForm=new FormRoles({
      region: 'center'
    });
    me.panBusqueda=new  BusquedaRoles({
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 270,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaRoles({
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Roles',
         id: 'panAbmRoles',
         name: 'panAbmRoles',
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