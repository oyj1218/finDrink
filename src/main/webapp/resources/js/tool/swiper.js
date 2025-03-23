
const swiper = new Swiper('.swiper-container', {
    direction: 'horizontal',
    speed: 1000,
    loop: true,
    loopedSlides: 2,  
    autoplay: {
        delay: 1000,  
        disableOnInteraction: false,  
    },
    slidesPerView: 2
});

$(".section3-right").on("click", function() {
	alert("hi");
});