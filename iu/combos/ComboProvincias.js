ComboProvincias = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboProvincias.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Provincia',
        width: 180, 
        store: new Ext.data.JsonStore({
          url: '/prototipo/svc/conector/geografia/seleccionaProvincias',
          root: 'data',
          fields: ['id', 'nombre']
        }),
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150,
        mode: 'local', //esto evita que se vuelva a cargar al apretar el trigger, sólo externamente 
        emptyText: '-- Provincias --',
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('comboprovincias', ComboProvincias);