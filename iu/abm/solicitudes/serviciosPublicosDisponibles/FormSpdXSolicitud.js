FormSpdXSolicitud = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormSpdXSolicitud.superclass.constructor.call(this, Ext.apply({
  		id: 'formSpdXSolicitud',
        frame: true,
  		prefijo: 'formSpdXSolicitud',
  		nombreElementoId: 'spdXSolicitudId',
   	    urlAgregado: '/prototipo/svc/conector/solicitudes/insertaServicioPublicoDisponible',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/solicitudes/borraServicioPublicoDisponible',
  		items: [
          {xtype: 'hidden', name: 'spdXSolicitudId', id: 'spdXSolicitudId', itemId: 'spdXSolicitudId'},
          {xtype: 'comboserviciospublicosdisponibles', name: 'comboServiciosPublicosDisponibles', id: 'comboServiciosPublicosDisponibles', itemId: 'comboServiciosPublicosDisponibles', 
           hiddenName: 'spdAsignadoId', hiddenId: 'spdAsignadoId'},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'spdXSolicitudDescripcion', name: 'spdXSolicitudDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('spdXSolicitudId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('solicitudId'), this);
   	  	  this.getComponent('spdXSolicitudDescripcion').setValue(record.get('eqSocXSolicitudDescripcion'));
  	      this.getComponent('comboServiciosPublicosDisponibles').setRawValue(record.get('sidNombre'));
          Ext.get('spdAsignadoId').dom.value=record.data['spdId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('spdXSolicitudId').getValue();
  	      record.data['solicitudId'] = this.getValorIdPadre(this);
  	      record.data['spdXSolicitudDescripcion'] = this.getComponent('spdXSolicitudDescripcion').getValue();
  	      record.data['spdNombre']=  this.getComponent('comboServiciosPublicosDisponibles').getRawValue();
  	      record.data['spdId']= Ext.get('spdAsignadoId').dom.value;
  		  record.commit();
  	    },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('comboServiciosPublicosDisponibles').isValid()){
  			   valido=false;
  			   mensaje='El servicio público no es válido';
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











