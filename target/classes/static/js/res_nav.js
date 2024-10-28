// JS for Header Mobile Menu starts Here
let menuList = document.getElementById("menulist");
menuList.style.maxHeight = "0px";
let menuBtn = document.querySelector(".mob_menu");
function toggleMenu() {
    if (menuList.style.maxHeight == "0px") {
        menuList.style.maxHeight = "300px";
        menuBtn.innerHTML = '<i class="fa-solid fa-xmark"></i> ';

    }
    else {
        menuList.style.maxHeight = "0px";
        menuBtn.innerHTML = '<i class="fa-solid fa-bars" ></i>';
    }
}