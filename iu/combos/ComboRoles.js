ComboRoles = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboRoles.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Rol',
        width: 180, 
        store: getDsRoles(), 
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150, 
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('comboroles', ComboRoles);