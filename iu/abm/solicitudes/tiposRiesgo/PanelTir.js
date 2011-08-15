PanelTir = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelTir.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormTirXSolicitud({
             region: 'north',
             height: 100,
             itemId: 'tirxsForm',
             id: 'tirxsForm'
           }),
           new GrillaTirXSolicitud({
             region:'center',
             itemId: 'tirxsGrid',
             id: 'tirxsGrid'
           })
      ],
      listeners: {
        render: function(me) {
          //var cabecera=me.creaTabCabecera(me);
          //var eqSoc=me.creaTabTir(me);
          //me.agrega(cabecera, eqSoc);
        }
      }  
 
    }, config));
  }, //constructor
});