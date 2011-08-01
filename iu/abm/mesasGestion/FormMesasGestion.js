FormMesasGestion = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormMesasGestion.superclass.constructor.call(this, Ext.apply({
        frame: true,
  		prefijo: 'formMesasGestion',
  		nombreElementoId: 'mesaGestionId',
  	    urlAgregado: '/prototipo/svc/conector/mesasGestion/inserta',
  	    urlModificacion: '/prototipo/svc/conector/mesasGestion/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/mesasGestion/borra',
  		items: [
          {xtype: 'hidden', name: 'mesaGestionId', id: 'mesaGestionId', itemId: 'mesaGestionId'},
          {fieldLabel: 'Mesa de gestión',  name: 'fechaMesaGestion', itemId: 'fechaMesaGestion', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
          {fieldLabel: 'Acta de acuerdo',  name: 'fechaActaAcuerdo',  itemId: 'fechaActaAcuerdo', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
          {fieldLabel: 'Estado', xtype: 'combo', id: 'comboEstados', name: 'comboEstados', itemId: 'comboEstados', ref: '../comboEstados', allowBlank: false, 
	          store: new Ext.data.SimpleStore({
	          fields: ['descripcionEstado'],
	    	     data: [["En formulación"],["Finalizada"]]
	    	    }),
              displayField: 'descripcionEstado', valueField: 'descripcionEstado', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false,
              hiddenName: 'estado', triggerAction: 'all'
          }          
      ],      
      
  	   
  	  pueblaDatosEnForm : function(record){
         this.getComponent('mesaGestionId').setValue(record.id);
         this.getComponent('fechaMesaGestion').setValue(record.get('fechaMesaGestion'));
         this.getComponent('fechaActaAcuerdo').setValue(record.get('fechaActaAcuerdo'));
         this.getComponent('comboEstados').setRawValue(record.get('estado'));
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['id']=  this.getComponent('mesaGestionId').getValue();
  	     record.data['fechaMesaGestion']=  this.getComponent('fechaMesaGestion').getValue();
  	     record.data['fechaActaAcuerdo']=  this.getComponent('fechaActaAcuerdo').getValue();
  	     record.data['estado']=  this.getComponent('comboEstados').getValue();
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('fechaMesaGestion').isValid()){
  			   valido=false;
  			   mensaje='La fecha de Mesa de gestión no es válida';
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











