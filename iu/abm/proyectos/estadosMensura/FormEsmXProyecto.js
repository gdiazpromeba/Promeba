FormEsmXProyecto = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormEsmXProyecto.superclass.constructor.call(this, Ext.apply({
  		id: 'formEsmXProyecto',
        frame: true,
  		prefijo: 'formEsmXProyecto',
  		nombreElementoId: 'esmXProyectoId',
   	    urlAgregado: '/prototipo/svc/conector/proyectos/insertaEstadoMensura',
  	    //urlModificacion: '/prototipo/svc/conector/roles.php/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/proyectos/borraEstadoMensura',
  		items: [
          {xtype: 'hidden', name: 'esmXProyectoId', id: 'esmXProyectoId', itemId: 'esmXProyectoId'},
          {xtype: 'comboestadosmensura', name: 'comboEstadosMensura', id: 'comboEstadosMensura', itemId: 'comboEstadosMensura', 
            hiddenName: 'esmAsignadoId', hiddenId: 'esmAsignadoId', width: 420},
          {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'esmXProyectoDescripcion', name: 'esmXProyectoDescripcion', width: 420, allowBlank: false},
          
        ],
        listeners: {
          beforerender: function(me) {
            //oculto botModificar
            me.buttons[1].setVisible(false);
            return true;
          }
 	    },
  	    pueblaDatosEnForm : function(record){
  	  	  this.getComponent('esmXProyectoId').setValue(record.data['id']);
  	  	  this.setValorIdPadre(record.get('proyectoId'), this);
  	   	  this.getComponent('esmXProyectoDescripcion').setValue(record.get('esmXProyectoDescripcion'));
  	      this.getComponent('comboEstadosMensura').setRawValue(record.get('esmNombre'));
          Ext.get('esmAsignadoId').dom.value=record.data['esmId'];
  	    },
  	   
  	    pueblaFormEnRegistro : function(record){
  	      record.data['id']=  this.getComponent('esmXProyectoId').getValue();
  	      record.data['proyectoId'] = this.getValorIdPadre(this);
  	      record.data['esmXProyectoDescripcion'] = this.getComponent('esmXProyectoDescripcion').getValue();
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











