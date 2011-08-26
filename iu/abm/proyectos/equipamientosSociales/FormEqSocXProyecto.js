FormEqSocXProyecto = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormEqSocXProyecto.superclass.constructor.call(this, Ext.apply({
  		id: 'formEqSocXProyecto',
        frame: true,
  		prefijo: 'formEqSocXProyecto',
  		nombreElementoId: 'eqSocXProyectoId',
   	    urlAgregado: '/prototipo/svc/conector/proyectos/insertaEquipamientoSocial',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/proyectos/borraEquipamientoSocial',
  		items: [
          {xtype: 'hidden', name: 'eqSocXProyectoId', id: 'eqSocXProyectoId', itemId: 'eqSocXProyectoId'},
          {xtype: 'comboequipamientossociales', name: 'comboEquipamientosSociales', id: 'comboEquipamientosSociales', itemId: 'comboEquipamientosSociales', hiddenName: 'eqSocAsignadoId', hiddenId: 'eqSocAsignadoId'},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'eqSocXProyectoDescripcion', name: 'eqSocXProyectoDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('eqSocXProyectoId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('proyectoId'), this);
  	   	  this.getComponent('eqSocXProyectoDescripcion').setValue(record.get('eqSocXProyectoDescripcion'));
  	      this.getComponent('comboEquipamientosSociales').setRawValue(record.get('eqSocNombre'));
          Ext.get('eqSocAsignadoId').dom.value=record.data['eqSocId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('eqSocXProyectoId').getValue();
  	      record.data['proyectoId'] = this.getValorIdPadre(this);
  	      record.data['eqSocXProyectoDescripcion'] = this.getComponent('eqSocXProyectoDescripcion').getValue();
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











