FormPersonasJuridicas = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormPersonasJuridicas.superclass.constructor.call(this, Ext.apply({
        frame: true,
  		prefijo: 'formPersonasJuridicas',
  		nombreElementoId: 'personaJuridicaId',
  	    urlAgregado: '/prototipo/svc/conector/personasJuridicas/inserta',
  	    urlModificacion: '/prototipo/svc/conector/personasJuridicas/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/personasJuridicas/borra',
  		items: [
          {xtype: 'hidden', name: 'personaJuridicaId', id: 'personaJuridicaId', itemId: 'personaJuridicaId'},
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
                  {fieldLabel: 'Nombre',  xtype: 'textfield', itemId: 'nombre', name: 'nombre', width: 350},
                  {fieldLabel: 'Fecha de inscripción',  name: 'fechaInscripcion',  itemId: 'fechaInscripcion', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
                  {fieldLabel: 'Personería',  xtype: 'textfield', itemId: 'personeria', name: 'personeria', width: 190},
                  {fieldLabel: 'CUIT',  xtype: 'textfield', itemId: 'cuit', name: 'cuit', width: 150},
                ],
               },
               {xtype: 'domiciliopanel', title: 'Domicilio', frame: true, prefijo: 'personasJuridicas', itemId: 'panelDomicilio'}
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
         this.getComponent('personaJuridicaId').setValue(record.id);
         var generales=this.getComponent('tabs').getComponent('generales');
         generales.getComponent('nombre').setValue(record.get('nombre'));
         generales.getComponent('fechaInscripcion').setValue(record.get('inscripcion'));
         generales.getComponent('personeria').setValue(record.get('personeria'));
         generales.getComponent('cuit').setValue(record.get('cuit'));
         var domicilio=this.getComponent('tabs').getComponent('panelDomicilio');
         domicilio.registroAControles(domicilio, record);
 	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	   	 var generales=this.getComponent('tabs').getComponent('generales');
  	     record.id=  this.getComponent('personaJuridicaId').getValue();
  	     record.data['nombre']=  generales.getComponent('nombre').getValue();
  	     record.data['inscripcion']=  generales.getComponent('fechaInscripcion').getValue();
  	     record.data['personeria']=  generales.getComponent('personeria').getValue();
  	     record.data['cuit']=  generales.getComponent('cuit').getValue();
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











