    var myData = [
        ['Ufi-Technic','28/12/2009',387,6,'5802 Sillón Suraki alto (CIE)',''],
        ['Ufi-Technic','28/12/2009',387,3,'8800 Estructura para sillón Aillites bajo (CIE)',''],
        ['Ufi-Technic','28/12/2009',387,3,'8800 Estructura para sillón Aillites alto (CIE)','']
//        [6,'5802 Sillón "Suraki" alto (CIE)',''],
//        [3,'8800 Estructura para sillón "Aillites" bajo (CIE)',''],
//        [3,'8800 Estructura para sillón "Aillites" alto (CIE)','']        
        
    ];


var store = new Ext.data.SimpleStore({
    fields: [
       {name: 'cliente', type: 'string'},
       {name: 'fecha', type: 'date'},
       {name: 'interno', type: 'date'},
       {name: 'cantidad', type: 'int'},
       {name: 'pieza', type: 'string'},
       {name: 'observaciones', type: 'string'}
    ]
});
store.loadData(myData);
    
    
    
var Base64 = (function() {

    // private property
    var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // private method for UTF-8 encoding
    function utf8Encode(string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            }
            else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }
            else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
        }
        return utftext;
    }

    // public method for encoding
    return {
        encode : (typeof btoa == 'function') ? function(input) { return btoa(input); } : function (input) {
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;
            input = utf8Encode(input);
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output = output +
                keyStr.charAt(enc1) + keyStr.charAt(enc2) +
                keyStr.charAt(enc3) + keyStr.charAt(enc4);
            }
            return output;
        }
    };
})();

Ext.util.Format.escapeXml = function(str) {
  function replaceChars(character) {
    switch (character) {
      case '<': return '&lt;';
      case '>': return '&gt;';
      case '&': return '&amp;';
      case "'": return '&apos;';
      case '"': return '&quot;';
      case 'á': return '&aacute;';
      case 'é': return '&eacute;';
      case 'í': return '&iacute;';
      case 'ó': return '&oacute;';
      case 'ú': return '&uacute;';
      case 'Á': return '&#193';
      case 'É': return '&#201;';
      case 'Í': return '&#205;';
      case 'Ó': return '&#211;';
      case 'Ú': return '&#218;';
      default: return '';
    };      
  };
  return String(str).replace(/[<>&"'áéíóúÁÉÍÓÚ]/g, replaceChars);
};


Ext.LinkButton = Ext.extend(Ext.Button, {
    template: new Ext.Template(
        '<table border="0" cellpadding="0" cellspacing="0" class="x-btn-wrap"><tbody><tr>',
        '<td class="x-btn-left"><i> </i></td><td class="x-btn-center"><a class="x-btn-text" href="{1}" target="{2}">{0}</a></td><td class="x-btn-right"><i> </i></td>',
        "</tr></tbody></table>"),
    
    onRender:   function(ct, position){
        var btn, targs = [this.text || ' ', this.href, this.target || "_self"];
        if(position){
            btn = this.template.insertBefore(position, targs, true);
        }else{
            btn = this.template.append(ct, targs, true);
        }
        var btnEl = btn.child("a:first");
        btnEl.on('focus', this.onFocus, this);
        btnEl.on('blur', this.onBlur, this);

        this.initButtonEl(btn, btnEl);
        Ext.ButtonToggleMgr.register(this);
    },

    onClick : function(e){
        if(e.button != 0){
            return;
        }
        if(!this.disabled){
            this.fireEvent("click", this, e);
            if(this.handler){
                this.handler.call(this.scope || this, this, e);
            }
        }
    }

});

Ext.override(Ext.grid.GridPanel, {
    getExcelXml: function(includeHidden) {
        var worksheet = this.createWorksheet(includeHidden);
        var totalWidth = this.getColumnModel().getTotalWidth(includeHidden);
        var titulo='ALMAR MULTILAMINADOS';
        return '<?xml version="1.0" encoding="utf-8"?>' +
            '<ss:Workbook xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns:o="urn:schemas-microsoft-com:office:office">' +
            '<o:DocumentProperties><o:Title>' + titulo + '</o:Title></o:DocumentProperties>' +
            '<ss:ExcelWorkbook>' +
//                '<ss:WindowHeight>' + worksheet.height + '</ss:WindowHeight>' +
//                '<ss:WindowWidth>' + worksheet.width + '</ss:WindowWidth>' +
                '<ss:ProtectStructure>False</ss:ProtectStructure>' +
                '<ss:ProtectWindows>False</ss:ProtectWindows>' +
            '</ss:ExcelWorkbook>' +
            '<ss:Styles>' +
                '<ss:Style ss:ID="Default">' +
                    '<ss:Alignment ss:Vertical="Top" ss:WrapText="1" />' +
                    '<ss:Font ss:FontName="arial" ss:Size="10" />' +
                    '<ss:Borders>' +
                        '<ss:Border ss:Color="#e4e4e4" ss:Weight="1" ss:LineStyle="Continuous" ss:Position="Top" />' +
                        '<ss:Border ss:Color="#e4e4e4" ss:Weight="1" ss:LineStyle="Continuous" ss:Position="Bottom" />' +
                        '<ss:Border ss:Color="#e4e4e4" ss:Weight="1" ss:LineStyle="Continuous" ss:Position="Left" />' +
                        '<ss:Border ss:Color="#e4e4e4" ss:Weight="1" ss:LineStyle="Continuous" ss:Position="Right" />' +
                    '</ss:Borders>' +
                    '<ss:Interior />' +
                    '<ss:NumberFormat />' +
                    '<ss:Protection />' +
                '</ss:Style>' +
                '<ss:Style ss:ID="title">' +
                    '<ss:Borders />' +
                    '<ss:Font />' +
                    '<ss:Alignment ss:WrapText="1" ss:Vertical="Center" ss:Horizontal="Center" />' +
                    '<ss:NumberFormat ss:Format="@" />' +
                '</ss:Style>' +
                '<ss:Style ss:ID="headercell">' +
                    '<ss:Font ss:Bold="1" ss:Size="10" />' +
                    '<ss:Alignment ss:WrapText="1" ss:Horizontal="Center" />' +
                    '<ss:Interior ss:Pattern="Solid" ss:Color="#A3C9F1" />' +
                '</ss:Style>' +
                '<ss:Style ss:ID="even">' +
                    '<ss:Interior ss:Pattern="Solid" ss:Color="#CCFFFF" />' +
                '</ss:Style>' +
                '<ss:Style ss:Parent="even" ss:ID="evendate">' +
                    '<ss:NumberFormat ss:Format="[ENG][$-409]dd\-mmm\-yyyy;@" />' +
                '</ss:Style>' +
                '<ss:Style ss:Parent="even" ss:ID="evenint">' +
                    '<ss:NumberFormat ss:Format="0" />' +
                '</ss:Style>' +
                '<ss:Style ss:Parent="even" ss:ID="evenfloat">' +
                    '<ss:NumberFormat ss:Format="0.00" />' +
                '</ss:Style>' +
                '<ss:Style ss:ID="odd">' +
                    '<ss:Interior ss:Pattern="Solid" ss:Color="#CCCCFF" />' +
                '</ss:Style>' +
                '<ss:Style ss:Parent="odd" ss:ID="odddate">' +
                    '<ss:NumberFormat ss:Format="[ENG][$-409]dd\-mmm\-yyyy;@" />' +
                '</ss:Style>' +
                '<ss:Style ss:Parent="odd" ss:ID="oddint">' +
                    '<ss:NumberFormat ss:Format="0" />' +
                '</ss:Style>' +
                '<ss:Style ss:Parent="odd" ss:ID="oddfloat">' +
                    '<ss:NumberFormat ss:Format="0.00" />' +
                '</ss:Style>' +
            '</ss:Styles>' +
            worksheet.xml +
            '</ss:Workbook>';
    },

    createWorksheet: function(includeHidden) {

//      Calculate cell data types and extra class names which affect formatting
        var cellType = [];
        var cellTypeClass = [];
        var cm = this.getColumnModel();
        var totalWidthInPixels = 0;
        var colXml = '';
        var headerXml = '';
        colXml += '<ss:Column ss:AutoFitWidth="1" ss:Width="50" /><ss:Column ss:AutoFitWidth="1" ss:Width="300" /><ss:Column ss:AutoFitWidth="1" ss:Width="300" />';
        
        headerXml += '<ss:Cell ss:StyleID="headercell">' +
                    '<ss:Data ss:Type="String">Cantidad</ss:Data>' +
                    '<ss:NamedCell ss:Name="Print_Titles" /></ss:Cell>';
        headerXml += '<ss:Cell ss:StyleID="headercell">' +
                    '<ss:Data ss:Type="String">Art&iacute;culo</ss:Data>' +
                    '<ss:NamedCell ss:Name="Print_Titles" /></ss:Cell>';
        headerXml += '<ss:Cell ss:StyleID="headercell">' +
                    '<ss:Data ss:Type="String">Observaciones</ss:Data>' +
                    '<ss:NamedCell ss:Name="Print_Titles" /></ss:Cell>';
                    
                    
                    
   
        var visibleColumnCount = 3;

        var result = {
            height: 9000,
            width: Math.floor(totalWidthInPixels * 30) + 50
        };

        var cliente= store.data.items[0].data['cliente'];
        
        
        var tituloPlanilla='387';
        var otroTitulo='Otro título';
        var tercerTitulo='Tercer título';
        
//      Generate worksheet header details.
        var t = '<ss:Worksheet ss:Name="' + tituloPlanilla + '">' +
            '<ss:Names>' +
                '<ss:NamedRange ss:Name="Print_Titles" ss:RefersTo="=\'' + otroTitulo + '\'!R1:R2" />' +
            '</ss:Names>' +
            '<ss:Table x:FullRows="1" x:FullColumns="1"' +
                ' ss:ExpandedColumnCount="3" ss:ExpandedRowCount="' + (store.getCount() + 3) + '">' +
                '<ss:Column ss:AutoFitWidth="1" ss:Width="50" />' +
                '<ss:Column ss:AutoFitWidth="1" ss:Width="300" />' +
                '<ss:Column ss:AutoFitWidth="1" ss:Width="300" />' +
                //almar multilaminados
                '<ss:Row ss:Height="38">' +
                    '<ss:Cell ss:StyleID="title" ss:MergeAcross="2">' +
                      '<ss:Data xmlns:html="http://www.w3.org/TR/REC-html40" ss:Type="String">' +
                        '<html:B><html:U><html:Font html:Size="15">' + 'ALMAR MULTILAMINADOS' +
                        '</html:Font></html:U></html:B>' +
                        '</ss:Data><ss:NamedCell ss:Name="Print_Titles" />' +
                    '</ss:Cell>' +
                '</ss:Row>' +
                //Cliente
                '<ss:Row ss:Height="38">' +
                    '<ss:Cell ss:StyleID="title">' +
                      '<ss:Data xmlns:html="http://www.w3.org/TR/REC-html40" ss:Type="String">' +
                        '<html:B><html:Font html:Size="15">Cliente : </html:Font></html:B>' +
                      '</ss:Data><ss:NamedCell ss:Name="Print_Titles" />' +
                    '</ss:Cell>' +
                    '<ss:Cell ss:StyleID="title" ss:MergeAcross="1">' +
                      '<ss:Data xmlns:html="http://www.w3.org/TR/REC-html40" ss:Type="String">' +
                        '<html:B><html:U><html:Font html:Size="15">' + cliente +
                        '</html:Font></html:U></html:B>' +
                        '</ss:Data><ss:NamedCell ss:Name="Print_Titles" />' +
                    '</ss:Cell>' +
                    
                '</ss:Row>' +
                
                '<ss:Row ss:AutoFitHeight="1">' +
                headerXml + 
                '</ss:Row>';

//      Generate the data rows from the data in the Store
        for (var i = 0, it = store.data.items, l = it.length; i < l; i++) {
            t += '<ss:Row>';
            var cellClass = (i & 1) ? 'odd' : 'even';
            r = it[i].data;
            var k = 0;
//            for (var j = 0; j < 3; j++) {
              var v = r['cantidad'];
              t += '<ss:Cell ss:StyleID="' + cellClass + "int" + '"><ss:Data ss:Type="Number">';
              t += Ext.util.Format.escapeXml(v);
              t +='</ss:Data></ss:Cell>';
              
              var v = r['pieza'];
              t += '<ss:Cell ss:StyleID="' + cellClass + "" + '" ss:MergeAcross="1"><ss:Data ss:Type="String">';
              t += Ext.util.Format.escapeXml(v);
              t +='</ss:Data></ss:Cell>';
              
              
              var v = r['observaciones'];
              t += '<ss:Cell ss:StyleID="' + cellClass + "" + '"><ss:Data ss:Type="String">';
              t += Ext.util.Format.escapeXml(v);
              t +='</ss:Data></ss:Cell>';
              
              
              
//              k++;
//            }
            t += '</ss:Row>';
        }

        result.xml = t + '</ss:Table>' +
            '<x:WorksheetOptions>' +
                '<x:PageSetup>' +
                    '<x:Layout x:CenterHorizontal="1" x:Orientation="Landscape" />' +
                    '<x:Footer x:Data="Page &amp;P of &amp;N" x:Margin="0.5" />' +
                    '<x:PageMargins x:Top="0.5" x:Right="0.5" x:Left="0.5" x:Bottom="0.8" />' +
                '</x:PageSetup>' +
                '<x:FitToPage />' +
                '<x:Print>' +
                    '<x:PrintErrors>Blank</x:PrintErrors>' +
                    '<x:FitWidth>1</x:FitWidth>' +
                    '<x:FitHeight>32767</x:FitHeight>' +
                    '<x:ValidPrinterInfo />' +
                    '<x:VerticalResolution>600</x:VerticalResolution>' +
                '</x:Print>' +
                '<x:Selected />' +
                '<x:DoNotDisplayGridlines />' +
                '<x:ProtectObjects>False</x:ProtectObjects>' +
                '<x:ProtectScenarios>False</x:ProtectScenarios>' +
            '</x:WorksheetOptions>' +
        '</ss:Worksheet>';
        return result;
    }
});

Ext.onReady(function(){
  
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());



    // example of custom renderer function
    function change(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '</span>';
        }
        return val;
    }

    // example of custom renderer function
    function pctChange(val){
        if(val > 0){
            return '<span style="color:green;">' + val + '%</span>';
        }else if(val < 0){
            return '<span style="color:red;">' + val + '%</span>';
        }
        return val;
    }




    var linkButton = new Ext.LinkButton({
        id: 'grid-excel-button',
        text: 'Excel'
    });

    // create the Grid
    var grid = new Ext.grid.GridPanel({
        id: 'static-grid',
        store: store,
        columns: [
//            {header: "Cliente", width: 160, sortable: true, dataIndex: 'cliente'},
//            {header: "Fecha",   width: 160, sortable: true, dataIndex: 'fecha'},
//            {header: "Pedido Nº", width: 75, dataIndex: 'interno'},
            {header: "Cantidad", width: 75, dataIndex: 'cantidad'},
            {id: "pieza", header: "Articulo", width: 150, dataIndex: 'pieza'},
            {header: "observaciones", width: 250, dataIndex: 'observaciones'}
        ],
        stripeRows: true,
        autoExpandColumn: 'pieza',
        height:350,
        width:600,
        title:'Array Grid',
        bbar: new Ext.Toolbar({
            buttons: [linkButton]
        })
    });

    grid.render('grid-example');
    linkButton.getEl().child('a', true).href = 'data:application/vnd.ms-excel;base64,' + // 'pedido 387';  
    Base64.encode(grid.getExcelXml());

    grid.getSelectionModel().selectFirstRow();
});