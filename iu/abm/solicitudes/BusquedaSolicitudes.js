BusquedaSolicitudes = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaSolicitudes.superclass.constructor.call(this, Ext.apply({
        region: 'west',
        frame: true,
        items: [
          {fieldLabel: 'Subejecutor',  itemId: 'cmbSubejecutores', xtype : 'combosubejecutores',  hiddenName: 'subejecutorIdBus',  hiddenId: 'subejecutorIdBus',  width: 220, allowBlank : true},
	        {fieldLabel: 'Estado', xtype: 'combo', id: 'comboEstadosBus', name: 'comboEstadosBus', itemId: 'comboEstadosBus', ref: '../comboEstadosBus', allowBlank: false, 
	          store: new Ext.data.SimpleStore({
	          fields: ['descripcionEstado'],
	    	     data: [["Ingresada"],["Seleccionada"],["Priorizada"],["Aprobada"]]
	    	    }),
              displayField: 'descripcionEstado', valueField: 'descripcionEstado', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false,
              hiddenName: 'estadoBus', triggerAction: 'all'
            }             
        ],
      
        getParamsBusqueda: function(){
          var resultado=new Array();
          this.agregaClaveValor(resultado, 'estado',  Ext.get('estadoBus').dom.value);
          this.agregaClaveValor(resultado, 'subejecutorId', Ext.get('subejecutorIdBus').dom.value);
          return resultado;
        }
      
		}, config));
    
	} //constructor
});
