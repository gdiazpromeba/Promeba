BusquedaInstituciones = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaInstituciones.superclass.constructor.call(this, Ext.apply({
        region: 'west',
        frame: true,
        items: [
          {fieldLabel: 'Nombre',  itemId: 'nombre', xtype : 'textfield',  width: 220, allowBlank : true}
        ],
      
        getParamsBusqueda: function(){
          var resultado=new Array();
          this.agregaClaveValor(resultado, 'nombre', this.getComponent('nombre').getValue());
          return resultado;
        }
      
		}, config));
    
	} //constructor
});
