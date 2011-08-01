FormRoles_x_User = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormRoles_x_User.superclass.constructor.call(this, Ext.apply({
  		id: 'formRoles',
        frame: true,
  		prefijo: 'frmRXU',
  		nombreElementoId: 'rolXUsuarioId',
   	    urlAgregado: '/prototipo/svc/conector/usuarios/insertaRol',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/usuarios/borraRol',
  		items: [
          {xtype: 'hidden', name: 'rolXUsuarioId', id: 'rolXUsuarioId', itemId: 'rolXUsuarioId'},
          {xtype: 'comboroles', name: 'comboRolesXUsuario', id: 'comboRolesXUsuario', itemId: 'comboRolesXUsuario', hiddenName: 'rolAsignadoId', hiddenId: 'rolAsignadoId'}
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('rolXUsuarioId').setValue(record.data['id']);
  	  	  this.getComponent('valorIdPadre').setValue(record.get('usuarioId'));
  	      this.getComponent('comboRolesXUsuario').setRawValue(record.get('rolNombre'));
          Ext.get('rolAsignadoId').dom.value=record.data['id'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('rolXUsuarioId').getValue();
  	      record.data['usuarioId'] = this.getComponent('valorIdPadre').getValue();
  	      record.data['rolNombre']=  this.getComponent('comboRolesXUsuario').getRawValue();
  	      record.data['rolId']= Ext.get('rolAsignadoId').dom.value;
  		  record.commit();
  	    },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('comboRolesXUsuario').isValid()){
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











