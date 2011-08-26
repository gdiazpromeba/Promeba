FormTirXProyecto = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormTirXProyecto.superclass.constructor.call(this, Ext.apply({
  		id: 'formTirXProyecto',
        frame: true,
  		prefijo: 'formTirXProyecto',
  		nombreElementoId: 'tirXProyectoId',
   	    urlAgregado: '/prototipo/svc/conector/proyectos/insertaTipoRiesgo',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/proyectos/borraTipoRiesgo',
  		items: [
          {xtype: 'hidden', name: 'tirXProyectoId', id: 'tirXProyectoId', itemId: 'tirXProyectoId'},
          {xtype: 'combotiposriesgo', name: 'comboTiposRiesgo', id: 'comboTiposRiesgo', itemId: 'comboTiposRiesgo', hiddenName: 'tirAsignadoId', hiddenId: 'tirAsignadoId'},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'tirXProyectoDescripcion', name: 'tirXProyectoDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('tirXProyectoId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('proyectoId'), this);
    	  	  this.getComponent('tirXProyectoDescripcion').setValue(record.get('tirXProyectoDescripcion'));
  	      this.getComponent('comboTiposRiesgo').setRawValue(record.get('tirNombre'));
          Ext.get('tirAsignadoId').dom.value=record.data['tirId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('tirXProyectoId').getValue();
  	      record.data['proyectoId'] = this.getValorIdPadre(this);
  	      record.data['tirXProyectoDescripcion'] = this.getComponent('tirXProyectoDescripcion').getValue();
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











