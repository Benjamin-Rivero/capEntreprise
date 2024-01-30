function hoverFocus(){
    const divs = document.querySelectorAll('[not-hovered]');
    if(divs){
        for(const div of divs){
            div.addEventListener('mouseenter',()=>{
                div.classList.toggle('hovered');
                process(div);
                console.log(div);
            })
            div.addEventListener('mouseleave',()=>{
                
                console.log(div);
                process(div);
                div.classList.toggle('hovered');
            })
        }
    }
}

function process(value){
    
    const notHovereds = document.querySelectorAll("div.game-card:not(.hovered)");
    for(const notHovered of notHovereds){
        notHovered.classList.toggle('not-hovered');
        console.log(notHovered);
    }
}

window.addEventListener('load',()=>{
    hoverFocus();
})