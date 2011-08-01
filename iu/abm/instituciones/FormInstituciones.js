FormInstituciones = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormInstituciones.superclass.constructor.call(this, Ext.apply({
        frame: true,
  		prefijo: 'formInstituciones',
  		nombreElementoId: 'institucionId',
  	    urlAgregado: '/prototipo/svc/conector/instituciones/inserta',
  	    urlModificacion: '/prototipo/svc/conector/instituciones/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/instituciones/borra',
  		items: [
          {xtype: 'hidden', name: 'institucionId', id: 'institucionId', itemId: 'institucionId'},
          {xtype:'tabpanel',
           activeTab: 0,
           itemId: 'tabs',
           border: true,
           deferredRender : false,
           hideMode: 'offsets',
           defaults:{autoHeight: true, bodyStyle:'padding:10px'},
           items:[{
                title:'Datos Generales',
                layout:'form',
                frame: true,
                itemId: 'generales',
                items: [          
                  {fieldLabel: 'Nombre',  xtype: 'textfield', itemId: 'nombre', name: 'nombre'},
                  {xtype: 'combotiposdocumento', itemId: 'comboTiposDocumento', name: 'comboTiposDocumento', hiddenName: 'tipoDocumentoId', hiddenId: 'tipoDocumentoId', allowBlank: false },
                  {fieldLabel: 'Número Doc.',  xtype: 'numberfield', itemId: 'documentoNumero', name: 'documentoNumero', width: 130},
                  {fieldLabel: 'Fecha inscripción',  itemId: 'fechaInscripcion', name: 'fechaInscripcion', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
                ],
               },
               {xtype: 'domiciliopanel', title: 'Domicilio', frame: true, prefijo: 'instituciones', itemId: 'panelDomicilio'}
              ], 
              listeners: {
              	scope: this,
              	'render' : function(tab){
              		tab.setActiveTab(1);
              		tab.setActiveTab(0);
              	}
              	
              }
           }//del tabpanel
           
        ],
              
                     
      
  	   
  	  pueblaDatosEnForm : function(record){
         this.getComponent('institucionId').setValue(record.id);
         var generales=this.getComponent('tabs').getComponent('generales');
         generales.getComponent('nombre').setValue(record.get('nombre'));
         generales.getComponent('fechaInscripcion').setValue(record.get('fechaInscripcion'));
         generales.getComponent('documentoNumero').setValue(record.get('documentoNumero'));
         generales.getComponent('comboTiposDocumento').setRawValue(record.get('tipoDocumentoNombre'));
         Ext.get('tipoDocumentoId').dom.value=record.get('tipoDocumentoId');
         var domicilio=this.getComponent('tabs').getComponent('panelDomicilio');
         domicilio.registroAControles(domicilio, record);
 	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	   	 var generales=this.getComponent('tabs').getComponent('generales');
  	     record.id=  this.getComponent('institucionId').getValue();
  	     record.data['nombre']=  generales.getComponent('nombre').getValue();
  	     record.data['fechaInscripcion']=  generales.getComponent('fechaInscripcion').getValue();
  	     record.data['documentoNumero']=  generales.getComponent('documentoNumero').getValue();
    	 record.data['tipoDocumentoNombre']=  generales.getComponent('comboTiposDocumento').getRawValue();
    	 record.data['tipoDocumentoId']=  Ext.get('tipoDocumentoId').dom.value;
    	 var domicilio=this.getComponent('tabs').getComponent('panelDomicilio');
    	 domicilio.controlesARegistro(domicilio, record);

    	 
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  	   	   var generales=this.getComponent('tabs').getComponent('generales');
  		   var mensaje=null;
  		   var valido=true;
  		   
  	       if (!generales.getComponent('nombre').isValid()){
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











