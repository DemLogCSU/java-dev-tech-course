(async function(){

    let list = [];


    const response = await fetch("http://localhost:8080/api/list", {
        method: "GET",
        headers: { "Accept": "application/json" }
    });
    if (response.ok === true) {

        list = await response.json();

        let oneLink;
        for (oneLink of list) {
            append_table_list(oneLink);
        }
        let elem = document.getElementById("btn-re-add");
        elem.addEventListener("click", onRedirect);
    }
    function append_table_list(oneLink) {
            let links = document.getElementById("list");
            let child = document.createElement("div")
            let link = `<a href=${"http://" + oneLink.shortLink} name=${oneLink.shortLink}>${oneLink.shortLink}</a>`;
            child.innerHTML += link;
            child.innerHTML += `<div>${oneLink.desс}</div>`;
            child.innerHTML += `<div>${oneLink.useCount}</div>`;
            child.innerHTML += `<input type="button" class="btn_del" id="${oneLink.id}" value="Delete">`;
            child.innerHTML += `<input type="button" class="btn_update" id="${oneLink.id}" value="Change">`;
            links.appendChild(child);
            child.querySelector(".btn_del").addEventListener("click", onDelete);
            child.querySelector(".btn_update").addEventListener("click", onUpdate);
        }
    async function onRedirect() {

        window.location.href = '/add';
    }

    async function onDelete(event) {
        const response = await fetch(`/api/${event.target.id}`,{
            method: "DELETE",
            headers: { "Accept": "application/json" }
        });
        if (response.ok === true) {
            window.location.href = '/';
        }
    }
    async function onUpdate(event) {
        window.location.href = `/update/${event.target.id}`;
    }


})();