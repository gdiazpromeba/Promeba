FormCargos = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormCargos.superclass.constructor.call(this, Ext.apply({
        frame: true,
  		prefijo: 'formCargos',
  		nombreElementoId: 'cargoId',
  	    urlAgregado: '/prototipo/svc/conector/cargos/inserta',
  	    urlModificacion: '/prototipo/svc/conector/cargos/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/cargos/borra',
  		items: [
          {xtype: 'hidden', name: 'cargoId', id: 'cargoId', itemId: 'cargoId'},
          {fieldLabel: 'Nombre',  xtype: 'textfield', itemId: 'nombre', name: 'nombre', width: 200}
      ],      
      
  	   
  	  pueblaDatosEnForm : function(record){
         this.getComponent('cargoId').setValue(record.id);
         this.getComponent('nombre').setValue(record.get('nombre'));
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['id']=  this.getComponent('cargoId').getValue();
  	     record.data['nombre']=  this.getComponent('nombre').getValue();
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('nombre').isValid()){
  			   valido=false;
  			   mensaje='El nombre no es válido';
  		   }
  		   
  		   if (!valido && muestraVentana){
  	           Ext.MessageBox.show({
  	               title: 'Validación de campos',
  	               msg: mensaje,
  	               buttons: Ext.MessageBox.OK,
  	               icon: Ext.MessageBox.ERROR
  	           });
  		   }
  		   return valido;
  	   }
  	   
  	   
	   }, config));
  
  } //constructor
  
  
  
});











