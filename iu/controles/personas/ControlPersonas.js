FormPersonas = Ext.extend(Ext.form.FormPanel, {
  
  constructor : function(config) {
    FormPersonas.superclass.constructor.call(this, Ext.apply({
        labelWidth: 50,
        labelAlignt: 'right',
        name: 'controlPersonas',
        fileUpload : true,
        padding: 5,
        plain: true,
        layout: 'border',
        aceptarPulsado: null,
        url : '/prototipo/vacio',
        items: [
          {xtype: 'hidden', itemId: 'aceptado', name: 'aceptado'},
          {xtype: 'panel', region: 'north', height: 40, layout: 'column', border: false, plain: true, padding: 5,
            items: [
              {xtype: 'form', itemId: 'panFisica', id: 'panFisica', columnWidth: 0.16, labelWidth: 40, border: false, plain: true,
                items: [{xtype: 'checkbox', fieldLabel: 'Física', checked: 'true', name: 'fisica', itemId: 'fisica'}]
              },
              {xtype: 'form', itemId: 'panJuridica', id: 'panJuridica', columnWidth: 0.16, labelWidth: 50, border: false, plain: true,
                items: [{xtype: 'checkbox', fieldLabel: 'Jurídica', checked: 'true', name: 'juridica', itemId: 'juridica',}]
              },
              {xtype: 'form', itemId: 'panDenominacion', id: 'panDenominacion', columnWidth: 0.48, border: false, plain: true, labelWidth: 80,
                items: [{xtype: 'textfield', fieldLabel: 'Denominacion', name: 'denominacion', itemId: 'denominacion'}]
              },
              {xtype: 'form', itemId: 'panBoton', columnWidth: 0.2, border: false, plain: true,
                items: [
                 {xtype: 'button', text: 'Buscar', itemId: 'botBuscar', ref: '../botBuscar',  listeners:{scope: this, 
		           'click':  function(){
		           	 var formulario=this;
		               formulario.getForm().submit({
		               success : function(formulario) {
		                 var grilla=Ext.getCmp('grillaPersonas');
        	             var str=grilla.getStore();
        	             var isFisica=Ext.getCmp('panFisica').getComponent('fisica').getValue();
        	             var isJuridica=Ext.getCmp('panJuridica').getComponent('juridica').getValue();
        	             var denominacion=Ext.getCmp('panDenominacion').getComponent('denominacion').getValue();
        	             str.reload({
        		           params: {
        			         fisica: isFisica,
        			         juridica: isJuridica,
        			         denominacion: denominacion
        		           }
        	             });
                       }
		             }); //del formulario.submit
		           } //de la función del click
		          }//de los listeners
		         },	//del botón
                ]
              },              
              
            ]
          },
          new GrillaPersonas({id: 'grillaPersonas', itemId: 'grillaPersonas', region: 'center', width: 440, height: 200}),
        ],
        buttons: [
         {text: 'Aceptar', itemId: 'botAceptar', ref: '../botAceptar',  listeners:{scope: this, 
           'click':  function(){
           	 var grillaPersonas=Ext.getCmp('grillaPersonas');
           	 if (grillaPersonas.obtieneSeleccionado()==null){
           	 	Ext.MessageBox.alert("Personas", "Debe seleccionar un registro");
           	 	return;
           	 }
           	 var formulario=this;
               formulario.getForm().submit({
               success : function(controlPersonas, formulario) {
               	 //Ext.get(controlPersonas.id).dom.elements['aceptado'].value='sí';
               	 var control=Ext.getCmp('controlPersonasId');
               	 var ventana=Ext.getCmp('ventanaPersonas');
               	 var formPersonas = ventana.getComponent('formPersonas');
               	 var grillaPersonas=formPersonas.getComponent('grillaPersonas');
                 var registro=grillaPersonas.obtieneSeleccionado();
                 control.getComponent('personaId').setValue(registro.get('id'));
                 control.getComponent('personaDenominacion').setValue(registro.get('denominacion'));
               	 ventana.destroy();
               	 // var control=Ext.getCmp(controlPersonas.id);
               	 // control.getComponent('aceptado').setValue('sí');
               }
             }); //del formulario.submit
           } //de la función del click
          }//de los listeners
         },	//del botón
         {text: 'Cancelar', itemId: 'botCancelar', ref: '../botCancelar',  listeners:{scope: this, 
           'click':  function(){
           	 var formulario=this;
               formulario.getForm().submit({
               success : function(form, action) {
               	 formulario.getComponent('aceptado').setValue('no');
               	 var ventana=Ext.getCmp('ventanaPersonas');
               	 ventana.destroy();
               }
             }); //del formulario.submit
           } //de la función del click
          }//de los listeners
         }	//del botón
        ],
        listeners: {
        	 scope: this,  
                 'render' :  function(){
                    this.pueblaGrilla();       	
                  }
        },
        pueblaGrilla : function (){
        	var grilla=Ext.getCmp('grillaPersonas');
        	var str=grilla.getStore();
        	str.reload({
        		params: {
        			fisica: true,
        			juridica: true
        		}
        	});
        }

                
	   }, config));
  } //constructor
  
});


VentanaPersonas = Ext.extend(Ext.Window, {
  
  constructor : function(config) {
    VentanaPersonas.superclass.constructor.call(this, Ext.apply({
      modal: true,
      closable: true,
      frame: true,
      layout:'fit',
      width: 445,
      height: 210,
      url : 'url',
      id: 'ventanaPersonas',
      items: [
        new FormPersonas({itemId: 'formPersonas'}),
      ],
     
      getNombreArchivoFoto : function(){
        return Ext.getCmp('nombreArchivo').getValue();
      },
      
            	
    }, config));
  
  } //constructor
  
});    

ControlPersonas = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    ControlPersonas.superclass.constructor.call(this, Ext.apply({
    layout: 'column',
    id: 'controlPersonasId',
  		items: [
  		   {xtype: 'hidden', id: 'personaId'},
  	  	   {xtype: 'button', text: '...', itemId: 'botAceptar', ref: '../botAceptar', columnWidth: 0.1,
                listeners: {scope: this,  
                'click' :  function(){
                  var control=this;
                  var win=new VentanaPersonas();
                  win.show();
                  //var formPersonas=win.getComponent('formPersonas');
                  win.on("caca", function(ventana){
                  	var formPersonas=ventana.getComponent('formPersonas');
                  	//var fueAceptado=ventana.getComponent('aceptada').getValue();
                  	var fueAceptado=formPersonas.getComponent('aceptado').getValue();
                  	if (fueAceptado=='sí'){
                  	  var grillaPersonas=formPersonas.getComponent('grillaPersonas');
                  	  var registro=grillaPersonas.obtieneSeleccionado();
                  	  control.getComponent('personaId').setValue(registro.get('id'));
                  	  control.getComponent('personaDenominacion').setValue(registro.get('denominacion'));
                  	  //win.destroy();
                  	}
                  });
                  }
                }
             },//botón
             {fieldLabel: 'Persona', xtype: 'textfield', itemId: 'personaDenominacion', name: 'personaDenominacion', width: 300}
		]
    }, config));
  }, //constructor
  
  getPersonaId : function(me){
  	me.getComponent('personaId').getValue();
  },
  
  getPersonaDenominacion : function(me){
  	me.getComponent('personaDenominacion').getValue();
  },
  
  setPersonaId : function(me, personaId){
  	me.getComponent('personaId').setValue(personaId);
  },
  
  setPersonaDenominacion : function(me, personaDenominacion){
  	me.getComponent('personaDenominacion').setValue(personaDenominacion);
  }
  
  
});   


Ext.reg('controlpersonas', ControlPersonas);


