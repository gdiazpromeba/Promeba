PanelEqSoc = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelEqSoc.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormEqSocXSolicitud({
             region: 'north',
             height: 100,
             itemId: 'esxsForm',
             id: 'esxsForm'
           }),
           new GrillaEqSocXSolicitud({
             region:'center',
             itemId: 'esxsGrid',
             id: 'esxsGrid'
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