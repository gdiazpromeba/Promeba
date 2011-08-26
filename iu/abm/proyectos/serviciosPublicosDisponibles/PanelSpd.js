PanelSpd = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    PanelSpd.superclass.constructor.call(this, Ext.apply({
      layout: 'border', 
      deferredRender : false,
      items: [
           new FormSpdXProyecto({
             region: 'north',
             height: 100,
             itemId: 'spdxpForm',
             id: 'spdxpForm'
           }),
           new GrillaSpdXProyecto({
             region:'center',
             itemId: 'spdxpGrid',
             id: 'spdxpGrid'
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