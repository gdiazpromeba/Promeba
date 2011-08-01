ComboDepartamentos = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboDepartamentos.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Departamento',
        width: 180, 
        store: dsDepartamentos, 
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150, 
        mode: 'local', //esto evita que se vuelva a cargar al apretar el trigger, sólo externamente
        emptyText: '-- Departamentos --',
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('combodepartamentos', ComboDepartamentos);