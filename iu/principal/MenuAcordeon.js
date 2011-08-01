/**
 * Clase que contiene a todos los PanelMenu.
 * Es capaz de recibir información sobre qué paneles de menú mostrar y cuáles no
 * @class PanelMenu
 * @extends Ext.grid.GridPanel
 */
MenuAcordeon = Ext.extend(Ext.Panel, {

	constructor : function(config) {
      MenuAcordeon.superclass.constructor.call(this, Ext.apply({    
        id: 'menuAcordeonId',
        name: 'menuAcordeonId',
        region: 'west',
	    layout: 'accordion',
	    width: 230
      }, config));
    }, //constructor
  
    /**
     * hace visibles sólo algunos de los paneles, de acuerdo con la información
     * recibida al validar el usuario
     */
    evaluaOcultamiento: function(arrArFun) {
      //en debug, todo se ve
      if (debug){
        return;
      }
    	
      //primero oculta todos
      for (var i=0; i<this.items.length; i++){
          var panelMenu=this.items.items[i];
          panelMenu.setVisible(false);
      }
      //luego hace visibles algunos, selectivamente
      for (var i=0; i< arrArFun.length; i++){
          var fila=arrArFun[i];
    	  this.ocultaPanel(fila.areaNombre);
      }
    },
    
    ocultaPanel : function (etiquetaPanel){
      for (var i=0; i<this.items.length; i++){
        var panelMenu=this.items.items[i];
        if (panelMenu.title==etiquetaPanel){
        	panelMenu.setVisible(true);
        	return;
        }
      }
    }
  
});