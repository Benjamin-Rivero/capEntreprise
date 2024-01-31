function initSortable() {
    const sortableItems = document.querySelectorAll('[data-my-sortable]');
    for (const sortableItem of sortableItems) {
        if (window
            .location
            .search
            .includes(sortableItem.getAttribute('data-my-sortable'))
        ) {
            sortableItem.classList.toggle('invisible');
        }
    }
}

function disableLink(){
    var url = new URL(location.href);
    console.log(url);
    const links = document.querySelectorAll('[link]');
    if(links){
        links.forEach((link)=>{
            if(link.getAttribute("href")===url.pathname){
                var disabledLink = document.createElement("span");
                disabledLink.innerHTML = link.innerHTML;
                link.parentElement.replaceChild(disabledLink,link);
                disabledLink.classList.toggle("isDisabled");
                disabledLink.classList.toggle("btn");
            }
        })
    }
}

window.addEventListener('load', () => {
   initSortable();
   disableLink();
});