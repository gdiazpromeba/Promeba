/**
 * Clase que muestra íconos en un panel grid, relacionándolos con una función al cliquear sobre ellos.
 * Si el menú principal se vuelve un acordeón, cada uno de sus paneles podría ser una clase de éstas.
 * @class PanelMenu
 * @extends Ext.grid.GridPanel
 */
PanelMenu = Ext.extend(Ext.grid.GridPanel, {
  constructor : function(config) {
    PanelMenu.superclass.constructor.call(this, Ext.apply({    
      frame: true,
      split: true,
      collapsible: true,
      store:  new Ext.data.SimpleStore({
        fields:['columna1', 'columna2'],
        data:  [
        ]
      }),
      trackMouseOver: false,
      listeners: {
            render: function(grid) {
              grid.getView().el.select('.x-grid3-header').setStyle('display', 'none');
            }    
      }, 
      columns: [{ dataIndex: 'columna1', renderer: this.muestraIcono }, { dataIndex: 'columna2' , renderer: this.muestraIcono }],
      sm: new Ext.grid.CellSelectionModel({
        listeners: {
          scope: this,
          beforecellselect: function(sm, row, col) {
            var f=this.store.data.items[row].json[col][2];
            f.call();
            return true; 
          }
        }
      }),
      viewConfig: {
          forceFit: true
      },
      deferRowRender: false
   
  
    }, config));
  }, //constructor
  
    /**TODO: averiguar por qué el estilo no se aplica, y tengo que escribirlo expresamente aquí
     * 
     * @param {} value
     * @param {} metaData
     * @param {} record
     * @param {} row
     * @param {} col
     * @param {} store
     * @return {}
     */  
    muestraIcono: function(value, metaData, record, row, col, store) {
        html="<div style='text-align:center' class='celdaMenuLateral'>";
        if (record.json[col]!=undefined){
          html+=" <img style='height:50px;display:block;margin-left:auto;margin-right:auto;' class='imagenMenuLateral' src='" + dirIconos + record.json[col][0] + "'/>";
          html+=" <div class='etiquetaMenuLateral'>" + record.json[col][1] +  "</div>";
        }
        html+="</div>";
        return html;
    }   
  
});