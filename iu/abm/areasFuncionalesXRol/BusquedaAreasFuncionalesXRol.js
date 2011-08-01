BusquedaAreasFuncionalesXRol = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaAreasFuncionalesXRol.superclass.constructor.call(this, Ext.apply({
      region: 'west',
      frame: true,
      items: [
        {fieldLabel: 'Rol',  xtype : 'comboroles', hiddenName: 'rolIBus', hiddenId : 'rolIdBus'},
      ],
      
      getParamsBusqueda: function(){
        var resultado=new Array();
        this.agregaClaveValor(resultado, 'rolId', Ext.get('rolIdBus').dom.value);
        return resultado;
      }
      
	}, config));
    
  } //constructor
});
