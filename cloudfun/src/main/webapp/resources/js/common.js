
var Utils = {};
var preUrl = "/www";

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

