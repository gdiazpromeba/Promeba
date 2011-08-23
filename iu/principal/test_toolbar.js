Ext.onReady( function() {

   var viewport = new Ext.Viewport({
		layout:'border',
		frame : true,
		title: 'Hola',
		items:[
	     {xtype: 'toolbar', region: 'north', height: '25',
                items: [
                    { xtype: 'tbbutton', text: 'BOTON',
                        menu: {
                            xtype: 'menu',
                            items: [
                                {xtype: 'menuitem', text: 'Bandeja de Entrada'},
                                {xtype: 'menuitem', text: 'Gestion de Proyectos' },
                                {xtype: 'menuitem', text: 'Gestion de Solicitudes' }
                            ]
                        }
                    },
                    {text: 'Toggle Me',  
                      handler: function(){
					    var panCentral=Ext.getCmp('panelCentral');
					    panCentral.removeAll();
					    var nuevoPanel=new Ext.Panel({title: 'agregado', frame: true, 
					      layout: 'form', items: [
					        {xtype: 'checkbox', fieldLabel: 'Visión', checked: 'true', name: 'vision', itemId: 'vision'}
					    ]});
				        panCentral.add(nuevoPanel);
				        panCentral.doLayout();
				       }
				   },
                   {text: 'Otro',  
                      handler: function(){
					    var panCentral=Ext.getCmp('panelCentral');
					    panCentral.removeAll();
					    var nuevoPanel=new Ext.Panel({title: 'agregado', frame: true, 
					      layout: 'form', items: [
					        {xtype: 'textfield', fieldLabel: 'Hola', width: 100}
					    ]});
				        panCentral.add(nuevoPanel);
				        panCentral.doLayout();
				       }
				   }				   
                ]
            },
		  {xtype: 'panel', id: 'panelCentral',  frame: true, region: 'center'},
		]
	});


});