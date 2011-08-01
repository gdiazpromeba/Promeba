BusquedaRoles = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaUsers.superclass.constructor.call(this, Ext.apply({
      region: 'west',
      frame: true,
      items: [
        {fieldLabel: 'Nombre', xtype: 'textfield', itemId: 'nombreBus', allowBlank: true, width: 220}
      ],
      
      getParamsBusqueda: function(){
        var resultado=new Array();
        this.agregaClaveValor(resultado, 'nombre', this.getComponent('nombreBus').getValue());
        return resultado;
      }
      
		}, config));
    
	} //constructor
});
