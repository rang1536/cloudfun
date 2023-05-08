var Utils = {};


/** form to json return */
Utils.getFormValue = function (formObj) {
    var arr = formObj.serializeArray();
    if (arr && arr.length > 0) {
        var values = {};
        for (var i in arr) {
            var obj = arr[i];
            values[obj.name] = obj.value
        }
        return values;
    }
    return null;
}

/** Form init */
Utils.resetForm = function (formObj) {
    formObj.find('input:text, input:password, input:file, select, textarea').val('');
    formObj.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
}

/** json to form*/
Utils.setFormValue = function (formObj, jsonData) {
    Utils.getFormValue(formObj);
    $.each(jsonData, function (key, value) {
        var ctrl = formObj.find('[name=' + key + ']');
        if (ctrl.is('select')) {
            $('option', ctrl).each(function () {
                if (this.value == value)
                    this.selected = true;
            });
        } else if (ctrl.is('textarea')) {
            ctrl.val(value);
        } else {
            switch (ctrl.attr("type")) {
                case "text":
                case "hidden":
                    ctrl.val(value);
                    break;
                case "checkbox":
                    if (value == 'on') {
                        ctrl.prop('checked', true);
                    } else {
                        ctrl.prop('checked', false);
                    }
                    break;
            }
        }
    });
}

/**async / csrf add*/
Utils.ajax = function (options, successcb, failcb) {
    var header = $("meta[name='_csrf_header']").attr("content")
    var token = $("meta[name='_csrf']").attr("content")
    $.ajax({
        type: options.type || 'post',
        url: options.url,
        data: options.data,
        beforeSend: function (xhr) {
			xhr.setRequestHeader(header, token)
        },
        success: function () {
            var data = arguments[0];

            successcb(data);
        },
        error: function () {
            var jsonError = arguments[0].responseJSON;
            if (jsonError.status && jsonError.code && jsonError.message && jsonError.detailMessage) {
                var msg = 'TODO !\r\n';
                msg += jsonError.status + '\r\n';
                msg += jsonError.code + '\r\n';
                msg += jsonError.message + '\r\n';
                msg += jsonError.detailMessage;
                alert(msg);
            }
            if (failcb) {
                failcb(arguments);
            }
        },
        complete: function () {
        }
    });
}

/**  async = false*/
Utils.ajaxSync = function (options, successcb, failcb) {


    var mid = Utils.getCookie('memberId');
    var type = Utils.getCookie('conType');

    $.ajax({
        type: options.type || 'post',
        url: options.url,
        data: options.data,
        headers: options.headers,
        async:false,
        beforeSend: function () {
        },
        success: function () {
            var data = arguments[0];

            successcb(data);
        },
        error: function () {
            var jsonError = arguments[0].responseJSON;
            if (jsonError.status && jsonError.code && jsonError.message && jsonError.detailMessage) {
                var msg = 'TODO comm error msg!\r\n';
                msg += jsonError.status + '\r\n';
                msg += jsonError.code + '\r\n';
                msg += jsonError.message + '\r\n';
                msg += jsonError.detailMessage;
                alert(msg);
            }
            if (failcb) {
                failcb(arguments);
            }
        },
        complete: function () {
        }
    });
}

Utils.setCookie = function (cname, cvalue, exdays) {
    var exdays = exdays || 1;
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

Utils.getCookie = function (cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

Utils.deleteCookie = function (cname) {
    Utils.setCookie(cname, "", -1);
}



function getContextPath() {
  return sessionStorage.getItem("contextpath");
}
var path = getContextPath();

/*********************************************************************************/
/**google Login  *********************************************************************/
/*********************************************************************************/
//google Login
function loginWithGoogle() {
    $.ajax({
        url: preUrl + '/api/v1/oauth2/google',
        type: 'post',
    }).done(function (res) {
        location.href = res;
    });
}


function logout(){
	$.ajax({
        url: preUrl + '/api/logout',
        type: 'post',
    }).done(function (res) {
        location.href = preUrl + res;
    });
	
	
}







/*********************************************************************************/
/**comma *********************************************************************/
/*********************************************************************************/


//char delete
function removeChar(event) {
  event = event || window.event;
  var keyID = (event.which) ? event.which : event.keyCode;
  if (keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39)
      return;
  else
      //replace
      event.target.value = event.target.value.replace(/[^-\.0-9]/g, "");
}
//add comma
  function comma(obj) {
      var regx = new RegExp(/(-?\d+)(\d{3})/);
      var bExists = obj.indexOf(".", 0);//find index 0
      var strArr = obj.split('.');
      while (regx.test(strArr[0])) {//regx
          //number add comma
          strArr[0] = strArr[0].replace(regx, "$1,$2");
      }
      if (bExists > -1) {
          //. not exist return 01
          obj = strArr[0] + "." + strArr[1];
      } else { //no '.' // 
          obj = strArr[0];
      }
      return obj;
  }
//cancel comma
function uncomma(str) {
  str = "" + str.replace(/,/gi, ''); // delete comma 
  str = str.replace(/(^\s*)|(\s*$)/g, ""); // trim() 
  return (new Number(str));//str to number
}
//input box 콤마달기
function inputNumberFormat(obj) {
  obj.value = comma(obj.value);
}


//input box 콤마풀기 호출
function uncomma_call(){
  var input_value = document.getElementById('input1');
  input_value.value = uncomma(input_value.value);
}



//file upload 
function readUrl(input) {

	if (input.files && input.files[0]) {
		let reader = new FileReader();
		reader.onload = (e) => {
			let imgData = e.target.result;
			let imgName = input.files[0].name;
			input.setAttribute("data-title", imgName);
			console.log(e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
	
	var inputId = input.getAttribute("id");
	if(inputId=="inputPreview"){
		$(".group001").remove()
		
	}else if(inputId=="inputPreview2"){
		$(".group002").remove()
	}

}	




// post edit 


function setPreviewImg(res){
	
	var url = res.result.THUMBNAIL_NM
	
	var fileId = res.result.FILE_ID
	
	/*if(fileName){
		var textbox = $("#inputPreviewLabel");
		textbox.text(fileName);
		
		var inputPreview = $("#inputPreview");
		inputPreview.attr("data-title", fileName);
	}*/
	
	var dvPreview = $("#divImageMediaPreview");
	var img = $("<img />");
	img.attr("style", "width: 100%; height:100%; padding: 10px");
	img.attr("src", path+"/display?filename=" +url);
	dvPreview.html(img);
	
	addOldFile(fileId, "001");
	
}

/*
param.result.
*/
function setCommonInfo(param){
	$("#title").val(param.result.TITLE)
	$("#contents").text(param.result.CONTENTS)
	
	
	var tags = param.result.TAGS;
	//var arrTags = tags.split(",");
	$('input[name="tags"]').val(tags)
	// tags
	$('input[name="tags"]').amsifySuggestags({
		 tagLimit: 5
	});
	
	
	$("input:radio[name='aiYn']:radio[value="+param.result.AI_YN+"]").prop('checked', true);
	$("input:radio[name='anoFunYn']:radio[value="+param.result.ANO_FUN_YN+"]").prop('checked', true);
	
	$('input[name="funStartDt"]').val(param.result.FUN_START_DT);
	$('input[name="funEndDt"]').val(param.result.FUN_END_DT);
	
	$('input[name="tgtAmt"]').val(param.result.TGT_AMT);
	$('input[name="openAmt"]').val(param.result.OPEN_AMT);
	
}

function setTextFile(res){
	var fileName = res.fileList[0].FILE_NM
	var fileId = res.fileList[0].FILE_ID
	if(fileName){
		
		
		var inputPreview = $("#inputPreview2");
		inputPreview.attr("data-title", fileName);
	}
	
	//addOldFile(fileId);
	
	addOldFile(fileId, "002");
}



//첨부파일 미리보기

function setEditPicturePreview(param){
	var dvPreview = $("#divImageMediaPreview2");
	var inputPreview2 = $("#inputPreview2")
 //dvPreview.html("");
	
	var fileList = param.fileList;
	
	for(var i in fileList){
		var div = $("<div class='col-lg-2 filebox row m-0' id='file" + fileNo + "' style='border: 1px solid #d6dee7;' > </div>");
 	var div2 = $("<div class='col-lg-12 text-center mb-3'> </div>");
 	var div2_2 = $("<div class='col-lg-12'> </div>");
 	var div3 = $("<div class='text-center'> </div>");
     var img = $("<img />");
     img.attr("style","max-height:150px;padding: 0px");
     img.attr("src", path+"/display?filename=" + fileList[i].FILE_ID);
     var pTag = $('<p class="name">' + fileList[i].FILE_NM + '</p>')
     var aTag = $('<button type="button" class="delete btn btn-info btn-sm" onclick="deleteFile(' + fileNo + ",'" + fileList[i].FILE_ID+"'" +');">DELETE</button>')
     
     
     div3.append(aTag);
     div3.append(pTag);
     
     
     div2_2.append(div3);
     div2.append(img);
     
     div.append(div2_2);
     div.append(div2);
     dvPreview.append(div);
     
     filesArr.push("before");
     //filesArr.push(file[0]);
     fileNo++;
     
     
     addOldFile(fileList[i].FILE_ID);
		
	}
		
}



//첨부파일 미리보기
function setEdit3DImgPreview(param){
	var dvPreview = $("#divImageMediaPreview3");
	var inputPreview2 = $("#inputPreview3")
	
	var fileList = param.fileList3;
	
	for(var i in fileList){
		var div = $("<div class='col-lg-2 filebox3 row m-0' id='fileImg" + fileNo3 + "' style='border: 1px solid #d6dee7;' > </div>");
		var div2 = $("<div class='col-lg-12 text-center mb-3'> </div>");
		var div2_2 = $("<div class='col-lg-12'> </div>");
		var div3 = $("<div class='text-center'> </div>");
	   var img = $("<img />");
	   img.attr("style","max-height:150px;padding: 0px");
	   img.attr("src", path+"/display?filename=" + fileList[i].FILE_ID);
	   var pTag = $('<p class="name">' + fileList[i].FILE_NM + '</p>')
	   var aTag = $('<button type="button" class="delete btn btn-info btn-sm" onclick="deleteFileImg(' + fileNo3 + ",'" + fileList[i].FILE_ID+"'" +');">DELETE</button>')
	   
	   
	   div3.append(aTag);
	   div3.append(pTag);
	   
	   
	   div2_2.append(div3);
	   div2.append(img);
	   
	   div.append(div2_2);
	   div.append(div2);
	   dvPreview.append(div);
	   
	   filesArr3.push("before");
	   //filesArr.push(file[0]);
	   fileNo3++;
	   
	   
	   addOldFile(fileList[i].FILE_ID);
		
	}
		
}

function setEdit3DFilePreview(param){
	var dvPreview = $("#divImageMediaPreview2");
	
	var fileList = param.fileList;
	
	for(var i in fileList){
		var date = fileList[i].FILE_ID
		var year = date.substring(0,4);
	    var month = date.substring(4,6);
	    var day = date.substring(6,8);

    	
    	var dt = day +'/'+ month +'/'+ year; 
	
	
		var tr = $("<tr class='filebox' id='file" + fileNo + "'></tr>");
		var td1 = $('<td class="align-middle">' + fileList[i].FILE_NM  + '</td>')
		var td2 = $('<td class="align-middle"> '+    dt    + '</td>')
		var td3 = $('<td class="align-middle"><button type="button" class="delete btn btn-info btn-sm" onclick="deleteFile(' + fileNo + ",'" + fileList[i].FILE_ID+"'" +');">DELETE</button></td>')
	
	 	tr.append(td1);
		tr.append(td2);
		tr.append(td3);
		
		dvPreview.append(tr);
        
		filesArr3.push("before");
        fileNo++;
		
		
	}
    
	
}



function addOldFile(fileId , group){
	var oldFileContainer = $("#oldFileContainer");
	var inputDv = $("<input type='hidden' class='oldFile group"+group+"' id='"+fileId+"'  value='"+ fileId +"'/>");
	oldFileContainer.append(inputDv)
	
}


function deleteOldFile(fileId){
	$("input[id='"+fileId+"']").remove()
	
}


// set domain type
function changesType(param){
	var value = $('#changeType').find(":selected").val();
	location.href=path + "/setType/"+value;
}
