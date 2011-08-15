ComboTiposRiesgo = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboTiposRiesgo.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Est.Mensura',
        width: 280, 
        store: new Ext.data.JsonStore({
          url: '/prototipo/svc/conector/auxSolicitudes/seleccionaTiposRiesgo',
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

Ext.reg('combotiposriesgo', ComboTiposRiesgo);