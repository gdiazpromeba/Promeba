BusquedaAreasGeograficas = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaAreasGeograficas.superclass.constructor.call(this, Ext.apply({
      region: 'west',
      frame: true,
      items: [
        {fieldLabel: 'Nombre', xtype: 'textfield', itemId: 'nombreBus', allowBlank: true, width: 220},
        {fieldLabel: 'Fecha desde',  itemId: 'fechaDesde', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
        {fieldLabel: 'Fecha hasta',  itemId: 'fechaHasta', xtype : 'datefield', format: 'd/m/Y', allowBlank : true}

      ],
      
      getParamsBusqueda: function(){
        var resultado=new Array();
        this.agregaClaveValor(resultado, 'nombre', this.getComponent('nombreBus').getValue());
        this.agregaClaveValor(resultado, 'fechaDesde', this.getComponent('fechaDesde').getValue());
        this.agregaClaveValor(resultado, 'fechaHasta', this.getComponent('fechaHasta').getValue());
        return resultado;
      }
      
		}, config));
    
	} //constructor
});
