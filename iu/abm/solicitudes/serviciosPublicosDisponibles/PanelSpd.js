PanelSpd = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelSpd.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormSpdXSolicitud({
             region: 'north',
             height: 100,
             itemId: 'spdxsForm',
             id: 'spdxsForm'
           }),
           new GrillaSpdXSolicitud({
             region:'center',
             itemId: 'spdxsGrid',
             id: 'spdxsGrid'
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