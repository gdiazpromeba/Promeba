ComboServiciosPublicosDisponibles = Ext.extend(ComboFijo, {

    constructor : function(config) {
      ComboServiciosPublicosDisponibles.superclass.constructor.call(this, Ext.apply({
        pageSize: 0,
        fieldLabel: 'Equip. Social',
        width: 280, 
        store: new Ext.data.JsonStore({
          url: '/prototipo/svc/conector/auxSolicitudes/seleccionaServiciosPublicosDisponibles',
          root: 'data',
          fields: ['id', 'nombre']
        }), 
        displayField: 'nombre', 
        valueField: 'id',
        minListWidth: 150, 
        tpl: new Ext.XTemplate( '<tpl for="."><div class="search-item">', "<h4>{nombre}</h4>", '</div></tpl>')
    }, config));
  } //constructor
  
});

Ext.reg('comboserviciospublicosdisponibles', ComboServiciosPublicosDisponibles);