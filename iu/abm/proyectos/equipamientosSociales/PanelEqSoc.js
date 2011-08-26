PanelEqSoc = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelEqSoc.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormEqSocXProyecto({
             region: 'north',
             height: 100,
             itemId: 'esxpForm',
             id: 'esxpForm'
           }),
           new GrillaEqSocXProyecto({
             region:'center',
             itemId: 'esxpGrid',
             id: 'esxpGrid'
           })
      ],
      listeners: {
        render: function(me) {
          //var cabecera=me.creaTabCabecera(me);
          //var eqSoc=me.creaTabEqSoc(me);
          //me.agrega(cabecera, eqSoc);
        }
      }  
 
    }, config));
  }, //constructor
});