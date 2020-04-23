let priK = "-----BEGIN RSA PRIVATE KEY-----\n" +
    "MIICXQIBAAKBgQDapAsMdGeyvVe856D/WE8eRMo4+PaMz7QZEQdqX4R+oqRMRaQo\n" +
    "pSk3O1j+Lkm2wWoPe1nCXBWn5GwzwflZnlsExuUpuqQJaHlrE0yAd3n4g0oWiDlc\n" +
    "WXm7Y25NuZidtqq5j01/Pbh/062dmaoE7dQPQmXitr7BOgCWvW6Fhi8SWwIDAQAB\n" +
    "AoGABxlsgmsFMviS8L9UqvXjG8iACHmNB+V2o5m58+pGExaU3yUjv24deH2k1hwK\n" +
    "qLR/DSGkOHtk+J6iJMwXnKFXszCj6D7IksqkfpOAG0ZWKuFZf5pTXa5Fc2FptCWH\n" +
    "MHVHBpOVC86C5vXnbTjrM/5d8VAzUqeM4iG9tddv5C45uwECQQDyAFAxl5TO1lte\n" +
    "6R5DZx2EVKi9HqPTsdmG9QtZI8yZ+Z+BPOJEI1ujhbDwEyc5upKWWBFh9ucRIcZu\n" +
    "/XooU0+zAkEA50nLvOuLT54aSq6ZP3LzHTqbh2D5xOxBRrBo9ycarhPEFkpuWfp/\n" +
    "hQqtGiO/rheZxITQBetCQ9YzxwPbeQ6euQJBAKb+YFhYaW9xrAuKgj+w4EV4xhf7\n" +
    "FMH4cEsYvYP9mCqIfvEHunuaipoEn1yLWv4TKSRmEViwsDK6Q9xDPhClrNECQQCi\n" +
    "eGdjNX8uFZzYe1/xfzkifioE5m1uFJwiIQgLHfAS+pWTJwPvtxTfC0ngA97BV5jP\n" +
    "wJA0qDdrWcwTcDU7C7cZAkAEz9CaIiUe9X3Wy3Z6PF/Gf2pg1icwKkTkR3Odjevy\n" +
    "foVdggcODXEDTO5HgujrLHtcUr+tO/RqIe1ZFyPaRVyY\n" +
    "-----END RSA PRIVATE KEY-----";


function warpPrams(params) {
    var paramsObj = JSON.parse(params);
    var nowTime = getFormatDate();
    paramsObj.timestamp =nowTime;
    var sign = getSign(paramsObj);
    paramsObj.sign =sign;
    return paramsObj;
}


function getSign(params) {
    let content;
    if (typeof params == "object") {
        var arr = [];
        for (var i in params) {
            if(i == 'bizData') {
                var bizData = JSON.stringify(params[i]);
                bizData = JSON.parse(bizData);
                var arrBizData = [];
                let bizDataCont;
                for (var j in bizData) {
                    arrBizData.push(j + "=" + bizData[j]);
                }
                bizDataCont = arrBizData.join("__");
                bizDataCont = bizDataCont.split("__").sort().join("__");
                arr.push(i + "=" + bizDataCont);
                continue;
            }
            arr.push(i + "=" + params[i]);
        }
        content = arr.join("&")
    }
    var urlStr = content.split("&").sort().join("&");
    console.log("frontEnd_beforeSignStr="+urlStr);
    return doSign(urlStr);
}

function doSign(urlStr) {
    let signature=new KJUR.crypto.Signature({alg:"SHA256withRSA",prvkeypem:priK});
    signature.updateString(urlStr);
    let sign = hextob64(signature.sign());
    console.log('frontEnd_sign='+sign);
    return sign;
}

function getFormatDate() {
    var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    // 14位时间格式
    var currentDate = date.getFullYear() + month + strDate+ date.getHours() +  date.getMinutes() +  date.getSeconds();
    return currentDate;
}
