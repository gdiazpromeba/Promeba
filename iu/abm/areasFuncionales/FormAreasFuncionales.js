FormAreasFuncionales = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormAreasFuncionales.superclass.constructor.call(this, Ext.apply({
  		id: 'formUsers',
        frame: true,
  		prefijo: 'formAreasFuncionales',
  		nombreElementoId: 'areaFuncionalId',
  	    urlAgregado: '/prototipo/svc/conector/areasFuncionales/inserta',
  	    urlModificacion: '/prototipo/svc/conector/areasFuncionales/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/areasFuncionales/borra',
  		items: [
          {xtype: 'hidden', name: 'areaFuncionalId', id: 'areaFuncionalId', itemId: 'areaFuncionalId'},
          {fieldLabel: 'Nombre',  xtype: 'textfield', itemId: 'nombre', name: 'nombre'},
          {fieldLabel: 'Orden',  xtype: 'numberfield', itemId: 'orden', name: 'orden', width: 30}
        ],      
      
  	   
  	    pueblaDatosEnForm : function(record){
          this.getComponent('areaFuncionalId').setValue(record.id);
          this.getComponent('nombre').setValue(record.get('nombre'));
          this.getComponent('orden').setValue(record.get('orden'));
  	    },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['areaFuncionalId']=  this.getComponent('areaFuncionalId').getValue();
  	     record.data['nombre']=  this.getComponent('nombre').getValue();
  	     record.data['orden']=  this.getComponent('orden').getRawValue();
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('nombre').isValid()){
  			   valido=false;
  			   mensaje='El nombre no es válido';
  		   }
  		   
  	  	   if (!this.getComponent('orden').isValid()){
  			   valido=false;
  			   mensaje='El orden no es válido';
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











