VentanaSuba = Ext.extend(Ext.Window, {
  
  constructor : function(config) {
    VentanaSuba.superclass.constructor.call(this, Ext.apply({
      modal: true,
      closable: true,
      frame: true,
      layout:'fit',
      width: 230,
      height: 120,
      id: 'ventanaUpload',
      items: new SubaFoto({url: url}),
      
      getNombreArchivoFoto : function(){
        return Ext.getCmp('nombreArchivoFoto').getValue();
      }      
            	
    }, config));
  
  } //constructor
  
});


      






