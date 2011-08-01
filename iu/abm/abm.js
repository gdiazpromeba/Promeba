function abmIza(grillaCabecera, formCabecera, panelSearch) {
	
	var modeloCabecera = grillaCabecera.getStore();
	var paginacion = grillaCabecera.getBottomToolbar();
	var storeCabecera=grillaCabecera.getStore();

	//storeCabecera.load({params:{start:0, limit:15}});
	
	
	/**
	 * le carga al store los parámetros de paginación, 
	 * más cualquier cosa nueva que provea el formulario
	 * de búsqueda
	 */
	function cargaParametros(store) {
		var paginacion = grillaCabecera.getBottomToolbar();
		store.baseParams = {};
		store.setBaseParam('start', paginacion.cursor);
		store.setBaseParam('limit', paginacion.pageSize);
		//parámetros adicionales
		cargaParametrosAdicionales(store, panelSearch);
		var arrAdicionales = panelSearch.getParamsBusqueda();
		for (i = 0; i < arrAdicionales.length; i++) {
			store.setBaseParam(arrAdicionales[i]['nombre'], arrAdicionales[i]['valor']);
		}
	};
	
	cargaParametrosAdicionales = function(store, panelSearch){
		var arrAdicionales = panelSearch.getParamsBusqueda();
		for (i = 0; i < arrAdicionales.length; i++) {
			store.setBaseParam([arrAdicionales[i]['nombre']], arrAdicionales[i]['valor']);
		}
	};

	formCabecera.on('datos del formulario cabecera cambiaron',
			function() {
				cargaStore(grillaCabecera);
    });
	
	formCabecera.on('exitoAgregado',function(idNuevo) {
		var store=grillaCabecera.getStore();
	    var registro=new store.recordType();
	    formCabecera.pueblaFormEnRegistro(registro); //todo menos el id
	    registro.id=idNuevo;
	    store.insert(0, registro);
      grillaCabecera.getSelectionModel().selectRow(0);
	  }
	);	

	
	grillaCabecera.getSelectionModel().on(
			'rowselect',
			function(sm) {
				formCabecera.reaccionaACambioSelGrilla(sm, storeCabecera);
	});	
  
  
	formCabecera.on('cambio en agregando/modificando',
			function(agregando, modificando) {
				if (agregando || modificando){
				  grillaCabecera.disable();
				  panelSearch.disable();
				}else{
				  grillaCabecera.enable();
				  panelSearch.enable();
				}
	});
	
	
	function reaccionaAClickBuscar(){
		storeCabecera.setBaseParam('start', 0);
		storeCabecera.setBaseParam('limit', paginacion.pageSize);		
		cargaParametrosAdicionales(storeCabecera, panelSearch);
		storeCabecera.load();
	}
	
	panelSearch.getBotonBuscar().on('click', function() {
		reaccionaAClickBuscar();
	});

	/**
	 * el reset debe hacerse aquí, externamente, no internamente en el form,
	 * pues los eventos ocurren casi simultáneamente y el form no llega a vaciarse
	 */
	panelSearch.getBotonReinicializar().on('click', function() {
		panelSearch.reinicializar();
		panelSearch.borraOcultos();
		storeCabecera.setBaseParam('start', paginacion.cursor);
		storeCabecera.setBaseParam('limit', paginacion.pageSize);		
//		storeCabecera.lastOptions.params.start=0;
//		storeCabecera.lastOptions.params.limit=paginacion.pageSize;
		cargaParametrosAdicionales(storeCabecera, panelSearch);
		storeCabecera.load();
	});

	function cargaStore(grilla) {
		var store = grilla.getStore();
		cargaParametros(store);
		store.load();
	};	
	

	

	
}