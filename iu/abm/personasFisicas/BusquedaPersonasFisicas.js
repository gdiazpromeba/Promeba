BusquedaPersonasFisicas = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaPersonasFisicas.superclass.constructor.call(this, Ext.apply({
        region: 'west',
        frame: true,
        items: [
          {fieldLabel: 'Apellido',  itemId: 'apellido', xtype : 'textfield',  width: 220, allowBlank : true}
        ],
      
        getParamsBusqueda: function(){
          var resultado=new Array();
          this.agregaClaveValor(resultado, 'apellido', this.getComponent('apellido').getValue());
          return resultado;
        }
      
		}, config));
    
	} //constructor
});
