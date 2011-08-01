BusquedaCargos = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaCargos.superclass.constructor.call(this, Ext.apply({
      region: 'west',
      frame: true,
      items: [
        {fieldLabel: 'Nombre', xtype: 'textfield', itemId: 'nombre', allowBlank: true, width: 220}
      ],
      
      getParamsBusqueda: function(){
        var resultado=new Array();
        this.agregaClaveValor(resultado, 'nombre', this.getComponent('nombre').getValue());
        return resultado;
      }
      
		}, config));
    
	} //constructor
});
