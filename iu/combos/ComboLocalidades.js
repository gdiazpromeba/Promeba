ComboLocalidades = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboLocalidades.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Municipio',
        width: 180, 
        store: dsLocalidades, 
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150, 
        mode: 'local', //esto evita que se vuelva a cargar al apretar el trigger, sólo externamente
        emptyText: '-- Municipios --',
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('combolocalidades', ComboLocalidades);