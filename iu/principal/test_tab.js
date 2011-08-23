Ext.onReady( function() {

   var viewport = new Ext.Viewport({
		layout:'border',
		frame : true,
		title: 'Hola',
		items:[
		 {xtype: 'toolbar', height: 25, region: 'north',
		   items:[
		     {xtype: 'button', 		       
		       menu: {xtype: 'menu',
                  items: [
                      {xtype: 'menuitem', text: 'Bandeja de Entrada'},
                      {xtype: 'menuitem', text: 'Gestion de Proyectos', 
                        handler: function(){
                          var tabs=Ext.getCmp('tabs');
                          tabs.add(new Ext.Panel({title: this.text , closable: true }));
                        }
                      },
                      {xtype: 'menuitem', text: 'Gestion de Solicitudes' }
                  ]
                } 
		     }
		   ]
		 },
		 {xtype: 'tabpanel', itemId: 'tabs', activeItem: 0, region: 'center', 
		   deferredRender : false, height: 530, width: 500, id: 'tabs',
		   items: [
		     {xtype: 'panel', title: 'Principal', closable: false},
		     {xtype: 'panel', title: 'Otro', closable: true}
		   ]
		 }
		]
	});


});