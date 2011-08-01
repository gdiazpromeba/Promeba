var dsDepartamentos = new Ext.data.JsonStore({
    url: '/prototipo/svc/conector/geografia/seleccionaDepartamentos',
    root: 'data',
    fields: ['id', 'nombre']
});


