PanelEsm = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelEsm.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormEsmXSolicitud({
             region: 'north',
             height: 100,
             itemId: 'esmxsForm',
             id: 'esmxsForm'
           }),
           new GrillaEsmXSolicitud({
             region:'center',
             itemId: 'esmxsGrid',
             id: 'esmxsGrid'
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