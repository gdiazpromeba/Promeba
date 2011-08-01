ComboRegiones = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboRegiones.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Región',
        emptyText: '-- Regiones --',
        width: 180, 
        store: new Ext.data.JsonStore({
          url: '/prototipo/svc/conector/geografia/seleccionaRegiones',
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

Ext.reg('comboregiones', ComboRegiones);