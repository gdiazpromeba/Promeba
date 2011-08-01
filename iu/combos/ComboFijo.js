ComboFijo = Ext.extend(Ext.form.ComboBox, {

    constructor : function(config) {
      ComboFijo.superclass.constructor.call(this, Ext.apply({
        xtype: 'combo',
        loadingText: 'cargando ...',
        typeAhead: false, 
        forceSelecion: true, 
        hideTrigger: false,
        lazyInit: false, 
        triggerAction: 'all',
        itemSelector: 'div.search-item',
        pageSize:0
    }, config));
  } //constructor
  
});