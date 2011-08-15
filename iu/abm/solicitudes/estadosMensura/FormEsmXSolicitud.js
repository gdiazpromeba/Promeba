FormEsmXSolicitud = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormEsmXSolicitud.superclass.constructor.call(this, Ext.apply({
  		id: 'formEsmXSolicitud',
        frame: true,
  		prefijo: 'formEsmXSolicitud',
  		nombreElementoId: 'esmXSolicitudId',
   	    urlAgregado: '/prototipo/svc/conector/solicitudes/insertaEstadoMensura',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/solicitudes/borraEstadoMensura',
  		items: [
          {xtype: 'hidden', name: 'esmXSolicitudId', id: 'esmXSolicitudId', itemId: 'esmXSolicitudId'},
          {xtype: 'comboestadosmensura', name: 'comboEstadosMensura', id: 'comboEstadosMensura', itemId: 'comboEstadosMensura', 
            hiddenName: 'esmAsignadoId', hiddenId: 'esmAsignadoId', width: 420},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'esmXSolicitudDescripcion', name: 'esmXSolicitudDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('esmXSolicitudId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('solicitudId'), this);
  	   	  this.getComponent('esmXSolicitudDescripcion').setValue(record.get('esmXSolicitudDescripcion'));
  	      this.getComponent('comboEstadosMensura').setRawValue(record.get('esmNombre'));
          Ext.get('esmAsignadoId').dom.value=record.data['esmId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('esmXSolicitudId').getValue();
  	      record.data['solicitudId'] = this.getValorIdPadre(this);
  	      record.data['esmXSolicitudDescripcion'] = this.getComponent('esmXSolicitudDescripcion').getValue();
  	      record.data['esmNombre']=  this.getComponent('comboEstadosMensura').getRawValue();
  	      record.data['esmId']= Ext.get('esmAsignadoId').dom.value;
  		  record.commit();
  	    },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('comboEstadosMensura').isValid()){
  			   valido=false;
  			   mensaje='El estado de mensura no es válido';
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











