FormRoles = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormRoles.superclass.constructor.call(this, Ext.apply({
  		id: 'formRoles',
        frame: true,
  		prefijo: 'formRoles',
  		nombreElementoId: 'rolId',
  	    urlAgregado: '/prototipo/svc/conector/roles/inserta',
  	    urlModificacion: '/prototipo/svc/conector/roles/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/roles/borra',
  		items: [
          {xtype: 'hidden', name: 'rolId', id: 'rolId', itemId: 'rolId'},
          {fieldLabel: 'Nombre',  xtype: 'textfield', itemId: 'nombre', name: 'nombre'}
      ],      
      
  	   
  	  pueblaDatosEnForm : function(record){
         this.getComponent('rolId').setValue(record.id);
         this.getComponent('nombre').setValue(record.get('nombre'));
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['id']=  this.getComponent('rolId').getValue();
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











