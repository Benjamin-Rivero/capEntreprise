function hoverFocus(){
    const divs = document.querySelectorAll('[not-hovered]');
    if(divs){
        for(const div of divs){
            div.classList.remove('hovered');
            div.classList.remove('not-hovered');
            div.addEventListener('mouseenter',()=>{
                div.classList.toggle('hovered');
                process(div);
            })
            div.addEventListener('mouseleave',()=>{
                process(div);
                div.classList.toggle('hovered');
            })
        }
    }
}

function process(value){
    
    const notHovereds = document.querySelectorAll("div.card:not(.hovered)");
    for(const notHovered of notHovereds){
        notHovered.classList.toggle('not-hovered');
    }
}

window.addEventListener('DOMContentLoaded',()=>{
    hoverFocus();
})