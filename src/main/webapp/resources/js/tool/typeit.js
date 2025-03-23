document.addEventListener('DOMContentLoaded',() => {
    new TypeIt('.main-3-p', {loop:true})
    .pause(800)
    .delete(".main-3-p",{delay: 1000})
    .type('Thank you for visiting FINDRINK')
    
    .go();

});


document.addEventListener('DOMContentLoaded',() => {
    new TypeIt('.section2-h1-bussiness')
    .go();

});

document.addEventListener('DOMContentLoaded',() => {
    new TypeIt('.section2-h1-user')
    .go();

});


