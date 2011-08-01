PanelSubejecutores = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelSubejecutores.superclass.constructor.call(this, Ext.apply({
      id: 'panelSubejecutores',
      region: 'center',
      layout: 'fit',
      frame: false,
      border: 'none',
      listeners: {
        render: function(me) {
          var cabecera=me.creaTabCabecera(me);
          me.agrega(cabecera);
        }
      }  
 
    }, config));
    
    var panForm;
    var panBusqueda;
    var panGrilla;
  }, //constructor
  
  creaTabCabecera :  function(me){
    me.panForm=new FormSubejecutores({
      region: 'center'
    });
    me.panBusqueda=new  BusquedaSubejecutores({
      region: 'west',
      width: 400,
    });
    var panSuperior=new Ext.Container({
        layout: 'border',
        region: 'north',
        height: 330,
        items: [me.panForm, me.panBusqueda]
     });
     me.panGrilla = new GrillaSubejecutores({
       region: 'center'
     });
     var panelAbm=new Ext.Panel({
         title: 'Subejecutores',
         id: 'panAbmSubejecutores',
         name: 'panAbmSubejecutores',
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