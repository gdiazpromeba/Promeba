ComboTiposDocumento = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboTiposDocumento.superclass.constructor.call(this, Ext.apply({
        fieldLabel: 'Tipo Documento',
        width: 80, 
        store: dsTiposDocumento, 
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150, 
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('combotiposdocumento', ComboTiposDocumento);