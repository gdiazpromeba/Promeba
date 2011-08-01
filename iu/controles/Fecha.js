Fecha = Ext.extend(Ext.form.DateField, {

    constructor : function(config) {
      Fecha.superclass.constructor.call(this, Ext.apply({
        width: 100, 
        xtype: 'datefield',
        format: 'd/m/Y',
        listeners: {
            scope: this,
            'render' : function(campo){
            if (this.muestraHoy){
              var dt = new Date();
              campo.setValue(dt);
            }
          }
        }
    }, config));
  } //constructor
  
});

Ext.reg('fecha', Fecha);