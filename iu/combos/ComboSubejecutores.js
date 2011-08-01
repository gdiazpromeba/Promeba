ComboSubejecutores = Ext.extend(Ext.form.ComboBox, {
  
    constructor : function(config) {
      ComboSubejecutores.superclass.constructor.call(this, Ext.apply({
        fieldLabel: 'Subejecutor',
        allowBlank: true, 
        width: 500, 
        xtype: 'combo',
        loadingText: 'Cargando ...',
        typeAhead: false, 
        forceSelecion: true, 
        store: dsSubejecutores, 
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150, 
        pageSize:15, 
        hideTrigger: true, 
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>'),
        minChars: 1, 
        itemSelector: 'div.search-item'
    }, config));
  } //constructor
});

Ext.reg('combosubejecutores', ComboSubejecutores);