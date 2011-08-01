function creaPanReportePrecios(){
	var panelReporte = new Ext.Panel({
		layout: 'form',
		padding: 5,
		items: [	
          {fieldLabel: 'Sillones', xtype: 'button', text: 'Imprimir',  
              listeners: {
                scope: this,
                click: function(boton, evento){
                  Ext.Ajax.request({
                  url:  '/prototipo/svc/conector/reportesPrecios.php/sillones',
                  method: 'POST',
                  params: {},
                  failure: function (response, options) {
                    Ext.Msg.show({ title:'Impresión', msg: 'Error de red o base de datos', buttons: Ext.Msg.OK});
                  },
                  success: function (response, options) {
                      var html=response.responseText;
                      var win=window.open('', 'Planilla de producción', "dependent=true, height = 800, width = 520, resizable = yes, scrollbars = yes, menubar=yes");
                      win.document.write(html);
                      win.document.close(); 
                  }
                });
              }
           }
          },
          {fieldLabel: 'Sillas', xtype: 'button', text: 'Imprimir',  
              listeners: {
                scope: this,
                click: function(boton, evento){
                  Ext.Ajax.request({
                  url:  '/prototipo/svc/conector/reportesPrecios.php/sillas',
                  method: 'POST',
                  params: {},
                  failure: function (response, options) {
                    Ext.Msg.show({ title:'Impresión', msg: 'Error de red o base de datos', buttons: Ext.Msg.OK});
                  },
                  success: function (response, options) {
                      var html=response.responseText;
                      var win=window.open('', 'Planilla de producción', "dependent=true, height = 800, width = 520, resizable = yes, scrollbars = yes, menubar=yes");
                      win.document.write(html);
                      win.document.close(); 
                  }
                });
              }
           }
          
      }
          ]
	});
	quitaDePanel();
	muestraEnPanel(panelReporte);
}