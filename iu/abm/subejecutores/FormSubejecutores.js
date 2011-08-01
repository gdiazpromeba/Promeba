FormSubejecutores = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormSubejecutores.superclass.constructor.call(this, Ext.apply({
  		id: 'formSubejecutores',
  		frame: true,
  		prefijo: 'formSubejecutores',
  		nombreElementoId: 'subejecutorId',
  	    urlAgregado: '/prototipo/svc/conector/subejecutores/inserta',
  	    urlModificacion: '/prototipo/svc/conector/subejecutores/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/subejecutores/borra',
  		items: [
  		 {xtype: 'hidden', name: 'subejecutorId', id: 'subejecutorId', itemId: 'subejecutorId'},
  		 {xtype:'tabpanel',
           activeTab: 0,
           itemId: 'tabs',
           border: true,
           deferredRender : false,
           hideMode: 'offsets',
           defaults:{autoHeight: true, bodyStyle:'padding:10px'},
           items:[
             {title:'Datos Generales',
              layout:'form',
              frame: true,
              itemId: 'generales',
              items: [   
                  {fieldLabel: 'Persona jurídica', xtype: 'controlpersonas', itemId: 'controlPersonas', name: 'controlPersonas', width: 400},
                  {fieldLabel: 'Código',  xtype: 'textfield', itemId: 'nombre', name: 'nombre'},
                  {fieldLabel: 'Email',  xtype: 'textfield', itemId: 'email', name: 'email', allowBlank: true, width: 180},
                  {fieldLabel: 'Carácter', xtype: 'combo', id: 'comboCaracter', name: 'comboCaracter', itemId: 'comboCaracter', width: 110, ref: '../comboCaracter', allowBlank: false, 
	                store: new Ext.data.SimpleStore({
	                fields: ['caracter'],
	    	          data: [["Provincial"],["Municipal"]]
	    	        }),
                    displayField: 'caracter', valueField: 'caracter', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false, 
                    allowBlank: false, triggerAction: 'all'
                 },
                 {fieldLabel: 'Contacto',  xtype: 'textarea', itemId: 'contacto', name: 'contacto', height: 75, width: 250, maxLength: 2000, allowBlank: false},
                 {fieldLabel: 'Fec.Legit.',  itemId: 'legitimacion', name: 'legitimacion', xtype : 'datefield', format: 'd/m/Y', allowBlank : false},
                 {xtype: 'controlsuba', fieldLabel: 'Vínculo contrato', width: 200, itemId: 'vinculoContrato', urlSuba: '/prototipo/upload', 
                         urlDestino: document.location.protocol + '//' + document.location.host + '/prototipo/archivos' },
                ],
               }//del panel de datos generales
              ], 
              listeners: {
              	scope: this,
              	'render' : function(tab){
              		tab.setActiveTab(1);
              		tab.setActiveTab(0);
              	}
              	
              }
              
           }//del tabpanel
        ],//de los items globales
  		
  		

  	   
  	  pueblaDatosEnForm : function(record){
  	  	 var generales=this.getComponent('tabs').getComponent('generales');
         this.getComponent('subejecutorId').setValue(record.id);
         generales.getComponent('nombre').setValue(record.get('nombre'));
         generales.getComponent('email').setValue(record.get('email'));
         generales.getComponent('contacto').setValue(record.get('contacto'));
         generales.getComponent('legitimacion').setValue(record.get('legitimacion'));
         generales.getComponent('vinculoContrato').setValue(record.get('convenio'));
         var cmbCaracter=generales.getComponent('comboCaracter');
         cmbCaracter.setRawValue(record.get('caracter'));
         var personas=generales.getComponent('controlPersonas');
         personas.setPersonaId(personas, record.get('personaId'));
         personas.setPersonaDenominacion(personas, record.get('personaDenominacion'));
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['subejecutorId']=  this.getComponent('subejecutorId').getValue();
  	     var generales=this.getComponent('tabs').getComponent('generales');
    	 record.data['nombre']=  generales.getComponent('nombre').getValue();
  	     record.data['caracter']=  generales.getComponent('comboCaracter').getRawValue();
  	     record.data['email']=  generales.getComponent('email').getValue();
  	     record.data['contacto']=  generales.getComponent('contacto').getValue();
    	 record.data['legitimacion']=  generales.getComponent('legitimacion').getValue();
  	     record.data['convenio']=  generales.getComponent('vinculoContrato').getValue();
  	     var personas=generales.getComponent('controlPersonas');
  	     record.data['personaId']=personas.getPersonaId(personas);
  	     record.data['personaDenominacion']=personas.getPersonaDenominacion(personas);     
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
  		   var generales=this.getComponent('tabs').getComponent('generales');
   		   if (!generales.getComponent('nombre').isValid()){
  			   valido=false;
  			   mensaje='El nombre no es válido';
  		   }
  		   
  	  	   if (!generales.getComponent('comboCaracter').isValid()){
  			   valido=false;
  			   mensaje='El carácter no es válido';
  		   }
  		   
 	       
   		   if (!generales.getComponent('legitimacion').isValid()){
  			   valido=false;
  			   mensaje='la fecha de legitimación no es válida';
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











