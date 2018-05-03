/**
 *  While document is loaded, we start definig events
 */
var data = new Array();

/*  Definition of the JSON data stucture for input parameters  */
/*  Only for test purposes!!                                   */
var inputDS = {"description": "Addition & Substraction", "inputdata": [  
      {  
         "varname": "a",
         "vartype": "number",
         "vardesc": "Mutiplyer A",
         "defaultval": 1
      },
      {  
         "varname": "A",
         "vartype": "matrix",
         "vardesc": "Matrix A",
         "defaultval": 2
      },
      {  
         "varname": "b",
         "vartype": "number",
         "vardesc": "Multiplyer B",
         "defaultval": 1
      },
      {  
         "varname": "B",
         "vartype": "matrix",
         "vardesc": "Matrix B",
         "defaultval": 4
      }
   ]
};

var outputDS = {"Vartype":"matrix","vardesc": "Matrix B","Value":[["5","5","5"],["5","5","5"],["5","5","5"]]};

$(document).ready(function () {
		var i = 0;
		var board = $('.input-data');
		var elem = document.getElementsByClassName('menuItem'), i = elem.length;
		/* Event on selection menu item */
		$('#operation-def').html('loaded');
		console.log('loaded');
		
		while(i--) {
			elem[i].addEventListener("click", clickHandler);
		}
		//
		getInfo(inputDS);	

		for(var i = 0; i < data.length; i++) {
			if(data[i] instanceof matrixds) {
				$('#' + data[i].XSelect[0].id).on('change', {name: data[i].varname}, function(event) {
					var data = event.data;
					var itemSelected = $(this).val();
					var colCount = $('.' + event.data.name).find("tr:first td").length;
					if (itemSelected > colCount) {
						var tablerow;
						var dm = itemSelected - colCount;
						$('.' + event.data.name + ' tr').each(function(){
							tablerow = "";
							for (var j = 0; j < dm; j++) {
								tablerow += "<td><input type='text' name='0101' value='" + 0 + "' id='minput'></td>";
							}
							$(this).append(tablerow);
						});
					} else if (itemSelected < colCount) {
						var dm = colCount - itemSelected;
						$('.' + event.data.name + ' tr').each(function(){
							for (var j = 0; j < dm; j++) {
								$(this).find('td:last').remove();
							}
						});
					};
				});

				$('#' + data[i].YSelect[0].id).on('change', {name: data[i].varname}, function(event) {
					var data = event.data;
					var itemSelected = $(this).val();
					var rowsCount = $('.' + event.data.name).find("tr").length;
					if (itemSelected > rowsCount) {
						var dn = itemSelected - rowsCount;
						var tablerow;
						var m = $('.' + event.data.name).find("tr:first td").length;
						for (var i = 0; i < dn; i++) {
							var lastrow = $('<tr/>').appendTo($('.' + event.data.name).find('tbody:last'));
							tablerow = "";
							for (var j = 0; j < m; j++) {
								tablerow += "<td><input type='text' name='0101' value='" + 0 + "' id='minput'></td>";
							}
							$(tablerow).appendTo(lastrow);
						}
					} else if (itemSelected < rowsCount) {
						var dn = rowsCount - itemSelected;
						for (var i = 0; i < dn; i++) {
							$('.' + event.data.name).find('tr:last').remove();
						}
					};
				});
			}
		}
		
		$('#GR').on('click', function() {
			var resData  = outputDS;
			//clearing result panel
			$('#result').empty();
			if(resData.Vartype == "number") {
				var nd = new numberds($('#result'), resData.varname, resData.vardesc, resData.Value, true);
				dn.displayinput();
			}
			if(resData.Vartype == "matrix") {
				var md = new matrixds(null, $('#result'), resData.varname, resData.vardesc, resData.Value, true);
				md.displayinput(resData.Value);
			}
			$('#result').append('<div class="clearfix"></div>');
		});
		
		
		
	});
	
	var article = new Object();
	
	/* get selected menuItem */
	$('.menuItem').on('click', function(e) {
		var operation_id = $(this).prop('id');
		
	});

/* event handler for menu click */
var menuClickHandler = function(e) {

}

var clickHandler = function(e) {
	/* Assign selection to variable */
	var operation_id = $(this).prop('id');
	/* POST query */
	var url = '/CalcMatrix';
	console.log(operation_id);
	$.post(
		url,
		"operation_id=" + operation_id,
		function(result) {
			if (result.type == 'error') {
				alert ('error!!');
				return(false);
			}
			else {
				$('#operation-def').html(operation_id);
				
			}
		},
		"json"
	);
}

/**/
var initMatrix = function(n, m, initval) {
	var matrix = new Array();
	for (var i = 0; i < n; i++) {
		matrix[i] = new Array();
		for(var j = 0; j < m; j++) {
			matrix[i][j] = initval;
		}
	}
	return matrix;
}

/**/
var plotMatrixTB = function(matrix, board, readonly) {
	//insert TextBoxes into the table
	var tablerow;
	for (var i = 0; i < matrix.length; i++) {
		//i-th row
		tablerow = "<tr>"
		for(var j = 0; j < matrix[0].length; j++) {
			//j-th column
			if(!readonly) {
				tablerow += "<td><input type='text' name='0101' value='" + matrix[i][j] + "' id='minput'></td>";
			} else {
				tablerow += "<td>" + matrix[i][j] + "</td>";
			}
		}
		tablerow += "</tr>";
		$(tablerow).appendTo(board);
	}
}

/*Reading information about data to be requested */
var getInfo = function(dataset) {
	var resData = dataset;
	//Clearing def panel
	$('.operation-def').empty();
	//Add Header
	var div1 = $('.operation-def').append('<div class = "operation-header">');
	$('.operation-def').append('<h1>' + resData.description + '</h1></div>');
	//Clear input data panel
	$('#userinput').empty();
	$('#result').empty();
	// iterate throu variables
	for(var i = 0; i < resData.inputdata.length; i++) {
		if(resData.inputdata[i].vartype == "number") {
			var nd = new numberds($('#userinput'), resData.inputdata[i].varname, resData.inputdata[i].vardesc, resData.inputdata[i].defaultval, false);
			nd.displayinput();
			data.push(nd);
		} 
		if(resData.inputdata[i].vartype == "matrix") {
			var md = new matrixds($('.operation-def'), $('#userinput'), resData.inputdata[i].varname, resData.inputdata[i].vardesc, resData.inputdata[i].defaultval, false);
			md.displaydims();
			md.displayinput(initMatrix(3, 3, resData.inputdata[i].defaultval));
			data.push(md);
		}
	}
	$('.operation-def').append('<div class="clearfix"></div>');
	$('#userinput').append('<div class="clearfix"></div>');
}

class numberds {
	constructor(inputboard, varname, vardesc, def, readonly) {
		this.inputboard = inputboard;
		this.varname = varname;
		this.vardesc = vardesc;
		this.def = def;
		this.readonly = readonly;
	}
	displayinput() {
		var div1 = $('<div class = "column">').appendTo(this.inputboard);
		var outputstring = this.vardesc + "<br>";
		div1.append(outputstring);
		if(!this.readonly) {
			$("<input type = 'text' id = '" + this.varname + "' value = '" +  this.def + "' size = '4' >").appendTo(div1);
		} else {
			$(this.def).appendTo(div1);
		}
		
	}
	value() {
		var retval = new Array();
		var cols = new Array();
		cols.push($('#' + this.varname).val());
		retval.push(cols);
		return retval;
	}
}

class matrixds {
	constructor (defboard, inputboard, varname, vardesc, defaultval, XSelect, YSelect,readonly) {
		this.defboard = defboard;
		this.inputboard = inputboard;
		this.varname = varname;
		this.vardesc = vardesc;
		this.XSelect = XSelect;
		this.YSelect = YSelect;
		this.readonly = readonly;
	}

	//displaying matrix dimention input
	displaydims() {
		if(this.defboard != null) {
			var div1 = $('<div class = "column">').appendTo(this.defboard);
			div1.append(this.varname + ' Columns<br>');
			this.XSelect = $('<select id="'+ this.varname + 'colcount_id">').appendTo(div1);
			//Select for Columns count of matrix 
			var arr = [1, 2, 3, 4, 5];
			var XSelect = this.XSelect;
			$.each(arr, function(i, item) {
				 XSelect.append($('<option value = "' + arr[i] + '">' + arr[i] + '</option>'));
			});
			this.XSelect = XSelect;
			div1.append('<br>');
			div1.append('<br>');
			div1.append(this.varname + ' Rows<br>');
			this.YSelect = $('<select id="' + this.varname + 'rowscount_id">').appendTo(div1);
			var YSelect = this.YSelect;
			$.each(arr, function(i, item) {
				 YSelect.append($('<option value = "' + arr[i] + '">' + arr[i] + '</option>')); 
			});
			this.YSelect = YSelect;
		}
	}

	//dispaying data input table
	displayinput(value) {
		var div1 = $('<div class = "column">').appendTo(this.inputboard);
		//add table caption
		var table = $("<table class = '" + this.varname + "'>").appendTo(div1);
		$('<caption><b>' + this.vardesc + '</b></caption>').appendTo(table);
		//insert inputs into the table
		plotMatrixTB(value, table, this.readonly);
	}

	//function for getting data, entered by user
	value() {
		var matrix = new Array();
		$('.' + this.varname + ' tr').each(function() {
			var cols = new Array();
			$(this).find('td').each(function() {
				cols.push($(this).children('#minput').val());
			});
			matrix.push(cols);
		});
		return matrix;
	}
}