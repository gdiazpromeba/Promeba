Ext.state.Manager.setProvider(new Ext.state.CookieProvider({
	expires: new Date(new Date().getTime()+(1000*60*60*24*30)) //30 days
}));

/**

 * el panel del área de la derecha, que contiene las distintas pantallas operativas

 */

var panelCentral= new Ext.Container({
	id: 'panelCentral',
	name: 'panelCentral',
	region: 'center',
	layout: 'fit',
	margins: '5 5 5 0',
	cls:'empty',
	bodyStyle:'background:#f1f1f1'
});

var dirIconos='/prototipo/recursos/iconos/';

/**

 * variable que almacena el último panel mostrado en el panel central

 */

var idUltimoPanel=null;

function quitaDePanel() {
	if (idUltimoPanel!=null) {
		var up=panelCentral.findById(idUltimoPanel);
		if (up!=null) {
			panelCentral.doLayout();
			panelCentral.remove(idUltimoPanel, true);
		}
		idUltimoPanel=null;
	}
}

function muestraEnPanel(componente) {
	var el=componente.getEl();
	if (el!=null) {
		el.setVisible(true);
	}
	panelCentral.add(componente);
	panelCentral.doLayout();
	idUltimoPanel=componente.getId();
}

var itemsMenu=new Array();

Ext.onReady( function() {

	var panelSeguridad = new PanelMenu({
		title: 'Seguridad',
		store:  new Ext.data.SimpleStore({
			fields:['columna1', 'columna2'],
			data: [
			[['seguridad.png', 'Roles', creaPanRoles], ['lista_precios.png', 'Ár.func.x rol', creaPanAreasFuncionalesXRol]]
			]
		})
	});

	var panelConfiguracion = new PanelMenu({
		title: 'Configuración',
		store:  new Ext.data.SimpleStore({
			fields:['columna1', 'columna2'],
			data:  [
			  [['clientes.png', 'Usuarios', creaPanUsers], ['fichas.png', 'Áreas Funcionales', creaPanAreasFuncionales]],
			  [['pedidos.png', 'Subejecutores', creaPanSubejecutores], ['inventario.png', 'Áreas Geográficas', creaPanAreasGeograficas]],
			  [['proveedores.png', 'Cargos', creaPanCargos], ['cotizaciones.png', 'Mesas de Gestión', creaPanMesasGestion]],
			  [['usuarios.png', 'Pers. Físicas', creaPanPersonasFisicas], ['estadisticas.png', 'Pers. Jurídicas', creaPanPersonasJuridicas]],
			]
		})

	});

	var panelGestionDeCCLIP = new PanelMenu({
		title: 'Gestión de CCLIP'
	});

	var panelSolicitudes = new PanelMenu({
		title: 'Solicitudes',
		store:  new Ext.data.SimpleStore({
			fields:['columna1', 'columna2'],
			data:  [
			[['piezas.png', 'Solicitudes', creaPanSolicitudes], ['argentina.png', 'Selección', creaVentanaSolicitudes]]
		]
		})	
	});

    var panelOtraArea = new PanelMenu({
		title: 'Otra área'
	});

	var panelOtraAreaMas = new PanelMenu({
		title: 'Otra área más'
	});

	var acordeon=new MenuAcordeon({
		items:[panelSeguridad, panelConfiguracion, panelGestionDeCCLIP, panelSolicitudes, panelOtraArea, panelOtraAreaMas]
	});

	var viewport = new Ext.Viewport({
		layout:'border',
		items:[
		  acordeon,
		  panelCentral
		]
	});

	/**

	 * muestra la ventana de ingreso de usuario y clave

	 */

	acordeon.setVisible(false);

	var win=new VentanaLogin({
		acordeon: acordeon
	});

	win.show();

	var map = new Ext.KeyMap(win.id, [{
		key: [10,13],
		fn: function() {
			win.botAceptar.fireEvent('click');
		}
	}

	]);

});