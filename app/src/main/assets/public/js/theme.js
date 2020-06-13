/**
 * Theme Change
 * @param theme
 */
function setTheme(theme){
    if(parseInt(theme) === 17){
        document.getElementById('_theme').className = 'white grey-text text-darken-4';
    } else if(parseInt(theme) === 33){
        document.getElementById('_theme').className = 'grey darken-4 white-text';
    } else {
        document.getElementById('_theme').className = 'white grey-text text-darken-4';
    }
}

/**
 * AppName Change
 * @param text
 */
function setAppName(text) {
    var x = document.getElementById("_appinfo").textContent;
    var c = x.split("%s").length -1;
    var e = document.getElementById("_appinfo");

    for (var i = 0; i < c; i++){
        e.innerHTML = e.innerHTML.replace('%s', text);
    }
}