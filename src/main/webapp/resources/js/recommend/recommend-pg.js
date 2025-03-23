const slider = document.querySelector('.list-img');
  let isMouseDown = false;
  let startX, scrollLeft;

  slider.addEventListener('mousedown', (e) => {
    isMouseDown = true;
    slider.classList.add('active');

    startX = e.pageX - slider.offsetLeft;
    scrollLeft = slider.scrollLeft;
  });
  
  slider.addEventListener('mouseleave', () => {
    isMouseDown = false;
    slider.classList.remove('active');
  });
  
  slider.addEventListener('mouseup', () => {
    isMouseDown = false;
    slider.classList.remove('active');
  });

  slider.addEventListener('mousemove', (e) => {
    if (!isMouseDown) return;

    e.preventDefault();
    const x = e.pageX - slider.offsetLeft;
    const walk = (x - startX) * 0.7;
    slider.scrollLeft = scrollLeft - walk;
  });
