/**
 * El 'ControlSuba' consta de 2 botones: uno es para invocar una ventana que permite elegir un archivo para subir; el otro 
 * es un control Button que contiene un vínculo, el cual al ser clickeado puede invocar a otra ventana de navegador, para
 * mostrar a lo que apunte.
 * El parámetro 'urlSuba' apunta al servlet que produce el upload.
 * El parámetro 'urlDestino' indica el directorio (de web) a donde los archivos van a parar.
 * En las requests (para actualizar bases de datos con el nombre del nuevo archivo) el parámetro que se manda conteniendo
 * dicho nombre es 'nombreArchivoSubido'. Esto convendría hacerlo paramétrico, en caso de que hubiere más de un control de suba en
 * una misma pantalla.
 */


SubaFoto = Ext.extend(Ext.form.FormPanel, {
  
  constructor : function(config) {
    SubaFoto.superclass.constructor.call(this, Ext.apply({
        labelWidth: 50,
        labelAlignt: 'right',
        name: 'uploadForm',
        fileUpload : true,
        method: 'POST',
        url: 'url',
        padding: 5,
        plain: true,
        items: [
          {xtype: 'hidden', itemId: 'nombreArchivo', id: 'nombreArchivo'},
          {xtype: 'hidden', id: 'algoSeleccionado'},
          {xtype: 'fileuploadfield', name: 'fileUpload', itemId: 'fileUpload', id: 'fileUpload', emptyText: 'Seleccione un archivo',  fieldLabel: 'Archivo', width: 160, 
                  buttonOnly: false, buttonCfg: { text: '', iconCls: 'upload-icon'}, listeners:{scope: this, 
                    'fileselected':  function(){
                      	 Ext.getCmp('algoSeleccionado').setValue('true');
                     }
                    }
          }
        ],
        buttons: [
         {text: 'Aceptar', itemId: 'botAceptar', ref: '../botAceptar',  listeners:{scope: this, 
           'click':  function(){
           	 var formulario=this;
           	 var nas=Ext.getCmp('algoSeleccionado').getValue();
           	 if (Ext.isEmpty(nas)){
           	 	Ext.Msg.alert('Suba de archivos', 'Debe seleccionar un archivo');
           	 	return;
           	 };
             formulario.getForm().submit({
               url : Ext.getCmp('ventanaUpload').url,
               waitMsg : 'Subiendo archivo ...',
               failure : function(form, action) {
                    if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
                        Ext.Msg.alert('Error',
                            'Status:'+action.response.status+': '+
                            action.response.statusText);
                    }
                    if (action.failureType === Ext.form.Action.SERVER_INVALID){
                        // server responded with success = false
                        Ext.Msg.alert('Invalid', action.result.errormsg);
                    }
                
                 formulario.destroy();
             },
             success : function(form, action) {
               var txtRespuesta = action.response.responseText;
               txtRespuesta=txtRespuesta.replace('<pre>', '');
               txtRespuesta=txtRespuesta.replace('</pre>', '');
               txtRespuesta=txtRespuesta.replace('&amp;#123;', '{'); 
               txtRespuesta=txtRespuesta.replace('&amp;#125;', '}');
               //txtRespuesta=txtRespuesta.replace('"', '');
               var jres= Ext.util.JSON.decode(txtRespuesta);
               if (jres.success == true) {
                 //Ext.getCmp('nombreArchivo').setValue(jres.archivo);
               }else{
                 alert(jres.error);
               }
               formulario.getComponent('nombreArchivo').setValue(jres.archivo);
               var ventana=Ext.getCmp('ventanaUpload');
               ventana.destroy();
               // formulario.destroy();
             }
           }); //del formulario.submit

           }}}
        ]        
	   }, config));
  
  } //constructor
  
});


VentanaSuba = Ext.extend(Ext.Window, {
  
  constructor : function(config) {
    VentanaSuba.superclass.constructor.call(this, Ext.apply({
      modal: true,
      closable: true,
      frame: true,
      layout:'fit',
      width: 245,
      height: 110,
      url : 'url',
      id: 'ventanaUpload',
      items: new SubaFoto(),
     
      getNombreArchivoFoto : function(){
        return Ext.getCmp('nombreArchivo').getValue();
      },
      
            	
    }, config));
  
  } //constructor
  
});    

ControlSuba = Ext.extend(Ext.Panel, {
  constructor : function(config) {
    ControlSuba.superclass.constructor.call(this, Ext.apply({
    layout: 'column',
  		items: [
  		   {xtype: 'hidden', id: 'nombreArchivoSubido'},
  		   {xtype: 'button', text: '...', itemId: 'botAceptar', ref: '../botAceptar', columnWidth: 0.1,
                listeners: {scope: this,  
                'click' :  function(){
                  var win=new VentanaSuba({url: config.urlSuba});
                  win.show();
                  win.on("beforedestroy", function(){
                  	var archivo=win.getNombreArchivoFoto();
                  	var vinculo=Ext.getCmp('controlVinculo');
                  	var textoLink= config.urlDestino + '/' + archivo;
                  	vinculo.setText(archivo);
                  	vinculo.setHref(textoLink);
                  	Ext.getCmp('nombreArchivoSubido').setValue(archivo) ;
                  });
                  }
                }
             },//botón
             {fieldLabel: 'Vínculo', xtype: 'linkbutton', href: 'http://www.google.com', 
                id: 'controlVinculo', target:'_blank', region: 'center', columnWidth: 0.9, width: 200}
		]
    }, config));
  }, //constructor
  
  getValue : function(){
  	return Ext.getCmp('nombreArchivoSubido').getValue();
  },
  
  setValue : function(valor){
  	Ext.getCmp('nombreArchivoSubido').setValue(valor);
  	var vinculo=Ext.getCmp('controlVinculo');
    var textoLink=document.location.protocol + '//' + document.location.host + '/prototipo/archivos/' + valor;
    vinculo.setText(valor);
    vinculo.setHref(textoLink);
  }
  
  
});   


Ext.reg('controlsuba', ControlSuba);


