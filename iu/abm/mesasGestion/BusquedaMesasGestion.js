BusquedaMesasGestion = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaMesasGestion.superclass.constructor.call(this, Ext.apply({
      region: 'west',
      frame: true,
      items: [
        {fieldLabel: 'Mesa de gestión',  itemId: 'fechaMesaGestion', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
        {fieldLabel: 'Acta de acuerdo',  itemId: 'fechaActaAcuerdo', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
        {fieldLabel: 'Estado', xtype: 'combo',  itemId: 'comboEstados', allowBlank: true, 
	          store: new Ext.data.SimpleStore({
	          fields: ['descripcionEstado'],
	    	     data: [["En formulación"],["Finalizada"]]
	    	    }),
              displayField: 'descripcionEstado', valueField: 'descripcionEstado', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false,
              hiddenName: 'estado', triggerAction: 'all'
         }
      ],
      
      getParamsBusqueda: function(){
        var resultado=new Array();
        this.agregaClaveValor(resultado, 'estado', this.getComponent('comboEstados').getValue());
        this.agregaClaveValor(resultado, 'fechaMesaGestion', this.formatoFecha(this.getComponent('fechaMesaGestion').getValue()));
        this.agregaClaveValor(resultado, 'fechaActaAcuerdo', this.formatoFecha(this.getComponent('fechaActaAcuerdo').getValue()));
        return resultado;
      }
      
	}, config));
    
  } //constructor
});
