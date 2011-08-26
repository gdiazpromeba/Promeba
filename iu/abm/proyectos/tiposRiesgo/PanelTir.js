PanelTir = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelTir.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormTirXProyecto({
             region: 'north',
             height: 100,
             itemId: 'tirxpForm',
             id: 'tirxpForm'
           }),
           new GrillaTirXProyecto({
             region:'center',
             itemId: 'tirxpGrid',
             id: 'tirxpGrid'
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