var typed = new Typed(".text", {
  strings: ["IT Engineer", "Data Scientist", "Software Engineer"],
  typeSpeed: 50,
  backSpeed: 50, // Corrected from 'backspeed' to 'backSpeed'
  backDelay: 1000,
  loop: true,
});

const menuToggle = document.querySelector('.menu-toggle');
const navbar = document.querySelector('.navbar');

menuToggle.addEventListener('click', () => {
    navbar.classList.toggle('active');
});