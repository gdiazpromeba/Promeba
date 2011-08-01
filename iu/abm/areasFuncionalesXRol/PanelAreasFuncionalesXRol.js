PanelAreasFuncionalesXRol = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelAreasFuncionalesXRol.superclass.constructor.call(this, Ext.apply({
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
    me.panForm=new FormAreasFuncionalesXRol({
      id: 'formAreasFuncionalesXRol',	
      region: 'center'
    });
    me.panBusqueda=new  BusquedaAreasFuncionalesXRol({
      id: 'busquedaAreasFuncionalesXRol', 	
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 230,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaAreasFuncionalesXRol({
       id: 'grillaAreasFuncionalesXRol',	
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Áreas Funcionales x Rol',
         id: 'panAreasFuncionalesXRol',
         name: 'panAreasFuncionalesXRol',
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