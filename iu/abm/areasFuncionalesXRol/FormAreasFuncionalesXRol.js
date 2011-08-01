FormAreasFuncionalesXRol = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormAreasFuncionalesXRol.superclass.constructor.call(this, Ext.apply({
        frame: true,
  		prefijo: 'formAreasFuncionalesXRol',
  		nombreElementoId: 'areaFuncionalXRolId',
  	    urlAgregado: '/prototipo/svc/conector/areasFuncionalesXRol/inserta',
  	    urlModificacion: '/prototipo/svc/conector/areasFuncionalesXRol/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/areasFuncionalesXRol/borra',
  		items: [
  		 {xtype: 'hidden', name: 'areaFuncionalXRolId', id: 'areaFuncionalXRolId', itemId: 'areaFuncionalXRolId'},
         {fieldLabel: 'Área Funcional',  itemId: 'areasFuncionales', xtype : 'comboareasfuncionales',
           hiddenName: 'areaFuncionalId', hiddenId : 'areaFuncionalId'},
         {fieldLabel: 'Rol',  itemId: 'roles', xtype : 'comboroles',
           hiddenName: 'rolId', hiddenId : 'rolId'},
         {xtype: 'checkbox', fieldLabel: 'Visión', checked: 'true', name: 'vision', itemId: 'vision'},
         {xtype: 'checkbox', fieldLabel: 'Lectura', checked: 'true', name: 'lectura', itemId: 'lectura'},
         {xtype: 'checkbox', fieldLabel: 'Escritura', checked: 'true', name: 'escritura', itemId: 'escritura'},
         {xtype: 'checkbox', fieldLabel: 'Ejecución', checked: 'true', name: 'ejecucion', itemId: 'ejecucion'},
         {xtype: 'checkbox', fieldLabel: 'Impresión', checked: 'true', name: 'impresion', itemId: 'impresion'}
      ],
      
  	  pueblaDatosEnForm : function(record){
         this.getComponent('areaFuncionalXRolId').setValue(record.id);
         this.getComponent('areasFuncionales').setRawValue(record.get('areaFuncionalNombre'));
         Ext.get('areaFuncionalId').dom.value=record.get('areaFuncionalId');
         this.getComponent('roles').setRawValue(record.get('rolNombre'));
         Ext.get('rolId').dom.value=record.get('rolId');
         this.getComponent('roles').setRawValue(record.get('rolNombre'));
		 this.getComponent('vision').setValue(record.get('vision'));
		 this.getComponent('lectura').setValue(record.get('lectura'));
		 this.getComponent('escritura').setValue(record.get('escritura'));
		 this.getComponent('ejecucion').setValue(record.get('ejecucion'));
		 this.getComponent('impresion').setValue(record.get('impresion'));
	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['id']=  this.getComponent('areaFuncionalXRolId').getValue();
  	     record.data['areaFuncionalNombre']=this.getComponent('areasFuncionales').getRawValue();
  	     record.data['areaFuncionalId']= Ext.get('areaFuncionalId').dom.value;
  	     record.data['rolNombre']=this.getComponent('roles').getRawValue();
  	     record.data['rolId']= Ext.get('rolId').dom.value;
  	     record.data['vision']=this.getComponent('vision').getValue();
  	     record.data['lectura']=this.getComponent('lectura').getValue();
  	     record.data['escritura']=this.getComponent('escritura').getValue();
  	     record.data['ejecucion']=this.getComponent('ejecucion').getValue();
  	     record.data['impresion']=this.getComponent('impresion').getValue();
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('areasFuncionales').isValid()){
  			   valido=false;
  			   mensaje='El área funcional no es válida';
  		   }
  		   
     	   if (!this.getComponent('roles').isValid()){
  			   valido=false;
  			   mensaje='El rol no es válido';
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











