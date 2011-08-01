FormPersonasFisicas = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormPersonasFisicas.superclass.constructor.call(this, Ext.apply({
        frame: true,
  		prefijo: 'formPersonasFisicas',
  		nombreElementoId: 'personaFisicaId',
  	    urlAgregado: '/prototipo/svc/conector/personasFisicas/inserta',
  	    urlModificacion: '/prototipo/svc/conector/personasFisicas/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/personasFisicas/borra',
  		items: [
          {xtype: 'hidden', name: 'personaFisicaId', id: 'personaFisicaId', itemId: 'personaFisicaId'},
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
                  {fieldLabel: 'Apellido',  xtype: 'textfield', itemId: 'apellido', name: 'apellido'},
                  {xtype: 'combotiposdocumento', itemId: 'comboTiposDocumento', name: 'comboTiposDocumento', hiddenName: 'tipoDocumentoId', hiddenId: 'tipoDocumentoId', allowBlank: false },
                  {fieldLabel: 'Número Doc.',  xtype: 'numberfield', itemId: 'documentoNumero', name: 'documentoNumero', width: 130},
                  {fieldLabel: 'Ocupación',  xtype: 'textfield', itemId: 'ocupacion', name: 'ocupacion', width: 190},
                  {fieldLabel: 'Sexo', xtype: 'combo', id: 'comboSexo', name: 'comboSexo', itemId: 'comboSexo', ref: '../comboSexo', allowBlank: false, 
	                store: new Ext.data.SimpleStore({
	                  fields: ['descripcionSexo'],
	    	           data: [["M"],["F"]]
	    	        }),
                    displayField: 'descripcionSexo', valueField: 'descripcionSexo', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false,
                    hiddenName: 'sexo', triggerAction: 'all', width: 40
                 },
                 {fieldLabel: 'Fecha de nacimiento',  name: 'fechaNacimiento',  itemId: 'fechaNacimiento', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
                ],
               },
               {xtype: 'domiciliopanel', title: 'Domicilio', frame: true, prefijo: 'personasFisicas', itemId: 'panelDomicilio'}
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
         this.getComponent('personaFisicaId').setValue(record.id);
         var generales=this.getComponent('tabs').getComponent('generales');
         generales.getComponent('nombre').setValue(record.get('nombre'));
         generales.getComponent('apellido').setValue(record.get('apellido'));
         generales.getComponent('documentoNumero').setValue(record.get('documentoNumero'));
         generales.getComponent('comboTiposDocumento').setRawValue(record.get('tipoDocumentoNombre'));
         Ext.get('tipoDocumentoId').dom.value=record.get('tipoDocumentoId');
         var domicilio=this.getComponent('tabs').getComponent('panelDomicilio');
         domicilio.registroAControles(domicilio, record);
         generales.getComponent('ocupacion').setRawValue(record.get('ocupacion'));
         generales.getComponent('comboSexo').setRawValue(record.get('sexo'));
         generales.getComponent('fechaNacimiento').setValue(record.get('nacimiento'));
 	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	   	 var generales=this.getComponent('tabs').getComponent('generales');
  	     record.id=  this.getComponent('personaFisicaId').getValue();
  	     record.data['nombre']=  generales.getComponent('nombre').getValue();
  	     record.data['apellido']=  generales.getComponent('apellido').getValue();
  	     record.data['documentoNumero']=  generales.getComponent('documentoNumero').getValue();
    	 record.data['tipoDocumentoNombre']=  generales.getComponent('comboTiposDocumento').getRawValue();
    	 record.data['tipoDocumentoId']=  Ext.get('tipoDocumentoId').dom.value;
    	 var domicilio=this.getComponent('tabs').getComponent('panelDomicilio');
    	 domicilio.controlesARegistro(domicilio, record);
    	 record.data['ocupacion'] = generales.getComponent('ocupacion').getValue();
    	 record.data['sexo'] = generales.getComponent('comboSexo').getRawValue();
    	 record.data['nacimiento'] = generales.getComponent('fechaNacimiento').getValue();
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
  		   
  	  	   if (!generales.getComponent('apellido').isValid()){
  			   valido=false;
  			   mensaje='El apellido no es válido';
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











