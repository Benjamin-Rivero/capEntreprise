
function initFilter(){
    const filter = document.querySelector("input[data-filter]");
    if(filter){
        const submit = filter.nextElementSibling;
        filter.addEventListener('keydown',(e)=>{
            if(e.key==="Enter"){
                filterWith(filter.value);
            }
        })
        if(submit){
            submit.addEventListener('click',()=>{
                filterWith(filter.value);
            })
        }
    }


}

function filterWith(value){
    if(value.trim()){
        location.href="/avis/filtre/"+value;
    }
}

window.addEventListener('load',()=>{
    initFilter();
})