FormUsers = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormUsers.superclass.constructor.call(this, Ext.apply({
  		id: 'formUsers',
        frame: true,
  		prefijo: 'formUsers',
  		nombreElementoId: 'usuarioId',
  	    urlAgregado: '/prototipo/svc/conector/usuarios/inserta',
  	    urlModificacion: '/prototipo/svc/conector/usuarios/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/usuarios/borra',
  		items: [
          {xtype: 'hidden', name: 'usuarioId', id: 'usuarioId', itemId: 'usuarioId'},
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
                  {fieldLabel: 'Login',  xtype: 'textfield', itemId: 'login', name: 'login'},
                  {fieldLabel: 'Email',  xtype: 'textfield', itemId: 'email', name: 'email', width: 180},
                  {fieldLabel: 'Clave', xtype: 'textfield', itemId: 'clave', name: 'clave', inputType: 'password'},
                  {fieldLabel: 'Confirmación clave', xtype: 'textfield', itemId: 'confirmacionClave', name: 'confirmacionClave', inputType: 'password'},
                  {fieldLabel: 'Persona física', xtype: 'controlpersonas', itemId: 'controlPersonas', name: 'controlPersonas', width: 400}
                ],
               }
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
         this.getComponent('usuarioId').setValue(record.id);
         var generales=this.getComponent('tabs').getComponent('generales');
         generales.getComponent('login').setValue(record.get('login'));
         generales.getComponent('email').setValue(record.get('email'));
         generales.getComponent('clave').setValue(record.get('clave'));
         generales.getComponent('confirmacionClave').setValue(record.get('clave'));
         var personas=generales.getComponent('controlPersonas');
         personas.setPersonaId(personas, record.get('personaId'));
         personas.setPersonaDenominacion(personas, record.get('personaDenominacion'));
 	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	   	 var generales=this.getComponent('tabs').getComponent('generales');
  	     record.data['usuarioId']=  this.getComponent('usuarioId').getValue();
  	     record.data['login']=  generales.getComponent('login').getValue();
  	     record.data['email']=  generales.getComponent('email').getValue();
  	     record.data['clave']=  generales.getComponent('clave').getValue();
  	     var personas=generales.getComponent('controlPersonas');
  	     record.data['personaId']=personas.getPersonaId(personas);
  	     record.data['personaDenominacion']=personas.getPersonaDenominacion(personas);     
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  	   	   var generales=this.getComponent('tabs').getComponent('generales');
  		   var mensaje=null;
  		   var valido=true;
  		   
  		   if (generales.getComponent('clave').getValue()!=generales.getComponent('confirmacionClave').getValue()){
  			   valido=false;
  			   mensaje='Los campos de Clave y Confirmacion de Clave deben coincidir';
  		   }
  		   
   		   if (!generales.getComponent('login').isValid()){
  			   valido=false;
  			   mensaje='El login no es válido';
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











