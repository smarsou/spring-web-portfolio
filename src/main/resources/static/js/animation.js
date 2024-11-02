function fadeOut(el, duration) {

    /*
    * @param el - The element to be faded out.
    * @param duration - Animation duration in milliseconds.
    */

    var step = 10 / duration,
        opacity = 1;
    function next() {
        if (opacity <= 0) { return; }
        el.style.opacity = ( opacity -= step );
        setTimeout(next, 10);
    }
    next();
}
function fadeIn(el, duration) {

    /*
    * @param el - The element to be faded out.
    * @param duration - Animation duration in milliseconds.
    */

    var step = 10 / duration,
        opacity = 0;
    function next() {
        if (opacity >= 1) { return; }
        el.style.opacity = ( opacity += step );
        setTimeout(next, 10);
    }
    next();
}

container = document.getElementById('container')
cl1 = document.getElementById('left-column')
cl2 = document.getElementById('right-column')
cv = document.getElementById('cv')
homebutton = document.getElementById('homebutton')
projects = document.getElementById('projects')
desc = document.getElementById('description')
height = "70vh"
width = "50vw"
// document.getElementsByTagName("body")[0].style.justifyContent = "right"
// d.style.transform = "scale(0.5, 1)"

function home_to_projects(){
    container.style.width = "10vw"
    container.style.height = "100vh"
    container.style.transform = 'translate(45vw)'
    cl2.style.display = 'none'
    cl1.style.display = 'none'
    desc.style.display = 'none'
    homebutton.style.display = 'block'
    projects.style.display = 'block'
    container.style.justifyContent = 'center'
    fadeIn(projects, 300)
    // container.style.borderLeft = "2px solid #8f8f8f"
    // projects.style.transform = 'translate(0,100vh)'
}

function home_to_CV(){
    container.style.width = "10vw"
    container.style.height = "100vh"
    container.style.transform = 'translate(-45vw)'
    cl1.style.display = 'none'
    cl2.style.display = 'none'
    desc.style.display = 'none'
    homebutton.style.display = 'block'
    cv.style.display = "block"
    fadeIn(cv, 300)
}

function home(){
    container.style.width = width
    container.style.height = height
    container.style.transform = 'translate(0)'
    cl1.style.display = 'block'
    cl2.style.display = 'block'
    cv.style.display = "none"
    projects.style.display = "none"
    homebutton.style.display = 'none'
    desc.style.display = 'block'
    container.style.alignItems = "center"
    container.style.border = "0px"
}

// home_to_projects()