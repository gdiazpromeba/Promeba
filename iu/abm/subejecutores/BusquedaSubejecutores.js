BusquedaSubejecutores = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaSubejecutores.superclass.constructor.call(this, Ext.apply({
        region: 'west',
        frame: true,
        items: [
          {fieldLabel: 'Nombre',  itemId: 'nombre', xtype : 'textfield',  width: 220, allowBlank : true},
          {xtype: 'comboregiones', hiddenName: 'regionIdBus', allowBlank: true }
        ],
      
        getParamsBusqueda: function(){
          var resultado=new Array();
          this.agregaClaveValor(resultado, 'nombre', this.getComponent('nombre').getValue());
          this.agregaClaveValor(resultado, 'regionId', Ext.get('regionIdBus').dom.value);
          return resultado;
        }
      
		}, config));
    
	} //constructor
});
