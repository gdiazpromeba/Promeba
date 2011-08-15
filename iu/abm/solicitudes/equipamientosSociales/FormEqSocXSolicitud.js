FormEqSocXSolicitud = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormEqSocXSolicitud.superclass.constructor.call(this, Ext.apply({
  		id: 'formEqSocXSolicitud',
        frame: true,
  		prefijo: 'formEqSocXSolicitud',
  		nombreElementoId: 'eqSocXSolicitudId',
   	    urlAgregado: '/prototipo/svc/conector/solicitudes/insertaEquipamientoSocial',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/solicitudes/borraEquipamientoSocial',
  		items: [
          {xtype: 'hidden', name: 'eqSocXSolicitudId', id: 'eqSocXSolicitudId', itemId: 'eqSocXSolicitudId'},
          {xtype: 'comboequipamientossociales', name: 'comboEquipamientosSociales', id: 'comboEquipamientosSociales', itemId: 'comboEquipamientosSociales', hiddenName: 'eqSocAsignadoId', hiddenId: 'eqSocAsignadoId'},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'eqSocXSolicitudDescripcion', name: 'eqSocXSolicitudDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('eqSocXSolicitudId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('solicitudId'), this);
  	   	  this.getComponent('eqSocXSolicitudDescripcion').setValue(record.get('eqSocXSolicitudDescripcion'));
  	      this.getComponent('comboEquipamientosSociales').setRawValue(record.get('eqSocNombre'));
          Ext.get('eqSocAsignadoId').dom.value=record.data['eqSocId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('eqSocXSolicitudId').getValue();
  	      record.data['solicitudId'] = this.getValorIdPadre(this);
  	      record.data['eqSocXSolicitudDescripcion'] = this.getComponent('eqSocXSolicitudDescripcion').getValue();
  	      record.data['eqSocNombre']=  this.getComponent('comboEquipamientosSociales').getRawValue();
  	      record.data['eqSocId']= Ext.get('eqSocAsignadoId').dom.value;
  		  record.commit();
  	    },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('comboEquipamientosSociales').isValid()){
  			   valido=false;
  			   mensaje='El equipamiento social no es válido';
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











