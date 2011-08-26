FormSpdXProyecto = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormSpdXProyecto.superclass.constructor.call(this, Ext.apply({
  		id: 'formSpdXProyecto',
        frame: true,
  		prefijo: 'formSpdXProyecto',
  		nombreElementoId: 'spdXProyectoId',
   	    urlAgregado: '/prototipo/svc/conector/proyectos/insertaServicioPublicoDisponible',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/proyectos/borraServicioPublicoDisponible',
  		items: [
          {xtype: 'hidden', name: 'spdXProyectoId', id: 'spdXProyectoId', itemId: 'spdXProyectoId'},
          {xtype: 'comboserviciospublicosdisponibles', name: 'comboServiciosPublicosDisponibles', id: 'comboServiciosPublicosDisponibles', itemId: 'comboServiciosPublicosDisponibles', 
           hiddenName: 'spdAsignadoId', hiddenId: 'spdAsignadoId'},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'spdXProyectoDescripcion', name: 'spdXProyectoDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('spdXProyectoId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('proyectoId'), this);
   	  	  this.getComponent('spdXProyectoDescripcion').setValue(record.get('eqSocXProyectoDescripcion'));
  	      this.getComponent('comboServiciosPublicosDisponibles').setRawValue(record.get('sidNombre'));
          Ext.get('spdAsignadoId').dom.value=record.data['spdId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('spdXProyectoId').getValue();
  	      record.data['proyectoId'] = this.getValorIdPadre(this);
  	      record.data['spdXProyectoDescripcion'] = this.getComponent('spdXProyectoDescripcion').getValue();
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











