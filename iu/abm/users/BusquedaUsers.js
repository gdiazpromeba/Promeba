BusquedaUsers = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaUsers.superclass.constructor.call(this, Ext.apply({
        region: 'west',
        frame: true,
        items: [
          {fieldLabel: 'Email',  itemId: 'email', xtype : 'textfield',  width: 180, allowBlank : true},
          {fieldLabel: 'Rol', xtype: 'comboroles', hiddenName: 'rolBusId', allowBlank: true, width: 220}
        ],
      
        getParamsBusqueda: function(){
          var resultado=new Array();
          this.agregaClaveValor(resultado, 'email', this.getComponent('email').getValue());
          this.agregaClaveValor(resultado, 'rolId', Ext.get('rolBusId').dom.value);
          return resultado;
        }
      
		}, config));
    
	} //constructor
});
