function creaVentanaSolicitudes(){

	var win=new Ext.Window({
		closable: true,
		plain: true,
		modal: true,
		height: 640,
		width: 1350,
		layout: 'fit',
		items: [
		  new PanelSolicitudes()
		]
	});	
	
	win.show();
}
