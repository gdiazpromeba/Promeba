PanelEsm = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelEsm.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormEsmXProyecto({
             region: 'north',
             height: 100,
             itemId: 'esmxpForm',
             id: 'esmxpForm'
           }),
           new GrillaEsmXProyecto({
             region:'center',
             itemId: 'esmxpGrid',
             id: 'esmxpGrid'
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