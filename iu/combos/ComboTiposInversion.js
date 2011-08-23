ComboTiposInversion = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboTiposInversion.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Tipo de Inversión',
        width: 280, 
        store: new Ext.data.JsonStore({
          url: '/prototipo/svc/conector/auxSolicitudes/seleccionaTiposInversion',
          root: 'data',
          fields: ['id', 'nombre']
        }), 
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150, 
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('combotiposinversion', ComboTiposInversion);