Dinero = Ext.extend(Ext.form.NumberField, {

    constructor : function(config) {
      Dinero.superclass.constructor.call(this, Ext.apply({
        xtype: 'numberfield',
        allowDecimals: true,
        decimalPrecision: 2
    }, config));
  }, //constructor
  
  setValue : function(v){
      v = typeof v == 'number' ? v : String(v).replace(this.decimalSeparator, ".");
      v = isNaN(v) ? '' : String(v).replace(".", this.decimalSeparator);
//      alert('v es =' + v);
      //  if you want to ensure that the values being set on the field is also forced to the required number of decimal places.
      // (not extensively tested)
      v = isNaN(v) ? '' : this.fixPrecision(String(v).replace(".", this.decimalSeparator));
      return Ext.form.NumberField.superclass.setValue.call(this, v);
  },
  
  fixPrecision : function(value){
//	  alert('arreglando ...');
      var nan = isNaN(value);
      if(!this.allowDecimals || this.decimalPrecision == -1 || nan || !value){
         return nan ? '' : value;
      }
      return parseFloat(value).toFixed(this.decimalPrecision);
  }
  
  
  
});

Ext.reg('dinero', Dinero);