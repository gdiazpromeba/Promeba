/**
 * Clase utilitaria (con métodos estáticos) para manipular objetos Date de Javascript
 * @return
 */
function FechaUtils () {
}

FechaUtils.extraeCadenaHora = function(objetoDate){
	var horas=objetoDate.getHours();
	horas=FechaUtils.rellenaCeros(horas, 2);
	var minutos=objetoDate.getMinutes();
	minutos=FechaUtils.rellenaCeros(minutos, 2);
	return horas + ':' + minutos;
}

FechaUtils.extraeCadenaFecha = function(objetoDate){
	var dias=objetoDate.getDate();
	dias=FechaUtils.rellenaCeros(dias, 2);
	var meses=objetoDate.getMonth() + 1; //empieza de 0, hasta 11
	meses=FechaUtils.rellenaCeros(meses, 2);
	var año=objetoDate.getFullYear();
	return dias + '/' + meses + '/' + año;
}

/**
 * entran un objeto Date de Javascript y una  '14:50', sale un objeto Date de Javascript (nuevo), con la hora/minutos correspondientes 
 */
FechaUtils.objDateCadenaHmAObjeto = function(dma, horaMinuto){
	var arrHm=horaMinuto.split(':');
	var hora=arrHm[0]; 
	var minuto=arrHm[1];
	var res=new Date(dma.getFullYear(), dma.getMonth(), dma.getDate(), hora, minuto, 0, 0);
	return res;
}

FechaUtils.rellenaCeros = function(n, totalDigits){ 
    n = n.toString(); 
    var pd = ''; 
    if (totalDigits > n.length){ 
        for (i=0; i < (totalDigits-n.length); i++){ 
            pd += '0'; 
        } 
    } 
    return pd + n.toString(); 
} 