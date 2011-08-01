ComboAreasFuncionales = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboAreasFuncionales.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Área Funcional',
        width: 180, 
        store: new Ext.data.JsonStore({
          url: '/prototipo/svc/conector/areasFuncionales/selecciona',
          root: 'data',
          fields: ['id', 'nombre']
        }),
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150,
        mode: 'local', //esto evita que se vuelva a cargar al apretar el trigger, sólo externamente 
        emptyText: '-- Áreas Funcionales --',
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('comboareasfuncionales', ComboAreasFuncionales);