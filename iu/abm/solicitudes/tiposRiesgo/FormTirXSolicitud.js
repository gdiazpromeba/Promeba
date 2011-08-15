FormTirXSolicitud = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormTirXSolicitud.superclass.constructor.call(this, Ext.apply({
  		id: 'formTirXSolicitud',
        frame: true,
  		prefijo: 'formTirXSolicitud',
  		nombreElementoId: 'tirXSolicitudId',
   	    urlAgregado: '/prototipo/svc/conector/solicitudes/insertaTipoRiesgo',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/solicitudes/borraTipoRiesgo',
  		items: [
          {xtype: 'hidden', name: 'tirXSolicitudId', id: 'tirXSolicitudId', itemId: 'tirXSolicitudId'},
          {xtype: 'combotiposriesgo', name: 'comboTiposRiesgo', id: 'comboTiposRiesgo', itemId: 'comboTiposRiesgo', hiddenName: 'tirAsignadoId', hiddenId: 'tirAsignadoId'},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'tirXSolicitudDescripcion', name: 'tirXSolicitudDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('tirXSolicitudId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('solicitudId'), this);
    	  	  this.getComponent('tirXSolicitudDescripcion').setValue(record.get('tirXSolicitudDescripcion'));
  	      this.getComponent('comboTiposRiesgo').setRawValue(record.get('tirNombre'));
          Ext.get('tirAsignadoId').dom.value=record.data['tirId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('tirXSolicitudId').getValue();
  	      record.data['solicitudId'] = this.getValorIdPadre(this);
  	      record.data['tirXSolicitudDescripcion'] = this.getComponent('tirXSolicitudDescripcion').getValue();
  	      record.data['tirNombre']=  this.getComponent('comboTiposRiesgo').getRawValue();
  	      record.data['tirId']= Ext.get('tirAsignadoId').dom.value;
  		  record.commit();
  	    },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('comboTiposRiesgo').isValid()){
  			   valido=false;
  			   mensaje='El tipo de riesgo no es válido';
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











