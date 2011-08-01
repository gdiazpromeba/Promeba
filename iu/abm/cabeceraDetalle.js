/**
 * controla la interacción entre las grillas de un par cabecera-detalle
 * @param grillaDetalle
 * @param formCabecera
 * @param panelSearch
 * @return
 */
function cabeceraDetalle(grillaDetalle, formDetalle, grillaCabecera) {
  
  
  formDetalle.on('datos del formulario cabecera cambiaron',
      function() {
        var paginacion = grillaDetalle.getBottomToolbar();
        storeDetalle=grillaDetalle.getStore();
        storeDetalle.baseParams = {};
        storeDetalle.setBaseParam('start', paginacion.cursor);
        storeDetalle.setBaseParam('limit', paginacion.pageSize);
        storeDetalle.setBaseParam('valorIdPadre', formDetalle.getComponent('valorIdPadre').getValue());
        storeDetalle.reload();
    });
  
  formDetalle.on('exitoAgregado',
      function(idNuevo) {
        var modeloDetalle=grillaDetalle.getStore();
        var registro=new modeloDetalle.recordType();
        formDetalle.pueblaFormEnRegistro(registro); //todo menos el id
        registro.id=idNuevo;
        modeloDetalle.insert(0, registro);
        grillaDetalle.getSelectionModel().selectRow(0);
        formDetalle.setId(idNuevo);
      }
  );  
  
  grillaDetalle.getSelectionModel().on('rowselect',
      function(sm) {
        var modeloDetalle=grillaDetalle.getStore(); 
        formDetalle.reaccionaACambioSelGrilla(sm, modeloDetalle);

  }); 
  
  formDetalle.on('cambio en agregando/modificando',
      function(agregando, modificando) {
        if (agregando || modificando){
          grillaDetalle.disable();
        }else{
          grillaDetalle.enable();
        }
  });
  

  
  /**
   * le comunica el último id cabecera seleccionado a la grilla de detalle
   */
  grillaCabecera.getSelectionModel().on(
      'rowselect',
      function(sm, indice, registro) {
        var storeDetalle=grillaDetalle.getStore();
        formDetalle.setValorIdPadre(registro.id);
        formDetalle.setTitle(grillaCabecera.tituloHijo());
        var paginacion = grillaDetalle.getBottomToolbar();
        storeDetalle.setBaseParam('valorIdPadre', registro.id);
        storeDetalle.setBaseParam('start', paginacion.cursor);
        storeDetalle.setBaseParam('limit', paginacion.pageSize);        
        storeDetalle.load();
  });   
  

  

  
}