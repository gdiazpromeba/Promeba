PanelFormDetalleAbm=Ext.extend(PanelFormCabeceraAbm, {
	store:null,
	grid:null,
    initComponent:function() {
//	    var config = {
//          items: [{
//    	    name: 'idPadre',
//    	    id: 'idPadre',
//    	    xtype: 'hidden'
//          }],
//	    };
//	    
//        Ext.apply(this, config);
//        Ext.apply(this.initialConfig, config);
        PanelFormDetalleAbm.superclass.initComponent.apply(this, arguments);
     },
     
     borrado: function(idABorrar){
         var reg = this.grid.getSelectionModel().getSelected();
         if (!reg) {
             return false;
         }
         this.store.remove(reg);
         this.exitoBorrado();
     },    	 

	   
     modificacion: function(){
         var reg = this.grid.getSelectionModel().getSelected();
         if (!reg) {
             return false;
         }
         this.cargaFormEnRegistro(reg); 
         this.exitoModificacion();
     },
	   
     agregado: function(){
    	 registro=this.creaRegistroDeForm(this.store);
         this.store.add(registro);
         this.exitoAgregado();
     },
     
     setPadreModificando: function(valor){
    	 this.padreModificando=valor;
     },
     
     

     
    

  }
);
